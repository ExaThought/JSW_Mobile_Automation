
package com.qapitol.jsw.apitest.pojos.order.getLineItemDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "processingCharge",
    "processingChargeWithTax",
    "freightCharge",
    "freightChargeWithTax",
    "loadingCharge",
    "loadingChargeWithTax",
    "otherCharge",
    "otherChargeWithTax"
})
@Generated("jsonschema2pojo")
public class ServiceCostBreakupLineItem {

    @JsonProperty("processingCharge")
    private ProcessingCharge processingCharge;
    @JsonProperty("processingChargeWithTax")
    private ProcessingChargeWithTax processingChargeWithTax;
    @JsonProperty("freightCharge")
    private FreightCharge freightCharge;
    @JsonProperty("freightChargeWithTax")
    private FreightChargeWithTax freightChargeWithTax;
    @JsonProperty("loadingCharge")
    private LoadingCharge loadingCharge;
    @JsonProperty("loadingChargeWithTax")
    private LoadingChargeWithTax loadingChargeWithTax;
    @JsonProperty("otherCharge")
    private Object otherCharge;
    @JsonProperty("otherChargeWithTax")
    private Object otherChargeWithTax;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("processingCharge")
    public ProcessingCharge getProcessingCharge() {
        return processingCharge;
    }

    @JsonProperty("processingCharge")
    public void setProcessingCharge(ProcessingCharge processingCharge) {
        this.processingCharge = processingCharge;
    }

    @JsonProperty("processingChargeWithTax")
    public ProcessingChargeWithTax getProcessingChargeWithTax() {
        return processingChargeWithTax;
    }

    @JsonProperty("processingChargeWithTax")
    public void setProcessingChargeWithTax(ProcessingChargeWithTax processingChargeWithTax) {
        this.processingChargeWithTax = processingChargeWithTax;
    }

    @JsonProperty("freightCharge")
    public FreightCharge getFreightCharge() {
        return freightCharge;
    }

    @JsonProperty("freightCharge")
    public void setFreightCharge(FreightCharge freightCharge) {
        this.freightCharge = freightCharge;
    }

    @JsonProperty("freightChargeWithTax")
    public FreightChargeWithTax getFreightChargeWithTax() {
        return freightChargeWithTax;
    }

    @JsonProperty("freightChargeWithTax")
    public void setFreightChargeWithTax(FreightChargeWithTax freightChargeWithTax) {
        this.freightChargeWithTax = freightChargeWithTax;
    }

    @JsonProperty("loadingCharge")
    public LoadingCharge getLoadingCharge() {
        return loadingCharge;
    }

    @JsonProperty("loadingCharge")
    public void setLoadingCharge(LoadingCharge loadingCharge) {
        this.loadingCharge = loadingCharge;
    }

    @JsonProperty("loadingChargeWithTax")
    public LoadingChargeWithTax getLoadingChargeWithTax() {
        return loadingChargeWithTax;
    }

    @JsonProperty("loadingChargeWithTax")
    public void setLoadingChargeWithTax(LoadingChargeWithTax loadingChargeWithTax) {
        this.loadingChargeWithTax = loadingChargeWithTax;
    }

    @JsonProperty("otherCharge")
    public Object getOtherCharge() {
        return otherCharge;
    }

    @JsonProperty("otherCharge")
    public void setOtherCharge(Object otherCharge) {
        this.otherCharge = otherCharge;
    }

    @JsonProperty("otherChargeWithTax")
    public Object getOtherChargeWithTax() {
        return otherChargeWithTax;
    }

    @JsonProperty("otherChargeWithTax")
    public void setOtherChargeWithTax(Object otherChargeWithTax) {
        this.otherChargeWithTax = otherChargeWithTax;
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
        sb.append(ServiceCostBreakupLineItem.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("processingCharge");
        sb.append('=');
        sb.append(((this.processingCharge == null)?"<null>":this.processingCharge));
        sb.append(',');
        sb.append("processingChargeWithTax");
        sb.append('=');
        sb.append(((this.processingChargeWithTax == null)?"<null>":this.processingChargeWithTax));
        sb.append(',');
        sb.append("freightCharge");
        sb.append('=');
        sb.append(((this.freightCharge == null)?"<null>":this.freightCharge));
        sb.append(',');
        sb.append("freightChargeWithTax");
        sb.append('=');
        sb.append(((this.freightChargeWithTax == null)?"<null>":this.freightChargeWithTax));
        sb.append(',');
        sb.append("loadingCharge");
        sb.append('=');
        sb.append(((this.loadingCharge == null)?"<null>":this.loadingCharge));
        sb.append(',');
        sb.append("loadingChargeWithTax");
        sb.append('=');
        sb.append(((this.loadingChargeWithTax == null)?"<null>":this.loadingChargeWithTax));
        sb.append(',');
        sb.append("otherCharge");
        sb.append('=');
        sb.append(((this.otherCharge == null)?"<null>":this.otherCharge));
        sb.append(',');
        sb.append("otherChargeWithTax");
        sb.append('=');
        sb.append(((this.otherChargeWithTax == null)?"<null>":this.otherChargeWithTax));
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
        result = ((result* 31)+((this.processingChargeWithTax == null)? 0 :this.processingChargeWithTax.hashCode()));
        result = ((result* 31)+((this.otherChargeWithTax == null)? 0 :this.otherChargeWithTax.hashCode()));
        result = ((result* 31)+((this.otherCharge == null)? 0 :this.otherCharge.hashCode()));
        result = ((result* 31)+((this.loadingCharge == null)? 0 :this.loadingCharge.hashCode()));
        result = ((result* 31)+((this.freightChargeWithTax == null)? 0 :this.freightChargeWithTax.hashCode()));
        result = ((result* 31)+((this.processingCharge == null)? 0 :this.processingCharge.hashCode()));
        result = ((result* 31)+((this.loadingChargeWithTax == null)? 0 :this.loadingChargeWithTax.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.freightCharge == null)? 0 :this.freightCharge.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServiceCostBreakupLineItem) == false) {
            return false;
        }
        ServiceCostBreakupLineItem rhs = ((ServiceCostBreakupLineItem) other);
        return ((((((((((this.processingChargeWithTax == rhs.processingChargeWithTax)||((this.processingChargeWithTax!= null)&&this.processingChargeWithTax.equals(rhs.processingChargeWithTax)))&&((this.otherChargeWithTax == rhs.otherChargeWithTax)||((this.otherChargeWithTax!= null)&&this.otherChargeWithTax.equals(rhs.otherChargeWithTax))))&&((this.otherCharge == rhs.otherCharge)||((this.otherCharge!= null)&&this.otherCharge.equals(rhs.otherCharge))))&&((this.loadingCharge == rhs.loadingCharge)||((this.loadingCharge!= null)&&this.loadingCharge.equals(rhs.loadingCharge))))&&((this.freightChargeWithTax == rhs.freightChargeWithTax)||((this.freightChargeWithTax!= null)&&this.freightChargeWithTax.equals(rhs.freightChargeWithTax))))&&((this.processingCharge == rhs.processingCharge)||((this.processingCharge!= null)&&this.processingCharge.equals(rhs.processingCharge))))&&((this.loadingChargeWithTax == rhs.loadingChargeWithTax)||((this.loadingChargeWithTax!= null)&&this.loadingChargeWithTax.equals(rhs.loadingChargeWithTax))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.freightCharge == rhs.freightCharge)||((this.freightCharge!= null)&&this.freightCharge.equals(rhs.freightCharge))));
    }

}
