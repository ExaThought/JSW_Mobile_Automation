package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.LocatorReader;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class mlogoutSteps extends AbstractWebPage {

    private static final String PROFILE_ICON = "profileIcon";
    private static final String LOGIN_PAGE_IDENTIFIER = "continuetoLogout";

    LocatorReader locatorReader =
            new LocatorReader("locators/mlogoutsteps.yaml");

    @When("user clicks on profile icon")
    public void user_clicks_on_profile_icon() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        waitAndCheckIsElementClickable(10, PROFILE_ICON);
        waitAndCheckIsElementVisible(PROFILE_ICON);
        waitAndClickOn(PROFILE_ICON);


    }

    private void scrollToElementAndClick(String yamlKey) {
        WebElement element = null;
        int attempts = 0;

        while (attempts < 10) {
            try {
                // Get XPath from YAML
                String xpath = locatorReader.getXpath("elements." + yamlKey + ".en_US.web");
                element = Grid.driver().findElement(By.xpath(xpath));

                if (element.isDisplayed()) {
                    // Scroll precisely to element's top, accounting for fixed headers
                    ((JavascriptExecutor) Grid.driver()).executeScript(
                            "window.scrollTo({top: arguments[0] - 100, behavior: 'smooth'});",
                            element.getLocation().getY()
                    );

                    // Small wait for scroll animation
                    element.click();
                    return;
                }
            } catch (Exception ignored) {}
            attempts++;
        }
    }


    @When("user clicks on logout button and redirects to home page")
    public void user_clicks_on_logout_button() {

        String logoutXpath =
                locatorReader.getXpath("elements.logoutButton.en_US.web");

        WebDriverWait wait =
                new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        WebElement logoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(logoutXpath))
        );


        scrollToElementAndClick(logoutXpath);
        logoutBtn.click();
        waitAndCheckIsElementVisible(LOGIN_PAGE_IDENTIFIER);
        waitAndClickOn(LOGIN_PAGE_IDENTIFIER);
    }

    @Then("user should be logged out successfully")
    public void user_should_be_logged_out_successfully() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(Grid.driver(), Duration.ofSeconds(15));

        waitAndCheckIsElementClickable(10, PROFILE_ICON);

        boolean isLoginButtonVisible =
                waitAndCheckIsElementVisible("PROFILE_ICON");


        softAssert.assertTrue(
                isLoginButtonVisible,
                "PROFILE_ICON is NOT visible after logout"
        );
    }

    @Then("user session should be invalidated")
    public void user_session_should_be_invalidated() {

        // Try accessing profile page directly
        Grid.driver().get("https://qa-ssr.msme.jswone.in/profile");

        WebDriverWait wait =
                new WebDriverWait(Grid.driver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("/login"));

        softAssert.assertTrue(
                Grid.driver().getCurrentUrl().contains("/login"),
                "User session still active after logout"
        );
    }}
