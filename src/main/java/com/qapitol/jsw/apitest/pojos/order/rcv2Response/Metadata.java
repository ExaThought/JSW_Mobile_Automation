
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "created_by",
    "modified_by",
    "created_at",
    "modified_at",
    "expires_at"
})
@Generated("jsonschema2pojo")
public class Metadata {

    @JsonProperty("status")
    private String status;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("modified_by")
    private String modifiedBy;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("modified_at")
    private String modifiedAt;
    @JsonProperty("expires_at")
    private Object expiresAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("created_by")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("modified_by")
    public String getModifiedBy() {
        return modifiedBy;
    }

    @JsonProperty("modified_by")
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("modified_at")
    public String getModifiedAt() {
        return modifiedAt;
    }

    @JsonProperty("modified_at")
    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @JsonProperty("expires_at")
    public Object getExpiresAt() {
        return expiresAt;
    }

    @JsonProperty("expires_at")
    public void setExpiresAt(Object expiresAt) {
        this.expiresAt = expiresAt;
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
        sb.append(Metadata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("createdBy");
        sb.append('=');
        sb.append(((this.createdBy == null)?"<null>":this.createdBy));
        sb.append(',');
        sb.append("modifiedBy");
        sb.append('=');
        sb.append(((this.modifiedBy == null)?"<null>":this.modifiedBy));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("modifiedAt");
        sb.append('=');
        sb.append(((this.modifiedAt == null)?"<null>":this.modifiedAt));
        sb.append(',');
        sb.append("expiresAt");
        sb.append('=');
        sb.append(((this.expiresAt == null)?"<null>":this.expiresAt));
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
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.createdBy == null)? 0 :this.createdBy.hashCode()));
        result = ((result* 31)+((this.modifiedAt == null)? 0 :this.modifiedAt.hashCode()));
        result = ((result* 31)+((this.modifiedBy == null)? 0 :this.modifiedBy.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.expiresAt == null)? 0 :this.expiresAt.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metadata) == false) {
            return false;
        }
        Metadata rhs = ((Metadata) other);
        return ((((((((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt)))&&((this.createdBy == rhs.createdBy)||((this.createdBy!= null)&&this.createdBy.equals(rhs.createdBy))))&&((this.modifiedAt == rhs.modifiedAt)||((this.modifiedAt!= null)&&this.modifiedAt.equals(rhs.modifiedAt))))&&((this.modifiedBy == rhs.modifiedBy)||((this.modifiedBy!= null)&&this.modifiedBy.equals(rhs.modifiedBy))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.expiresAt == rhs.expiresAt)||((this.expiresAt!= null)&&this.expiresAt.equals(rhs.expiresAt))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
