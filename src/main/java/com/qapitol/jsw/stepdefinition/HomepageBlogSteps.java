package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.pageFactory.CategoryPages;
import com.qapitol.jsw.utils.CustomUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class HomepageBlogSteps extends AbstractWebPage {
    private static final String BLOG_SECTION_TITLE = "blogSectionTitle";
    private static final String BLOG_TITLE = "blogTitle";
    private static final String FIRST_BLOG_READ_MORE_BUTTON = "firstBlogReadMoreButton";
    private static final String BLOG_POSTED_ON_TEXT = "blogPostedOnText";
    private static final String BLOG_YOUTUBE_ICON = "blogYoutubeIcon";


    @Then("user should see Blogs section title in homepage")
    public void userShouldSeeBlogsSectionTitleInHomepage() {
        sleep(2000);
        WebElement locater= Grid.driver().findElement(CategoryPages.BLOG_SECTION);
        CustomUtil.scrollingToViewAndClickUsingJS(locater);
        sleep(2000);
        waitAndCheckIsElementVisible(BLOG_SECTION_TITLE);

        softAssert.assertTrue(
                isElementPresent(BLOG_SECTION_TITLE),
                "Blogs section title is not displayed on homepage"
        );
    }

    @Then("user navigate to blog")
    public void userNavigateToBlog() {
        sleep(2000);
        WebElement locator = Grid.driver().findElement(CategoryPages.BLOG_SECTION);
        CustomUtil.scrollingToViewAndClickUsingJS(locator);
        sleep(2000);
        waitAndCheckIsElementClickable(FIRST_BLOG_READ_MORE_BUTTON);
        waitAndClickOn(BLOG_TITLE);
    }


    @And("user should see the vlogs page title")
    public void userShouldSeeTheVlogsPageTitle() {
        waitAndCheckIsElementVisible(BLOG_POSTED_ON_TEXT);
        softAssert.assertTrue(
                isElementPresent(BLOG_POSTED_ON_TEXT),
                "'Posted on' text is not displayed on blog page"
        );
    }
    @And("user should see youtube video icon")
    public void userShouldSeeYoutubeVideoIcon() {

        WebElement locator = Grid.driver().findElement(CategoryPages.BLOG_Youtube);
        CustomUtil.scrollingToViewAndClickUsingJS(locator);
        sleep(2000);

        waitAndCheckIsElementVisible(BLOG_YOUTUBE_ICON);

        softAssert.assertTrue(
                isElementPresent(BLOG_YOUTUBE_ICON),
                "Youtube video icon is not displayed on blog page"
        );
    }

    @And("user click on youtube video icon")
    public void userClickOnYoutubeVideoIcon() {
        WebElement locator = Grid.driver().findElement(CategoryPages.BLOG_Youtube);
        CustomUtil.scrollingToViewAndClickUsingJS(locator);
        sleep(2000);
        waitAndCheckIsElementClickable(BLOG_YOUTUBE_ICON);
        waitAndClickOn(BLOG_YOUTUBE_ICON);
    }
    @Then("user should be redirected to youtube page")
    public void userShouldBeRedirectedToYoutubePage() {

        // Switch to new tab
        for (String windowHandle : Grid.driver().getWindowHandles()) {
            Grid.driver().switchTo().window(windowHandle);
        }

        String currentUrl = Grid.driver().getCurrentUrl();

        softAssert.assertTrue(
                currentUrl.contains("youtube"),
                "User is not redirected to YouTube page"
        );
    }
}