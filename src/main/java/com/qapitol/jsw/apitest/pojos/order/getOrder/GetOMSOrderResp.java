
package com.qapitol.jsw.apitest.pojos.order.getOrder;

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
    "orderNumber",
    "id",
    "buyerDetails",
    "orderAmount",
    "orderStatus",
    "nextOrderStates",
    "paymentType",
    "paymentStatus",
    "mode",
    "deliveryType",
    "amounts",
    "orderType",
    "channels",
    "childOrders",
    "businessUnit",
    "salesforceOpportunityId",
    "orderDate",
    "cashBacks",
    "sellerDetails",
    "category",
    "isMultiSeller",
    "orderRefundCount",
    "orderPaymentType",
    "advancePercentage",
    "orderPaymentTransactionType",
    "isZohoCustomer",
    "creditBlockBypassFlag",
    "isZeroAdvanceOrder"
})
@Generated("jsonschema2pojo")
public class GetOMSOrderResp {

    @JsonProperty("orderNumber")
    private String orderNumber;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("buyerDetails")
    private BuyerDetails buyerDetails;
    @JsonProperty("orderAmount")
    private OrderAmount orderAmount;
    @JsonProperty("orderStatus")
    private String orderStatus;
    @JsonProperty("nextOrderStates")
    private List<NextOrderState> nextOrderStates = new ArrayList<NextOrderState>();
    @JsonProperty("paymentType")
    private String paymentType;
    @JsonProperty("paymentStatus")
    private String paymentStatus;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("amounts")
    private Map<String,Amounts> amounts;
    @JsonProperty("orderType")
    private String orderType;
    @JsonProperty("channels")
    private List<String> channels = new ArrayList<String>();
    @JsonProperty("childOrders")
    private List<ChildOrder> childOrders = new ArrayList<ChildOrder>();
    @JsonProperty("businessUnit")
    private String businessUnit;
    @JsonProperty("salesforceOpportunityId")
    private String salesforceOpportunityId;
    @JsonProperty("orderDate")
    private String orderDate;
    @JsonProperty("cashBacks")
    private Map<String,CashBacks> cashBacks;
    @JsonProperty("sellerDetails")
    private SellerDetails sellerDetails;
    @JsonProperty("category")
    private String category;
    @JsonProperty("isMultiSeller")
    private Boolean isMultiSeller;
    @JsonProperty("orderRefundCount")
    private Integer orderRefundCount;
    @JsonProperty("orderPaymentType")
    private String orderPaymentType;
    @JsonProperty("advancePercentage")
    private Double advancePercentage;
    @JsonProperty("orderPaymentTransactionType")
    private String orderPaymentTransactionType;
    @JsonProperty("isZohoCustomer")
    private Boolean isZohoCustomer;
    @JsonProperty("creditBlockBypassFlag")
    private Boolean creditBlockBypassFlag;
    @JsonProperty("isZeroAdvanceOrder")
    private Boolean isZeroAdvanceOrder;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("orderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("buyerDetails")
    public BuyerDetails getBuyerDetails() {
        return buyerDetails;
    }

    @JsonProperty("buyerDetails")
    public void setBuyerDetails(BuyerDetails buyerDetails) {
        this.buyerDetails = buyerDetails;
    }

    @JsonProperty("orderAmount")
    public OrderAmount getOrderAmount() {
        return orderAmount;
    }

    @JsonProperty("orderAmount")
    public void setOrderAmount(OrderAmount orderAmount) {
        this.orderAmount = orderAmount;
    }

    @JsonProperty("orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("orderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("nextOrderStates")
    public List<NextOrderState> getNextOrderStates() {
        return nextOrderStates;
    }

    @JsonProperty("nextOrderStates")
    public void setNextOrderStates(List<NextOrderState> nextOrderStates) {
        this.nextOrderStates = nextOrderStates;
    }

    @JsonProperty("paymentType")
    public String getPaymentType() {
        return paymentType;
    }

    @JsonProperty("paymentType")
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("paymentStatus")
    public String getPaymentStatus() {
        return paymentStatus;
    }

    @JsonProperty("paymentStatus")
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("deliveryType")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonProperty("amounts")
    public Map<String, Amounts> getAmounts() {
        return amounts;
    }

    @JsonProperty("amounts")
    public void setAmounts(Map<String, Amounts> amounts) {
        this.amounts = amounts;
    }

    @JsonProperty("orderType")
    public String getOrderType() {
        return orderType;
    }

    @JsonProperty("orderType")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @JsonProperty("channels")
    public List<String> getChannels() {
        return channels;
    }

    @JsonProperty("channels")
    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    @JsonProperty("childOrders")
    public List<ChildOrder> getChildOrders() {
        return childOrders;
    }

    @JsonProperty("childOrders")
    public void setChildOrders(List<ChildOrder> childOrders) {
        this.childOrders = childOrders;
    }

    @JsonProperty("businessUnit")
    public String getBusinessUnit() {
        return businessUnit;
    }

    @JsonProperty("businessUnit")
    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    @JsonProperty("salesforceOpportunityId")
    public String getSalesforceOpportunityId() {
        return salesforceOpportunityId;
    }

    @JsonProperty("salesforceOpportunityId")
    public void setSalesforceOpportunityId(String salesforceOpportunityId) {
        this.salesforceOpportunityId = salesforceOpportunityId;
    }

    @JsonProperty("orderDate")
    public String getOrderDate() {
        return orderDate;
    }

    @JsonProperty("orderDate")
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @JsonProperty("cashBacks")
    public Map<String, CashBacks> getCashBacks() {
        return cashBacks;
    }

    @JsonProperty("cashBacks")
    public void setCashBacks(Map<String,CashBacks> cashBacks) {
        this.cashBacks = cashBacks;
    }

    @JsonProperty("sellerDetails")
    public SellerDetails getSellerDetails() {
        return sellerDetails;
    }

    @JsonProperty("sellerDetails")
    public void setSellerDetails(SellerDetails sellerDetails) {
        this.sellerDetails = sellerDetails;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("isMultiSeller")
    public Boolean getIsMultiSeller() {
        return isMultiSeller;
    }

    @JsonProperty("isMultiSeller")
    public void setIsMultiSeller(Boolean isMultiSeller) {
        this.isMultiSeller = isMultiSeller;
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

    @JsonProperty("advancePercentage")
    public Double getAdvancePercentage() {
        return advancePercentage;
    }

    @JsonProperty("advancePercentage")
    public void setAdvancePercentage(Double advancePercentage) {
        this.advancePercentage = advancePercentage;
    }

    @JsonProperty("orderPaymentTransactionType")
    public String getOrderPaymentTransactionType() {
        return orderPaymentTransactionType;
    }

    @JsonProperty("orderPaymentTransactionType")
    public void setOrderPaymentTransactionType(String orderPaymentTransactionType) {
        this.orderPaymentTransactionType = orderPaymentTransactionType;
    }

    @JsonProperty("isZohoCustomer")
    public Boolean getIsZohoCustomer() {
        return isZohoCustomer;
    }

    @JsonProperty("isZohoCustomer")
    public void setIsZohoCustomer(Boolean isZohoCustomer) {
        this.isZohoCustomer = isZohoCustomer;
    }

    @JsonProperty("creditBlockBypassFlag")
    public Boolean getCreditBlockBypassFlag() {
        return creditBlockBypassFlag;
    }

    @JsonProperty("creditBlockBypassFlag")
    public void setCreditBlockBypassFlag(Boolean creditBlockBypassFlag) {
        this.creditBlockBypassFlag = creditBlockBypassFlag;
    }

    @JsonProperty("isZeroAdvanceOrder")
    public Boolean getIsZeroAdvanceOrder() {
        return isZeroAdvanceOrder;
    }

    @JsonProperty("isZeroAdvanceOrder")
    public void setIsZeroAdvanceOrder(Boolean isZeroAdvanceOrder) {
        this.isZeroAdvanceOrder = isZeroAdvanceOrder;
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
        sb.append(GetOMSOrderResp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderNumber");
        sb.append('=');
        sb.append(((this.orderNumber == null)?"<null>":this.orderNumber));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("buyerDetails");
        sb.append('=');
        sb.append(((this.buyerDetails == null)?"<null>":this.buyerDetails));
        sb.append(',');
        sb.append("orderAmount");
        sb.append('=');
        sb.append(((this.orderAmount == null)?"<null>":this.orderAmount));
        sb.append(',');
        sb.append("orderStatus");
        sb.append('=');
        sb.append(((this.orderStatus == null)?"<null>":this.orderStatus));
        sb.append(',');
        sb.append("nextOrderStates");
        sb.append('=');
        sb.append(((this.nextOrderStates == null)?"<null>":this.nextOrderStates));
        sb.append(',');
        sb.append("paymentType");
        sb.append('=');
        sb.append(((this.paymentType == null)?"<null>":this.paymentType));
        sb.append(',');
        sb.append("paymentStatus");
        sb.append('=');
        sb.append(((this.paymentStatus == null)?"<null>":this.paymentStatus));
        sb.append(',');
        sb.append("mode");
        sb.append('=');
        sb.append(((this.mode == null)?"<null>":this.mode));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("amounts");
        sb.append('=');
        sb.append(((this.amounts == null)?"<null>":this.amounts));
        sb.append(',');
        sb.append("orderType");
        sb.append('=');
        sb.append(((this.orderType == null)?"<null>":this.orderType));
        sb.append(',');
        sb.append("channels");
        sb.append('=');
        sb.append(((this.channels == null)?"<null>":this.channels));
        sb.append(',');
        sb.append("childOrders");
        sb.append('=');
        sb.append(((this.childOrders == null)?"<null>":this.childOrders));
        sb.append(',');
        sb.append("businessUnit");
        sb.append('=');
        sb.append(((this.businessUnit == null)?"<null>":this.businessUnit));
        sb.append(',');
        sb.append("salesforceOpportunityId");
        sb.append('=');
        sb.append(((this.salesforceOpportunityId == null)?"<null>":this.salesforceOpportunityId));
        sb.append(',');
        sb.append("orderDate");
        sb.append('=');
        sb.append(((this.orderDate == null)?"<null>":this.orderDate));
        sb.append(',');
        sb.append("cashBacks");
        sb.append('=');
        sb.append(((this.cashBacks == null)?"<null>":this.cashBacks));
        sb.append(',');
        sb.append("sellerDetails");
        sb.append('=');
        sb.append(((this.sellerDetails == null)?"<null>":this.sellerDetails));
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("isMultiSeller");
        sb.append('=');
        sb.append(((this.isMultiSeller == null)?"<null>":this.isMultiSeller));
        sb.append(',');
        sb.append("orderRefundCount");
        sb.append('=');
        sb.append(((this.orderRefundCount == null)?"<null>":this.orderRefundCount));
        sb.append(',');
        sb.append("orderPaymentType");
        sb.append('=');
        sb.append(((this.orderPaymentType == null)?"<null>":this.orderPaymentType));
        sb.append(',');
        sb.append("advancePercentage");
        sb.append('=');
        sb.append(((this.advancePercentage == null)?"<null>":this.advancePercentage));
        sb.append(',');
        sb.append("orderPaymentTransactionType");
        sb.append('=');
        sb.append(((this.orderPaymentTransactionType == null)?"<null>":this.orderPaymentTransactionType));
        sb.append(',');
        sb.append("isZohoCustomer");
        sb.append('=');
        sb.append(((this.isZohoCustomer == null)?"<null>":this.isZohoCustomer));
        sb.append(',');
        sb.append("creditBlockBypassFlag");
        sb.append('=');
        sb.append(((this.creditBlockBypassFlag == null)?"<null>":this.creditBlockBypassFlag));
        sb.append(',');
        sb.append("isZeroAdvanceOrder");
        sb.append('=');
        sb.append(((this.isZeroAdvanceOrder == null)?"<null>":this.isZeroAdvanceOrder));
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
        result = ((result* 31)+((this.orderType == null)? 0 :this.orderType.hashCode()));
        result = ((result* 31)+((this.isZeroAdvanceOrder == null)? 0 :this.isZeroAdvanceOrder.hashCode()));
        result = ((result* 31)+((this.orderNumber == null)? 0 :this.orderNumber.hashCode()));
        result = ((result* 31)+((this.businessUnit == null)? 0 :this.businessUnit.hashCode()));
        result = ((result* 31)+((this.sellerDetails == null)? 0 :this.sellerDetails.hashCode()));
        result = ((result* 31)+((this.cashBacks == null)? 0 :this.cashBacks.hashCode()));
        result = ((result* 31)+((this.orderPaymentTransactionType == null)? 0 :this.orderPaymentTransactionType.hashCode()));
        result = ((result* 31)+((this.orderStatus == null)? 0 :this.orderStatus.hashCode()));
        result = ((result* 31)+((this.orderRefundCount == null)? 0 :this.orderRefundCount.hashCode()));
        result = ((result* 31)+((this.paymentType == null)? 0 :this.paymentType.hashCode()));
        result = ((result* 31)+((this.orderPaymentType == null)? 0 :this.orderPaymentType.hashCode()));
        result = ((result* 31)+((this.mode == null)? 0 :this.mode.hashCode()));
        result = ((result* 31)+((this.isMultiSeller == null)? 0 :this.isMultiSeller.hashCode()));
        result = ((result* 31)+((this.orderAmount == null)? 0 :this.orderAmount.hashCode()));
        result = ((result* 31)+((this.amounts == null)? 0 :this.amounts.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.buyerDetails == null)? 0 :this.buyerDetails.hashCode()));
        result = ((result* 31)+((this.paymentStatus == null)? 0 :this.paymentStatus.hashCode()));
        result = ((result* 31)+((this.salesforceOpportunityId == null)? 0 :this.salesforceOpportunityId.hashCode()));
        result = ((result* 31)+((this.advancePercentage == null)? 0 :this.advancePercentage.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.childOrders == null)? 0 :this.childOrders.hashCode()));
        result = ((result* 31)+((this.isZohoCustomer == null)? 0 :this.isZohoCustomer.hashCode()));
        result = ((result* 31)+((this.channels == null)? 0 :this.channels.hashCode()));
        result = ((result* 31)+((this.nextOrderStates == null)? 0 :this.nextOrderStates.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.category == null)? 0 :this.category.hashCode()));
        result = ((result* 31)+((this.orderDate == null)? 0 :this.orderDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetOMSOrderResp) == false) {
            return false;
        }
        GetOMSOrderResp rhs = ((GetOMSOrderResp) other);
        return ((((((((((((((((((((((((((((((this.creditBlockBypassFlag == rhs.creditBlockBypassFlag)||((this.creditBlockBypassFlag!= null)&&this.creditBlockBypassFlag.equals(rhs.creditBlockBypassFlag)))&&((this.orderType == rhs.orderType)||((this.orderType!= null)&&this.orderType.equals(rhs.orderType))))&&((this.isZeroAdvanceOrder == rhs.isZeroAdvanceOrder)||((this.isZeroAdvanceOrder!= null)&&this.isZeroAdvanceOrder.equals(rhs.isZeroAdvanceOrder))))&&((this.orderNumber == rhs.orderNumber)||((this.orderNumber!= null)&&this.orderNumber.equals(rhs.orderNumber))))&&((this.businessUnit == rhs.businessUnit)||((this.businessUnit!= null)&&this.businessUnit.equals(rhs.businessUnit))))&&((this.sellerDetails == rhs.sellerDetails)||((this.sellerDetails!= null)&&this.sellerDetails.equals(rhs.sellerDetails))))&&((this.cashBacks == rhs.cashBacks)||((this.cashBacks!= null)&&this.cashBacks.equals(rhs.cashBacks))))&&((this.orderPaymentTransactionType == rhs.orderPaymentTransactionType)||((this.orderPaymentTransactionType!= null)&&this.orderPaymentTransactionType.equals(rhs.orderPaymentTransactionType))))&&((this.orderStatus == rhs.orderStatus)||((this.orderStatus!= null)&&this.orderStatus.equals(rhs.orderStatus))))&&((this.orderRefundCount == rhs.orderRefundCount)||((this.orderRefundCount!= null)&&this.orderRefundCount.equals(rhs.orderRefundCount))))&&((this.paymentType == rhs.paymentType)||((this.paymentType!= null)&&this.paymentType.equals(rhs.paymentType))))&&((this.orderPaymentType == rhs.orderPaymentType)||((this.orderPaymentType!= null)&&this.orderPaymentType.equals(rhs.orderPaymentType))))&&((this.mode == rhs.mode)||((this.mode!= null)&&this.mode.equals(rhs.mode))))&&((this.isMultiSeller == rhs.isMultiSeller)||((this.isMultiSeller!= null)&&this.isMultiSeller.equals(rhs.isMultiSeller))))&&((this.orderAmount == rhs.orderAmount)||((this.orderAmount!= null)&&this.orderAmount.equals(rhs.orderAmount))))&&((this.amounts == rhs.amounts)||((this.amounts!= null)&&this.amounts.equals(rhs.amounts))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.buyerDetails == rhs.buyerDetails)||((this.buyerDetails!= null)&&this.buyerDetails.equals(rhs.buyerDetails))))&&((this.paymentStatus == rhs.paymentStatus)||((this.paymentStatus!= null)&&this.paymentStatus.equals(rhs.paymentStatus))))&&((this.salesforceOpportunityId == rhs.salesforceOpportunityId)||((this.salesforceOpportunityId!= null)&&this.salesforceOpportunityId.equals(rhs.salesforceOpportunityId))))&&((this.advancePercentage == rhs.advancePercentage)||((this.advancePercentage!= null)&&this.advancePercentage.equals(rhs.advancePercentage))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.childOrders == rhs.childOrders)||((this.childOrders!= null)&&this.childOrders.equals(rhs.childOrders))))&&((this.isZohoCustomer == rhs.isZohoCustomer)||((this.isZohoCustomer!= null)&&this.isZohoCustomer.equals(rhs.isZohoCustomer))))&&((this.channels == rhs.channels)||((this.channels!= null)&&this.channels.equals(rhs.channels))))&&((this.nextOrderStates == rhs.nextOrderStates)||((this.nextOrderStates!= null)&&this.nextOrderStates.equals(rhs.nextOrderStates))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.category == rhs.category)||((this.category!= null)&&this.category.equals(rhs.category))))&&((this.orderDate == rhs.orderDate)||((this.orderDate!= null)&&this.orderDate.equals(rhs.orderDate))));
    }

}
