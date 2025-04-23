package com.qapitol.jsw.apitest.pojos.order.rejectDo;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "psId",
        "psIdentifier",
        "transactionStatus",
        "rejectionReason"
})
@Generated("jsonschema2pojo")
public class RejectDO {

    @JsonProperty("psId")
    private Integer psId;
    @JsonProperty("psIdentifier")
    private String psIdentifier;
    @JsonProperty("transactionStatus")
    private String transactionStatus;
    @JsonProperty("rejectionReason")
    private String rejectionReason;
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

    @JsonProperty("psIdentifier")
    public String getPsIdentifier() {
        return psIdentifier;
    }

    @JsonProperty("psIdentifier")
    public void setPsIdentifier(String psIdentifier) {
        this.psIdentifier = psIdentifier;
    }

    @JsonProperty("transactionStatus")
    public String getTransactionStatus() {
        return transactionStatus;
    }

    @JsonProperty("transactionStatus")
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @JsonProperty("rejectionReason")
    public String getRejectionReason() {
        return rejectionReason;
    }

    @JsonProperty("rejectionReason")
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
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
        sb.append(RejectDO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("psId");
        sb.append('=');
        sb.append(((this.psId == null)?"<null>":this.psId));
        sb.append(',');
        sb.append("psIdentifier");
        sb.append('=');
        sb.append(((this.psIdentifier == null)?"<null>":this.psIdentifier));
        sb.append(',');
        sb.append("transactionStatus");
        sb.append('=');
        sb.append(((this.transactionStatus == null)?"<null>":this.transactionStatus));
        sb.append(',');
        sb.append("rejectionReason");
        sb.append('=');
        sb.append(((this.rejectionReason == null)?"<null>":this.rejectionReason));
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
        result = ((result* 31)+((this.psId == null)? 0 :this.psId.hashCode()));
        result = ((result* 31)+((this.psIdentifier == null)? 0 :this.psIdentifier.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.rejectionReason == null)? 0 :this.rejectionReason.hashCode()));
        result = ((result* 31)+((this.transactionStatus == null)? 0 :this.transactionStatus.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RejectDO) == false) {
            return false;
        }
        RejectDO rhs = ((RejectDO) other);
        return ((((((this.psId == rhs.psId)||((this.psId!= null)&&this.psId.equals(rhs.psId)))&&((this.psIdentifier == rhs.psIdentifier)||((this.psIdentifier!= null)&&this.psIdentifier.equals(rhs.psIdentifier))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.rejectionReason == rhs.rejectionReason)||((this.rejectionReason!= null)&&this.rejectionReason.equals(rhs.rejectionReason))))&&((this.transactionStatus == rhs.transactionStatus)||((this.transactionStatus!= null)&&this.transactionStatus.equals(rhs.transactionStatus))));
    }

}