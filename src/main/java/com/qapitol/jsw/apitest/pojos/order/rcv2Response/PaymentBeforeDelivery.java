
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "centAmount",
    "currency",
    "displayAmount",
    "absoluteDisplayAmount",
    "precisionAmount",
    "precision"
})
@Generated("jsonschema2pojo")
public class PaymentBeforeDelivery {

    @JsonProperty("centAmount")
    private Integer centAmount;
    @JsonProperty("currency")
    private Object currency;
    @JsonProperty("displayAmount")
    private Object displayAmount;
    @JsonProperty("absoluteDisplayAmount")
    private Object absoluteDisplayAmount;
    @JsonProperty("precisionAmount")
    private Integer precisionAmount;
    @JsonProperty("precision")
    private Integer precision;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("centAmount")
    public Integer getCentAmount() {
        return centAmount;
    }

    @JsonProperty("centAmount")
    public void setCentAmount(Integer centAmount) {
        this.centAmount = centAmount;
    }

    @JsonProperty("currency")
    public Object getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    @JsonProperty("displayAmount")
    public Object getDisplayAmount() {
        return displayAmount;
    }

    @JsonProperty("displayAmount")
    public void setDisplayAmount(Object displayAmount) {
        this.displayAmount = displayAmount;
    }

    @JsonProperty("absoluteDisplayAmount")
    public Object getAbsoluteDisplayAmount() {
        return absoluteDisplayAmount;
    }

    @JsonProperty("absoluteDisplayAmount")
    public void setAbsoluteDisplayAmount(Object absoluteDisplayAmount) {
        this.absoluteDisplayAmount = absoluteDisplayAmount;
    }

    @JsonProperty("precisionAmount")
    public Integer getPrecisionAmount() {
        return precisionAmount;
    }

    @JsonProperty("precisionAmount")
    public void setPrecisionAmount(Integer precisionAmount) {
        this.precisionAmount = precisionAmount;
    }

    @JsonProperty("precision")
    public Integer getPrecision() {
        return precision;
    }

    @JsonProperty("precision")
    public void setPrecision(Integer precision) {
        this.precision = precision;
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
        sb.append(PaymentBeforeDelivery.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("centAmount");
        sb.append('=');
        sb.append(((this.centAmount == null)?"<null>":this.centAmount));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("displayAmount");
        sb.append('=');
        sb.append(((this.displayAmount == null)?"<null>":this.displayAmount));
        sb.append(',');
        sb.append("absoluteDisplayAmount");
        sb.append('=');
        sb.append(((this.absoluteDisplayAmount == null)?"<null>":this.absoluteDisplayAmount));
        sb.append(',');
        sb.append("precisionAmount");
        sb.append('=');
        sb.append(((this.precisionAmount == null)?"<null>":this.precisionAmount));
        sb.append(',');
        sb.append("precision");
        sb.append('=');
        sb.append(((this.precision == null)?"<null>":this.precision));
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
        result = ((result* 31)+((this.displayAmount == null)? 0 :this.displayAmount.hashCode()));
        result = ((result* 31)+((this.precisionAmount == null)? 0 :this.precisionAmount.hashCode()));
        result = ((result* 31)+((this.precision == null)? 0 :this.precision.hashCode()));
        result = ((result* 31)+((this.centAmount == null)? 0 :this.centAmount.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.absoluteDisplayAmount == null)? 0 :this.absoluteDisplayAmount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentBeforeDelivery) == false) {
            return false;
        }
        PaymentBeforeDelivery rhs = ((PaymentBeforeDelivery) other);
        return ((((((((this.displayAmount == rhs.displayAmount)||((this.displayAmount!= null)&&this.displayAmount.equals(rhs.displayAmount)))&&((this.precisionAmount == rhs.precisionAmount)||((this.precisionAmount!= null)&&this.precisionAmount.equals(rhs.precisionAmount))))&&((this.precision == rhs.precision)||((this.precision!= null)&&this.precision.equals(rhs.precision))))&&((this.centAmount == rhs.centAmount)||((this.centAmount!= null)&&this.centAmount.equals(rhs.centAmount))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.absoluteDisplayAmount == rhs.absoluteDisplayAmount)||((this.absoluteDisplayAmount!= null)&&this.absoluteDisplayAmount.equals(rhs.absoluteDisplayAmount))));
    }

}
