
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalTaxRatio",
    "totalTaxAmount",
    "taxList"
})
@Generated("jsonschema2pojo")
public class PerUnitAmountTax {

    @JsonProperty("totalTaxRatio")
    private Double totalTaxRatio;
    @JsonProperty("totalTaxAmount")
    private TotalTaxAmount__1 totalTaxAmount;
    @JsonProperty("taxList")
    private List<Tax> taxList = new ArrayList<Tax>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("totalTaxRatio")
    public Double getTotalTaxRatio() {
        return totalTaxRatio;
    }

    @JsonProperty("totalTaxRatio")
    public void setTotalTaxRatio(Double totalTaxRatio) {
        this.totalTaxRatio = totalTaxRatio;
    }

    @JsonProperty("totalTaxAmount")
    public TotalTaxAmount__1 getTotalTaxAmount() {
        return totalTaxAmount;
    }

    @JsonProperty("totalTaxAmount")
    public void setTotalTaxAmount(TotalTaxAmount__1 totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    @JsonProperty("taxList")
    public List<Tax> getTaxList() {
        return taxList;
    }

    @JsonProperty("taxList")
    public void setTaxList(List<Tax> taxList) {
        this.taxList = taxList;
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
        sb.append(PerUnitAmountTax.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("totalTaxRatio");
        sb.append('=');
        sb.append(((this.totalTaxRatio == null)?"<null>":this.totalTaxRatio));
        sb.append(',');
        sb.append("totalTaxAmount");
        sb.append('=');
        sb.append(((this.totalTaxAmount == null)?"<null>":this.totalTaxAmount));
        sb.append(',');
        sb.append("taxList");
        sb.append('=');
        sb.append(((this.taxList == null)?"<null>":this.taxList));
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
        result = ((result* 31)+((this.taxList == null)? 0 :this.taxList.hashCode()));
        result = ((result* 31)+((this.totalTaxRatio == null)? 0 :this.totalTaxRatio.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.totalTaxAmount == null)? 0 :this.totalTaxAmount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PerUnitAmountTax) == false) {
            return false;
        }
        PerUnitAmountTax rhs = ((PerUnitAmountTax) other);
        return (((((this.taxList == rhs.taxList)||((this.taxList!= null)&&this.taxList.equals(rhs.taxList)))&&((this.totalTaxRatio == rhs.totalTaxRatio)||((this.totalTaxRatio!= null)&&this.totalTaxRatio.equals(rhs.totalTaxRatio))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.totalTaxAmount == rhs.totalTaxAmount)||((this.totalTaxAmount!= null)&&this.totalTaxAmount.equals(rhs.totalTaxAmount))));
    }

}
