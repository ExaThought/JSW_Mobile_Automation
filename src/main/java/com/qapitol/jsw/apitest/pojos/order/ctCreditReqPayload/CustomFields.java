
package com.qapitol.jsw.apitest.pojos.order.ctCreditReqPayload;

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
    "tdc_requirements",
    "specialDeliveryInstructions",
    "sellerPaymentTerms",
    "estimatedDeliveryTimeline",
    "creditDetails",
    "committedDeliveryTimeline"
})
@Generated("jsonschema2pojo")
public class CustomFields {

    @JsonProperty("tdc_requirements")
    private TdcRequirements tdcRequirements;
    @JsonProperty("specialDeliveryInstructions")
    private SpecialDeliveryInstructions specialDeliveryInstructions;
    @JsonProperty("sellerPaymentTerms")
    private SellerPaymentTerms sellerPaymentTerms;
    @JsonProperty("estimatedDeliveryTimeline")
    private EstimatedDeliveryTimeline estimatedDeliveryTimeline;
    @JsonProperty("creditDetails")
    private CreditDetails creditDetails;
    @JsonProperty("committedDeliveryTimeline")
    private CommittedDeliveryTimeline committedDeliveryTimeline;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("tdc_requirements")
    public TdcRequirements getTdcRequirements() {
        return tdcRequirements;
    }

    @JsonProperty("tdc_requirements")
    public void setTdcRequirements(TdcRequirements tdcRequirements) {
        this.tdcRequirements = tdcRequirements;
    }

    @JsonProperty("specialDeliveryInstructions")
    public SpecialDeliveryInstructions getSpecialDeliveryInstructions() {
        return specialDeliveryInstructions;
    }

    @JsonProperty("specialDeliveryInstructions")
    public void setSpecialDeliveryInstructions(SpecialDeliveryInstructions specialDeliveryInstructions) {
        this.specialDeliveryInstructions = specialDeliveryInstructions;
    }

    @JsonProperty("sellerPaymentTerms")
    public SellerPaymentTerms getSellerPaymentTerms() {
        return sellerPaymentTerms;
    }

    @JsonProperty("sellerPaymentTerms")
    public void setSellerPaymentTerms(SellerPaymentTerms sellerPaymentTerms) {
        this.sellerPaymentTerms = sellerPaymentTerms;
    }

    @JsonProperty("estimatedDeliveryTimeline")
    public EstimatedDeliveryTimeline getEstimatedDeliveryTimeline() {
        return estimatedDeliveryTimeline;
    }

    @JsonProperty("estimatedDeliveryTimeline")
    public void setEstimatedDeliveryTimeline(EstimatedDeliveryTimeline estimatedDeliveryTimeline) {
        this.estimatedDeliveryTimeline = estimatedDeliveryTimeline;
    }

    @JsonProperty("creditDetails")
    public CreditDetails getCreditDetails() {
        return creditDetails;
    }

    @JsonProperty("creditDetails")
    public void setCreditDetails(CreditDetails creditDetails) {
        this.creditDetails = creditDetails;
    }

    @JsonProperty("committedDeliveryTimeline")
    public CommittedDeliveryTimeline getCommittedDeliveryTimeline() {
        return committedDeliveryTimeline;
    }

    @JsonProperty("committedDeliveryTimeline")
    public void setCommittedDeliveryTimeline(CommittedDeliveryTimeline committedDeliveryTimeline) {
        this.committedDeliveryTimeline = committedDeliveryTimeline;
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
        sb.append(CustomFields.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("tdcRequirements");
        sb.append('=');
        sb.append(((this.tdcRequirements == null)?"<null>":this.tdcRequirements));
        sb.append(',');
        sb.append("specialDeliveryInstructions");
        sb.append('=');
        sb.append(((this.specialDeliveryInstructions == null)?"<null>":this.specialDeliveryInstructions));
        sb.append(',');
        sb.append("sellerPaymentTerms");
        sb.append('=');
        sb.append(((this.sellerPaymentTerms == null)?"<null>":this.sellerPaymentTerms));
        sb.append(',');
        sb.append("estimatedDeliveryTimeline");
        sb.append('=');
        sb.append(((this.estimatedDeliveryTimeline == null)?"<null>":this.estimatedDeliveryTimeline));
        sb.append(',');
        sb.append("creditDetails");
        sb.append('=');
        sb.append(((this.creditDetails == null)?"<null>":this.creditDetails));
        sb.append(',');
        sb.append("committedDeliveryTimeline");
        sb.append('=');
        sb.append(((this.committedDeliveryTimeline == null)?"<null>":this.committedDeliveryTimeline));
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
        result = ((result* 31)+((this.committedDeliveryTimeline == null)? 0 :this.committedDeliveryTimeline.hashCode()));
        result = ((result* 31)+((this.creditDetails == null)? 0 :this.creditDetails.hashCode()));
        result = ((result* 31)+((this.tdcRequirements == null)? 0 :this.tdcRequirements.hashCode()));
        result = ((result* 31)+((this.sellerPaymentTerms == null)? 0 :this.sellerPaymentTerms.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.specialDeliveryInstructions == null)? 0 :this.specialDeliveryInstructions.hashCode()));
        result = ((result* 31)+((this.estimatedDeliveryTimeline == null)? 0 :this.estimatedDeliveryTimeline.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CustomFields) == false) {
            return false;
        }
        CustomFields rhs = ((CustomFields) other);
        return ((((((((this.committedDeliveryTimeline == rhs.committedDeliveryTimeline)||((this.committedDeliveryTimeline!= null)&&this.committedDeliveryTimeline.equals(rhs.committedDeliveryTimeline)))&&((this.creditDetails == rhs.creditDetails)||((this.creditDetails!= null)&&this.creditDetails.equals(rhs.creditDetails))))&&((this.tdcRequirements == rhs.tdcRequirements)||((this.tdcRequirements!= null)&&this.tdcRequirements.equals(rhs.tdcRequirements))))&&((this.sellerPaymentTerms == rhs.sellerPaymentTerms)||((this.sellerPaymentTerms!= null)&&this.sellerPaymentTerms.equals(rhs.sellerPaymentTerms))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.specialDeliveryInstructions == rhs.specialDeliveryInstructions)||((this.specialDeliveryInstructions!= null)&&this.specialDeliveryInstructions.equals(rhs.specialDeliveryInstructions))))&&((this.estimatedDeliveryTimeline == rhs.estimatedDeliveryTimeline)||((this.estimatedDeliveryTimeline!= null)&&this.estimatedDeliveryTimeline.equals(rhs.estimatedDeliveryTimeline))));
    }

}
