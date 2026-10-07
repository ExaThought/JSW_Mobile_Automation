package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class FooterSteps extends AbstractWebPage {

    private final LocatorReader locatorReader = new LocatorReader("locators/footersteps.yaml");

    /* Scoped to <footer> and matched case-insensitively on purpose.
     *
     * Every footer link renders twice - one 0x0 copy and one visible - and the header renders a
     * third copy whose text is doubled ("Mild SteelMild Steel"). Scoping to <footer> drops the
     * header copy; clickFirstVisible then picks the visible one of the remaining two.
     *
     * The case folding is what fixes the 22 Sep 2026 failures. The Examples table passes the label
     * as authored - "Mild steel", "Structural Steel" - while the footer renders "Mild Steel" and
     * "Structural steel". An exact text() match found ZERO nodes for those two rows and reported
     * them as "Footer link not visible", which read like a missing element rather than a case
     * mismatch. The other eleven rows matched only because their casing happened to agree.
     *
     * XPath 1.0 has no lower-case(), hence translate(). */
    private static final String FOOTER_LINK =
            "//footer//a[translate(normalize-space(.),"
                    + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='%s']";
    private static final String BUY_ONLINE = "//p[text()='%s']";

    /** Footer link locator for a label as written in the feature's Examples table. */
    private static By footerLinkBy(String linkName) {
        return By.xpath(String.format(FOOTER_LINK, linkName.toLowerCase()));
    }
    private static final String SUPPORT_PHONE = "//a[@href=\"tel: +917208055523\"]";
    private static final String SUPPORT_EMAIL = "//a[@href=\"mailto:support@jswonemsme.com\"]";

    // Captured BEFORE click so the Then step can assert the destination
    // independently of any text-mapping table.
    private String expectedHref;
    private String originalWindow;

    public FooterSteps() {
        this.page = super.page != null ? super.page : new com.qapitol.sauron.core.pages.Page();
    }

    // ------------------- Footer Section Visibility -------------------

    @Then("the {string} section should be visible in the footer for web")
    public void the_section_should_be_visible_in_the_footer_for_web(String sectionKey)
            throws InterruptedException {
        softAssert.assertTrue(
                isAnyVisible(By.xpath(String.format(BUY_ONLINE, sectionKey))),
                "Footer section heading not visible: " + sectionKey
        );
    }

    // ------------------- Footer Link Visibility -------------------

    @Then("the footer link {string} should be visible for web")
    public void the_footer_link_should_be_visible_for_web(String linkName) {
        try {
            log.info("Verifying visibility of footer link: " + linkName);
            // First step after login: pre-prod opens the business details dialog a few seconds
            // after the page loads. While it is open the page cannot scroll, so every footer link
            // below the fold reads as not visible and clicks land on the overlay.
            closeBlockingDialogsWithin(10);

            By footerLink = footerLinkBy(linkName);

            softAssert.assertTrue(
                    isAnyVisible(footerLink),
                    "Footer link not visible: " + linkName
            );

            log.info("Footer link is visible: " + linkName);

        } catch (Exception e) {
            log.info("Exception occurred while verifying footer link visibility: " + linkName + " | " + e.getMessage());
            softAssert.fail("Exception while verifying footer link visibility: " + linkName + " | " + e.getMessage());
        }
    }

    // ------------------- Support Info -------------------

    @Then("the support phone number should be visible for web")
    public void the_support_phone_number_should_be_visible_for_web()
            throws InterruptedException {

        softAssert.assertTrue(
                isAnyVisible(By.xpath(SUPPORT_PHONE)),
                "Support phone number not visible"
        );
    }

    @Then("the support email address should be visible for web")
    public void the_support_email_address_should_be_visible_for_web()
            throws InterruptedException {

        softAssert.assertTrue(
                isAnyVisible(By.xpath(SUPPORT_EMAIL)),
                "Support email address not visible"
        );
    }

    // ------------------- Scroll & Click -------------------

    /**
     * Resolves a footer locator key to the element the user can actually see.
     *
     * <p>findElement() returned the first of four copies, which is always a hidden 0x0 duplicate.
     * That made element.isDisplayed() false in scrollToElementAndClick(), so it burned its ten
     * retries and never clicked, and it made getAttribute("href") read the hidden node.
     */
    private WebElement findFooterElement(String yamlKey) {
        String xpath = locatorReader.getXpath("elements." + yamlKey + ".en_US.web");
        return firstVisible(By.xpath(xpath));
    }

    /** True when the element behind a footer locator key is rendered. Never throws. */
    private boolean isFooterElementVisible(String yamlKey) {
        return isAnyVisible(By.xpath(locatorReader.getXpath("elements." + yamlKey + ".en_US.web")));
    }

    private void scrollToElementAndClick(String yamlKey) {

        WebElement element = null;
        int attempts = 0;

        while (attempts < 10) {
            try {
                element = findFooterElement(yamlKey);

                if (element.isDisplayed()) {

                    ((JavascriptExecutor) Grid.driver()).executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            element
                    );

                    Thread.sleep(500);

                    element.click();
                    System.out.println("Clicked element: " + yamlKey);
                    return;
                }

            } catch (Exception ignored) {}

            try { Thread.sleep(500); }
            catch (InterruptedException ignored) {}

            attempts++;
        }

        throw new RuntimeException("Element not clickable: " + yamlKey);
    }
    // ------------------- Footer Link Click -------------------

    @When("the user clicks on the footer link {string} for web")
    public void the_user_clicks_on_the_footer_link_for_web(String linkName) {
        try {
            log.info("Clicking on footer link: " + linkName);
            // Again here: the dialog can open between the visibility check and the click, which
            // is how "Blogs" was clicked yet never navigated.
            closeBlockingDialogsWithin(BLOCKING_DIALOG_QUICK_WAIT_SECONDS);
            By footerLink = footerLinkBy(linkName);
            softAssert.assertTrue(
                    isAnyVisible(footerLink),
                    "Footer link not displayed: " + linkName
            );

            // clickFirstVisible, not clickButton: clickButton resolves the first match, which is a
            // hidden 0x0 copy, so the click silently lands on nothing. No separate scroll either:
            // scrollToCenter(By) has the same first-match problem, and clickFirstVisible already
            // scrolls the VISIBLE copy to the centre before clicking - keep that if it is refactored.
            clickFirstVisible(footerLink);

            log.info("Successfully clicked on footer link: " + linkName);

        } catch (Exception e) {
            log.info("Exception occurred while clicking footer link: " + linkName + " | " + e.getMessage());
            softAssert.fail("Failed to click footer link: " + linkName + " | " + e.getMessage());
        }
    }

    // ------------------- Verification -------------------

    @Then("the user should be redirected to the corresponding page for web {string}")
    public void the_user_should_be_redirected_to_the_corresponding_page_for_web(String text) {
        try {
            log.info("Verifying redirection contains text: " + text);

            WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

            /* Named: the catch below reports e.getMessage(), so without this the failure reads
             * "Exception while verifying redirection: ... waiting for
             * FooterSteps$$Lambda$1412/0x0000000800b51440@17b692f1" - which is what two footer
             * scenarios reported on 3 Sep 2026, naming neither the expected text nor the URL. */
            wait.withMessage("URL never contained '" + text + "' within 10s - still at "
                            + Grid.driver().getCurrentUrl())
                    .until(driver -> driver.getCurrentUrl().toLowerCase().contains(text.toLowerCase()));

            String currentUrl = Grid.driver().getCurrentUrl();
            log.info("Current URL after navigation: " + currentUrl);

            softAssert.assertTrue(
                    currentUrl.toLowerCase().contains(text.toLowerCase()),
                    "Expected URL to contain: " + text + " but found: " + currentUrl
            );

            log.info("URL redirection verified successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying page redirection: " + e.getMessage());
            softAssert.fail("Exception while verifying redirection: " + e.getMessage());
        }
    }

    @Then("the {string} should be visible in the footer for web")
    public void verify_element_visible(String key) throws InterruptedException {

        // isFooterElementVisible, not waitAndCheckIsElementVisible: the latter resolves the first
        // match, which is a hidden duplicate for every element in this footer.
        softAssert.assertTrue(
                isFooterElementVisible(key),
                "Element not visible in footer: " + key
        );
    }

    @When("the user clicks on social icon {string}")
    public void click_social_icon(String iconName) {
        closeBlockingDialogsWithin(10);

        String key = getSocialKey(iconName);

        // target="_blank" on social anchors — capture the original window
        // handle and the href, then click and switch to the new tab in the
        // Then step.
        expectedHref = findFooterElement(key).getAttribute("href");
        originalWindow = Grid.driver().getWindowHandle();
        scrollToElementAndClick(key);
    }

    @When("the user clicks on app icon {string}")
    public void click_app_icon(String appName) {

        String key = getAppKey(appName);
        expectedHref = findFooterElement(key).getAttribute("href");
        originalWindow = Grid.driver().getWindowHandle();
        scrollToElementAndClick(key);
    }

    private String getSocialKey(String name) {

        switch (name.toLowerCase()) {
            case "youtube": return "socialYoutubeIcon";
            case "linkedin": return "socialLinkedInIcon";
            case "instagram": return "socialInstagramIcon";
            case "facebook": return "socialFacebookIcon";
            default: throw new IllegalArgumentException("Invalid social icon: " + name);
        }
    }

    private String getAppKey(String name) {

        switch (name.toLowerCase()) {
            case "google play": return "googlePlayIcon";
            case "app store": return "appStoreIcon";
            default: throw new IllegalArgumentException("Invalid app icon: " + name);
        }
    }

    @Then("the user should be redirected to the correct social page {string}")
    public void verify_social_redirect(String platform) {

        // Wait for the new tab to open, then switch to it. This one is NOT caught, so its message
        // goes straight to the report - name the platform rather than a lambda.
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                .withMessage("the " + platform + " social link opened no new tab within 10s"
                        + " (still " + Grid.driver().getWindowHandles().size() + " window handle(s))")
                .until(d -> d.getWindowHandles().size() > 1);

        Set<String> handles = Grid.driver().getWindowHandles();
        String newWindow = handles.stream()
                .filter(h -> !h.equals(originalWindow))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No new tab opened after click"));

        Grid.driver().switchTo().window(newWindow);

        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                    .until(d -> d.getCurrentUrl().toLowerCase().contains(platform.toLowerCase()));
        } catch (Exception ignored) {}

        String url = Grid.driver().getCurrentUrl();
        boolean ok = url.toLowerCase().contains(platform.toLowerCase());

        // Always close the popup and switch back so subsequent clicks
        // in the same scenario operate on the original JSW tab.
        try { Grid.driver().close(); } catch (Exception ignored) {}
        Grid.driver().switchTo().window(originalWindow);

        Assert.assertTrue(ok,
                "Expected " + platform + " URL but landed at: " + url);
    }

    @Then("the user should be redirected to the correct app store {string}")
    public void verify_app_redirect(String store) {

        // The badges are anchored on QA, so a new tab is expected. If none opens, the anchor
        // has regressed - keep the explicit failure message rather than passing silently.
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(5))
                    .until(d -> d.getWindowHandles().size() > 1);
        } catch (Exception ignored) {}

        Set<String> handles = Grid.driver().getWindowHandles();
        if (handles.size() <= 1) {
            Assert.fail("App store icon click did not open a new tab — the badge appears to have no anchor (website bug). Expected store: " + store);
        }

        String newWindow = handles.stream()
                .filter(h -> !h.equals(originalWindow))
                .findFirst()
                .orElseThrow();
        Grid.driver().switchTo().window(newWindow);
        String url = Grid.driver().getCurrentUrl();
        boolean ok;
        switch (store.toLowerCase()) {
            case "playstore": ok = url.contains("play.google"); break;
            case "appstore":  ok = url.contains("apple"); break;
            default:          ok = false;
        }
        try { Grid.driver().close(); } catch (Exception ignored) {}
        Grid.driver().switchTo().window(originalWindow);
        Assert.assertTrue(ok, "Expected " + store + " URL but landed at: " + url);
    }
}