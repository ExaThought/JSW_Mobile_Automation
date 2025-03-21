
package com.qapitol.jsw.apitest.pojos.order.shipmentInvoice;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customerId",
    "customerPhoneNumber",
    "creditAmount",
    "creditDays",
    "customerOrderNo",
    "advanceStatus",
    "advanceAmount",
    "sapReferenceNumber",
    "shipmentNumber",
    "creditProgramName",
    "creditProgramReferenceId",
    "plannedDispatchDate",
    "actualDispatchDate",
    "actualDeliveryDate",
    "modeOfShipment",
    "customerNotes",
    "itemCount",
    "invoiceStatus",
    "vendorId",
    "invoiceDocumentLink",
    "lineItems",
    "customerBillingAddress",
    "customerShippingAddress",
    "sellerBillingAddress",
    "sellerShippingAddress",
    "shipmentInvoiceDetails",
    "reposted"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("customerPhoneNumber")
    private String customerPhoneNumber;
    @JsonProperty("creditAmount")
    private String creditAmount;
    @JsonProperty("creditDays")
    private Object creditDays;
    @JsonProperty("customerOrderNo")
    private String customerOrderNo;
    @JsonProperty("advanceStatus")
    private String advanceStatus;
    @JsonProperty("advanceAmount")
    private String advanceAmount;
    @JsonProperty("sapReferenceNumber")
    private Object sapReferenceNumber;
    @JsonProperty("shipmentNumber")
    private String shipmentNumber;
    @JsonProperty("creditProgramName")
    private Object creditProgramName;
    @JsonProperty("creditProgramReferenceId")
    private Object creditProgramReferenceId;
    @JsonProperty("plannedDispatchDate")
    private String plannedDispatchDate;
    @JsonProperty("actualDispatchDate")
    private String actualDispatchDate;
    @JsonProperty("actualDeliveryDate")
    private String actualDeliveryDate;
    @JsonProperty("modeOfShipment")
    private String modeOfShipment;
    @JsonProperty("customerNotes")
    private Object customerNotes;
    @JsonProperty("itemCount")
    private Integer itemCount;
    @JsonProperty("invoiceStatus")
    private String invoiceStatus;
    @JsonProperty("vendorId")
    private String vendorId;
    @JsonProperty("invoiceDocumentLink")
    private Object invoiceDocumentLink;
    @JsonProperty("lineItems")
    private List<LineItem> lineItems = new ArrayList<LineItem>();
    @JsonProperty("customerBillingAddress")
    private CustomerBillingAddress customerBillingAddress;
    @JsonProperty("customerShippingAddress")
    private CustomerShippingAddress customerShippingAddress;
    @JsonProperty("sellerBillingAddress")
    private SellerBillingAddress sellerBillingAddress;
    @JsonProperty("sellerShippingAddress")
    private SellerShippingAddress sellerShippingAddress;
    @JsonProperty("shipmentInvoiceDetails")
    private ShipmentInvoiceDetails shipmentInvoiceDetails;
    @JsonProperty("reposted")
    private String reposted;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("customerPhoneNumber")
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    @JsonProperty("customerPhoneNumber")
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    @JsonProperty("creditAmount")
    public String getCreditAmount() {
        return creditAmount;
    }

    @JsonProperty("creditAmount")
    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    @JsonProperty("creditDays")
    public Object getCreditDays() {
        return creditDays;
    }

    @JsonProperty("creditDays")
    public void setCreditDays(Object creditDays) {
        this.creditDays = creditDays;
    }

    @JsonProperty("customerOrderNo")
    public String getCustomerOrderNo() {
        return customerOrderNo;
    }

    @JsonProperty("customerOrderNo")
    public void setCustomerOrderNo(String customerOrderNo) {
        this.customerOrderNo = customerOrderNo;
    }

    @JsonProperty("advanceStatus")
    public String getAdvanceStatus() {
        return advanceStatus;
    }

    @JsonProperty("advanceStatus")
    public void setAdvanceStatus(String advanceStatus) {
        this.advanceStatus = advanceStatus;
    }

    @JsonProperty("advanceAmount")
    public String getAdvanceAmount() {
        return advanceAmount;
    }

    @JsonProperty("advanceAmount")
    public void setAdvanceAmount(String advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    @JsonProperty("sapReferenceNumber")
    public Object getSapReferenceNumber() {
        return sapReferenceNumber;
    }

    @JsonProperty("sapReferenceNumber")
    public void setSapReferenceNumber(Object sapReferenceNumber) {
        this.sapReferenceNumber = sapReferenceNumber;
    }

    @JsonProperty("shipmentNumber")
    public String getShipmentNumber() {
        return shipmentNumber;
    }

    @JsonProperty("shipmentNumber")
    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    @JsonProperty("creditProgramName")
    public Object getCreditProgramName() {
        return creditProgramName;
    }

    @JsonProperty("creditProgramName")
    public void setCreditProgramName(Object creditProgramName) {
        this.creditProgramName = creditProgramName;
    }

    @JsonProperty("creditProgramReferenceId")
    public Object getCreditProgramReferenceId() {
        return creditProgramReferenceId;
    }

    @JsonProperty("creditProgramReferenceId")
    public void setCreditProgramReferenceId(Object creditProgramReferenceId) {
        this.creditProgramReferenceId = creditProgramReferenceId;
    }

    @JsonProperty("plannedDispatchDate")
    public String getPlannedDispatchDate() {
        return plannedDispatchDate;
    }

    @JsonProperty("plannedDispatchDate")
    public void setPlannedDispatchDate(String plannedDispatchDate) {
        this.plannedDispatchDate = plannedDispatchDate;
    }

    @JsonProperty("actualDispatchDate")
    public String getActualDispatchDate() {
        return actualDispatchDate;
    }

    @JsonProperty("actualDispatchDate")
    public void setActualDispatchDate(String actualDispatchDate) {
        this.actualDispatchDate = actualDispatchDate;
    }

    @JsonProperty("actualDeliveryDate")
    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    @JsonProperty("actualDeliveryDate")
    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    @JsonProperty("modeOfShipment")
    public String getModeOfShipment() {
        return modeOfShipment;
    }

    @JsonProperty("modeOfShipment")
    public void setModeOfShipment(String modeOfShipment) {
        this.modeOfShipment = modeOfShipment;
    }

    @JsonProperty("customerNotes")
    public Object getCustomerNotes() {
        return customerNotes;
    }

    @JsonProperty("customerNotes")
    public void setCustomerNotes(Object customerNotes) {
        this.customerNotes = customerNotes;
    }

    @JsonProperty("itemCount")
    public Integer getItemCount() {
        return itemCount;
    }

    @JsonProperty("itemCount")
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    @JsonProperty("invoiceStatus")
    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    @JsonProperty("invoiceStatus")
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @JsonProperty("vendorId")
    public String getVendorId() {
        return vendorId;
    }

    @JsonProperty("vendorId")
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @JsonProperty("invoiceDocumentLink")
    public Object getInvoiceDocumentLink() {
        return invoiceDocumentLink;
    }

    @JsonProperty("invoiceDocumentLink")
    public void setInvoiceDocumentLink(Object invoiceDocumentLink) {
        this.invoiceDocumentLink = invoiceDocumentLink;
    }

    @JsonProperty("lineItems")
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    @JsonProperty("lineItems")
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @JsonProperty("customerBillingAddress")
    public CustomerBillingAddress getCustomerBillingAddress() {
        return customerBillingAddress;
    }

    @JsonProperty("customerBillingAddress")
    public void setCustomerBillingAddress(CustomerBillingAddress customerBillingAddress) {
        this.customerBillingAddress = customerBillingAddress;
    }

    @JsonProperty("customerShippingAddress")
    public CustomerShippingAddress getCustomerShippingAddress() {
        return customerShippingAddress;
    }

    @JsonProperty("customerShippingAddress")
    public void setCustomerShippingAddress(CustomerShippingAddress customerShippingAddress) {
        this.customerShippingAddress = customerShippingAddress;
    }

    @JsonProperty("sellerBillingAddress")
    public SellerBillingAddress getSellerBillingAddress() {
        return sellerBillingAddress;
    }

    @JsonProperty("sellerBillingAddress")
    public void setSellerBillingAddress(SellerBillingAddress sellerBillingAddress) {
        this.sellerBillingAddress = sellerBillingAddress;
    }

    @JsonProperty("sellerShippingAddress")
    public SellerShippingAddress getSellerShippingAddress() {
        return sellerShippingAddress;
    }

    @JsonProperty("sellerShippingAddress")
    public void setSellerShippingAddress(SellerShippingAddress sellerShippingAddress) {
        this.sellerShippingAddress = sellerShippingAddress;
    }

    @JsonProperty("shipmentInvoiceDetails")
    public ShipmentInvoiceDetails getShipmentInvoiceDetails() {
        return shipmentInvoiceDetails;
    }

    @JsonProperty("shipmentInvoiceDetails")
    public void setShipmentInvoiceDetails(ShipmentInvoiceDetails shipmentInvoiceDetails) {
        this.shipmentInvoiceDetails = shipmentInvoiceDetails;
    }

    @JsonProperty("reposted")
    public String getReposted() {
        return reposted;
    }

    @JsonProperty("reposted")
    public void setReposted(String reposted) {
        this.reposted = reposted;
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
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerId");
        sb.append('=');
        sb.append(((this.customerId == null)?"<null>":this.customerId));
        sb.append(',');
        sb.append("customerPhoneNumber");
        sb.append('=');
        sb.append(((this.customerPhoneNumber == null)?"<null>":this.customerPhoneNumber));
        sb.append(',');
        sb.append("creditAmount");
        sb.append('=');
        sb.append(((this.creditAmount == null)?"<null>":this.creditAmount));
        sb.append(',');
        sb.append("creditDays");
        sb.append('=');
        sb.append(((this.creditDays == null)?"<null>":this.creditDays));
        sb.append(',');
        sb.append("customerOrderNo");
        sb.append('=');
        sb.append(((this.customerOrderNo == null)?"<null>":this.customerOrderNo));
        sb.append(',');
        sb.append("advanceStatus");
        sb.append('=');
        sb.append(((this.advanceStatus == null)?"<null>":this.advanceStatus));
        sb.append(',');
        sb.append("advanceAmount");
        sb.append('=');
        sb.append(((this.advanceAmount == null)?"<null>":this.advanceAmount));
        sb.append(',');
        sb.append("sapReferenceNumber");
        sb.append('=');
        sb.append(((this.sapReferenceNumber == null)?"<null>":this.sapReferenceNumber));
        sb.append(',');
        sb.append("shipmentNumber");
        sb.append('=');
        sb.append(((this.shipmentNumber == null)?"<null>":this.shipmentNumber));
        sb.append(',');
        sb.append("creditProgramName");
        sb.append('=');
        sb.append(((this.creditProgramName == null)?"<null>":this.creditProgramName));
        sb.append(',');
        sb.append("creditProgramReferenceId");
        sb.append('=');
        sb.append(((this.creditProgramReferenceId == null)?"<null>":this.creditProgramReferenceId));
        sb.append(',');
        sb.append("plannedDispatchDate");
        sb.append('=');
        sb.append(((this.plannedDispatchDate == null)?"<null>":this.plannedDispatchDate));
        sb.append(',');
        sb.append("actualDispatchDate");
        sb.append('=');
        sb.append(((this.actualDispatchDate == null)?"<null>":this.actualDispatchDate));
        sb.append(',');
        sb.append("actualDeliveryDate");
        sb.append('=');
        sb.append(((this.actualDeliveryDate == null)?"<null>":this.actualDeliveryDate));
        sb.append(',');
        sb.append("modeOfShipment");
        sb.append('=');
        sb.append(((this.modeOfShipment == null)?"<null>":this.modeOfShipment));
        sb.append(',');
        sb.append("customerNotes");
        sb.append('=');
        sb.append(((this.customerNotes == null)?"<null>":this.customerNotes));
        sb.append(',');
        sb.append("itemCount");
        sb.append('=');
        sb.append(((this.itemCount == null)?"<null>":this.itemCount));
        sb.append(',');
        sb.append("invoiceStatus");
        sb.append('=');
        sb.append(((this.invoiceStatus == null)?"<null>":this.invoiceStatus));
        sb.append(',');
        sb.append("vendorId");
        sb.append('=');
        sb.append(((this.vendorId == null)?"<null>":this.vendorId));
        sb.append(',');
        sb.append("invoiceDocumentLink");
        sb.append('=');
        sb.append(((this.invoiceDocumentLink == null)?"<null>":this.invoiceDocumentLink));
        sb.append(',');
        sb.append("lineItems");
        sb.append('=');
        sb.append(((this.lineItems == null)?"<null>":this.lineItems));
        sb.append(',');
        sb.append("customerBillingAddress");
        sb.append('=');
        sb.append(((this.customerBillingAddress == null)?"<null>":this.customerBillingAddress));
        sb.append(',');
        sb.append("customerShippingAddress");
        sb.append('=');
        sb.append(((this.customerShippingAddress == null)?"<null>":this.customerShippingAddress));
        sb.append(',');
        sb.append("sellerBillingAddress");
        sb.append('=');
        sb.append(((this.sellerBillingAddress == null)?"<null>":this.sellerBillingAddress));
        sb.append(',');
        sb.append("sellerShippingAddress");
        sb.append('=');
        sb.append(((this.sellerShippingAddress == null)?"<null>":this.sellerShippingAddress));
        sb.append(',');
        sb.append("shipmentInvoiceDetails");
        sb.append('=');
        sb.append(((this.shipmentInvoiceDetails == null)?"<null>":this.shipmentInvoiceDetails));
        sb.append(',');
        sb.append("reposted");
        sb.append('=');
        sb.append(((this.reposted == null)?"<null>":this.reposted));
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
        result = ((result* 31)+((this.sellerShippingAddress == null)? 0 :this.sellerShippingAddress.hashCode()));
        result = ((result* 31)+((this.advanceAmount == null)? 0 :this.advanceAmount.hashCode()));
        result = ((result* 31)+((this.invoiceDocumentLink == null)? 0 :this.invoiceDocumentLink.hashCode()));
        result = ((result* 31)+((this.vendorId == null)? 0 :this.vendorId.hashCode()));
        result = ((result* 31)+((this.customerNotes == null)? 0 :this.customerNotes.hashCode()));
        result = ((result* 31)+((this.modeOfShipment == null)? 0 :this.modeOfShipment.hashCode()));
        result = ((result* 31)+((this.creditProgramName == null)? 0 :this.creditProgramName.hashCode()));
        result = ((result* 31)+((this.lineItems == null)? 0 :this.lineItems.hashCode()));
        result = ((result* 31)+((this.customerBillingAddress == null)? 0 :this.customerBillingAddress.hashCode()));
        result = ((result* 31)+((this.customerId == null)? 0 :this.customerId.hashCode()));
        result = ((result* 31)+((this.advanceStatus == null)? 0 :this.advanceStatus.hashCode()));
        result = ((result* 31)+((this.sapReferenceNumber == null)? 0 :this.sapReferenceNumber.hashCode()));
        result = ((result* 31)+((this.shipmentNumber == null)? 0 :this.shipmentNumber.hashCode()));
        result = ((result* 31)+((this.plannedDispatchDate == null)? 0 :this.plannedDispatchDate.hashCode()));
        result = ((result* 31)+((this.actualDeliveryDate == null)? 0 :this.actualDeliveryDate.hashCode()));
        result = ((result* 31)+((this.creditDays == null)? 0 :this.creditDays.hashCode()));
        result = ((result* 31)+((this.customerShippingAddress == null)? 0 :this.customerShippingAddress.hashCode()));
        result = ((result* 31)+((this.itemCount == null)? 0 :this.itemCount.hashCode()));
        result = ((result* 31)+((this.creditProgramReferenceId == null)? 0 :this.creditProgramReferenceId.hashCode()));
        result = ((result* 31)+((this.sellerBillingAddress == null)? 0 :this.sellerBillingAddress.hashCode()));
        result = ((result* 31)+((this.actualDispatchDate == null)? 0 :this.actualDispatchDate.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.creditAmount == null)? 0 :this.creditAmount.hashCode()));
        result = ((result* 31)+((this.invoiceStatus == null)? 0 :this.invoiceStatus.hashCode()));
        result = ((result* 31)+((this.shipmentInvoiceDetails == null)? 0 :this.shipmentInvoiceDetails.hashCode()));
        result = ((result* 31)+((this.customerPhoneNumber == null)? 0 :this.customerPhoneNumber.hashCode()));
        result = ((result* 31)+((this.customerOrderNo == null)? 0 :this.customerOrderNo.hashCode()));
        result = ((result* 31)+((this.reposted == null)? 0 :this.reposted.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return (((((((((((((((((((((((((((((this.sellerShippingAddress == rhs.sellerShippingAddress)||((this.sellerShippingAddress!= null)&&this.sellerShippingAddress.equals(rhs.sellerShippingAddress)))&&((this.advanceAmount == rhs.advanceAmount)||((this.advanceAmount!= null)&&this.advanceAmount.equals(rhs.advanceAmount))))&&((this.invoiceDocumentLink == rhs.invoiceDocumentLink)||((this.invoiceDocumentLink!= null)&&this.invoiceDocumentLink.equals(rhs.invoiceDocumentLink))))&&((this.vendorId == rhs.vendorId)||((this.vendorId!= null)&&this.vendorId.equals(rhs.vendorId))))&&((this.customerNotes == rhs.customerNotes)||((this.customerNotes!= null)&&this.customerNotes.equals(rhs.customerNotes))))&&((this.modeOfShipment == rhs.modeOfShipment)||((this.modeOfShipment!= null)&&this.modeOfShipment.equals(rhs.modeOfShipment))))&&((this.creditProgramName == rhs.creditProgramName)||((this.creditProgramName!= null)&&this.creditProgramName.equals(rhs.creditProgramName))))&&((this.lineItems == rhs.lineItems)||((this.lineItems!= null)&&this.lineItems.equals(rhs.lineItems))))&&((this.customerBillingAddress == rhs.customerBillingAddress)||((this.customerBillingAddress!= null)&&this.customerBillingAddress.equals(rhs.customerBillingAddress))))&&((this.customerId == rhs.customerId)||((this.customerId!= null)&&this.customerId.equals(rhs.customerId))))&&((this.advanceStatus == rhs.advanceStatus)||((this.advanceStatus!= null)&&this.advanceStatus.equals(rhs.advanceStatus))))&&((this.sapReferenceNumber == rhs.sapReferenceNumber)||((this.sapReferenceNumber!= null)&&this.sapReferenceNumber.equals(rhs.sapReferenceNumber))))&&((this.shipmentNumber == rhs.shipmentNumber)||((this.shipmentNumber!= null)&&this.shipmentNumber.equals(rhs.shipmentNumber))))&&((this.plannedDispatchDate == rhs.plannedDispatchDate)||((this.plannedDispatchDate!= null)&&this.plannedDispatchDate.equals(rhs.plannedDispatchDate))))&&((this.actualDeliveryDate == rhs.actualDeliveryDate)||((this.actualDeliveryDate!= null)&&this.actualDeliveryDate.equals(rhs.actualDeliveryDate))))&&((this.creditDays == rhs.creditDays)||((this.creditDays!= null)&&this.creditDays.equals(rhs.creditDays))))&&((this.customerShippingAddress == rhs.customerShippingAddress)||((this.customerShippingAddress!= null)&&this.customerShippingAddress.equals(rhs.customerShippingAddress))))&&((this.itemCount == rhs.itemCount)||((this.itemCount!= null)&&this.itemCount.equals(rhs.itemCount))))&&((this.creditProgramReferenceId == rhs.creditProgramReferenceId)||((this.creditProgramReferenceId!= null)&&this.creditProgramReferenceId.equals(rhs.creditProgramReferenceId))))&&((this.sellerBillingAddress == rhs.sellerBillingAddress)||((this.sellerBillingAddress!= null)&&this.sellerBillingAddress.equals(rhs.sellerBillingAddress))))&&((this.actualDispatchDate == rhs.actualDispatchDate)||((this.actualDispatchDate!= null)&&this.actualDispatchDate.equals(rhs.actualDispatchDate))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.creditAmount == rhs.creditAmount)||((this.creditAmount!= null)&&this.creditAmount.equals(rhs.creditAmount))))&&((this.invoiceStatus == rhs.invoiceStatus)||((this.invoiceStatus!= null)&&this.invoiceStatus.equals(rhs.invoiceStatus))))&&((this.shipmentInvoiceDetails == rhs.shipmentInvoiceDetails)||((this.shipmentInvoiceDetails!= null)&&this.shipmentInvoiceDetails.equals(rhs.shipmentInvoiceDetails))))&&((this.customerPhoneNumber == rhs.customerPhoneNumber)||((this.customerPhoneNumber!= null)&&this.customerPhoneNumber.equals(rhs.customerPhoneNumber))))&&((this.customerOrderNo == rhs.customerOrderNo)||((this.customerOrderNo!= null)&&this.customerOrderNo.equals(rhs.customerOrderNo))))&&((this.reposted == rhs.reposted)||((this.reposted!= null)&&this.reposted.equals(rhs.reposted))));
    }

}
