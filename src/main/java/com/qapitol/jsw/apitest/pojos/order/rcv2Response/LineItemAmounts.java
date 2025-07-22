
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "payableAmount",
    "amount",
    "taxedAmount",
    "discountedAmount",
    "discountedTaxedAmount",
    "perUnitAmount",
    "discountedPerUnitAmount",
    "previousPerUnitAmount",
    "discountedTaxAmount",
    "lineItemTaxAmount"
})
@Generated("jsonschema2pojo")
public class LineItemAmounts {

    @JsonProperty("payableAmount")
    private PayableAmount__1 payableAmount;
    @JsonProperty("amount")
    private Amount__2 amount;
    @JsonProperty("taxedAmount")
    private TaxedAmount taxedAmount;
    @JsonProperty("discountedAmount")
    private DiscountedAmount discountedAmount;
    @JsonProperty("discountedTaxedAmount")
    private DiscountedTaxedAmount discountedTaxedAmount;
    @JsonProperty("perUnitAmount")
    private PerUnitAmount perUnitAmount;
    @JsonProperty("discountedPerUnitAmount")
    private DiscountedPerUnitAmount discountedPerUnitAmount;
    @JsonProperty("previousPerUnitAmount")
    private PreviousPerUnitAmount previousPerUnitAmount;
    @JsonProperty("discountedTaxAmount")
    private Object discountedTaxAmount;
    @JsonProperty("lineItemTaxAmount")
    private Object lineItemTaxAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("payableAmount")
    public PayableAmount__1 getPayableAmount() {
        return payableAmount;
    }

    @JsonProperty("payableAmount")
    public void setPayableAmount(PayableAmount__1 payableAmount) {
        this.payableAmount = payableAmount;
    }

    @JsonProperty("amount")
    public Amount__2 getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Amount__2 amount) {
        this.amount = amount;
    }

    @JsonProperty("taxedAmount")
    public TaxedAmount getTaxedAmount() {
        return taxedAmount;
    }

    @JsonProperty("taxedAmount")
    public void setTaxedAmount(TaxedAmount taxedAmount) {
        this.taxedAmount = taxedAmount;
    }

    @JsonProperty("discountedAmount")
    public DiscountedAmount getDiscountedAmount() {
        return discountedAmount;
    }

    @JsonProperty("discountedAmount")
    public void setDiscountedAmount(DiscountedAmount discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    @JsonProperty("discountedTaxedAmount")
    public DiscountedTaxedAmount getDiscountedTaxedAmount() {
        return discountedTaxedAmount;
    }

    @JsonProperty("discountedTaxedAmount")
    public void setDiscountedTaxedAmount(DiscountedTaxedAmount discountedTaxedAmount) {
        this.discountedTaxedAmount = discountedTaxedAmount;
    }

    @JsonProperty("perUnitAmount")
    public PerUnitAmount getPerUnitAmount() {
        return perUnitAmount;
    }

    @JsonProperty("perUnitAmount")
    public void setPerUnitAmount(PerUnitAmount perUnitAmount) {
        this.perUnitAmount = perUnitAmount;
    }

    @JsonProperty("discountedPerUnitAmount")
    public DiscountedPerUnitAmount getDiscountedPerUnitAmount() {
        return discountedPerUnitAmount;
    }

    @JsonProperty("discountedPerUnitAmount")
    public void setDiscountedPerUnitAmount(DiscountedPerUnitAmount discountedPerUnitAmount) {
        this.discountedPerUnitAmount = discountedPerUnitAmount;
    }

    @JsonProperty("previousPerUnitAmount")
    public PreviousPerUnitAmount getPreviousPerUnitAmount() {
        return previousPerUnitAmount;
    }

    @JsonProperty("previousPerUnitAmount")
    public void setPreviousPerUnitAmount(PreviousPerUnitAmount previousPerUnitAmount) {
        this.previousPerUnitAmount = previousPerUnitAmount;
    }

    @JsonProperty("discountedTaxAmount")
    public Object getDiscountedTaxAmount() {
        return discountedTaxAmount;
    }

    @JsonProperty("discountedTaxAmount")
    public void setDiscountedTaxAmount(Object discountedTaxAmount) {
        this.discountedTaxAmount = discountedTaxAmount;
    }

    @JsonProperty("lineItemTaxAmount")
    public Object getLineItemTaxAmount() {
        return lineItemTaxAmount;
    }

    @JsonProperty("lineItemTaxAmount")
    public void setLineItemTaxAmount(Object lineItemTaxAmount) {
        this.lineItemTaxAmount = lineItemTaxAmount;
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
        sb.append(LineItemAmounts.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("payableAmount");
        sb.append('=');
        sb.append(((this.payableAmount == null)?"<null>":this.payableAmount));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("taxedAmount");
        sb.append('=');
        sb.append(((this.taxedAmount == null)?"<null>":this.taxedAmount));
        sb.append(',');
        sb.append("discountedAmount");
        sb.append('=');
        sb.append(((this.discountedAmount == null)?"<null>":this.discountedAmount));
        sb.append(',');
        sb.append("discountedTaxedAmount");
        sb.append('=');
        sb.append(((this.discountedTaxedAmount == null)?"<null>":this.discountedTaxedAmount));
        sb.append(',');
        sb.append("perUnitAmount");
        sb.append('=');
        sb.append(((this.perUnitAmount == null)?"<null>":this.perUnitAmount));
        sb.append(',');
        sb.append("discountedPerUnitAmount");
        sb.append('=');
        sb.append(((this.discountedPerUnitAmount == null)?"<null>":this.discountedPerUnitAmount));
        sb.append(',');
        sb.append("previousPerUnitAmount");
        sb.append('=');
        sb.append(((this.previousPerUnitAmount == null)?"<null>":this.previousPerUnitAmount));
        sb.append(',');
        sb.append("discountedTaxAmount");
        sb.append('=');
        sb.append(((this.discountedTaxAmount == null)?"<null>":this.discountedTaxAmount));
        sb.append(',');
        sb.append("lineItemTaxAmount");
        sb.append('=');
        sb.append(((this.lineItemTaxAmount == null)?"<null>":this.lineItemTaxAmount));
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
        result = ((result* 31)+((this.discountedAmount == null)? 0 :this.discountedAmount.hashCode()));
        result = ((result* 31)+((this.discountedTaxAmount == null)? 0 :this.discountedTaxAmount.hashCode()));
        result = ((result* 31)+((this.perUnitAmount == null)? 0 :this.perUnitAmount.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.discountedTaxedAmount == null)? 0 :this.discountedTaxedAmount.hashCode()));
        result = ((result* 31)+((this.discountedPerUnitAmount == null)? 0 :this.discountedPerUnitAmount.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.previousPerUnitAmount == null)? 0 :this.previousPerUnitAmount.hashCode()));
        result = ((result* 31)+((this.payableAmount == null)? 0 :this.payableAmount.hashCode()));
        result = ((result* 31)+((this.lineItemTaxAmount == null)? 0 :this.lineItemTaxAmount.hashCode()));
        result = ((result* 31)+((this.taxedAmount == null)? 0 :this.taxedAmount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemAmounts) == false) {
            return false;
        }
        LineItemAmounts rhs = ((LineItemAmounts) other);
        return ((((((((((((this.discountedAmount == rhs.discountedAmount)||((this.discountedAmount!= null)&&this.discountedAmount.equals(rhs.discountedAmount)))&&((this.discountedTaxAmount == rhs.discountedTaxAmount)||((this.discountedTaxAmount!= null)&&this.discountedTaxAmount.equals(rhs.discountedTaxAmount))))&&((this.perUnitAmount == rhs.perUnitAmount)||((this.perUnitAmount!= null)&&this.perUnitAmount.equals(rhs.perUnitAmount))))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.discountedTaxedAmount == rhs.discountedTaxedAmount)||((this.discountedTaxedAmount!= null)&&this.discountedTaxedAmount.equals(rhs.discountedTaxedAmount))))&&((this.discountedPerUnitAmount == rhs.discountedPerUnitAmount)||((this.discountedPerUnitAmount!= null)&&this.discountedPerUnitAmount.equals(rhs.discountedPerUnitAmount))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.previousPerUnitAmount == rhs.previousPerUnitAmount)||((this.previousPerUnitAmount!= null)&&this.previousPerUnitAmount.equals(rhs.previousPerUnitAmount))))&&((this.payableAmount == rhs.payableAmount)||((this.payableAmount!= null)&&this.payableAmount.equals(rhs.payableAmount))))&&((this.lineItemTaxAmount == rhs.lineItemTaxAmount)||((this.lineItemTaxAmount!= null)&&this.lineItemTaxAmount.equals(rhs.lineItemTaxAmount))))&&((this.taxedAmount == rhs.taxedAmount)||((this.taxedAmount!= null)&&this.taxedAmount.equals(rhs.taxedAmount))));
    }

}
