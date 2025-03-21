
package com.qapitol.jsw.apitest.pojos.order.confirmOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "label",
    "lastModifiedBy",
    "reasonForCancellation",
    "reasonForDecline",
    "lastModifiedDate"
})
@Generated("jsonschema2pojo")
public class OrderStateInfo {

    @JsonProperty("key")
    private String key;
    @JsonProperty("label")
    private String label;
    @JsonProperty("lastModifiedBy")
    private String lastModifiedBy;
    @JsonProperty("reasonForCancellation")
    private Object reasonForCancellation;
    @JsonProperty("reasonForDecline")
    private Object reasonForDecline;
    @JsonProperty("lastModifiedDate")
    private Integer lastModifiedDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("lastModifiedBy")
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @JsonProperty("lastModifiedBy")
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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

    @JsonProperty("lastModifiedDate")
    public Integer getLastModifiedDate() {
        return lastModifiedDate;
    }

    @JsonProperty("lastModifiedDate")
    public void setLastModifiedDate(Integer lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
        sb.append(OrderStateInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("key");
        sb.append('=');
        sb.append(((this.key == null)?"<null>":this.key));
        sb.append(',');
        sb.append("label");
        sb.append('=');
        sb.append(((this.label == null)?"<null>":this.label));
        sb.append(',');
        sb.append("lastModifiedBy");
        sb.append('=');
        sb.append(((this.lastModifiedBy == null)?"<null>":this.lastModifiedBy));
        sb.append(',');
        sb.append("reasonForCancellation");
        sb.append('=');
        sb.append(((this.reasonForCancellation == null)?"<null>":this.reasonForCancellation));
        sb.append(',');
        sb.append("reasonForDecline");
        sb.append('=');
        sb.append(((this.reasonForDecline == null)?"<null>":this.reasonForDecline));
        sb.append(',');
        sb.append("lastModifiedDate");
        sb.append('=');
        sb.append(((this.lastModifiedDate == null)?"<null>":this.lastModifiedDate));
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
        result = ((result* 31)+((this.reasonForDecline == null)? 0 :this.reasonForDecline.hashCode()));
        result = ((result* 31)+((this.lastModifiedDate == null)? 0 :this.lastModifiedDate.hashCode()));
        result = ((result* 31)+((this.lastModifiedBy == null)? 0 :this.lastModifiedBy.hashCode()));
        result = ((result* 31)+((this.reasonForCancellation == null)? 0 :this.reasonForCancellation.hashCode()));
        result = ((result* 31)+((this.label == null)? 0 :this.label.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.key == null)? 0 :this.key.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderStateInfo) == false) {
            return false;
        }
        OrderStateInfo rhs = ((OrderStateInfo) other);
        return ((((((((this.reasonForDecline == rhs.reasonForDecline)||((this.reasonForDecline!= null)&&this.reasonForDecline.equals(rhs.reasonForDecline)))&&((this.lastModifiedDate == rhs.lastModifiedDate)||((this.lastModifiedDate!= null)&&this.lastModifiedDate.equals(rhs.lastModifiedDate))))&&((this.lastModifiedBy == rhs.lastModifiedBy)||((this.lastModifiedBy!= null)&&this.lastModifiedBy.equals(rhs.lastModifiedBy))))&&((this.reasonForCancellation == rhs.reasonForCancellation)||((this.reasonForCancellation!= null)&&this.reasonForCancellation.equals(rhs.reasonForCancellation))))&&((this.label == rhs.label)||((this.label!= null)&&this.label.equals(rhs.label))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))));
    }

}
