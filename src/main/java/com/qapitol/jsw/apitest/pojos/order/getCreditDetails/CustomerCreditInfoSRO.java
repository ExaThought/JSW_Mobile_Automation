
package com.qapitol.jsw.apitest.pojos.order.getCreditDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "zohoCreatorId",
    "loanAccountNo",
    "pan",
    "creditType",
    "creditInstrument",
    "creditSanctioned",
    "creditUtilised",
    "creditBlocked",
    "creditAvailable",
    "creditUsable",
    "creditExpiry",
    "creditDueDay",
    "penalRate",
    "status",
    "isCreditBlocked",
    "camId",
    "customerProgramId",
    "gracePeriod",
    "path",
    "fileName",
    "advancePercentage",
    "sellerPan",
    "visibleToCustomer",
    "creditBlockedReason",
    "creditLimitRefId",
    "blockedDetails"
})
@Generated("jsonschema2pojo")
public class CustomerCreditInfoSRO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("zohoCreatorId")
    private String zohoCreatorId;
    @JsonProperty("loanAccountNo")
    private Object loanAccountNo;
    @JsonProperty("pan")
    private String pan;
    @JsonProperty("creditType")
    private String creditType;
    @JsonProperty("creditInstrument")
    private String creditInstrument;
    @JsonProperty("creditSanctioned")
    private Double creditSanctioned;
    @JsonProperty("creditUtilised")
    private Double creditUtilised;
    @JsonProperty("creditBlocked")
    private Double creditBlocked;
    @JsonProperty("creditAvailable")
    private Double creditAvailable;
    @JsonProperty("creditUsable")
    private Double creditUsable;
    @JsonProperty("creditExpiry")
    private String creditExpiry;
    @JsonProperty("creditDueDay")
    private Integer creditDueDay;
    @JsonProperty("penalRate")
    private Double penalRate;
    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("isCreditBlocked")
    private Boolean isCreditBlocked;
    @JsonProperty("camId")
    private Object camId;
    @JsonProperty("customerProgramId")
    private Object customerProgramId;
    @JsonProperty("gracePeriod")
    private Integer gracePeriod;
    @JsonProperty("path")
    private Object path;
    @JsonProperty("fileName")
    private Object fileName;
    @JsonProperty("advancePercentage")
    private Object advancePercentage;
    @JsonProperty("sellerPan")
    private Object sellerPan;
    @JsonProperty("visibleToCustomer")
    private Boolean visibleToCustomer;
    @JsonProperty("creditBlockedReason")
    private String creditBlockedReason;
    @JsonProperty("creditLimitRefId")
    private String creditLimitRefId;
    @JsonProperty("blockedDetails")
    private BlockedDetails blockedDetails;
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

    @JsonProperty("zohoCreatorId")
    public String getZohoCreatorId() {
        return zohoCreatorId;
    }

    @JsonProperty("zohoCreatorId")
    public void setZohoCreatorId(String zohoCreatorId) {
        this.zohoCreatorId = zohoCreatorId;
    }

    @JsonProperty("loanAccountNo")
    public Object getLoanAccountNo() {
        return loanAccountNo;
    }

    @JsonProperty("loanAccountNo")
    public void setLoanAccountNo(Object loanAccountNo) {
        this.loanAccountNo = loanAccountNo;
    }

    @JsonProperty("pan")
    public String getPan() {
        return pan;
    }

    @JsonProperty("pan")
    public void setPan(String pan) {
        this.pan = pan;
    }

    @JsonProperty("creditType")
    public String getCreditType() {
        return creditType;
    }

    @JsonProperty("creditType")
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    @JsonProperty("creditInstrument")
    public String getCreditInstrument() {
        return creditInstrument;
    }

    @JsonProperty("creditInstrument")
    public void setCreditInstrument(String creditInstrument) {
        this.creditInstrument = creditInstrument;
    }

    @JsonProperty("creditSanctioned")
    public Double getCreditSanctioned() {
        return creditSanctioned;
    }

    @JsonProperty("creditSanctioned")
    public void setCreditSanctioned(Double creditSanctioned) {
        this.creditSanctioned = creditSanctioned;
    }

    @JsonProperty("creditUtilised")
    public Double getCreditUtilised() {
        return creditUtilised;
    }

    @JsonProperty("creditUtilised")
    public void setCreditUtilised(Double creditUtilised) {
        this.creditUtilised = creditUtilised;
    }

    @JsonProperty("creditBlocked")
    public Double getCreditBlocked() {
        return creditBlocked;
    }

    @JsonProperty("creditBlocked")
    public void setCreditBlocked(Double creditBlocked) {
        this.creditBlocked = creditBlocked;
    }

    @JsonProperty("creditAvailable")
    public Double getCreditAvailable() {
        return creditAvailable;
    }

    @JsonProperty("creditAvailable")
    public void setCreditAvailable(Double creditAvailable) {
        this.creditAvailable = creditAvailable;
    }

    @JsonProperty("creditUsable")
    public Double getCreditUsable() {
        return creditUsable;
    }

    @JsonProperty("creditUsable")
    public void setCreditUsable(Double creditUsable) {
        this.creditUsable = creditUsable;
    }

    @JsonProperty("creditExpiry")
    public String getCreditExpiry() {
        return creditExpiry;
    }

    @JsonProperty("creditExpiry")
    public void setCreditExpiry(String creditExpiry) {
        this.creditExpiry = creditExpiry;
    }

    @JsonProperty("creditDueDay")
    public Integer getCreditDueDay() {
        return creditDueDay;
    }

    @JsonProperty("creditDueDay")
    public void setCreditDueDay(Integer creditDueDay) {
        this.creditDueDay = creditDueDay;
    }

    @JsonProperty("penalRate")
    public Double getPenalRate() {
        return penalRate;
    }

    @JsonProperty("penalRate")
    public void setPenalRate(Double penalRate) {
        this.penalRate = penalRate;
    }

    @JsonProperty("status")
    public Boolean getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonProperty("isCreditBlocked")
    public Boolean getIsCreditBlocked() {
        return isCreditBlocked;
    }

    @JsonProperty("isCreditBlocked")
    public void setIsCreditBlocked(Boolean isCreditBlocked) {
        this.isCreditBlocked = isCreditBlocked;
    }

    @JsonProperty("camId")
    public Object getCamId() {
        return camId;
    }

    @JsonProperty("camId")
    public void setCamId(Object camId) {
        this.camId = camId;
    }

    @JsonProperty("customerProgramId")
    public Object getCustomerProgramId() {
        return customerProgramId;
    }

    @JsonProperty("customerProgramId")
    public void setCustomerProgramId(Object customerProgramId) {
        this.customerProgramId = customerProgramId;
    }

    @JsonProperty("gracePeriod")
    public Integer getGracePeriod() {
        return gracePeriod;
    }

    @JsonProperty("gracePeriod")
    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    @JsonProperty("path")
    public Object getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(Object path) {
        this.path = path;
    }

    @JsonProperty("fileName")
    public Object getFileName() {
        return fileName;
    }

    @JsonProperty("fileName")
    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    @JsonProperty("advancePercentage")
    public Object getAdvancePercentage() {
        return advancePercentage;
    }

    @JsonProperty("advancePercentage")
    public void setAdvancePercentage(Object advancePercentage) {
        this.advancePercentage = advancePercentage;
    }

    @JsonProperty("sellerPan")
    public Object getSellerPan() {
        return sellerPan;
    }

    @JsonProperty("sellerPan")
    public void setSellerPan(Object sellerPan) {
        this.sellerPan = sellerPan;
    }

    @JsonProperty("visibleToCustomer")
    public Boolean getVisibleToCustomer() {
        return visibleToCustomer;
    }

    @JsonProperty("visibleToCustomer")
    public void setVisibleToCustomer(Boolean visibleToCustomer) {
        this.visibleToCustomer = visibleToCustomer;
    }

    @JsonProperty("creditBlockedReason")
    public String getCreditBlockedReason() {
        return creditBlockedReason;
    }

    @JsonProperty("creditBlockedReason")
    public void setCreditBlockedReason(String creditBlockedReason) {
        this.creditBlockedReason = creditBlockedReason;
    }

    @JsonProperty("creditLimitRefId")
    public String getCreditLimitRefId() {
        return creditLimitRefId;
    }

    @JsonProperty("creditLimitRefId")
    public void setCreditLimitRefId(String creditLimitRefId) {
        this.creditLimitRefId = creditLimitRefId;
    }

    @JsonProperty("blockedDetails")
    public BlockedDetails getBlockedDetails() {
        return blockedDetails;
    }

    @JsonProperty("blockedDetails")
    public void setBlockedDetails(BlockedDetails blockedDetails) {
        this.blockedDetails = blockedDetails;
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
        sb.append(CustomerCreditInfoSRO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("zohoCreatorId");
        sb.append('=');
        sb.append(((this.zohoCreatorId == null)?"<null>":this.zohoCreatorId));
        sb.append(',');
        sb.append("loanAccountNo");
        sb.append('=');
        sb.append(((this.loanAccountNo == null)?"<null>":this.loanAccountNo));
        sb.append(',');
        sb.append("pan");
        sb.append('=');
        sb.append(((this.pan == null)?"<null>":this.pan));
        sb.append(',');
        sb.append("creditType");
        sb.append('=');
        sb.append(((this.creditType == null)?"<null>":this.creditType));
        sb.append(',');
        sb.append("creditInstrument");
        sb.append('=');
        sb.append(((this.creditInstrument == null)?"<null>":this.creditInstrument));
        sb.append(',');
        sb.append("creditSanctioned");
        sb.append('=');
        sb.append(((this.creditSanctioned == null)?"<null>":this.creditSanctioned));
        sb.append(',');
        sb.append("creditUtilised");
        sb.append('=');
        sb.append(((this.creditUtilised == null)?"<null>":this.creditUtilised));
        sb.append(',');
        sb.append("creditBlocked");
        sb.append('=');
        sb.append(((this.creditBlocked == null)?"<null>":this.creditBlocked));
        sb.append(',');
        sb.append("creditAvailable");
        sb.append('=');
        sb.append(((this.creditAvailable == null)?"<null>":this.creditAvailable));
        sb.append(',');
        sb.append("creditUsable");
        sb.append('=');
        sb.append(((this.creditUsable == null)?"<null>":this.creditUsable));
        sb.append(',');
        sb.append("creditExpiry");
        sb.append('=');
        sb.append(((this.creditExpiry == null)?"<null>":this.creditExpiry));
        sb.append(',');
        sb.append("creditDueDay");
        sb.append('=');
        sb.append(((this.creditDueDay == null)?"<null>":this.creditDueDay));
        sb.append(',');
        sb.append("penalRate");
        sb.append('=');
        sb.append(((this.penalRate == null)?"<null>":this.penalRate));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("isCreditBlocked");
        sb.append('=');
        sb.append(((this.isCreditBlocked == null)?"<null>":this.isCreditBlocked));
        sb.append(',');
        sb.append("camId");
        sb.append('=');
        sb.append(((this.camId == null)?"<null>":this.camId));
        sb.append(',');
        sb.append("customerProgramId");
        sb.append('=');
        sb.append(((this.customerProgramId == null)?"<null>":this.customerProgramId));
        sb.append(',');
        sb.append("gracePeriod");
        sb.append('=');
        sb.append(((this.gracePeriod == null)?"<null>":this.gracePeriod));
        sb.append(',');
        sb.append("path");
        sb.append('=');
        sb.append(((this.path == null)?"<null>":this.path));
        sb.append(',');
        sb.append("fileName");
        sb.append('=');
        sb.append(((this.fileName == null)?"<null>":this.fileName));
        sb.append(',');
        sb.append("advancePercentage");
        sb.append('=');
        sb.append(((this.advancePercentage == null)?"<null>":this.advancePercentage));
        sb.append(',');
        sb.append("sellerPan");
        sb.append('=');
        sb.append(((this.sellerPan == null)?"<null>":this.sellerPan));
        sb.append(',');
        sb.append("visibleToCustomer");
        sb.append('=');
        sb.append(((this.visibleToCustomer == null)?"<null>":this.visibleToCustomer));
        sb.append(',');
        sb.append("creditBlockedReason");
        sb.append('=');
        sb.append(((this.creditBlockedReason == null)?"<null>":this.creditBlockedReason));
        sb.append(',');
        sb.append("creditLimitRefId");
        sb.append('=');
        sb.append(((this.creditLimitRefId == null)?"<null>":this.creditLimitRefId));
        sb.append(',');
        sb.append("blockedDetails");
        sb.append('=');
        sb.append(((this.blockedDetails == null)?"<null>":this.blockedDetails));
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
        result = ((result* 31)+((this.customerProgramId == null)? 0 :this.customerProgramId.hashCode()));
        result = ((result* 31)+((this.gracePeriod == null)? 0 :this.gracePeriod.hashCode()));
        result = ((result* 31)+((this.fileName == null)? 0 :this.fileName.hashCode()));
        result = ((result* 31)+((this.creditInstrument == null)? 0 :this.creditInstrument.hashCode()));
        result = ((result* 31)+((this.creditType == null)? 0 :this.creditType.hashCode()));
        result = ((result* 31)+((this.creditBlocked == null)? 0 :this.creditBlocked.hashCode()));
        result = ((result* 31)+((this.creditSanctioned == null)? 0 :this.creditSanctioned.hashCode()));
        result = ((result* 31)+((this.path == null)? 0 :this.path.hashCode()));
        result = ((result* 31)+((this.zohoCreatorId == null)? 0 :this.zohoCreatorId.hashCode()));
        result = ((result* 31)+((this.isCreditBlocked == null)? 0 :this.isCreditBlocked.hashCode()));
        result = ((result* 31)+((this.penalRate == null)? 0 :this.penalRate.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.sellerPan == null)? 0 :this.sellerPan.hashCode()));
        result = ((result* 31)+((this.pan == null)? 0 :this.pan.hashCode()));
        result = ((result* 31)+((this.advancePercentage == null)? 0 :this.advancePercentage.hashCode()));
        result = ((result* 31)+((this.blockedDetails == null)? 0 :this.blockedDetails.hashCode()));
        result = ((result* 31)+((this.creditUsable == null)? 0 :this.creditUsable.hashCode()));
        result = ((result* 31)+((this.camId == null)? 0 :this.camId.hashCode()));
        result = ((result* 31)+((this.creditAvailable == null)? 0 :this.creditAvailable.hashCode()));
        result = ((result* 31)+((this.loanAccountNo == null)? 0 :this.loanAccountNo.hashCode()));
        result = ((result* 31)+((this.creditLimitRefId == null)? 0 :this.creditLimitRefId.hashCode()));
        result = ((result* 31)+((this.visibleToCustomer == null)? 0 :this.visibleToCustomer.hashCode()));
        result = ((result* 31)+((this.creditUtilised == null)? 0 :this.creditUtilised.hashCode()));
        result = ((result* 31)+((this.creditDueDay == null)? 0 :this.creditDueDay.hashCode()));
        result = ((result* 31)+((this.creditExpiry == null)? 0 :this.creditExpiry.hashCode()));
        result = ((result* 31)+((this.creditBlockedReason == null)? 0 :this.creditBlockedReason.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CustomerCreditInfoSRO) == false) {
            return false;
        }
        CustomerCreditInfoSRO rhs = ((CustomerCreditInfoSRO) other);
        return (((((((((((((((((((((((((((((this.customerProgramId == rhs.customerProgramId)||((this.customerProgramId!= null)&&this.customerProgramId.equals(rhs.customerProgramId)))&&((this.gracePeriod == rhs.gracePeriod)||((this.gracePeriod!= null)&&this.gracePeriod.equals(rhs.gracePeriod))))&&((this.fileName == rhs.fileName)||((this.fileName!= null)&&this.fileName.equals(rhs.fileName))))&&((this.creditInstrument == rhs.creditInstrument)||((this.creditInstrument!= null)&&this.creditInstrument.equals(rhs.creditInstrument))))&&((this.creditType == rhs.creditType)||((this.creditType!= null)&&this.creditType.equals(rhs.creditType))))&&((this.creditBlocked == rhs.creditBlocked)||((this.creditBlocked!= null)&&this.creditBlocked.equals(rhs.creditBlocked))))&&((this.creditSanctioned == rhs.creditSanctioned)||((this.creditSanctioned!= null)&&this.creditSanctioned.equals(rhs.creditSanctioned))))&&((this.path == rhs.path)||((this.path!= null)&&this.path.equals(rhs.path))))&&((this.zohoCreatorId == rhs.zohoCreatorId)||((this.zohoCreatorId!= null)&&this.zohoCreatorId.equals(rhs.zohoCreatorId))))&&((this.isCreditBlocked == rhs.isCreditBlocked)||((this.isCreditBlocked!= null)&&this.isCreditBlocked.equals(rhs.isCreditBlocked))))&&((this.penalRate == rhs.penalRate)||((this.penalRate!= null)&&this.penalRate.equals(rhs.penalRate))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.sellerPan == rhs.sellerPan)||((this.sellerPan!= null)&&this.sellerPan.equals(rhs.sellerPan))))&&((this.pan == rhs.pan)||((this.pan!= null)&&this.pan.equals(rhs.pan))))&&((this.advancePercentage == rhs.advancePercentage)||((this.advancePercentage!= null)&&this.advancePercentage.equals(rhs.advancePercentage))))&&((this.blockedDetails == rhs.blockedDetails)||((this.blockedDetails!= null)&&this.blockedDetails.equals(rhs.blockedDetails))))&&((this.creditUsable == rhs.creditUsable)||((this.creditUsable!= null)&&this.creditUsable.equals(rhs.creditUsable))))&&((this.camId == rhs.camId)||((this.camId!= null)&&this.camId.equals(rhs.camId))))&&((this.creditAvailable == rhs.creditAvailable)||((this.creditAvailable!= null)&&this.creditAvailable.equals(rhs.creditAvailable))))&&((this.loanAccountNo == rhs.loanAccountNo)||((this.loanAccountNo!= null)&&this.loanAccountNo.equals(rhs.loanAccountNo))))&&((this.creditLimitRefId == rhs.creditLimitRefId)||((this.creditLimitRefId!= null)&&this.creditLimitRefId.equals(rhs.creditLimitRefId))))&&((this.visibleToCustomer == rhs.visibleToCustomer)||((this.visibleToCustomer!= null)&&this.visibleToCustomer.equals(rhs.visibleToCustomer))))&&((this.creditUtilised == rhs.creditUtilised)||((this.creditUtilised!= null)&&this.creditUtilised.equals(rhs.creditUtilised))))&&((this.creditDueDay == rhs.creditDueDay)||((this.creditDueDay!= null)&&this.creditDueDay.equals(rhs.creditDueDay))))&&((this.creditExpiry == rhs.creditExpiry)||((this.creditExpiry!= null)&&this.creditExpiry.equals(rhs.creditExpiry))))&&((this.creditBlockedReason == rhs.creditBlockedReason)||((this.creditBlockedReason!= null)&&this.creditBlockedReason.equals(rhs.creditBlockedReason))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
