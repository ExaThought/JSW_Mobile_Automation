
package com.qapitol.jsw.apitest.pojos.order.Rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "name",
    "total_amount",
    "is_gst_applied",
    "total_tax_amount",
    "vendor_location_key",
    "pricing_mode"
})
@Generated("jsonschema2pojo")
public class ServiceCharge {

    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("total_amount")
    private Double totalAmount;
    @JsonProperty("is_gst_applied")
    private Boolean isGstApplied;
    @JsonProperty("total_tax_amount")
    private Double totalTaxAmount;
    @JsonProperty("vendor_location_key")
    private Object vendorLocationKey;
    @JsonProperty("pricing_mode")
    private Object pricingMode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("total_amount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("total_amount")
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonProperty("is_gst_applied")
    public Boolean getIsGstApplied() {
        return isGstApplied;
    }

    @JsonProperty("is_gst_applied")
    public void setIsGstApplied(Boolean isGstApplied) {
        this.isGstApplied = isGstApplied;
    }

    @JsonProperty("total_tax_amount")
    public Double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    @JsonProperty("total_tax_amount")
    public void setTotalTaxAmount(Double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    @JsonProperty("vendor_location_key")
    public Object getVendorLocationKey() {
        return vendorLocationKey;
    }

    @JsonProperty("vendor_location_key")
    public void setVendorLocationKey(Object vendorLocationKey) {
        this.vendorLocationKey = vendorLocationKey;
    }

    @JsonProperty("pricing_mode")
    public Object getPricingMode() {
        return pricingMode;
    }

    @JsonProperty("pricing_mode")
    public void setPricingMode(Object pricingMode) {
        this.pricingMode = pricingMode;
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
        sb.append(ServiceCharge.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("totalAmount");
        sb.append('=');
        sb.append(((this.totalAmount == null)?"<null>":this.totalAmount));
        sb.append(',');
        sb.append("isGstApplied");
        sb.append('=');
        sb.append(((this.isGstApplied == null)?"<null>":this.isGstApplied));
        sb.append(',');
        sb.append("totalTaxAmount");
        sb.append('=');
        sb.append(((this.totalTaxAmount == null)?"<null>":this.totalTaxAmount));
        sb.append(',');
        sb.append("vendorLocationKey");
        sb.append('=');
        sb.append(((this.vendorLocationKey == null)?"<null>":this.vendorLocationKey));
        sb.append(',');
        sb.append("pricingMode");
        sb.append('=');
        sb.append(((this.pricingMode == null)?"<null>":this.pricingMode));
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
        result = ((result* 31)+((this.totalAmount == null)? 0 :this.totalAmount.hashCode()));
        result = ((result* 31)+((this.isGstApplied == null)? 0 :this.isGstApplied.hashCode()));
        result = ((result* 31)+((this.vendorLocationKey == null)? 0 :this.vendorLocationKey.hashCode()));
        result = ((result* 31)+((this.pricingMode == null)? 0 :this.pricingMode.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.totalTaxAmount == null)? 0 :this.totalTaxAmount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServiceCharge) == false) {
            return false;
        }
        ServiceCharge rhs = ((ServiceCharge) other);
        return (((((((((this.totalAmount == rhs.totalAmount)||((this.totalAmount!= null)&&this.totalAmount.equals(rhs.totalAmount)))&&((this.isGstApplied == rhs.isGstApplied)||((this.isGstApplied!= null)&&this.isGstApplied.equals(rhs.isGstApplied))))&&((this.vendorLocationKey == rhs.vendorLocationKey)||((this.vendorLocationKey!= null)&&this.vendorLocationKey.equals(rhs.vendorLocationKey))))&&((this.pricingMode == rhs.pricingMode)||((this.pricingMode!= null)&&this.pricingMode.equals(rhs.pricingMode))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.totalTaxAmount == rhs.totalTaxAmount)||((this.totalTaxAmount!= null)&&this.totalTaxAmount.equals(rhs.totalTaxAmount))));
    }

}
