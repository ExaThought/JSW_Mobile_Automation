
package com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cart_id",
    "version",
    "metadata",
    "cart_identifier",
    "customer_details",
    "shipping_details",
    "salesforce_opportunity_details",
    "order_details",
    "credit_details",
    "currency",
    "line_items",
    "cart_custom_meta_data",
    "documents",
    "cart_price"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("cart_id")
    private String cartId;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("cart_identifier")
    private CartIdentifier cartIdentifier;
    @JsonProperty("customer_details")
    private CustomerDetails customerDetails;
    @JsonProperty("shipping_details")
    private ShippingDetails shippingDetails;
    @JsonProperty("salesforce_opportunity_details")
    private Object salesforceOpportunityDetails;
    @JsonProperty("order_details")
    private Object orderDetails;
    @JsonProperty("credit_details")
    private Object creditDetails;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("line_items")
    private List<LineItem> lineItems = new ArrayList<LineItem>();
    @JsonProperty("cart_custom_meta_data")
    private CartCustomMetaData cartCustomMetaData;
    @JsonProperty("documents")
    private Object documents;
    @JsonProperty("cart_price")
    private CartPrice cartPrice;
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

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

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

    @JsonProperty("salesforce_opportunity_details")
    public Object getSalesforceOpportunityDetails() {
        return salesforceOpportunityDetails;
    }

    @JsonProperty("salesforce_opportunity_details")
    public void setSalesforceOpportunityDetails(Object salesforceOpportunityDetails) {
        this.salesforceOpportunityDetails = salesforceOpportunityDetails;
    }

    @JsonProperty("order_details")
    public Object getOrderDetails() {
        return orderDetails;
    }

    @JsonProperty("order_details")
    public void setOrderDetails(Object orderDetails) {
        this.orderDetails = orderDetails;
    }

    @JsonProperty("credit_details")
    public Object getCreditDetails() {
        return creditDetails;
    }

    @JsonProperty("credit_details")
    public void setCreditDetails(Object creditDetails) {
        this.creditDetails = creditDetails;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("line_items")
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    @JsonProperty("line_items")
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @JsonProperty("cart_custom_meta_data")
    public CartCustomMetaData getCartCustomMetaData() {
        return cartCustomMetaData;
    }

    @JsonProperty("cart_custom_meta_data")
    public void setCartCustomMetaData(CartCustomMetaData cartCustomMetaData) {
        this.cartCustomMetaData = cartCustomMetaData;
    }

    @JsonProperty("documents")
    public Object getDocuments() {
        return documents;
    }

    @JsonProperty("documents")
    public void setDocuments(Object documents) {
        this.documents = documents;
    }

    @JsonProperty("cart_price")
    public CartPrice getCartPrice() {
        return cartPrice;
    }

    @JsonProperty("cart_price")
    public void setCartPrice(CartPrice cartPrice) {
        this.cartPrice = cartPrice;
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
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cartId");
        sb.append('=');
        sb.append(((this.cartId == null)?"<null>":this.cartId));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("metadata");
        sb.append('=');
        sb.append(((this.metadata == null)?"<null>":this.metadata));
        sb.append(',');
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
        sb.append("salesforceOpportunityDetails");
        sb.append('=');
        sb.append(((this.salesforceOpportunityDetails == null)?"<null>":this.salesforceOpportunityDetails));
        sb.append(',');
        sb.append("orderDetails");
        sb.append('=');
        sb.append(((this.orderDetails == null)?"<null>":this.orderDetails));
        sb.append(',');
        sb.append("creditDetails");
        sb.append('=');
        sb.append(((this.creditDetails == null)?"<null>":this.creditDetails));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("lineItems");
        sb.append('=');
        sb.append(((this.lineItems == null)?"<null>":this.lineItems));
        sb.append(',');
        sb.append("cartCustomMetaData");
        sb.append('=');
        sb.append(((this.cartCustomMetaData == null)?"<null>":this.cartCustomMetaData));
        sb.append(',');
        sb.append("documents");
        sb.append('=');
        sb.append(((this.documents == null)?"<null>":this.documents));
        sb.append(',');
        sb.append("cartPrice");
        sb.append('=');
        sb.append(((this.cartPrice == null)?"<null>":this.cartPrice));
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
        result = ((result* 31)+((this.metadata == null)? 0 :this.metadata.hashCode()));
        result = ((result* 31)+((this.documents == null)? 0 :this.documents.hashCode()));
        result = ((result* 31)+((this.cartId == null)? 0 :this.cartId.hashCode()));
        result = ((result* 31)+((this.cartIdentifier == null)? 0 :this.cartIdentifier.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.lineItems == null)? 0 :this.lineItems.hashCode()));
        result = ((result* 31)+((this.orderDetails == null)? 0 :this.orderDetails.hashCode()));
        result = ((result* 31)+((this.salesforceOpportunityDetails == null)? 0 :this.salesforceOpportunityDetails.hashCode()));
        result = ((result* 31)+((this.shippingDetails == null)? 0 :this.shippingDetails.hashCode()));
        result = ((result* 31)+((this.creditDetails == null)? 0 :this.creditDetails.hashCode()));
        result = ((result* 31)+((this.cartPrice == null)? 0 :this.cartPrice.hashCode()));
        result = ((result* 31)+((this.cartCustomMetaData == null)? 0 :this.cartCustomMetaData.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.customerDetails == null)? 0 :this.customerDetails.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return ((((((((((((((((this.metadata == rhs.metadata)||((this.metadata!= null)&&this.metadata.equals(rhs.metadata)))&&((this.documents == rhs.documents)||((this.documents!= null)&&this.documents.equals(rhs.documents))))&&((this.cartId == rhs.cartId)||((this.cartId!= null)&&this.cartId.equals(rhs.cartId))))&&((this.cartIdentifier == rhs.cartIdentifier)||((this.cartIdentifier!= null)&&this.cartIdentifier.equals(rhs.cartIdentifier))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.lineItems == rhs.lineItems)||((this.lineItems!= null)&&this.lineItems.equals(rhs.lineItems))))&&((this.orderDetails == rhs.orderDetails)||((this.orderDetails!= null)&&this.orderDetails.equals(rhs.orderDetails))))&&((this.salesforceOpportunityDetails == rhs.salesforceOpportunityDetails)||((this.salesforceOpportunityDetails!= null)&&this.salesforceOpportunityDetails.equals(rhs.salesforceOpportunityDetails))))&&((this.shippingDetails == rhs.shippingDetails)||((this.shippingDetails!= null)&&this.shippingDetails.equals(rhs.shippingDetails))))&&((this.creditDetails == rhs.creditDetails)||((this.creditDetails!= null)&&this.creditDetails.equals(rhs.creditDetails))))&&((this.cartPrice == rhs.cartPrice)||((this.cartPrice!= null)&&this.cartPrice.equals(rhs.cartPrice))))&&((this.cartCustomMetaData == rhs.cartCustomMetaData)||((this.cartCustomMetaData!= null)&&this.cartCustomMetaData.equals(rhs.cartCustomMetaData))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.customerDetails == rhs.customerDetails)||((this.customerDetails!= null)&&this.customerDetails.equals(rhs.customerDetails))));
    }

}
