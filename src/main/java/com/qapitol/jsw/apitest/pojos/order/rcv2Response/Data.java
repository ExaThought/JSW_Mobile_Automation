
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "identifier",
    "orderNumber",
    "orderDate",
    "orderPaymentStatus",
    "customerData",
    "orderStatus",
    "totalOrderAmount",
    "shippingMode",
    "appliedDiscounts",
    "appliedDiscountsV2",
    "shippingAddress",
    "billingAddress",
    "distributorAddresses",
    "totalQuantityDelivered",
    "totalReadyForDispatch",
    "totalQuantity",
    "totalRemainingToBeDelivered",
    "totalPaymentReceived",
    "totalAmountOutstanding",
    "paymentSummaryList",
    "lineItemList",
    "summaryLink",
    "shipments",
    "sapInfo",
    "summaryComments",
    "customOrderStatus",
    "customPaymentStatus",
    "salesForceOpportunityId",
    "purchaseOrderDocumentDetails",
    "slcPurchaseOrderDocumentDetails",
    "orderSummaryPdfDetails",
    "estimatedDeliveryTimeline",
    "creditDetails",
    "orderPaymentType",
    "isCashbackChanged",
    "advancePercentage",
    "isPBDistributor",
    "orderType",
    "sfShippingAddress",
    "modeOfOrder",
    "salesforceOpportunityUrl",
    "sourceOfOrder",
    "salesAssociate",
    "channelOfOrder",
    "opportunityString",
    "remark",
    "opportunityOwnerEmail",
    "creditLimitBypass",
    "shipToType",
    "lineItemFreightAmount",
    "lineItemFreightTax",
    "deliveryType",
    "businessUnit",
    "shippingAddressSFId",
    "billingAddressSFId",
    "orderPaymentTransactionType",
    "poNumber",
    "billingAddressValidated",
    "otherDataMap"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("identifier")
    private Identifier identifier;
    @JsonProperty("orderNumber")
    private Object orderNumber;
    @JsonProperty("orderDate")
    private String orderDate;
    @JsonProperty("orderPaymentStatus")
    private Object orderPaymentStatus;
    @JsonProperty("customerData")
    private CustomerData customerData;
    @JsonProperty("orderStatus")
    private Object orderStatus;
    @JsonProperty("totalOrderAmount")
    private TotalOrderAmount totalOrderAmount;
    @JsonProperty("shippingMode")
    private String shippingMode;
    @JsonProperty("appliedDiscounts")
    private List<Object> appliedDiscounts = new ArrayList<Object>();
    @JsonProperty("appliedDiscountsV2")
    private List<Object> appliedDiscountsV2 = new ArrayList<Object>();
    @JsonProperty("shippingAddress")
    private ShippingAddress shippingAddress;
    @JsonProperty("billingAddress")
    private BillingAddress billingAddress;
    @JsonProperty("distributorAddresses")
    private List<DistributorAddress> distributorAddresses = new ArrayList<DistributorAddress>();
    @JsonProperty("totalQuantityDelivered")
    private Object totalQuantityDelivered;
    @JsonProperty("totalReadyForDispatch")
    private Object totalReadyForDispatch;
    @JsonProperty("totalQuantity")
    private String totalQuantity;
    @JsonProperty("totalRemainingToBeDelivered")
    private String totalRemainingToBeDelivered;
    @JsonProperty("totalPaymentReceived")
    private TotalPaymentReceived totalPaymentReceived;
    @JsonProperty("totalAmountOutstanding")
    private TotalAmountOutstanding totalAmountOutstanding;
    @JsonProperty("paymentSummaryList")
    private List<PaymentSummary> paymentSummaryList = new ArrayList<PaymentSummary>();
    @JsonProperty("lineItemList")
    private List<LineItem> lineItemList = new ArrayList<LineItem>();
    @JsonProperty("summaryLink")
    private String summaryLink;
    @JsonProperty("shipments")
    private List<Object> shipments = new ArrayList<Object>();
    @JsonProperty("sapInfo")
    private SapInfo sapInfo;
    @JsonProperty("summaryComments")
    private SummaryComments summaryComments;
    @JsonProperty("customOrderStatus")
    private CustomOrderStatus customOrderStatus;
    @JsonProperty("customPaymentStatus")
    private CustomPaymentStatus customPaymentStatus;
    @JsonProperty("salesForceOpportunityId")
    private Object salesForceOpportunityId;
    @JsonProperty("purchaseOrderDocumentDetails")
    private Object purchaseOrderDocumentDetails;
    @JsonProperty("slcPurchaseOrderDocumentDetails")
    private Object slcPurchaseOrderDocumentDetails;
    @JsonProperty("orderSummaryPdfDetails")
    private Object orderSummaryPdfDetails;
    @JsonProperty("estimatedDeliveryTimeline")
    private Object estimatedDeliveryTimeline;
    @JsonProperty("creditDetails")
    private Object creditDetails;
    @JsonProperty("orderPaymentType")
    private Object orderPaymentType;
    @JsonProperty("isCashbackChanged")
    private Object isCashbackChanged;
    @JsonProperty("advancePercentage")
    private Double advancePercentage;
    @JsonProperty("isPBDistributor")
    private Object isPBDistributor;
    @JsonProperty("orderType")
    private String orderType;
    @JsonProperty("sfShippingAddress")
    private SfShippingAddress sfShippingAddress;
    @JsonProperty("modeOfOrder")
    private Object modeOfOrder;
    @JsonProperty("salesforceOpportunityUrl")
    private Object salesforceOpportunityUrl;
    @JsonProperty("sourceOfOrder")
    private Object sourceOfOrder;
    @JsonProperty("salesAssociate")
    private Object salesAssociate;
    @JsonProperty("channelOfOrder")
    private Object channelOfOrder;
    @JsonProperty("opportunityString")
    private Object opportunityString;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("opportunityOwnerEmail")
    private Object opportunityOwnerEmail;
    @JsonProperty("creditLimitBypass")
    private Boolean creditLimitBypass;
    @JsonProperty("shipToType")
    private String shipToType;
    @JsonProperty("lineItemFreightAmount")
    private LineItemFreightAmount lineItemFreightAmount;
    @JsonProperty("lineItemFreightTax")
    private LineItemFreightTax lineItemFreightTax;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("businessUnit")
    private String businessUnit;
    @JsonProperty("shippingAddressSFId")
    private Object shippingAddressSFId;
    @JsonProperty("billingAddressSFId")
    private Object billingAddressSFId;
    @JsonProperty("orderPaymentTransactionType")
    private Object orderPaymentTransactionType;
    @JsonProperty("poNumber")
    private String poNumber;
    @JsonProperty("billingAddressValidated")
    private Boolean billingAddressValidated;
    @JsonProperty("otherDataMap")
    private OtherDataMap otherDataMap;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("identifier")
    public Identifier getIdentifier() {
        return identifier;
    }

    @JsonProperty("identifier")
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @JsonProperty("orderNumber")
    public Object getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(Object orderNumber) {
        this.orderNumber = orderNumber;
    }

    @JsonProperty("orderDate")
    public String getOrderDate() {
        return orderDate;
    }

    @JsonProperty("orderDate")
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @JsonProperty("orderPaymentStatus")
    public Object getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    @JsonProperty("orderPaymentStatus")
    public void setOrderPaymentStatus(Object orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    @JsonProperty("customerData")
    public CustomerData getCustomerData() {
        return customerData;
    }

    @JsonProperty("customerData")
    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    @JsonProperty("orderStatus")
    public Object getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("orderStatus")
    public void setOrderStatus(Object orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("totalOrderAmount")
    public TotalOrderAmount getTotalOrderAmount() {
        return totalOrderAmount;
    }

    @JsonProperty("totalOrderAmount")
    public void setTotalOrderAmount(TotalOrderAmount totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    @JsonProperty("shippingMode")
    public String getShippingMode() {
        return shippingMode;
    }

    @JsonProperty("shippingMode")
    public void setShippingMode(String shippingMode) {
        this.shippingMode = shippingMode;
    }

    @JsonProperty("appliedDiscounts")
    public List<Object> getAppliedDiscounts() {
        return appliedDiscounts;
    }

    @JsonProperty("appliedDiscounts")
    public void setAppliedDiscounts(List<Object> appliedDiscounts) {
        this.appliedDiscounts = appliedDiscounts;
    }

    @JsonProperty("appliedDiscountsV2")
    public List<Object> getAppliedDiscountsV2() {
        return appliedDiscountsV2;
    }

    @JsonProperty("appliedDiscountsV2")
    public void setAppliedDiscountsV2(List<Object> appliedDiscountsV2) {
        this.appliedDiscountsV2 = appliedDiscountsV2;
    }

    @JsonProperty("shippingAddress")
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    @JsonProperty("shippingAddress")
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @JsonProperty("billingAddress")
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    @JsonProperty("billingAddress")
    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    @JsonProperty("distributorAddresses")
    public List<DistributorAddress> getDistributorAddresses() {
        return distributorAddresses;
    }

    @JsonProperty("distributorAddresses")
    public void setDistributorAddresses(List<DistributorAddress> distributorAddresses) {
        this.distributorAddresses = distributorAddresses;
    }

    @JsonProperty("totalQuantityDelivered")
    public Object getTotalQuantityDelivered() {
        return totalQuantityDelivered;
    }

    @JsonProperty("totalQuantityDelivered")
    public void setTotalQuantityDelivered(Object totalQuantityDelivered) {
        this.totalQuantityDelivered = totalQuantityDelivered;
    }

    @JsonProperty("totalReadyForDispatch")
    public Object getTotalReadyForDispatch() {
        return totalReadyForDispatch;
    }

    @JsonProperty("totalReadyForDispatch")
    public void setTotalReadyForDispatch(Object totalReadyForDispatch) {
        this.totalReadyForDispatch = totalReadyForDispatch;
    }

    @JsonProperty("totalQuantity")
    public String getTotalQuantity() {
        return totalQuantity;
    }

    @JsonProperty("totalQuantity")
    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @JsonProperty("totalRemainingToBeDelivered")
    public String getTotalRemainingToBeDelivered() {
        return totalRemainingToBeDelivered;
    }

    @JsonProperty("totalRemainingToBeDelivered")
    public void setTotalRemainingToBeDelivered(String totalRemainingToBeDelivered) {
        this.totalRemainingToBeDelivered = totalRemainingToBeDelivered;
    }

    @JsonProperty("totalPaymentReceived")
    public TotalPaymentReceived getTotalPaymentReceived() {
        return totalPaymentReceived;
    }

    @JsonProperty("totalPaymentReceived")
    public void setTotalPaymentReceived(TotalPaymentReceived totalPaymentReceived) {
        this.totalPaymentReceived = totalPaymentReceived;
    }

    @JsonProperty("totalAmountOutstanding")
    public TotalAmountOutstanding getTotalAmountOutstanding() {
        return totalAmountOutstanding;
    }

    @JsonProperty("totalAmountOutstanding")
    public void setTotalAmountOutstanding(TotalAmountOutstanding totalAmountOutstanding) {
        this.totalAmountOutstanding = totalAmountOutstanding;
    }

    @JsonProperty("paymentSummaryList")
    public List<PaymentSummary> getPaymentSummaryList() {
        return paymentSummaryList;
    }

    @JsonProperty("paymentSummaryList")
    public void setPaymentSummaryList(List<PaymentSummary> paymentSummaryList) {
        this.paymentSummaryList = paymentSummaryList;
    }

    @JsonProperty("lineItemList")
    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    @JsonProperty("lineItemList")
    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    @JsonProperty("summaryLink")
    public String getSummaryLink() {
        return summaryLink;
    }

    @JsonProperty("summaryLink")
    public void setSummaryLink(String summaryLink) {
        this.summaryLink = summaryLink;
    }

    @JsonProperty("shipments")
    public List<Object> getShipments() {
        return shipments;
    }

    @JsonProperty("shipments")
    public void setShipments(List<Object> shipments) {
        this.shipments = shipments;
    }

    @JsonProperty("sapInfo")
    public SapInfo getSapInfo() {
        return sapInfo;
    }

    @JsonProperty("sapInfo")
    public void setSapInfo(SapInfo sapInfo) {
        this.sapInfo = sapInfo;
    }

    @JsonProperty("summaryComments")
    public SummaryComments getSummaryComments() {
        return summaryComments;
    }

    @JsonProperty("summaryComments")
    public void setSummaryComments(SummaryComments summaryComments) {
        this.summaryComments = summaryComments;
    }

    @JsonProperty("customOrderStatus")
    public CustomOrderStatus getCustomOrderStatus() {
        return customOrderStatus;
    }

    @JsonProperty("customOrderStatus")
    public void setCustomOrderStatus(CustomOrderStatus customOrderStatus) {
        this.customOrderStatus = customOrderStatus;
    }

    @JsonProperty("customPaymentStatus")
    public CustomPaymentStatus getCustomPaymentStatus() {
        return customPaymentStatus;
    }

    @JsonProperty("customPaymentStatus")
    public void setCustomPaymentStatus(CustomPaymentStatus customPaymentStatus) {
        this.customPaymentStatus = customPaymentStatus;
    }

    @JsonProperty("salesForceOpportunityId")
    public Object getSalesForceOpportunityId() {
        return salesForceOpportunityId;
    }

    @JsonProperty("salesForceOpportunityId")
    public void setSalesForceOpportunityId(Object salesForceOpportunityId) {
        this.salesForceOpportunityId = salesForceOpportunityId;
    }

    @JsonProperty("purchaseOrderDocumentDetails")
    public Object getPurchaseOrderDocumentDetails() {
        return purchaseOrderDocumentDetails;
    }

    @JsonProperty("purchaseOrderDocumentDetails")
    public void setPurchaseOrderDocumentDetails(Object purchaseOrderDocumentDetails) {
        this.purchaseOrderDocumentDetails = purchaseOrderDocumentDetails;
    }

    @JsonProperty("slcPurchaseOrderDocumentDetails")
    public Object getSlcPurchaseOrderDocumentDetails() {
        return slcPurchaseOrderDocumentDetails;
    }

    @JsonProperty("slcPurchaseOrderDocumentDetails")
    public void setSlcPurchaseOrderDocumentDetails(Object slcPurchaseOrderDocumentDetails) {
        this.slcPurchaseOrderDocumentDetails = slcPurchaseOrderDocumentDetails;
    }

    @JsonProperty("orderSummaryPdfDetails")
    public Object getOrderSummaryPdfDetails() {
        return orderSummaryPdfDetails;
    }

    @JsonProperty("orderSummaryPdfDetails")
    public void setOrderSummaryPdfDetails(Object orderSummaryPdfDetails) {
        this.orderSummaryPdfDetails = orderSummaryPdfDetails;
    }

    @JsonProperty("estimatedDeliveryTimeline")
    public Object getEstimatedDeliveryTimeline() {
        return estimatedDeliveryTimeline;
    }

    @JsonProperty("estimatedDeliveryTimeline")
    public void setEstimatedDeliveryTimeline(Object estimatedDeliveryTimeline) {
        this.estimatedDeliveryTimeline = estimatedDeliveryTimeline;
    }

    @JsonProperty("creditDetails")
    public Object getCreditDetails() {
        return creditDetails;
    }

    @JsonProperty("creditDetails")
    public void setCreditDetails(Object creditDetails) {
        this.creditDetails = creditDetails;
    }

    @JsonProperty("orderPaymentType")
    public Object getOrderPaymentType() {
        return orderPaymentType;
    }

    @JsonProperty("orderPaymentType")
    public void setOrderPaymentType(Object orderPaymentType) {
        this.orderPaymentType = orderPaymentType;
    }

    @JsonProperty("isCashbackChanged")
    public Object getIsCashbackChanged() {
        return isCashbackChanged;
    }

    @JsonProperty("isCashbackChanged")
    public void setIsCashbackChanged(Object isCashbackChanged) {
        this.isCashbackChanged = isCashbackChanged;
    }

    @JsonProperty("advancePercentage")
    public Double getAdvancePercentage() {
        return advancePercentage;
    }

    @JsonProperty("advancePercentage")
    public void setAdvancePercentage(Double advancePercentage) {
        this.advancePercentage = advancePercentage;
    }

    @JsonProperty("isPBDistributor")
    public Object getIsPBDistributor() {
        return isPBDistributor;
    }

    @JsonProperty("isPBDistributor")
    public void setIsPBDistributor(Object isPBDistributor) {
        this.isPBDistributor = isPBDistributor;
    }

    @JsonProperty("orderType")
    public String getOrderType() {
        return orderType;
    }

    @JsonProperty("orderType")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @JsonProperty("sfShippingAddress")
    public SfShippingAddress getSfShippingAddress() {
        return sfShippingAddress;
    }

    @JsonProperty("sfShippingAddress")
    public void setSfShippingAddress(SfShippingAddress sfShippingAddress) {
        this.sfShippingAddress = sfShippingAddress;
    }

    @JsonProperty("modeOfOrder")
    public Object getModeOfOrder() {
        return modeOfOrder;
    }

    @JsonProperty("modeOfOrder")
    public void setModeOfOrder(Object modeOfOrder) {
        this.modeOfOrder = modeOfOrder;
    }

    @JsonProperty("salesforceOpportunityUrl")
    public Object getSalesforceOpportunityUrl() {
        return salesforceOpportunityUrl;
    }

    @JsonProperty("salesforceOpportunityUrl")
    public void setSalesforceOpportunityUrl(Object salesforceOpportunityUrl) {
        this.salesforceOpportunityUrl = salesforceOpportunityUrl;
    }

    @JsonProperty("sourceOfOrder")
    public Object getSourceOfOrder() {
        return sourceOfOrder;
    }

    @JsonProperty("sourceOfOrder")
    public void setSourceOfOrder(Object sourceOfOrder) {
        this.sourceOfOrder = sourceOfOrder;
    }

    @JsonProperty("salesAssociate")
    public Object getSalesAssociate() {
        return salesAssociate;
    }

    @JsonProperty("salesAssociate")
    public void setSalesAssociate(Object salesAssociate) {
        this.salesAssociate = salesAssociate;
    }

    @JsonProperty("channelOfOrder")
    public Object getChannelOfOrder() {
        return channelOfOrder;
    }

    @JsonProperty("channelOfOrder")
    public void setChannelOfOrder(Object channelOfOrder) {
        this.channelOfOrder = channelOfOrder;
    }

    @JsonProperty("opportunityString")
    public Object getOpportunityString() {
        return opportunityString;
    }

    @JsonProperty("opportunityString")
    public void setOpportunityString(Object opportunityString) {
        this.opportunityString = opportunityString;
    }

    @JsonProperty("remark")
    public String getRemark() {
        return remark;
    }

    @JsonProperty("remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonProperty("opportunityOwnerEmail")
    public Object getOpportunityOwnerEmail() {
        return opportunityOwnerEmail;
    }

    @JsonProperty("opportunityOwnerEmail")
    public void setOpportunityOwnerEmail(Object opportunityOwnerEmail) {
        this.opportunityOwnerEmail = opportunityOwnerEmail;
    }

    @JsonProperty("creditLimitBypass")
    public Boolean getCreditLimitBypass() {
        return creditLimitBypass;
    }

    @JsonProperty("creditLimitBypass")
    public void setCreditLimitBypass(Boolean creditLimitBypass) {
        this.creditLimitBypass = creditLimitBypass;
    }

    @JsonProperty("shipToType")
    public String getShipToType() {
        return shipToType;
    }

    @JsonProperty("shipToType")
    public void setShipToType(String shipToType) {
        this.shipToType = shipToType;
    }

    @JsonProperty("lineItemFreightAmount")
    public LineItemFreightAmount getLineItemFreightAmount() {
        return lineItemFreightAmount;
    }

    @JsonProperty("lineItemFreightAmount")
    public void setLineItemFreightAmount(LineItemFreightAmount lineItemFreightAmount) {
        this.lineItemFreightAmount = lineItemFreightAmount;
    }

    @JsonProperty("lineItemFreightTax")
    public LineItemFreightTax getLineItemFreightTax() {
        return lineItemFreightTax;
    }

    @JsonProperty("lineItemFreightTax")
    public void setLineItemFreightTax(LineItemFreightTax lineItemFreightTax) {
        this.lineItemFreightTax = lineItemFreightTax;
    }

    @JsonProperty("deliveryType")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonProperty("businessUnit")
    public String getBusinessUnit() {
        return businessUnit;
    }

    @JsonProperty("businessUnit")
    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    @JsonProperty("shippingAddressSFId")
    public Object getShippingAddressSFId() {
        return shippingAddressSFId;
    }

    @JsonProperty("shippingAddressSFId")
    public void setShippingAddressSFId(Object shippingAddressSFId) {
        this.shippingAddressSFId = shippingAddressSFId;
    }

    @JsonProperty("billingAddressSFId")
    public Object getBillingAddressSFId() {
        return billingAddressSFId;
    }

    @JsonProperty("billingAddressSFId")
    public void setBillingAddressSFId(Object billingAddressSFId) {
        this.billingAddressSFId = billingAddressSFId;
    }

    @JsonProperty("orderPaymentTransactionType")
    public Object getOrderPaymentTransactionType() {
        return orderPaymentTransactionType;
    }

    @JsonProperty("orderPaymentTransactionType")
    public void setOrderPaymentTransactionType(Object orderPaymentTransactionType) {
        this.orderPaymentTransactionType = orderPaymentTransactionType;
    }

    @JsonProperty("poNumber")
    public String getPoNumber() {
        return poNumber;
    }

    @JsonProperty("poNumber")
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    @JsonProperty("billingAddressValidated")
    public Boolean getBillingAddressValidated() {
        return billingAddressValidated;
    }

    @JsonProperty("billingAddressValidated")
    public void setBillingAddressValidated(Boolean billingAddressValidated) {
        this.billingAddressValidated = billingAddressValidated;
    }

    @JsonProperty("otherDataMap")
    public OtherDataMap getOtherDataMap() {
        return otherDataMap;
    }

    @JsonProperty("otherDataMap")
    public void setOtherDataMap(OtherDataMap otherDataMap) {
        this.otherDataMap = otherDataMap;
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
        sb.append("identifier");
        sb.append('=');
        sb.append(((this.identifier == null)?"<null>":this.identifier));
        sb.append(',');
        sb.append("orderNumber");
        sb.append('=');
        sb.append(((this.orderNumber == null)?"<null>":this.orderNumber));
        sb.append(',');
        sb.append("orderDate");
        sb.append('=');
        sb.append(((this.orderDate == null)?"<null>":this.orderDate));
        sb.append(',');
        sb.append("orderPaymentStatus");
        sb.append('=');
        sb.append(((this.orderPaymentStatus == null)?"<null>":this.orderPaymentStatus));
        sb.append(',');
        sb.append("customerData");
        sb.append('=');
        sb.append(((this.customerData == null)?"<null>":this.customerData));
        sb.append(',');
        sb.append("orderStatus");
        sb.append('=');
        sb.append(((this.orderStatus == null)?"<null>":this.orderStatus));
        sb.append(',');
        sb.append("totalOrderAmount");
        sb.append('=');
        sb.append(((this.totalOrderAmount == null)?"<null>":this.totalOrderAmount));
        sb.append(',');
        sb.append("shippingMode");
        sb.append('=');
        sb.append(((this.shippingMode == null)?"<null>":this.shippingMode));
        sb.append(',');
        sb.append("appliedDiscounts");
        sb.append('=');
        sb.append(((this.appliedDiscounts == null)?"<null>":this.appliedDiscounts));
        sb.append(',');
        sb.append("appliedDiscountsV2");
        sb.append('=');
        sb.append(((this.appliedDiscountsV2 == null)?"<null>":this.appliedDiscountsV2));
        sb.append(',');
        sb.append("shippingAddress");
        sb.append('=');
        sb.append(((this.shippingAddress == null)?"<null>":this.shippingAddress));
        sb.append(',');
        sb.append("billingAddress");
        sb.append('=');
        sb.append(((this.billingAddress == null)?"<null>":this.billingAddress));
        sb.append(',');
        sb.append("distributorAddresses");
        sb.append('=');
        sb.append(((this.distributorAddresses == null)?"<null>":this.distributorAddresses));
        sb.append(',');
        sb.append("totalQuantityDelivered");
        sb.append('=');
        sb.append(((this.totalQuantityDelivered == null)?"<null>":this.totalQuantityDelivered));
        sb.append(',');
        sb.append("totalReadyForDispatch");
        sb.append('=');
        sb.append(((this.totalReadyForDispatch == null)?"<null>":this.totalReadyForDispatch));
        sb.append(',');
        sb.append("totalQuantity");
        sb.append('=');
        sb.append(((this.totalQuantity == null)?"<null>":this.totalQuantity));
        sb.append(',');
        sb.append("totalRemainingToBeDelivered");
        sb.append('=');
        sb.append(((this.totalRemainingToBeDelivered == null)?"<null>":this.totalRemainingToBeDelivered));
        sb.append(',');
        sb.append("totalPaymentReceived");
        sb.append('=');
        sb.append(((this.totalPaymentReceived == null)?"<null>":this.totalPaymentReceived));
        sb.append(',');
        sb.append("totalAmountOutstanding");
        sb.append('=');
        sb.append(((this.totalAmountOutstanding == null)?"<null>":this.totalAmountOutstanding));
        sb.append(',');
        sb.append("paymentSummaryList");
        sb.append('=');
        sb.append(((this.paymentSummaryList == null)?"<null>":this.paymentSummaryList));
        sb.append(',');
        sb.append("lineItemList");
        sb.append('=');
        sb.append(((this.lineItemList == null)?"<null>":this.lineItemList));
        sb.append(',');
        sb.append("summaryLink");
        sb.append('=');
        sb.append(((this.summaryLink == null)?"<null>":this.summaryLink));
        sb.append(',');
        sb.append("shipments");
        sb.append('=');
        sb.append(((this.shipments == null)?"<null>":this.shipments));
        sb.append(',');
        sb.append("sapInfo");
        sb.append('=');
        sb.append(((this.sapInfo == null)?"<null>":this.sapInfo));
        sb.append(',');
        sb.append("summaryComments");
        sb.append('=');
        sb.append(((this.summaryComments == null)?"<null>":this.summaryComments));
        sb.append(',');
        sb.append("customOrderStatus");
        sb.append('=');
        sb.append(((this.customOrderStatus == null)?"<null>":this.customOrderStatus));
        sb.append(',');
        sb.append("customPaymentStatus");
        sb.append('=');
        sb.append(((this.customPaymentStatus == null)?"<null>":this.customPaymentStatus));
        sb.append(',');
        sb.append("salesForceOpportunityId");
        sb.append('=');
        sb.append(((this.salesForceOpportunityId == null)?"<null>":this.salesForceOpportunityId));
        sb.append(',');
        sb.append("purchaseOrderDocumentDetails");
        sb.append('=');
        sb.append(((this.purchaseOrderDocumentDetails == null)?"<null>":this.purchaseOrderDocumentDetails));
        sb.append(',');
        sb.append("slcPurchaseOrderDocumentDetails");
        sb.append('=');
        sb.append(((this.slcPurchaseOrderDocumentDetails == null)?"<null>":this.slcPurchaseOrderDocumentDetails));
        sb.append(',');
        sb.append("orderSummaryPdfDetails");
        sb.append('=');
        sb.append(((this.orderSummaryPdfDetails == null)?"<null>":this.orderSummaryPdfDetails));
        sb.append(',');
        sb.append("estimatedDeliveryTimeline");
        sb.append('=');
        sb.append(((this.estimatedDeliveryTimeline == null)?"<null>":this.estimatedDeliveryTimeline));
        sb.append(',');
        sb.append("creditDetails");
        sb.append('=');
        sb.append(((this.creditDetails == null)?"<null>":this.creditDetails));
        sb.append(',');
        sb.append("orderPaymentType");
        sb.append('=');
        sb.append(((this.orderPaymentType == null)?"<null>":this.orderPaymentType));
        sb.append(',');
        sb.append("isCashbackChanged");
        sb.append('=');
        sb.append(((this.isCashbackChanged == null)?"<null>":this.isCashbackChanged));
        sb.append(',');
        sb.append("advancePercentage");
        sb.append('=');
        sb.append(((this.advancePercentage == null)?"<null>":this.advancePercentage));
        sb.append(',');
        sb.append("isPBDistributor");
        sb.append('=');
        sb.append(((this.isPBDistributor == null)?"<null>":this.isPBDistributor));
        sb.append(',');
        sb.append("orderType");
        sb.append('=');
        sb.append(((this.orderType == null)?"<null>":this.orderType));
        sb.append(',');
        sb.append("sfShippingAddress");
        sb.append('=');
        sb.append(((this.sfShippingAddress == null)?"<null>":this.sfShippingAddress));
        sb.append(',');
        sb.append("modeOfOrder");
        sb.append('=');
        sb.append(((this.modeOfOrder == null)?"<null>":this.modeOfOrder));
        sb.append(',');
        sb.append("salesforceOpportunityUrl");
        sb.append('=');
        sb.append(((this.salesforceOpportunityUrl == null)?"<null>":this.salesforceOpportunityUrl));
        sb.append(',');
        sb.append("sourceOfOrder");
        sb.append('=');
        sb.append(((this.sourceOfOrder == null)?"<null>":this.sourceOfOrder));
        sb.append(',');
        sb.append("salesAssociate");
        sb.append('=');
        sb.append(((this.salesAssociate == null)?"<null>":this.salesAssociate));
        sb.append(',');
        sb.append("channelOfOrder");
        sb.append('=');
        sb.append(((this.channelOfOrder == null)?"<null>":this.channelOfOrder));
        sb.append(',');
        sb.append("opportunityString");
        sb.append('=');
        sb.append(((this.opportunityString == null)?"<null>":this.opportunityString));
        sb.append(',');
        sb.append("remark");
        sb.append('=');
        sb.append(((this.remark == null)?"<null>":this.remark));
        sb.append(',');
        sb.append("opportunityOwnerEmail");
        sb.append('=');
        sb.append(((this.opportunityOwnerEmail == null)?"<null>":this.opportunityOwnerEmail));
        sb.append(',');
        sb.append("creditLimitBypass");
        sb.append('=');
        sb.append(((this.creditLimitBypass == null)?"<null>":this.creditLimitBypass));
        sb.append(',');
        sb.append("shipToType");
        sb.append('=');
        sb.append(((this.shipToType == null)?"<null>":this.shipToType));
        sb.append(',');
        sb.append("lineItemFreightAmount");
        sb.append('=');
        sb.append(((this.lineItemFreightAmount == null)?"<null>":this.lineItemFreightAmount));
        sb.append(',');
        sb.append("lineItemFreightTax");
        sb.append('=');
        sb.append(((this.lineItemFreightTax == null)?"<null>":this.lineItemFreightTax));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("businessUnit");
        sb.append('=');
        sb.append(((this.businessUnit == null)?"<null>":this.businessUnit));
        sb.append(',');
        sb.append("shippingAddressSFId");
        sb.append('=');
        sb.append(((this.shippingAddressSFId == null)?"<null>":this.shippingAddressSFId));
        sb.append(',');
        sb.append("billingAddressSFId");
        sb.append('=');
        sb.append(((this.billingAddressSFId == null)?"<null>":this.billingAddressSFId));
        sb.append(',');
        sb.append("orderPaymentTransactionType");
        sb.append('=');
        sb.append(((this.orderPaymentTransactionType == null)?"<null>":this.orderPaymentTransactionType));
        sb.append(',');
        sb.append("poNumber");
        sb.append('=');
        sb.append(((this.poNumber == null)?"<null>":this.poNumber));
        sb.append(',');
        sb.append("billingAddressValidated");
        sb.append('=');
        sb.append(((this.billingAddressValidated == null)?"<null>":this.billingAddressValidated));
        sb.append(',');
        sb.append("otherDataMap");
        sb.append('=');
        sb.append(((this.otherDataMap == null)?"<null>":this.otherDataMap));
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
        result = ((result* 31)+((this.orderType == null)? 0 :this.orderType.hashCode()));
        result = ((result* 31)+((this.salesAssociate == null)? 0 :this.salesAssociate.hashCode()));
        result = ((result* 31)+((this.orderNumber == null)? 0 :this.orderNumber.hashCode()));
        result = ((result* 31)+((this.businessUnit == null)? 0 :this.businessUnit.hashCode()));
        result = ((result* 31)+((this.orderPaymentTransactionType == null)? 0 :this.orderPaymentTransactionType.hashCode()));
        result = ((result* 31)+((this.slcPurchaseOrderDocumentDetails == null)? 0 :this.slcPurchaseOrderDocumentDetails.hashCode()));
        result = ((result* 31)+((this.customerData == null)? 0 :this.customerData.hashCode()));
        result = ((result* 31)+((this.sfShippingAddress == null)? 0 :this.sfShippingAddress.hashCode()));
        result = ((result* 31)+((this.sapInfo == null)? 0 :this.sapInfo.hashCode()));
        result = ((result* 31)+((this.sourceOfOrder == null)? 0 :this.sourceOfOrder.hashCode()));
        result = ((result* 31)+((this.totalQuantityDelivered == null)? 0 :this.totalQuantityDelivered.hashCode()));
        result = ((result* 31)+((this.totalQuantity == null)? 0 :this.totalQuantity.hashCode()));
        result = ((result* 31)+((this.identifier == null)? 0 :this.identifier.hashCode()));
        result = ((result* 31)+((this.summaryLink == null)? 0 :this.summaryLink.hashCode()));
        result = ((result* 31)+((this.advancePercentage == null)? 0 :this.advancePercentage.hashCode()));
        result = ((result* 31)+((this.creditLimitBypass == null)? 0 :this.creditLimitBypass.hashCode()));
        result = ((result* 31)+((this.otherDataMap == null)? 0 :this.otherDataMap.hashCode()));
        result = ((result* 31)+((this.purchaseOrderDocumentDetails == null)? 0 :this.purchaseOrderDocumentDetails.hashCode()));
        result = ((result* 31)+((this.totalOrderAmount == null)? 0 :this.totalOrderAmount.hashCode()));
        result = ((result* 31)+((this.lineItemList == null)? 0 :this.lineItemList.hashCode()));
        result = ((result* 31)+((this.totalRemainingToBeDelivered == null)? 0 :this.totalRemainingToBeDelivered.hashCode()));
        result = ((result* 31)+((this.summaryComments == null)? 0 :this.summaryComments.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.orderDate == null)? 0 :this.orderDate.hashCode()));
        result = ((result* 31)+((this.distributorAddresses == null)? 0 :this.distributorAddresses.hashCode()));
        result = ((result* 31)+((this.poNumber == null)? 0 :this.poNumber.hashCode()));
        result = ((result* 31)+((this.appliedDiscountsV2 == null)? 0 :this.appliedDiscountsV2 .hashCode()));
        result = ((result* 31)+((this.appliedDiscounts == null)? 0 :this.appliedDiscounts.hashCode()));
        result = ((result* 31)+((this.customPaymentStatus == null)? 0 :this.customPaymentStatus.hashCode()));
        result = ((result* 31)+((this.orderStatus == null)? 0 :this.orderStatus.hashCode()));
        result = ((result* 31)+((this.remark == null)? 0 :this.remark.hashCode()));
        result = ((result* 31)+((this.shipToType == null)? 0 :this.shipToType.hashCode()));
        result = ((result* 31)+((this.shippingMode == null)? 0 :this.shippingMode.hashCode()));
        result = ((result* 31)+((this.estimatedDeliveryTimeline == null)? 0 :this.estimatedDeliveryTimeline.hashCode()));
        result = ((result* 31)+((this.orderPaymentType == null)? 0 :this.orderPaymentType.hashCode()));
        result = ((result* 31)+((this.creditDetails == null)? 0 :this.creditDetails.hashCode()));
        result = ((result* 31)+((this.paymentSummaryList == null)? 0 :this.paymentSummaryList.hashCode()));
        result = ((result* 31)+((this.isCashbackChanged == null)? 0 :this.isCashbackChanged.hashCode()));
        result = ((result* 31)+((this.customOrderStatus == null)? 0 :this.customOrderStatus.hashCode()));
        result = ((result* 31)+((this.lineItemFreightTax == null)? 0 :this.lineItemFreightTax.hashCode()));
        result = ((result* 31)+((this.totalReadyForDispatch == null)? 0 :this.totalReadyForDispatch.hashCode()));
        result = ((result* 31)+((this.orderSummaryPdfDetails == null)? 0 :this.orderSummaryPdfDetails.hashCode()));
        result = ((result* 31)+((this.modeOfOrder == null)? 0 :this.modeOfOrder.hashCode()));
        result = ((result* 31)+((this.lineItemFreightAmount == null)? 0 :this.lineItemFreightAmount.hashCode()));
        result = ((result* 31)+((this.salesforceOpportunityUrl == null)? 0 :this.salesforceOpportunityUrl.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.billingAddressSFId == null)? 0 :this.billingAddressSFId.hashCode()));
        result = ((result* 31)+((this.shippingAddressSFId == null)? 0 :this.shippingAddressSFId.hashCode()));
        result = ((result* 31)+((this.shipments == null)? 0 :this.shipments.hashCode()));
        result = ((result* 31)+((this.orderPaymentStatus == null)? 0 :this.orderPaymentStatus.hashCode()));
        result = ((result* 31)+((this.billingAddressValidated == null)? 0 :this.billingAddressValidated.hashCode()));
        result = ((result* 31)+((this.opportunityOwnerEmail == null)? 0 :this.opportunityOwnerEmail.hashCode()));
        result = ((result* 31)+((this.totalPaymentReceived == null)? 0 :this.totalPaymentReceived.hashCode()));
        result = ((result* 31)+((this.opportunityString == null)? 0 :this.opportunityString.hashCode()));
        result = ((result* 31)+((this.totalAmountOutstanding == null)? 0 :this.totalAmountOutstanding.hashCode()));
        result = ((result* 31)+((this.isPBDistributor == null)? 0 :this.isPBDistributor.hashCode()));
        result = ((result* 31)+((this.shippingAddress == null)? 0 :this.shippingAddress.hashCode()));
        result = ((result* 31)+((this.billingAddress == null)? 0 :this.billingAddress.hashCode()));
        result = ((result* 31)+((this.salesForceOpportunityId == null)? 0 :this.salesForceOpportunityId.hashCode()));
        result = ((result* 31)+((this.channelOfOrder == null)? 0 :this.channelOfOrder.hashCode()));
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
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.orderType == rhs.orderType)||((this.orderType!= null)&&this.orderType.equals(rhs.orderType)))&&((this.salesAssociate == rhs.salesAssociate)||((this.salesAssociate!= null)&&this.salesAssociate.equals(rhs.salesAssociate))))&&((this.orderNumber == rhs.orderNumber)||((this.orderNumber!= null)&&this.orderNumber.equals(rhs.orderNumber))))&&((this.businessUnit == rhs.businessUnit)||((this.businessUnit!= null)&&this.businessUnit.equals(rhs.businessUnit))))&&((this.orderPaymentTransactionType == rhs.orderPaymentTransactionType)||((this.orderPaymentTransactionType!= null)&&this.orderPaymentTransactionType.equals(rhs.orderPaymentTransactionType))))&&((this.slcPurchaseOrderDocumentDetails == rhs.slcPurchaseOrderDocumentDetails)||((this.slcPurchaseOrderDocumentDetails!= null)&&this.slcPurchaseOrderDocumentDetails.equals(rhs.slcPurchaseOrderDocumentDetails))))&&((this.customerData == rhs.customerData)||((this.customerData!= null)&&this.customerData.equals(rhs.customerData))))&&((this.sfShippingAddress == rhs.sfShippingAddress)||((this.sfShippingAddress!= null)&&this.sfShippingAddress.equals(rhs.sfShippingAddress))))&&((this.sapInfo == rhs.sapInfo)||((this.sapInfo!= null)&&this.sapInfo.equals(rhs.sapInfo))))&&((this.sourceOfOrder == rhs.sourceOfOrder)||((this.sourceOfOrder!= null)&&this.sourceOfOrder.equals(rhs.sourceOfOrder))))&&((this.totalQuantityDelivered == rhs.totalQuantityDelivered)||((this.totalQuantityDelivered!= null)&&this.totalQuantityDelivered.equals(rhs.totalQuantityDelivered))))&&((this.totalQuantity == rhs.totalQuantity)||((this.totalQuantity!= null)&&this.totalQuantity.equals(rhs.totalQuantity))))&&((this.identifier == rhs.identifier)||((this.identifier!= null)&&this.identifier.equals(rhs.identifier))))&&((this.summaryLink == rhs.summaryLink)||((this.summaryLink!= null)&&this.summaryLink.equals(rhs.summaryLink))))&&((this.advancePercentage == rhs.advancePercentage)||((this.advancePercentage!= null)&&this.advancePercentage.equals(rhs.advancePercentage))))&&((this.creditLimitBypass == rhs.creditLimitBypass)||((this.creditLimitBypass!= null)&&this.creditLimitBypass.equals(rhs.creditLimitBypass))))&&((this.otherDataMap == rhs.otherDataMap)||((this.otherDataMap!= null)&&this.otherDataMap.equals(rhs.otherDataMap))))&&((this.purchaseOrderDocumentDetails == rhs.purchaseOrderDocumentDetails)||((this.purchaseOrderDocumentDetails!= null)&&this.purchaseOrderDocumentDetails.equals(rhs.purchaseOrderDocumentDetails))))&&((this.totalOrderAmount == rhs.totalOrderAmount)||((this.totalOrderAmount!= null)&&this.totalOrderAmount.equals(rhs.totalOrderAmount))))&&((this.lineItemList == rhs.lineItemList)||((this.lineItemList!= null)&&this.lineItemList.equals(rhs.lineItemList))))&&((this.totalRemainingToBeDelivered == rhs.totalRemainingToBeDelivered)||((this.totalRemainingToBeDelivered!= null)&&this.totalRemainingToBeDelivered.equals(rhs.totalRemainingToBeDelivered))))&&((this.summaryComments == rhs.summaryComments)||((this.summaryComments!= null)&&this.summaryComments.equals(rhs.summaryComments))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.orderDate == rhs.orderDate)||((this.orderDate!= null)&&this.orderDate.equals(rhs.orderDate))))&&((this.distributorAddresses == rhs.distributorAddresses)||((this.distributorAddresses!= null)&&this.distributorAddresses.equals(rhs.distributorAddresses))))&&((this.poNumber == rhs.poNumber)||((this.poNumber!= null)&&this.poNumber.equals(rhs.poNumber))))&&((this.appliedDiscountsV2 == rhs.appliedDiscountsV2)||((this.appliedDiscountsV2 != null)&&this.appliedDiscountsV2 .equals(rhs.appliedDiscountsV2))))&&((this.appliedDiscounts == rhs.appliedDiscounts)||((this.appliedDiscounts!= null)&&this.appliedDiscounts.equals(rhs.appliedDiscounts))))&&((this.customPaymentStatus == rhs.customPaymentStatus)||((this.customPaymentStatus!= null)&&this.customPaymentStatus.equals(rhs.customPaymentStatus))))&&((this.orderStatus == rhs.orderStatus)||((this.orderStatus!= null)&&this.orderStatus.equals(rhs.orderStatus))))&&((this.remark == rhs.remark)||((this.remark!= null)&&this.remark.equals(rhs.remark))))&&((this.shipToType == rhs.shipToType)||((this.shipToType!= null)&&this.shipToType.equals(rhs.shipToType))))&&((this.shippingMode == rhs.shippingMode)||((this.shippingMode!= null)&&this.shippingMode.equals(rhs.shippingMode))))&&((this.estimatedDeliveryTimeline == rhs.estimatedDeliveryTimeline)||((this.estimatedDeliveryTimeline!= null)&&this.estimatedDeliveryTimeline.equals(rhs.estimatedDeliveryTimeline))))&&((this.orderPaymentType == rhs.orderPaymentType)||((this.orderPaymentType!= null)&&this.orderPaymentType.equals(rhs.orderPaymentType))))&&((this.creditDetails == rhs.creditDetails)||((this.creditDetails!= null)&&this.creditDetails.equals(rhs.creditDetails))))&&((this.paymentSummaryList == rhs.paymentSummaryList)||((this.paymentSummaryList!= null)&&this.paymentSummaryList.equals(rhs.paymentSummaryList))))&&((this.isCashbackChanged == rhs.isCashbackChanged)||((this.isCashbackChanged!= null)&&this.isCashbackChanged.equals(rhs.isCashbackChanged))))&&((this.customOrderStatus == rhs.customOrderStatus)||((this.customOrderStatus!= null)&&this.customOrderStatus.equals(rhs.customOrderStatus))))&&((this.lineItemFreightTax == rhs.lineItemFreightTax)||((this.lineItemFreightTax!= null)&&this.lineItemFreightTax.equals(rhs.lineItemFreightTax))))&&((this.totalReadyForDispatch == rhs.totalReadyForDispatch)||((this.totalReadyForDispatch!= null)&&this.totalReadyForDispatch.equals(rhs.totalReadyForDispatch))))&&((this.orderSummaryPdfDetails == rhs.orderSummaryPdfDetails)||((this.orderSummaryPdfDetails!= null)&&this.orderSummaryPdfDetails.equals(rhs.orderSummaryPdfDetails))))&&((this.modeOfOrder == rhs.modeOfOrder)||((this.modeOfOrder!= null)&&this.modeOfOrder.equals(rhs.modeOfOrder))))&&((this.lineItemFreightAmount == rhs.lineItemFreightAmount)||((this.lineItemFreightAmount!= null)&&this.lineItemFreightAmount.equals(rhs.lineItemFreightAmount))))&&((this.salesforceOpportunityUrl == rhs.salesforceOpportunityUrl)||((this.salesforceOpportunityUrl!= null)&&this.salesforceOpportunityUrl.equals(rhs.salesforceOpportunityUrl))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.billingAddressSFId == rhs.billingAddressSFId)||((this.billingAddressSFId!= null)&&this.billingAddressSFId.equals(rhs.billingAddressSFId))))&&((this.shippingAddressSFId == rhs.shippingAddressSFId)||((this.shippingAddressSFId!= null)&&this.shippingAddressSFId.equals(rhs.shippingAddressSFId))))&&((this.shipments == rhs.shipments)||((this.shipments!= null)&&this.shipments.equals(rhs.shipments))))&&((this.orderPaymentStatus == rhs.orderPaymentStatus)||((this.orderPaymentStatus!= null)&&this.orderPaymentStatus.equals(rhs.orderPaymentStatus))))&&((this.billingAddressValidated == rhs.billingAddressValidated)||((this.billingAddressValidated!= null)&&this.billingAddressValidated.equals(rhs.billingAddressValidated))))&&((this.opportunityOwnerEmail == rhs.opportunityOwnerEmail)||((this.opportunityOwnerEmail!= null)&&this.opportunityOwnerEmail.equals(rhs.opportunityOwnerEmail))))&&((this.totalPaymentReceived == rhs.totalPaymentReceived)||((this.totalPaymentReceived!= null)&&this.totalPaymentReceived.equals(rhs.totalPaymentReceived))))&&((this.opportunityString == rhs.opportunityString)||((this.opportunityString!= null)&&this.opportunityString.equals(rhs.opportunityString))))&&((this.totalAmountOutstanding == rhs.totalAmountOutstanding)||((this.totalAmountOutstanding!= null)&&this.totalAmountOutstanding.equals(rhs.totalAmountOutstanding))))&&((this.isPBDistributor == rhs.isPBDistributor)||((this.isPBDistributor!= null)&&this.isPBDistributor.equals(rhs.isPBDistributor))))&&((this.shippingAddress == rhs.shippingAddress)||((this.shippingAddress!= null)&&this.shippingAddress.equals(rhs.shippingAddress))))&&((this.billingAddress == rhs.billingAddress)||((this.billingAddress!= null)&&this.billingAddress.equals(rhs.billingAddress))))&&((this.salesForceOpportunityId == rhs.salesForceOpportunityId)||((this.salesForceOpportunityId!= null)&&this.salesForceOpportunityId.equals(rhs.salesForceOpportunityId))))&&((this.channelOfOrder == rhs.channelOfOrder)||((this.channelOfOrder!= null)&&this.channelOfOrder.equals(rhs.channelOfOrder))));
    }

}
