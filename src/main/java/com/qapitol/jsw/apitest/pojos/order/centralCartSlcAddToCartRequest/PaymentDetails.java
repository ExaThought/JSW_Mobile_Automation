
package com.qapitol.jsw.apitest.pojos.order.centralCartSlcAddToCartRequest;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "payment_method",
    "advance_required",
    "advance_amount",
    "order_payment_transaction_type"
})
@Generated("jsonschema2pojo")
public class PaymentDetails {

    @JsonProperty("payment_method")
    private String paymentMethod;
    @JsonProperty("advance_required")
    private Boolean advanceRequired;
    @JsonProperty("advance_amount")
    private String advanceAmount;
    @JsonProperty("order_payment_transaction_type")
    private String orderPaymentTransactionType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("payment_method")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @JsonProperty("payment_method")
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @JsonProperty("advance_required")
    public Boolean getAdvanceRequired() {
        return advanceRequired;
    }

    @JsonProperty("advance_required")
    public void setAdvanceRequired(Boolean advanceRequired) {
        this.advanceRequired = advanceRequired;
    }

    @JsonProperty("advance_amount")
    public String getAdvanceAmount() {
        return advanceAmount;
    }

    @JsonProperty("advance_amount")
    public void setAdvanceAmount(String advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    @JsonProperty("order_payment_transaction_type")
    public String getOrderPaymentTransactionType() {
        return orderPaymentTransactionType;
    }

    @JsonProperty("order_payment_transaction_type")
    public void setOrderPaymentTransactionType(String orderPaymentTransactionType) {
        this.orderPaymentTransactionType = orderPaymentTransactionType;
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
        sb.append(PaymentDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("paymentMethod");
        sb.append('=');
        sb.append(((this.paymentMethod == null)?"<null>":this.paymentMethod));
        sb.append(',');
        sb.append("advanceRequired");
        sb.append('=');
        sb.append(((this.advanceRequired == null)?"<null>":this.advanceRequired));
        sb.append(',');
        sb.append("advanceAmount");
        sb.append('=');
        sb.append(((this.advanceAmount == null)?"<null>":this.advanceAmount));
        sb.append(',');
        sb.append("orderPaymentTransactionType");
        sb.append('=');
        sb.append(((this.orderPaymentTransactionType == null)?"<null>":this.orderPaymentTransactionType));
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
        result = ((result* 31)+((this.advanceRequired == null)? 0 :this.advanceRequired.hashCode()));
        result = ((result* 31)+((this.paymentMethod == null)? 0 :this.paymentMethod.hashCode()));
        result = ((result* 31)+((this.advanceAmount == null)? 0 :this.advanceAmount.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.orderPaymentTransactionType == null)? 0 :this.orderPaymentTransactionType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentDetails) == false) {
            return false;
        }
        PaymentDetails rhs = ((PaymentDetails) other);
        return ((((((this.advanceRequired == rhs.advanceRequired)||((this.advanceRequired!= null)&&this.advanceRequired.equals(rhs.advanceRequired)))&&((this.paymentMethod == rhs.paymentMethod)||((this.paymentMethod!= null)&&this.paymentMethod.equals(rhs.paymentMethod))))&&((this.advanceAmount == rhs.advanceAmount)||((this.advanceAmount!= null)&&this.advanceAmount.equals(rhs.advanceAmount))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.orderPaymentTransactionType == rhs.orderPaymentTransactionType)||((this.orderPaymentTransactionType!= null)&&this.orderPaymentTransactionType.equals(rhs.orderPaymentTransactionType))));
    }

}
