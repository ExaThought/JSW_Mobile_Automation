package com.qapitol.jsw.apitest.apis.impl;

import com.github.dockerjava.api.exception.BadRequestException;
import com.qapitol.jsw.apitest.apis.RequestApi;

import com.qapitol.jsw.apitest.pojos.order.ctSlcSloRequestPayload.CtSlcSloRequestPayload;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloRequestPayload.LineItem;
import com.qapitol.jsw.apitest.pojos.order.ctSlcSloResponse.CtSlcSloRes;
import com.qapitol.sauron.data.Data;




import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import static com.qapitol.jsw.utils.CustomUtills.*;

public class CtSlcSlocreationImpl extends BaseApiImpl {
    private String addressId;
    private String customerId;
    private String opportunityId;
    public static double requestQuantity=0;
    public static double requestedDeliveryCharges=0;
    public static double requestedCashBack=0;
    public static double requestProductPrice=0;
    public static double requestAdvanaceAmount=0;
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

    public CtSlcSloRes getSlcSloResponse(String orderType) throws IOException {
        try {

            //Load the Payload
            CtSlcSloRequestPayload requestPayload = getPayloadJsonFileAsPOJO((String) Data.get().get(orderType), CtSlcSloRequestPayload.class);

            //Read the data from Yaml
            opportunityId = "006" + java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15);
            customerId = Data.get().get("CustomerId").toString();
            addressId = Data.get().get("AddressId").toString();

            //Set RequestPayload
            requestPayload.setBillingAddressId(addressId);
            requestPayload.setShippingAddressId(addressId);
            requestPayload.setSalesforceOpportunityId(opportunityId);
            requestPayload.setCustomerId(customerId);

            //Request API
            RequestApi requestApi = ccpUrl().create(RequestApi.class);
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

                    requestedCashBack=0;
                    if(item.getCashbackDetails().getCashbackRate() != null){
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

            requestPaymentType=requestPayload.getOrderPaymentType();
            requestNodelorNonNodel=requestPayload.getPayTo();
            requestOrderType=requestPayload.getOrderType();
            requestDeliveryType=requestPayload.getDeliveryType();
            if (requestPayload.getAdvancePayment() != null) {
                requestAdvanaceAmount=requestPayload.getAdvancePayment();
            }


            return responseBody;
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }
}
