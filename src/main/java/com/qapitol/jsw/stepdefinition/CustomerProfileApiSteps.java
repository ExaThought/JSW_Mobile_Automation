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
        // Verified-expired, otherwise this scenario asserts a rejection the API has no reason to
        // give: a valid token returns 200 and the test fails for the wrong reason.
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

    /**
     * Asserts the profile returned belongs to the account the call authenticated as.
     *
     * <p>Without this, a 200 with a populated data node passes even if the middleware hands back
     * somebody else's profile - which is the failure that matters most on production, and the one
     * a status-code check cannot see.
     *
     * <p>Compared against {@code UserConfig.getEmail()} rather than a literal, so the same
     * scenario works on qa, preprod and prod: each resolves {@code <test.group>.user.email} from
     * its own properties file, and that is the same credential {@code Token.getCCPToken()} used to
     * make the request.
     *
     * <p>{@code email}, not {@code customerId}: the response's data node has no customerId key.
     * Confirmed against qa on 3 Sep 2026 - the keys are id, sfAccountId, sfUserId,
     * customerProxyId, gstIn, pan, firstName, lastName, phoneNumber, email.
     */
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
