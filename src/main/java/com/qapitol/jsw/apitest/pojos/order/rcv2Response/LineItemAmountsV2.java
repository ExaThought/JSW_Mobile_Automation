
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "payableAmount",
    "amount",
    "discountedAmount",
    "perUnitAmount",
    "perUnitAmountTax",
    "discountedPerUnitAmount",
    "discountedPerUnitAmountTax",
    "previousPerUnitAmount",
    "taxOnDiscountAmount",
    "taxOnlineItemAmount",
    "partingCharge",
    "bendingCharge"
})
@Generated("jsonschema2pojo")
public class LineItemAmountsV2 {

    @JsonProperty("payableAmount")
    private PayableAmount__2 payableAmount;
    @JsonProperty("amount")
    private Amount__3 amount;
    @JsonProperty("discountedAmount")
    private DiscountedAmount__1 discountedAmount;
    @JsonProperty("perUnitAmount")
    private PerUnitAmount__1 perUnitAmount;
    @JsonProperty("perUnitAmountTax")
    private PerUnitAmountTax perUnitAmountTax;
    @JsonProperty("discountedPerUnitAmount")
    private DiscountedPerUnitAmount__1 discountedPerUnitAmount;
    @JsonProperty("discountedPerUnitAmountTax")
    private DiscountedPerUnitAmountTax discountedPerUnitAmountTax;
    @JsonProperty("previousPerUnitAmount")
    private PreviousPerUnitAmount__1 previousPerUnitAmount;
    @JsonProperty("taxOnDiscountAmount")
    private TaxOnDiscountAmount taxOnDiscountAmount;
    @JsonProperty("taxOnlineItemAmount")
    private TaxOnlineItemAmount taxOnlineItemAmount;
    @JsonProperty("partingCharge")
    private PartingCharge partingCharge;
    @JsonProperty("bendingCharge")
    private BendingCharge bendingCharge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("payableAmount")
    public PayableAmount__2 getPayableAmount() {
        return payableAmount;
    }

    @JsonProperty("payableAmount")
    public void setPayableAmount(PayableAmount__2 payableAmount) {
        this.payableAmount = payableAmount;
    }

    @JsonProperty("amount")
    public Amount__3 getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Amount__3 amount) {
        this.amount = amount;
    }

    @JsonProperty("discountedAmount")
    public DiscountedAmount__1 getDiscountedAmount() {
        return discountedAmount;
    }

    @JsonProperty("discountedAmount")
    public void setDiscountedAmount(DiscountedAmount__1 discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    @JsonProperty("perUnitAmount")
    public PerUnitAmount__1 getPerUnitAmount() {
        return perUnitAmount;
    }

    @JsonProperty("perUnitAmount")
    public void setPerUnitAmount(PerUnitAmount__1 perUnitAmount) {
        this.perUnitAmount = perUnitAmount;
    }

    @JsonProperty("perUnitAmountTax")
    public PerUnitAmountTax getPerUnitAmountTax() {
        return perUnitAmountTax;
    }

    @JsonProperty("perUnitAmountTax")
    public void setPerUnitAmountTax(PerUnitAmountTax perUnitAmountTax) {
        this.perUnitAmountTax = perUnitAmountTax;
    }

    @JsonProperty("discountedPerUnitAmount")
    public DiscountedPerUnitAmount__1 getDiscountedPerUnitAmount() {
        return discountedPerUnitAmount;
    }

    @JsonProperty("discountedPerUnitAmount")
    public void setDiscountedPerUnitAmount(DiscountedPerUnitAmount__1 discountedPerUnitAmount) {
        this.discountedPerUnitAmount = discountedPerUnitAmount;
    }

    @JsonProperty("discountedPerUnitAmountTax")
    public DiscountedPerUnitAmountTax getDiscountedPerUnitAmountTax() {
        return discountedPerUnitAmountTax;
    }

    @JsonProperty("discountedPerUnitAmountTax")
    public void setDiscountedPerUnitAmountTax(DiscountedPerUnitAmountTax discountedPerUnitAmountTax) {
        this.discountedPerUnitAmountTax = discountedPerUnitAmountTax;
    }

    @JsonProperty("previousPerUnitAmount")
    public PreviousPerUnitAmount__1 getPreviousPerUnitAmount() {
        return previousPerUnitAmount;
    }

    @JsonProperty("previousPerUnitAmount")
    public void setPreviousPerUnitAmount(PreviousPerUnitAmount__1 previousPerUnitAmount) {
        this.previousPerUnitAmount = previousPerUnitAmount;
    }

    @JsonProperty("taxOnDiscountAmount")
    public TaxOnDiscountAmount getTaxOnDiscountAmount() {
        return taxOnDiscountAmount;
    }

    @JsonProperty("taxOnDiscountAmount")
    public void setTaxOnDiscountAmount(TaxOnDiscountAmount taxOnDiscountAmount) {
        this.taxOnDiscountAmount = taxOnDiscountAmount;
    }

    @JsonProperty("taxOnlineItemAmount")
    public TaxOnlineItemAmount getTaxOnlineItemAmount() {
        return taxOnlineItemAmount;
    }

    @JsonProperty("taxOnlineItemAmount")
    public void setTaxOnlineItemAmount(TaxOnlineItemAmount taxOnlineItemAmount) {
        this.taxOnlineItemAmount = taxOnlineItemAmount;
    }

    @JsonProperty("partingCharge")
    public PartingCharge getPartingCharge() {
        return partingCharge;
    }

    @JsonProperty("partingCharge")
    public void setPartingCharge(PartingCharge partingCharge) {
        this.partingCharge = partingCharge;
    }

    @JsonProperty("bendingCharge")
    public BendingCharge getBendingCharge() {
        return bendingCharge;
    }

    @JsonProperty("bendingCharge")
    public void setBendingCharge(BendingCharge bendingCharge) {
        this.bendingCharge = bendingCharge;
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
        sb.append(LineItemAmountsV2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("payableAmount");
        sb.append('=');
        sb.append(((this.payableAmount == null)?"<null>":this.payableAmount));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("discountedAmount");
        sb.append('=');
        sb.append(((this.discountedAmount == null)?"<null>":this.discountedAmount));
        sb.append(',');
        sb.append("perUnitAmount");
        sb.append('=');
        sb.append(((this.perUnitAmount == null)?"<null>":this.perUnitAmount));
        sb.append(',');
        sb.append("perUnitAmountTax");
        sb.append('=');
        sb.append(((this.perUnitAmountTax == null)?"<null>":this.perUnitAmountTax));
        sb.append(',');
        sb.append("discountedPerUnitAmount");
        sb.append('=');
        sb.append(((this.discountedPerUnitAmount == null)?"<null>":this.discountedPerUnitAmount));
        sb.append(',');
        sb.append("discountedPerUnitAmountTax");
        sb.append('=');
        sb.append(((this.discountedPerUnitAmountTax == null)?"<null>":this.discountedPerUnitAmountTax));
        sb.append(',');
        sb.append("previousPerUnitAmount");
        sb.append('=');
        sb.append(((this.previousPerUnitAmount == null)?"<null>":this.previousPerUnitAmount));
        sb.append(',');
        sb.append("taxOnDiscountAmount");
        sb.append('=');
        sb.append(((this.taxOnDiscountAmount == null)?"<null>":this.taxOnDiscountAmount));
        sb.append(',');
        sb.append("taxOnlineItemAmount");
        sb.append('=');
        sb.append(((this.taxOnlineItemAmount == null)?"<null>":this.taxOnlineItemAmount));
        sb.append(',');
        sb.append("partingCharge");
        sb.append('=');
        sb.append(((this.partingCharge == null)?"<null>":this.partingCharge));
        sb.append(',');
        sb.append("bendingCharge");
        sb.append('=');
        sb.append(((this.bendingCharge == null)?"<null>":this.bendingCharge));
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
        result = ((result* 31)+((this.perUnitAmount == null)? 0 :this.perUnitAmount.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.discountedPerUnitAmountTax == null)? 0 :this.discountedPerUnitAmountTax.hashCode()));
        result = ((result* 31)+((this.discountedPerUnitAmount == null)? 0 :this.discountedPerUnitAmount.hashCode()));
        result = ((result* 31)+((this.payableAmount == null)? 0 :this.payableAmount.hashCode()));
        result = ((result* 31)+((this.discountedAmount == null)? 0 :this.discountedAmount.hashCode()));
        result = ((result* 31)+((this.partingCharge == null)? 0 :this.partingCharge.hashCode()));
        result = ((result* 31)+((this.bendingCharge == null)? 0 :this.bendingCharge.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.taxOnlineItemAmount == null)? 0 :this.taxOnlineItemAmount.hashCode()));
        result = ((result* 31)+((this.perUnitAmountTax == null)? 0 :this.perUnitAmountTax.hashCode()));
        result = ((result* 31)+((this.previousPerUnitAmount == null)? 0 :this.previousPerUnitAmount.hashCode()));
        result = ((result* 31)+((this.taxOnDiscountAmount == null)? 0 :this.taxOnDiscountAmount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemAmountsV2) == false) {
            return false;
        }
        LineItemAmountsV2 rhs = ((LineItemAmountsV2) other);
        return ((((((((((((((this.perUnitAmount == rhs.perUnitAmount)||((this.perUnitAmount!= null)&&this.perUnitAmount.equals(rhs.perUnitAmount)))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.discountedPerUnitAmountTax == rhs.discountedPerUnitAmountTax)||((this.discountedPerUnitAmountTax!= null)&&this.discountedPerUnitAmountTax.equals(rhs.discountedPerUnitAmountTax))))&&((this.discountedPerUnitAmount == rhs.discountedPerUnitAmount)||((this.discountedPerUnitAmount!= null)&&this.discountedPerUnitAmount.equals(rhs.discountedPerUnitAmount))))&&((this.payableAmount == rhs.payableAmount)||((this.payableAmount!= null)&&this.payableAmount.equals(rhs.payableAmount))))&&((this.discountedAmount == rhs.discountedAmount)||((this.discountedAmount!= null)&&this.discountedAmount.equals(rhs.discountedAmount))))&&((this.partingCharge == rhs.partingCharge)||((this.partingCharge!= null)&&this.partingCharge.equals(rhs.partingCharge))))&&((this.bendingCharge == rhs.bendingCharge)||((this.bendingCharge!= null)&&this.bendingCharge.equals(rhs.bendingCharge))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.taxOnlineItemAmount == rhs.taxOnlineItemAmount)||((this.taxOnlineItemAmount!= null)&&this.taxOnlineItemAmount.equals(rhs.taxOnlineItemAmount))))&&((this.perUnitAmountTax == rhs.perUnitAmountTax)||((this.perUnitAmountTax!= null)&&this.perUnitAmountTax.equals(rhs.perUnitAmountTax))))&&((this.previousPerUnitAmount == rhs.previousPerUnitAmount)||((this.previousPerUnitAmount!= null)&&this.previousPerUnitAmount.equals(rhs.previousPerUnitAmount))))&&((this.taxOnDiscountAmount == rhs.taxOnDiscountAmount)||((this.taxOnDiscountAmount!= null)&&this.taxOnDiscountAmount.equals(rhs.taxOnDiscountAmount))));
    }

}
