
package com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiRequest;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sf_customer_id",
    "cart_type",
    "store_front"
})
@Generated("jsonschema2pojo")
public class CentralCartFetchApiReq {

    @JsonProperty("sf_customer_id")
    private String sfCustomerId;
    @JsonProperty("cart_type")
    private String cartType;
    @JsonProperty("store_front")
    private String storeFront;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sf_customer_id")
    public String getSfCustomerId() {
        return sfCustomerId;
    }

    @JsonProperty("sf_customer_id")
    public void setSfCustomerId(String sfCustomerId) {
        this.sfCustomerId = sfCustomerId;
    }

    @JsonProperty("cart_type")
    public String getCartType() {
        return cartType;
    }

    @JsonProperty("cart_type")
    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    @JsonProperty("store_front")
    public String getStoreFront() {
        return storeFront;
    }

    @JsonProperty("store_front")
    public void setStoreFront(String storeFront) {
        this.storeFront = storeFront;
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
        sb.append(CentralCartFetchApiReq.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sfCustomerId");
        sb.append('=');
        sb.append(((this.sfCustomerId == null)?"<null>":this.sfCustomerId));
        sb.append(',');
        sb.append("cartType");
        sb.append('=');
        sb.append(((this.cartType == null)?"<null>":this.cartType));
        sb.append(',');
        sb.append("storeFront");
        sb.append('=');
        sb.append(((this.storeFront == null)?"<null>":this.storeFront));
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
        result = ((result* 31)+((this.cartType == null)? 0 :this.cartType.hashCode()));
        result = ((result* 31)+((this.storeFront == null)? 0 :this.storeFront.hashCode()));
        result = ((result* 31)+((this.sfCustomerId == null)? 0 :this.sfCustomerId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CentralCartFetchApiReq) == false) {
            return false;
        }
        CentralCartFetchApiReq rhs = ((CentralCartFetchApiReq) other);
        return (((((this.cartType == rhs.cartType)||((this.cartType!= null)&&this.cartType.equals(rhs.cartType)))&&((this.storeFront == rhs.storeFront)||((this.storeFront!= null)&&this.storeFront.equals(rhs.storeFront))))&&((this.sfCustomerId == rhs.sfCustomerId)||((this.sfCustomerId!= null)&&this.sfCustomerId.equals(rhs.sfCustomerId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
