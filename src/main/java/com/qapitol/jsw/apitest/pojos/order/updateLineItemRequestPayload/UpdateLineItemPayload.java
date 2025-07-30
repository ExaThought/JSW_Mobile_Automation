
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
    "lineItemId",
    "customerDetails",
    "cartIdentifier",
    "lineItem"
})
@Generated("jsonschema2pojo")
public class UpdateLineItemPayload {

    @JsonProperty("lineItemId")
    private String lineItemId;
    @JsonProperty("customerDetails")
    private CustomerDetails customerDetails;
    @JsonProperty("cartIdentifier")
    private CartIdentifier cartIdentifier;
    @JsonProperty("lineItem")
    private LineItem lineItem;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("lineItemId")
    public String getLineItemId() {
        return lineItemId;
    }

    @JsonProperty("lineItemId")
    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    @JsonProperty("customerDetails")
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    @JsonProperty("customerDetails")
    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    @JsonProperty("cartIdentifier")
    public CartIdentifier getCartIdentifier() {
        return cartIdentifier;
    }

    @JsonProperty("cartIdentifier")
    public void setCartIdentifier(CartIdentifier cartIdentifier) {
        this.cartIdentifier = cartIdentifier;
    }

    @JsonProperty("lineItem")
    public LineItem getLineItem() {
        return lineItem;
    }

    @JsonProperty("lineItem")
    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
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
        sb.append(UpdateLineItemPayload.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lineItemId");
        sb.append('=');
        sb.append(((this.lineItemId == null)?"<null>":this.lineItemId));
        sb.append(',');
        sb.append("customerDetails");
        sb.append('=');
        sb.append(((this.customerDetails == null)?"<null>":this.customerDetails));
        sb.append(',');
        sb.append("cartIdentifier");
        sb.append('=');
        sb.append(((this.cartIdentifier == null)?"<null>":this.cartIdentifier));
        sb.append(',');
        sb.append("lineItem");
        sb.append('=');
        sb.append(((this.lineItem == null)?"<null>":this.lineItem));
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
        result = ((result* 31)+((this.lineItem == null)? 0 :this.lineItem.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.customerDetails == null)? 0 :this.customerDetails.hashCode()));
        result = ((result* 31)+((this.lineItemId == null)? 0 :this.lineItemId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UpdateLineItemPayload) == false) {
            return false;
        }
        UpdateLineItemPayload rhs = ((UpdateLineItemPayload) other);
        return ((((((this.cartIdentifier == rhs.cartIdentifier)||((this.cartIdentifier!= null)&&this.cartIdentifier.equals(rhs.cartIdentifier)))&&((this.lineItem == rhs.lineItem)||((this.lineItem!= null)&&this.lineItem.equals(rhs.lineItem))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.customerDetails == rhs.customerDetails)||((this.customerDetails!= null)&&this.customerDetails.equals(rhs.customerDetails))))&&((this.lineItemId == rhs.lineItemId)||((this.lineItemId!= null)&&this.lineItemId.equals(rhs.lineItemId))));
    }

}
