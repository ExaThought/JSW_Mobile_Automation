
package com.qapitol.jsw.apitest.pojos.order.updateLineItemRequestPayload;

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
    "cartType",
    "cartJourneyType"
})
@Generated("jsonschema2pojo")
public class CartIdentifier {

    @JsonProperty("cartType")
    private String cartType;
    @JsonProperty("cartJourneyType")
    private String cartJourneyType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cartType")
    public String getCartType() {
        return cartType;
    }

    @JsonProperty("cartType")
    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    @JsonProperty("cartJourneyType")
    public String getCartJourneyType() {
        return cartJourneyType;
    }

    @JsonProperty("cartJourneyType")
    public void setCartJourneyType(String cartJourneyType) {
        this.cartJourneyType = cartJourneyType;
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
        result = ((result* 31)+((this.cartJourneyType == null)? 0 :this.cartJourneyType.hashCode()));
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
        return ((((this.cartType == rhs.cartType)||((this.cartType!= null)&&this.cartType.equals(rhs.cartType)))&&((this.cartJourneyType == rhs.cartJourneyType)||((this.cartJourneyType!= null)&&this.cartJourneyType.equals(rhs.cartJourneyType))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
