
package com.qapitol.jsw.apitest.pojos.order.updateShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "number",
    "currentState",
    "actualDispatchDate",
    "actualDeliveryDate",
    "shipmentInvoiceDetails",
    "deliveryType",
    "modeOfShipment",
    "sellerOrderId",
    "payoutRejectedCount",
    "vehicleDetails",
    "sellerGstin",
    "lineItemsDetails",
    "customerOrderId",
    "customerOrderNumber",
    "invoiceDate",
    "invoiceDueDate",
    "previousState",
    "category",
    "shippingAddress",
    "netLedgerBalance",
    "customerBalanceAmount",
    "blockedLedgerAmount",
    "customerGstin",
    "customerId",
    "orderPaymentType",
    "shipmentInvoiceLedgerPayoutEntriesFlag"
})
@Generated("jsonschema2pojo")
public class UpdateShipmentResp {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("number")
    private String number;
    @JsonProperty("currentState")
    private CurrentState currentState;
    @JsonProperty("actualDispatchDate")
    private String actualDispatchDate;
    @JsonProperty("actualDeliveryDate")
    private String actualDeliveryDate;
    @JsonProperty("shipmentInvoiceDetails")
    private ShipmentInvoiceDetailsResp shipmentInvoiceDetails;
    @JsonProperty("deliveryType")
    private DeliveryType deliveryType;
    @JsonProperty("modeOfShipment")
    private String modeOfShipment;
    @JsonProperty("sellerOrderId")
    private Integer sellerOrderId;
    @JsonProperty("payoutRejectedCount")
    private Integer payoutRejectedCount;
    @JsonProperty("vehicleDetails")
    private VehicleDetails vehicleDetails;
    @JsonProperty("sellerGstin")
    private String sellerGstin;
    @JsonProperty("lineItemsDetails")
    private List<Object> lineItemsDetails = new ArrayList<Object>();
    @JsonProperty("customerOrderId")
    private Integer customerOrderId;
    @JsonProperty("customerOrderNumber")
    private String customerOrderNumber;
    @JsonProperty("invoiceDate")
    private String invoiceDate;
    @JsonProperty("invoiceDueDate")
    private String invoiceDueDate;
    @JsonProperty("previousState")
    private PreviousState previousState;
    @JsonProperty("category")
    private String category;
    @JsonProperty("shippingAddress")
    private ShippingAddress shippingAddress;
    @JsonProperty("netLedgerBalance")
    private NetLedgerBalance netLedgerBalance;
    @JsonProperty("customerBalanceAmount")
    private CustomerBalanceAmount customerBalanceAmount;
    @JsonProperty("blockedLedgerAmount")
    private BlockedLedgerAmount blockedLedgerAmount;
    @JsonProperty("customerGstin")
    private String customerGstin;
    @JsonProperty("customerId")
    private Integer customerId;
    @JsonProperty("orderPaymentType")
    private String orderPaymentType;
    @JsonProperty("shipmentInvoiceLedgerPayoutEntriesFlag")
    private Boolean shipmentInvoiceLedgerPayoutEntriesFlag;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("currentState")
    public CurrentState getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
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

    @JsonProperty("shipmentInvoiceDetails")
    public ShipmentInvoiceDetailsResp getShipmentInvoiceDetails() {
        return shipmentInvoiceDetails;
    }

    @JsonProperty("shipmentInvoiceDetails")
    public void setShipmentInvoiceDetails(ShipmentInvoiceDetailsResp shipmentInvoiceDetails) {
        this.shipmentInvoiceDetails = shipmentInvoiceDetails;
    }

    @JsonProperty("deliveryType")
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonProperty("modeOfShipment")
    public String getModeOfShipment() {
        return modeOfShipment;
    }

    @JsonProperty("modeOfShipment")
    public void setModeOfShipment(String modeOfShipment) {
        this.modeOfShipment = modeOfShipment;
    }

    @JsonProperty("sellerOrderId")
    public Integer getSellerOrderId() {
        return sellerOrderId;
    }

    @JsonProperty("sellerOrderId")
    public void setSellerOrderId(Integer sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    @JsonProperty("payoutRejectedCount")
    public Integer getPayoutRejectedCount() {
        return payoutRejectedCount;
    }

    @JsonProperty("payoutRejectedCount")
    public void setPayoutRejectedCount(Integer payoutRejectedCount) {
        this.payoutRejectedCount = payoutRejectedCount;
    }

    @JsonProperty("vehicleDetails")
    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    @JsonProperty("vehicleDetails")
    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    @JsonProperty("sellerGstin")
    public String getSellerGstin() {
        return sellerGstin;
    }

    @JsonProperty("sellerGstin")
    public void setSellerGstin(String sellerGstin) {
        this.sellerGstin = sellerGstin;
    }

    @JsonProperty("lineItemsDetails")
    public List<Object> getLineItemsDetails() {
        return lineItemsDetails;
    }

    @JsonProperty("lineItemsDetails")
    public void setLineItemsDetails(List<Object> lineItemsDetails) {
        this.lineItemsDetails = lineItemsDetails;
    }

    @JsonProperty("customerOrderId")
    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    @JsonProperty("customerOrderId")
    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    @JsonProperty("customerOrderNumber")
    public String getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    @JsonProperty("customerOrderNumber")
    public void setCustomerOrderNumber(String customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    @JsonProperty("invoiceDate")
    public String getInvoiceDate() {
        return invoiceDate;
    }

    @JsonProperty("invoiceDate")
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @JsonProperty("invoiceDueDate")
    public String getInvoiceDueDate() {
        return invoiceDueDate;
    }

    @JsonProperty("invoiceDueDate")
    public void setInvoiceDueDate(String invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    @JsonProperty("previousState")
    public PreviousState getPreviousState() {
        return previousState;
    }

    @JsonProperty("previousState")
    public void setPreviousState(PreviousState previousState) {
        this.previousState = previousState;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("shippingAddress")
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    @JsonProperty("shippingAddress")
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @JsonProperty("netLedgerBalance")
    public NetLedgerBalance getNetLedgerBalance() {
        return netLedgerBalance;
    }

    @JsonProperty("netLedgerBalance")
    public void setNetLedgerBalance(NetLedgerBalance netLedgerBalance) {
        this.netLedgerBalance = netLedgerBalance;
    }

    @JsonProperty("customerBalanceAmount")
    public CustomerBalanceAmount getCustomerBalanceAmount() {
        return customerBalanceAmount;
    }

    @JsonProperty("customerBalanceAmount")
    public void setCustomerBalanceAmount(CustomerBalanceAmount customerBalanceAmount) {
        this.customerBalanceAmount = customerBalanceAmount;
    }

    @JsonProperty("blockedLedgerAmount")
    public BlockedLedgerAmount getBlockedLedgerAmount() {
        return blockedLedgerAmount;
    }

    @JsonProperty("blockedLedgerAmount")
    public void setBlockedLedgerAmount(BlockedLedgerAmount blockedLedgerAmount) {
        this.blockedLedgerAmount = blockedLedgerAmount;
    }

    @JsonProperty("customerGstin")
    public String getCustomerGstin() {
        return customerGstin;
    }

    @JsonProperty("customerGstin")
    public void setCustomerGstin(String customerGstin) {
        this.customerGstin = customerGstin;
    }

    @JsonProperty("customerId")
    public Integer getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("orderPaymentType")
    public String getOrderPaymentType() {
        return orderPaymentType;
    }

    @JsonProperty("orderPaymentType")
    public void setOrderPaymentType(String orderPaymentType) {
        this.orderPaymentType = orderPaymentType;
    }

    @JsonProperty("shipmentInvoiceLedgerPayoutEntriesFlag")
    public Boolean getShipmentInvoiceLedgerPayoutEntriesFlag() {
        return shipmentInvoiceLedgerPayoutEntriesFlag;
    }

    @JsonProperty("shipmentInvoiceLedgerPayoutEntriesFlag")
    public void setShipmentInvoiceLedgerPayoutEntriesFlag(Boolean shipmentInvoiceLedgerPayoutEntriesFlag) {
        this.shipmentInvoiceLedgerPayoutEntriesFlag = shipmentInvoiceLedgerPayoutEntriesFlag;
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
        sb.append(UpdateShipmentResp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("number");
        sb.append('=');
        sb.append(((this.number == null)?"<null>":this.number));
        sb.append(',');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
        sb.append(',');
        sb.append("actualDispatchDate");
        sb.append('=');
        sb.append(((this.actualDispatchDate == null)?"<null>":this.actualDispatchDate));
        sb.append(',');
        sb.append("actualDeliveryDate");
        sb.append('=');
        sb.append(((this.actualDeliveryDate == null)?"<null>":this.actualDeliveryDate));
        sb.append(',');
        sb.append("shipmentInvoiceDetails");
        sb.append('=');
        sb.append(((this.shipmentInvoiceDetails == null)?"<null>":this.shipmentInvoiceDetails));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("modeOfShipment");
        sb.append('=');
        sb.append(((this.modeOfShipment == null)?"<null>":this.modeOfShipment));
        sb.append(',');
        sb.append("sellerOrderId");
        sb.append('=');
        sb.append(((this.sellerOrderId == null)?"<null>":this.sellerOrderId));
        sb.append(',');
        sb.append("payoutRejectedCount");
        sb.append('=');
        sb.append(((this.payoutRejectedCount == null)?"<null>":this.payoutRejectedCount));
        sb.append(',');
        sb.append("vehicleDetails");
        sb.append('=');
        sb.append(((this.vehicleDetails == null)?"<null>":this.vehicleDetails));
        sb.append(',');
        sb.append("sellerGstin");
        sb.append('=');
        sb.append(((this.sellerGstin == null)?"<null>":this.sellerGstin));
        sb.append(',');
        sb.append("lineItemsDetails");
        sb.append('=');
        sb.append(((this.lineItemsDetails == null)?"<null>":this.lineItemsDetails));
        sb.append(',');
        sb.append("customerOrderId");
        sb.append('=');
        sb.append(((this.customerOrderId == null)?"<null>":this.customerOrderId));
        sb.append(',');
        sb.append("customerOrderNumber");
        sb.append('=');
        sb.append(((this.customerOrderNumber == null)?"<null>":this.customerOrderNumber));
        sb.append(',');
        sb.append("invoiceDate");
        sb.append('=');
        sb.append(((this.invoiceDate == null)?"<null>":this.invoiceDate));
        sb.append(',');
        sb.append("invoiceDueDate");
        sb.append('=');
        sb.append(((this.invoiceDueDate == null)?"<null>":this.invoiceDueDate));
        sb.append(',');
        sb.append("previousState");
        sb.append('=');
        sb.append(((this.previousState == null)?"<null>":this.previousState));
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("shippingAddress");
        sb.append('=');
        sb.append(((this.shippingAddress == null)?"<null>":this.shippingAddress));
        sb.append(',');
        sb.append("netLedgerBalance");
        sb.append('=');
        sb.append(((this.netLedgerBalance == null)?"<null>":this.netLedgerBalance));
        sb.append(',');
        sb.append("customerBalanceAmount");
        sb.append('=');
        sb.append(((this.customerBalanceAmount == null)?"<null>":this.customerBalanceAmount));
        sb.append(',');
        sb.append("blockedLedgerAmount");
        sb.append('=');
        sb.append(((this.blockedLedgerAmount == null)?"<null>":this.blockedLedgerAmount));
        sb.append(',');
        sb.append("customerGstin");
        sb.append('=');
        sb.append(((this.customerGstin == null)?"<null>":this.customerGstin));
        sb.append(',');
        sb.append("customerId");
        sb.append('=');
        sb.append(((this.customerId == null)?"<null>":this.customerId));
        sb.append(',');
        sb.append("orderPaymentType");
        sb.append('=');
        sb.append(((this.orderPaymentType == null)?"<null>":this.orderPaymentType));
        sb.append(',');
        sb.append("shipmentInvoiceLedgerPayoutEntriesFlag");
        sb.append('=');
        sb.append(((this.shipmentInvoiceLedgerPayoutEntriesFlag == null)?"<null>":this.shipmentInvoiceLedgerPayoutEntriesFlag));
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
        result = ((result* 31)+((this.customerOrderId == null)? 0 :this.customerOrderId.hashCode()));
        result = ((result* 31)+((this.customerGstin == null)? 0 :this.customerGstin.hashCode()));
        result = ((result* 31)+((this.modeOfShipment == null)? 0 :this.modeOfShipment.hashCode()));
        result = ((result* 31)+((this.blockedLedgerAmount == null)? 0 :this.blockedLedgerAmount.hashCode()));
        result = ((result* 31)+((this.customerOrderNumber == null)? 0 :this.customerOrderNumber.hashCode()));
        result = ((result* 31)+((this.orderPaymentType == null)? 0 :this.orderPaymentType.hashCode()));
        result = ((result* 31)+((this.number == null)? 0 :this.number.hashCode()));
        result = ((result* 31)+((this.payoutRejectedCount == null)? 0 :this.payoutRejectedCount.hashCode()));
        result = ((result* 31)+((this.lineItemsDetails == null)? 0 :this.lineItemsDetails.hashCode()));
        result = ((result* 31)+((this.customerId == null)? 0 :this.customerId.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.actualDeliveryDate == null)? 0 :this.actualDeliveryDate.hashCode()));
        result = ((result* 31)+((this.vehicleDetails == null)? 0 :this.vehicleDetails.hashCode()));
        result = ((result* 31)+((this.customerBalanceAmount == null)? 0 :this.customerBalanceAmount.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.invoiceDate == null)? 0 :this.invoiceDate.hashCode()));
        result = ((result* 31)+((this.invoiceDueDate == null)? 0 :this.invoiceDueDate.hashCode()));
        result = ((result* 31)+((this.previousState == null)? 0 :this.previousState.hashCode()));
        result = ((result* 31)+((this.netLedgerBalance == null)? 0 :this.netLedgerBalance.hashCode()));
        result = ((result* 31)+((this.actualDispatchDate == null)? 0 :this.actualDispatchDate.hashCode()));
        result = ((result* 31)+((this.shippingAddress == null)? 0 :this.shippingAddress.hashCode()));
        result = ((result* 31)+((this.shipmentInvoiceLedgerPayoutEntriesFlag == null)? 0 :this.shipmentInvoiceLedgerPayoutEntriesFlag.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        result = ((result* 31)+((this.shipmentInvoiceDetails == null)? 0 :this.shipmentInvoiceDetails.hashCode()));
        result = ((result* 31)+((this.category == null)? 0 :this.category.hashCode()));
        result = ((result* 31)+((this.sellerOrderId == null)? 0 :this.sellerOrderId.hashCode()));
        result = ((result* 31)+((this.sellerGstin == null)? 0 :this.sellerGstin.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UpdateShipmentResp) == false) {
            return false;
        }
        UpdateShipmentResp rhs = ((UpdateShipmentResp) other);
        return (((((((((((((((((((((((((((((this.customerOrderId == rhs.customerOrderId)||((this.customerOrderId!= null)&&this.customerOrderId.equals(rhs.customerOrderId)))&&((this.customerGstin == rhs.customerGstin)||((this.customerGstin!= null)&&this.customerGstin.equals(rhs.customerGstin))))&&((this.modeOfShipment == rhs.modeOfShipment)||((this.modeOfShipment!= null)&&this.modeOfShipment.equals(rhs.modeOfShipment))))&&((this.blockedLedgerAmount == rhs.blockedLedgerAmount)||((this.blockedLedgerAmount!= null)&&this.blockedLedgerAmount.equals(rhs.blockedLedgerAmount))))&&((this.customerOrderNumber == rhs.customerOrderNumber)||((this.customerOrderNumber!= null)&&this.customerOrderNumber.equals(rhs.customerOrderNumber))))&&((this.orderPaymentType == rhs.orderPaymentType)||((this.orderPaymentType!= null)&&this.orderPaymentType.equals(rhs.orderPaymentType))))&&((this.number == rhs.number)||((this.number!= null)&&this.number.equals(rhs.number))))&&((this.payoutRejectedCount == rhs.payoutRejectedCount)||((this.payoutRejectedCount!= null)&&this.payoutRejectedCount.equals(rhs.payoutRejectedCount))))&&((this.lineItemsDetails == rhs.lineItemsDetails)||((this.lineItemsDetails!= null)&&this.lineItemsDetails.equals(rhs.lineItemsDetails))))&&((this.customerId == rhs.customerId)||((this.customerId!= null)&&this.customerId.equals(rhs.customerId))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.actualDeliveryDate == rhs.actualDeliveryDate)||((this.actualDeliveryDate!= null)&&this.actualDeliveryDate.equals(rhs.actualDeliveryDate))))&&((this.vehicleDetails == rhs.vehicleDetails)||((this.vehicleDetails!= null)&&this.vehicleDetails.equals(rhs.vehicleDetails))))&&((this.customerBalanceAmount == rhs.customerBalanceAmount)||((this.customerBalanceAmount!= null)&&this.customerBalanceAmount.equals(rhs.customerBalanceAmount))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.invoiceDate == rhs.invoiceDate)||((this.invoiceDate!= null)&&this.invoiceDate.equals(rhs.invoiceDate))))&&((this.invoiceDueDate == rhs.invoiceDueDate)||((this.invoiceDueDate!= null)&&this.invoiceDueDate.equals(rhs.invoiceDueDate))))&&((this.previousState == rhs.previousState)||((this.previousState!= null)&&this.previousState.equals(rhs.previousState))))&&((this.netLedgerBalance == rhs.netLedgerBalance)||((this.netLedgerBalance!= null)&&this.netLedgerBalance.equals(rhs.netLedgerBalance))))&&((this.actualDispatchDate == rhs.actualDispatchDate)||((this.actualDispatchDate!= null)&&this.actualDispatchDate.equals(rhs.actualDispatchDate))))&&((this.shippingAddress == rhs.shippingAddress)||((this.shippingAddress!= null)&&this.shippingAddress.equals(rhs.shippingAddress))))&&((this.shipmentInvoiceLedgerPayoutEntriesFlag == rhs.shipmentInvoiceLedgerPayoutEntriesFlag)||((this.shipmentInvoiceLedgerPayoutEntriesFlag!= null)&&this.shipmentInvoiceLedgerPayoutEntriesFlag.equals(rhs.shipmentInvoiceLedgerPayoutEntriesFlag))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))))&&((this.shipmentInvoiceDetails == rhs.shipmentInvoiceDetails)||((this.shipmentInvoiceDetails!= null)&&this.shipmentInvoiceDetails.equals(rhs.shipmentInvoiceDetails))))&&((this.category == rhs.category)||((this.category!= null)&&this.category.equals(rhs.category))))&&((this.sellerOrderId == rhs.sellerOrderId)||((this.sellerOrderId!= null)&&this.sellerOrderId.equals(rhs.sellerOrderId))))&&((this.sellerGstin == rhs.sellerGstin)||((this.sellerGstin!= null)&&this.sellerGstin.equals(rhs.sellerGstin))));
    }

}
