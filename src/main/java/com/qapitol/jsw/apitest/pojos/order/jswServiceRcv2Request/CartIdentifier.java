
package com.qapitol.jsw.apitest.pojos.order.jswServiceRcv2Request;

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
    "cart_type",
    "cart_journey_type",
    "store_front",
    "device_type"
})
@Generated("jsonschema2pojo")
public class CartIdentifier {

    @JsonProperty("cart_type")
    private String cartType;
    @JsonProperty("cart_journey_type")
    private String cartJourneyType;
    @JsonProperty("store_front")
    private String storeFront;
    @JsonProperty("device_type")
    private String deviceType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cart_type")
    public String getCartType() {
        return cartType;
    }

    @JsonProperty("cart_type")
    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    @JsonProperty("cart_journey_type")
    public String getCartJourneyType() {
        return cartJourneyType;
    }

    @JsonProperty("cart_journey_type")
    public void setCartJourneyType(String cartJourneyType) {
        this.cartJourneyType = cartJourneyType;
    }

    @JsonProperty("store_front")
    public String getStoreFront() {
        return storeFront;
    }

    @JsonProperty("store_front")
    public void setStoreFront(String storeFront) {
        this.storeFront = storeFront;
    }

    @JsonProperty("device_type")
    public String getDeviceType() {
        return deviceType;
    }

    @JsonProperty("device_type")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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
        sb.append(CartIdentifier.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cartType");
        sb.append('=');
        sb.append(((this.cartType == null)?"<null>":this.cartType));
        sb.append(',');
        sb.append("cartJourneyType");
        sb.append('=');
        sb.append(((this.cartJourneyType == null)?"<null>":this.cartJourneyType));
        sb.append(',');
        sb.append("storeFront");
        sb.append('=');
        sb.append(((this.storeFront == null)?"<null>":this.storeFront));
        sb.append(',');
        sb.append("deviceType");
        sb.append('=');
        sb.append(((this.deviceType == null)?"<null>":this.deviceType));
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
        result = ((result* 31)+((this.deviceType == null)? 0 :this.deviceType.hashCode()));
        result = ((result* 31)+((this.cartType == null)? 0 :this.cartType.hashCode()));
        result = ((result* 31)+((this.cartJourneyType == null)? 0 :this.cartJourneyType.hashCode()));
        result = ((result* 31)+((this.storeFront == null)? 0 :this.storeFront.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CartIdentifier) == false) {
            return false;
        }
        CartIdentifier rhs = ((CartIdentifier) other);
        return ((((((this.deviceType == rhs.deviceType)||((this.deviceType!= null)&&this.deviceType.equals(rhs.deviceType)))&&((this.cartType == rhs.cartType)||((this.cartType!= null)&&this.cartType.equals(rhs.cartType))))&&((this.cartJourneyType == rhs.cartJourneyType)||((this.cartJourneyType!= null)&&this.cartJourneyType.equals(rhs.cartJourneyType))))&&((this.storeFront == rhs.storeFront)||((this.storeFront!= null)&&this.storeFront.equals(rhs.storeFront))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
