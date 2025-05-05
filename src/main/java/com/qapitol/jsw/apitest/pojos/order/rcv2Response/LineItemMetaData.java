
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "created_at",
    "modified_at",
    "line_item_error",
    "variant_hash"
})
@Generated("jsonschema2pojo")
public class LineItemMetaData {

    @JsonProperty("status")
    private String status;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("modified_at")
    private String modifiedAt;
    @JsonProperty("line_item_error")
    private Object lineItemError;
    @JsonProperty("variant_hash")
    private String variantHash;
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

    @JsonProperty("line_item_error")
    public Object getLineItemError() {
        return lineItemError;
    }

    @JsonProperty("line_item_error")
    public void setLineItemError(Object lineItemError) {
        this.lineItemError = lineItemError;
    }

    @JsonProperty("variant_hash")
    public String getVariantHash() {
        return variantHash;
    }

    @JsonProperty("variant_hash")
    public void setVariantHash(String variantHash) {
        this.variantHash = variantHash;
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
        sb.append(LineItemMetaData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("modifiedAt");
        sb.append('=');
        sb.append(((this.modifiedAt == null)?"<null>":this.modifiedAt));
        sb.append(',');
        sb.append("lineItemError");
        sb.append('=');
        sb.append(((this.lineItemError == null)?"<null>":this.lineItemError));
        sb.append(',');
        sb.append("variantHash");
        sb.append('=');
        sb.append(((this.variantHash == null)?"<null>":this.variantHash));
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
        result = ((result* 31)+((this.variantHash == null)? 0 :this.variantHash.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.lineItemError == null)? 0 :this.lineItemError.hashCode()));
        result = ((result* 31)+((this.modifiedAt == null)? 0 :this.modifiedAt.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemMetaData) == false) {
            return false;
        }
        LineItemMetaData rhs = ((LineItemMetaData) other);
        return (((((((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt)))&&((this.variantHash == rhs.variantHash)||((this.variantHash!= null)&&this.variantHash.equals(rhs.variantHash))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.lineItemError == rhs.lineItemError)||((this.lineItemError!= null)&&this.lineItemError.equals(rhs.lineItemError))))&&((this.modifiedAt == rhs.modifiedAt)||((this.modifiedAt!= null)&&this.modifiedAt.equals(rhs.modifiedAt))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
