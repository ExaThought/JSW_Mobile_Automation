
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contractNumber",
    "contractCreationDate",
    "sapMetadata",
    "orderCreationDate",
    "validStartDate",
    "validEndDate",
    "soldPartyCode",
    "distributionChannel",
    "sapApprovalStatus",
    "sapOrderNumber",
    "sapOrderApprovedBy",
    "sapOrderCreationDate",
    "sapStatusRemarks"
})
@Generated("jsonschema2pojo")
public class SapInfo {

    @JsonProperty("contractNumber")
    private Object contractNumber;
    @JsonProperty("contractCreationDate")
    private Object contractCreationDate;
    @JsonProperty("sapMetadata")
    private Object sapMetadata;
    @JsonProperty("orderCreationDate")
    private String orderCreationDate;
    @JsonProperty("validStartDate")
    private Object validStartDate;
    @JsonProperty("validEndDate")
    private Object validEndDate;
    @JsonProperty("soldPartyCode")
    private Object soldPartyCode;
    @JsonProperty("distributionChannel")
    private Object distributionChannel;
    @JsonProperty("sapApprovalStatus")
    private Object sapApprovalStatus;
    @JsonProperty("sapOrderNumber")
    private Object sapOrderNumber;
    @JsonProperty("sapOrderApprovedBy")
    private Object sapOrderApprovedBy;
    @JsonProperty("sapOrderCreationDate")
    private Object sapOrderCreationDate;
    @JsonProperty("sapStatusRemarks")
    private Object sapStatusRemarks;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("contractNumber")
    public Object getContractNumber() {
        return contractNumber;
    }

    @JsonProperty("contractNumber")
    public void setContractNumber(Object contractNumber) {
        this.contractNumber = contractNumber;
    }

    @JsonProperty("contractCreationDate")
    public Object getContractCreationDate() {
        return contractCreationDate;
    }

    @JsonProperty("contractCreationDate")
    public void setContractCreationDate(Object contractCreationDate) {
        this.contractCreationDate = contractCreationDate;
    }

    @JsonProperty("sapMetadata")
    public Object getSapMetadata() {
        return sapMetadata;
    }

    @JsonProperty("sapMetadata")
    public void setSapMetadata(Object sapMetadata) {
        this.sapMetadata = sapMetadata;
    }

    @JsonProperty("orderCreationDate")
    public String getOrderCreationDate() {
        return orderCreationDate;
    }

    @JsonProperty("orderCreationDate")
    public void setOrderCreationDate(String orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    @JsonProperty("validStartDate")
    public Object getValidStartDate() {
        return validStartDate;
    }

    @JsonProperty("validStartDate")
    public void setValidStartDate(Object validStartDate) {
        this.validStartDate = validStartDate;
    }

    @JsonProperty("validEndDate")
    public Object getValidEndDate() {
        return validEndDate;
    }

    @JsonProperty("validEndDate")
    public void setValidEndDate(Object validEndDate) {
        this.validEndDate = validEndDate;
    }

    @JsonProperty("soldPartyCode")
    public Object getSoldPartyCode() {
        return soldPartyCode;
    }

    @JsonProperty("soldPartyCode")
    public void setSoldPartyCode(Object soldPartyCode) {
        this.soldPartyCode = soldPartyCode;
    }

    @JsonProperty("distributionChannel")
    public Object getDistributionChannel() {
        return distributionChannel;
    }

    @JsonProperty("distributionChannel")
    public void setDistributionChannel(Object distributionChannel) {
        this.distributionChannel = distributionChannel;
    }

    @JsonProperty("sapApprovalStatus")
    public Object getSapApprovalStatus() {
        return sapApprovalStatus;
    }

    @JsonProperty("sapApprovalStatus")
    public void setSapApprovalStatus(Object sapApprovalStatus) {
        this.sapApprovalStatus = sapApprovalStatus;
    }

    @JsonProperty("sapOrderNumber")
    public Object getSapOrderNumber() {
        return sapOrderNumber;
    }

    @JsonProperty("sapOrderNumber")
    public void setSapOrderNumber(Object sapOrderNumber) {
        this.sapOrderNumber = sapOrderNumber;
    }

    @JsonProperty("sapOrderApprovedBy")
    public Object getSapOrderApprovedBy() {
        return sapOrderApprovedBy;
    }

    @JsonProperty("sapOrderApprovedBy")
    public void setSapOrderApprovedBy(Object sapOrderApprovedBy) {
        this.sapOrderApprovedBy = sapOrderApprovedBy;
    }

    @JsonProperty("sapOrderCreationDate")
    public Object getSapOrderCreationDate() {
        return sapOrderCreationDate;
    }

    @JsonProperty("sapOrderCreationDate")
    public void setSapOrderCreationDate(Object sapOrderCreationDate) {
        this.sapOrderCreationDate = sapOrderCreationDate;
    }

    @JsonProperty("sapStatusRemarks")
    public Object getSapStatusRemarks() {
        return sapStatusRemarks;
    }

    @JsonProperty("sapStatusRemarks")
    public void setSapStatusRemarks(Object sapStatusRemarks) {
        this.sapStatusRemarks = sapStatusRemarks;
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
        sb.append(SapInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("contractNumber");
        sb.append('=');
        sb.append(((this.contractNumber == null)?"<null>":this.contractNumber));
        sb.append(',');
        sb.append("contractCreationDate");
        sb.append('=');
        sb.append(((this.contractCreationDate == null)?"<null>":this.contractCreationDate));
        sb.append(',');
        sb.append("sapMetadata");
        sb.append('=');
        sb.append(((this.sapMetadata == null)?"<null>":this.sapMetadata));
        sb.append(',');
        sb.append("orderCreationDate");
        sb.append('=');
        sb.append(((this.orderCreationDate == null)?"<null>":this.orderCreationDate));
        sb.append(',');
        sb.append("validStartDate");
        sb.append('=');
        sb.append(((this.validStartDate == null)?"<null>":this.validStartDate));
        sb.append(',');
        sb.append("validEndDate");
        sb.append('=');
        sb.append(((this.validEndDate == null)?"<null>":this.validEndDate));
        sb.append(',');
        sb.append("soldPartyCode");
        sb.append('=');
        sb.append(((this.soldPartyCode == null)?"<null>":this.soldPartyCode));
        sb.append(',');
        sb.append("distributionChannel");
        sb.append('=');
        sb.append(((this.distributionChannel == null)?"<null>":this.distributionChannel));
        sb.append(',');
        sb.append("sapApprovalStatus");
        sb.append('=');
        sb.append(((this.sapApprovalStatus == null)?"<null>":this.sapApprovalStatus));
        sb.append(',');
        sb.append("sapOrderNumber");
        sb.append('=');
        sb.append(((this.sapOrderNumber == null)?"<null>":this.sapOrderNumber));
        sb.append(',');
        sb.append("sapOrderApprovedBy");
        sb.append('=');
        sb.append(((this.sapOrderApprovedBy == null)?"<null>":this.sapOrderApprovedBy));
        sb.append(',');
        sb.append("sapOrderCreationDate");
        sb.append('=');
        sb.append(((this.sapOrderCreationDate == null)?"<null>":this.sapOrderCreationDate));
        sb.append(',');
        sb.append("sapStatusRemarks");
        sb.append('=');
        sb.append(((this.sapStatusRemarks == null)?"<null>":this.sapStatusRemarks));
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
        result = ((result* 31)+((this.sapOrderNumber == null)? 0 :this.sapOrderNumber.hashCode()));
        result = ((result* 31)+((this.sapOrderCreationDate == null)? 0 :this.sapOrderCreationDate.hashCode()));
        result = ((result* 31)+((this.contractNumber == null)? 0 :this.contractNumber.hashCode()));
        result = ((result* 31)+((this.orderCreationDate == null)? 0 :this.orderCreationDate.hashCode()));
        result = ((result* 31)+((this.sapStatusRemarks == null)? 0 :this.sapStatusRemarks.hashCode()));
        result = ((result* 31)+((this.contractCreationDate == null)? 0 :this.contractCreationDate.hashCode()));
        result = ((result* 31)+((this.sapMetadata == null)? 0 :this.sapMetadata.hashCode()));
        result = ((result* 31)+((this.sapApprovalStatus == null)? 0 :this.sapApprovalStatus.hashCode()));
        result = ((result* 31)+((this.validStartDate == null)? 0 :this.validStartDate.hashCode()));
        result = ((result* 31)+((this.soldPartyCode == null)? 0 :this.soldPartyCode.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.validEndDate == null)? 0 :this.validEndDate.hashCode()));
        result = ((result* 31)+((this.distributionChannel == null)? 0 :this.distributionChannel.hashCode()));
        result = ((result* 31)+((this.sapOrderApprovedBy == null)? 0 :this.sapOrderApprovedBy.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SapInfo) == false) {
            return false;
        }
        SapInfo rhs = ((SapInfo) other);
        return (((((((((((((((this.sapOrderNumber == rhs.sapOrderNumber)||((this.sapOrderNumber!= null)&&this.sapOrderNumber.equals(rhs.sapOrderNumber)))&&((this.sapOrderCreationDate == rhs.sapOrderCreationDate)||((this.sapOrderCreationDate!= null)&&this.sapOrderCreationDate.equals(rhs.sapOrderCreationDate))))&&((this.contractNumber == rhs.contractNumber)||((this.contractNumber!= null)&&this.contractNumber.equals(rhs.contractNumber))))&&((this.orderCreationDate == rhs.orderCreationDate)||((this.orderCreationDate!= null)&&this.orderCreationDate.equals(rhs.orderCreationDate))))&&((this.sapStatusRemarks == rhs.sapStatusRemarks)||((this.sapStatusRemarks!= null)&&this.sapStatusRemarks.equals(rhs.sapStatusRemarks))))&&((this.contractCreationDate == rhs.contractCreationDate)||((this.contractCreationDate!= null)&&this.contractCreationDate.equals(rhs.contractCreationDate))))&&((this.sapMetadata == rhs.sapMetadata)||((this.sapMetadata!= null)&&this.sapMetadata.equals(rhs.sapMetadata))))&&((this.sapApprovalStatus == rhs.sapApprovalStatus)||((this.sapApprovalStatus!= null)&&this.sapApprovalStatus.equals(rhs.sapApprovalStatus))))&&((this.validStartDate == rhs.validStartDate)||((this.validStartDate!= null)&&this.validStartDate.equals(rhs.validStartDate))))&&((this.soldPartyCode == rhs.soldPartyCode)||((this.soldPartyCode!= null)&&this.soldPartyCode.equals(rhs.soldPartyCode))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.validEndDate == rhs.validEndDate)||((this.validEndDate!= null)&&this.validEndDate.equals(rhs.validEndDate))))&&((this.distributionChannel == rhs.distributionChannel)||((this.distributionChannel!= null)&&this.distributionChannel.equals(rhs.distributionChannel))))&&((this.sapOrderApprovedBy == rhs.sapOrderApprovedBy)||((this.sapOrderApprovedBy!= null)&&this.sapOrderApprovedBy.equals(rhs.sapOrderApprovedBy))));
    }

}
