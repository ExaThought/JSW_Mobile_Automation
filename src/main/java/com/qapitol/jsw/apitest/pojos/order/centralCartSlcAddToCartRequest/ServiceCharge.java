
package com.qapitol.jsw.apitest.pojos.order.centralCartSlcAddToCartRequest;

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
    "type",
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
    @JsonProperty("total_amount")
    private Integer totalAmount;
    @JsonProperty("is_gst_applied")
    private Boolean isGstApplied;
    @JsonProperty("total_tax_amount")
    private Integer totalTaxAmount;
    @JsonProperty("vendor_location_key")
    private String vendorLocationKey;
    @JsonProperty("pricing_mode")
    private String pricingMode;
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

    @JsonProperty("total_amount")
    public Integer getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("total_amount")
    public void setTotalAmount(Integer totalAmount) {
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
    public Integer getTotalTaxAmount() {
        return totalTaxAmount;
    }

    @JsonProperty("total_tax_amount")
    public void setTotalTaxAmount(Integer totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    @JsonProperty("vendor_location_key")
    public String getVendorLocationKey() {
        return vendorLocationKey;
    }

    @JsonProperty("vendor_location_key")
    public void setVendorLocationKey(String vendorLocationKey) {
        this.vendorLocationKey = vendorLocationKey;
    }

    @JsonProperty("pricing_mode")
    public String getPricingMode() {
        return pricingMode;
    }

    @JsonProperty("pricing_mode")
    public void setPricingMode(String pricingMode) {
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
        return ((((((((this.totalAmount == rhs.totalAmount)||((this.totalAmount!= null)&&this.totalAmount.equals(rhs.totalAmount)))&&((this.isGstApplied == rhs.isGstApplied)||((this.isGstApplied!= null)&&this.isGstApplied.equals(rhs.isGstApplied))))&&((this.vendorLocationKey == rhs.vendorLocationKey)||((this.vendorLocationKey!= null)&&this.vendorLocationKey.equals(rhs.vendorLocationKey))))&&((this.pricingMode == rhs.pricingMode)||((this.pricingMode!= null)&&this.pricingMode.equals(rhs.pricingMode))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.totalTaxAmount == rhs.totalTaxAmount)||((this.totalTaxAmount!= null)&&this.totalTaxAmount.equals(rhs.totalTaxAmount))));
    }

}
