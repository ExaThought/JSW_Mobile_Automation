
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "identifier",
    "totalLineItemPrice",
    "totalDiscountedLineItemPrice",
    "totalLineItemTax",
    "totalOrderAmount",
    "totalPrice",
    "totalReceivedAmount",
    "totalDueAmount",
    "totalRequestedAmount",
    "netAdvancePayable",
    "paymentBeforeDelivery",
    "discounts",
    "adjustments",
    "charges",
    "paymentAwaited",
    "lastSuccessfulPayment",
    "lastConfirmedPayment",
    "payments",
    "associatedSkus",
    "totalOtherTax",
    "totalTaxOnDiscountedAmount",
    "delivery",
    "deliveryTax"
})
@Generated("jsonschema2pojo")
public class PaymentSummary {

    @JsonProperty("identifier")
    private Identifier__1 identifier;
    @JsonProperty("totalLineItemPrice")
    private TotalLineItemPrice totalLineItemPrice;
    @JsonProperty("totalDiscountedLineItemPrice")
    private TotalDiscountedLineItemPrice totalDiscountedLineItemPrice;
    @JsonProperty("totalLineItemTax")
    private Object totalLineItemTax;
    @JsonProperty("totalOrderAmount")
    private TotalOrderAmount__1 totalOrderAmount;
    @JsonProperty("totalPrice")
    private TotalPrice totalPrice;
    @JsonProperty("totalReceivedAmount")
    private TotalReceivedAmount totalReceivedAmount;
    @JsonProperty("totalDueAmount")
    private TotalDueAmount totalDueAmount;
    @JsonProperty("totalRequestedAmount")
    private TotalRequestedAmount totalRequestedAmount;
    @JsonProperty("netAdvancePayable")
    private NetAdvancePayable netAdvancePayable;
    @JsonProperty("paymentBeforeDelivery")
    private PaymentBeforeDelivery paymentBeforeDelivery;
    @JsonProperty("discounts")
    private Discounts discounts;
    @JsonProperty("adjustments")
    private Adjustments adjustments;
    @JsonProperty("charges")
    private Charges charges;
    @JsonProperty("paymentAwaited")
    private PaymentAwaited paymentAwaited;
    @JsonProperty("lastSuccessfulPayment")
    private Object lastSuccessfulPayment;
    @JsonProperty("lastConfirmedPayment")
    private Object lastConfirmedPayment;
    @JsonProperty("payments")
    private List<Payment> payments = new ArrayList<Payment>();
    @JsonProperty("associatedSkus")
    private List<String> associatedSkus = new ArrayList<String>();
    @JsonProperty("totalOtherTax")
    private Object totalOtherTax;
    @JsonProperty("totalTaxOnDiscountedAmount")
    private Object totalTaxOnDiscountedAmount;
    @JsonProperty("delivery")
    private Delivery__1 delivery;
    @JsonProperty("deliveryTax")
    private DeliveryTax deliveryTax;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("identifier")
    public Identifier__1 getIdentifier() {
        return identifier;
    }

    @JsonProperty("identifier")
    public void setIdentifier(Identifier__1 identifier) {
        this.identifier = identifier;
    }

    @JsonProperty("totalLineItemPrice")
    public TotalLineItemPrice getTotalLineItemPrice() {
        return totalLineItemPrice;
    }

    @JsonProperty("totalLineItemPrice")
    public void setTotalLineItemPrice(TotalLineItemPrice totalLineItemPrice) {
        this.totalLineItemPrice = totalLineItemPrice;
    }

    @JsonProperty("totalDiscountedLineItemPrice")
    public TotalDiscountedLineItemPrice getTotalDiscountedLineItemPrice() {
        return totalDiscountedLineItemPrice;
    }

    @JsonProperty("totalDiscountedLineItemPrice")
    public void setTotalDiscountedLineItemPrice(TotalDiscountedLineItemPrice totalDiscountedLineItemPrice) {
        this.totalDiscountedLineItemPrice = totalDiscountedLineItemPrice;
    }

    @JsonProperty("totalLineItemTax")
    public Object getTotalLineItemTax() {
        return totalLineItemTax;
    }

    @JsonProperty("totalLineItemTax")
    public void setTotalLineItemTax(Object totalLineItemTax) {
        this.totalLineItemTax = totalLineItemTax;
    }

    @JsonProperty("totalOrderAmount")
    public TotalOrderAmount__1 getTotalOrderAmount() {
        return totalOrderAmount;
    }

    @JsonProperty("totalOrderAmount")
    public void setTotalOrderAmount(TotalOrderAmount__1 totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    @JsonProperty("totalPrice")
    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    @JsonProperty("totalPrice")
    public void setTotalPrice(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonProperty("totalReceivedAmount")
    public TotalReceivedAmount getTotalReceivedAmount() {
        return totalReceivedAmount;
    }

    @JsonProperty("totalReceivedAmount")
    public void setTotalReceivedAmount(TotalReceivedAmount totalReceivedAmount) {
        this.totalReceivedAmount = totalReceivedAmount;
    }

    @JsonProperty("totalDueAmount")
    public TotalDueAmount getTotalDueAmount() {
        return totalDueAmount;
    }

    @JsonProperty("totalDueAmount")
    public void setTotalDueAmount(TotalDueAmount totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    @JsonProperty("totalRequestedAmount")
    public TotalRequestedAmount getTotalRequestedAmount() {
        return totalRequestedAmount;
    }

    @JsonProperty("totalRequestedAmount")
    public void setTotalRequestedAmount(TotalRequestedAmount totalRequestedAmount) {
        this.totalRequestedAmount = totalRequestedAmount;
    }

    @JsonProperty("netAdvancePayable")
    public NetAdvancePayable getNetAdvancePayable() {
        return netAdvancePayable;
    }

    @JsonProperty("netAdvancePayable")
    public void setNetAdvancePayable(NetAdvancePayable netAdvancePayable) {
        this.netAdvancePayable = netAdvancePayable;
    }

    @JsonProperty("paymentBeforeDelivery")
    public PaymentBeforeDelivery getPaymentBeforeDelivery() {
        return paymentBeforeDelivery;
    }

    @JsonProperty("paymentBeforeDelivery")
    public void setPaymentBeforeDelivery(PaymentBeforeDelivery paymentBeforeDelivery) {
        this.paymentBeforeDelivery = paymentBeforeDelivery;
    }

    @JsonProperty("discounts")
    public Discounts getDiscounts() {
        return discounts;
    }

    @JsonProperty("discounts")
    public void setDiscounts(Discounts discounts) {
        this.discounts = discounts;
    }

    @JsonProperty("adjustments")
    public Adjustments getAdjustments() {
        return adjustments;
    }

    @JsonProperty("adjustments")
    public void setAdjustments(Adjustments adjustments) {
        this.adjustments = adjustments;
    }

    @JsonProperty("charges")
    public Charges getCharges() {
        return charges;
    }

    @JsonProperty("charges")
    public void setCharges(Charges charges) {
        this.charges = charges;
    }

    @JsonProperty("paymentAwaited")
    public PaymentAwaited getPaymentAwaited() {
        return paymentAwaited;
    }

    @JsonProperty("paymentAwaited")
    public void setPaymentAwaited(PaymentAwaited paymentAwaited) {
        this.paymentAwaited = paymentAwaited;
    }

    @JsonProperty("lastSuccessfulPayment")
    public Object getLastSuccessfulPayment() {
        return lastSuccessfulPayment;
    }

    @JsonProperty("lastSuccessfulPayment")
    public void setLastSuccessfulPayment(Object lastSuccessfulPayment) {
        this.lastSuccessfulPayment = lastSuccessfulPayment;
    }

    @JsonProperty("lastConfirmedPayment")
    public Object getLastConfirmedPayment() {
        return lastConfirmedPayment;
    }

    @JsonProperty("lastConfirmedPayment")
    public void setLastConfirmedPayment(Object lastConfirmedPayment) {
        this.lastConfirmedPayment = lastConfirmedPayment;
    }

    @JsonProperty("payments")
    public List<Payment> getPayments() {
        return payments;
    }

    @JsonProperty("payments")
    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @JsonProperty("associatedSkus")
    public List<String> getAssociatedSkus() {
        return associatedSkus;
    }

    @JsonProperty("associatedSkus")
    public void setAssociatedSkus(List<String> associatedSkus) {
        this.associatedSkus = associatedSkus;
    }

    @JsonProperty("totalOtherTax")
    public Object getTotalOtherTax() {
        return totalOtherTax;
    }

    @JsonProperty("totalOtherTax")
    public void setTotalOtherTax(Object totalOtherTax) {
        this.totalOtherTax = totalOtherTax;
    }

    @JsonProperty("totalTaxOnDiscountedAmount")
    public Object getTotalTaxOnDiscountedAmount() {
        return totalTaxOnDiscountedAmount;
    }

    @JsonProperty("totalTaxOnDiscountedAmount")
    public void setTotalTaxOnDiscountedAmount(Object totalTaxOnDiscountedAmount) {
        this.totalTaxOnDiscountedAmount = totalTaxOnDiscountedAmount;
    }

    @JsonProperty("delivery")
    public Delivery__1 getDelivery() {
        return delivery;
    }

    @JsonProperty("delivery")
    public void setDelivery(Delivery__1 delivery) {
        this.delivery = delivery;
    }

    @JsonProperty("deliveryTax")
    public DeliveryTax getDeliveryTax() {
        return deliveryTax;
    }

    @JsonProperty("deliveryTax")
    public void setDeliveryTax(DeliveryTax deliveryTax) {
        this.deliveryTax = deliveryTax;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PaymentSummary.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("identifier");
        sb.append('=');
        sb.append(((this.identifier == null)?"<null>":this.identifier));
        sb.append(',');
        sb.append("totalLineItemPrice");
        sb.append('=');
        sb.append(((this.totalLineItemPrice == null)?"<null>":this.totalLineItemPrice));
        sb.append(',');
        sb.append("totalDiscountedLineItemPrice");
        sb.append('=');
        sb.append(((this.totalDiscountedLineItemPrice == null)?"<null>":this.totalDiscountedLineItemPrice));
        sb.append(',');
        sb.append("totalLineItemTax");
        sb.append('=');
        sb.append(((this.totalLineItemTax == null)?"<null>":this.totalLineItemTax));
        sb.append(',');
        sb.append("totalOrderAmount");
        sb.append('=');
        sb.append(((this.totalOrderAmount == null)?"<null>":this.totalOrderAmount));
        sb.append(',');
        sb.append("totalPrice");
        sb.append('=');
        sb.append(((this.totalPrice == null)?"<null>":this.totalPrice));
        sb.append(',');
        sb.append("totalReceivedAmount");
        sb.append('=');
        sb.append(((this.totalReceivedAmount == null)?"<null>":this.totalReceivedAmount));
        sb.append(',');
        sb.append("totalDueAmount");
        sb.append('=');
        sb.append(((this.totalDueAmount == null)?"<null>":this.totalDueAmount));
        sb.append(',');
        sb.append("totalRequestedAmount");
        sb.append('=');
        sb.append(((this.totalRequestedAmount == null)?"<null>":this.totalRequestedAmount));
        sb.append(',');
        sb.append("netAdvancePayable");
        sb.append('=');
        sb.append(((this.netAdvancePayable == null)?"<null>":this.netAdvancePayable));
        sb.append(',');
        sb.append("paymentBeforeDelivery");
        sb.append('=');
        sb.append(((this.paymentBeforeDelivery == null)?"<null>":this.paymentBeforeDelivery));
        sb.append(',');
        sb.append("discounts");
        sb.append('=');
        sb.append(((this.discounts == null)?"<null>":this.discounts));
        sb.append(',');
        sb.append("adjustments");
        sb.append('=');
        sb.append(((this.adjustments == null)?"<null>":this.adjustments));
        sb.append(',');
        sb.append("charges");
        sb.append('=');
        sb.append(((this.charges == null)?"<null>":this.charges));
        sb.append(',');
        sb.append("paymentAwaited");
        sb.append('=');
        sb.append(((this.paymentAwaited == null)?"<null>":this.paymentAwaited));
        sb.append(',');
        sb.append("lastSuccessfulPayment");
        sb.append('=');
        sb.append(((this.lastSuccessfulPayment == null)?"<null>":this.lastSuccessfulPayment));
        sb.append(',');
        sb.append("lastConfirmedPayment");
        sb.append('=');
        sb.append(((this.lastConfirmedPayment == null)?"<null>":this.lastConfirmedPayment));
        sb.append(',');
        sb.append("payments");
        sb.append('=');
        sb.append(((this.payments == null)?"<null>":this.payments));
        sb.append(',');
        sb.append("associatedSkus");
        sb.append('=');
        sb.append(((this.associatedSkus == null)?"<null>":this.associatedSkus));
        sb.append(',');
        sb.append("totalOtherTax");
        sb.append('=');
        sb.append(((this.totalOtherTax == null)?"<null>":this.totalOtherTax));
        sb.append(',');
        sb.append("totalTaxOnDiscountedAmount");
        sb.append('=');
        sb.append(((this.totalTaxOnDiscountedAmount == null)?"<null>":this.totalTaxOnDiscountedAmount));
        sb.append(',');
        sb.append("delivery");
        sb.append('=');
        sb.append(((this.delivery == null)?"<null>":this.delivery));
        sb.append(',');
        sb.append("deliveryTax");
        sb.append('=');
        sb.append(((this.deliveryTax == null)?"<null>":this.deliveryTax));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.totalReceivedAmount == null)? 0 :this.totalReceivedAmount.hashCode()));
        result = ((result* 31)+((this.adjustments == null)? 0 :this.adjustments.hashCode()));
        result = ((result* 31)+((this.totalPrice == null)? 0 :this.totalPrice.hashCode()));
        result = ((result* 31)+((this.payments == null)? 0 :this.payments.hashCode()));
        result = ((result* 31)+((this.totalDiscountedLineItemPrice == null)? 0 :this.totalDiscountedLineItemPrice.hashCode()));
        result = ((result* 31)+((this.paymentBeforeDelivery == null)? 0 :this.paymentBeforeDelivery.hashCode()));
        result = ((result* 31)+((this.totalLineItemTax == null)? 0 :this.totalLineItemTax.hashCode()));
        result = ((result* 31)+((this.discounts == null)? 0 :this.discounts.hashCode()));
        result = ((result* 31)+((this.deliveryTax == null)? 0 :this.deliveryTax.hashCode()));
        result = ((result* 31)+((this.totalOtherTax == null)? 0 :this.totalOtherTax.hashCode()));
        result = ((result* 31)+((this.identifier == null)? 0 :this.identifier.hashCode()));
        result = ((result* 31)+((this.delivery == null)? 0 :this.delivery.hashCode()));
        result = ((result* 31)+((this.lastSuccessfulPayment == null)? 0 :this.lastSuccessfulPayment.hashCode()));
        result = ((result* 31)+((this.totalRequestedAmount == null)? 0 :this.totalRequestedAmount.hashCode()));
        result = ((result* 31)+((this.associatedSkus == null)? 0 :this.associatedSkus.hashCode()));
        result = ((result* 31)+((this.totalLineItemPrice == null)? 0 :this.totalLineItemPrice.hashCode()));
        result = ((result* 31)+((this.charges == null)? 0 :this.charges.hashCode()));
        result = ((result* 31)+((this.totalOrderAmount == null)? 0 :this.totalOrderAmount.hashCode()));
        result = ((result* 31)+((this.paymentAwaited == null)? 0 :this.paymentAwaited.hashCode()));
        result = ((result* 31)+((this.totalDueAmount == null)? 0 :this.totalDueAmount.hashCode()));
        result = ((result* 31)+((this.lastConfirmedPayment == null)? 0 :this.lastConfirmedPayment.hashCode()));
        result = ((result* 31)+((this.netAdvancePayable == null)? 0 :this.netAdvancePayable.hashCode()));
        result = ((result* 31)+((this.totalTaxOnDiscountedAmount == null)? 0 :this.totalTaxOnDiscountedAmount.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentSummary) == false) {
            return false;
        }
        PaymentSummary rhs = ((PaymentSummary) other);
        return (((((((((((((((((((((((((this.totalReceivedAmount == rhs.totalReceivedAmount)||((this.totalReceivedAmount!= null)&&this.totalReceivedAmount.equals(rhs.totalReceivedAmount)))&&((this.adjustments == rhs.adjustments)||((this.adjustments!= null)&&this.adjustments.equals(rhs.adjustments))))&&((this.totalPrice == rhs.totalPrice)||((this.totalPrice!= null)&&this.totalPrice.equals(rhs.totalPrice))))&&((this.payments == rhs.payments)||((this.payments!= null)&&this.payments.equals(rhs.payments))))&&((this.totalDiscountedLineItemPrice == rhs.totalDiscountedLineItemPrice)||((this.totalDiscountedLineItemPrice!= null)&&this.totalDiscountedLineItemPrice.equals(rhs.totalDiscountedLineItemPrice))))&&((this.paymentBeforeDelivery == rhs.paymentBeforeDelivery)||((this.paymentBeforeDelivery!= null)&&this.paymentBeforeDelivery.equals(rhs.paymentBeforeDelivery))))&&((this.totalLineItemTax == rhs.totalLineItemTax)||((this.totalLineItemTax!= null)&&this.totalLineItemTax.equals(rhs.totalLineItemTax))))&&((this.discounts == rhs.discounts)||((this.discounts!= null)&&this.discounts.equals(rhs.discounts))))&&((this.deliveryTax == rhs.deliveryTax)||((this.deliveryTax!= null)&&this.deliveryTax.equals(rhs.deliveryTax))))&&((this.totalOtherTax == rhs.totalOtherTax)||((this.totalOtherTax!= null)&&this.totalOtherTax.equals(rhs.totalOtherTax))))&&((this.identifier == rhs.identifier)||((this.identifier!= null)&&this.identifier.equals(rhs.identifier))))&&((this.delivery == rhs.delivery)||((this.delivery!= null)&&this.delivery.equals(rhs.delivery))))&&((this.lastSuccessfulPayment == rhs.lastSuccessfulPayment)||((this.lastSuccessfulPayment!= null)&&this.lastSuccessfulPayment.equals(rhs.lastSuccessfulPayment))))&&((this.totalRequestedAmount == rhs.totalRequestedAmount)||((this.totalRequestedAmount!= null)&&this.totalRequestedAmount.equals(rhs.totalRequestedAmount))))&&((this.associatedSkus == rhs.associatedSkus)||((this.associatedSkus!= null)&&this.associatedSkus.equals(rhs.associatedSkus))))&&((this.totalLineItemPrice == rhs.totalLineItemPrice)||((this.totalLineItemPrice!= null)&&this.totalLineItemPrice.equals(rhs.totalLineItemPrice))))&&((this.charges == rhs.charges)||((this.charges!= null)&&this.charges.equals(rhs.charges))))&&((this.totalOrderAmount == rhs.totalOrderAmount)||((this.totalOrderAmount!= null)&&this.totalOrderAmount.equals(rhs.totalOrderAmount))))&&((this.paymentAwaited == rhs.paymentAwaited)||((this.paymentAwaited!= null)&&this.paymentAwaited.equals(rhs.paymentAwaited))))&&((this.totalDueAmount == rhs.totalDueAmount)||((this.totalDueAmount!= null)&&this.totalDueAmount.equals(rhs.totalDueAmount))))&&((this.lastConfirmedPayment == rhs.lastConfirmedPayment)||((this.lastConfirmedPayment!= null)&&this.lastConfirmedPayment.equals(rhs.lastConfirmedPayment))))&&((this.netAdvancePayable == rhs.netAdvancePayable)||((this.netAdvancePayable!= null)&&this.netAdvancePayable.equals(rhs.netAdvancePayable))))&&((this.totalTaxOnDiscountedAmount == rhs.totalTaxOnDiscountedAmount)||((this.totalTaxOnDiscountedAmount!= null)&&this.totalTaxOnDiscountedAmount.equals(rhs.totalTaxOnDiscountedAmount))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
