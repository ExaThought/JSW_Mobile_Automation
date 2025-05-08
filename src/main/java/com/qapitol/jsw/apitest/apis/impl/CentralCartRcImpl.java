package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse.AddToCartRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartDeleteApiRequest.CentralCartDeleteApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest.CentralCartFetchApiReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse.CentralCartFetchApiRes;
import com.qapitol.jsw.apitest.pojos.order.centralCartRcAddToCart.CentralCartRcAddToCartReq;
import com.qapitol.jsw.apitest.pojos.order.centralCartSizeRequest.CentralCartSizeReq;
import com.qapitol.sauron.data.Data;
import org.testng.Assert;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class CentralCartRcImpl extends BaseApiImpl{
    private CentralCartRcAddToCartReq centralCartRcAddToCartReq;
    private AddToCartRes addToCartApiResponse;
    private CentralCartDeleteApiReq deleteApiReq;
    private String cartId;
    private String requestedSfCustomerId;
    private String requestedCartType;
    private String requestedCartJourneyType;
    private String requestedStoreFront;
    private String requestedDeviceType;
    private int listItemsSize;



    public AddToCartRes getCentralCartRcAddToCartResponse() throws IOException {

        // Load request body from JSON file
        centralCartRcAddToCartReq = getPayloadJsonFileAsPOJO((String) Data.get().get("CENTRAL_CART_RC_ADD_TO_CART"), CentralCartRcAddToCartReq.class);

        // Read values from YAML/test data
        String deviceType = (String) Data.get().get("device_type");
        String storeFront = (String) Data.get().get("store_front");
        String cartType = (String) Data.get().get("cart_type");
        String cartJourneyType = (String) Data.get().get("cart_journey_type");
        String deliveryType = (String) Data.get().get("delivery_type");

        // Set request payload values from test data
        if (nonNull(deviceType)) {
            centralCartRcAddToCartReq.getCartIdentifier().setDeviceType(deviceType);
        }
        if (nonNull(storeFront)) {
            centralCartRcAddToCartReq.getCartIdentifier().setStoreFront(storeFront);
        }
        if (nonNull(cartType)) {
            centralCartRcAddToCartReq.getCartIdentifier().setCartType(cartType);
        }
        if (nonNull(cartJourneyType)) {
            centralCartRcAddToCartReq.getCartIdentifier().setCartJourneyType(cartJourneyType);
        }
        if (nonNull(deliveryType)) {
            centralCartRcAddToCartReq.getShippingDetails().setDeliveryType(deliveryType);
        }

        // Call the API
        RequestApi requestApi = centralCartCcpUrl().create(RequestApi.class);
        addToCartApiResponse = requestApi.centralCartRcAddToCart(centralCartRcAddToCartReq);
        log.info("RC V2 API successfully Triggered ");

        // Log Cart ID
        cartId = addToCartApiResponse.getData().getCartId();//Reading CartID from response
        log.info("CartID =" + cartId);

        // sending cartId
        return addToCartApiResponse;
    }

    public void nonNullValidationForResponse() {
        int shippingDetailsSize = addToCartApiResponse.getData().getShippingDetails().getCartAddress().size();
        listItemsSize = addToCartApiResponse.getData().getLineItems().size();

        //Shipping details NonNull validation
        for (int i = 0; i < shippingDetailsSize; i++) {
            Assert.assertNotNull(addToCartApiResponse.getData().getShippingDetails().getCartAddress().get(i).getAddressType(), "AddressType Can not be Null");
            Assert.assertNotNull(addToCartApiResponse.getData().getShippingDetails().getCartAddress().get(i).getStreetLine1(), "StreetLine1 Can not be Null");
            Assert.assertNotNull(addToCartApiResponse.getData().getShippingDetails().getCartAddress().get(i).getPinCode(), "PinCode Can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getShippingDetails().getCartAddress().get(i).getCity(), "Cit can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getShippingDetails().getCartAddress().get(i).getRegion(), "Region can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getShippingDetails().getCartAddress().get(i).getState(), "State can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getShippingDetails().getCartAddress().get(i).getCompany(), "CompanyName can not be null");
        }

        //Line items nonNull validation
        for (int i = 0; i < listItemsSize; i++) {
            Assert.assertNotNull(addToCartApiResponse.getData().getLineItems().get(i).getLineItemId(), "Line item ID cannot be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getLineItems().get(i).getProductMmid(), "Product MMID can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getLineItems().get(i).getAttributes(), "Attributes can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getLineItems().get(i).getPurchasedQuantity(), "PurchasedQuantity can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getLineItems().get(i).getPrimaryQuantity(), "PrimaryQuantity can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getLineItems().get(i).getLineItemMetaData().getVariantHash(), "Variant can not be null");
            Assert.assertNotNull(addToCartApiResponse.getData().getLineItems().get(i).getCatalougeInfo(), "Catalogue can not be null");
        }
    }

    public void validationForRCV2Api() {
        // Verify response status(Response status should be "Created")
        Assert.assertEquals(addToCartApiResponse.getStatus(), "CREATED");

        // validation for Cart Identifier(Cart should create with requested Cart Identifier)
        requestedDeviceType = centralCartRcAddToCartReq.getCartIdentifier().getDeviceType();
        Assert.assertEquals(addToCartApiResponse.getData().getCartIdentifier().getDeviceType(), requestedDeviceType);
        requestedStoreFront = centralCartRcAddToCartReq.getCartIdentifier().getStoreFront();
        Assert.assertEquals(addToCartApiResponse.getData().getCartIdentifier().getStoreFront(), requestedStoreFront);
        requestedCartType = centralCartRcAddToCartReq.getCartIdentifier().getCartType();
        Assert.assertEquals(addToCartApiResponse.getData().getCartIdentifier().getCartType(), requestedCartType);
        requestedCartJourneyType = centralCartRcAddToCartReq.getCartIdentifier().getCartJourneyType();
        Assert.assertEquals(addToCartApiResponse.getData().getCartIdentifier().getCartJourneyType(), requestedCartJourneyType);

        // Validation For delivery type(Request deliveryType and Response deliveryType should be same)
        String requestedDeliveryType = centralCartRcAddToCartReq.getShippingDetails().getDeliveryType();
        String responseDeliveryType = addToCartApiResponse.getData().getShippingDetails().getDeliveryType();
        Assert.assertEquals(requestedDeliveryType, responseDeliveryType);
        log.info("Delivery Type validation is working fine =" + responseDeliveryType);

        // Validation For MetaData
        //Response metaData status should be Active
        String responseMetaData = addToCartApiResponse.getData().getMetadata().getStatus();
        Assert.assertEquals(responseMetaData, "ACTIVE");
        //check metaData CreatedBy and ModifiedBy CustomerID
        String responseCreatedBy = addToCartApiResponse.getData().getMetadata().getCreatedBy();
        String responseModifiedBy = addToCartApiResponse.getData().getMetadata().getModifiedBy();
        requestedSfCustomerId = centralCartRcAddToCartReq.getCustomerDetails().getSfCustomerId();
        if (responseCreatedBy.equals(responseModifiedBy)) {
            Assert.assertEquals(requestedSfCustomerId, responseCreatedBy,"Created and requested customerID does not match");
        }

        //Validation For Customer Details(Requested and response SF Customer should be same)
        String responseSfCustomerId = addToCartApiResponse.getData().getCustomerDetails().getSfCustomerId();
        Assert.assertEquals(requestedSfCustomerId, responseSfCustomerId);

        //Validation for Line items
        for (int i = 0; i < listItemsSize; i++) {
            //Request quantity and Response quantity should be same
            int requestedPurchasedQuantity = centralCartRcAddToCartReq.getLineItems().get(i).getPurchasedQuantity().getQuantity();//Reading requested purchase quantity
            Double responsePurchasedQuantity = addToCartApiResponse.getData().getLineItems().get(i).getPurchasedQuantity().getQuantity(); //Reading response purchase quantity
            Assert.assertEquals(requestedPurchasedQuantity, responsePurchasedQuantity);
            log.info("Purchase Quantity validation is working fine =" + responsePurchasedQuantity);
            //Requested productMMID,Response productMMI and Response catalogueId should be same
            String requestedProductMMID = centralCartRcAddToCartReq.getLineItems().get(i).getProductMmid();
            String responseProductMMID = addToCartApiResponse.getData().getLineItems().get(i).getProductMmid();
            String responseCatalogueID = addToCartApiResponse.getData().getLineItems().get(i).getCatalougeInfo().getCatalougeId();
            Assert.assertTrue(requestedProductMMID.equals(responseProductMMID) && requestedProductMMID.equals(responseCatalogueID),
                    "All Product MMIDs and Catalogue ID should be equal");
        }
    }

    public void deleteCartID() throws IOException {

        // Load request body from JSON file
        CentralCartDeleteApiReq deleteRequestBody = getPayloadJsonFileAsPOJO((String) Data.get().get("DELETE_API_REQ"), CentralCartDeleteApiReq.class);

        //Read Cart Identifier from the RCV2RequestBody
        requestedDeviceType = centralCartRcAddToCartReq.getCartIdentifier().getDeviceType();
        requestedStoreFront = centralCartRcAddToCartReq.getCartIdentifier().getStoreFront();
        requestedCartType = centralCartRcAddToCartReq.getCartIdentifier().getCartType();
        requestedCartJourneyType = centralCartRcAddToCartReq.getCartIdentifier().getCartJourneyType();

        //Set CartId
        deleteRequestBody.setCartId(cartId);

        //Set SfCustomerID
        requestedSfCustomerId = centralCartRcAddToCartReq.getCustomerDetails().getSfCustomerId();

        //Set the cart Identifier
        deleteRequestBody.setSfCustomerId(requestedSfCustomerId);
        deleteRequestBody.getCartIdentifier().setCartJourneyType(requestedCartJourneyType);
        deleteRequestBody.getCartIdentifier().setCartType(requestedCartType);
        deleteRequestBody.getCartIdentifier().setDeviceType(requestedDeviceType);
        deleteRequestBody.getCartIdentifier().setStoreFront(requestedStoreFront);

        //Call Delete Api Method
        CentralCartDeleteApiImpl centralCartDeleteApi=new CentralCartDeleteApiImpl();
        centralCartDeleteApi.deleteCartId(deleteRequestBody);

    }

    public void fetchRcCart() throws IOException {

        // Load request body from JSON file
        CentralCartFetchApiReq fetchRequestBody=getPayloadJsonFileAsPOJO((String)Data.get().get("FETCH_API_REQ"),CentralCartFetchApiReq.class);

        //set the fetch request Body
        fetchRequestBody.setSfCustomerId(requestedSfCustomerId);
        fetchRequestBody.setCartType(requestedCartType);
        fetchRequestBody.setStoreFront(requestedStoreFront);

        //Call Fetch Api
        CentralCartFetchApiImpl fetchApi=new CentralCartFetchApiImpl();
        CentralCartFetchApiRes fetchCartResponse=fetchApi.fetchCart(fetchRequestBody,addToCartApiResponse);
    }

    public void getCartSize() throws IOException {

        //Call Cart Size RequestBody
        CentralCartSizeReq cartSizeRequestBody=getPayloadJsonFileAsPOJO((String)Data.get().get("CENTRAL_CART_RC_ADD_TO_CART"), CentralCartSizeReq.class);

        //Set the Request Body
        cartSizeRequestBody.setSfCustomerId(requestedSfCustomerId);
        cartSizeRequestBody.setStoreFront(requestedStoreFront);
        int cartSize=addToCartApiResponse.getData().getLineItems().size();

        //Call Cart Size Method
        CentralCartSizeImpl centralCartSize=new CentralCartSizeImpl();
        centralCartSize.getCartSizeResponse(cartSizeRequestBody,cartSize);

    }
}
