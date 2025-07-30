
package com.qapitol.jsw.apitest.pojos.order.rcv2CartSizeResponse;

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
    "enquiryCart",
    "orderCart"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("enquiryCart")
    private EnquiryCart enquiryCart;
    @JsonProperty("orderCart")
    private OrderCart orderCart;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("enquiryCart")
    public EnquiryCart getEnquiryCart() {
        return enquiryCart;
    }

    @JsonProperty("enquiryCart")
    public void setEnquiryCart(EnquiryCart enquiryCart) {
        this.enquiryCart = enquiryCart;
    }

    @JsonProperty("orderCart")
    public OrderCart getOrderCart() {
        return orderCart;
    }

    @JsonProperty("orderCart")
    public void setOrderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
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
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("enquiryCart");
        sb.append('=');
        sb.append(((this.enquiryCart == null)?"<null>":this.enquiryCart));
        sb.append(',');
        sb.append("orderCart");
        sb.append('=');
        sb.append(((this.orderCart == null)?"<null>":this.orderCart));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.enquiryCart == null)? 0 :this.enquiryCart.hashCode()));
        result = ((result* 31)+((this.orderCart == null)? 0 :this.orderCart.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.enquiryCart == rhs.enquiryCart)||((this.enquiryCart!= null)&&this.enquiryCart.equals(rhs.enquiryCart))))&&((this.orderCart == rhs.orderCart)||((this.orderCart!= null)&&this.orderCart.equals(rhs.orderCart))));
    }

}
