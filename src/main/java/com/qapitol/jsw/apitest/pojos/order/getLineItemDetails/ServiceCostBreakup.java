
package com.qapitol.jsw.apitest.pojos.order.getLineItemDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "processingCharge",
    "freightCharge",
    "loadingCharge",
    "otherCharge",
    "processingChargeTax",
    "freightChargeTax",
    "loadingChargeTax",
    "otherChargeTax",
    "gstFreightCharge",
    "igstFreightCharge",
    "sgstFreightCharge",
    "cgstFreightCharge",
    "gstLoadingCharge",
    "igstLoadingCharge",
    "sgstLoadingCharge",
    "cgstLoadingCharge",
    "gstOtherCharge",
    "igstOtherCharge",
    "sgstOtherCharge",
    "cgstOtherCharge",
    "gstProcessingCharge",
    "igstProcessingCharge",
    "sgstProcessingCharge",
    "cgstProcessingCharge"
})
@Generated("jsonschema2pojo")
public class ServiceCostBreakup {

    @JsonProperty("processingCharge")
    private Integer processingCharge;
    @JsonProperty("freightCharge")
    private Double freightCharge;
    @JsonProperty("loadingCharge")
    private Double loadingCharge;
    @JsonProperty("otherCharge")
    private Object otherCharge;
    @JsonProperty("processingChargeTax")
    private Object processingChargeTax;
    @JsonProperty("freightChargeTax")
    private Object freightChargeTax;
    @JsonProperty("loadingChargeTax")
    private Object loadingChargeTax;
    @JsonProperty("otherChargeTax")
    private Object otherChargeTax;
    @JsonProperty("gstFreightCharge")
    private Object gstFreightCharge;
    @JsonProperty("igstFreightCharge")
    private Object igstFreightCharge;
    @JsonProperty("sgstFreightCharge")
    private Object sgstFreightCharge;
    @JsonProperty("cgstFreightCharge")
    private Object cgstFreightCharge;
    @JsonProperty("gstLoadingCharge")
    private Object gstLoadingCharge;
    @JsonProperty("igstLoadingCharge")
    private Object igstLoadingCharge;
    @JsonProperty("sgstLoadingCharge")
    private Object sgstLoadingCharge;
    @JsonProperty("cgstLoadingCharge")
    private Object cgstLoadingCharge;
    @JsonProperty("gstOtherCharge")
    private Object gstOtherCharge;
    @JsonProperty("igstOtherCharge")
    private Object igstOtherCharge;
    @JsonProperty("sgstOtherCharge")
    private Object sgstOtherCharge;
    @JsonProperty("cgstOtherCharge")
    private Object cgstOtherCharge;
    @JsonProperty("gstProcessingCharge")
    private Object gstProcessingCharge;
    @JsonProperty("igstProcessingCharge")
    private Object igstProcessingCharge;
    @JsonProperty("sgstProcessingCharge")
    private Object sgstProcessingCharge;
    @JsonProperty("cgstProcessingCharge")
    private Object cgstProcessingCharge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("processingCharge")
    public Integer getProcessingCharge() {
        return processingCharge;
    }

    @JsonProperty("processingCharge")
    public void setProcessingCharge(Integer processingCharge) {
        this.processingCharge = processingCharge;
    }

    @JsonProperty("freightCharge")
    public Double getFreightCharge() {
        return freightCharge;
    }

    @JsonProperty("freightCharge")
    public void setFreightCharge(Double freightCharge) {
        this.freightCharge = freightCharge;
    }

    @JsonProperty("loadingCharge")
    public Double getLoadingCharge() {
        return loadingCharge;
    }

    @JsonProperty("loadingCharge")
    public void setLoadingCharge(Double loadingCharge) {
        this.loadingCharge = loadingCharge;
    }

    @JsonProperty("otherCharge")
    public Object getOtherCharge() {
        return otherCharge;
    }

    @JsonProperty("otherCharge")
    public void setOtherCharge(Object otherCharge) {
        this.otherCharge = otherCharge;
    }

    @JsonProperty("processingChargeTax")
    public Object getProcessingChargeTax() {
        return processingChargeTax;
    }

    @JsonProperty("processingChargeTax")
    public void setProcessingChargeTax(Object processingChargeTax) {
        this.processingChargeTax = processingChargeTax;
    }

    @JsonProperty("freightChargeTax")
    public Object getFreightChargeTax() {
        return freightChargeTax;
    }

    @JsonProperty("freightChargeTax")
    public void setFreightChargeTax(Object freightChargeTax) {
        this.freightChargeTax = freightChargeTax;
    }

    @JsonProperty("loadingChargeTax")
    public Object getLoadingChargeTax() {
        return loadingChargeTax;
    }

    @JsonProperty("loadingChargeTax")
    public void setLoadingChargeTax(Object loadingChargeTax) {
        this.loadingChargeTax = loadingChargeTax;
    }

    @JsonProperty("otherChargeTax")
    public Object getOtherChargeTax() {
        return otherChargeTax;
    }

    @JsonProperty("otherChargeTax")
    public void setOtherChargeTax(Object otherChargeTax) {
        this.otherChargeTax = otherChargeTax;
    }

    @JsonProperty("gstFreightCharge")
    public Object getGstFreightCharge() {
        return gstFreightCharge;
    }

    @JsonProperty("gstFreightCharge")
    public void setGstFreightCharge(Object gstFreightCharge) {
        this.gstFreightCharge = gstFreightCharge;
    }

    @JsonProperty("igstFreightCharge")
    public Object getIgstFreightCharge() {
        return igstFreightCharge;
    }

    @JsonProperty("igstFreightCharge")
    public void setIgstFreightCharge(Object igstFreightCharge) {
        this.igstFreightCharge = igstFreightCharge;
    }

    @JsonProperty("sgstFreightCharge")
    public Object getSgstFreightCharge() {
        return sgstFreightCharge;
    }

    @JsonProperty("sgstFreightCharge")
    public void setSgstFreightCharge(Object sgstFreightCharge) {
        this.sgstFreightCharge = sgstFreightCharge;
    }

    @JsonProperty("cgstFreightCharge")
    public Object getCgstFreightCharge() {
        return cgstFreightCharge;
    }

    @JsonProperty("cgstFreightCharge")
    public void setCgstFreightCharge(Object cgstFreightCharge) {
        this.cgstFreightCharge = cgstFreightCharge;
    }

    @JsonProperty("gstLoadingCharge")
    public Object getGstLoadingCharge() {
        return gstLoadingCharge;
    }

    @JsonProperty("gstLoadingCharge")
    public void setGstLoadingCharge(Object gstLoadingCharge) {
        this.gstLoadingCharge = gstLoadingCharge;
    }

    @JsonProperty("igstLoadingCharge")
    public Object getIgstLoadingCharge() {
        return igstLoadingCharge;
    }

    @JsonProperty("igstLoadingCharge")
    public void setIgstLoadingCharge(Object igstLoadingCharge) {
        this.igstLoadingCharge = igstLoadingCharge;
    }

    @JsonProperty("sgstLoadingCharge")
    public Object getSgstLoadingCharge() {
        return sgstLoadingCharge;
    }

    @JsonProperty("sgstLoadingCharge")
    public void setSgstLoadingCharge(Object sgstLoadingCharge) {
        this.sgstLoadingCharge = sgstLoadingCharge;
    }

    @JsonProperty("cgstLoadingCharge")
    public Object getCgstLoadingCharge() {
        return cgstLoadingCharge;
    }

    @JsonProperty("cgstLoadingCharge")
    public void setCgstLoadingCharge(Object cgstLoadingCharge) {
        this.cgstLoadingCharge = cgstLoadingCharge;
    }

    @JsonProperty("gstOtherCharge")
    public Object getGstOtherCharge() {
        return gstOtherCharge;
    }

    @JsonProperty("gstOtherCharge")
    public void setGstOtherCharge(Object gstOtherCharge) {
        this.gstOtherCharge = gstOtherCharge;
    }

    @JsonProperty("igstOtherCharge")
    public Object getIgstOtherCharge() {
        return igstOtherCharge;
    }

    @JsonProperty("igstOtherCharge")
    public void setIgstOtherCharge(Object igstOtherCharge) {
        this.igstOtherCharge = igstOtherCharge;
    }

    @JsonProperty("sgstOtherCharge")
    public Object getSgstOtherCharge() {
        return sgstOtherCharge;
    }

    @JsonProperty("sgstOtherCharge")
    public void setSgstOtherCharge(Object sgstOtherCharge) {
        this.sgstOtherCharge = sgstOtherCharge;
    }

    @JsonProperty("cgstOtherCharge")
    public Object getCgstOtherCharge() {
        return cgstOtherCharge;
    }

    @JsonProperty("cgstOtherCharge")
    public void setCgstOtherCharge(Object cgstOtherCharge) {
        this.cgstOtherCharge = cgstOtherCharge;
    }

    @JsonProperty("gstProcessingCharge")
    public Object getGstProcessingCharge() {
        return gstProcessingCharge;
    }

    @JsonProperty("gstProcessingCharge")
    public void setGstProcessingCharge(Object gstProcessingCharge) {
        this.gstProcessingCharge = gstProcessingCharge;
    }

    @JsonProperty("igstProcessingCharge")
    public Object getIgstProcessingCharge() {
        return igstProcessingCharge;
    }

    @JsonProperty("igstProcessingCharge")
    public void setIgstProcessingCharge(Object igstProcessingCharge) {
        this.igstProcessingCharge = igstProcessingCharge;
    }

    @JsonProperty("sgstProcessingCharge")
    public Object getSgstProcessingCharge() {
        return sgstProcessingCharge;
    }

    @JsonProperty("sgstProcessingCharge")
    public void setSgstProcessingCharge(Object sgstProcessingCharge) {
        this.sgstProcessingCharge = sgstProcessingCharge;
    }

    @JsonProperty("cgstProcessingCharge")
    public Object getCgstProcessingCharge() {
        return cgstProcessingCharge;
    }

    @JsonProperty("cgstProcessingCharge")
    public void setCgstProcessingCharge(Object cgstProcessingCharge) {
        this.cgstProcessingCharge = cgstProcessingCharge;
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
        sb.append(ServiceCostBreakup.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("processingCharge");
        sb.append('=');
        sb.append(((this.processingCharge == null)?"<null>":this.processingCharge));
        sb.append(',');
        sb.append("freightCharge");
        sb.append('=');
        sb.append(((this.freightCharge == null)?"<null>":this.freightCharge));
        sb.append(',');
        sb.append("loadingCharge");
        sb.append('=');
        sb.append(((this.loadingCharge == null)?"<null>":this.loadingCharge));
        sb.append(',');
        sb.append("otherCharge");
        sb.append('=');
        sb.append(((this.otherCharge == null)?"<null>":this.otherCharge));
        sb.append(',');
        sb.append("processingChargeTax");
        sb.append('=');
        sb.append(((this.processingChargeTax == null)?"<null>":this.processingChargeTax));
        sb.append(',');
        sb.append("freightChargeTax");
        sb.append('=');
        sb.append(((this.freightChargeTax == null)?"<null>":this.freightChargeTax));
        sb.append(',');
        sb.append("loadingChargeTax");
        sb.append('=');
        sb.append(((this.loadingChargeTax == null)?"<null>":this.loadingChargeTax));
        sb.append(',');
        sb.append("otherChargeTax");
        sb.append('=');
        sb.append(((this.otherChargeTax == null)?"<null>":this.otherChargeTax));
        sb.append(',');
        sb.append("gstFreightCharge");
        sb.append('=');
        sb.append(((this.gstFreightCharge == null)?"<null>":this.gstFreightCharge));
        sb.append(',');
        sb.append("igstFreightCharge");
        sb.append('=');
        sb.append(((this.igstFreightCharge == null)?"<null>":this.igstFreightCharge));
        sb.append(',');
        sb.append("sgstFreightCharge");
        sb.append('=');
        sb.append(((this.sgstFreightCharge == null)?"<null>":this.sgstFreightCharge));
        sb.append(',');
        sb.append("cgstFreightCharge");
        sb.append('=');
        sb.append(((this.cgstFreightCharge == null)?"<null>":this.cgstFreightCharge));
        sb.append(',');
        sb.append("gstLoadingCharge");
        sb.append('=');
        sb.append(((this.gstLoadingCharge == null)?"<null>":this.gstLoadingCharge));
        sb.append(',');
        sb.append("igstLoadingCharge");
        sb.append('=');
        sb.append(((this.igstLoadingCharge == null)?"<null>":this.igstLoadingCharge));
        sb.append(',');
        sb.append("sgstLoadingCharge");
        sb.append('=');
        sb.append(((this.sgstLoadingCharge == null)?"<null>":this.sgstLoadingCharge));
        sb.append(',');
        sb.append("cgstLoadingCharge");
        sb.append('=');
        sb.append(((this.cgstLoadingCharge == null)?"<null>":this.cgstLoadingCharge));
        sb.append(',');
        sb.append("gstOtherCharge");
        sb.append('=');
        sb.append(((this.gstOtherCharge == null)?"<null>":this.gstOtherCharge));
        sb.append(',');
        sb.append("igstOtherCharge");
        sb.append('=');
        sb.append(((this.igstOtherCharge == null)?"<null>":this.igstOtherCharge));
        sb.append(',');
        sb.append("sgstOtherCharge");
        sb.append('=');
        sb.append(((this.sgstOtherCharge == null)?"<null>":this.sgstOtherCharge));
        sb.append(',');
        sb.append("cgstOtherCharge");
        sb.append('=');
        sb.append(((this.cgstOtherCharge == null)?"<null>":this.cgstOtherCharge));
        sb.append(',');
        sb.append("gstProcessingCharge");
        sb.append('=');
        sb.append(((this.gstProcessingCharge == null)?"<null>":this.gstProcessingCharge));
        sb.append(',');
        sb.append("igstProcessingCharge");
        sb.append('=');
        sb.append(((this.igstProcessingCharge == null)?"<null>":this.igstProcessingCharge));
        sb.append(',');
        sb.append("sgstProcessingCharge");
        sb.append('=');
        sb.append(((this.sgstProcessingCharge == null)?"<null>":this.sgstProcessingCharge));
        sb.append(',');
        sb.append("cgstProcessingCharge");
        sb.append('=');
        sb.append(((this.cgstProcessingCharge == null)?"<null>":this.cgstProcessingCharge));
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
        result = ((result* 31)+((this.otherChargeTax == null)? 0 :this.otherChargeTax.hashCode()));
        result = ((result* 31)+((this.loadingCharge == null)? 0 :this.loadingCharge.hashCode()));
        result = ((result* 31)+((this.sgstOtherCharge == null)? 0 :this.sgstOtherCharge.hashCode()));
        result = ((result* 31)+((this.sgstFreightCharge == null)? 0 :this.sgstFreightCharge.hashCode()));
        result = ((result* 31)+((this.cgstProcessingCharge == null)? 0 :this.cgstProcessingCharge.hashCode()));
        result = ((result* 31)+((this.processingChargeTax == null)? 0 :this.processingChargeTax.hashCode()));
        result = ((result* 31)+((this.igstProcessingCharge == null)? 0 :this.igstProcessingCharge.hashCode()));
        result = ((result* 31)+((this.gstLoadingCharge == null)? 0 :this.gstLoadingCharge.hashCode()));
        result = ((result* 31)+((this.cgstFreightCharge == null)? 0 :this.cgstFreightCharge.hashCode()));
        result = ((result* 31)+((this.igstFreightCharge == null)? 0 :this.igstFreightCharge.hashCode()));
        result = ((result* 31)+((this.sgstLoadingCharge == null)? 0 :this.sgstLoadingCharge.hashCode()));
        result = ((result* 31)+((this.sgstProcessingCharge == null)? 0 :this.sgstProcessingCharge.hashCode()));
        result = ((result* 31)+((this.freightChargeTax == null)? 0 :this.freightChargeTax.hashCode()));
        result = ((result* 31)+((this.gstOtherCharge == null)? 0 :this.gstOtherCharge.hashCode()));
        result = ((result* 31)+((this.freightCharge == null)? 0 :this.freightCharge.hashCode()));
        result = ((result* 31)+((this.cgstLoadingCharge == null)? 0 :this.cgstLoadingCharge.hashCode()));
        result = ((result* 31)+((this.loadingChargeTax == null)? 0 :this.loadingChargeTax.hashCode()));
        result = ((result* 31)+((this.cgstOtherCharge == null)? 0 :this.cgstOtherCharge.hashCode()));
        result = ((result* 31)+((this.igstLoadingCharge == null)? 0 :this.igstLoadingCharge.hashCode()));
        result = ((result* 31)+((this.otherCharge == null)? 0 :this.otherCharge.hashCode()));
        result = ((result* 31)+((this.igstOtherCharge == null)? 0 :this.igstOtherCharge.hashCode()));
        result = ((result* 31)+((this.gstFreightCharge == null)? 0 :this.gstFreightCharge.hashCode()));
        result = ((result* 31)+((this.processingCharge == null)? 0 :this.processingCharge.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.gstProcessingCharge == null)? 0 :this.gstProcessingCharge.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServiceCostBreakup) == false) {
            return false;
        }
        ServiceCostBreakup rhs = ((ServiceCostBreakup) other);
        return ((((((((((((((((((((((((((this.otherChargeTax == rhs.otherChargeTax)||((this.otherChargeTax!= null)&&this.otherChargeTax.equals(rhs.otherChargeTax)))&&((this.loadingCharge == rhs.loadingCharge)||((this.loadingCharge!= null)&&this.loadingCharge.equals(rhs.loadingCharge))))&&((this.sgstOtherCharge == rhs.sgstOtherCharge)||((this.sgstOtherCharge!= null)&&this.sgstOtherCharge.equals(rhs.sgstOtherCharge))))&&((this.sgstFreightCharge == rhs.sgstFreightCharge)||((this.sgstFreightCharge!= null)&&this.sgstFreightCharge.equals(rhs.sgstFreightCharge))))&&((this.cgstProcessingCharge == rhs.cgstProcessingCharge)||((this.cgstProcessingCharge!= null)&&this.cgstProcessingCharge.equals(rhs.cgstProcessingCharge))))&&((this.processingChargeTax == rhs.processingChargeTax)||((this.processingChargeTax!= null)&&this.processingChargeTax.equals(rhs.processingChargeTax))))&&((this.igstProcessingCharge == rhs.igstProcessingCharge)||((this.igstProcessingCharge!= null)&&this.igstProcessingCharge.equals(rhs.igstProcessingCharge))))&&((this.gstLoadingCharge == rhs.gstLoadingCharge)||((this.gstLoadingCharge!= null)&&this.gstLoadingCharge.equals(rhs.gstLoadingCharge))))&&((this.cgstFreightCharge == rhs.cgstFreightCharge)||((this.cgstFreightCharge!= null)&&this.cgstFreightCharge.equals(rhs.cgstFreightCharge))))&&((this.igstFreightCharge == rhs.igstFreightCharge)||((this.igstFreightCharge!= null)&&this.igstFreightCharge.equals(rhs.igstFreightCharge))))&&((this.sgstLoadingCharge == rhs.sgstLoadingCharge)||((this.sgstLoadingCharge!= null)&&this.sgstLoadingCharge.equals(rhs.sgstLoadingCharge))))&&((this.sgstProcessingCharge == rhs.sgstProcessingCharge)||((this.sgstProcessingCharge!= null)&&this.sgstProcessingCharge.equals(rhs.sgstProcessingCharge))))&&((this.freightChargeTax == rhs.freightChargeTax)||((this.freightChargeTax!= null)&&this.freightChargeTax.equals(rhs.freightChargeTax))))&&((this.gstOtherCharge == rhs.gstOtherCharge)||((this.gstOtherCharge!= null)&&this.gstOtherCharge.equals(rhs.gstOtherCharge))))&&((this.freightCharge == rhs.freightCharge)||((this.freightCharge!= null)&&this.freightCharge.equals(rhs.freightCharge))))&&((this.cgstLoadingCharge == rhs.cgstLoadingCharge)||((this.cgstLoadingCharge!= null)&&this.cgstLoadingCharge.equals(rhs.cgstLoadingCharge))))&&((this.loadingChargeTax == rhs.loadingChargeTax)||((this.loadingChargeTax!= null)&&this.loadingChargeTax.equals(rhs.loadingChargeTax))))&&((this.cgstOtherCharge == rhs.cgstOtherCharge)||((this.cgstOtherCharge!= null)&&this.cgstOtherCharge.equals(rhs.cgstOtherCharge))))&&((this.igstLoadingCharge == rhs.igstLoadingCharge)||((this.igstLoadingCharge!= null)&&this.igstLoadingCharge.equals(rhs.igstLoadingCharge))))&&((this.otherCharge == rhs.otherCharge)||((this.otherCharge!= null)&&this.otherCharge.equals(rhs.otherCharge))))&&((this.igstOtherCharge == rhs.igstOtherCharge)||((this.igstOtherCharge!= null)&&this.igstOtherCharge.equals(rhs.igstOtherCharge))))&&((this.gstFreightCharge == rhs.gstFreightCharge)||((this.gstFreightCharge!= null)&&this.gstFreightCharge.equals(rhs.gstFreightCharge))))&&((this.processingCharge == rhs.processingCharge)||((this.processingCharge!= null)&&this.processingCharge.equals(rhs.processingCharge))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.gstProcessingCharge == rhs.gstProcessingCharge)||((this.gstProcessingCharge!= null)&&this.gstProcessingCharge.equals(rhs.gstProcessingCharge))));
    }

}
