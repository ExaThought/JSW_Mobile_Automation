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

    /** The cart the CCP requirements journey uses - the pair the portal itself sends. */
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

    /**
     * Deletes every line item from the logged-in user's requirements cart in a single call.
     *
     * <p>Self-contained, unlike {@link #emptyCart()}, which reads the payload file name and the
     * customer id out of {@code Data.get()}. That map only exists for scenarios declaring a
     * {@code @dataFile}, so UI features cannot use it. Here the request is built in code and the
     * customer id comes from the {@code userId} claim of the CCP login token - so it always acts
     * on the cart of whoever the run actually logged in as, rather than a configured id that can
     * drift out of step with the account and empty a different customer's cart.
     *
     * <p>The {@code access_token} header is not set here: {@code LoginApiImpl} injects it for the
     * {@code ccpUrl} address from {@code Token.getCCPToken()}, which performs the CCP login.
     *
     * @return the number of line items deleted; 0 when the cart was already empty.
     */
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
        // The whole list in one request, matching what the portal sends. Deleting one id per call
        // leaves the cart half-emptied when a later call fails.
        requestBody.setLineItemIdList(lineItemIds);

        ccpUrl().create(RequestApi.class).rcv2DeleteLineItem(requestBody);
        log.info("Requested deletion of " + lineItemIds.size() + " cart line item(s): " + lineItemIds);
        return lineItemIds.size();
    }

    /** Number of line items currently in the cart. */
    public int getCartLineItemCount() {
        return collectLineItemIds(getCartResponse()).size();
    }

    /**
     * Line item ids in the cart. Empty when the cart has none, and also when the response carries
     * no data at all - an unreadable cart is treated as nothing to delete rather than as a crash,
     * because the caller re-reads the cart afterwards to confirm the outcome.
     */
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
