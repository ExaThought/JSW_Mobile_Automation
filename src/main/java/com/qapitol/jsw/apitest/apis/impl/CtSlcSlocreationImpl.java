package com.qapitol.jsw.apitest.apis.impl;

import com.github.dockerjava.api.exception.BadRequestException;
import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.ctCashAndCarryRequestPayload.CtSlcSloCashAndCarryReq;
import com.qapitol.jsw.apitest.pojos.order.ctCashAndCarryRequestPayload.LineItem;
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

    public CtSlcSloRes getSlcSloResponse() throws IOException {
        try {
            //Load the Payload
            CtSlcSloCashAndCarryReq requestPayload = getPayloadJsonFileAsPOJO((String) Data.get().get("CT_SLC_SLO_CASH_AND_CARRY"), CtSlcSloCashAndCarryReq.class);

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
            CtSlcSloRes responseBody = requestApi.requestCtSlcSlo(requestPayload);

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

                    if(item.getCashbackDetails().getCashbackRate() != null){
                        double cashBack = item.getCashbackDetails().getCashbackRate() * (item.getOrderedQuantity() / 1000);
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
            return responseBody;
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

}
