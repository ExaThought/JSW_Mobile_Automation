
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "line_item_amount",
    "tax_amount",
    "offers",
    "service_charges",
    "unit_price"
})
@Generated("jsonschema2pojo")
public class LineItemPrice {

    @JsonProperty("line_item_amount")
    private Double lineItemAmount;
    @JsonProperty("tax_amount")
    private Double taxAmount;
    @JsonProperty("offers")
    private Object offers;
    @JsonProperty("service_charges")
    private Object serviceCharges;
    @JsonProperty("unit_price")
    private Double unitPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("line_item_amount")
    public Double getLineItemAmount() {
        return lineItemAmount;
    }

    @JsonProperty("line_item_amount")
    public void setLineItemAmount(Double lineItemAmount) {
        this.lineItemAmount = lineItemAmount;
    }

    @JsonProperty("tax_amount")
    public Double getTaxAmount() {
        return taxAmount;
    }

    @JsonProperty("tax_amount")
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    @JsonProperty("offers")
    public Object getOffers() {
        return offers;
    }

    @JsonProperty("offers")
    public void setOffers(Object offers) {
        this.offers = offers;
    }

    @JsonProperty("service_charges")
    public Object getServiceCharges() {
        return serviceCharges;
    }

    @JsonProperty("service_charges")
    public void setServiceCharges(Object serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    @JsonProperty("unit_price")
    public Double getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("unit_price")
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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
        sb.append(LineItemPrice.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lineItemAmount");
        sb.append('=');
        sb.append(((this.lineItemAmount == null)?"<null>":this.lineItemAmount));
        sb.append(',');
        sb.append("taxAmount");
        sb.append('=');
        sb.append(((this.taxAmount == null)?"<null>":this.taxAmount));
        sb.append(',');
        sb.append("offers");
        sb.append('=');
        sb.append(((this.offers == null)?"<null>":this.offers));
        sb.append(',');
        sb.append("serviceCharges");
        sb.append('=');
        sb.append(((this.serviceCharges == null)?"<null>":this.serviceCharges));
        sb.append(',');
        sb.append("unitPrice");
        sb.append('=');
        sb.append(((this.unitPrice == null)?"<null>":this.unitPrice));
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
        result = ((result* 31)+((this.lineItemAmount == null)? 0 :this.lineItemAmount.hashCode()));
        result = ((result* 31)+((this.offers == null)? 0 :this.offers.hashCode()));
        result = ((result* 31)+((this.unitPrice == null)? 0 :this.unitPrice.hashCode()));
        result = ((result* 31)+((this.serviceCharges == null)? 0 :this.serviceCharges.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.taxAmount == null)? 0 :this.taxAmount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemPrice) == false) {
            return false;
        }
        LineItemPrice rhs = ((LineItemPrice) other);
        return (((((((this.lineItemAmount == rhs.lineItemAmount)||((this.lineItemAmount!= null)&&this.lineItemAmount.equals(rhs.lineItemAmount)))&&((this.offers == rhs.offers)||((this.offers!= null)&&this.offers.equals(rhs.offers))))&&((this.unitPrice == rhs.unitPrice)||((this.unitPrice!= null)&&this.unitPrice.equals(rhs.unitPrice))))&&((this.serviceCharges == rhs.serviceCharges)||((this.serviceCharges!= null)&&this.serviceCharges.equals(rhs.serviceCharges))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.taxAmount == rhs.taxAmount)||((this.taxAmount!= null)&&this.taxAmount.equals(rhs.taxAmount))));
    }

}
