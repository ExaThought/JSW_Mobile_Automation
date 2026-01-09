package com.qapitol.jsw.stepdefinition.msiteStepDefinition;

import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class FooterSteps extends AbstractWebPage {

    // Reference to HeaderSteps for shared methods
    HeaderSteps hs = new HeaderSteps();

    // ---------- Footer Section Headers ----------
    public static final String BUY_ONLINE_SECTION = "buyOnlineSection";
    public static final String MSME_SECTION = "msmeSection";
    public static final String SUPPORT_SECTION = "supportSection";
    public static final String POLICY_SECTION = "policySection";

    // ---------- Buy Online Links ----------
    public static final String MILD_STEEL = "mildSteelLink";
    public static final String STRUCTURAL_STEEL = "structuralSteelLink";
    public static final String TMT = "tmtLink";
    public static final String CEMENT = "cementLink";

    // ---------- MSME Links ----------
    public static final String ABOUT_US = "aboutUsLink";
    public static final String BLOGS = "blogsLink";
    public static final String SITE_MAP = "siteMapLink";
    public static final String JSW_ONE_TMT = "jswOneTmtLink";

    // ---------- Support ----------
    public static final String SUPPORT_PHONE = "supportPhone";
    public static final String SUPPORT_EMAIL = "supportEmail";
    // ---------- Support ----------
    public static final String HELP_SUPPORT = "helpSupportLink";

    // ---------- Policy ----------
    public static final String TERMS = "termsAndConditions";
    public static final String PRIVACY = "privacyPolicy";
    public static final String RETURN_POLICY = "returnPolicy";

    SoftAssert softAssert = new SoftAssert();

    // ------------------- Constructor -------------------
    public FooterSteps() {
        // Initialize the page object to avoid NullPointerException
        this.page = super.page != null ? super.page : new com.qapitol.sauron.core.pages.Page();
    }

    // ---------------------------------------------------
    // Footer Section Visibility
    // ---------------------------------------------------
    @Then("the {string} section should be visible in the footer")
    public void the_section_should_be_visible_in_the_footer(String sectionKey) throws InterruptedException {
        hs.user_open_the_msite();
        waitAndCheckIsElementVisible(sectionKey);
    }

    // ---------------------------------------------------
    // Footer Link Visibility
    // ---------------------------------------------------
    @Then("the footer link {string} should be visible")
    public void the_footer_link_should_be_visible(String linkKey) throws InterruptedException {
        hs.user_open_the_msite();
        softAssert.assertTrue(
                waitAndCheckIsElementVisible(linkKey),
                "Footer link not visible: " + linkKey
        );
    }

    // ---------------------------------------------------
    // Support Info
    // ---------------------------------------------------
    @Then("the support phone number should be visible")
    public void the_support_phone_number_should_be_visible() throws InterruptedException {
        hs.user_open_the_msite();
        softAssert.assertTrue(
                waitAndCheckIsElementVisible(SUPPORT_PHONE),
                "Support phone number not visible"
        );
    }

    @Then("the support email address should be visible")
    public void the_support_email_address_should_be_visible() throws InterruptedException {
        hs.user_open_the_msite();
        softAssert.assertTrue(
                waitAndCheckIsElementVisible(SUPPORT_EMAIL),
                "Support email address not visible"
        );
    }



    // ------------------- JS Scroll + Click -------------------
    private void scrollToElementAndClick(String xpath) {
        WebElement element = null;
        int attempts = 0;

        while (attempts < 10) { // retry ~10s
            try {
                element = Grid.driver().findElement(By.xpath(xpath));
                if (element.isDisplayed()) break; // element found and visible
            } catch (Exception e) {
                // element not found yet
            }
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            attempts++;
        }

        if (element != null) {
            ((JavascriptExecutor) Grid.driver())
                    .executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", element);
            System.out.println("Clicked element: " + xpath);
        } else {
            System.err.println("Element not found after retrying: " + xpath);
        }
    }


    // ------------------- Map link names to XPath from YAML -------------------
    private String getFooterLinkXPath(String linkName) {
        switch (linkName.toLowerCase()) {
            case "buy online": return "//*[contains(normalize-space(text()),'Buy online')]";
            case "msme": return "//p[contains(text(),'JSW One MSME')]";
            case "support": return "//*[contains(normalize-space(text()),'Support')]";
            case "policy": return "//*[contains(normalize-space(text()),'Policy')]";

            case "mild steel": return "//*[contains(normalize-space(text()),'Mild steel')]";
            case "structural steel": return "//*[contains(normalize-space(text()),'Structural Steel')]";
            case "tmt": return "//*[contains(normalize-space(text()),'TMT')]";
            case "cement": return "//*[contains(normalize-space(text()),'Cement')]";

            case "about us": return "//*[contains(normalize-space(text()),'About us')]";
            case "blogs": return "//*[contains(normalize-space(text()),'Blogs')]";
            case "site map": return "//*[contains(normalize-space(text()),'Site map')]";
            case "jsw one tmt": return "//*[contains(normalize-space(text()),'JSW One TMT')]";

            case "support phone": return "//*[contains(text(),'+91')]";
            case "support email": return "//*[contains(text(),'@')]";
            case "help support": return "//*[contains(normalize-space(text()),'Help & support')]";

            case "terms & conditions": return "//*[contains(normalize-space(text()),'Terms & conditions')]";
            case "privacy policy": return "//*[contains(normalize-space(text()),'Privacy policy')]";
            case "return policy": return "//*[contains(normalize-space(text()),'Return Policy')]";

            default:
                throw new IllegalArgumentException("No XPath mapping for footer link: " + linkName);
        }
    }

    // ------------------- Footer Link Click -------------------
    @When("the user clicks on the footer link {string}")
    public void the_user_clicks_on_the_footer_link(String linkName) throws InterruptedException {
        hs.user_open_the_msite();// open site
        String xpath = getFooterLinkXPath(linkName);
        scrollToElementAndClick(xpath);
        System.out.println("clicked on footer link: " + linkName);
    }

    // ------------------- Verification -------------------
    @Then("the user should be redirected to the corresponding page")
    public void the_user_should_be_redirected_to_the_corresponding_page() {
        String currentUrl = Grid.driver().getCurrentUrl();
        Assert.assertTrue(currentUrl.startsWith("http"), "Footer navigation redirection failed");
        System.out.println("Current URL: " + currentUrl);
    }
}
