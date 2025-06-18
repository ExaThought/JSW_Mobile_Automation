package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.AllureCustomLogReporter;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.Set;

import static com.qapitol.jsw.utils.CustomUtills.softAssert;

public class CcpRegistrationSteps extends AbstractWebPage {

    public static final String CCP_REGISTRATION="ccpRegistration";
    public static final String CCP_USER_NAME="userName";
    public static final String CCP_PHONE_NUMBER="phoneNumber";
    public static final String CCP_GSTN="gstn";
    public static final String CCP_EMAIL="email";
    public static final String CCP_REGISTRATION_CHECKBOX="ccpRegistrationCheckbox";
    public static final String CCP_REGISTRATION_TERMS="terms";
    public static final String CCP_REGISTRATION_TITLE="registrationTitle";
    public static final String CCP_REGISTRATION_SUB_TITLE="registrationSubTitle";
    public static final String CCP_REGISTRATION_LOGIN="registrationLogin";
    public static final String CCP_REGISTRATION_LOGIN_PAGE="loginPage";
    public static final String CCP_REGISTRATION_CTA_BUTTON="registrationCtaButton";
    public static final String CCP_REGISTRATION_JSW_LOGO="registrationJswLogo";
    public static final String CCP_REGISTRATION_DESCRIPTION="registrationDescription";
    public static final String CCP_REGISTRATION_CLOSE_BUTTON="registrationCloserButton";
    public static final String CCP_REGISTRATION_NEXT_BUTTON="registrationNextButton";
    public static final String CCP_REGISTRATION_SET_PASSWORD="registrationSetPassword";
    public static final String CCP_REGISTRATION_CONFIRM_PASSWORD="registrationConfirmPassword";
    public static final String CCP_CREATE_ACC_CTA_BUTTON="registrationCreateAccountCTAButton";

    @When("user open the website and open registration")
    public void user_open_the_website_and_open_registration_page(){

        Grid.driver().get(Config.getConfigProperty("ccpPortalUrl"));
        Grid.driver().manage().window().maximize();
        waitAndCheckIsElementVisible(CCP_REGISTRATION);
        waitAndCheckIsElementPresent(CCP_REGISTRATION);
        waitAndClickOn(CCP_REGISTRATION);
        waitAndCheckIsElementVisible(CCP_REGISTRATION_CTA_BUTTON);
        waitAndClickOn(CCP_REGISTRATION_CLOSE_BUTTON);
    }
    @And("user enter {string} {string} {string} {string}")
    public void user_enter_userName_phoneNumber_email_gstn(String userName,String phoneNumber,String email,String gstn){


        waitAndCheckIsElementPresent(CCP_USER_NAME);
        waitAndSetText(CCP_USER_NAME,userName);
        waitAndCheckIsElementPresent(CCP_PHONE_NUMBER);
        waitAndSetText(CCP_PHONE_NUMBER, phoneNumber);
        waitAndCheckIsElementPresent(CCP_GSTN);
        waitAndSetText(CCP_GSTN,email);
        waitAndCheckIsElementPresent(CCP_EMAIL);
        waitAndSetText(CCP_EMAIL,gstn);
        waitAndCheckIsElementPresent(CCP_REGISTRATION_CHECKBOX);
        waitAndClickOn(CCP_REGISTRATION_CHECKBOX);

        //Check terms&condition, Return policy and privacy policy is re-directing to correct page when user click it.
        for (WebElement terms:locateElements(CCP_REGISTRATION_TERMS)){
            Actions actions = new Actions(Grid.driver());
            actions.keyDown(Keys.CONTROL)
                    .click(terms)
                    .keyUp(Keys.CONTROL)
                    .perform();
        }
        Set<String>window=Grid.driver().getWindowHandles();
        Iterator<String> iterator=window.iterator();
        String parentWindow=iterator.next();
        while (iterator.hasNext()) {
                String title = Grid.driver().switchTo().window(iterator.next()).getTitle();
                System.out.println(title);

        }
        Grid.driver().switchTo().window(parentWindow);

        //Test Registration Login button
        waitAndCheckIsElementPresent(CCP_REGISTRATION_LOGIN);
        waitAndClickOn(CCP_REGISTRATION_LOGIN);
        softAssert.assertEquals(waitAndGetText(CCP_REGISTRATION_LOGIN_PAGE),"Login","Failing in registration for re-directing to login");
        waitAndCheckIsElementPresent(CCP_REGISTRATION_CTA_BUTTON);
        waitAndClickOn(CCP_REGISTRATION_CTA_BUTTON);

        //Test Jsw Logo
        softAssert.assertNotNull(waitAndGetText(CCP_REGISTRATION_JSW_LOGO),"JSW Logo is missing in Registration page");

        //Check Title and description
        softAssert.assertEquals(waitAndGetText(CCP_REGISTRATION_TITLE),"Register Now","Registration title is failing");
        softAssert.assertEquals(waitAndGetText(CCP_REGISTRATION_SUB_TITLE),"Create your JSW One MSME account","Failing is sub title registration");
        softAssert.assertEquals(waitAndGetText(CCP_REGISTRATION_DESCRIPTION),"Your one-stop digital marketplace for all your industrial needs","Registration description is not matching");

        //Open the password page
        waitAndCheckIsElementPresent(CCP_REGISTRATION_NEXT_BUTTON);
        waitAndClickOn(CCP_REGISTRATION_NEXT_BUTTON);
    }

    @And("user set the {string} {string}")
    public void user_set_the_password(String password, String gstn){
        //Set The password
        waitAndCheckIsElementPresent(CCP_REGISTRATION_SET_PASSWORD);
        waitAndSetText(CCP_REGISTRATION_SET_PASSWORD,password);

        waitAndCheckIsElementPresent(CCP_REGISTRATION_CONFIRM_PASSWORD);
        waitAndSetText(CCP_REGISTRATION_CONFIRM_PASSWORD,password);

        //Create account
        waitAndCheckIsElementPresent(CCP_CREATE_ACC_CTA_BUTTON);
        waitAndClickOn(CCP_CREATE_ACC_CTA_BUTTON);

        waitAndCheckIsElementVisible(Grid.driver().getTitle());
        waitAndCheckIsElementPresent(Grid.driver().getTitle());
        softAssert.assertNotNull(Grid.driver().getTitle());

        AllureCustomLogReporter.addTestStepLog("Account created successfully and GSTN is ="+gstn);


    }
}
