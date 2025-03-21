
package com.qapitol.jsw.apitest.pojos.order.confirmOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "ordNumber",
    "currentState",
    "shippingAddress",
    "billingAddress",
    "discountInfo",
    "mode",
    "orderDates",
    "category",
    "ctId",
    "priorityId",
    "customerId",
    "cashback",
    "buyerCashback",
    "channelOfOrder",
    "opsSpoc",
    "salesSpoc",
    "reasonForCancellation",
    "reasonForDecline",
    "specialDeliveryInstructions",
    "buyerComments",
    "sellerComments",
    "salesForceOpportunityId",
    "salesForceOpportunityUrl",
    "opportunityString",
    "files",
    "isZeroAdvanceOrder",
    "source",
    "sapMetadataJson",
    "orderTermDetails",
    "isVerifiedPO",
    "isVerifiedSO",
    "orderRefundCount",
    "orderPaymentType",
    "isMoeEventTriggered",
    "advancePercentage",
    "createdBy",
    "createdDate",
    "lastModifiedBy",
    "lastModifiedDate",
    "orderCreatedDate",
    "orderPlacedDate",
    "committedDeliveryDate",
    "scheduledDeliveryDate",
    "nextActionDate",
    "paymentCompletionDate",
    "acceptedDate",
    "rejectedDate",
    "cartId",
    "creditBlockBypassFlag",
    "orderSummaryFileDetails",
    "remarks",
    "orderPaymentTransactionType",
    "orderConfirmedBy"
})
@Generated("jsonschema2pojo")
public class ConfirmOrderResp {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("ordNumber")
    private String ordNumber;
    @JsonProperty("currentState")
    private CurrentState currentState;
    @JsonProperty("shippingAddress")
    private ShippingAddress shippingAddress;
    @JsonProperty("billingAddress")
    private BillingAddress billingAddress;
    @JsonProperty("discountInfo")
    private DiscountInfo discountInfo;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("orderDates")
    private OrderDates orderDates;
    @JsonProperty("category")
    private String category;
    @JsonProperty("ctId")
    private Object ctId;
    @JsonProperty("priorityId")
    private Object priorityId;
    @JsonProperty("customerId")
    private Integer customerId;
    @JsonProperty("cashback")
    private Double cashback;
    @JsonProperty("buyerCashback")
    private Object buyerCashback;
    @JsonProperty("channelOfOrder")
    private Object channelOfOrder;
    @JsonProperty("opsSpoc")
    private String opsSpoc;
    @JsonProperty("salesSpoc")
    private Object salesSpoc;
    @JsonProperty("reasonForCancellation")
    private Object reasonForCancellation;
    @JsonProperty("reasonForDecline")
    private Object reasonForDecline;
    @JsonProperty("specialDeliveryInstructions")
    private Object specialDeliveryInstructions;
    @JsonProperty("buyerComments")
    private Object buyerComments;
    @JsonProperty("sellerComments")
    private Object sellerComments;
    @JsonProperty("salesForceOpportunityId")
    private String salesForceOpportunityId;
    @JsonProperty("salesForceOpportunityUrl")
    private Object salesForceOpportunityUrl;
    @JsonProperty("opportunityString")
    private Object opportunityString;
    @JsonProperty("files")
    private Object files;
    @JsonProperty("isZeroAdvanceOrder")
    private Boolean isZeroAdvanceOrder;
    @JsonProperty("source")
    private String source;
    @JsonProperty("sapMetadataJson")
    private SapMetadataJson sapMetadataJson;
    @JsonProperty("orderTermDetails")
    private Object orderTermDetails;
    @JsonProperty("isVerifiedPO")
    private Boolean isVerifiedPO;
    @JsonProperty("isVerifiedSO")
    private Boolean isVerifiedSO;
    @JsonProperty("orderRefundCount")
    private Integer orderRefundCount;
    @JsonProperty("orderPaymentType")
    private String orderPaymentType;
    @JsonProperty("isMoeEventTriggered")
    private Object isMoeEventTriggered;
    @JsonProperty("advancePercentage")
    private Double advancePercentage;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("createdDate")
    private List<Integer> createdDate = new ArrayList<Integer>();
    @JsonProperty("lastModifiedBy")
    private String lastModifiedBy;
    @JsonProperty("lastModifiedDate")
    private List<Integer> lastModifiedDate = new ArrayList<Integer>();
    @JsonProperty("orderCreatedDate")
    private List<Integer> orderCreatedDate = new ArrayList<Integer>();
    @JsonProperty("orderPlacedDate")
    private Object orderPlacedDate;
    @JsonProperty("committedDeliveryDate")
    private Object committedDeliveryDate;
    @JsonProperty("scheduledDeliveryDate")
    private Object scheduledDeliveryDate;
    @JsonProperty("nextActionDate")
    private Object nextActionDate;
    @JsonProperty("paymentCompletionDate")
    private Object paymentCompletionDate;
    @JsonProperty("acceptedDate")
    private Object acceptedDate;
    @JsonProperty("rejectedDate")
    private Object rejectedDate;
    @JsonProperty("cartId")
    private String cartId;
    @JsonProperty("creditBlockBypassFlag")
    private Boolean creditBlockBypassFlag;
    @JsonProperty("orderSummaryFileDetails")
    private OrderSummaryFileDetails orderSummaryFileDetails;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("orderPaymentTransactionType")
    private String orderPaymentTransactionType;
    @JsonProperty("orderConfirmedBy")
    private String orderConfirmedBy;
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

    @JsonProperty("ordNumber")
    public String getOrdNumber() {
        return ordNumber;
    }

    @JsonProperty("ordNumber")
    public void setOrdNumber(String ordNumber) {
        this.ordNumber = ordNumber;
    }

    @JsonProperty("currentState")
    public CurrentState getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
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

    @JsonProperty("discountInfo")
    public DiscountInfo getDiscountInfo() {
        return discountInfo;
    }

    @JsonProperty("discountInfo")
    public void setDiscountInfo(DiscountInfo discountInfo) {
        this.discountInfo = discountInfo;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("orderDates")
    public OrderDates getOrderDates() {
        return orderDates;
    }

    @JsonProperty("orderDates")
    public void setOrderDates(OrderDates orderDates) {
        this.orderDates = orderDates;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("ctId")
    public Object getCtId() {
        return ctId;
    }

    @JsonProperty("ctId")
    public void setCtId(Object ctId) {
        this.ctId = ctId;
    }

    @JsonProperty("priorityId")
    public Object getPriorityId() {
        return priorityId;
    }

    @JsonProperty("priorityId")
    public void setPriorityId(Object priorityId) {
        this.priorityId = priorityId;
    }

    @JsonProperty("customerId")
    public Integer getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("cashback")
    public Double getCashback() {
        return cashback;
    }

    @JsonProperty("cashback")
    public void setCashback(Double cashback) {
        this.cashback = cashback;
    }

    @JsonProperty("buyerCashback")
    public Object getBuyerCashback() {
        return buyerCashback;
    }

    @JsonProperty("buyerCashback")
    public void setBuyerCashback(Object buyerCashback) {
        this.buyerCashback = buyerCashback;
    }

    @JsonProperty("channelOfOrder")
    public Object getChannelOfOrder() {
        return channelOfOrder;
    }

    @JsonProperty("channelOfOrder")
    public void setChannelOfOrder(Object channelOfOrder) {
        this.channelOfOrder = channelOfOrder;
    }

    @JsonProperty("opsSpoc")
    public String getOpsSpoc() {
        return opsSpoc;
    }

    @JsonProperty("opsSpoc")
    public void setOpsSpoc(String opsSpoc) {
        this.opsSpoc = opsSpoc;
    }

    @JsonProperty("salesSpoc")
    public Object getSalesSpoc() {
        return salesSpoc;
    }

    @JsonProperty("salesSpoc")
    public void setSalesSpoc(Object salesSpoc) {
        this.salesSpoc = salesSpoc;
    }

    @JsonProperty("reasonForCancellation")
    public Object getReasonForCancellation() {
        return reasonForCancellation;
    }

    @JsonProperty("reasonForCancellation")
    public void setReasonForCancellation(Object reasonForCancellation) {
        this.reasonForCancellation = reasonForCancellation;
    }

    @JsonProperty("reasonForDecline")
    public Object getReasonForDecline() {
        return reasonForDecline;
    }

    @JsonProperty("reasonForDecline")
    public void setReasonForDecline(Object reasonForDecline) {
        this.reasonForDecline = reasonForDecline;
    }

    @JsonProperty("specialDeliveryInstructions")
    public Object getSpecialDeliveryInstructions() {
        return specialDeliveryInstructions;
    }

    @JsonProperty("specialDeliveryInstructions")
    public void setSpecialDeliveryInstructions(Object specialDeliveryInstructions) {
        this.specialDeliveryInstructions = specialDeliveryInstructions;
    }

    @JsonProperty("buyerComments")
    public Object getBuyerComments() {
        return buyerComments;
    }

    @JsonProperty("buyerComments")
    public void setBuyerComments(Object buyerComments) {
        this.buyerComments = buyerComments;
    }

    @JsonProperty("sellerComments")
    public Object getSellerComments() {
        return sellerComments;
    }

    @JsonProperty("sellerComments")
    public void setSellerComments(Object sellerComments) {
        this.sellerComments = sellerComments;
    }

    @JsonProperty("salesForceOpportunityId")
    public String getSalesForceOpportunityId() {
        return salesForceOpportunityId;
    }

    @JsonProperty("salesForceOpportunityId")
    public void setSalesForceOpportunityId(String salesForceOpportunityId) {
        this.salesForceOpportunityId = salesForceOpportunityId;
    }

    @JsonProperty("salesForceOpportunityUrl")
    public Object getSalesForceOpportunityUrl() {
        return salesForceOpportunityUrl;
    }

    @JsonProperty("salesForceOpportunityUrl")
    public void setSalesForceOpportunityUrl(Object salesForceOpportunityUrl) {
        this.salesForceOpportunityUrl = salesForceOpportunityUrl;
    }

    @JsonProperty("opportunityString")
    public Object getOpportunityString() {
        return opportunityString;
    }

    @JsonProperty("opportunityString")
    public void setOpportunityString(Object opportunityString) {
        this.opportunityString = opportunityString;
    }

    @JsonProperty("files")
    public Object getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(Object files) {
        this.files = files;
    }

    @JsonProperty("isZeroAdvanceOrder")
    public Boolean getIsZeroAdvanceOrder() {
        return isZeroAdvanceOrder;
    }

    @JsonProperty("isZeroAdvanceOrder")
    public void setIsZeroAdvanceOrder(Boolean isZeroAdvanceOrder) {
        this.isZeroAdvanceOrder = isZeroAdvanceOrder;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("sapMetadataJson")
    public SapMetadataJson getSapMetadataJson() {
        return sapMetadataJson;
    }

    @JsonProperty("sapMetadataJson")
    public void setSapMetadataJson(SapMetadataJson sapMetadataJson) {
        this.sapMetadataJson = sapMetadataJson;
    }

    @JsonProperty("orderTermDetails")
    public Object getOrderTermDetails() {
        return orderTermDetails;
    }

    @JsonProperty("orderTermDetails")
    public void setOrderTermDetails(Object orderTermDetails) {
        this.orderTermDetails = orderTermDetails;
    }

    @JsonProperty("isVerifiedPO")
    public Boolean getIsVerifiedPO() {
        return isVerifiedPO;
    }

    @JsonProperty("isVerifiedPO")
    public void setIsVerifiedPO(Boolean isVerifiedPO) {
        this.isVerifiedPO = isVerifiedPO;
    }

    @JsonProperty("isVerifiedSO")
    public Boolean getIsVerifiedSO() {
        return isVerifiedSO;
    }

    @JsonProperty("isVerifiedSO")
    public void setIsVerifiedSO(Boolean isVerifiedSO) {
        this.isVerifiedSO = isVerifiedSO;
    }

    @JsonProperty("orderRefundCount")
    public Integer getOrderRefundCount() {
        return orderRefundCount;
    }

    @JsonProperty("orderRefundCount")
    public void setOrderRefundCount(Integer orderRefundCount) {
        this.orderRefundCount = orderRefundCount;
    }

    @JsonProperty("orderPaymentType")
    public String getOrderPaymentType() {
        return orderPaymentType;
    }

    @JsonProperty("orderPaymentType")
    public void setOrderPaymentType(String orderPaymentType) {
        this.orderPaymentType = orderPaymentType;
    }

    @JsonProperty("isMoeEventTriggered")
    public Object getIsMoeEventTriggered() {
        return isMoeEventTriggered;
    }

    @JsonProperty("isMoeEventTriggered")
    public void setIsMoeEventTriggered(Object isMoeEventTriggered) {
        this.isMoeEventTriggered = isMoeEventTriggered;
    }

    @JsonProperty("advancePercentage")
    public Double getAdvancePercentage() {
        return advancePercentage;
    }

    @JsonProperty("advancePercentage")
    public void setAdvancePercentage(Double advancePercentage) {
        this.advancePercentage = advancePercentage;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("createdDate")
    public List<Integer> getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(List<Integer> createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("lastModifiedBy")
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @JsonProperty("lastModifiedBy")
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @JsonProperty("lastModifiedDate")
    public List<Integer> getLastModifiedDate() {
        return lastModifiedDate;
    }

    @JsonProperty("lastModifiedDate")
    public void setLastModifiedDate(List<Integer> lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @JsonProperty("orderCreatedDate")
    public List<Integer> getOrderCreatedDate() {
        return orderCreatedDate;
    }

    @JsonProperty("orderCreatedDate")
    public void setOrderCreatedDate(List<Integer> orderCreatedDate) {
        this.orderCreatedDate = orderCreatedDate;
    }

    @JsonProperty("orderPlacedDate")
    public Object getOrderPlacedDate() {
        return orderPlacedDate;
    }

    @JsonProperty("orderPlacedDate")
    public void setOrderPlacedDate(Object orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate;
    }

    @JsonProperty("committedDeliveryDate")
    public Object getCommittedDeliveryDate() {
        return committedDeliveryDate;
    }

    @JsonProperty("committedDeliveryDate")
    public void setCommittedDeliveryDate(Object committedDeliveryDate) {
        this.committedDeliveryDate = committedDeliveryDate;
    }

    @JsonProperty("scheduledDeliveryDate")
    public Object getScheduledDeliveryDate() {
        return scheduledDeliveryDate;
    }

    @JsonProperty("scheduledDeliveryDate")
    public void setScheduledDeliveryDate(Object scheduledDeliveryDate) {
        this.scheduledDeliveryDate = scheduledDeliveryDate;
    }

    @JsonProperty("nextActionDate")
    public Object getNextActionDate() {
        return nextActionDate;
    }

    @JsonProperty("nextActionDate")
    public void setNextActionDate(Object nextActionDate) {
        this.nextActionDate = nextActionDate;
    }

    @JsonProperty("paymentCompletionDate")
    public Object getPaymentCompletionDate() {
        return paymentCompletionDate;
    }

    @JsonProperty("paymentCompletionDate")
    public void setPaymentCompletionDate(Object paymentCompletionDate) {
        this.paymentCompletionDate = paymentCompletionDate;
    }

    @JsonProperty("acceptedDate")
    public Object getAcceptedDate() {
        return acceptedDate;
    }

    @JsonProperty("acceptedDate")
    public void setAcceptedDate(Object acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    @JsonProperty("rejectedDate")
    public Object getRejectedDate() {
        return rejectedDate;
    }

    @JsonProperty("rejectedDate")
    public void setRejectedDate(Object rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    @JsonProperty("cartId")
    public String getCartId() {
        return cartId;
    }

    @JsonProperty("cartId")
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @JsonProperty("creditBlockBypassFlag")
    public Boolean getCreditBlockBypassFlag() {
        return creditBlockBypassFlag;
    }

    @JsonProperty("creditBlockBypassFlag")
    public void setCreditBlockBypassFlag(Boolean creditBlockBypassFlag) {
        this.creditBlockBypassFlag = creditBlockBypassFlag;
    }

    @JsonProperty("orderSummaryFileDetails")
    public OrderSummaryFileDetails getOrderSummaryFileDetails() {
        return orderSummaryFileDetails;
    }

    @JsonProperty("orderSummaryFileDetails")
    public void setOrderSummaryFileDetails(OrderSummaryFileDetails orderSummaryFileDetails) {
        this.orderSummaryFileDetails = orderSummaryFileDetails;
    }

    @JsonProperty("remarks")
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("orderPaymentTransactionType")
    public String getOrderPaymentTransactionType() {
        return orderPaymentTransactionType;
    }

    @JsonProperty("orderPaymentTransactionType")
    public void setOrderPaymentTransactionType(String orderPaymentTransactionType) {
        this.orderPaymentTransactionType = orderPaymentTransactionType;
    }

    @JsonProperty("orderConfirmedBy")
    public String getOrderConfirmedBy() {
        return orderConfirmedBy;
    }

    @JsonProperty("orderConfirmedBy")
    public void setOrderConfirmedBy(String orderConfirmedBy) {
        this.orderConfirmedBy = orderConfirmedBy;
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
        sb.append(ConfirmOrderResp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("ordNumber");
        sb.append('=');
        sb.append(((this.ordNumber == null)?"<null>":this.ordNumber));
        sb.append(',');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
        sb.append(',');
        sb.append("shippingAddress");
        sb.append('=');
        sb.append(((this.shippingAddress == null)?"<null>":this.shippingAddress));
        sb.append(',');
        sb.append("billingAddress");
        sb.append('=');
        sb.append(((this.billingAddress == null)?"<null>":this.billingAddress));
        sb.append(',');
        sb.append("discountInfo");
        sb.append('=');
        sb.append(((this.discountInfo == null)?"<null>":this.discountInfo));
        sb.append(',');
        sb.append("mode");
        sb.append('=');
        sb.append(((this.mode == null)?"<null>":this.mode));
        sb.append(',');
        sb.append("orderDates");
        sb.append('=');
        sb.append(((this.orderDates == null)?"<null>":this.orderDates));
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("ctId");
        sb.append('=');
        sb.append(((this.ctId == null)?"<null>":this.ctId));
        sb.append(',');
        sb.append("priorityId");
        sb.append('=');
        sb.append(((this.priorityId == null)?"<null>":this.priorityId));
        sb.append(',');
        sb.append("customerId");
        sb.append('=');
        sb.append(((this.customerId == null)?"<null>":this.customerId));
        sb.append(',');
        sb.append("cashback");
        sb.append('=');
        sb.append(((this.cashback == null)?"<null>":this.cashback));
        sb.append(',');
        sb.append("buyerCashback");
        sb.append('=');
        sb.append(((this.buyerCashback == null)?"<null>":this.buyerCashback));
        sb.append(',');
        sb.append("channelOfOrder");
        sb.append('=');
        sb.append(((this.channelOfOrder == null)?"<null>":this.channelOfOrder));
        sb.append(',');
        sb.append("opsSpoc");
        sb.append('=');
        sb.append(((this.opsSpoc == null)?"<null>":this.opsSpoc));
        sb.append(',');
        sb.append("salesSpoc");
        sb.append('=');
        sb.append(((this.salesSpoc == null)?"<null>":this.salesSpoc));
        sb.append(',');
        sb.append("reasonForCancellation");
        sb.append('=');
        sb.append(((this.reasonForCancellation == null)?"<null>":this.reasonForCancellation));
        sb.append(',');
        sb.append("reasonForDecline");
        sb.append('=');
        sb.append(((this.reasonForDecline == null)?"<null>":this.reasonForDecline));
        sb.append(',');
        sb.append("specialDeliveryInstructions");
        sb.append('=');
        sb.append(((this.specialDeliveryInstructions == null)?"<null>":this.specialDeliveryInstructions));
        sb.append(',');
        sb.append("buyerComments");
        sb.append('=');
        sb.append(((this.buyerComments == null)?"<null>":this.buyerComments));
        sb.append(',');
        sb.append("sellerComments");
        sb.append('=');
        sb.append(((this.sellerComments == null)?"<null>":this.sellerComments));
        sb.append(',');
        sb.append("salesForceOpportunityId");
        sb.append('=');
        sb.append(((this.salesForceOpportunityId == null)?"<null>":this.salesForceOpportunityId));
        sb.append(',');
        sb.append("salesForceOpportunityUrl");
        sb.append('=');
        sb.append(((this.salesForceOpportunityUrl == null)?"<null>":this.salesForceOpportunityUrl));
        sb.append(',');
        sb.append("opportunityString");
        sb.append('=');
        sb.append(((this.opportunityString == null)?"<null>":this.opportunityString));
        sb.append(',');
        sb.append("files");
        sb.append('=');
        sb.append(((this.files == null)?"<null>":this.files));
        sb.append(',');
        sb.append("isZeroAdvanceOrder");
        sb.append('=');
        sb.append(((this.isZeroAdvanceOrder == null)?"<null>":this.isZeroAdvanceOrder));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null)?"<null>":this.source));
        sb.append(',');
        sb.append("sapMetadataJson");
        sb.append('=');
        sb.append(((this.sapMetadataJson == null)?"<null>":this.sapMetadataJson));
        sb.append(',');
        sb.append("orderTermDetails");
        sb.append('=');
        sb.append(((this.orderTermDetails == null)?"<null>":this.orderTermDetails));
        sb.append(',');
        sb.append("isVerifiedPO");
        sb.append('=');
        sb.append(((this.isVerifiedPO == null)?"<null>":this.isVerifiedPO));
        sb.append(',');
        sb.append("isVerifiedSO");
        sb.append('=');
        sb.append(((this.isVerifiedSO == null)?"<null>":this.isVerifiedSO));
        sb.append(',');
        sb.append("orderRefundCount");
        sb.append('=');
        sb.append(((this.orderRefundCount == null)?"<null>":this.orderRefundCount));
        sb.append(',');
        sb.append("orderPaymentType");
        sb.append('=');
        sb.append(((this.orderPaymentType == null)?"<null>":this.orderPaymentType));
        sb.append(',');
        sb.append("isMoeEventTriggered");
        sb.append('=');
        sb.append(((this.isMoeEventTriggered == null)?"<null>":this.isMoeEventTriggered));
        sb.append(',');
        sb.append("advancePercentage");
        sb.append('=');
        sb.append(((this.advancePercentage == null)?"<null>":this.advancePercentage));
        sb.append(',');
        sb.append("createdBy");
        sb.append('=');
        sb.append(((this.createdBy == null)?"<null>":this.createdBy));
        sb.append(',');
        sb.append("createdDate");
        sb.append('=');
        sb.append(((this.createdDate == null)?"<null>":this.createdDate));
        sb.append(',');
        sb.append("lastModifiedBy");
        sb.append('=');
        sb.append(((this.lastModifiedBy == null)?"<null>":this.lastModifiedBy));
        sb.append(',');
        sb.append("lastModifiedDate");
        sb.append('=');
        sb.append(((this.lastModifiedDate == null)?"<null>":this.lastModifiedDate));
        sb.append(',');
        sb.append("orderCreatedDate");
        sb.append('=');
        sb.append(((this.orderCreatedDate == null)?"<null>":this.orderCreatedDate));
        sb.append(',');
        sb.append("orderPlacedDate");
        sb.append('=');
        sb.append(((this.orderPlacedDate == null)?"<null>":this.orderPlacedDate));
        sb.append(',');
        sb.append("committedDeliveryDate");
        sb.append('=');
        sb.append(((this.committedDeliveryDate == null)?"<null>":this.committedDeliveryDate));
        sb.append(',');
        sb.append("scheduledDeliveryDate");
        sb.append('=');
        sb.append(((this.scheduledDeliveryDate == null)?"<null>":this.scheduledDeliveryDate));
        sb.append(',');
        sb.append("nextActionDate");
        sb.append('=');
        sb.append(((this.nextActionDate == null)?"<null>":this.nextActionDate));
        sb.append(',');
        sb.append("paymentCompletionDate");
        sb.append('=');
        sb.append(((this.paymentCompletionDate == null)?"<null>":this.paymentCompletionDate));
        sb.append(',');
        sb.append("acceptedDate");
        sb.append('=');
        sb.append(((this.acceptedDate == null)?"<null>":this.acceptedDate));
        sb.append(',');
        sb.append("rejectedDate");
        sb.append('=');
        sb.append(((this.rejectedDate == null)?"<null>":this.rejectedDate));
        sb.append(',');
        sb.append("cartId");
        sb.append('=');
        sb.append(((this.cartId == null)?"<null>":this.cartId));
        sb.append(',');
        sb.append("creditBlockBypassFlag");
        sb.append('=');
        sb.append(((this.creditBlockBypassFlag == null)?"<null>":this.creditBlockBypassFlag));
        sb.append(',');
        sb.append("orderSummaryFileDetails");
        sb.append('=');
        sb.append(((this.orderSummaryFileDetails == null)?"<null>":this.orderSummaryFileDetails));
        sb.append(',');
        sb.append("remarks");
        sb.append('=');
        sb.append(((this.remarks == null)?"<null>":this.remarks));
        sb.append(',');
        sb.append("orderPaymentTransactionType");
        sb.append('=');
        sb.append(((this.orderPaymentTransactionType == null)?"<null>":this.orderPaymentTransactionType));
        sb.append(',');
        sb.append("orderConfirmedBy");
        sb.append('=');
        sb.append(((this.orderConfirmedBy == null)?"<null>":this.orderConfirmedBy));
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
        result = ((result* 31)+((this.creditBlockBypassFlag == null)? 0 :this.creditBlockBypassFlag.hashCode()));
        result = ((result* 31)+((this.isZeroAdvanceOrder == null)? 0 :this.isZeroAdvanceOrder.hashCode()));
        result = ((result* 31)+((this.orderPaymentTransactionType == null)? 0 :this.orderPaymentTransactionType.hashCode()));
        result = ((result* 31)+((this.orderRefundCount == null)? 0 :this.orderRefundCount.hashCode()));
        result = ((result* 31)+((this.source == null)? 0 :this.source.hashCode()));
        result = ((result* 31)+((this.acceptedDate == null)? 0 :this.acceptedDate.hashCode()));
        result = ((result* 31)+((this.priorityId == null)? 0 :this.priorityId.hashCode()));
        result = ((result* 31)+((this.mode == null)? 0 :this.mode.hashCode()));
        result = ((result* 31)+((this.isVerifiedSO == null)? 0 :this.isVerifiedSO.hashCode()));
        result = ((result* 31)+((this.buyerComments == null)? 0 :this.buyerComments.hashCode()));
        result = ((result* 31)+((this.ordNumber == null)? 0 :this.ordNumber.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.salesSpoc == null)? 0 :this.salesSpoc.hashCode()));
        result = ((result* 31)+((this.orderCreatedDate == null)? 0 :this.orderCreatedDate.hashCode()));
        result = ((result* 31)+((this.committedDeliveryDate == null)? 0 :this.committedDeliveryDate.hashCode()));
        result = ((result* 31)+((this.specialDeliveryInstructions == null)? 0 :this.specialDeliveryInstructions.hashCode()));
        result = ((result* 31)+((this.advancePercentage == null)? 0 :this.advancePercentage.hashCode()));
        result = ((result* 31)+((this.cartId == null)? 0 :this.cartId.hashCode()));
        result = ((result* 31)+((this.opsSpoc == null)? 0 :this.opsSpoc.hashCode()));
        result = ((result* 31)+((this.discountInfo == null)? 0 :this.discountInfo.hashCode()));
        result = ((result* 31)+((this.reasonForDecline == null)? 0 :this.reasonForDecline.hashCode()));
        result = ((result* 31)+((this.orderConfirmedBy == null)? 0 :this.orderConfirmedBy.hashCode()));
        result = ((result* 31)+((this.sellerComments == null)? 0 :this.sellerComments.hashCode()));
        result = ((result* 31)+((this.files == null)? 0 :this.files.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.ctId == null)? 0 :this.ctId.hashCode()));
        result = ((result* 31)+((this.orderDates == null)? 0 :this.orderDates.hashCode()));
        result = ((result* 31)+((this.nextActionDate == null)? 0 :this.nextActionDate.hashCode()));
        result = ((result* 31)+((this.paymentCompletionDate == null)? 0 :this.paymentCompletionDate.hashCode()));
        result = ((result* 31)+((this.orderSummaryFileDetails == null)? 0 :this.orderSummaryFileDetails.hashCode()));
        result = ((result* 31)+((this.orderPaymentType == null)? 0 :this.orderPaymentType.hashCode()));
        result = ((result* 31)+((this.scheduledDeliveryDate == null)? 0 :this.scheduledDeliveryDate.hashCode()));
        result = ((result* 31)+((this.sapMetadataJson == null)? 0 :this.sapMetadataJson.hashCode()));
        result = ((result* 31)+((this.orderPlacedDate == null)? 0 :this.orderPlacedDate.hashCode()));
        result = ((result* 31)+((this.customerId == null)? 0 :this.customerId.hashCode()));
        result = ((result* 31)+((this.buyerCashback == null)? 0 :this.buyerCashback.hashCode()));
        result = ((result* 31)+((this.orderTermDetails == null)? 0 :this.orderTermDetails.hashCode()));
        result = ((result* 31)+((this.salesForceOpportunityUrl == null)? 0 :this.salesForceOpportunityUrl.hashCode()));
        result = ((result* 31)+((this.isMoeEventTriggered == null)? 0 :this.isMoeEventTriggered.hashCode()));
        result = ((result* 31)+((this.lastModifiedDate == null)? 0 :this.lastModifiedDate.hashCode()));
        result = ((result* 31)+((this.lastModifiedBy == null)? 0 :this.lastModifiedBy.hashCode()));
        result = ((result* 31)+((this.reasonForCancellation == null)? 0 :this.reasonForCancellation.hashCode()));
        result = ((result* 31)+((this.rejectedDate == null)? 0 :this.rejectedDate.hashCode()));
        result = ((result* 31)+((this.cashback == null)? 0 :this.cashback.hashCode()));
        result = ((result* 31)+((this.isVerifiedPO == null)? 0 :this.isVerifiedPO.hashCode()));
        result = ((result* 31)+((this.createdDate == null)? 0 :this.createdDate.hashCode()));
        result = ((result* 31)+((this.createdBy == null)? 0 :this.createdBy.hashCode()));
        result = ((result* 31)+((this.opportunityString == null)? 0 :this.opportunityString.hashCode()));
        result = ((result* 31)+((this.shippingAddress == null)? 0 :this.shippingAddress.hashCode()));
        result = ((result* 31)+((this.billingAddress == null)? 0 :this.billingAddress.hashCode()));
        result = ((result* 31)+((this.salesForceOpportunityId == null)? 0 :this.salesForceOpportunityId.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        result = ((result* 31)+((this.category == null)? 0 :this.category.hashCode()));
        result = ((result* 31)+((this.channelOfOrder == null)? 0 :this.channelOfOrder.hashCode()));
        result = ((result* 31)+((this.remarks == null)? 0 :this.remarks.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConfirmOrderResp) == false) {
            return false;
        }
        ConfirmOrderResp rhs = ((ConfirmOrderResp) other);
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.creditBlockBypassFlag == rhs.creditBlockBypassFlag)||((this.creditBlockBypassFlag!= null)&&this.creditBlockBypassFlag.equals(rhs.creditBlockBypassFlag)))&&((this.isZeroAdvanceOrder == rhs.isZeroAdvanceOrder)||((this.isZeroAdvanceOrder!= null)&&this.isZeroAdvanceOrder.equals(rhs.isZeroAdvanceOrder))))&&((this.orderPaymentTransactionType == rhs.orderPaymentTransactionType)||((this.orderPaymentTransactionType!= null)&&this.orderPaymentTransactionType.equals(rhs.orderPaymentTransactionType))))&&((this.orderRefundCount == rhs.orderRefundCount)||((this.orderRefundCount!= null)&&this.orderRefundCount.equals(rhs.orderRefundCount))))&&((this.source == rhs.source)||((this.source!= null)&&this.source.equals(rhs.source))))&&((this.acceptedDate == rhs.acceptedDate)||((this.acceptedDate!= null)&&this.acceptedDate.equals(rhs.acceptedDate))))&&((this.priorityId == rhs.priorityId)||((this.priorityId!= null)&&this.priorityId.equals(rhs.priorityId))))&&((this.mode == rhs.mode)||((this.mode!= null)&&this.mode.equals(rhs.mode))))&&((this.isVerifiedSO == rhs.isVerifiedSO)||((this.isVerifiedSO!= null)&&this.isVerifiedSO.equals(rhs.isVerifiedSO))))&&((this.buyerComments == rhs.buyerComments)||((this.buyerComments!= null)&&this.buyerComments.equals(rhs.buyerComments))))&&((this.ordNumber == rhs.ordNumber)||((this.ordNumber!= null)&&this.ordNumber.equals(rhs.ordNumber))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.salesSpoc == rhs.salesSpoc)||((this.salesSpoc!= null)&&this.salesSpoc.equals(rhs.salesSpoc))))&&((this.orderCreatedDate == rhs.orderCreatedDate)||((this.orderCreatedDate!= null)&&this.orderCreatedDate.equals(rhs.orderCreatedDate))))&&((this.committedDeliveryDate == rhs.committedDeliveryDate)||((this.committedDeliveryDate!= null)&&this.committedDeliveryDate.equals(rhs.committedDeliveryDate))))&&((this.specialDeliveryInstructions == rhs.specialDeliveryInstructions)||((this.specialDeliveryInstructions!= null)&&this.specialDeliveryInstructions.equals(rhs.specialDeliveryInstructions))))&&((this.advancePercentage == rhs.advancePercentage)||((this.advancePercentage!= null)&&this.advancePercentage.equals(rhs.advancePercentage))))&&((this.cartId == rhs.cartId)||((this.cartId!= null)&&this.cartId.equals(rhs.cartId))))&&((this.opsSpoc == rhs.opsSpoc)||((this.opsSpoc!= null)&&this.opsSpoc.equals(rhs.opsSpoc))))&&((this.discountInfo == rhs.discountInfo)||((this.discountInfo!= null)&&this.discountInfo.equals(rhs.discountInfo))))&&((this.reasonForDecline == rhs.reasonForDecline)||((this.reasonForDecline!= null)&&this.reasonForDecline.equals(rhs.reasonForDecline))))&&((this.orderConfirmedBy == rhs.orderConfirmedBy)||((this.orderConfirmedBy!= null)&&this.orderConfirmedBy.equals(rhs.orderConfirmedBy))))&&((this.sellerComments == rhs.sellerComments)||((this.sellerComments!= null)&&this.sellerComments.equals(rhs.sellerComments))))&&((this.files == rhs.files)||((this.files!= null)&&this.files.equals(rhs.files))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.ctId == rhs.ctId)||((this.ctId!= null)&&this.ctId.equals(rhs.ctId))))&&((this.orderDates == rhs.orderDates)||((this.orderDates!= null)&&this.orderDates.equals(rhs.orderDates))))&&((this.nextActionDate == rhs.nextActionDate)||((this.nextActionDate!= null)&&this.nextActionDate.equals(rhs.nextActionDate))))&&((this.paymentCompletionDate == rhs.paymentCompletionDate)||((this.paymentCompletionDate!= null)&&this.paymentCompletionDate.equals(rhs.paymentCompletionDate))))&&((this.orderSummaryFileDetails == rhs.orderSummaryFileDetails)||((this.orderSummaryFileDetails!= null)&&this.orderSummaryFileDetails.equals(rhs.orderSummaryFileDetails))))&&((this.orderPaymentType == rhs.orderPaymentType)||((this.orderPaymentType!= null)&&this.orderPaymentType.equals(rhs.orderPaymentType))))&&((this.scheduledDeliveryDate == rhs.scheduledDeliveryDate)||((this.scheduledDeliveryDate!= null)&&this.scheduledDeliveryDate.equals(rhs.scheduledDeliveryDate))))&&((this.sapMetadataJson == rhs.sapMetadataJson)||((this.sapMetadataJson!= null)&&this.sapMetadataJson.equals(rhs.sapMetadataJson))))&&((this.orderPlacedDate == rhs.orderPlacedDate)||((this.orderPlacedDate!= null)&&this.orderPlacedDate.equals(rhs.orderPlacedDate))))&&((this.customerId == rhs.customerId)||((this.customerId!= null)&&this.customerId.equals(rhs.customerId))))&&((this.buyerCashback == rhs.buyerCashback)||((this.buyerCashback!= null)&&this.buyerCashback.equals(rhs.buyerCashback))))&&((this.orderTermDetails == rhs.orderTermDetails)||((this.orderTermDetails!= null)&&this.orderTermDetails.equals(rhs.orderTermDetails))))&&((this.salesForceOpportunityUrl == rhs.salesForceOpportunityUrl)||((this.salesForceOpportunityUrl!= null)&&this.salesForceOpportunityUrl.equals(rhs.salesForceOpportunityUrl))))&&((this.isMoeEventTriggered == rhs.isMoeEventTriggered)||((this.isMoeEventTriggered!= null)&&this.isMoeEventTriggered.equals(rhs.isMoeEventTriggered))))&&((this.lastModifiedDate == rhs.lastModifiedDate)||((this.lastModifiedDate!= null)&&this.lastModifiedDate.equals(rhs.lastModifiedDate))))&&((this.lastModifiedBy == rhs.lastModifiedBy)||((this.lastModifiedBy!= null)&&this.lastModifiedBy.equals(rhs.lastModifiedBy))))&&((this.reasonForCancellation == rhs.reasonForCancellation)||((this.reasonForCancellation!= null)&&this.reasonForCancellation.equals(rhs.reasonForCancellation))))&&((this.rejectedDate == rhs.rejectedDate)||((this.rejectedDate!= null)&&this.rejectedDate.equals(rhs.rejectedDate))))&&((this.cashback == rhs.cashback)||((this.cashback!= null)&&this.cashback.equals(rhs.cashback))))&&((this.isVerifiedPO == rhs.isVerifiedPO)||((this.isVerifiedPO!= null)&&this.isVerifiedPO.equals(rhs.isVerifiedPO))))&&((this.createdDate == rhs.createdDate)||((this.createdDate!= null)&&this.createdDate.equals(rhs.createdDate))))&&((this.createdBy == rhs.createdBy)||((this.createdBy!= null)&&this.createdBy.equals(rhs.createdBy))))&&((this.opportunityString == rhs.opportunityString)||((this.opportunityString!= null)&&this.opportunityString.equals(rhs.opportunityString))))&&((this.shippingAddress == rhs.shippingAddress)||((this.shippingAddress!= null)&&this.shippingAddress.equals(rhs.shippingAddress))))&&((this.billingAddress == rhs.billingAddress)||((this.billingAddress!= null)&&this.billingAddress.equals(rhs.billingAddress))))&&((this.salesForceOpportunityId == rhs.salesForceOpportunityId)||((this.salesForceOpportunityId!= null)&&this.salesForceOpportunityId.equals(rhs.salesForceOpportunityId))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))))&&((this.category == rhs.category)||((this.category!= null)&&this.category.equals(rhs.category))))&&((this.channelOfOrder == rhs.channelOfOrder)||((this.channelOfOrder!= null)&&this.channelOfOrder.equals(rhs.channelOfOrder))))&&((this.remarks == rhs.remarks)||((this.remarks!= null)&&this.remarks.equals(rhs.remarks))));
    }

}
