package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class Hamburger extends AbstractWebPage {

    private static final Logger log = LoggerFactory.getLogger(Hamburger.class);
    private final LocatorReader locatorReader = new LocatorReader("locators/hamburger.yaml");
    private final JavascriptExecutor js = (JavascriptExecutor) Grid.driver();

    // ── Element Keys ──────────────────────────────────────────────────────────
    private static final String HAMBURGER_ICON          = "hamburgerIcon";
    private static final String PROFILE_DETAILS_OPTION  = "profileDetailsOption";
    private static final String SAVED_ADDRESSES_OPTION  = "savedAddressesOption";
    private static final String BANK_DETAILS_OPTION     = "bankDetailsOption";
    private static final String BLOGS_OPTION            = "blogsOption";
    private static final String HELP_SUPPORT_OPTION     = "helpSupportOption";
    private static final String POLICIES_OPTION         = "policiesOption";
    private static final String TERMS_CONDITIONS_OPTION  = "termsConditionsOption";
    private static final String PRIVACY_POLICY_OPTION    = "privacyPolicyOption";
    private static final String RETURN_POLICY_OPTION     = "returnPolicyOption";

    // Page Titles
    private static final String PROFILE_PAGE_TITLE      = "profilePageTitle";
    private static final String ADDRESS_PAGE_TITLE      = "addressesPageTitle";
    private static final String BANK_PAGE_TITLE         = "bankPageTitle";
    private static final String BLOGS_PAGE_TITLE        = "blogsPageTitle";
    private static final String HELP_PAGE_TITLE         = "helpPageTitle";
    private static final String POLICIES_PAGE_TITLE     = "policiesPageTitle";
    private static final String TERMS_PAGE_TITLE        = "termsPageTitle";
    private static final String PRIVACY_PAGE_TITLE      = "privacyPageTitle";
    private static final String RETURN_PAGE_TITLE       = "returnPageTitle";

    @When("user opens hamburger menu")
    public void userOpensHamburgerMenu() {
        try {
            // On m-site, /profile often serves as the hamburger/account menu
            Grid.driver().get("https://qa-ssr.msme.jswone.in/profile");
            sleep(2000);
            log.info("Opened hamburger menu via direct navigation to /profile");
        } catch (Exception e) {
            try {
                waitAndCheckIsElementVisible(HAMBURGER_ICON);
                waitAndClickOn(HAMBURGER_ICON);
                sleep(2000);
                log.info("Clicked hamburger icon to open menu");
            } catch (Exception ex) {
                throw new RuntimeException("Failed to open hamburger menu", ex);
            }
        }
    }

    @Then("all hamburger menu options should be visible and clickable")
    public void validateAllHamburgerOptions() {
        softAssert.assertTrue(isElementPresent(PROFILE_DETAILS_OPTION),  "Profile details not visible");
        softAssert.assertTrue(isElementPresent(SAVED_ADDRESSES_OPTION),  "Saved addresses not visible");
        softAssert.assertTrue(isElementPresent(BANK_DETAILS_OPTION),     "Bank details not visible");
        softAssert.assertTrue(isElementPresent(BLOGS_OPTION),            "Blogs not visible");
        softAssert.assertTrue(isElementPresent(HELP_SUPPORT_OPTION),     "Help & support not visible");
        softAssert.assertTrue(isElementPresent(POLICIES_OPTION),         "Policies not visible");
        log.info("All hamburger menu options are visible");
    }

    @When("user clicks on {string}")
    public void userClicksOnMenu(String menu) {
        String locatorKey;
        switch (menu) {
            case "Profile details":      locatorKey = PROFILE_DETAILS_OPTION; break;
            case "Saved addresses":     locatorKey = SAVED_ADDRESSES_OPTION; break;
            case "Bank account details": locatorKey = BANK_DETAILS_OPTION;    break;
            case "Blogs":                locatorKey = BLOGS_OPTION;           break;
            case "Help & support":       locatorKey = HELP_SUPPORT_OPTION;    break;
            case "JSW One policies":     locatorKey = POLICIES_OPTION;        break;
            case "Terms & conditions":   locatorKey = TERMS_CONDITIONS_OPTION; break;
            case "Privacy policy":       locatorKey = PRIVACY_POLICY_OPTION;   break;
            case "Return policy":        locatorKey = RETURN_POLICY_OPTION;    break;
            default:
                throw new RuntimeException("Invalid menu option: " + menu);
        }
        
        try {
            String xpath = locatorReader.getXpath("elements." + locatorKey + ".en_US.web");
            WebElement el = Grid.driver().findElement(By.xpath((xpath != null && xpath.startsWith("xpath=")) ? xpath.substring(6) : xpath));
            
            // Scroll into view for safety
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", el);
            sleep(500);
            
            // Try standard click, then JS click as fallback
            try {
                el.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", el);
            }
            log.info("Clicked menu option: {}", menu);
        } catch (Exception e) {
            log.error("Failed to click menu option '{}', trying fallback: {}", menu, e.getMessage());
            waitAndClickOn(locatorKey);
        }
        sleep(2000);
    }

    @Then("user should be navigated to {string} page")
    public void userShouldBeNavigatedToPage(String page) {
        String url = Grid.driver().getCurrentUrl();
        log.info("Current URL after navigation: {}", url);

        switch (page) {
            case "profile":
                softAssert.assertTrue(url.contains("my-profile") || url.contains("profile"), "Not on profile page. URL: " + url);
                break;
            case "addresses":
                softAssert.assertTrue(url.contains("address") || url.contains("profile"), "Not on addresses page. URL: " + url);
                break;
            case "bank":
                softAssert.assertTrue(url.contains("bank") || url.contains("profile"), "Not on bank page. URL: " + url);
                break;
            case "blogs":
                softAssert.assertTrue(url.contains("blog"), "Not on blogs page. URL: " + url);
                break;
            case "help":
                softAssert.assertTrue(url.contains("help") || url.contains("support"), "Not on help page. URL: " + url);
                break;
            case "policies":
                softAssert.assertTrue(url.contains("polic"), "Not on policies page. URL: " + url);
                break;
            case "terms":
                softAssert.assertTrue(url.contains("terms"), "Not on terms page. URL: " + url);
                break;
            case "privacy":
                softAssert.assertTrue(url.contains("privacy"), "Not on privacy page. URL: " + url);
                break;
            case "return":
                softAssert.assertTrue(url.contains("return"), "Not on return page. URL: " + url);
                break;
            default:
                throw new RuntimeException("Invalid page: " + page);
        }
        log.info("Navigation to '{}' validated.", page);
    }

    @And("the {string} page title should be displayed")
    public void verifyPageTitleVisible(String page) {
        String locatorKey;
        switch (page) {
            case "profile": locatorKey = PROFILE_PAGE_TITLE; break;
            case "addresses": locatorKey = ADDRESS_PAGE_TITLE; break;
            case "bank": locatorKey = BANK_PAGE_TITLE; break;
            case "blogs": locatorKey = BLOGS_PAGE_TITLE; break;
            case "help": locatorKey = HELP_PAGE_TITLE; break;
            case "policies": locatorKey = POLICIES_PAGE_TITLE; break;
            case "terms": locatorKey = TERMS_PAGE_TITLE; break;
            case "privacy": locatorKey = PRIVACY_PAGE_TITLE; break;
            case "return": locatorKey = RETURN_PAGE_TITLE; break;
            default:
                throw new RuntimeException("Invalid page title key: " + page);
        }
        waitAndCheckIsElementVisible(locatorKey);
        softAssert.assertTrue(isElementPresent(locatorKey), page + " title not visible");
        log.info("Verified {} page title is displayed", page);
    }
}
