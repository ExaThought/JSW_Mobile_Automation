package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CustomerProfileApiImpl;
import com.qapitol.jsw.apitest.pojos.order.customerProfile.CustomerProfileResponse;
import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.Token;
import com.qapitol.jsw.utils.UserConfig;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;

import static com.qapitol.jsw.utils.Hooks.softAssert;
import static com.qapitol.sauron.web.WebActionListener.log;

public class CustomerProfileApiSteps {

    private final CustomerProfileApiImpl customerProfileApi = new CustomerProfileApiImpl();

    private Response rawResponse;
    private CustomerProfileResponse customerProfile;

    @When("we request the customer profile Api")
    public void weRequestTheCustomerProfileApi() {
        rawResponse = ApiUtils.fetchCustomerProfile();
        log.info("Customer profile endpoint: " + ApiUtils.customerProfileEndpoint());
        Allure.addAttachment("customer-profile response", rawResponse.asString());
    }

    @When("we request the customer profile Api with an expired token")
    public void weRequestTheCustomerProfileApiWithAnExpiredToken() {
        rawResponse = ApiUtils.fetchCustomerProfile(Token.getExpiredCcpToken());
        Allure.addAttachment("customer-profile response (expired token)", rawResponse.asString());
    }

    @When("we request the customer profile Api through the middleware client")
    public void weRequestTheCustomerProfileApiThroughTheMiddlewareClient() {
        customerProfile = customerProfileApi.getCustomerProfile();
    }

    @Then("customer profile Api should respond with status code {int}")
    public void customerProfileApiShouldRespondWithStatusCode(int expectedStatusCode) {
        Assert.assertNotNull(rawResponse, "Customer profile API was not called before asserting the status code");
        Assert.assertEquals(rawResponse.getStatusCode(), expectedStatusCode,
                "Unexpected status code from " + ApiUtils.customerProfileEndpoint()
                        + " | body: " + rawResponse.asString());
    }

    @Then("customer profile response should contain a valid customer profile")
    public void customerProfileResponseShouldContainAValidCustomerProfile() {
        if (customerProfile == null) {
            customerProfile = ApiUtils.getCustomerProfile();
        }
        softAssert.assertNotNull(customerProfile.getData(), "Customer profile response has no data node");
        softAssert.assertFalse(customerProfile.getData().isEmpty(), "Customer profile data node is empty");
        log.info("Customer profile: " + customerProfile);
    }

    @Then("the customer profile belongs to the signed-in account")
    public void theCustomerProfileBelongsToTheSignedInAccount() {
        if (customerProfile == null) {
            customerProfile = ApiUtils.getCustomerProfile();
        }
        String expectedEmail = UserConfig.getEmail();
        String actualEmail = customerProfile.getDataValueAsString("email");

        softAssert.assertNotNull(actualEmail,
                "Customer profile response carries no data.email, so it cannot be checked against"
                        + " the signed-in account. Endpoint: " + ApiUtils.customerProfileEndpoint());
        softAssert.assertEquals(actualEmail, expectedEmail,
                "The customer profile API returned a profile for '" + actualEmail + "' but the"
                        + " request was authenticated as '" + expectedEmail + "'. Endpoint: "
                        + ApiUtils.customerProfileEndpoint());
        log.info("Customer profile identity OK - profile belongs to " + actualEmail);
    }

    @Then("customer profile response should have {string} as {string}")
    public void customerProfileResponseShouldHaveValue(String dottedPath, String expectedValue) {
        if (customerProfile == null) {
            customerProfile = ApiUtils.getCustomerProfile();
        }
        String actualValue = customerProfile.getDataValueAsString(dottedPath);
        softAssert.assertEquals(actualValue, expectedValue,
                "Mismatch at data." + dottedPath + " in the customer profile response");
    }
}
