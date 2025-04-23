package com.qapitol.jsw.apitest.pojos.order.rejectDo;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "psId",
        "customerOrderId",
        "psIdentifier",
        "requestAmount",
        "freightValue",
        "requestTime",
        "transactionStatus",
        "actions",
        "rejectionReason",
        "mandatoryAdvance",
        "creditBlockedAmount",
        "doPaymentType",
        "sellerAddress",
        "sellerAddressMetadata"
})
@Generated("jsonschema2pojo")
public class RejectDOResp {

    @JsonProperty("psId")
    private Integer psId;
    @JsonProperty("customerOrderId")
    private Integer customerOrderId;
    @JsonProperty("psIdentifier")
    private String psIdentifier;
    @JsonProperty("requestAmount")
    private Double requestAmount;
    @JsonProperty("freightValue")
    private Double freightValue;
    @JsonProperty("requestTime")
    private String requestTime;
    @JsonProperty("transactionStatus")
    private String transactionStatus;
    @JsonProperty("actions")
    private String actions;
    @JsonProperty("rejectionReason")
    private String rejectionReason;
    @JsonProperty("mandatoryAdvance")
    private Double mandatoryAdvance;
    @JsonProperty("creditBlockedAmount")
    private Double creditBlockedAmount;
    @JsonProperty("doPaymentType")
    private String doPaymentType;
    @JsonProperty("sellerAddress")
    private String sellerAddress;
    @JsonProperty("sellerAddressMetadata")
    private SellerAddressMetadata sellerAddressMetadata;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("psId")
    public Integer getPsId() {
        return psId;
    }

    @JsonProperty("psId")
    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    @JsonProperty("customerOrderId")
    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    @JsonProperty("customerOrderId")
    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    @JsonProperty("psIdentifier")
    public String getPsIdentifier() {
        return psIdentifier;
    }

    @JsonProperty("psIdentifier")
    public void setPsIdentifier(String psIdentifier) {
        this.psIdentifier = psIdentifier;
    }

    @JsonProperty("requestAmount")
    public Double getRequestAmount() {
        return requestAmount;
    }

    @JsonProperty("requestAmount")
    public void setRequestAmount(Double requestAmount) {
        this.requestAmount = requestAmount;
    }

    @JsonProperty("freightValue")
    public Double getFreightValue() {
        return freightValue;
    }

    @JsonProperty("freightValue")
    public void setFreightValue(Double freightValue) {
        this.freightValue = freightValue;
    }

    @JsonProperty("requestTime")
    public String getRequestTime() {
        return requestTime;
    }

    @JsonProperty("requestTime")
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    @JsonProperty("transactionStatus")
    public String getTransactionStatus() {
        return transactionStatus;
    }

    @JsonProperty("transactionStatus")
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @JsonProperty("actions")
    public String getActions() {
        return actions;
    }

    @JsonProperty("actions")
    public void setActions(String actions) {
        this.actions = actions;
    }

    @JsonProperty("rejectionReason")
    public String getRejectionReason() {
        return rejectionReason;
    }

    @JsonProperty("rejectionReason")
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @JsonProperty("mandatoryAdvance")
    public Double getMandatoryAdvance() {
        return mandatoryAdvance;
    }

    @JsonProperty("mandatoryAdvance")
    public void setMandatoryAdvance(Double mandatoryAdvance) {
        this.mandatoryAdvance = mandatoryAdvance;
    }

    @JsonProperty("creditBlockedAmount")
    public Double getCreditBlockedAmount() {
        return creditBlockedAmount;
    }

    @JsonProperty("creditBlockedAmount")
    public void setCreditBlockedAmount(Double creditBlockedAmount) {
        this.creditBlockedAmount = creditBlockedAmount;
    }

    @JsonProperty("doPaymentType")
    public String getDoPaymentType() {
        return doPaymentType;
    }

    @JsonProperty("doPaymentType")
    public void setDoPaymentType(String doPaymentType) {
        this.doPaymentType = doPaymentType;
    }

    @JsonProperty("sellerAddress")
    public String getSellerAddress() {
        return sellerAddress;
    }

    @JsonProperty("sellerAddress")
    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    @JsonProperty("sellerAddressMetadata")
    public SellerAddressMetadata getSellerAddressMetadata() {
        return sellerAddressMetadata;
    }

    @JsonProperty("sellerAddressMetadata")
    public void setSellerAddressMetadata(SellerAddressMetadata sellerAddressMetadata) {
        this.sellerAddressMetadata = sellerAddressMetadata;
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
        sb.append(RejectDOResp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("psId");
        sb.append('=');
        sb.append(((this.psId == null)?"<null>":this.psId));
        sb.append(',');
        sb.append("customerOrderId");
        sb.append('=');
        sb.append(((this.customerOrderId == null)?"<null>":this.customerOrderId));
        sb.append(',');
        sb.append("psIdentifier");
        sb.append('=');
        sb.append(((this.psIdentifier == null)?"<null>":this.psIdentifier));
        sb.append(',');
        sb.append("requestAmount");
        sb.append('=');
        sb.append(((this.requestAmount == null)?"<null>":this.requestAmount));
        sb.append(',');
        sb.append("freightValue");
        sb.append('=');
        sb.append(((this.freightValue == null)?"<null>":this.freightValue));
        sb.append(',');
        sb.append("requestTime");
        sb.append('=');
        sb.append(((this.requestTime == null)?"<null>":this.requestTime));
        sb.append(',');
        sb.append("transactionStatus");
        sb.append('=');
        sb.append(((this.transactionStatus == null)?"<null>":this.transactionStatus));
        sb.append(',');
        sb.append("actions");
        sb.append('=');
        sb.append(((this.actions == null)?"<null>":this.actions));
        sb.append(',');
        sb.append("rejectionReason");
        sb.append('=');
        sb.append(((this.rejectionReason == null)?"<null>":this.rejectionReason));
        sb.append(',');
        sb.append("mandatoryAdvance");
        sb.append('=');
        sb.append(((this.mandatoryAdvance == null)?"<null>":this.mandatoryAdvance));
        sb.append(',');
        sb.append("creditBlockedAmount");
        sb.append('=');
        sb.append(((this.creditBlockedAmount == null)?"<null>":this.creditBlockedAmount));
        sb.append(',');
        sb.append("doPaymentType");
        sb.append('=');
        sb.append(((this.doPaymentType == null)?"<null>":this.doPaymentType));
        sb.append(',');
        sb.append("sellerAddress");
        sb.append('=');
        sb.append(((this.sellerAddress == null)?"<null>":this.sellerAddress));
        sb.append(',');
        sb.append("sellerAddressMetadata");
        sb.append('=');
        sb.append(((this.sellerAddressMetadata == null)?"<null>":this.sellerAddressMetadata));
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
        result = ((result* 31)+((this.psIdentifier == null)? 0 :this.psIdentifier.hashCode()));
        result = ((result* 31)+((this.transactionStatus == null)? 0 :this.transactionStatus.hashCode()));
        result = ((result* 31)+((this.customerOrderId == null)? 0 :this.customerOrderId.hashCode()));
        result = ((result* 31)+((this.freightValue == null)? 0 :this.freightValue.hashCode()));
        result = ((result* 31)+((this.psId == null)? 0 :this.psId.hashCode()));
        result = ((result* 31)+((this.sellerAddress == null)? 0 :this.sellerAddress.hashCode()));
        result = ((result* 31)+((this.creditBlockedAmount == null)? 0 :this.creditBlockedAmount.hashCode()));
        result = ((result* 31)+((this.requestAmount == null)? 0 :this.requestAmount.hashCode()));
        result = ((result* 31)+((this.requestTime == null)? 0 :this.requestTime.hashCode()));
        result = ((result* 31)+((this.sellerAddressMetadata == null)? 0 :this.sellerAddressMetadata.hashCode()));
        result = ((result* 31)+((this.mandatoryAdvance == null)? 0 :this.mandatoryAdvance.hashCode()));
        result = ((result* 31)+((this.doPaymentType == null)? 0 :this.doPaymentType.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.rejectionReason == null)? 0 :this.rejectionReason.hashCode()));
        result = ((result* 31)+((this.actions == null)? 0 :this.actions.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RejectDOResp) == false) {
            return false;
        }
        RejectDOResp rhs = ((RejectDOResp) other);
        return ((((((((((((((((this.psIdentifier == rhs.psIdentifier)||((this.psIdentifier!= null)&&this.psIdentifier.equals(rhs.psIdentifier)))&&((this.transactionStatus == rhs.transactionStatus)||((this.transactionStatus!= null)&&this.transactionStatus.equals(rhs.transactionStatus))))&&((this.customerOrderId == rhs.customerOrderId)||((this.customerOrderId!= null)&&this.customerOrderId.equals(rhs.customerOrderId))))&&((this.freightValue == rhs.freightValue)||((this.freightValue!= null)&&this.freightValue.equals(rhs.freightValue))))&&((this.psId == rhs.psId)||((this.psId!= null)&&this.psId.equals(rhs.psId))))&&((this.sellerAddress == rhs.sellerAddress)||((this.sellerAddress!= null)&&this.sellerAddress.equals(rhs.sellerAddress))))&&((this.creditBlockedAmount == rhs.creditBlockedAmount)||((this.creditBlockedAmount!= null)&&this.creditBlockedAmount.equals(rhs.creditBlockedAmount))))&&((this.requestAmount == rhs.requestAmount)||((this.requestAmount!= null)&&this.requestAmount.equals(rhs.requestAmount))))&&((this.requestTime == rhs.requestTime)||((this.requestTime!= null)&&this.requestTime.equals(rhs.requestTime))))&&((this.sellerAddressMetadata == rhs.sellerAddressMetadata)||((this.sellerAddressMetadata!= null)&&this.sellerAddressMetadata.equals(rhs.sellerAddressMetadata))))&&((this.mandatoryAdvance == rhs.mandatoryAdvance)||((this.mandatoryAdvance!= null)&&this.mandatoryAdvance.equals(rhs.mandatoryAdvance))))&&((this.doPaymentType == rhs.doPaymentType)||((this.doPaymentType!= null)&&this.doPaymentType.equals(rhs.doPaymentType))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.rejectionReason == rhs.rejectionReason)||((this.rejectionReason!= null)&&this.rejectionReason.equals(rhs.rejectionReason))))&&((this.actions == rhs.actions)||((this.actions!= null)&&this.actions.equals(rhs.actions))));
    }

}