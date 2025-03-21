
package com.qapitol.jsw.apitest.pojos.order.updateShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "measurementId",
    "perUnitDiscountRate",
    "perUnitDiscountRateTax",
    "perUnitFinalRate",
    "perUnitFinalRateTax",
    "perUnitRate",
    "perUnitRateTax"
})
@Generated("jsonschema2pojo")
public class InvoiceInfo {

    @JsonProperty("measurementId")
    private String measurementId;
    @JsonProperty("perUnitDiscountRate")
    private String perUnitDiscountRate;
    @JsonProperty("perUnitDiscountRateTax")
    private Integer perUnitDiscountRateTax;
    @JsonProperty("perUnitFinalRate")
    private Integer perUnitFinalRate;
    @JsonProperty("perUnitFinalRateTax")
    private Integer perUnitFinalRateTax;
    @JsonProperty("perUnitRate")
    private String perUnitRate;
    @JsonProperty("perUnitRateTax")
    private Integer perUnitRateTax;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("measurementId")
    public String getMeasurementId() {
        return measurementId;
    }

    @JsonProperty("measurementId")
    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    @JsonProperty("perUnitDiscountRate")
    public String getPerUnitDiscountRate() {
        return perUnitDiscountRate;
    }

    @JsonProperty("perUnitDiscountRate")
    public void setPerUnitDiscountRate(String perUnitDiscountRate) {
        this.perUnitDiscountRate = perUnitDiscountRate;
    }

    @JsonProperty("perUnitDiscountRateTax")
    public Integer getPerUnitDiscountRateTax() {
        return perUnitDiscountRateTax;
    }

    @JsonProperty("perUnitDiscountRateTax")
    public void setPerUnitDiscountRateTax(Integer perUnitDiscountRateTax) {
        this.perUnitDiscountRateTax = perUnitDiscountRateTax;
    }

    @JsonProperty("perUnitFinalRate")
    public Integer getPerUnitFinalRate() {
        return perUnitFinalRate;
    }

    @JsonProperty("perUnitFinalRate")
    public void setPerUnitFinalRate(Integer perUnitFinalRate) {
        this.perUnitFinalRate = perUnitFinalRate;
    }

    @JsonProperty("perUnitFinalRateTax")
    public Integer getPerUnitFinalRateTax() {
        return perUnitFinalRateTax;
    }

    @JsonProperty("perUnitFinalRateTax")
    public void setPerUnitFinalRateTax(Integer perUnitFinalRateTax) {
        this.perUnitFinalRateTax = perUnitFinalRateTax;
    }

    @JsonProperty("perUnitRate")
    public String getPerUnitRate() {
        return perUnitRate;
    }

    @JsonProperty("perUnitRate")
    public void setPerUnitRate(String perUnitRate) {
        this.perUnitRate = perUnitRate;
    }

    @JsonProperty("perUnitRateTax")
    public Integer getPerUnitRateTax() {
        return perUnitRateTax;
    }

    @JsonProperty("perUnitRateTax")
    public void setPerUnitRateTax(Integer perUnitRateTax) {
        this.perUnitRateTax = perUnitRateTax;
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
        sb.append(InvoiceInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("measurementId");
        sb.append('=');
        sb.append(((this.measurementId == null)?"<null>":this.measurementId));
        sb.append(',');
        sb.append("perUnitDiscountRate");
        sb.append('=');
        sb.append(((this.perUnitDiscountRate == null)?"<null>":this.perUnitDiscountRate));
        sb.append(',');
        sb.append("perUnitDiscountRateTax");
        sb.append('=');
        sb.append(((this.perUnitDiscountRateTax == null)?"<null>":this.perUnitDiscountRateTax));
        sb.append(',');
        sb.append("perUnitFinalRate");
        sb.append('=');
        sb.append(((this.perUnitFinalRate == null)?"<null>":this.perUnitFinalRate));
        sb.append(',');
        sb.append("perUnitFinalRateTax");
        sb.append('=');
        sb.append(((this.perUnitFinalRateTax == null)?"<null>":this.perUnitFinalRateTax));
        sb.append(',');
        sb.append("perUnitRate");
        sb.append('=');
        sb.append(((this.perUnitRate == null)?"<null>":this.perUnitRate));
        sb.append(',');
        sb.append("perUnitRateTax");
        sb.append('=');
        sb.append(((this.perUnitRateTax == null)?"<null>":this.perUnitRateTax));
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
        result = ((result* 31)+((this.perUnitDiscountRate == null)? 0 :this.perUnitDiscountRate.hashCode()));
        result = ((result* 31)+((this.perUnitRate == null)? 0 :this.perUnitRate.hashCode()));
        result = ((result* 31)+((this.perUnitDiscountRateTax == null)? 0 :this.perUnitDiscountRateTax.hashCode()));
        result = ((result* 31)+((this.perUnitFinalRate == null)? 0 :this.perUnitFinalRate.hashCode()));
        result = ((result* 31)+((this.perUnitFinalRateTax == null)? 0 :this.perUnitFinalRateTax.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.measurementId == null)? 0 :this.measurementId.hashCode()));
        result = ((result* 31)+((this.perUnitRateTax == null)? 0 :this.perUnitRateTax.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InvoiceInfo) == false) {
            return false;
        }
        InvoiceInfo rhs = ((InvoiceInfo) other);
        return (((((((((this.perUnitDiscountRate == rhs.perUnitDiscountRate)||((this.perUnitDiscountRate!= null)&&this.perUnitDiscountRate.equals(rhs.perUnitDiscountRate)))&&((this.perUnitRate == rhs.perUnitRate)||((this.perUnitRate!= null)&&this.perUnitRate.equals(rhs.perUnitRate))))&&((this.perUnitDiscountRateTax == rhs.perUnitDiscountRateTax)||((this.perUnitDiscountRateTax!= null)&&this.perUnitDiscountRateTax.equals(rhs.perUnitDiscountRateTax))))&&((this.perUnitFinalRate == rhs.perUnitFinalRate)||((this.perUnitFinalRate!= null)&&this.perUnitFinalRate.equals(rhs.perUnitFinalRate))))&&((this.perUnitFinalRateTax == rhs.perUnitFinalRateTax)||((this.perUnitFinalRateTax!= null)&&this.perUnitFinalRateTax.equals(rhs.perUnitFinalRateTax))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.measurementId == rhs.measurementId)||((this.measurementId!= null)&&this.measurementId.equals(rhs.measurementId))))&&((this.perUnitRateTax == rhs.perUnitRateTax)||((this.perUnitRateTax!= null)&&this.perUnitRateTax.equals(rhs.perUnitRateTax))));
    }

}
