package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

public class FooterSteps extends AbstractWebPage {

    private final LocatorReader locatorReader =
            new LocatorReader("locators/footersteps.yaml");

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

        Assert.assertTrue(
                waitAndCheckIsElementVisible(sectionKey),
                "Footer section not visible: " + sectionKey
        );
    }

    // ------------------- Footer Link Visibility -------------------

    @Then("the footer link {string} should be visible for web")
    public void the_footer_link_should_be_visible_for_web(String linkName)
            throws InterruptedException {

        // The feature passes a human label ("Mild steel"), not a yaml key.
        // Map to the actual locator key before looking up.
        String yamlKey = getFooterYamlKey(linkName);
        Assert.assertTrue(
                waitAndCheckIsElementVisible(yamlKey),
                "Footer link not visible: " + linkName + " (key=" + yamlKey + ")"
        );
    }

    // ------------------- Support Info -------------------

    @Then("the support phone number should be visible for web")
    public void the_support_phone_number_should_be_visible_for_web()
            throws InterruptedException {

        Assert.assertTrue(
                waitAndCheckIsElementVisible("supportPhone"),
                "Support phone number not visible"
        );
    }

    @Then("the support email address should be visible for web")
    public void the_support_email_address_should_be_visible_for_web()
            throws InterruptedException {

        Assert.assertTrue(
                waitAndCheckIsElementVisible("supportEmail"),
                "Support email address not visible"
        );
    }

    // ------------------- Scroll & Click -------------------

    private WebElement findFooterElement(String yamlKey) {
        String xpath = locatorReader.getXpath("elements." + yamlKey + ".en_US.web");
        return Grid.driver().findElement(By.xpath(xpath));
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

    // ------------------- Map feature names to YAML keys -------------------

    private String getFooterYamlKey(String linkName) {

        switch (linkName.trim().toLowerCase()) {

            case "mild steel": return "mildSteelLink";
            case "structural steel": return "structuralSteelLink";
            case "tmt": return "tmtLink";
            case "cement": return "cementLink";

            case "about us": return "aboutUsLink";
            case "blogs": return "blogsLink";
            case "sitemap": return "siteMapLink";
            case "one-helix": return "jswOneTmtLink";

            case "help & support": return "helpSupportLink";

            case "terms & conditions": return "termsAndConditions";
            case "privacy policy": return "privacyPolicy";
            case "return policy": return "returnPolicy";

            default:
                throw new IllegalArgumentException("Unknown footer link: " + linkName);
        }
    }

    // ------------------- Footer Link Click -------------------

    @When("the user clicks on the footer link {string} for web")
    public void the_user_clicks_on_the_footer_link_for_web(String linkName)
            throws InterruptedException {

        String yamlKey = getFooterYamlKey(linkName);

        // Capture the destination from the anchor BEFORE clicking, so the
        // Then step can verify the URL really changed to this target.
        expectedHref = findFooterElement(yamlKey).getAttribute("href");
        scrollToElementAndClick(yamlKey);

        System.out.println("Clicked on footer link: " + linkName + " -> " + expectedHref);
    }

    // ------------------- Verification -------------------

    @Then("the user should be redirected to the corresponding page for web")
    public void the_user_should_be_redirected_to_the_corresponding_page_for_web() {

        Assert.assertNotNull(expectedHref, "expectedHref was not captured before click");

        // Allow up to 10s for the SPA to finish routing
        try {
            new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains(extractPath(expectedHref)));
        } catch (Exception ignored) {}

        String currentUrl = Grid.driver().getCurrentUrl();
        String expectedPath = extractPath(expectedHref);

        Assert.assertTrue(
                currentUrl.contains(expectedPath),
                "Expected redirect to " + expectedPath + " but landed at " + currentUrl
        );
        System.out.println("Current URL: " + currentUrl);
    }

    private String extractPath(String hrefOrUrl) {
        if (hrefOrUrl == null) return "";
        // Strip the origin so we compare just the path
        int idx = hrefOrUrl.indexOf("://");
        if (idx < 0) return hrefOrUrl; // already a path
        int pathStart = hrefOrUrl.indexOf('/', idx + 3);
        return pathStart < 0 ? "/" : hrefOrUrl.substring(pathStart);
    }

    @Then("the {string} should be visible in the footer for web")
    public void verify_element_visible(String key) throws InterruptedException {

        Assert.assertTrue(
                waitAndCheckIsElementVisible(key),
                "Element not visible: " + key
        );
    }

    @When("the user clicks on social icon {string}")
    public void click_social_icon(String iconName) {

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
        // App-store badges are <img> with no parent anchor on preprod, so
        // there's nothing to capture. The scenario that depends on actual
        // navigation is tagged @ignore in the feature file.
        scrollToElementAndClick(key);
    }

    private String getSocialKey(String name) {

        switch (name.toLowerCase()) {
            case "youtube": return "socialYoutubeIcon";
            case "linkedin": return "socialLinkedInIcon";
            case "instagram": return "socialInstagramIcon";
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

        // Wait for the new tab to open, then switch to it.
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(10))
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

        // App badges on preprod are <img> tags without a parent anchor, so
        // clicking them never opens a new tab. Wait briefly and fail with
        // a clear message — the linked feature scenario is @ignored.
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
