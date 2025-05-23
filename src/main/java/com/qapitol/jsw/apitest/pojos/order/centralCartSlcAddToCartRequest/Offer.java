
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
    "rate_per_uom",
    "uom",
    "source",
    "coupon_code"
})
@Generated("jsonschema2pojo")
public class Offer {

    @JsonProperty("type")
    private String type;
    @JsonProperty("total_amount")
    private Integer totalAmount;
    @JsonProperty("rate_per_uom")
    private Integer ratePerUom;
    @JsonProperty("uom")
    private String uom;
    @JsonProperty("source")
    private String source;
    @JsonProperty("coupon_code")
    private String couponCode;
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

    @JsonProperty("rate_per_uom")
    public Integer getRatePerUom() {
        return ratePerUom;
    }

    @JsonProperty("rate_per_uom")
    public void setRatePerUom(Integer ratePerUom) {
        this.ratePerUom = ratePerUom;
    }

    @JsonProperty("uom")
    public String getUom() {
        return uom;
    }

    @JsonProperty("uom")
    public void setUom(String uom) {
        this.uom = uom;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("coupon_code")
    public String getCouponCode() {
        return couponCode;
    }

    @JsonProperty("coupon_code")
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
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
        sb.append(Offer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("totalAmount");
        sb.append('=');
        sb.append(((this.totalAmount == null)?"<null>":this.totalAmount));
        sb.append(',');
        sb.append("ratePerUom");
        sb.append('=');
        sb.append(((this.ratePerUom == null)?"<null>":this.ratePerUom));
        sb.append(',');
        sb.append("uom");
        sb.append('=');
        sb.append(((this.uom == null)?"<null>":this.uom));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null)?"<null>":this.source));
        sb.append(',');
        sb.append("couponCode");
        sb.append('=');
        sb.append(((this.couponCode == null)?"<null>":this.couponCode));
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
        result = ((result* 31)+((this.uom == null)? 0 :this.uom.hashCode()));
        result = ((result* 31)+((this.source == null)? 0 :this.source.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.couponCode == null)? 0 :this.couponCode.hashCode()));
        result = ((result* 31)+((this.ratePerUom == null)? 0 :this.ratePerUom.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Offer) == false) {
            return false;
        }
        Offer rhs = ((Offer) other);
        return ((((((((this.totalAmount == rhs.totalAmount)||((this.totalAmount!= null)&&this.totalAmount.equals(rhs.totalAmount)))&&((this.uom == rhs.uom)||((this.uom!= null)&&this.uom.equals(rhs.uom))))&&((this.source == rhs.source)||((this.source!= null)&&this.source.equals(rhs.source))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.couponCode == rhs.couponCode)||((this.couponCode!= null)&&this.couponCode.equals(rhs.couponCode))))&&((this.ratePerUom == rhs.ratePerUom)||((this.ratePerUom!= null)&&this.ratePerUom.equals(rhs.ratePerUom))));
    }

}
