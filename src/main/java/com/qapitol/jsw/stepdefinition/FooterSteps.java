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
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class FooterSteps extends AbstractWebPage {

    private final LocatorReader locatorReader =
            new LocatorReader("locators/footersteps.yaml");

    SoftAssert softAssert = new SoftAssert();

    public FooterSteps() {
        this.page = super.page != null ? super.page : new com.qapitol.sauron.core.pages.Page();
    }

    // ------------------- Footer Section Visibility -------------------

    @Then("the {string} section should be visible in the footer for web")
    public void the_section_should_be_visible_in_the_footer_for_web(String sectionKey)
            throws InterruptedException {

        waitAndCheckIsElementVisible(sectionKey);
    }

    // ------------------- Footer Link Visibility -------------------

    @Then("the footer link {string} should be visible for web")
    public void the_footer_link_should_be_visible_for_web(String linkKey)
            throws InterruptedException {

        softAssert.assertTrue(
                waitAndCheckIsElementVisible(linkKey),
                "Footer link not visible: " + linkKey
        );
    }

    // ------------------- Support Info -------------------

    @Then("the support phone number should be visible for web")
    public void the_support_phone_number_should_be_visible_for_web()
            throws InterruptedException {

        softAssert.assertTrue(
                waitAndCheckIsElementVisible("supportPhone"),
                "Support phone number not visible"
        );
    }

    @Then("the support email address should be visible for web")
    public void the_support_email_address_should_be_visible_for_web()
            throws InterruptedException {

        softAssert.assertTrue(
                waitAndCheckIsElementVisible("supportEmail"),
                "Support email address not visible"
        );
    }

    // ------------------- Scroll & Click -------------------

    private void scrollToElementAndClick(String yamlKey) {

        WebElement element = null;
        int attempts = 0;

        while (attempts < 10) {
            try {
                String xpath =
                        locatorReader.getXpath("elements." + yamlKey + ".en_US.web");

                element = Grid.driver().findElement(By.xpath(xpath));

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
            case "site map": return "siteMapLink";
            case "jsw one tmt": return "jswOneTmtLink";

            case "help support": return "helpSupportLink";

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
        scrollToElementAndClick(yamlKey);

        System.out.println("Clicked on footer link: " + linkName);
    }

    // ------------------- Verification -------------------

    @Then("the user should be redirected to the corresponding page for web")
    public void the_user_should_be_redirected_to_the_corresponding_page_for_web() {

        String currentUrl = Grid.driver().getCurrentUrl();

        Assert.assertTrue(
                currentUrl.startsWith("http"),
                "Footer navigation redirection failed"
        );

        System.out.println("Current URL: " + currentUrl);
    }
}
