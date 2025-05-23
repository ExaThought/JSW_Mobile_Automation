
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
    "is_credit_required",
    "credit_limit_bypass",
    "limit_reference_id",
    "credit_days",
    "credit_amount",
    "credit_usable_amount"
})
@Generated("jsonschema2pojo")
public class CreditDetails {

    @JsonProperty("is_credit_required")
    private Boolean isCreditRequired;
    @JsonProperty("credit_limit_bypass")
    private Boolean creditLimitBypass;
    @JsonProperty("limit_reference_id")
    private String limitReferenceId;
    @JsonProperty("credit_days")
    private String creditDays;
    @JsonProperty("credit_amount")
    private Double creditAmount;
    @JsonProperty("credit_usable_amount")
    private Integer creditUsableAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("is_credit_required")
    public Boolean getIsCreditRequired() {
        return isCreditRequired;
    }

    @JsonProperty("is_credit_required")
    public void setIsCreditRequired(Boolean isCreditRequired) {
        this.isCreditRequired = isCreditRequired;
    }

    @JsonProperty("credit_limit_bypass")
    public Boolean getCreditLimitBypass() {
        return creditLimitBypass;
    }

    @JsonProperty("credit_limit_bypass")
    public void setCreditLimitBypass(Boolean creditLimitBypass) {
        this.creditLimitBypass = creditLimitBypass;
    }

    @JsonProperty("limit_reference_id")
    public String getLimitReferenceId() {
        return limitReferenceId;
    }

    @JsonProperty("limit_reference_id")
    public void setLimitReferenceId(String limitReferenceId) {
        this.limitReferenceId = limitReferenceId;
    }

    @JsonProperty("credit_days")
    public String getCreditDays() {
        return creditDays;
    }

    @JsonProperty("credit_days")
    public void setCreditDays(String creditDays) {
        this.creditDays = creditDays;
    }

    @JsonProperty("credit_amount")
    public Double getCreditAmount() {
        return creditAmount;
    }

    @JsonProperty("credit_amount")
    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    @JsonProperty("credit_usable_amount")
    public Integer getCreditUsableAmount() {
        return creditUsableAmount;
    }

    @JsonProperty("credit_usable_amount")
    public void setCreditUsableAmount(Integer creditUsableAmount) {
        this.creditUsableAmount = creditUsableAmount;
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
        sb.append("isCreditRequired");
        sb.append('=');
        sb.append(((this.isCreditRequired == null)?"<null>":this.isCreditRequired));
        sb.append(',');
        sb.append("creditLimitBypass");
        sb.append('=');
        sb.append(((this.creditLimitBypass == null)?"<null>":this.creditLimitBypass));
        sb.append(',');
        sb.append("limitReferenceId");
        sb.append('=');
        sb.append(((this.limitReferenceId == null)?"<null>":this.limitReferenceId));
        sb.append(',');
        sb.append("creditDays");
        sb.append('=');
        sb.append(((this.creditDays == null)?"<null>":this.creditDays));
        sb.append(',');
        sb.append("creditAmount");
        sb.append('=');
        sb.append(((this.creditAmount == null)?"<null>":this.creditAmount));
        sb.append(',');
        sb.append("creditUsableAmount");
        sb.append('=');
        sb.append(((this.creditUsableAmount == null)?"<null>":this.creditUsableAmount));
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
        result = ((result* 31)+((this.creditLimitBypass == null)? 0 :this.creditLimitBypass.hashCode()));
        result = ((result* 31)+((this.creditUsableAmount == null)? 0 :this.creditUsableAmount.hashCode()));
        result = ((result* 31)+((this.creditDays == null)? 0 :this.creditDays.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.creditAmount == null)? 0 :this.creditAmount.hashCode()));
        result = ((result* 31)+((this.limitReferenceId == null)? 0 :this.limitReferenceId.hashCode()));
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
        return ((((((((this.isCreditRequired == rhs.isCreditRequired)||((this.isCreditRequired!= null)&&this.isCreditRequired.equals(rhs.isCreditRequired)))&&((this.creditLimitBypass == rhs.creditLimitBypass)||((this.creditLimitBypass!= null)&&this.creditLimitBypass.equals(rhs.creditLimitBypass))))&&((this.creditUsableAmount == rhs.creditUsableAmount)||((this.creditUsableAmount!= null)&&this.creditUsableAmount.equals(rhs.creditUsableAmount))))&&((this.creditDays == rhs.creditDays)||((this.creditDays!= null)&&this.creditDays.equals(rhs.creditDays))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.creditAmount == rhs.creditAmount)||((this.creditAmount!= null)&&this.creditAmount.equals(rhs.creditAmount))))&&((this.limitReferenceId == rhs.limitReferenceId)||((this.limitReferenceId!= null)&&this.limitReferenceId.equals(rhs.limitReferenceId))));
    }

}
