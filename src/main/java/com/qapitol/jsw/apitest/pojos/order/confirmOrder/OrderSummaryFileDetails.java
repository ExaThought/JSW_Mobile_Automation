
package com.qapitol.jsw.apitest.pojos.order.confirmOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "path",
    "url",
    "originalName",
    "whenCreated",
    "generatedId",
    "createdDate",
    "active",
    "tags",
    "documentId",
    "documentDate"
})
@Generated("jsonschema2pojo")
public class OrderSummaryFileDetails {

    @JsonProperty("path")
    private String path;
    @JsonProperty("url")
    private String url;
    @JsonProperty("originalName")
    private String originalName;
    @JsonProperty("whenCreated")
    private String whenCreated;
    @JsonProperty("generatedId")
    private String generatedId;
    @JsonProperty("createdDate")
    private Object createdDate;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("tags")
    private Object tags;
    @JsonProperty("documentId")
    private Object documentId;
    @JsonProperty("documentDate")
    private Object documentDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("originalName")
    public String getOriginalName() {
        return originalName;
    }

    @JsonProperty("originalName")
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @JsonProperty("whenCreated")
    public String getWhenCreated() {
        return whenCreated;
    }

    @JsonProperty("whenCreated")
    public void setWhenCreated(String whenCreated) {
        this.whenCreated = whenCreated;
    }

    @JsonProperty("generatedId")
    public String getGeneratedId() {
        return generatedId;
    }

    @JsonProperty("generatedId")
    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    @JsonProperty("createdDate")
    public Object getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("tags")
    public Object getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Object tags) {
        this.tags = tags;
    }

    @JsonProperty("documentId")
    public Object getDocumentId() {
        return documentId;
    }

    @JsonProperty("documentId")
    public void setDocumentId(Object documentId) {
        this.documentId = documentId;
    }

    @JsonProperty("documentDate")
    public Object getDocumentDate() {
        return documentDate;
    }

    @JsonProperty("documentDate")
    public void setDocumentDate(Object documentDate) {
        this.documentDate = documentDate;
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
        sb.append(OrderSummaryFileDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("path");
        sb.append('=');
        sb.append(((this.path == null)?"<null>":this.path));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("originalName");
        sb.append('=');
        sb.append(((this.originalName == null)?"<null>":this.originalName));
        sb.append(',');
        sb.append("whenCreated");
        sb.append('=');
        sb.append(((this.whenCreated == null)?"<null>":this.whenCreated));
        sb.append(',');
        sb.append("generatedId");
        sb.append('=');
        sb.append(((this.generatedId == null)?"<null>":this.generatedId));
        sb.append(',');
        sb.append("createdDate");
        sb.append('=');
        sb.append(((this.createdDate == null)?"<null>":this.createdDate));
        sb.append(',');
        sb.append("active");
        sb.append('=');
        sb.append(((this.active == null)?"<null>":this.active));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
        sb.append(',');
        sb.append("documentId");
        sb.append('=');
        sb.append(((this.documentId == null)?"<null>":this.documentId));
        sb.append(',');
        sb.append("documentDate");
        sb.append('=');
        sb.append(((this.documentDate == null)?"<null>":this.documentDate));
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
        result = ((result* 31)+((this.originalName == null)? 0 :this.originalName.hashCode()));
        result = ((result* 31)+((this.generatedId == null)? 0 :this.generatedId.hashCode()));
        result = ((result* 31)+((this.documentDate == null)? 0 :this.documentDate.hashCode()));
        result = ((result* 31)+((this.path == null)? 0 :this.path.hashCode()));
        result = ((result* 31)+((this.whenCreated == null)? 0 :this.whenCreated.hashCode()));
        result = ((result* 31)+((this.createdDate == null)? 0 :this.createdDate.hashCode()));
        result = ((result* 31)+((this.active == null)? 0 :this.active.hashCode()));
        result = ((result* 31)+((this.documentId == null)? 0 :this.documentId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.tags == null)? 0 :this.tags.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderSummaryFileDetails) == false) {
            return false;
        }
        OrderSummaryFileDetails rhs = ((OrderSummaryFileDetails) other);
        return ((((((((((((this.originalName == rhs.originalName)||((this.originalName!= null)&&this.originalName.equals(rhs.originalName)))&&((this.generatedId == rhs.generatedId)||((this.generatedId!= null)&&this.generatedId.equals(rhs.generatedId))))&&((this.documentDate == rhs.documentDate)||((this.documentDate!= null)&&this.documentDate.equals(rhs.documentDate))))&&((this.path == rhs.path)||((this.path!= null)&&this.path.equals(rhs.path))))&&((this.whenCreated == rhs.whenCreated)||((this.whenCreated!= null)&&this.whenCreated.equals(rhs.whenCreated))))&&((this.createdDate == rhs.createdDate)||((this.createdDate!= null)&&this.createdDate.equals(rhs.createdDate))))&&((this.active == rhs.active)||((this.active!= null)&&this.active.equals(rhs.active))))&&((this.documentId == rhs.documentId)||((this.documentId!= null)&&this.documentId.equals(rhs.documentId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.tags == rhs.tags)||((this.tags!= null)&&this.tags.equals(rhs.tags))));
    }

}
