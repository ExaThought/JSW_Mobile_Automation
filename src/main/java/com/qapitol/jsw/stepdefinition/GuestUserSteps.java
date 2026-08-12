package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
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
import java.util.List;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class GuestUserSteps extends AbstractWebPage {

    private final String ACTIVE_ORDER = "activeOrders";
    private final String LOGIN_REGISTER_PAGE = "loginRegistrationPage";
    private final String MY_REQUIREMENT = "myRequirements";
    private final String MY_ORDERS = "myOrders";
    private final String LOGIN_PAGE = "loginPage";

    String pageName;

    WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
    HomepageBlogSteps homepageBlogSteps = new HomepageBlogSteps();

    @Given("user open the CCP portal as guest user")
    public void userOpenTheCCPPortalAsGuestUser() {
        Grid.driver().get(ConfigUtil.getSecretValue("ccpUrl"));
        Grid.driver().manage().window().maximize();
    }

    @When("User clicks on Login button")
    public void userClicksOnLoginButton() {
        sleep(1000);
        WebElement LOGIN_BUTTON = Grid.driver().findElement(By.xpath("//button[text()='Login']"));
        JavascriptExecutor js = Grid.driver();
        js.executeScript("arguments[0].scrollIntoView(true);", LOGIN_BUTTON);
        js.executeScript("arguments[0].click();", LOGIN_BUTTON);
    }

    @Then("User should be redirected to Login or Register")
    public void userShouldBeRedirectedToLoginOrRegister() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        if (isElementPresent(LOGIN_PAGE)) {
            softAssert.assertTrue(isElementPresent(LOGIN_REGISTER_PAGE), "User is not prompted to Login or Register when trying to access " + pageName + " page as Guest User");
        }
    }

    @Then("Active order section should not be displayed")
    public void activeOrderSectionShouldNotBeDisplayed() {
        softAssert.assertFalse(isElementPresent(ACTIVE_ORDER), "Active order section is displayed for Guest User");
    }

    @When("user give direct {string}")
    public void userGiveDirectUrl(String pageName) {
        this.pageName = pageName;
        Grid.driver().get(ConfigUtil.getSecretValue(pageName));
        Grid.driver().manage().window().maximize();
    }

    @Then("user should be prompted to Login or Register")
    public void userShouldBePromptedToLoginOrRegister() {
        //Wait until the page is loaded completely before verifying the presence of Login/Register page elements to avoid flaky test execution
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        if (isElementPresent(LOGIN_PAGE)) {
            softAssert.assertTrue(isElementPresent(LOGIN_REGISTER_PAGE), "User is not prompted to Login or Register when trying to access " + pageName + " page as Guest User");
        }
    }

    @Then("user should not able to see {string} section")
    public void userShouldNotAbleToSeeSection(String sectionName) {
        if (sectionName.equalsIgnoreCase("My Requirements")) {
            softAssert.assertFalse(isElementPresent(MY_REQUIREMENT), sectionName + " section is displayed for Guest User");
        } else if (sectionName.equalsIgnoreCase("My Orders")) {
            softAssert.assertFalse(isElementPresent(MY_ORDERS), sectionName + " section is displayed for Guest User");
        }
    }

    @Then("MSME Masterclass video section should be displayed in home page and able to play")
    public void MsmeMasterclassVideoSectionShouldBeDisplayedInHomePageAndAbleToPlay() {
        sleep(1000);
        JavascriptExecutor js = Grid.driver();
        WebElement msmeMasterclass_Video_Section = Grid.driver().findElement(By.xpath("//h2[text()='MSME Masterclass']/../../div/div"));
        js.executeScript("arguments[0].scrollIntoView(true);", msmeMasterclass_Video_Section);
        WebElement first_Item = Grid.driver().findElement(By.xpath("(//h2[text()='MSME Masterclass']/../../div/div/div/button/picture/img)[1]"));
        js.executeScript("arguments[0].click();", first_Item);
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(50));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='video']")));
        Grid.driver().switchTo().frame(Grid.driver().findElement(By.xpath("//iframe[@title='video']")));
        WebElement videoSource = Grid.driver().findElement(By.xpath("//video/source[@type='video/mp4']"));
        softAssert.assertTrue(videoSource.isEnabled(),
                "MSME Masterclass video is not played on clicking the video thumbnail");
    }

    @And("clicks on the Blogs label of the top navbar")
    public void userClicksOnTheBlogsLabelOfTheTopNavbar() {
        //homepageBlogSteps.userNavigateToBlog();
        WebElement Blogs = Grid.driver().findElement(By.xpath("//a[normalize-space()='Blogs' or contains(@href,'/blogs')]"));
        Blogs.click();
    }

    @Then("Blogs Page should be displayed with multiple blogs")
    public void BlogsPageShouldBeDisplayedWithMultipleBlogs() {
        WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));
        List<WebElement> Blogs_Container = wait.until(ExpectedConditions.visibilityOfAllElements(Grid.driver().findElements(By.xpath("//div[@class='common-container']/div/button']"))));
        Assert.assertFalse(Blogs_Container.isEmpty(), "Blogs are not present");
    }
}
