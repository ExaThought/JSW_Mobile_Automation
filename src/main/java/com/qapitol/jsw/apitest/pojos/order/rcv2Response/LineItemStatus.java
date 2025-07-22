
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineItemErrorType",
    "lineItemErrorMessage",
    "isActive"
})
@Generated("jsonschema2pojo")
public class LineItemStatus {

    @JsonProperty("lineItemErrorType")
    private Object lineItemErrorType;
    @JsonProperty("lineItemErrorMessage")
    private String lineItemErrorMessage;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("lineItemErrorType")
    public Object getLineItemErrorType() {
        return lineItemErrorType;
    }

    @JsonProperty("lineItemErrorType")
    public void setLineItemErrorType(Object lineItemErrorType) {
        this.lineItemErrorType = lineItemErrorType;
    }

    @JsonProperty("lineItemErrorMessage")
    public String getLineItemErrorMessage() {
        return lineItemErrorMessage;
    }

    @JsonProperty("lineItemErrorMessage")
    public void setLineItemErrorMessage(String lineItemErrorMessage) {
        this.lineItemErrorMessage = lineItemErrorMessage;
    }

    @JsonProperty("isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
        sb.append(LineItemStatus.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lineItemErrorType");
        sb.append('=');
        sb.append(((this.lineItemErrorType == null)?"<null>":this.lineItemErrorType));
        sb.append(',');
        sb.append("lineItemErrorMessage");
        sb.append('=');
        sb.append(((this.lineItemErrorMessage == null)?"<null>":this.lineItemErrorMessage));
        sb.append(',');
        sb.append("isActive");
        sb.append('=');
        sb.append(((this.isActive == null)?"<null>":this.isActive));
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
        result = ((result* 31)+((this.lineItemErrorMessage == null)? 0 :this.lineItemErrorMessage.hashCode()));
        result = ((result* 31)+((this.lineItemErrorType == null)? 0 :this.lineItemErrorType.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.isActive == null)? 0 :this.isActive.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemStatus) == false) {
            return false;
        }
        LineItemStatus rhs = ((LineItemStatus) other);
        return (((((this.lineItemErrorMessage == rhs.lineItemErrorMessage)||((this.lineItemErrorMessage!= null)&&this.lineItemErrorMessage.equals(rhs.lineItemErrorMessage)))&&((this.lineItemErrorType == rhs.lineItemErrorType)||((this.lineItemErrorType!= null)&&this.lineItemErrorType.equals(rhs.lineItemErrorType))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.isActive == rhs.isActive)||((this.isActive!= null)&&this.isActive.equals(rhs.isActive))));
    }

}
