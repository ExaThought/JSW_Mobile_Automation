
package com.qapitol.jsw.apitest.pojos.order.getCreditDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "isCustomerCreditLimitBlocked",
    "blockedReason",
    "customerCreditInfoSROS"
})
@Generated("jsonschema2pojo")
public class GetCreditDetailsResp {

    @JsonProperty("isCustomerCreditLimitBlocked")
    private Boolean isCustomerCreditLimitBlocked;
    @JsonProperty("blockedReason")
    private Object blockedReason;
    @JsonProperty("customerCreditInfoSROS")
    private List<CustomerCreditInfoSRO> customerCreditInfoSROS = new ArrayList<CustomerCreditInfoSRO>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("isCustomerCreditLimitBlocked")
    public Boolean getIsCustomerCreditLimitBlocked() {
        return isCustomerCreditLimitBlocked;
    }

    @JsonProperty("isCustomerCreditLimitBlocked")
    public void setIsCustomerCreditLimitBlocked(Boolean isCustomerCreditLimitBlocked) {
        this.isCustomerCreditLimitBlocked = isCustomerCreditLimitBlocked;
    }

    @JsonProperty("blockedReason")
    public Object getBlockedReason() {
        return blockedReason;
    }

    @JsonProperty("blockedReason")
    public void setBlockedReason(Object blockedReason) {
        this.blockedReason = blockedReason;
    }

    @JsonProperty("customerCreditInfoSROS")
    public List<CustomerCreditInfoSRO> getCustomerCreditInfoSROS() {
        return customerCreditInfoSROS;
    }

    @JsonProperty("customerCreditInfoSROS")
    public void setCustomerCreditInfoSROS(List<CustomerCreditInfoSRO> customerCreditInfoSROS) {
        this.customerCreditInfoSROS = customerCreditInfoSROS;
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
        sb.append(GetCreditDetailsResp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("isCustomerCreditLimitBlocked");
        sb.append('=');
        sb.append(((this.isCustomerCreditLimitBlocked == null)?"<null>":this.isCustomerCreditLimitBlocked));
        sb.append(',');
        sb.append("blockedReason");
        sb.append('=');
        sb.append(((this.blockedReason == null)?"<null>":this.blockedReason));
        sb.append(',');
        sb.append("customerCreditInfoSROS");
        sb.append('=');
        sb.append(((this.customerCreditInfoSROS == null)?"<null>":this.customerCreditInfoSROS));
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
        result = ((result* 31)+((this.isCustomerCreditLimitBlocked == null)? 0 :this.isCustomerCreditLimitBlocked.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.customerCreditInfoSROS == null)? 0 :this.customerCreditInfoSROS.hashCode()));
        result = ((result* 31)+((this.blockedReason == null)? 0 :this.blockedReason.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetCreditDetailsResp) == false) {
            return false;
        }
        GetCreditDetailsResp rhs = ((GetCreditDetailsResp) other);
        return (((((this.isCustomerCreditLimitBlocked == rhs.isCustomerCreditLimitBlocked)||((this.isCustomerCreditLimitBlocked!= null)&&this.isCustomerCreditLimitBlocked.equals(rhs.isCustomerCreditLimitBlocked)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.customerCreditInfoSROS == rhs.customerCreditInfoSROS)||((this.customerCreditInfoSROS!= null)&&this.customerCreditInfoSROS.equals(rhs.customerCreditInfoSROS))))&&((this.blockedReason == rhs.blockedReason)||((this.blockedReason!= null)&&this.blockedReason.equals(rhs.blockedReason))));
    }

}
