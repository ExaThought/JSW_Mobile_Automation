
package com.qapitol.jsw.apitest.pojos.order.shipmentInvoice;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "variantMMId",
    "uom",
    "taxId",
    "amount",
    "lineItemPrice",
    "lineItemQty"
})
@Generated("jsonschema2pojo")
public class LineItem {

    @JsonProperty("variantMMId")
    private String variantMMId;
    @JsonProperty("uom")
    private String uom;
    @JsonProperty("taxId")
    private String taxId;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("lineItemPrice")
    private String lineItemPrice;
    @JsonProperty("lineItemQty")
    private String lineItemQty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("variantMMId")
    public String getVariantMMId() {
        return variantMMId;
    }

    @JsonProperty("variantMMId")
    public void setVariantMMId(String variantMMId) {
        this.variantMMId = variantMMId;
    }

    @JsonProperty("uom")
    public String getUom() {
        return uom;
    }

    @JsonProperty("uom")
    public void setUom(String uom) {
        this.uom = uom;
    }

    @JsonProperty("taxId")
    public String getTaxId() {
        return taxId;
    }

    @JsonProperty("taxId")
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonProperty("lineItemPrice")
    public String getLineItemPrice() {
        return lineItemPrice;
    }

    @JsonProperty("lineItemPrice")
    public void setLineItemPrice(String lineItemPrice) {
        this.lineItemPrice = lineItemPrice;
    }

    @JsonProperty("lineItemQty")
    public String getLineItemQty() {
        return lineItemQty;
    }

    @JsonProperty("lineItemQty")
    public void setLineItemQty(String lineItemQty) {
        this.lineItemQty = lineItemQty;
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
        sb.append(LineItem.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("variantMMId");
        sb.append('=');
        sb.append(((this.variantMMId == null)?"<null>":this.variantMMId));
        sb.append(',');
        sb.append("uom");
        sb.append('=');
        sb.append(((this.uom == null)?"<null>":this.uom));
        sb.append(',');
        sb.append("taxId");
        sb.append('=');
        sb.append(((this.taxId == null)?"<null>":this.taxId));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("lineItemPrice");
        sb.append('=');
        sb.append(((this.lineItemPrice == null)?"<null>":this.lineItemPrice));
        sb.append(',');
        sb.append("lineItemQty");
        sb.append('=');
        sb.append(((this.lineItemQty == null)?"<null>":this.lineItemQty));
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
        result = ((result* 31)+((this.uom == null)? 0 :this.uom.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.lineItemPrice == null)? 0 :this.lineItemPrice.hashCode()));
        result = ((result* 31)+((this.taxId == null)? 0 :this.taxId.hashCode()));
        result = ((result* 31)+((this.variantMMId == null)? 0 :this.variantMMId.hashCode()));
        result = ((result* 31)+((this.lineItemQty == null)? 0 :this.lineItemQty.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItem) == false) {
            return false;
        }
        LineItem rhs = ((LineItem) other);
        return ((((((((this.uom == rhs.uom)||((this.uom!= null)&&this.uom.equals(rhs.uom)))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.lineItemPrice == rhs.lineItemPrice)||((this.lineItemPrice!= null)&&this.lineItemPrice.equals(rhs.lineItemPrice))))&&((this.taxId == rhs.taxId)||((this.taxId!= null)&&this.taxId.equals(rhs.taxId))))&&((this.variantMMId == rhs.variantMMId)||((this.variantMMId!= null)&&this.variantMMId.equals(rhs.variantMMId))))&&((this.lineItemQty == rhs.lineItemQty)||((this.lineItemQty!= null)&&this.lineItemQty.equals(rhs.lineItemQty))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
