
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
    "talon_cashback_applied",
    "cashbackUoM",
    "cashbackRate"
})
@Generated("jsonschema2pojo")
public class CashbackDetails {

    @JsonProperty("talon_cashback_applied")
    private Boolean talonCashbackApplied;
    @JsonProperty("cashbackUoM")
    private String cashbackUoM;
    @JsonProperty("cashbackRate")
    private Double cashbackRate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("talon_cashback_applied")
    public Boolean getTalonCashbackApplied() {
        return talonCashbackApplied;
    }

    @JsonProperty("talon_cashback_applied")
    public void setTalonCashbackApplied(Boolean talonCashbackApplied) {
        this.talonCashbackApplied = talonCashbackApplied;
    }

    @JsonProperty("cashbackUoM")
    public String getCashbackUoM() {
        return cashbackUoM;
    }

    @JsonProperty("cashbackUoM")
    public void setCashbackUoM(String cashbackUoM) {
        this.cashbackUoM = cashbackUoM;
    }

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
        sb.append("talonCashbackApplied");
        sb.append('=');
        sb.append(((this.talonCashbackApplied == null)?"<null>":this.talonCashbackApplied));
        sb.append(',');
        sb.append("cashbackUoM");
        sb.append('=');
        sb.append(((this.cashbackUoM == null)?"<null>":this.cashbackUoM));
        sb.append(',');
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
        result = ((result* 31)+((this.talonCashbackApplied == null)? 0 :this.talonCashbackApplied.hashCode()));
        result = ((result* 31)+((this.cashbackUoM == null)? 0 :this.cashbackUoM.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.cashbackRate == null)? 0 :this.cashbackRate.hashCode()));
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
        return (((((this.talonCashbackApplied == rhs.talonCashbackApplied)||((this.talonCashbackApplied!= null)&&this.talonCashbackApplied.equals(rhs.talonCashbackApplied)))&&((this.cashbackUoM == rhs.cashbackUoM)||((this.cashbackUoM!= null)&&this.cashbackUoM.equals(rhs.cashbackUoM))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.cashbackRate == rhs.cashbackRate)||((this.cashbackRate!= null)&&this.cashbackRate.equals(rhs.cashbackRate))));
    }

}
