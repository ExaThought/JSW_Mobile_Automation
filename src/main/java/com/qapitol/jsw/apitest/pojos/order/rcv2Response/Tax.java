
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "taxAmount",
    "taxType",
    "taxRatio"
})
@Generated("jsonschema2pojo")
public class Tax {

    @JsonProperty("taxAmount")
    private TaxAmount taxAmount;
    @JsonProperty("taxType")
    private String taxType;
    @JsonProperty("taxRatio")
    private Double taxRatio;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("taxAmount")
    public TaxAmount getTaxAmount() {
        return taxAmount;
    }

    @JsonProperty("taxAmount")
    public void setTaxAmount(TaxAmount taxAmount) {
        this.taxAmount = taxAmount;
    }

    @JsonProperty("taxType")
    public String getTaxType() {
        return taxType;
    }

    @JsonProperty("taxType")
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    @JsonProperty("taxRatio")
    public Double getTaxRatio() {
        return taxRatio;
    }

    @JsonProperty("taxRatio")
    public void setTaxRatio(Double taxRatio) {
        this.taxRatio = taxRatio;
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
        sb.append(Tax.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("taxAmount");
        sb.append('=');
        sb.append(((this.taxAmount == null)?"<null>":this.taxAmount));
        sb.append(',');
        sb.append("taxType");
        sb.append('=');
        sb.append(((this.taxType == null)?"<null>":this.taxType));
        sb.append(',');
        sb.append("taxRatio");
        sb.append('=');
        sb.append(((this.taxRatio == null)?"<null>":this.taxRatio));
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
        result = ((result* 31)+((this.taxRatio == null)? 0 :this.taxRatio.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.taxAmount == null)? 0 :this.taxAmount.hashCode()));
        result = ((result* 31)+((this.taxType == null)? 0 :this.taxType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Tax) == false) {
            return false;
        }
        Tax rhs = ((Tax) other);
        return (((((this.taxRatio == rhs.taxRatio)||((this.taxRatio!= null)&&this.taxRatio.equals(rhs.taxRatio)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.taxAmount == rhs.taxAmount)||((this.taxAmount!= null)&&this.taxAmount.equals(rhs.taxAmount))))&&((this.taxType == rhs.taxType)||((this.taxType!= null)&&this.taxType.equals(rhs.taxType))));
    }

}
