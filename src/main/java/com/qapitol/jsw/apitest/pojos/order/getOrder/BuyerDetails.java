
package com.qapitol.jsw.apitest.pojos.order.getOrder;

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
    "name",
    "email",
    "shippingAddress",
    "billingAddress",
    "gstin",
    "billTo",
    "shipTo",
    "retailerName"
})
@Generated("jsonschema2pojo")
public class BuyerDetails {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("shippingAddress")
    private ShippingAddress shippingAddress;
    @JsonProperty("billingAddress")
    private BillingAddress billingAddress;
    @JsonProperty("gstin")
    private String gstin;
    @JsonProperty("billTo")
    private String billTo;
    @JsonProperty("shipTo")
    private String shipTo;
    @JsonProperty("retailerName")
    private String retailerName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("shippingAddress")
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    @JsonProperty("shippingAddress")
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @JsonProperty("billingAddress")
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    @JsonProperty("billingAddress")
    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    @JsonProperty("gstin")
    public String getGstin() {
        return gstin;
    }

    @JsonProperty("gstin")
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @JsonProperty("billTo")
    public String getBillTo() {
        return billTo;
    }

    @JsonProperty("billTo")
    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    @JsonProperty("shipTo")
    public String getShipTo() {
        return shipTo;
    }

    @JsonProperty("shipTo")
    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    @JsonProperty("retailerName")
    public String getRetailerName() {
        return retailerName;
    }

    @JsonProperty("retailerName")
    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
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
        sb.append(BuyerDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("shippingAddress");
        sb.append('=');
        sb.append(((this.shippingAddress == null)?"<null>":this.shippingAddress));
        sb.append(',');
        sb.append("billingAddress");
        sb.append('=');
        sb.append(((this.billingAddress == null)?"<null>":this.billingAddress));
        sb.append(',');
        sb.append("gstin");
        sb.append('=');
        sb.append(((this.gstin == null)?"<null>":this.gstin));
        sb.append(',');
        sb.append("billTo");
        sb.append('=');
        sb.append(((this.billTo == null)?"<null>":this.billTo));
        sb.append(',');
        sb.append("shipTo");
        sb.append('=');
        sb.append(((this.shipTo == null)?"<null>":this.shipTo));
        sb.append(',');
        sb.append("retailerName");
        sb.append('=');
        sb.append(((this.retailerName == null)?"<null>":this.retailerName));
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
        result = ((result* 31)+((this.billTo == null)? 0 :this.billTo.hashCode()));
        result = ((result* 31)+((this.retailerName == null)? 0 :this.retailerName.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.shippingAddress == null)? 0 :this.shippingAddress.hashCode()));
        result = ((result* 31)+((this.billingAddress == null)? 0 :this.billingAddress.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.gstin == null)? 0 :this.gstin.hashCode()));
        result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
        result = ((result* 31)+((this.shipTo == null)? 0 :this.shipTo.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BuyerDetails) == false) {
            return false;
        }
        BuyerDetails rhs = ((BuyerDetails) other);
        return ((((((((((this.billTo == rhs.billTo)||((this.billTo!= null)&&this.billTo.equals(rhs.billTo)))&&((this.retailerName == rhs.retailerName)||((this.retailerName!= null)&&this.retailerName.equals(rhs.retailerName))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.shippingAddress == rhs.shippingAddress)||((this.shippingAddress!= null)&&this.shippingAddress.equals(rhs.shippingAddress))))&&((this.billingAddress == rhs.billingAddress)||((this.billingAddress!= null)&&this.billingAddress.equals(rhs.billingAddress))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.gstin == rhs.gstin)||((this.gstin!= null)&&this.gstin.equals(rhs.gstin))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))))&&((this.shipTo == rhs.shipTo)||((this.shipTo!= null)&&this.shipTo.equals(rhs.shipTo))));
    }

}
