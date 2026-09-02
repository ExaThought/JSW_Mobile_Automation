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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static com.qapitol.jsw.utils.CustomUtil.clickFirstVisible;
import static com.qapitol.jsw.utils.CustomUtil.isAnyVisible;
import static com.qapitol.jsw.utils.CustomUtil.xpathOf;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class GuestUserSteps extends AbstractWebPage {

    private final String ACTIVE_ORDER = "activeOrders";
    private final String LOGIN_REGISTER_PAGE = "loginRegistrationPage";
    private final String MY_REQUIREMENT = "myRequirements";
    private final String MY_ORDERS = "myOrders";
    private final String LOGIN_PAGE = "loginPage";
    private static final String MASTERCLASS_SECTION = "masterclassSection";
    private static final String MASTERCLASS_PLAY_BUTTON = "masterclassPlayButton";
    private static final String MASTERCLASS_PLAYER_FRAME = "masterclassPlayerFrame";
    private static final String MASTERCLASS_VIDEO_SOURCE = "playedVideo";
    private static final String BLOGS_PAGE_HEADING = "blogsPageVerification";
    private static final String BLOGS_PAGE_ARTICLE_LINKS = "blogsPageArticleLinks";

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
        WebElement LOGIN_BUTTON = Grid.driver().findElement(By.xpath("//button[@data-test-id='desktop-header-user-type-button']"));
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
        softAssert.assertTrue(waitAndCheckIsElementVisible(MASTERCLASS_SECTION),
                "MSME Masterclass section is not displayed on the home page");

        clickFirstVisible(xpathOf(getLocator(MASTERCLASS_PLAY_BUTTON)));

        By playerFrame = xpathOf(getLocator(MASTERCLASS_PLAYER_FRAME));
        new WebDriverWait(Grid.driver(), Duration.ofSeconds(50))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(playerFrame));
        try {
            softAssert.assertTrue(waitAndCheckIsElementPresent(MASTERCLASS_VIDEO_SOURCE),
                    "MSME Masterclass video is not played on clicking the video thumbnail -"
                            + " no video source rendered inside the player frame");
            log.info("MSME Masterclass video is played");
        } finally {
            Grid.driver().switchTo().defaultContent();
        }
    }

    @And("clicks on the Blogs label of the top navbar")
    public void userClicksOnTheBlogsLabelOfTheTopNavbar() {
        //homepageBlogSteps.userNavigateToBlog();
        WebElement Blogs = Grid.driver().findElement(By.xpath("//a[normalize-space()='Blogs' or contains(@href,'/blogs')]"));
        Blogs.click();
    }

    /**
     * Two separate defects made this step incapable of running.
     *
     * <p>The XPath carried a stray {@code "']"}, so the driver rejected it as an invalid selector -
     * meaning this step had never once executed. And {@code visibilityOfAllElements} was handed a
     * findElements() list resolved BEFORE the wait started; that overload never re-queries the DOM,
     * so an empty first look could never recover no matter how long it waited.
     *
     * <p>Both are gone: the page is identified by a curated locator key and the blog count uses the
     * locator-based overload, which re-queries on every poll.
     */
    @Then("Blogs Page should be displayed with multiple blogs")
    public void BlogsPageShouldBeDisplayedWithMultipleBlogs() {
        // isAnyVisible, not waitAndCheckIsElementVisible: the heading renders twice on /blogs and
        // the second copy measures 0x0, so a first-match visibility check is one DOM-order change
        // away from reporting a visible heading as hidden.
        softAssert.assertTrue(isAnyVisible(xpathOf(getLocator(BLOGS_PAGE_HEADING))),
                "Blogs page did not load - the 'Blogs' heading is not shown");

        By articleLinks = xpathOf(getLocator(BLOGS_PAGE_ARTICLE_LINKS));
        List<WebElement> blogs;
        try {
            blogs = new WebDriverWait(Grid.driver(), Duration.ofSeconds(30))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(articleLinks));
        } catch (TimeoutException e) {
            blogs = Collections.emptyList();
        }
        softAssert.assertTrue(blogs.size() > 1,
                "Blogs page should list more than one blog but found " + blogs.size()
                        + " matching " + articleLinks);
    }
}
