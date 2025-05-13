
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
    "order_type",
    "payment_details"
})
@Generated("jsonschema2pojo")
public class OrderDetails {

    @JsonProperty("order_type")
    private String orderType;
    @JsonProperty("payment_details")
    private PaymentDetails paymentDetails;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("order_type")
    public String getOrderType() {
        return orderType;
    }

    @JsonProperty("order_type")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @JsonProperty("payment_details")
    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    @JsonProperty("payment_details")
    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
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
        sb.append(OrderDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderType");
        sb.append('=');
        sb.append(((this.orderType == null)?"<null>":this.orderType));
        sb.append(',');
        sb.append("paymentDetails");
        sb.append('=');
        sb.append(((this.paymentDetails == null)?"<null>":this.paymentDetails));
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
        result = ((result* 31)+((this.paymentDetails == null)? 0 :this.paymentDetails.hashCode()));
        result = ((result* 31)+((this.orderType == null)? 0 :this.orderType.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderDetails) == false) {
            return false;
        }
        OrderDetails rhs = ((OrderDetails) other);
        return ((((this.paymentDetails == rhs.paymentDetails)||((this.paymentDetails!= null)&&this.paymentDetails.equals(rhs.paymentDetails)))&&((this.orderType == rhs.orderType)||((this.orderType!= null)&&this.orderType.equals(rhs.orderType))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
