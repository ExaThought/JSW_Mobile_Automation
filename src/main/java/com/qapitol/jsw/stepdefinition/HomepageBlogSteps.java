package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.pageFactory.CategoryPages;
import com.qapitol.jsw.utils.CustomUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.qapitol.jsw.stepdefinition.SessionSteps.MANAGE_POPUP_CLOSE;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class HomepageBlogSteps extends AbstractWebPage {
    private static final String BLOG_SECTION_TITLE = "blogSectionTitle";
    private static final String BLOG_TITLE = "//h1[text()='Blogs']";
    private static final String FIRST_BLOG_READ_MORE_BUTTON = "firstBlogReadMoreButton";
    private static final String BLOG_POSTED_ON_TEXT = "(//h1[text()='Blogs']/following-sibling::div//h3)[1]";
    private static final String BLOG_YOUTUBE_ICON = "blogYoutubeIcon";



    @Then("user should see Blogs section title in homepage")
    public void userShouldSeeBlogsSectionTitleInHomepage() {
        try {
            log.info("Navigating to Blogs section from homepage.");

            waits(CategoryPages.BLOG_SECTION);

            WebElement blogSection = Grid.driver().findElement(CategoryPages.BLOG_SECTION);
            clickButton(blogSection);

            log.info("Clicked on Blogs section.");

            if (isPopDisplayed(By.xpath(MANAGE_POPUP_CLOSE))) {
                log.info("Manage popup is displayed. Closing it.");
                clickButton(By.xpath(MANAGE_POPUP_CLOSE));
            }

            waits(By.xpath(BLOG_TITLE));

            softAssert.assertTrue(
                    isDisplayed(By.xpath(BLOG_TITLE)),
                    "Blogs section title is not displayed on homepage."
            );

            log.info("Blogs section title is displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Blogs section title: " + e.getMessage());
            softAssert.fail("Exception while verifying Blogs section title: " + e.getMessage());
        }
    }

    @Then("user navigate to blog")
    public void userNavigateToBlog() {
        sleep(2000);
        WebElement locator = Grid.driver().findElement(CategoryPages.BLOG_SECTION);
        CustomUtil.scrollingToViewAndClickUsingJS(locator);
        sleep(2000);
        clickButton(CategoryPages.BLOG_SECTION);
//        waitAndCheckIsElementClickable(FIRST_BLOG_READ_MORE_BUTTON);
//        waitAndClickOn(BLOG_TITLE);
    }


    @And("user should see the vlogs page title")
    public void userShouldSeeTheVlogsPageTitle() {
        try {
            log.info("Verifying Vlogs page title is displayed.");

            waits(By.xpath(BLOG_POSTED_ON_TEXT));

            boolean isDisplayed = isDisplayed(By.xpath(BLOG_POSTED_ON_TEXT));

            softAssert.assertTrue(
                    isDisplayed,
                    "Vlogs page title is not displayed on blog page."
            );

            log.info("Vlogs page title is displayed successfully.");

        } catch (Exception e) {
            log.info("Exception occurred while verifying Vlogs page title: " + e.getMessage());
            softAssert.fail("Exception while verifying Vlogs page title: " + e.getMessage());
        }
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