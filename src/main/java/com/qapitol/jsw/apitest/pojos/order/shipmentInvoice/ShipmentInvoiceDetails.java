
package com.qapitol.jsw.apitest.pojos.order.shipmentInvoice;

import com.fasterxml.jackson.annotation.*;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.InvoiceDetail;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.InvoiceOtherAmounts;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalInvoicedAmount",
    "invoicedBaseAmount",
    "invoicedTaxAmount",
    "igstamount",
    "cgstamount",
    "sgstamount",
    "invoicedTCSAmount",
    "invoiceOtherServicesSum",
    "invoiceOtherAmounts",
    "shipmentValue",
    "invoiceComments",
    "invoiceDetails",
    "payoutHoldType",
    "invoicePayoutHoldAmount"
})
@Generated("jsonschema2pojo")
public class ShipmentInvoiceDetails {

    @JsonProperty("totalInvoicedAmount")
    private Double totalInvoicedAmount;
    @JsonProperty("invoicedBaseAmount")
    private Integer invoicedBaseAmount;
    @JsonProperty("invoicedTaxAmount")
    private Double invoicedTaxAmount;
    @JsonProperty("igstamount")
    private Double igstamount;
    @JsonProperty("cgstamount")
    private Integer cgstamount;
    @JsonProperty("sgstamount")
    private Integer sgstamount;
    @JsonProperty("invoicedTCSAmount")
    private Integer invoicedTCSAmount;
    @JsonProperty("invoiceOtherServicesSum")
    private Integer invoiceOtherServicesSum;
    @JsonProperty("invoiceOtherAmounts")
    private InvoiceOtherAmounts invoiceOtherAmounts;
    @JsonProperty("shipmentValue")
    private Double shipmentValue;
    @JsonProperty("invoiceComments")
    private String invoiceComments;
    @JsonProperty("invoiceDetails")
    private List<InvoiceDetail> invoiceDetails = new ArrayList<InvoiceDetail>();
    @JsonProperty("payoutHoldType")
    private String payoutHoldType;
    @JsonProperty("invoicePayoutHoldAmount")
    private Integer invoicePayoutHoldAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("totalInvoicedAmount")
    public Double getTotalInvoicedAmount() {
        return totalInvoicedAmount;
    }

    @JsonProperty("totalInvoicedAmount")
    public void setTotalInvoicedAmount(Double totalInvoicedAmount) {
        this.totalInvoicedAmount = totalInvoicedAmount;
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
    public Double getInvoicedTaxAmount() {
        return invoicedTaxAmount;
    }

    @JsonProperty("invoicedTaxAmount")
    public void setInvoicedTaxAmount(Double invoicedTaxAmount) {
        this.invoicedTaxAmount = invoicedTaxAmount;
    }

    @JsonProperty("igstamount")
    public Double getIgstamount() {
        return igstamount;
    }

    @JsonProperty("igstamount")
    public void setIgstamount(Double igstamount) {
        this.igstamount = igstamount;
    }

    @JsonProperty("cgstamount")
    public Integer getCgstamount() {
        return cgstamount;
    }

    @JsonProperty("cgstamount")
    public void setCgstamount(Integer cgstamount) {
        this.cgstamount = cgstamount;
    }

    @JsonProperty("sgstamount")
    public Integer getSgstamount() {
        return sgstamount;
    }

    @JsonProperty("sgstamount")
    public void setSgstamount(Integer sgstamount) {
        this.sgstamount = sgstamount;
    }

    @JsonProperty("invoicedTCSAmount")
    public Integer getInvoicedTCSAmount() {
        return invoicedTCSAmount;
    }

    @JsonProperty("invoicedTCSAmount")
    public void setInvoicedTCSAmount(Integer invoicedTCSAmount) {
        this.invoicedTCSAmount = invoicedTCSAmount;
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

    @JsonProperty("shipmentValue")
    public Double getShipmentValue() {
        return shipmentValue;
    }

    @JsonProperty("shipmentValue")
    public void setShipmentValue(Double shipmentValue) {
        this.shipmentValue = shipmentValue;
    }

    @JsonProperty("invoiceComments")
    public String getInvoiceComments() {
        return invoiceComments;
    }

    @JsonProperty("invoiceComments")
    public void setInvoiceComments(String invoiceComments) {
        this.invoiceComments = invoiceComments;
    }

    @JsonProperty("invoiceDetails")
    public List<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    @JsonProperty("invoiceDetails")
    public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    @JsonProperty("payoutHoldType")
    public String getPayoutHoldType() {
        return payoutHoldType;
    }

    @JsonProperty("payoutHoldType")
    public void setPayoutHoldType(String payoutHoldType) {
        this.payoutHoldType = payoutHoldType;
    }

    @JsonProperty("invoicePayoutHoldAmount")
    public Integer getInvoicePayoutHoldAmount() {
        return invoicePayoutHoldAmount;
    }

    @JsonProperty("invoicePayoutHoldAmount")
    public void setInvoicePayoutHoldAmount(Integer invoicePayoutHoldAmount) {
        this.invoicePayoutHoldAmount = invoicePayoutHoldAmount;
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
        sb.append("totalInvoicedAmount");
        sb.append('=');
        sb.append(((this.totalInvoicedAmount == null)?"<null>":this.totalInvoicedAmount));
        sb.append(',');
        sb.append("invoicedBaseAmount");
        sb.append('=');
        sb.append(((this.invoicedBaseAmount == null)?"<null>":this.invoicedBaseAmount));
        sb.append(',');
        sb.append("invoicedTaxAmount");
        sb.append('=');
        sb.append(((this.invoicedTaxAmount == null)?"<null>":this.invoicedTaxAmount));
        sb.append(',');
        sb.append("igstamount");
        sb.append('=');
        sb.append(((this.igstamount == null)?"<null>":this.igstamount));
        sb.append(',');
        sb.append("cgstamount");
        sb.append('=');
        sb.append(((this.cgstamount == null)?"<null>":this.cgstamount));
        sb.append(',');
        sb.append("sgstamount");
        sb.append('=');
        sb.append(((this.sgstamount == null)?"<null>":this.sgstamount));
        sb.append(',');
        sb.append("invoicedTCSAmount");
        sb.append('=');
        sb.append(((this.invoicedTCSAmount == null)?"<null>":this.invoicedTCSAmount));
        sb.append(',');
        sb.append("invoiceOtherServicesSum");
        sb.append('=');
        sb.append(((this.invoiceOtherServicesSum == null)?"<null>":this.invoiceOtherServicesSum));
        sb.append(',');
        sb.append("invoiceOtherAmounts");
        sb.append('=');
        sb.append(((this.invoiceOtherAmounts == null)?"<null>":this.invoiceOtherAmounts));
        sb.append(',');
        sb.append("shipmentValue");
        sb.append('=');
        sb.append(((this.shipmentValue == null)?"<null>":this.shipmentValue));
        sb.append(',');
        sb.append("invoiceComments");
        sb.append('=');
        sb.append(((this.invoiceComments == null)?"<null>":this.invoiceComments));
        sb.append(',');
        sb.append("invoiceDetails");
        sb.append('=');
        sb.append(((this.invoiceDetails == null)?"<null>":this.invoiceDetails));
        sb.append(',');
        sb.append("payoutHoldType");
        sb.append('=');
        sb.append(((this.payoutHoldType == null)?"<null>":this.payoutHoldType));
        sb.append(',');
        sb.append("invoicePayoutHoldAmount");
        sb.append('=');
        sb.append(((this.invoicePayoutHoldAmount == null)?"<null>":this.invoicePayoutHoldAmount));
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
        result = ((result* 31)+((this.payoutHoldType == null)? 0 :this.payoutHoldType.hashCode()));
        result = ((result* 31)+((this.invoiceOtherServicesSum == null)? 0 :this.invoiceOtherServicesSum.hashCode()));
        result = ((result* 31)+((this.totalInvoicedAmount == null)? 0 :this.totalInvoicedAmount.hashCode()));
        result = ((result* 31)+((this.igstamount == null)? 0 :this.igstamount.hashCode()));
        result = ((result* 31)+((this.invoicedTCSAmount == null)? 0 :this.invoicedTCSAmount.hashCode()));
        result = ((result* 31)+((this.shipmentValue == null)? 0 :this.shipmentValue.hashCode()));
        result = ((result* 31)+((this.invoiceOtherAmounts == null)? 0 :this.invoiceOtherAmounts.hashCode()));
        result = ((result* 31)+((this.invoicePayoutHoldAmount == null)? 0 :this.invoicePayoutHoldAmount.hashCode()));
        result = ((result* 31)+((this.sgstamount == null)? 0 :this.sgstamount.hashCode()));
        result = ((result* 31)+((this.invoicedBaseAmount == null)? 0 :this.invoicedBaseAmount.hashCode()));
        result = ((result* 31)+((this.invoiceDetails == null)? 0 :this.invoiceDetails.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.invoiceComments == null)? 0 :this.invoiceComments.hashCode()));
        result = ((result* 31)+((this.cgstamount == null)? 0 :this.cgstamount.hashCode()));
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
        return ((((((((((((((((this.payoutHoldType == rhs.payoutHoldType)||((this.payoutHoldType!= null)&&this.payoutHoldType.equals(rhs.payoutHoldType)))&&((this.invoiceOtherServicesSum == rhs.invoiceOtherServicesSum)||((this.invoiceOtherServicesSum!= null)&&this.invoiceOtherServicesSum.equals(rhs.invoiceOtherServicesSum))))&&((this.totalInvoicedAmount == rhs.totalInvoicedAmount)||((this.totalInvoicedAmount!= null)&&this.totalInvoicedAmount.equals(rhs.totalInvoicedAmount))))&&((this.igstamount == rhs.igstamount)||((this.igstamount!= null)&&this.igstamount.equals(rhs.igstamount))))&&((this.invoicedTCSAmount == rhs.invoicedTCSAmount)||((this.invoicedTCSAmount!= null)&&this.invoicedTCSAmount.equals(rhs.invoicedTCSAmount))))&&((this.shipmentValue == rhs.shipmentValue)||((this.shipmentValue!= null)&&this.shipmentValue.equals(rhs.shipmentValue))))&&((this.invoiceOtherAmounts == rhs.invoiceOtherAmounts)||((this.invoiceOtherAmounts!= null)&&this.invoiceOtherAmounts.equals(rhs.invoiceOtherAmounts))))&&((this.invoicePayoutHoldAmount == rhs.invoicePayoutHoldAmount)||((this.invoicePayoutHoldAmount!= null)&&this.invoicePayoutHoldAmount.equals(rhs.invoicePayoutHoldAmount))))&&((this.sgstamount == rhs.sgstamount)||((this.sgstamount!= null)&&this.sgstamount.equals(rhs.sgstamount))))&&((this.invoicedBaseAmount == rhs.invoicedBaseAmount)||((this.invoicedBaseAmount!= null)&&this.invoicedBaseAmount.equals(rhs.invoicedBaseAmount))))&&((this.invoiceDetails == rhs.invoiceDetails)||((this.invoiceDetails!= null)&&this.invoiceDetails.equals(rhs.invoiceDetails))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.invoiceComments == rhs.invoiceComments)||((this.invoiceComments!= null)&&this.invoiceComments.equals(rhs.invoiceComments))))&&((this.cgstamount == rhs.cgstamount)||((this.cgstamount!= null)&&this.cgstamount.equals(rhs.cgstamount))))&&((this.invoicedTaxAmount == rhs.invoicedTaxAmount)||((this.invoicedTaxAmount!= null)&&this.invoicedTaxAmount.equals(rhs.invoicedTaxAmount))));
    }

}
