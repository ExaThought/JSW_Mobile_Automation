
package com.qapitol.jsw.apitest.pojos.order.centralCartSlcAddToCartRequest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    "offers",
    "unit_price",
    "line_item_amount",
    "tax_amount"
})
@Generated("jsonschema2pojo")
public class LineItemPrice {

    @JsonProperty("offers")
    private List<Offer> offers = new ArrayList<Offer>();
    @JsonProperty("unit_price")
    private Integer unitPrice;
    @JsonProperty("line_item_amount")
    private String lineItemAmount;
    @JsonProperty("tax_amount")
    private Integer taxAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("offers")
    public List<Offer> getOffers() {
        return offers;
    }

    @JsonProperty("offers")
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @JsonProperty("unit_price")
    public Integer getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("unit_price")
    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    @JsonProperty("line_item_amount")
    public String getLineItemAmount() {
        return lineItemAmount;
    }

    @JsonProperty("line_item_amount")
    public void setLineItemAmount(String lineItemAmount) {
        this.lineItemAmount = lineItemAmount;
    }

    @JsonProperty("tax_amount")
    public Integer getTaxAmount() {
        return taxAmount;
    }

    @JsonProperty("tax_amount")
    public void setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
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
        sb.append("offers");
        sb.append('=');
        sb.append(((this.offers == null)?"<null>":this.offers));
        sb.append(',');
        sb.append("unitPrice");
        sb.append('=');
        sb.append(((this.unitPrice == null)?"<null>":this.unitPrice));
        sb.append(',');
        sb.append("lineItemAmount");
        sb.append('=');
        sb.append(((this.lineItemAmount == null)?"<null>":this.lineItemAmount));
        sb.append(',');
        sb.append("taxAmount");
        sb.append('=');
        sb.append(((this.taxAmount == null)?"<null>":this.taxAmount));
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
        result = ((result* 31)+((this.unitPrice == null)? 0 :this.unitPrice.hashCode()));
        result = ((result* 31)+((this.lineItemAmount == null)? 0 :this.lineItemAmount.hashCode()));
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
        return ((((((this.offers == rhs.offers)||((this.offers!= null)&&this.offers.equals(rhs.offers)))&&((this.unitPrice == rhs.unitPrice)||((this.unitPrice!= null)&&this.unitPrice.equals(rhs.unitPrice))))&&((this.lineItemAmount == rhs.lineItemAmount)||((this.lineItemAmount!= null)&&this.lineItemAmount.equals(rhs.lineItemAmount))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.taxAmount == rhs.taxAmount)||((this.taxAmount!= null)&&this.taxAmount.equals(rhs.taxAmount))));
    }

}
