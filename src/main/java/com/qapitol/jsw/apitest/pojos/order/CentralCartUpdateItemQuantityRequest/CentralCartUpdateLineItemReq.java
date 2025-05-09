
package com.qapitol.jsw.apitest.pojos.order.CentralCartUpdateItemQuantityRequest;

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
    "sf_customer_id",
    "cart_id",
    "cart_identifier",
    "line_item_id",
    "quantity"
})
@Generated("jsonschema2pojo")
public class CentralCartUpdateLineItemReq {

    @JsonProperty("sf_customer_id")
    private String sfCustomerId;
    @JsonProperty("cart_id")
    private String cartId;
    @JsonProperty("cart_identifier")
    private CartIdentifier cartIdentifier;
    @JsonProperty("line_item_id")
    private String lineItemId;
    @JsonProperty("quantity")
    private Double quantity;
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

    @JsonProperty("cart_id")
    public String getCartId() {
        return cartId;
    }

    @JsonProperty("cart_id")
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @JsonProperty("cart_identifier")
    public CartIdentifier getCartIdentifier() {
        return cartIdentifier;
    }

    @JsonProperty("cart_identifier")
    public void setCartIdentifier(CartIdentifier cartIdentifier) {
        this.cartIdentifier = cartIdentifier;
    }

    @JsonProperty("line_item_id")
    public String getLineItemId() {
        return lineItemId;
    }

    @JsonProperty("line_item_id")
    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    @JsonProperty("quantity")
    public Double getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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
        sb.append(CentralCartUpdateLineItemReq.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sfCustomerId");
        sb.append('=');
        sb.append(((this.sfCustomerId == null)?"<null>":this.sfCustomerId));
        sb.append(',');
        sb.append("cartId");
        sb.append('=');
        sb.append(((this.cartId == null)?"<null>":this.cartId));
        sb.append(',');
        sb.append("cartIdentifier");
        sb.append('=');
        sb.append(((this.cartIdentifier == null)?"<null>":this.cartIdentifier));
        sb.append(',');
        sb.append("lineItemId");
        sb.append('=');
        sb.append(((this.lineItemId == null)?"<null>":this.lineItemId));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null)?"<null>":this.quantity));
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
        result = ((result* 31)+((this.quantity == null)? 0 :this.quantity.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.lineItemId == null)? 0 :this.lineItemId.hashCode()));
        result = ((result* 31)+((this.cartId == null)? 0 :this.cartId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CentralCartUpdateLineItemReq) == false) {
            return false;
        }
        CentralCartUpdateLineItemReq rhs = ((CentralCartUpdateLineItemReq) other);
        return (((((((this.cartIdentifier == rhs.cartIdentifier)||((this.cartIdentifier!= null)&&this.cartIdentifier.equals(rhs.cartIdentifier)))&&((this.sfCustomerId == rhs.sfCustomerId)||((this.sfCustomerId!= null)&&this.sfCustomerId.equals(rhs.sfCustomerId))))&&((this.quantity == rhs.quantity)||((this.quantity!= null)&&this.quantity.equals(rhs.quantity))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.lineItemId == rhs.lineItemId)||((this.lineItemId!= null)&&this.lineItemId.equals(rhs.lineItemId))))&&((this.cartId == rhs.cartId)||((this.cartId!= null)&&this.cartId.equals(rhs.cartId))));
    }

}
