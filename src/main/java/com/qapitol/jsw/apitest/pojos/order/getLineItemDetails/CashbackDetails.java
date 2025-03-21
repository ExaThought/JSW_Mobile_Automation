
package com.qapitol.jsw.apitest.pojos.order.getLineItemDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cashbackRate"
})
@Generated("jsonschema2pojo")
public class CashbackDetails {

    @JsonProperty("cashbackRate")
    private Double cashbackRate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cashbackRate")
    public Double getCashbackRate() {
        return cashbackRate;
    }

    @JsonProperty("cashbackRate")
    public void setCashbackRate(Double cashbackRate) {
        this.cashbackRate = cashbackRate;
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
        sb.append(CashbackDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cashbackRate");
        sb.append('=');
        sb.append(((this.cashbackRate == null)?"<null>":this.cashbackRate));
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
        result = ((result* 31)+((this.cashbackRate == null)? 0 :this.cashbackRate.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CashbackDetails) == false) {
            return false;
        }
        CashbackDetails rhs = ((CashbackDetails) other);
        return (((this.cashbackRate == rhs.cashbackRate)||((this.cashbackRate!= null)&&this.cashbackRate.equals(rhs.cashbackRate)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
