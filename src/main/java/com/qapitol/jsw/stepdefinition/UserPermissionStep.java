package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.context.ScenarioContext;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.qapitol.jsw.utils.ApiUtils.*;
import static com.qapitol.jsw.utils.CustomUtil.*;
import static com.qapitol.jsw.utils.Hooks.softAssert;

public class UserPermissionStep extends AbstractWebPage
{
    private ScenarioContext context;
    public  final String ADD_USER_BTN = "//h2/../following-sibling::button//span[text()='Add user']";
    public final String ADD_USER_POPUP_TITLE = "//p[text()='Add user']";
    public final String NAME_TEXTBOX = "//input[@id=\"add-user-name\"]";
    public final String MOBILE_NUMBER = "//input[@id=\"add-user-mobile\"]";
    public final String EMAIL="//input[@id=\"add-user-email\"]";
    public final String DEPARTMENT_DROP_DOWN = "//label[text()='Department']/following-sibling::button";
    public final String DEPARTMENT = "//span[text()='%s']/preceding-sibling::button";
    public final String FINANCE_ACCOUNTING_TOGGLE = "//p[text()='Finance & account management']/../following-sibling::button";
    public final String ORDER_MANAGEMENT = "//p[text()='Order & materials management']/../following-sibling::button";
    public final String ADD_USER_POPUP_BTN = "(//button[text()='Add user'])[2]";
    public final String VIEW_EDIT_BUTTON = "//p[text()='+91 %s']/../../../following-sibling::button";
    public final String DELETE_USER = "//button[text()='Delete user']";
    public final String YES_DELETE_BTN = "//button[text()='Yes, delete']";
    public final String STATUS = "//h3[contains(text(),'%s .')]/preceding-sibling::div/span";
    public final String EXISTING_MOBILE_NUM_ERROR_MSG = "//div[text()='A user with this mobile number already exists. Use a different mobile or contact our customer support']";
    public final String SAVE_BTN = "(//button[text()='Save changes'])[2]";
    public final String NAME = "//h3[text()='%s']";
    public final String DEPARTMENTS = "(//span[text()='Select']/../following-sibling::div//span)[2]";
    public final String PERMISSIONS = "(//h3[text()='%s']/../../following-sibling::div//span)";




    public UserPermissionStep(ScenarioContext context)
    {
        this.context = context;
    }

    @And("User clicks on Add user under  button")
    public void UserClicksOnAddUserButton()
    {
        log.info("user clicks on add user button");
        waits(By.xpath(ADD_USER_BTN));
        clickButton(By.xpath(ADD_USER_BTN));
    }
    @And("User clicks the Add User button")
    public void userClicksAddUserButton() {
        log.info("User verifies user permission page");
        waits(By.xpath(ADD_USER_BTN));
        clickButton(By.xpath(ADD_USER_BTN));
    }

    @Then("User Adds secondary user under Finance & Accounting Manager")
    public void UserCreatesSecondaryUser()
    {
        log.info("user creates a secondary user");

        String name = generateFirstName();
        context.secondaryUserFullName=name;
        log.info("Enters the name "+name);
        enterText(By.xpath(NAME_TEXTBOX),name);
        String number = generateNotRegisteredMobileNumber();
        context.secondaryUserMobileNo = number;
        log.info("Enters the number "+number);
        enterText(By.xpath(MOBILE_NUMBER),number);
        String email = generateEmail();
        context.secondaryUserEmail = email;
        log.info("Enters the email "+email);
        softAssert.assertTrue(isDisplayed(By.xpath(EMAIL)),"Email textbox is missing");
        enterText(By.xpath(EMAIL),email);
        waits(By.xpath(DEPARTMENT_DROP_DOWN));
        clickButton(By.xpath(DEPARTMENT_DROP_DOWN));
        waits(By.xpath(String.format(DEPARTMENT,"Finance & Accounting Manager")));
        clickButton(By.xpath(String.format(DEPARTMENT,"Finance & Accounting Manager")));
        log.info("select the Finance & Accounting Manager");
        clickButton(By.xpath(DEPARTMENT_DROP_DOWN));
        clickButton(By.xpath(FINANCE_ACCOUNTING_TOGGLE));
        clickButton(By.xpath(ADD_USER_POPUP_BTN));
        sleep(2000);
        log.info("User clicks add user button");
        sleep(700);
        softAssert.assertTrue(isDisplayed(By.xpath(String.format(STATUS,context.secondaryUserFullName))),"Status not displayed");
        Map<String, String> sfIds = fetchUserByMobile(context.secondaryUserMobileNo);
        String contactSfid = sfIds.get("contactSfid");
        String customerSfId = sfIds.get("customerSfId");
        boolean result=verifyContactSfid(customerSfId, contactSfid);
        log.info("verifies the secondary user creation in SF");
        softAssert.assertTrue(result,"secondary user creation has not happened in SF");

    }

    @Then("User Adds secondary user under Order & materials management department")
    public void UserAddsSecondaryUserUnderOrderAndMaterialDept()
    {
        log.info("user creates a secondary user");

        String name = generateFirstName();
        context.secondaryUserFullName=name;
        log.info("Enters the name "+name);
        enterText(By.xpath(NAME_TEXTBOX),name);
        String number = generateNotRegisteredMobileNumber();
        context.secondaryUserMobileNo = number;
        log.info("Enters the number "+number);
        enterText(By.xpath(MOBILE_NUMBER),number);
        String email = generateEmail();
        context.secondaryUserEmail = email;
        log.info("Enters the email "+email);
        softAssert.assertTrue(isDisplayed(By.xpath(EMAIL)),"Email textbox is missing");
        enterText(By.xpath(EMAIL),email);
        waits(By.xpath(DEPARTMENT_DROP_DOWN));
        clickButton(By.xpath(DEPARTMENT_DROP_DOWN));
        waits(By.xpath(String.format(DEPARTMENT,"Operations Manager")));
        clickButton(By.xpath(String.format(DEPARTMENT,"Operations Manager")));
        log.info("select the Operations Manager");
        clickButton(By.xpath(DEPARTMENT_DROP_DOWN));
        clickButton(By.xpath(ORDER_MANAGEMENT));
        clickButton(By.xpath(ADD_USER_POPUP_BTN));
        log.info("User clicks add user button");
        sleep(2000);
        Map<String, String> sfIds = fetchUserByMobile(context.secondaryUserMobileNo);
        String contactSfid = sfIds.get("contactSfid");
        String customerSfId = sfIds.get("customerSfId");
        boolean result=verifyContactSfid(customerSfId, contactSfid);
        log.info("verifies the secondary user creation in SF");
        sleep(700);
        softAssert.assertTrue(isDisplayed(By.xpath(String.format(STATUS,context.secondaryUserFullName))),"Status not displayed");

    }

    @Then("User add Operations Manager")
    public void UserAddsSecondaryUserUnderOrderAndMaterialDeptToOrderMaterialsManagementDepartment() {
        waits(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        clickButton(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        context.secondaryUserFullName=context.secondaryUserFullName+" demo";
        clickButton(By.xpath(NAME_TEXTBOX));
        enterText(By.xpath(NAME_TEXTBOX),"demo");
        waits(By.xpath(DEPARTMENT_DROP_DOWN));
        clickButton(By.xpath(DEPARTMENT_DROP_DOWN));
        waits(By.xpath(String.format(DEPARTMENT,"Operations Manager")));
        clickButton(By.xpath(String.format(DEPARTMENT,"Operations Manager")));
        log.info("updates to Operations Manager");
        clickButton(By.xpath(DEPARTMENT_DROP_DOWN));
        clickButton(By.xpath(SAVE_BTN));

    }

    @Then("User add OrderAndMaterialDept permission")
    public void UserAddsOrderAndMaterialDeptPermission() {
        waits(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        clickButton(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        context.secondaryUserFullName=context.secondaryUserFullName+" demo";
        clickButton(By.xpath(NAME_TEXTBOX));
        enterText(By.xpath(NAME_TEXTBOX),"demo");
        clickButton(By.xpath(ORDER_MANAGEMENT));
        log.info("user click on order management toggle "+context.secondaryUserFullName);
        clickButton(By.xpath(SAVE_BTN));

    }

    @Then("User verifies new permission")
    public void UserVerifiesNewPermission() {
        pageRefresh();
        waits(By.xpath(String.format(PERMISSIONS,context.secondaryUserFullName)));
        log.info("verifying permission for"+context.secondaryUserFullName);
        List<WebElement> elements = Grid.driver().findElements(
                By.xpath(String.format(PERMISSIONS,context.secondaryUserFullName))
        );

        List<String> texts = elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        log.info("the permission list are "+texts);
        if(texts.contains("Order & materials management"))
        {
            softAssert.assertTrue(true);
        }else {
            softAssert.assertTrue(false,"permission not displayed");
        }


    }

    @Then("User verifies Operations Manager departments")
    public void UserVerifiesOrderAndMaterialDeptToOrderMaterialsManagementDepartment() {
        waits(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        clickButton(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        waits(By.xpath(DEPARTMENTS));
        if(getText(By.xpath(DEPARTMENTS)).contains("Operations Manager"))
        {
            softAssert.assertTrue(true);
        }else {
            softAssert.assertTrue(false);
        }
        waits(By.xpath(DELETE_USER));
        clickButton(By.xpath(DELETE_USER));
        waits(By.xpath(YES_DELETE_BTN));
        clickButton(By.xpath(YES_DELETE_BTN));
        sleep(1000);
        softAssert.assertFalse(isDisplayed(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo))),"Secondary user not deleted "+context.secondaryUserMobileNo);


    }

    @Then("User edits secondary user name {string}")
    public void UserAddsSecondaryUserName(String name) {
        waits(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        clickButton(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        log.info("user enter the new name "+name);
        context.secondaryUserFullName=context.secondaryUserFullName+" "+name;
        log.info("Enters the name "+name);
        clickButton(By.xpath(NAME_TEXTBOX));
        enterText(By.xpath(NAME_TEXTBOX),name);
        clickButton(By.xpath(SAVE_BTN));
    }

    @Then("User verifies the edit name")
    public void UserVerifiesEditName()
    {
        log.info("user verifies the edited name");
        waits(By.xpath(String.format(NAME,context.secondaryUserFullName)));
        softAssert.assertTrue(isDisplayed(By.xpath(String.format(NAME,context.secondaryUserFullName))),"name not updated");
    }



    @Then("User Adds secondary user under Order & materials management department with existing mobile number {string}")
    public void UserAddsSecondaryUserUnderOrderAndMaterialDeptWithExistingMobileNUmber(String number)
    {
        log.info("user creates a secondary user");

        String name = generateFirstName();
        context.secondaryUserFullName=name;
        log.info("Enters the name "+name);
        enterText(By.xpath(NAME_TEXTBOX),name);
        context.secondaryUserMobileNo = number;
        log.info("Enters the number "+number);
        enterText(By.xpath(MOBILE_NUMBER),number);
        String email = generateEmail();
        context.secondaryUserEmail = email;
        log.info("Enters the email "+email);
        softAssert.assertTrue(isDisplayed(By.xpath(EMAIL)),"Email textbox is missing");
        enterText(By.xpath(EMAIL),email);
        waits(By.xpath(DEPARTMENT_DROP_DOWN));
        clickButton(By.xpath(DEPARTMENT_DROP_DOWN));
        waits(By.xpath(String.format(DEPARTMENT,"Operations Manager")));
        clickButton(By.xpath(String.format(DEPARTMENT,"Operations Manager")));
        log.info("select the Operations Manager");
        clickButton(By.xpath(DEPARTMENT_DROP_DOWN));
        clickButton(By.xpath(ORDER_MANAGEMENT));
        clickButton(By.xpath(ADD_USER_POPUP_BTN));
        log.info("User clicks add user button");
        softAssert.assertTrue(isDisplayed(By.xpath(EXISTING_MOBILE_NUM_ERROR_MSG)),"error message not displayed");
        log.info("user verifies the error message");

    }

    @Then("User deletes the secondary user")
    public void UserDeletesTheSecondaryUser()
    {
        log.info("User deletes the secondary user");
        waits(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        clickButton(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo)));
        waits(By.xpath(DELETE_USER));
        clickButton(By.xpath(DELETE_USER));
        waits(By.xpath(YES_DELETE_BTN));
        clickButton(By.xpath(YES_DELETE_BTN));
        pageRefresh();
        sleep(500);
        softAssert.assertFalse(isDisplayed(By.xpath(String.format(VIEW_EDIT_BUTTON,context.secondaryUserMobileNo))),"Secondary user not deleted "+context.secondaryUserMobileNo);
        // Distinct from the row check above: that one says the user is gone from the SCREEN, this
        // one says the back end no longer grants them anything. A delete that only removed the row
        // would pass the first and fail here, which is the failure worth telling apart.
        softAssert.assertTrue(hasNoDepartmentsOrPermissions(context.secondaryUserMobileNo),
                "The deleted user " + context.secondaryUserMobileNo + " still holds departments or"
                        + " permissions in fetch-user, so the delete removed the row but left their"
                        + " access in place");
        log.info("User completes the SF validation for mobile number "+context.secondaryUserMobileNo);

    }
}
