
package com.qapitol.jsw.apitest.pojos.order.createDO;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "psId",
    "customerOrderId",
    "psIdentifier",
    "requestAmount",
    "freightValue",
    "requestTime",
    "psLineItemsDetails",
    "transactionStatus",
    "actions",
    "currentLedgerBalance",
    "mandatoryAdvance",
    "advanceAmount",
    "doPaymentType",
    "sellerAddress",
    "sellerAddressMetadata",
    "sellerCompanyName"
})
@Generated("jsonschema2pojo")
public class CreateDoResp {

    @JsonProperty("psId")
    private Integer psId;
    @JsonProperty("customerOrderId")
    private Integer customerOrderId;
    @JsonProperty("psIdentifier")
    private String psIdentifier;
    @JsonProperty("requestAmount")
    private Double requestAmount;
    @JsonProperty("freightValue")
    private Integer freightValue;
    @JsonProperty("requestTime")
    private String requestTime;
    @JsonProperty("psLineItemsDetails")
    private List<PsLineItemsDetailResp> psLineItemsDetails = new ArrayList<PsLineItemsDetailResp>();
    @JsonProperty("transactionStatus")
    private String transactionStatus;
    @JsonProperty("actions")
    private String actions;
    @JsonProperty("currentLedgerBalance")
    private Integer currentLedgerBalance;
    @JsonProperty("mandatoryAdvance")
    private Double mandatoryAdvance;
    @JsonProperty("advanceAmount")
    private Double advanceAmount;
    @JsonProperty("doPaymentType")
    private String doPaymentType;
    @JsonProperty("sellerAddress")
    private String sellerAddress;
    @JsonProperty("sellerAddressMetadata")
    private SellerAddressMetadata sellerAddressMetadata;
    @JsonProperty("sellerCompanyName")
    private String sellerCompanyName;
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
    public Integer getFreightValue() {
        return freightValue;
    }

    @JsonProperty("freightValue")
    public void setFreightValue(Integer freightValue) {
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

    @JsonProperty("psLineItemsDetails")
    public List<PsLineItemsDetailResp> getPsLineItemsDetails() {
        return psLineItemsDetails;
    }

    @JsonProperty("psLineItemsDetails")
    public void setPsLineItemsDetails(List<PsLineItemsDetailResp> psLineItemsDetails) {
        this.psLineItemsDetails = psLineItemsDetails;
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

    @JsonProperty("currentLedgerBalance")
    public Integer getCurrentLedgerBalance() {
        return currentLedgerBalance;
    }

    @JsonProperty("currentLedgerBalance")
    public void setCurrentLedgerBalance(Integer currentLedgerBalance) {
        this.currentLedgerBalance = currentLedgerBalance;
    }

    @JsonProperty("mandatoryAdvance")
    public Double getMandatoryAdvance() {
        return mandatoryAdvance;
    }

    @JsonProperty("mandatoryAdvance")
    public void setMandatoryAdvance(Double mandatoryAdvance) {
        this.mandatoryAdvance = mandatoryAdvance;
    }

    @JsonProperty("advanceAmount")
    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    @JsonProperty("advanceAmount")
    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
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

    @JsonProperty("sellerCompanyName")
    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    @JsonProperty("sellerCompanyName")
    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
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
        sb.append(CreateDoResp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("psLineItemsDetails");
        sb.append('=');
        sb.append(((this.psLineItemsDetails == null)?"<null>":this.psLineItemsDetails));
        sb.append(',');
        sb.append("transactionStatus");
        sb.append('=');
        sb.append(((this.transactionStatus == null)?"<null>":this.transactionStatus));
        sb.append(',');
        sb.append("actions");
        sb.append('=');
        sb.append(((this.actions == null)?"<null>":this.actions));
        sb.append(',');
        sb.append("currentLedgerBalance");
        sb.append('=');
        sb.append(((this.currentLedgerBalance == null)?"<null>":this.currentLedgerBalance));
        sb.append(',');
        sb.append("mandatoryAdvance");
        sb.append('=');
        sb.append(((this.mandatoryAdvance == null)?"<null>":this.mandatoryAdvance));
        sb.append(',');
        sb.append("advanceAmount");
        sb.append('=');
        sb.append(((this.advanceAmount == null)?"<null>":this.advanceAmount));
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
        sb.append("sellerCompanyName");
        sb.append('=');
        sb.append(((this.sellerCompanyName == null)?"<null>":this.sellerCompanyName));
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
        result = ((result* 31)+((this.advanceAmount == null)? 0 :this.advanceAmount.hashCode()));
        result = ((result* 31)+((this.psIdentifier == null)? 0 :this.psIdentifier.hashCode()));
        result = ((result* 31)+((this.transactionStatus == null)? 0 :this.transactionStatus.hashCode()));
        result = ((result* 31)+((this.customerOrderId == null)? 0 :this.customerOrderId.hashCode()));
        result = ((result* 31)+((this.freightValue == null)? 0 :this.freightValue.hashCode()));
        result = ((result* 31)+((this.psId == null)? 0 :this.psId.hashCode()));
        result = ((result* 31)+((this.sellerAddress == null)? 0 :this.sellerAddress.hashCode()));
        result = ((result* 31)+((this.sellerCompanyName == null)? 0 :this.sellerCompanyName.hashCode()));
        result = ((result* 31)+((this.requestAmount == null)? 0 :this.requestAmount.hashCode()));
        result = ((result* 31)+((this.requestTime == null)? 0 :this.requestTime.hashCode()));
        result = ((result* 31)+((this.psLineItemsDetails == null)? 0 :this.psLineItemsDetails.hashCode()));
        result = ((result* 31)+((this.sellerAddressMetadata == null)? 0 :this.sellerAddressMetadata.hashCode()));
        result = ((result* 31)+((this.currentLedgerBalance == null)? 0 :this.currentLedgerBalance.hashCode()));
        result = ((result* 31)+((this.mandatoryAdvance == null)? 0 :this.mandatoryAdvance.hashCode()));
        result = ((result* 31)+((this.doPaymentType == null)? 0 :this.doPaymentType.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.actions == null)? 0 :this.actions.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreateDoResp) == false) {
            return false;
        }
        CreateDoResp rhs = ((CreateDoResp) other);
        return ((((((((((((((((((this.advanceAmount == rhs.advanceAmount)||((this.advanceAmount!= null)&&this.advanceAmount.equals(rhs.advanceAmount)))&&((this.psIdentifier == rhs.psIdentifier)||((this.psIdentifier!= null)&&this.psIdentifier.equals(rhs.psIdentifier))))&&((this.transactionStatus == rhs.transactionStatus)||((this.transactionStatus!= null)&&this.transactionStatus.equals(rhs.transactionStatus))))&&((this.customerOrderId == rhs.customerOrderId)||((this.customerOrderId!= null)&&this.customerOrderId.equals(rhs.customerOrderId))))&&((this.freightValue == rhs.freightValue)||((this.freightValue!= null)&&this.freightValue.equals(rhs.freightValue))))&&((this.psId == rhs.psId)||((this.psId!= null)&&this.psId.equals(rhs.psId))))&&((this.sellerAddress == rhs.sellerAddress)||((this.sellerAddress!= null)&&this.sellerAddress.equals(rhs.sellerAddress))))&&((this.sellerCompanyName == rhs.sellerCompanyName)||((this.sellerCompanyName!= null)&&this.sellerCompanyName.equals(rhs.sellerCompanyName))))&&((this.requestAmount == rhs.requestAmount)||((this.requestAmount!= null)&&this.requestAmount.equals(rhs.requestAmount))))&&((this.requestTime == rhs.requestTime)||((this.requestTime!= null)&&this.requestTime.equals(rhs.requestTime))))&&((this.psLineItemsDetails == rhs.psLineItemsDetails)||((this.psLineItemsDetails!= null)&&this.psLineItemsDetails.equals(rhs.psLineItemsDetails))))&&((this.sellerAddressMetadata == rhs.sellerAddressMetadata)||((this.sellerAddressMetadata!= null)&&this.sellerAddressMetadata.equals(rhs.sellerAddressMetadata))))&&((this.currentLedgerBalance == rhs.currentLedgerBalance)||((this.currentLedgerBalance!= null)&&this.currentLedgerBalance.equals(rhs.currentLedgerBalance))))&&((this.mandatoryAdvance == rhs.mandatoryAdvance)||((this.mandatoryAdvance!= null)&&this.mandatoryAdvance.equals(rhs.mandatoryAdvance))))&&((this.doPaymentType == rhs.doPaymentType)||((this.doPaymentType!= null)&&this.doPaymentType.equals(rhs.doPaymentType))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.actions == rhs.actions)||((this.actions!= null)&&this.actions.equals(rhs.actions))));
    }

}
