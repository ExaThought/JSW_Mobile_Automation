package com.qapitol.jsw.apitest.apis.impl;

import com.github.dockerjava.api.exception.BadRequestException;
import com.qapitol.jsw.apitest.apis.RequestApi;

import com.qapitol.jsw.apitest.pojos.order.ctSlcSloRequestPayload.CtSlcSloRequestPayload;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloRequestPayload.LineItem;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloResponse.CtSlcSloRes;
import com.qapitol.jsw.utils.DataContractValidator;
import com.qapitol.sauron.data.Data;



import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import static java.util.Objects.nonNull;

public class CtSlcSlocreationImpl extends BaseApiImpl {
    private String addressId;
    private String customerId;
    private String opportunityId;
    public static double requestQuantity=0;
    public static double requestedDeliveryCharges=0;
    public static double requestedCashBack=0;
    public static double requestProductPrice=0;
    public static double requestAdvanceAmount;
    public static double requestTotalAmount=0;
    public static double requestProductTaxes=0;
    public static double lineItemAmount=0;
    public static String formatLineItemTotalAmount;
    public static String requestWidth;
    public static String requestThickness;
    public static String requestLength;
    public static String requestPaymentType;
    public static String requestedSourceSeller;
    public static String requestOrderType;
    public static String requestNodelorNonNodel;
    public static String requestDeliveryType;
    public static double requestLineAmount=0;
    /** Customer the cart was created for - used to explain an empty cart in the UI steps. */
    public static String requestCustomerId;


    public CtSlcSloRes getSlcSloResponse(String orderType) throws IOException {
        try {
            // These are static, so without an explicit reset the accumulators below carry over
            // into the next scenario (requestedDeliveryCharges in particular uses +=).
            resetRequestData();

            //Load the Payload
            String payloadFileName = resolvePayloadFileName(orderType);
            CtSlcSloRequestPayload requestPayload = getPayloadJsonFileAsPOJO(payloadFileName, CtSlcSloRequestPayload.class);

            //Read the data from Yaml
            opportunityId = "006" + java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15);
            customerId = Data.get().get("CustomerId").toString();
            addressId = Data.get().get("AddressId").toString();
            requestCustomerId = customerId;

            // Fail here, not three steps later on an "empty cart" locator timeout, when the cart
            // is about to be created for a different customer than the browser will log in as.
            DataContractValidator.assertApiCustomerMatchesLoggedInUser(customerId);

            //Set RequestPayload
            requestPayload.setBillingAddressId(addressId);
            requestPayload.setShippingAddressId(addressId);
            requestPayload.setSalesforceOpportunityId(opportunityId);
            requestPayload.setCustomerId(customerId);

            if(nonNull(Data.get().get("orderType")))
                requestPayload.setOrderType((String)Data.get().get("orderType"));
            if(nonNull(Data.get().get("isDirect")))
                requestPayload.setIsDirect((boolean)Data.get().get("isDirect"));
            if(nonNull(Data.get().get("advancePayment")))
                requestPayload.setAdvancePayment((Double)Data.get().get("advancePayment"));
            if(nonNull(Data.get().get("advancePercentage")))
                requestPayload.setAdvancePercentage((Double)Data.get().get("advancePercentage"));
            if (nonNull(Data.get().get("slo"))) {
                Boolean slo= (Boolean) Data.get().get("slo");
                requestPayload.setSlo(slo);
            }


            //Request API - salesOppUrl, not ccpUrl: QA serves this endpoint from its own host
            //with an X-API-KEY. On pre-prod the property resolves back to ccpUrl.
            RequestApi requestApi = salesOppUrl().create(RequestApi.class);
            CtSlcSloRes responseBody = requestApi.requestCtCashAndCarrySlcSlo(requestPayload);

            //Set the Request data to verify in the CCP portal
            for (LineItem item : requestPayload.getLineItems()) {
                if(item.getSellerVendorMasterId() != null){
                    requestQuantity = item.getOrderedQuantity();
                    requestQuantity = requestQuantity / 1000;

                    lineItemAmount=Double.parseDouble(item.getSourceSeller().getSourceSellerSKUPrice());
                    requestProductPrice=requestQuantity*lineItemAmount;
                    requestProductTaxes=requestProductPrice*0.18;

                    requestWidth=item.getAttributes().getWidthMm();
                    requestLength=item.getAttributes().getLengthMm();
                    requestThickness=item.getAttributes().getThicknessMm();
                    requestedSourceSeller=item.getSourceSeller().getSourceSellerCompanyName();
                    requestLineAmount=Double.parseDouble(item.getSourceSeller().getSourceSellerSKUPrice());

                    // cashbackDetails is optional - payloads for SKUs with no cashback offer omit
                    // the whole block, so this must not assume it is present.
                    requestedCashBack=0;
                    if(item.getCashbackDetails() != null && item.getCashbackDetails().getCashbackRate() != null){
                        Double cashBack = item.getCashbackDetails().getCashbackRate();
                        requestedCashBack += cashBack;
                    }
                }
                else if (item.getOrderedPrice() != null) {
                    double deliveryCharge = (item.getOrderedPrice() + item.getGstPrice()) / 100.0;
                    requestedDeliveryCharges += deliveryCharge;
                }
            }

            // Read the product amount and modify as per UI.  EX: product amount 5000 it will modify as ₹5,000.00
           double lineItemTotalAmount=requestProductPrice;
            Locale indianLocale = new Locale("en", "IN");
            // Format currency
            NumberFormat formatter = NumberFormat.getCurrencyInstance(indianLocale);
            formatLineItemTotalAmount = formatter.format(lineItemTotalAmount);

            //Requested Total Amount
            requestTotalAmount=requestProductPrice+requestProductTaxes+requestedDeliveryCharges;

            requestPaymentType=requestPayload.getOrderPaymentType(); //JSW Steel
            requestNodelorNonNodel=requestPayload.getPayTo();
            requestOrderType=requestPayload.getOrderType();
            requestDeliveryType=requestPayload.getDeliveryType();
            if (requestPayload.getAdvancePayment() != null) {
                requestAdvanceAmount =requestPayload.getAdvancePayment();
            }


            return responseBody;
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Maps an orderType key to its payload file name via data/&lt;env&gt;/default-data.yaml.
     *
     * An unmapped key used to reach Jackson as a null file name and surface as
     * "FileNotFoundException: ...\api\request\null.json", which names neither the key that was
     * missing nor the file that should have defined it. Renaming a key in default-data.yaml
     * without updating every step definition is easy to do and was costing a full run to
     * diagnose, so it fails here with both.
     *
     * @param orderType the key a step definition set, e.g. "CT_SLC_SLO_CREDIT".
     * @return the payload file name, without the .json extension.
     * @throws IllegalStateException when the key has no entry, or its entry is empty.
     */
    private String resolvePayloadFileName(String orderType) {
        Object mapped = orderType == null ? null : Data.get().get(orderType);
        String fileName = mapped == null ? null : String.valueOf(mapped).trim();

        if (fileName == null || fileName.isEmpty() || "null".equalsIgnoreCase(fileName)) {
            throw new IllegalStateException("No payload file is mapped to orderType key '" + orderType
                    + "'. Add it to src/test/resources/data/<env>/default-data.yaml, or point the step"
                    + " definition at an existing key. Keys currently defined there must also exist as"
                    + " src/test/resources/api/request/<name>.json.");
        }
        return fileName;
    }

    /**
     * Clears every derived value before a new request is built. Without this the accumulators
     * ({@code requestedDeliveryCharges}, {@code requestedCashBack}) keep the previous scenario's
     * totals, and a scenario that does not set a field would silently assert against the
     * previous scenario's value.
     */
    private static void resetRequestData() {
        requestQuantity = 0;
        requestedDeliveryCharges = 0;
        requestedCashBack = 0;
        requestProductPrice = 0;
        requestAdvanceAmount = 0;
        requestTotalAmount = 0;
        requestProductTaxes = 0;
        lineItemAmount = 0;
        requestLineAmount = 0;
        formatLineItemTotalAmount = null;
        requestWidth = null;
        requestThickness = null;
        requestLength = null;
        requestPaymentType = null;
        requestedSourceSeller = null;
        requestOrderType = null;
        requestNodelorNonNodel = null;
        requestDeliveryType = null;
        requestCustomerId = null;
    }
}
