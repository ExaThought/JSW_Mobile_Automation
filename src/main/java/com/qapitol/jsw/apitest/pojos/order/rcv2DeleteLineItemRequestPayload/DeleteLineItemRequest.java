
package com.qapitol.jsw.apitest.pojos.order.rcv2DeleteLineItemRequestPayload;

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
    "customerDetails",
    "cartIdentifier",
    "lineItemIdList"
})
@Generated("jsonschema2pojo")
public class DeleteLineItemRequest {

    @JsonProperty("customerDetails")
    private CustomerDetails customerDetails;
    @JsonProperty("cartIdentifier")
    private CartIdentifier cartIdentifier;
    @JsonProperty("lineItemIdList")
    private List<String> lineItemIdList = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

    @JsonProperty("lineItemIdList")
    public List<String> getLineItemIdList() {
        return lineItemIdList;
    }

    @JsonProperty("lineItemIdList")
    public void setLineItemIdList(List<String> lineItemIdList) {
        this.lineItemIdList = lineItemIdList;
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
        sb.append(DeleteLineItemRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerDetails");
        sb.append('=');
        sb.append(((this.customerDetails == null)?"<null>":this.customerDetails));
        sb.append(',');
        sb.append("cartIdentifier");
        sb.append('=');
        sb.append(((this.cartIdentifier == null)?"<null>":this.cartIdentifier));
        sb.append(',');
        sb.append("lineItemIdList");
        sb.append('=');
        sb.append(((this.lineItemIdList == null)?"<null>":this.lineItemIdList));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.customerDetails == null)? 0 :this.customerDetails.hashCode()));
        result = ((result* 31)+((this.lineItemIdList == null)? 0 :this.lineItemIdList.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DeleteLineItemRequest) == false) {
            return false;
        }
        DeleteLineItemRequest rhs = ((DeleteLineItemRequest) other);
        return (((((this.cartIdentifier == rhs.cartIdentifier)||((this.cartIdentifier!= null)&&this.cartIdentifier.equals(rhs.cartIdentifier)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.customerDetails == rhs.customerDetails)||((this.customerDetails!= null)&&this.customerDetails.equals(rhs.customerDetails))))&&((this.lineItemIdList == rhs.lineItemIdList)||((this.lineItemIdList!= null)&&this.lineItemIdList.equals(rhs.lineItemIdList))));
    }

}
