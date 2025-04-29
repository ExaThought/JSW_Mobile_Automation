
package com.qapitol.jsw.apitest.pojos.order.Rcv2Request;

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
    "cart_identifier",
    "customer_details",
    "shipping_details",
    "cart_currency",
    "line_items"
})
@Generated("jsonschema2pojo")
public class Rcv2Req {

    @JsonProperty("cart_identifier")
    private CartIdentifier cartIdentifier;
    @JsonProperty("customer_details")
    private CustomerDetails customerDetails;
    @JsonProperty("shipping_details")
    private ShippingDetails shippingDetails;
    @JsonProperty("cart_currency")
    private String cartCurrency;
    @JsonProperty("line_items")
    private List<LineItem> lineItems = new ArrayList<LineItem>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cart_identifier")
    public CartIdentifier getCartIdentifier() {
        return cartIdentifier;
    }

    @JsonProperty("cart_identifier")
    public void setCartIdentifier(CartIdentifier cartIdentifier) {
        this.cartIdentifier = cartIdentifier;
    }

    @JsonProperty("customer_details")
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    @JsonProperty("customer_details")
    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    @JsonProperty("shipping_details")
    public ShippingDetails getShippingDetails() {
        return shippingDetails;
    }

    @JsonProperty("shipping_details")
    public void setShippingDetails(ShippingDetails shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    @JsonProperty("cart_currency")
    public String getCartCurrency() {
        return cartCurrency;
    }

    @JsonProperty("cart_currency")
    public void setCartCurrency(String cartCurrency) {
        this.cartCurrency = cartCurrency;
    }

    @JsonProperty("line_items")
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    @JsonProperty("line_items")
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
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
        sb.append(Rcv2Req.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cartIdentifier");
        sb.append('=');
        sb.append(((this.cartIdentifier == null)?"<null>":this.cartIdentifier));
        sb.append(',');
        sb.append("customerDetails");
        sb.append('=');
        sb.append(((this.customerDetails == null)?"<null>":this.customerDetails));
        sb.append(',');
        sb.append("shippingDetails");
        sb.append('=');
        sb.append(((this.shippingDetails == null)?"<null>":this.shippingDetails));
        sb.append(',');
        sb.append("cartCurrency");
        sb.append('=');
        sb.append(((this.cartCurrency == null)?"<null>":this.cartCurrency));
        sb.append(',');
        sb.append("lineItems");
        sb.append('=');
        sb.append(((this.lineItems == null)?"<null>":this.lineItems));
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
        result = ((result* 31)+((this.lineItems == null)? 0 :this.lineItems.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.customerDetails == null)? 0 :this.customerDetails.hashCode()));
        result = ((result* 31)+((this.shippingDetails == null)? 0 :this.shippingDetails.hashCode()));
        result = ((result* 31)+((this.cartCurrency == null)? 0 :this.cartCurrency.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rcv2Req) == false) {
            return false;
        }
        Rcv2Req rhs = ((Rcv2Req) other);
        return (((((((this.cartIdentifier == rhs.cartIdentifier)||((this.cartIdentifier!= null)&&this.cartIdentifier.equals(rhs.cartIdentifier)))&&((this.lineItems == rhs.lineItems)||((this.lineItems!= null)&&this.lineItems.equals(rhs.lineItems))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.customerDetails == rhs.customerDetails)||((this.customerDetails!= null)&&this.customerDetails.equals(rhs.customerDetails))))&&((this.shippingDetails == rhs.shippingDetails)||((this.shippingDetails!= null)&&this.shippingDetails.equals(rhs.shippingDetails))))&&((this.cartCurrency == rhs.cartCurrency)||((this.cartCurrency!= null)&&this.cartCurrency.equals(rhs.cartCurrency))));
    }

}
