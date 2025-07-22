
package com.qapitol.jsw.apitest.pojos.order.rcv2UpdateDeliveryAddress;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customerDetails",
    "cartIdentifier",
    "addressId"
})
@Generated("jsonschema2pojo")
public class Rcv2UpdateDeliveryAddress {

    @JsonProperty("customerDetails")
    private CustomerDetails customerDetails;
    @JsonProperty("cartIdentifier")
    private CartIdentifier cartIdentifier;
    @JsonProperty("addressId")
    private String addressId;
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

    @JsonProperty("addressId")
    public String getAddressId() {
        return addressId;
    }

    @JsonProperty("addressId")
    public void setAddressId(String addressId) {
        this.addressId = addressId;
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
        sb.append(Rcv2UpdateDeliveryAddress.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerDetails");
        sb.append('=');
        sb.append(((this.customerDetails == null)?"<null>":this.customerDetails));
        sb.append(',');
        sb.append("cartIdentifier");
        sb.append('=');
        sb.append(((this.cartIdentifier == null)?"<null>":this.cartIdentifier));
        sb.append(',');
        sb.append("addressId");
        sb.append('=');
        sb.append(((this.addressId == null)?"<null>":this.addressId));
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
        result = ((result* 31)+((this.addressId == null)? 0 :this.addressId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rcv2UpdateDeliveryAddress) == false) {
            return false;
        }
        Rcv2UpdateDeliveryAddress rhs = ((Rcv2UpdateDeliveryAddress) other);
        return (((((this.cartIdentifier == rhs.cartIdentifier)||((this.cartIdentifier!= null)&&this.cartIdentifier.equals(rhs.cartIdentifier)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.customerDetails == rhs.customerDetails)||((this.customerDetails!= null)&&this.customerDetails.equals(rhs.customerDetails))))&&((this.addressId == rhs.addressId)||((this.addressId!= null)&&this.addressId.equals(rhs.addressId))));
    }

}
