
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "label",
    "ctKey",
    "lastModifiedBy",
    "lastModifiedDate"
})
@Generated("jsonschema2pojo")
public class CustomPaymentStatus {

    @JsonProperty("key")
    private Object key;
    @JsonProperty("label")
    private Object label;
    @JsonProperty("ctKey")
    private Object ctKey;
    @JsonProperty("lastModifiedBy")
    private Object lastModifiedBy;
    @JsonProperty("lastModifiedDate")
    private Integer lastModifiedDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("key")
    public Object getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(Object key) {
        this.key = key;
    }

    @JsonProperty("label")
    public Object getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(Object label) {
        this.label = label;
    }

    @JsonProperty("ctKey")
    public Object getCtKey() {
        return ctKey;
    }

    @JsonProperty("ctKey")
    public void setCtKey(Object ctKey) {
        this.ctKey = ctKey;
    }

    @JsonProperty("lastModifiedBy")
    public Object getLastModifiedBy() {
        return lastModifiedBy;
    }

    @JsonProperty("lastModifiedBy")
    public void setLastModifiedBy(Object lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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
        sb.append(CustomPaymentStatus.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("key");
        sb.append('=');
        sb.append(((this.key == null)?"<null>":this.key));
        sb.append(',');
        sb.append("label");
        sb.append('=');
        sb.append(((this.label == null)?"<null>":this.label));
        sb.append(',');
        sb.append("ctKey");
        sb.append('=');
        sb.append(((this.ctKey == null)?"<null>":this.ctKey));
        sb.append(',');
        sb.append("lastModifiedBy");
        sb.append('=');
        sb.append(((this.lastModifiedBy == null)?"<null>":this.lastModifiedBy));
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
        result = ((result* 31)+((this.lastModifiedDate == null)? 0 :this.lastModifiedDate.hashCode()));
        result = ((result* 31)+((this.ctKey == null)? 0 :this.ctKey.hashCode()));
        result = ((result* 31)+((this.lastModifiedBy == null)? 0 :this.lastModifiedBy.hashCode()));
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
        if ((other instanceof CustomPaymentStatus) == false) {
            return false;
        }
        CustomPaymentStatus rhs = ((CustomPaymentStatus) other);
        return (((((((this.lastModifiedDate == rhs.lastModifiedDate)||((this.lastModifiedDate!= null)&&this.lastModifiedDate.equals(rhs.lastModifiedDate)))&&((this.ctKey == rhs.ctKey)||((this.ctKey!= null)&&this.ctKey.equals(rhs.ctKey))))&&((this.lastModifiedBy == rhs.lastModifiedBy)||((this.lastModifiedBy!= null)&&this.lastModifiedBy.equals(rhs.lastModifiedBy))))&&((this.label == rhs.label)||((this.label!= null)&&this.label.equals(rhs.label))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))));
    }

}
