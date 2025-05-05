
package com.qapitol.jsw.apitest.pojos.order.centralCartFetchApiResponse;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total_cart_amount",
    "total_cart_tax_amount",
    "total_advance_amount_required",
    "total_credit_applied",
    "offers",
    "service_charges",
    "total_amount_due"
})
@Generated("jsonschema2pojo")
public class CartPrice {

    @JsonProperty("total_cart_amount")
    private Double totalCartAmount;
    @JsonProperty("total_cart_tax_amount")
    private Double totalCartTaxAmount;
    @JsonProperty("total_advance_amount_required")
    private Double totalAdvanceAmountRequired;
    @JsonProperty("total_credit_applied")
    private Double totalCreditApplied;
    @JsonProperty("offers")
    private List<Object> offers = new ArrayList<Object>();
    @JsonProperty("service_charges")
    private List<ServiceCharge> serviceCharges = new ArrayList<ServiceCharge>();
    @JsonProperty("total_amount_due")
    private Double totalAmountDue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("total_cart_amount")
    public Double getTotalCartAmount() {
        return totalCartAmount;
    }

    @JsonProperty("total_cart_amount")
    public void setTotalCartAmount(Double totalCartAmount) {
        this.totalCartAmount = totalCartAmount;
    }

    @JsonProperty("total_cart_tax_amount")
    public Double getTotalCartTaxAmount() {
        return totalCartTaxAmount;
    }

    @JsonProperty("total_cart_tax_amount")
    public void setTotalCartTaxAmount(Double totalCartTaxAmount) {
        this.totalCartTaxAmount = totalCartTaxAmount;
    }

    @JsonProperty("total_advance_amount_required")
    public Double getTotalAdvanceAmountRequired() {
        return totalAdvanceAmountRequired;
    }

    @JsonProperty("total_advance_amount_required")
    public void setTotalAdvanceAmountRequired(Double totalAdvanceAmountRequired) {
        this.totalAdvanceAmountRequired = totalAdvanceAmountRequired;
    }

    @JsonProperty("total_credit_applied")
    public Double getTotalCreditApplied() {
        return totalCreditApplied;
    }

    @JsonProperty("total_credit_applied")
    public void setTotalCreditApplied(Double totalCreditApplied) {
        this.totalCreditApplied = totalCreditApplied;
    }

    @JsonProperty("offers")
    public List<Object> getOffers() {
        return offers;
    }

    @JsonProperty("offers")
    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    @JsonProperty("service_charges")
    public List<ServiceCharge> getServiceCharges() {
        return serviceCharges;
    }

    @JsonProperty("service_charges")
    public void setServiceCharges(List<ServiceCharge> serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    @JsonProperty("total_amount_due")
    public Double getTotalAmountDue() {
        return totalAmountDue;
    }

    @JsonProperty("total_amount_due")
    public void setTotalAmountDue(Double totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
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
        sb.append(CartPrice.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("totalCartAmount");
        sb.append('=');
        sb.append(((this.totalCartAmount == null)?"<null>":this.totalCartAmount));
        sb.append(',');
        sb.append("totalCartTaxAmount");
        sb.append('=');
        sb.append(((this.totalCartTaxAmount == null)?"<null>":this.totalCartTaxAmount));
        sb.append(',');
        sb.append("totalAdvanceAmountRequired");
        sb.append('=');
        sb.append(((this.totalAdvanceAmountRequired == null)?"<null>":this.totalAdvanceAmountRequired));
        sb.append(',');
        sb.append("totalCreditApplied");
        sb.append('=');
        sb.append(((this.totalCreditApplied == null)?"<null>":this.totalCreditApplied));
        sb.append(',');
        sb.append("offers");
        sb.append('=');
        sb.append(((this.offers == null)?"<null>":this.offers));
        sb.append(',');
        sb.append("serviceCharges");
        sb.append('=');
        sb.append(((this.serviceCharges == null)?"<null>":this.serviceCharges));
        sb.append(',');
        sb.append("totalAmountDue");
        sb.append('=');
        sb.append(((this.totalAmountDue == null)?"<null>":this.totalAmountDue));
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
        result = ((result* 31)+((this.offers == null)? 0 :this.offers.hashCode()));
        result = ((result* 31)+((this.serviceCharges == null)? 0 :this.serviceCharges.hashCode()));
        result = ((result* 31)+((this.totalCartAmount == null)? 0 :this.totalCartAmount.hashCode()));
        result = ((result* 31)+((this.totalCartTaxAmount == null)? 0 :this.totalCartTaxAmount.hashCode()));
        result = ((result* 31)+((this.totalAdvanceAmountRequired == null)? 0 :this.totalAdvanceAmountRequired.hashCode()));
        result = ((result* 31)+((this.totalAmountDue == null)? 0 :this.totalAmountDue.hashCode()));
        result = ((result* 31)+((this.totalCreditApplied == null)? 0 :this.totalCreditApplied.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CartPrice) == false) {
            return false;
        }
        CartPrice rhs = ((CartPrice) other);
        return (((((((((this.offers == rhs.offers)||((this.offers!= null)&&this.offers.equals(rhs.offers)))&&((this.serviceCharges == rhs.serviceCharges)||((this.serviceCharges!= null)&&this.serviceCharges.equals(rhs.serviceCharges))))&&((this.totalCartAmount == rhs.totalCartAmount)||((this.totalCartAmount!= null)&&this.totalCartAmount.equals(rhs.totalCartAmount))))&&((this.totalCartTaxAmount == rhs.totalCartTaxAmount)||((this.totalCartTaxAmount!= null)&&this.totalCartTaxAmount.equals(rhs.totalCartTaxAmount))))&&((this.totalAdvanceAmountRequired == rhs.totalAdvanceAmountRequired)||((this.totalAdvanceAmountRequired!= null)&&this.totalAdvanceAmountRequired.equals(rhs.totalAdvanceAmountRequired))))&&((this.totalAmountDue == rhs.totalAmountDue)||((this.totalAmountDue!= null)&&this.totalAmountDue.equals(rhs.totalAmountDue))))&&((this.totalCreditApplied == rhs.totalCreditApplied)||((this.totalCreditApplied!= null)&&this.totalCreditApplied.equals(rhs.totalCreditApplied))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
