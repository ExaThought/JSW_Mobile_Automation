
package com.qapitol.jsw.apitest.pojos.order.centralCartDeleteLineItemRequest;

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
    "cart_id",
    "line_item_ids",
    "sf_customer_id",
    "cart_identifier"
})
@Generated("jsonschema2pojo")
public class CentralCartDeleteLineItemReq {

    @JsonProperty("cart_id")
    private String cartId;
    @JsonProperty("line_item_ids")
    private List<String> lineItemIds = new ArrayList<String>();
    @JsonProperty("sf_customer_id")
    private String sfCustomerId;
    @JsonProperty("cart_identifier")
    private CartIdentifier cartIdentifier;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cart_id")
    public String getCartId() {
        return cartId;
    }

    @JsonProperty("cart_id")
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @JsonProperty("line_item_ids")
    public List<String> getLineItemIds() {
        return lineItemIds;
    }

    @JsonProperty("line_item_ids")
    public void setLineItemIds(List<String> lineItemIds) {
        this.lineItemIds = lineItemIds;
    }

    @JsonProperty("sf_customer_id")
    public String getSfCustomerId() {
        return sfCustomerId;
    }

    @JsonProperty("sf_customer_id")
    public void setSfCustomerId(String sfCustomerId) {
        this.sfCustomerId = sfCustomerId;
    }

    @JsonProperty("cart_identifier")
    public CartIdentifier getCartIdentifier() {
        return cartIdentifier;
    }

    @JsonProperty("cart_identifier")
    public void setCartIdentifier(CartIdentifier cartIdentifier) {
        this.cartIdentifier = cartIdentifier;
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
        sb.append(CentralCartDeleteLineItemReq.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cartId");
        sb.append('=');
        sb.append(((this.cartId == null)?"<null>":this.cartId));
        sb.append(',');
        sb.append("lineItemIds");
        sb.append('=');
        sb.append(((this.lineItemIds == null)?"<null>":this.lineItemIds));
        sb.append(',');
        sb.append("sfCustomerId");
        sb.append('=');
        sb.append(((this.sfCustomerId == null)?"<null>":this.sfCustomerId));
        sb.append(',');
        sb.append("cartIdentifier");
        sb.append('=');
        sb.append(((this.cartIdentifier == null)?"<null>":this.cartIdentifier));
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
        result = ((result* 31)+((this.cartIdentifier == null)? 0 :this.cartIdentifier.hashCode()));
        result = ((result* 31)+((this.sfCustomerId == null)? 0 :this.sfCustomerId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.lineItemIds == null)? 0 :this.lineItemIds.hashCode()));
        result = ((result* 31)+((this.cartId == null)? 0 :this.cartId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CentralCartDeleteLineItemReq) == false) {
            return false;
        }
        CentralCartDeleteLineItemReq rhs = ((CentralCartDeleteLineItemReq) other);
        return ((((((this.cartIdentifier == rhs.cartIdentifier)||((this.cartIdentifier!= null)&&this.cartIdentifier.equals(rhs.cartIdentifier)))&&((this.sfCustomerId == rhs.sfCustomerId)||((this.sfCustomerId!= null)&&this.sfCustomerId.equals(rhs.sfCustomerId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.lineItemIds == rhs.lineItemIds)||((this.lineItemIds!= null)&&this.lineItemIds.equals(rhs.lineItemIds))))&&((this.cartId == rhs.cartId)||((this.cartId!= null)&&this.cartId.equals(rhs.cartId))));
    }

}
