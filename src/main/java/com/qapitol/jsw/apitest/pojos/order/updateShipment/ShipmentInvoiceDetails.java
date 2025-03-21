
package com.qapitol.jsw.apitest.pojos.order.updateShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "invoiceComments",
    "invoice",
    "invoicedBaseAmount",
    "invoicedTaxAmount",
    "invoiceOtherServicesSum",
    "invoiceOtherAmounts",
    "invoicePayoutHoldAmount",
    "payoutHoldType",
    "totalInvoicedAmount"
})
@Generated("jsonschema2pojo")
public class ShipmentInvoiceDetails {

    @JsonProperty("invoiceComments")
    private String invoiceComments;
    @JsonProperty("invoice")
    private Object invoice;
    @JsonProperty("invoicedBaseAmount")
    private Integer invoicedBaseAmount;
    @JsonProperty("invoicedTaxAmount")
    private Integer invoicedTaxAmount;
    @JsonProperty("invoiceOtherServicesSum")
    private Integer invoiceOtherServicesSum;
    @JsonProperty("invoiceOtherAmounts")
    private InvoiceOtherAmounts invoiceOtherAmounts;
    @JsonProperty("invoicePayoutHoldAmount")
    private Integer invoicePayoutHoldAmount;
    @JsonProperty("payoutHoldType")
    private String payoutHoldType;
    @JsonProperty("totalInvoicedAmount")
    private Integer totalInvoicedAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("invoiceComments")
    public String getInvoiceComments() {
        return invoiceComments;
    }

    @JsonProperty("invoiceComments")
    public void setInvoiceComments(String invoiceComments) {
        this.invoiceComments = invoiceComments;
    }

    @JsonProperty("invoice")
    public Object getInvoice() {
        return invoice;
    }

    @JsonProperty("invoice")
    public void setInvoice(Object invoice) {
        this.invoice = invoice;
    }

    @JsonProperty("invoicedBaseAmount")
    public Integer getInvoicedBaseAmount() {
        return invoicedBaseAmount;
    }

    @JsonProperty("invoicedBaseAmount")
    public void setInvoicedBaseAmount(Integer invoicedBaseAmount) {
        this.invoicedBaseAmount = invoicedBaseAmount;
    }

    @JsonProperty("invoicedTaxAmount")
    public Integer getInvoicedTaxAmount() {
        return invoicedTaxAmount;
    }

    @JsonProperty("invoicedTaxAmount")
    public void setInvoicedTaxAmount(Integer invoicedTaxAmount) {
        this.invoicedTaxAmount = invoicedTaxAmount;
    }

    @JsonProperty("invoiceOtherServicesSum")
    public Integer getInvoiceOtherServicesSum() {
        return invoiceOtherServicesSum;
    }

    @JsonProperty("invoiceOtherServicesSum")
    public void setInvoiceOtherServicesSum(Integer invoiceOtherServicesSum) {
        this.invoiceOtherServicesSum = invoiceOtherServicesSum;
    }

    @JsonProperty("invoiceOtherAmounts")
    public InvoiceOtherAmounts getInvoiceOtherAmounts() {
        return invoiceOtherAmounts;
    }

    @JsonProperty("invoiceOtherAmounts")
    public void setInvoiceOtherAmounts(InvoiceOtherAmounts invoiceOtherAmounts) {
        this.invoiceOtherAmounts = invoiceOtherAmounts;
    }

    @JsonProperty("invoicePayoutHoldAmount")
    public Integer getInvoicePayoutHoldAmount() {
        return invoicePayoutHoldAmount;
    }

    @JsonProperty("invoicePayoutHoldAmount")
    public void setInvoicePayoutHoldAmount(Integer invoicePayoutHoldAmount) {
        this.invoicePayoutHoldAmount = invoicePayoutHoldAmount;
    }

    @JsonProperty("payoutHoldType")
    public String getPayoutHoldType() {
        return payoutHoldType;
    }

    @JsonProperty("payoutHoldType")
    public void setPayoutHoldType(String payoutHoldType) {
        this.payoutHoldType = payoutHoldType;
    }

    @JsonProperty("totalInvoicedAmount")
    public Integer getTotalInvoicedAmount() {
        return totalInvoicedAmount;
    }

    @JsonProperty("totalInvoicedAmount")
    public void setTotalInvoicedAmount(Integer totalInvoicedAmount) {
        this.totalInvoicedAmount = totalInvoicedAmount;
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
        sb.append(ShipmentInvoiceDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("invoiceComments");
        sb.append('=');
        sb.append(((this.invoiceComments == null)?"<null>":this.invoiceComments));
        sb.append(',');
        sb.append("invoice");
        sb.append('=');
        sb.append(((this.invoice == null)?"<null>":this.invoice));
        sb.append(',');
        sb.append("invoicedBaseAmount");
        sb.append('=');
        sb.append(((this.invoicedBaseAmount == null)?"<null>":this.invoicedBaseAmount));
        sb.append(',');
        sb.append("invoicedTaxAmount");
        sb.append('=');
        sb.append(((this.invoicedTaxAmount == null)?"<null>":this.invoicedTaxAmount));
        sb.append(',');
        sb.append("invoiceOtherServicesSum");
        sb.append('=');
        sb.append(((this.invoiceOtherServicesSum == null)?"<null>":this.invoiceOtherServicesSum));
        sb.append(',');
        sb.append("invoiceOtherAmounts");
        sb.append('=');
        sb.append(((this.invoiceOtherAmounts == null)?"<null>":this.invoiceOtherAmounts));
        sb.append(',');
        sb.append("invoicePayoutHoldAmount");
        sb.append('=');
        sb.append(((this.invoicePayoutHoldAmount == null)?"<null>":this.invoicePayoutHoldAmount));
        sb.append(',');
        sb.append("payoutHoldType");
        sb.append('=');
        sb.append(((this.payoutHoldType == null)?"<null>":this.payoutHoldType));
        sb.append(',');
        sb.append("totalInvoicedAmount");
        sb.append('=');
        sb.append(((this.totalInvoicedAmount == null)?"<null>":this.totalInvoicedAmount));
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
        result = ((result* 31)+((this.invoiceOtherAmounts == null)? 0 :this.invoiceOtherAmounts.hashCode()));
        result = ((result* 31)+((this.invoicePayoutHoldAmount == null)? 0 :this.invoicePayoutHoldAmount.hashCode()));
        result = ((result* 31)+((this.payoutHoldType == null)? 0 :this.payoutHoldType.hashCode()));
        result = ((result* 31)+((this.invoiceOtherServicesSum == null)? 0 :this.invoiceOtherServicesSum.hashCode()));
        result = ((result* 31)+((this.totalInvoicedAmount == null)? 0 :this.totalInvoicedAmount.hashCode()));
        result = ((result* 31)+((this.invoicedBaseAmount == null)? 0 :this.invoicedBaseAmount.hashCode()));
        result = ((result* 31)+((this.invoice == null)? 0 :this.invoice.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.invoiceComments == null)? 0 :this.invoiceComments.hashCode()));
        result = ((result* 31)+((this.invoicedTaxAmount == null)? 0 :this.invoicedTaxAmount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipmentInvoiceDetails) == false) {
            return false;
        }
        ShipmentInvoiceDetails rhs = ((ShipmentInvoiceDetails) other);
        return (((((((((((this.invoiceOtherAmounts == rhs.invoiceOtherAmounts)||((this.invoiceOtherAmounts!= null)&&this.invoiceOtherAmounts.equals(rhs.invoiceOtherAmounts)))&&((this.invoicePayoutHoldAmount == rhs.invoicePayoutHoldAmount)||((this.invoicePayoutHoldAmount!= null)&&this.invoicePayoutHoldAmount.equals(rhs.invoicePayoutHoldAmount))))&&((this.payoutHoldType == rhs.payoutHoldType)||((this.payoutHoldType!= null)&&this.payoutHoldType.equals(rhs.payoutHoldType))))&&((this.invoiceOtherServicesSum == rhs.invoiceOtherServicesSum)||((this.invoiceOtherServicesSum!= null)&&this.invoiceOtherServicesSum.equals(rhs.invoiceOtherServicesSum))))&&((this.totalInvoicedAmount == rhs.totalInvoicedAmount)||((this.totalInvoicedAmount!= null)&&this.totalInvoicedAmount.equals(rhs.totalInvoicedAmount))))&&((this.invoicedBaseAmount == rhs.invoicedBaseAmount)||((this.invoicedBaseAmount!= null)&&this.invoicedBaseAmount.equals(rhs.invoicedBaseAmount))))&&((this.invoice == rhs.invoice)||((this.invoice!= null)&&this.invoice.equals(rhs.invoice))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.invoiceComments == rhs.invoiceComments)||((this.invoiceComments!= null)&&this.invoiceComments.equals(rhs.invoiceComments))))&&((this.invoicedTaxAmount == rhs.invoicedTaxAmount)||((this.invoicedTaxAmount!= null)&&this.invoicedTaxAmount.equals(rhs.invoicedTaxAmount))));
    }

}
