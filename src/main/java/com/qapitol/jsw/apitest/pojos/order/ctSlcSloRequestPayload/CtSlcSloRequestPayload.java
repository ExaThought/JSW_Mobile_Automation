
package com.qapitol.jsw.apitest.pojos.order.ctSlcSloRequestPayload;

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
    "slo",
    "shipToType",
    "shippingAddressSFId",
    "shippingAddressId",
    "selfPickup",
    "salesforceOpportunityURL",
    "salesforceOpportunityId",
    "salesAssociate",
    "remarks",
    "ratesValidity",
    "priceType",
    "poDetails",
    "payTo",
    "paymentTerms",
    "orderType",
    "orderPaymentType",
    "opportunityString",
    "opportunityOwnerEmail",
    "lineItems",
    "ledgerBalance",
    "isPBDistributor",
    "isDirect",
    "isBillingAddressValidated",
    "deliveryType",
    "customFields",
    "customerId",
    "creditLimitBypass",
    "channelOfOrder",
    "businessUnit",
    "blockOrderSummaryFlag",
    "billingAddressSFId",
    "billingAddressId",
    "advancePercentage",
    "advancePayment"
})
@Generated("jsonschema2pojo")
public class CtSlcSloRequestPayload {

    @JsonProperty("slo")
    private Boolean slo;
    @JsonProperty("shipToType")
    private String shipToType;
    @JsonProperty("shippingAddressSFId")
    private String shippingAddressSFId;
    @JsonProperty("shippingAddressId")
    private String shippingAddressId;
    @JsonProperty("selfPickup")
    private Boolean selfPickup;
    @JsonProperty("salesforceOpportunityURL")
    private String salesforceOpportunityURL;
    @JsonProperty("salesforceOpportunityId")
    private String salesforceOpportunityId;
    @JsonProperty("salesAssociate")
    private String salesAssociate;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("ratesValidity")
    private String ratesValidity;
    @JsonProperty("priceType")
    private String priceType;
    @JsonProperty("poDetails")
    private String poDetails;
    @JsonProperty("payTo")
    private String payTo;
    @JsonProperty("paymentTerms")
    private String paymentTerms;
    @JsonProperty("orderType")
    private String orderType;
    @JsonProperty("orderPaymentType")
    private String orderPaymentType;
    @JsonProperty("opportunityString")
    private String opportunityString;
    @JsonProperty("opportunityOwnerEmail")
    private String opportunityOwnerEmail;
    @JsonProperty("lineItems")
    private List<LineItem> lineItems = new ArrayList<LineItem>();
    @JsonProperty("ledgerBalance")
    private Double ledgerBalance;
    @JsonProperty("isPBDistributor")
    private Boolean isPBDistributor;
    @JsonProperty("isDirect")
    private Boolean isDirect;
    @JsonProperty("isBillingAddressValidated")
    private Boolean isBillingAddressValidated;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("customFields")
    private CustomFields customFields;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("creditLimitBypass")
    private Boolean creditLimitBypass;
    @JsonProperty("channelOfOrder")
    private String channelOfOrder;
    @JsonProperty("businessUnit")
    private String businessUnit;
    @JsonProperty("blockOrderSummaryFlag")
    private Boolean blockOrderSummaryFlag;
    @JsonProperty("billingAddressSFId")
    private String billingAddressSFId;
    @JsonProperty("billingAddressId")
    private String billingAddressId;
    @JsonProperty("advancePercentage")
    private Double advancePercentage;
    @JsonProperty("advancePayment")
    private Double advancePayment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("slo")
    public Boolean getSlo() {
        return slo;
    }

    @JsonProperty("slo")
    public void setSlo(Boolean slo) {
        this.slo = slo;
    }

    @JsonProperty("shipToType")
    public String getShipToType() {
        return shipToType;
    }

    @JsonProperty("shipToType")
    public void setShipToType(String shipToType) {
        this.shipToType = shipToType;
    }

    @JsonProperty("shippingAddressSFId")
    public String getShippingAddressSFId() {
        return shippingAddressSFId;
    }

    @JsonProperty("shippingAddressSFId")
    public void setShippingAddressSFId(String shippingAddressSFId) {
        this.shippingAddressSFId = shippingAddressSFId;
    }

    @JsonProperty("shippingAddressId")
    public String getShippingAddressId() {
        return shippingAddressId;
    }

    @JsonProperty("shippingAddressId")
    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    @JsonProperty("selfPickup")
    public Boolean getSelfPickup() {
        return selfPickup;
    }

    @JsonProperty("selfPickup")
    public void setSelfPickup(Boolean selfPickup) {
        this.selfPickup = selfPickup;
    }

    @JsonProperty("salesforceOpportunityURL")
    public String getSalesforceOpportunityURL() {
        return salesforceOpportunityURL;
    }

    @JsonProperty("salesforceOpportunityURL")
    public void setSalesforceOpportunityURL(String salesforceOpportunityURL) {
        this.salesforceOpportunityURL = salesforceOpportunityURL;
    }

    @JsonProperty("salesforceOpportunityId")
    public String getSalesforceOpportunityId() {
        return salesforceOpportunityId;
    }

    @JsonProperty("salesforceOpportunityId")
    public void setSalesforceOpportunityId(String salesforceOpportunityId) {
        this.salesforceOpportunityId = salesforceOpportunityId;
    }

    @JsonProperty("salesAssociate")
    public String getSalesAssociate() {
        return salesAssociate;
    }

    @JsonProperty("salesAssociate")
    public void setSalesAssociate(String salesAssociate) {
        this.salesAssociate = salesAssociate;
    }

    @JsonProperty("remarks")
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("ratesValidity")
    public String getRatesValidity() {
        return ratesValidity;
    }

    @JsonProperty("ratesValidity")
    public void setRatesValidity(String ratesValidity) {
        this.ratesValidity = ratesValidity;
    }

    @JsonProperty("priceType")
    public String getPriceType() {
        return priceType;
    }

    @JsonProperty("priceType")
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    @JsonProperty("poDetails")
    public String getPoDetails() {
        return poDetails;
    }

    @JsonProperty("poDetails")
    public void setPoDetails(String poDetails) {
        this.poDetails = poDetails;
    }

    @JsonProperty("payTo")
    public String getPayTo() {
        return payTo;
    }

    @JsonProperty("payTo")
    public void setPayTo(String payTo) {
        this.payTo = payTo;
    }

    @JsonProperty("paymentTerms")
    public String getPaymentTerms() {
        return paymentTerms;
    }

    @JsonProperty("paymentTerms")
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    @JsonProperty("orderType")
    public String getOrderType() {
        return orderType;
    }

    @JsonProperty("orderType")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @JsonProperty("orderPaymentType")
    public String getOrderPaymentType() {
        return orderPaymentType;
    }

    @JsonProperty("orderPaymentType")
    public void setOrderPaymentType(String orderPaymentType) {
        this.orderPaymentType = orderPaymentType;
    }

    @JsonProperty("opportunityString")
    public String getOpportunityString() {
        return opportunityString;
    }

    @JsonProperty("opportunityString")
    public void setOpportunityString(String opportunityString) {
        this.opportunityString = opportunityString;
    }

    @JsonProperty("opportunityOwnerEmail")
    public String getOpportunityOwnerEmail() {
        return opportunityOwnerEmail;
    }

    @JsonProperty("opportunityOwnerEmail")
    public void setOpportunityOwnerEmail(String opportunityOwnerEmail) {
        this.opportunityOwnerEmail = opportunityOwnerEmail;
    }

    @JsonProperty("lineItems")
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    @JsonProperty("lineItems")
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @JsonProperty("ledgerBalance")
    public Double getLedgerBalance() {
        return ledgerBalance;
    }

    @JsonProperty("ledgerBalance")
    public void setLedgerBalance(Double ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    @JsonProperty("isPBDistributor")
    public Boolean getIsPBDistributor() {
        return isPBDistributor;
    }

    @JsonProperty("isPBDistributor")
    public void setIsPBDistributor(Boolean isPBDistributor) {
        this.isPBDistributor = isPBDistributor;
    }

    @JsonProperty("isDirect")
    public Boolean getIsDirect() {
        return isDirect;
    }

    @JsonProperty("isDirect")
    public void setIsDirect(Boolean isDirect) {
        this.isDirect = isDirect;
    }

    @JsonProperty("isBillingAddressValidated")
    public Boolean getIsBillingAddressValidated() {
        return isBillingAddressValidated;
    }

    @JsonProperty("isBillingAddressValidated")
    public void setIsBillingAddressValidated(Boolean isBillingAddressValidated) {
        this.isBillingAddressValidated = isBillingAddressValidated;
    }

    @JsonProperty("deliveryType")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonProperty("customFields")
    public CustomFields getCustomFields() {
        return customFields;
    }

    @JsonProperty("customFields")
    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
    }

    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("creditLimitBypass")
    public Boolean getCreditLimitBypass() {
        return creditLimitBypass;
    }

    @JsonProperty("creditLimitBypass")
    public void setCreditLimitBypass(Boolean creditLimitBypass) {
        this.creditLimitBypass = creditLimitBypass;
    }

    @JsonProperty("channelOfOrder")
    public String getChannelOfOrder() {
        return channelOfOrder;
    }

    @JsonProperty("channelOfOrder")
    public void setChannelOfOrder(String channelOfOrder) {
        this.channelOfOrder = channelOfOrder;
    }

    @JsonProperty("businessUnit")
    public String getBusinessUnit() {
        return businessUnit;
    }

    @JsonProperty("businessUnit")
    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    @JsonProperty("blockOrderSummaryFlag")
    public Boolean getBlockOrderSummaryFlag() {
        return blockOrderSummaryFlag;
    }

    @JsonProperty("blockOrderSummaryFlag")
    public void setBlockOrderSummaryFlag(Boolean blockOrderSummaryFlag) {
        this.blockOrderSummaryFlag = blockOrderSummaryFlag;
    }

    @JsonProperty("billingAddressSFId")
    public String getBillingAddressSFId() {
        return billingAddressSFId;
    }

    @JsonProperty("billingAddressSFId")
    public void setBillingAddressSFId(String billingAddressSFId) {
        this.billingAddressSFId = billingAddressSFId;
    }

    @JsonProperty("billingAddressId")
    public String getBillingAddressId() {
        return billingAddressId;
    }

    @JsonProperty("billingAddressId")
    public void setBillingAddressId(String billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    @JsonProperty("advancePercentage")
    public Double getAdvancePercentage() {
        return advancePercentage;
    }

    @JsonProperty("advancePercentage")
    public void setAdvancePercentage(Double advancePercentage) {
        this.advancePercentage = advancePercentage;
    }

    @JsonProperty("advancePayment")
    public Double getAdvancePayment() {
        return advancePayment;
    }

    @JsonProperty("advancePayment")
    public void setAdvancePayment(Double advancePayment) {
        this.advancePayment = advancePayment;
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
        sb.append(CtSlcSloRequestPayload.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("slo");
        sb.append('=');
        sb.append(((this.slo == null)?"<null>":this.slo));
        sb.append(',');
        sb.append("shipToType");
        sb.append('=');
        sb.append(((this.shipToType == null)?"<null>":this.shipToType));
        sb.append(',');
        sb.append("shippingAddressSFId");
        sb.append('=');
        sb.append(((this.shippingAddressSFId == null)?"<null>":this.shippingAddressSFId));
        sb.append(',');
        sb.append("shippingAddressId");
        sb.append('=');
        sb.append(((this.shippingAddressId == null)?"<null>":this.shippingAddressId));
        sb.append(',');
        sb.append("selfPickup");
        sb.append('=');
        sb.append(((this.selfPickup == null)?"<null>":this.selfPickup));
        sb.append(',');
        sb.append("salesforceOpportunityURL");
        sb.append('=');
        sb.append(((this.salesforceOpportunityURL == null)?"<null>":this.salesforceOpportunityURL));
        sb.append(',');
        sb.append("salesforceOpportunityId");
        sb.append('=');
        sb.append(((this.salesforceOpportunityId == null)?"<null>":this.salesforceOpportunityId));
        sb.append(',');
        sb.append("salesAssociate");
        sb.append('=');
        sb.append(((this.salesAssociate == null)?"<null>":this.salesAssociate));
        sb.append(',');
        sb.append("remarks");
        sb.append('=');
        sb.append(((this.remarks == null)?"<null>":this.remarks));
        sb.append(',');
        sb.append("ratesValidity");
        sb.append('=');
        sb.append(((this.ratesValidity == null)?"<null>":this.ratesValidity));
        sb.append(',');
        sb.append("priceType");
        sb.append('=');
        sb.append(((this.priceType == null)?"<null>":this.priceType));
        sb.append(',');
        sb.append("poDetails");
        sb.append('=');
        sb.append(((this.poDetails == null)?"<null>":this.poDetails));
        sb.append(',');
        sb.append("payTo");
        sb.append('=');
        sb.append(((this.payTo == null)?"<null>":this.payTo));
        sb.append(',');
        sb.append("paymentTerms");
        sb.append('=');
        sb.append(((this.paymentTerms == null)?"<null>":this.paymentTerms));
        sb.append(',');
        sb.append("orderType");
        sb.append('=');
        sb.append(((this.orderType == null)?"<null>":this.orderType));
        sb.append(',');
        sb.append("orderPaymentType");
        sb.append('=');
        sb.append(((this.orderPaymentType == null)?"<null>":this.orderPaymentType));
        sb.append(',');
        sb.append("opportunityString");
        sb.append('=');
        sb.append(((this.opportunityString == null)?"<null>":this.opportunityString));
        sb.append(',');
        sb.append("opportunityOwnerEmail");
        sb.append('=');
        sb.append(((this.opportunityOwnerEmail == null)?"<null>":this.opportunityOwnerEmail));
        sb.append(',');
        sb.append("lineItems");
        sb.append('=');
        sb.append(((this.lineItems == null)?"<null>":this.lineItems));
        sb.append(',');
        sb.append("ledgerBalance");
        sb.append('=');
        sb.append(((this.ledgerBalance == null)?"<null>":this.ledgerBalance));
        sb.append(',');
        sb.append("isPBDistributor");
        sb.append('=');
        sb.append(((this.isPBDistributor == null)?"<null>":this.isPBDistributor));
        sb.append(',');
        sb.append("isDirect");
        sb.append('=');
        sb.append(((this.isDirect == null)?"<null>":this.isDirect));
        sb.append(',');
        sb.append("isBillingAddressValidated");
        sb.append('=');
        sb.append(((this.isBillingAddressValidated == null)?"<null>":this.isBillingAddressValidated));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("customFields");
        sb.append('=');
        sb.append(((this.customFields == null)?"<null>":this.customFields));
        sb.append(',');
        sb.append("customerId");
        sb.append('=');
        sb.append(((this.customerId == null)?"<null>":this.customerId));
        sb.append(',');
        sb.append("creditLimitBypass");
        sb.append('=');
        sb.append(((this.creditLimitBypass == null)?"<null>":this.creditLimitBypass));
        sb.append(',');
        sb.append("channelOfOrder");
        sb.append('=');
        sb.append(((this.channelOfOrder == null)?"<null>":this.channelOfOrder));
        sb.append(',');
        sb.append("businessUnit");
        sb.append('=');
        sb.append(((this.businessUnit == null)?"<null>":this.businessUnit));
        sb.append(',');
        sb.append("blockOrderSummaryFlag");
        sb.append('=');
        sb.append(((this.blockOrderSummaryFlag == null)?"<null>":this.blockOrderSummaryFlag));
        sb.append(',');
        sb.append("billingAddressSFId");
        sb.append('=');
        sb.append(((this.billingAddressSFId == null)?"<null>":this.billingAddressSFId));
        sb.append(',');
        sb.append("billingAddressId");
        sb.append('=');
        sb.append(((this.billingAddressId == null)?"<null>":this.billingAddressId));
        sb.append(',');
        sb.append("advancePercentage");
        sb.append('=');
        sb.append(((this.advancePercentage == null)?"<null>":this.advancePercentage));
        sb.append(',');
        sb.append("advancePayment");
        sb.append('=');
        sb.append(((this.advancePayment == null)?"<null>":this.advancePayment));
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
        result = ((result* 31)+((this.salesAssociate == null)? 0 :this.salesAssociate.hashCode()));
        result = ((result* 31)+((this.ratesValidity == null)? 0 :this.ratesValidity.hashCode()));
        result = ((result* 31)+((this.orderType == null)? 0 :this.orderType.hashCode()));
        result = ((result* 31)+((this.businessUnit == null)? 0 :this.businessUnit.hashCode()));
        result = ((result* 31)+((this.customFields == null)? 0 :this.customFields.hashCode()));
        result = ((result* 31)+((this.shipToType == null)? 0 :this.shipToType.hashCode()));
        result = ((result* 31)+((this.shippingAddressId == null)? 0 :this.shippingAddressId.hashCode()));
        result = ((result* 31)+((this.orderPaymentType == null)? 0 :this.orderPaymentType.hashCode()));
        result = ((result* 31)+((this.lineItems == null)? 0 :this.lineItems.hashCode()));
        result = ((result* 31)+((this.advancePayment == null)? 0 :this.advancePayment.hashCode()));
        result = ((result* 31)+((this.customerId == null)? 0 :this.customerId.hashCode()));
        result = ((result* 31)+((this.selfPickup == null)? 0 :this.selfPickup.hashCode()));
        result = ((result* 31)+((this.billingAddressId == null)? 0 :this.billingAddressId.hashCode()));
        result = ((result* 31)+((this.payTo == null)? 0 :this.payTo.hashCode()));
        result = ((result* 31)+((this.salesforceOpportunityId == null)? 0 :this.salesforceOpportunityId.hashCode()));
        result = ((result* 31)+((this.paymentTerms == null)? 0 :this.paymentTerms.hashCode()));
        result = ((result* 31)+((this.blockOrderSummaryFlag == null)? 0 :this.blockOrderSummaryFlag.hashCode()));
        result = ((result* 31)+((this.advancePercentage == null)? 0 :this.advancePercentage.hashCode()));
        result = ((result* 31)+((this.creditLimitBypass == null)? 0 :this.creditLimitBypass.hashCode()));
        result = ((result* 31)+((this.poDetails == null)? 0 :this.poDetails.hashCode()));
        result = ((result* 31)+((this.salesforceOpportunityURL == null)? 0 :this.salesforceOpportunityURL.hashCode()));
        result = ((result* 31)+((this.priceType == null)? 0 :this.priceType.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.billingAddressSFId == null)? 0 :this.billingAddressSFId.hashCode()));
        result = ((result* 31)+((this.shippingAddressSFId == null)? 0 :this.shippingAddressSFId.hashCode()));
        result = ((result* 31)+((this.ledgerBalance == null)? 0 :this.ledgerBalance.hashCode()));
        result = ((result* 31)+((this.opportunityOwnerEmail == null)? 0 :this.opportunityOwnerEmail.hashCode()));
        result = ((result* 31)+((this.slo == null)? 0 :this.slo.hashCode()));
        result = ((result* 31)+((this.opportunityString == null)? 0 :this.opportunityString.hashCode()));
        result = ((result* 31)+((this.isPBDistributor == null)? 0 :this.isPBDistributor.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.isDirect == null)? 0 :this.isDirect.hashCode()));
        result = ((result* 31)+((this.remarks == null)? 0 :this.remarks.hashCode()));
        result = ((result* 31)+((this.channelOfOrder == null)? 0 :this.channelOfOrder.hashCode()));
        result = ((result* 31)+((this.isBillingAddressValidated == null)? 0 :this.isBillingAddressValidated.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CtSlcSloRequestPayload) == false) {
            return false;
        }
        CtSlcSloRequestPayload rhs = ((CtSlcSloRequestPayload) other);
        return ((((((((((((((((((((((((((((((((((((this.salesAssociate == rhs.salesAssociate)||((this.salesAssociate!= null)&&this.salesAssociate.equals(rhs.salesAssociate)))&&((this.ratesValidity == rhs.ratesValidity)||((this.ratesValidity!= null)&&this.ratesValidity.equals(rhs.ratesValidity))))&&((this.orderType == rhs.orderType)||((this.orderType!= null)&&this.orderType.equals(rhs.orderType))))&&((this.businessUnit == rhs.businessUnit)||((this.businessUnit!= null)&&this.businessUnit.equals(rhs.businessUnit))))&&((this.customFields == rhs.customFields)||((this.customFields!= null)&&this.customFields.equals(rhs.customFields))))&&((this.shipToType == rhs.shipToType)||((this.shipToType!= null)&&this.shipToType.equals(rhs.shipToType))))&&((this.shippingAddressId == rhs.shippingAddressId)||((this.shippingAddressId!= null)&&this.shippingAddressId.equals(rhs.shippingAddressId))))&&((this.orderPaymentType == rhs.orderPaymentType)||((this.orderPaymentType!= null)&&this.orderPaymentType.equals(rhs.orderPaymentType))))&&((this.lineItems == rhs.lineItems)||((this.lineItems!= null)&&this.lineItems.equals(rhs.lineItems))))&&((this.advancePayment == rhs.advancePayment)||((this.advancePayment!= null)&&this.advancePayment.equals(rhs.advancePayment))))&&((this.customerId == rhs.customerId)||((this.customerId!= null)&&this.customerId.equals(rhs.customerId))))&&((this.selfPickup == rhs.selfPickup)||((this.selfPickup!= null)&&this.selfPickup.equals(rhs.selfPickup))))&&((this.billingAddressId == rhs.billingAddressId)||((this.billingAddressId!= null)&&this.billingAddressId.equals(rhs.billingAddressId))))&&((this.payTo == rhs.payTo)||((this.payTo!= null)&&this.payTo.equals(rhs.payTo))))&&((this.salesforceOpportunityId == rhs.salesforceOpportunityId)||((this.salesforceOpportunityId!= null)&&this.salesforceOpportunityId.equals(rhs.salesforceOpportunityId))))&&((this.paymentTerms == rhs.paymentTerms)||((this.paymentTerms!= null)&&this.paymentTerms.equals(rhs.paymentTerms))))&&((this.blockOrderSummaryFlag == rhs.blockOrderSummaryFlag)||((this.blockOrderSummaryFlag!= null)&&this.blockOrderSummaryFlag.equals(rhs.blockOrderSummaryFlag))))&&((this.advancePercentage == rhs.advancePercentage)||((this.advancePercentage!= null)&&this.advancePercentage.equals(rhs.advancePercentage))))&&((this.creditLimitBypass == rhs.creditLimitBypass)||((this.creditLimitBypass!= null)&&this.creditLimitBypass.equals(rhs.creditLimitBypass))))&&((this.poDetails == rhs.poDetails)||((this.poDetails!= null)&&this.poDetails.equals(rhs.poDetails))))&&((this.salesforceOpportunityURL == rhs.salesforceOpportunityURL)||((this.salesforceOpportunityURL!= null)&&this.salesforceOpportunityURL.equals(rhs.salesforceOpportunityURL))))&&((this.priceType == rhs.priceType)||((this.priceType!= null)&&this.priceType.equals(rhs.priceType))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.billingAddressSFId == rhs.billingAddressSFId)||((this.billingAddressSFId!= null)&&this.billingAddressSFId.equals(rhs.billingAddressSFId))))&&((this.shippingAddressSFId == rhs.shippingAddressSFId)||((this.shippingAddressSFId!= null)&&this.shippingAddressSFId.equals(rhs.shippingAddressSFId))))&&((this.ledgerBalance == rhs.ledgerBalance)||((this.ledgerBalance!= null)&&this.ledgerBalance.equals(rhs.ledgerBalance))))&&((this.opportunityOwnerEmail == rhs.opportunityOwnerEmail)||((this.opportunityOwnerEmail!= null)&&this.opportunityOwnerEmail.equals(rhs.opportunityOwnerEmail))))&&((this.slo == rhs.slo)||((this.slo!= null)&&this.slo.equals(rhs.slo))))&&((this.opportunityString == rhs.opportunityString)||((this.opportunityString!= null)&&this.opportunityString.equals(rhs.opportunityString))))&&((this.isPBDistributor == rhs.isPBDistributor)||((this.isPBDistributor!= null)&&this.isPBDistributor.equals(rhs.isPBDistributor))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.isDirect == rhs.isDirect)||((this.isDirect!= null)&&this.isDirect.equals(rhs.isDirect))))&&((this.remarks == rhs.remarks)||((this.remarks!= null)&&this.remarks.equals(rhs.remarks))))&&((this.channelOfOrder == rhs.channelOfOrder)||((this.channelOfOrder!= null)&&this.channelOfOrder.equals(rhs.channelOfOrder))))&&((this.isBillingAddressValidated == rhs.isBillingAddressValidated)||((this.isBillingAddressValidated!= null)&&this.isBillingAddressValidated.equals(rhs.isBillingAddressValidated))));
    }

}
