
package com.qapitol.jsw.apitest.pojos.order.jswServiceRcv2Request;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    "cart_address",
    "delivery_type"
})
@Generated("jsonschema2pojo")
public class ShippingDetails {

    @JsonProperty("cart_address")
    private List<CartAddress> cartAddress = new ArrayList<CartAddress>();
    @JsonProperty("delivery_type")
    private String deliveryType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cart_address")
    public List<CartAddress> getCartAddress() {
        return cartAddress;
    }

    @JsonProperty("cart_address")
    public void setCartAddress(List<CartAddress> cartAddress) {
        this.cartAddress = cartAddress;
    }

    @JsonProperty("delivery_type")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("delivery_type")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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
        sb.append(ShippingDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cartAddress");
        sb.append('=');
        sb.append(((this.cartAddress == null)?"<null>":this.cartAddress));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
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
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.cartAddress == null)? 0 :this.cartAddress.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShippingDetails) == false) {
            return false;
        }
        ShippingDetails rhs = ((ShippingDetails) other);
        return ((((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType)))&&((this.cartAddress == rhs.cartAddress)||((this.cartAddress!= null)&&this.cartAddress.equals(rhs.cartAddress))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
