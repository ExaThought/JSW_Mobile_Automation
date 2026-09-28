package com.qapitol.jsw.utils;

import com.qapitol.sauron.core.Grid;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Reads a Yopmail inbox in a throwaway browser tab.
 *
 * <p>WHY THIS EXISTS AT ALL: nothing in this suite could read a mailbox, and the invited-user
 * addresses are minted by {@link CustomUtil#generateEmail()} as {@code @yopmail.com}. Yopmail
 * has no API, so the only way to assert delivery is to drive its web UI.
 *
 * <p>TREAT THIS AS THE WEAKEST LINK IN ANY SCENARIO THAT USES IT. Three reasons, in order of how
 * likely they are to bite:
 *
 * <ul>
 *   <li>Yopmail serves Google reCAPTCHA. It is dormant on a clean IP but appears under load or
 *       from a shared CI egress address, and a CAPTCHA cannot and must not be automated around -
 *       when it fires, {@link #waitForMail} times out and the scenario fails for a reason that
 *       has nothing to do with JSW One.</li>
 *   <li>It is a third-party UI with no contract with us. The inbox URL, the {@code #nbmail}
 *       counter and the {@code #ifinbox} list frame below were read off the live site, but any
 *       of them can change without notice.</li>
 *   <li>Mail delivery is asynchronous, so every assertion here is a poll with a timeout rather
 *       than a fact.</li>
 * </ul>
 *
 * <p>If an internal notification or email-event API becomes available, replace the body of this
 * class and the step definitions above it need no change - that is why the mailbox lives behind
 * these four methods instead of inside the steps.
 */
public final class YopmailInbox {

    /** Verified against the live site: this URL opens the named inbox directly. */
    private static final String INBOX_URL = "https://yopmail.com/en/?%s";

    /** Verified: lives in the TOP document and reads e.g. "0 mail", "1 mail". */
    private static final By MAIL_COUNT = By.id("nbmail");

    /** Verified: the message-list frame. The reading pane is #ifmail, added once a mail opens. */
    private static final String LIST_FRAME = "ifinbox";
    private static final String MAIL_FRAME = "ifmail";

    private static final Duration POLL_INTERVAL = Duration.ofSeconds(5);

    /** Landing page, where the inbox name is typed rather than passed in the URL. */
    private static final String SITE_URL = "https://yopmail.com/";

    /** Verified on the live site: the inbox field, its submit button and the refresh control. */
    private static final By INBOX_FIELD = By.id("login");
    private static final By CHECK_INBOX_BUTTON = By.cssSelector("button[title*='Check Inbox']");
    private static final By REFRESH_BUTTON = By.id("refresh");

    private YopmailInbox() {
        // utility class
    }

    // =================================================================================
    // Driving the mailbox in the CURRENT window
    //
    // Separate from the tab-based methods below on purpose. These are for a scenario that has
    // FINISHED with the CCP window and is now acting as the invited user: the invitee is a
    // different person, and reusing the primary user's window would leave their session, cookies
    // and localStorage in place while the invitation is accepted - which is not what a real
    // invitee does, and can let an acceptance flow authenticate as the wrong user.
    // =================================================================================

    /** Opens the Yopmail landing page in the current window. */
    public static void openSite() {
        Grid.driver().get(SITE_URL);
        sleep(2000);
    }

    /**
     * Types the inbox name and opens it.
     *
     * <p>Typed rather than appended to the URL because that is what a person does, and because the
     * landing page sets a cookie on submit that the direct URL does not - inboxes opened by URL
     * have been seen to come back empty on a fresh browser profile.
     */
    public static void openInboxByTyping(String address) {
        String inbox = inboxName(address);
        WebElement field = Grid.driver().findElement(INBOX_FIELD);
        field.clear();
        field.sendKeys(inbox);
        log.info("Opening Yopmail inbox: " + inbox);
        try {
            Grid.driver().findElement(CHECK_INBOX_BUTTON).click();
        } catch (Exception e) {
            field.sendKeys(Keys.ENTER);
        }
        sleep(3000);
    }

    /** Clicks the inbox refresh control. Mail can land a few seconds after the invite is sent. */
    public static void refreshInbox() {
        try {
            Grid.driver().findElement(REFRESH_BUTTON).click();
            sleep(2500);
        } catch (Exception e) {
            log.warning("Could not click the Yopmail refresh control: " + e.getMessage());
            Grid.driver().navigate().refresh();
            sleep(2500);
        }
    }

    /** How many messages the inbox currently reports. */
    public static int messageCount() {
        return mailCount();
    }

    /**
     * Opens the newest message and leaves the driver INSIDE the reading frame, so the caller can
     * locate elements in the mail body directly.
     *
     * @return false when there was no message to open.
     */
    public static boolean openFirstMailAndFocusBody() {
        if (mailCount() <= 0) {
            return false;
        }
        openNewestMessage();
        return switchToFrame(MAIL_FRAME) || switchToFrame(LIST_FRAME);
    }

    /** Leaves whichever Yopmail frame the driver is in. */
    public static void leaveMailBody() {
        try {
            Grid.driver().switchTo().defaultContent();
        } catch (Exception ignored) {
            // nothing to leave
        }
    }

    /**
     * Polls the inbox until at least one message arrives.
     *
     * @param address the full address; only the part before @ names the Yopmail inbox.
     * @return true if a message arrived within the timeout.
     */
    public static boolean waitForMail(String address, Duration timeout) {
        String inbox = inboxName(address);
        String original = Grid.driver().getWindowHandle();
        String tab = openTab(String.format(INBOX_URL, inbox));
        try {
            Instant deadline = Instant.now().plus(timeout);
            int reloads = 0;
            while (Instant.now().isBefore(deadline)) {
                if (mailCount() > 0) {
                    log.info("Yopmail inbox '" + inbox + "' has mail after " + reloads + " reload(s)");
                    return true;
                }
                sleep(POLL_INTERVAL.toMillis());
                Grid.driver().navigate().refresh();
                reloads++;
            }
            log.warning("No mail in Yopmail inbox '" + inbox + "' within " + timeout.toSeconds()
                    + "s. If this is a CI run, check the failure screenshot for a CAPTCHA before"
                    + " suspecting the product - Yopmail serves one from shared IP addresses.");
            return false;
        } finally {
            closeTab(tab, original);
        }
    }

    /**
     * The visible text of the newest message, or an empty string when the inbox is empty.
     *
     * <p>Opens its own tab rather than assuming {@link #waitForMail} left one open, so the two
     * can be called independently and in either order.
     */
    public static String latestMailText(String address) {
        return readLatestMail(address, false);
    }

    /**
     * The href of the first link in the newest message, or null when there is none.
     *
     * <p>"First link" is deliberate rather than matching on link text: the accept call-to-action
     * is the primary link in an invitation mail, and matching on wording would break the moment
     * the template says "Get started" instead of "Accept invitation".
     */
    public static String latestMailFirstLink(String address) {
        String href = readLatestMail(address, true);
        return (href == null || href.isBlank()) ? null : href;
    }

    private static String readLatestMail(String address, boolean wantLink) {
        String inbox = inboxName(address);
        String original = Grid.driver().getWindowHandle();
        String tab = openTab(String.format(INBOX_URL, inbox));
        try {
            if (mailCount() <= 0) {
                log.warning("Yopmail inbox '" + inbox + "' is empty - nothing to read");
                return wantLink ? null : "";
            }
            openNewestMessage();
            if (!switchToFrame(MAIL_FRAME) && !switchToFrame(LIST_FRAME)) {
                log.warning("Could not switch to the Yopmail reading pane");
                return wantLink ? null : "";
            }
            if (!wantLink) {
                return Grid.driver().findElement(By.tagName("body")).getText();
            }
            List<WebElement> links = Grid.driver().findElements(By.tagName("a"));
            for (WebElement link : links) {
                String href = link.getAttribute("href");
                // Yopmail wraps its own chrome in anchors too; only an absolute http(s) link that
                // is not yopmail's own can be the invitation.
                if (href != null && href.startsWith("http") && !href.contains("yopmail.com")) {
                    return href;
                }
            }
            return null;
        } catch (Exception e) {
            log.warning("Failed to read the Yopmail message: " + e.getMessage());
            return wantLink ? null : "";
        } finally {
            Grid.driver().switchTo().defaultContent();
            closeTab(tab, original);
        }
    }

    /** Clicks the first message row in the list frame. */
    private static void openNewestMessage() {
        if (!switchToFrame(LIST_FRAME)) {
            return;
        }
        try {
            // Yopmail renders each message as a div.m; the newest is first. Falls back to any
            // clickable row so a markup tweak degrades into a wrong-mail failure rather than a
            // NoSuchElementException with no context.
            List<WebElement> rows = Grid.driver().findElements(By.cssSelector("div.m"));
            if (rows.isEmpty()) {
                rows = Grid.driver().findElements(By.cssSelector("button.lm, .m, .mail"));
            }
            if (rows.isEmpty()) {
                log.warning("No message rows found in the Yopmail list frame");
                return;
            }
            rows.get(0).click();
            sleep(1500);
        } finally {
            Grid.driver().switchTo().defaultContent();
        }
    }

    /** "0 mail" / "3 mails" as an int, or 0 when the counter cannot be read. */
    private static int mailCount() {
        try {
            String text = Grid.driver().findElement(MAIL_COUNT).getText();
            String digits = text.replaceAll("[^0-9]", "");
            return digits.isEmpty() ? 0 : Integer.parseInt(digits);
        } catch (Exception e) {
            return 0;
        }
    }

    private static boolean switchToFrame(String id) {
        try {
            Grid.driver().switchTo().defaultContent();
            Grid.driver().switchTo().frame(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Opens a tab on the given URL and returns its handle. */
    private static String openTab(String url) {
        WebDriver driver = Grid.driver();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
        sleep(2000);
        return driver.getWindowHandle();
    }

    /**
     * Closes the mailbox tab and returns to the tab the scenario was on.
     *
     * <p>Not optional: the steps either side of a mailbox check run against the CCP page, and a
     * driver left pointing at yopmail.com fails them with locator errors that name JSW One
     * elements while looking at somebody else's site.
     */
    private static void closeTab(String tab, String returnTo) {
        try {
            WebDriver driver = Grid.driver();
            if (driver.getWindowHandle().equals(tab)) {
                driver.close();
            }
            driver.switchTo().window(returnTo);
        } catch (Exception e) {
            log.warning("Could not return to the original tab: " + e.getMessage());
        }
    }

    /** The inbox name Yopmail uses - the local part, with any domain stripped. */
    private static String inboxName(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalStateException("No address to check: the scenario asked for an"
                    + " invitation mailbox before an invitation had been sent, so there is no"
                    + " invited address in ScenarioContext.");
        }
        int at = address.indexOf('@');
        return at < 0 ? address.trim() : address.substring(0, at).trim();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
