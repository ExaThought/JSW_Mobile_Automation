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
import com.qapitol.sauron.data.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        // Call the API
        RequestApi requestApi = ccpUrl().create(RequestApi.class);
        return requestApi.rcv2GetCart();
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
        return emptyCartOnce().deleted;
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
