package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class GuestUserSteps extends AbstractWebPage {

    private final String REGISTRATION_PAGE_MESSAGE = "registrationPageMessage";
    private final String ACTIVE_ORDER="activeOrders";
    private final String LOGIN_REGISTER_PAGE="loginRegistrationPage";
    private final String MY_REQUIREMENT="myRequirements";
    private final String MY_ORDERS="myOrders";
    private final String YOUTUBE_VIDEO="youtube";
    private final String YOUTUBE_VERIFICATION="verifyYoutubeVideo";
    private final String BLOGS_SECTION="blogsArticles";
    private final String BLOGS_VIEW_ALL="blogsArticleViewAll";
    private final String BLOGS_PAGE_VERIFICATION="blogsPageVerification";
    private final String LOGIN_PAGE="loginPage";


    String pageName;

    WebDriverWait wait = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30));

    @Given("user open the CCP portal as guest user")
    public void userOpenTheCCPPortalAsGuestUser() {
        Grid.driver().get(ConfigUtil.getSecretValue("ccpUrl"));
        Grid.driver().manage().window().maximize();
    }

    @When("user clicks on Register button")
    public void userClicksOnRegisterButton() {
        sleep(1000);
        WebElement REGISTER_BUTTON = Grid.driver().findElement(By.xpath("//button[@class='btn cta-btn primary']//span[contains(text(),'Register')]"));
        JavascriptExecutor js = Grid.driver();
        js.executeScript("arguments[0].scrollIntoView(true);", REGISTER_BUTTON);
        js.executeScript("arguments[0].click();", REGISTER_BUTTON);
    }

    @Then("user should be navigated to Registration page")
    public void userShouldBeNavigatedToRegistrationPage() {
        softAssert.assertEquals(waitAndGetText(REGISTRATION_PAGE_MESSAGE),"Create your JSW One MSME account","User is not navigated to Registration page from Guest Page Home Page");
    }

    @Then("Active order section should not be displayed")
    public void activeOrderSectionShouldNotBeDisplayed() {
        softAssert.assertFalse(isElementPresent(ACTIVE_ORDER),"Active order section is displayed for Guest User");
    }

    @When("user give direct {string}")
    public void userGiveDirectUrl(String pageName) {
        this.pageName=pageName;
        Grid.driver().get(ConfigUtil.getSecretValue(pageName));
        Grid.driver().manage().window().maximize();
    }

    @Then("user should be prompted to Login or Register")
    public void userShouldBePromptedToLoginOrRegister() {
       //Wait until the page is loaded completely before verifying the presence of Login/Register page elements to avoid flaky test execution
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        if(isElementPresent(LOGIN_PAGE)){
            softAssert.assertTrue(isElementPresent(LOGIN_REGISTER_PAGE),"User is not prompted to Login or Register when trying to access "+pageName+" page as Guest User");
        }
    }

    @Then("user should not able to see {string} section")
    public void userShouldNotAbleToSeeSection(String sectionName) {
        if(sectionName.equalsIgnoreCase("My Requirements")){
            softAssert.assertFalse(isElementPresent(MY_REQUIREMENT),sectionName+" section is displayed for Guest User");
        }
        else if(sectionName.equalsIgnoreCase("My Orders")){
            softAssert.assertFalse(isElementPresent(MY_ORDERS),sectionName+" section is displayed for Guest User");
        }
    }

    @Then("You tube video should be displayed in home page and able to play")
    public void youTubeVideoShouldBeDisplayedInHomePageAndAbleToPlay() {
        sleep(1000);
        JavascriptExecutor js = Grid.driver();
        WebElement youtubeFrame = Grid.driver().findElement(By.xpath("//img[@alt='JSW One video']"));
        js.executeScript("arguments[0].scrollIntoView(true);", youtubeFrame);
        js.executeScript("arguments[0].click();", youtubeFrame);
        sleep(1000);
        softAssert.assertTrue(isElementPresent(YOUTUBE_VERIFICATION),"You tube video is not played on clicking the video thumbnail");
    }

    @Then("Blogs section should be displayed in home page and user able to open any blog")
    public void blogsSectionShouldBeDisplayedInHomePageAndUserAbleToOpenAnyBlog() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        JavascriptExecutor js =(JavascriptExecutor) Grid.driver();
        WebElement blogSection = Grid.driver().findElement(By.xpath("//h2[contains(text(),'Browse articles & blogs')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", blogSection);
        softAssert.assertTrue(blogSection.isDisplayed(),"Blogs section is not displayed in home page for Guest User");
        softAssert.assertTrue(isElementPresent(BLOGS_SECTION),"Blogs section content is not displayed in home page for Guest User");
        sleep(1000);
        waitAndClickOn(BLOGS_VIEW_ALL);
        softAssert.assertTrue(isElementPresent(BLOGS_PAGE_VERIFICATION),"User is not navigated to Blogs page on clicking View All in Blogs section");
    }
}
