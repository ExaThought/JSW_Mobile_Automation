package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.appTesting.AppResponse;
import com.qapitol.jsw.apitest.pojos.order.rcv2CartSizeResponse.Rcv2CartSize;
import com.qapitol.jsw.apitest.pojos.order.rcv2AddToCartRequestPayload.Rcv2AddToCartRequest;
import com.qapitol.jsw.apitest.pojos.order.rcv2DeleteLineItemRequestPayload.CartIdentifier;
import com.qapitol.jsw.apitest.pojos.order.rcv2DeleteLineItemRequestPayload.CustomerDetails;
import com.qapitol.jsw.apitest.pojos.order.rcv2DeleteLineItemRequestPayload.DeleteLineItemRequest;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.LineItem;
import com.qapitol.jsw.apitest.pojos.order.rcv2Response.Rcv2Response;
import com.qapitol.jsw.apitest.pojos.order.updateLineItemRequestPayload.UpdateLineItemPayload;
import com.qapitol.jsw.utils.ApiUtils;
import com.qapitol.jsw.utils.Token;
import com.qapitol.sauron.api.client.ApiClientFactory;
import com.qapitol.sauron.data.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;

import javax.ws.rs.NotAuthorizedException;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

public class CartRCV2ApiImp extends BaseApiImpl {

    private static final String REQUIREMENTS_CART_TYPE = "enquiry_v2";
    private static final String REQUIREMENTS_CART_JOURNEY_TYPE = "REQUIREMENTS_CART_V2";

    public Rcv2Response getAddToCartResponse(String payload) throws IOException {

        // Read the request payload and convert as request pojo
        Rcv2AddToCartRequest requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get(payload),
                Rcv2AddToCartRequest.class);

        // Read data from Yaml.file
        String quantity = (String) Data.get().get("quantity_value");
        String customerId = (String) Data.get().get("customer_ID");

        // set the payload
        requestBody.getCustomerDetails().setCustomerId(customerId);
        if (nonNull(quantity)) {
            requestBody.getLineItems().get(0).getAttributeData().getCustomAttributes().get(0).setValue(quantity);
        }

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        return requestApi.rcv2AddToCartRequest(requestBody);
    }

    public Rcv2Response getAddToCartResponseWithInvalidData(String payload) throws IOException {
        // Read the request payload and convert as request pojo
        Rcv2AddToCartRequest requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get(payload),
                Rcv2AddToCartRequest.class);

        // Read data from Yaml.file
        String customerId = (String) Data.get().get("customer_ID");

        // set the payload
        requestBody.getCustomerDetails().setCustomerId(customerId);
        // Set Invalid MMID
        if (requestBody.getLineItems() != null && !requestBody.getLineItems().isEmpty()) {
            requestBody.getLineItems().get(0).setProductMMId("INVALID_MMID");
            requestBody.getLineItems().get(0).setVariantMMId("INVALID_VARIANT_MMID");
        }

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        return requestApi.rcv2AddToCartRequest(requestBody);
    }

    public Rcv2Response getDeleteLineItemResponse(String payload, List<String> lineItem) throws IOException {

        // Read the request payload and convert as request pojo
        DeleteLineItemRequest requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get(payload),
                DeleteLineItemRequest.class);

        // set the payload
        String customerId = (String) Data.get().get("customer_ID");
        requestBody.getCustomerDetails().setCustomerId(customerId);

        requestBody.setLineItemIdList(lineItem);

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        return requestApi.rcv2DeleteLineItem(requestBody);
    }

    public Rcv2Response getUpdateLineItemResponse(String payload, Rcv2Response addToCartResponse) throws IOException {

        // Read the request payload and convert as request pojo
        UpdateLineItemPayload requestBody = getPayloadJsonFileAsPOJO((String) Data.get().get(payload),
                UpdateLineItemPayload.class);

        // set the payload
        String customerId = (String) Data.get().get("customer_ID");
        requestBody.getCustomerDetails().setCustomerId(customerId);

        String lineItemId = addToCartResponse.getData().getLineItemList().get(0).getLineItemIdentifier()
                .getCtLineItemId();
        requestBody.setLineItemId(lineItemId);
        requestBody.getLineItem().getAttributeData().getCustomAttributes().get(0).setValue("60");

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);

        return requestApi.rcv2UpdateLineItem(requestBody);
    }

    public Rcv2Response getCartResponse() {
        return withFreshTokenOn401(() -> {
            logAsCurl("GET", "v2/cart/get", null);
            return ccpUrl().create(RequestApi.class).rcv2GetCart();
        });
    }

    /**
     * Logs the cart call as a cURL command, and attaches it to the Allure step, so a 401 can be
     * replayed by hand. Credential headers are replaced by a fingerprint plus the token's user and
     * expiry - enough to tell whose token was sent and whether it was stale, without disclosing it.
     */
    private void logAsCurl(String method, String path, Object body) {
        ApiClientFactory factory = ccpUrl();
        String address = factory.getAddress();
        StringBuilder curl = new StringBuilder("curl -X ").append(method).append(" '")
                .append(address.endsWith("/") ? address : address + "/").append(path).append("'");
        Map<String, List<String>> headers = factory.getJaxrsClientFactoryBean().getHeaders();
        String lineBreak = " \\\n  ";
        if (headers != null) {
            headers.forEach((name, values) -> values.forEach(value -> curl.append(lineBreak)
                    .append("-H '").append(name).append(": ")
                    .append(isCredentialHeader(name) ? describeCredential(value) : value).append("'")));
        }
        curl.append(lineBreak).append("-H 'Accept: application/json'");
        if (body != null) {
            try {
                curl.append(lineBreak).append("-H 'Content-Type: application/json'")
                        .append(lineBreak).append("--data '")
                        .append(new ObjectMapper().writeValueAsString(body)).append("'");
            } catch (Exception e) {
                curl.append(lineBreak).append("--data '<could not serialise: ")
                        .append(e.getMessage()).append(">'");
            }
        }
        log.info("Cart API request:\n" + curl);
        Allure.addAttachment("Cart API request - " + method + " " + path, "text/plain", curl.toString());
    }

    private static boolean isCredentialHeader(String name) {
        String lower = name.toLowerCase();
        return lower.contains("token") || lower.contains("key") || lower.contains("authorization");
    }

    private static String describeCredential(String value) {
        StringBuilder described = new StringBuilder("<redacted ").append(Token.fingerprintOf(value));
        try {
            described.append(", customerId=").append(ApiUtils.getUserId(value));
            long exp = Token.readExpiry(value);
            described.append(", exp=").append(exp == 0 ? "unreadable" : Instant.ofEpochSecond(exp));
        } catch (Exception e) {
            described.append(", not a readable JWT");
        }
        return described.append(">").toString();
    }

    /**
     * Runs a user-token cart call, and on a 401 drops the cached CCP token and retries once with
     * a fresh login. A second 401 is real and propagates.
     */
    private static <T> T withFreshTokenOn401(Supplier<T> call) {
        try {
            return call.get();
        } catch (NotAuthorizedException e) {
            Token.invalidateCCPToken();
            return call.get();
        }
    }

    public void getCartSize() {
        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        requestApi.rcv2CartSize();

    }

    public Rcv2Response emptyCart() throws IOException {
        Rcv2Response cart = getCartResponse();
        List<String> itemIds = collectLineItemIds(cart);
        if (!itemIds.isEmpty()) {
            return getDeleteLineItemResponse("RCV2_DELETE_LINE_ITEM", itemIds);
        }
        return cart;
    }

    /** What one cart cleanup did: line items deleted, and how many the delete response says are left. */
    public static final class CartCleanup {
        public final int deleted;
        public final int remaining;

        CartCleanup(int deleted, int remaining) {
            this.deleted = deleted;
            this.remaining = remaining;
        }
    }

    /**
     * The cleanup already done in this scenario, if any. Lets "empty the cart via API" reuse the
     * cleanup the login step just did instead of calling cart/get a second time.
     */
    private static final ThreadLocal<CartCleanup> SCENARIO_CLEANUP = new ThreadLocal<>();

    public static CartCleanup scenarioCleanup() {
        return SCENARIO_CLEANUP.get();
    }

    /** Called at the start of every scenario, so a cleanup is never reused across scenarios. */
    public static void resetScenarioCleanup() {
        SCENARIO_CLEANUP.remove();
    }

    /**
     * Empties the logged-in user's cart with exactly one cart/get call.
     *
     * <p>The line items come from that one cart/get. They are deleted in a single delete-items
     * call, and what is left is read from the delete response, which carries the cart after the
     * deletion (CartApiSteps reads it the same way) - so no second cart/get is needed to confirm
     * the cart is empty.
     */
    public CartCleanup emptyCartOnce() {
        List<String> lineItemIds = collectLineItemIds(getCartResponse());
        CartCleanup cleanup;
        if (lineItemIds.isEmpty()) {
            cleanup = new CartCleanup(0, 0);
        } else {
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setCustomerId(ApiUtils.getUserId(Token.getCCPToken()));

            CartIdentifier cartIdentifier = new CartIdentifier();
            cartIdentifier.setCartType(REQUIREMENTS_CART_TYPE);
            cartIdentifier.setCartJourneyType(REQUIREMENTS_CART_JOURNEY_TYPE);

            DeleteLineItemRequest requestBody = new DeleteLineItemRequest();
            requestBody.setCustomerDetails(customerDetails);
            requestBody.setCartIdentifier(cartIdentifier);
            requestBody.setLineItemIdList(lineItemIds);

            Rcv2Response afterDelete = ccpUrl().create(RequestApi.class).rcv2DeleteLineItem(requestBody);
            cleanup = new CartCleanup(lineItemIds.size(), collectLineItemIds(afterDelete).size());
            log.info("Requested deletion of " + lineItemIds.size() + " cart line item(s): " + lineItemIds);
        }
        SCENARIO_CLEANUP.set(cleanup);
        return cleanup;
    }

    public int emptyCartForLoggedInUser() {
        List<String> lineItemIds = collectLineItemIds(getCartResponse());
        if (lineItemIds.isEmpty()) {
            return 0;
        }

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerId(ApiUtils.getUserId(Token.getCCPToken()));

        CartIdentifier cartIdentifier = new CartIdentifier();
        cartIdentifier.setCartType(REQUIREMENTS_CART_TYPE);
        cartIdentifier.setCartJourneyType(REQUIREMENTS_CART_JOURNEY_TYPE);

        DeleteLineItemRequest requestBody = new DeleteLineItemRequest();
        requestBody.setCustomerDetails(customerDetails);
        requestBody.setCartIdentifier(cartIdentifier);
        requestBody.setLineItemIdList(lineItemIds);

        withFreshTokenOn401(() -> {
            logAsCurl("POST", "v2/cart/delete-items", requestBody);
            return ccpUrl().create(RequestApi.class).rcv2DeleteLineItem(requestBody);
        });
        log.info("Requested deletion of " + lineItemIds.size() + " cart line item(s): " + lineItemIds);
        return lineItemIds.size();
    }

    public int getCartLineItemCount() {
        return collectLineItemIds(getCartResponse()).size();
    }

    private List<String> collectLineItemIds(Rcv2Response cart) {
        List<String> ids = new ArrayList<>();
        if (cart == null || cart.getData() == null || cart.getData().getLineItemList() == null) {
            return ids;
        }
        for (LineItem item : cart.getData().getLineItemList()) {
            if (item.getLineItemIdentifier() != null
                    && item.getLineItemIdentifier().getCtLineItemId() != null) {
                ids.add(item.getLineItemIdentifier().getCtLineItemId());
            }
        }
        return ids;
    }

    public Rcv2CartSize getCartSizeResponse() {

        // call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        return requestApi.rcv2CartSize();
    }

    public AppResponse getAppApiResponse(){
        RequestApi requestApi=appUrl().create(RequestApi.class);
        return requestApi.getAppResponse();

    }

}
