package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.apitest.apis.impl.CustomerProfileApiImpl;
import com.qapitol.jsw.apitest.pojos.order.customerProfile.CustomerProfileResponse;
import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.jsw.utils.ConfigUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;

import static com.qapitol.jsw.utils.Hooks.softAssert;
import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Steps for GET {middlewareUrl}xp-mi/public/v3/customer-profile.
 *
 * The environment is picked up from the `middlewareUrl` property, so the same steps run
 * against preprod or qa without any change to the feature file.
 */
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
        rawResponse = ApiUtils.fetchCustomerProfile(ConfigUtil.resolveOrThrow("ccpExpiredToken"));
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
