
package com.qapitol.jsw.apitest.pojos.order.ctSlcSloRequestPayload;

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
    "penalInterest",
    "isCreditRequired",
    "CreditUtilisation",
    "creditType",
    "creditLimitReferenceId",
    "creditInstrument",
    "creditExpirationDate",
    "creditDays",
    "creditAvailable",
    "creditAmount"
})
@Generated("jsonschema2pojo")
public class CreditDetails {

    @JsonProperty("penalInterest")
    private Double penalInterest;
    @JsonProperty("isCreditRequired")
    private String isCreditRequired;
    @JsonProperty("CreditUtilisation")
    private Double creditUtilisation;
    @JsonProperty("creditType")
    private String creditType;
    @JsonProperty("creditLimitReferenceId")
    private String creditLimitReferenceId;
    @JsonProperty("creditInstrument")
    private String creditInstrument;
    @JsonProperty("creditExpirationDate")
    private String creditExpirationDate;
    @JsonProperty("creditDays")
    private String creditDays;
    @JsonProperty("creditAvailable")
    private Double creditAvailable;
    @JsonProperty("creditAmount")
    private Double creditAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("penalInterest")
    public Double getPenalInterest() {
        return penalInterest;
    }

    @JsonProperty("penalInterest")
    public void setPenalInterest(Double penalInterest) {
        this.penalInterest = penalInterest;
    }

    @JsonProperty("isCreditRequired")
    public String getIsCreditRequired() {
        return isCreditRequired;
    }

    @JsonProperty("isCreditRequired")
    public void setIsCreditRequired(String isCreditRequired) {
        this.isCreditRequired = isCreditRequired;
    }

    @JsonProperty("CreditUtilisation")
    public Double getCreditUtilisation() {
        return creditUtilisation;
    }

    @JsonProperty("CreditUtilisation")
    public void setCreditUtilisation(Double creditUtilisation) {
        this.creditUtilisation = creditUtilisation;
    }

    @JsonProperty("creditType")
    public String getCreditType() {
        return creditType;
    }

    @JsonProperty("creditType")
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    @JsonProperty("creditLimitReferenceId")
    public String getCreditLimitReferenceId() {
        return creditLimitReferenceId;
    }

    @JsonProperty("creditLimitReferenceId")
    public void setCreditLimitReferenceId(String creditLimitReferenceId) {
        this.creditLimitReferenceId = creditLimitReferenceId;
    }

    @JsonProperty("creditInstrument")
    public String getCreditInstrument() {
        return creditInstrument;
    }

    @JsonProperty("creditInstrument")
    public void setCreditInstrument(String creditInstrument) {
        this.creditInstrument = creditInstrument;
    }

    @JsonProperty("creditExpirationDate")
    public String getCreditExpirationDate() {
        return creditExpirationDate;
    }

    @JsonProperty("creditExpirationDate")
    public void setCreditExpirationDate(String creditExpirationDate) {
        this.creditExpirationDate = creditExpirationDate;
    }

    @JsonProperty("creditDays")
    public String getCreditDays() {
        return creditDays;
    }

    @JsonProperty("creditDays")
    public void setCreditDays(String creditDays) {
        this.creditDays = creditDays;
    }

    @JsonProperty("creditAvailable")
    public Double getCreditAvailable() {
        return creditAvailable;
    }

    @JsonProperty("creditAvailable")
    public void setCreditAvailable(Double creditAvailable) {
        this.creditAvailable = creditAvailable;
    }

    @JsonProperty("creditAmount")
    public Double getCreditAmount() {
        return creditAmount;
    }

    @JsonProperty("creditAmount")
    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
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
        sb.append(CreditDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("penalInterest");
        sb.append('=');
        sb.append(((this.penalInterest == null)?"<null>":this.penalInterest));
        sb.append(',');
        sb.append("isCreditRequired");
        sb.append('=');
        sb.append(((this.isCreditRequired == null)?"<null>":this.isCreditRequired));
        sb.append(',');
        sb.append("creditUtilisation");
        sb.append('=');
        sb.append(((this.creditUtilisation == null)?"<null>":this.creditUtilisation));
        sb.append(',');
        sb.append("creditType");
        sb.append('=');
        sb.append(((this.creditType == null)?"<null>":this.creditType));
        sb.append(',');
        sb.append("creditLimitReferenceId");
        sb.append('=');
        sb.append(((this.creditLimitReferenceId == null)?"<null>":this.creditLimitReferenceId));
        sb.append(',');
        sb.append("creditInstrument");
        sb.append('=');
        sb.append(((this.creditInstrument == null)?"<null>":this.creditInstrument));
        sb.append(',');
        sb.append("creditExpirationDate");
        sb.append('=');
        sb.append(((this.creditExpirationDate == null)?"<null>":this.creditExpirationDate));
        sb.append(',');
        sb.append("creditDays");
        sb.append('=');
        sb.append(((this.creditDays == null)?"<null>":this.creditDays));
        sb.append(',');
        sb.append("creditAvailable");
        sb.append('=');
        sb.append(((this.creditAvailable == null)?"<null>":this.creditAvailable));
        sb.append(',');
        sb.append("creditAmount");
        sb.append('=');
        sb.append(((this.creditAmount == null)?"<null>":this.creditAmount));
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
        result = ((result* 31)+((this.isCreditRequired == null)? 0 :this.isCreditRequired.hashCode()));
        result = ((result* 31)+((this.creditLimitReferenceId == null)? 0 :this.creditLimitReferenceId.hashCode()));
        result = ((result* 31)+((this.penalInterest == null)? 0 :this.penalInterest.hashCode()));
        result = ((result* 31)+((this.creditUtilisation == null)? 0 :this.creditUtilisation.hashCode()));
        result = ((result* 31)+((this.creditDays == null)? 0 :this.creditDays.hashCode()));
        result = ((result* 31)+((this.creditInstrument == null)? 0 :this.creditInstrument.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.creditAvailable == null)? 0 :this.creditAvailable.hashCode()));
        result = ((result* 31)+((this.creditAmount == null)? 0 :this.creditAmount.hashCode()));
        result = ((result* 31)+((this.creditType == null)? 0 :this.creditType.hashCode()));
        result = ((result* 31)+((this.creditExpirationDate == null)? 0 :this.creditExpirationDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreditDetails) == false) {
            return false;
        }
        CreditDetails rhs = ((CreditDetails) other);
        return ((((((((((((this.isCreditRequired == rhs.isCreditRequired)||((this.isCreditRequired!= null)&&this.isCreditRequired.equals(rhs.isCreditRequired)))&&((this.creditLimitReferenceId == rhs.creditLimitReferenceId)||((this.creditLimitReferenceId!= null)&&this.creditLimitReferenceId.equals(rhs.creditLimitReferenceId))))&&((this.penalInterest == rhs.penalInterest)||((this.penalInterest!= null)&&this.penalInterest.equals(rhs.penalInterest))))&&((this.creditUtilisation == rhs.creditUtilisation)||((this.creditUtilisation!= null)&&this.creditUtilisation.equals(rhs.creditUtilisation))))&&((this.creditDays == rhs.creditDays)||((this.creditDays!= null)&&this.creditDays.equals(rhs.creditDays))))&&((this.creditInstrument == rhs.creditInstrument)||((this.creditInstrument!= null)&&this.creditInstrument.equals(rhs.creditInstrument))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.creditAvailable == rhs.creditAvailable)||((this.creditAvailable!= null)&&this.creditAvailable.equals(rhs.creditAvailable))))&&((this.creditAmount == rhs.creditAmount)||((this.creditAmount!= null)&&this.creditAmount.equals(rhs.creditAmount))))&&((this.creditType == rhs.creditType)||((this.creditType!= null)&&this.creditType.equals(rhs.creditType))))&&((this.creditExpirationDate == rhs.creditExpirationDate)||((this.creditExpirationDate!= null)&&this.creditExpirationDate.equals(rhs.creditExpirationDate))));
    }

}
