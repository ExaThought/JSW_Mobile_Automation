
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "id",
    "category",
    "version",
    "isZeroAdvanceOrder",
    "journeyType"
})
@Generated("jsonschema2pojo")
public class Identifier {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("category")
    private String category;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("isZeroAdvanceOrder")
    private Boolean isZeroAdvanceOrder;
    @JsonProperty("journeyType")
    private String journeyType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("isZeroAdvanceOrder")
    public Boolean getIsZeroAdvanceOrder() {
        return isZeroAdvanceOrder;
    }

    @JsonProperty("isZeroAdvanceOrder")
    public void setIsZeroAdvanceOrder(Boolean isZeroAdvanceOrder) {
        this.isZeroAdvanceOrder = isZeroAdvanceOrder;
    }

    @JsonProperty("journeyType")
    public String getJourneyType() {
        return journeyType;
    }

    @JsonProperty("journeyType")
    public void setJourneyType(String journeyType) {
        this.journeyType = journeyType;
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
        sb.append(Identifier.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("isZeroAdvanceOrder");
        sb.append('=');
        sb.append(((this.isZeroAdvanceOrder == null)?"<null>":this.isZeroAdvanceOrder));
        sb.append(',');
        sb.append("journeyType");
        sb.append('=');
        sb.append(((this.journeyType == null)?"<null>":this.journeyType));
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
        result = ((result* 31)+((this.isZeroAdvanceOrder == null)? 0 :this.isZeroAdvanceOrder.hashCode()));
        result = ((result* 31)+((this.journeyType == null)? 0 :this.journeyType.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.category == null)? 0 :this.category.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Identifier) == false) {
            return false;
        }
        Identifier rhs = ((Identifier) other);
        return ((((((((this.isZeroAdvanceOrder == rhs.isZeroAdvanceOrder)||((this.isZeroAdvanceOrder!= null)&&this.isZeroAdvanceOrder.equals(rhs.isZeroAdvanceOrder)))&&((this.journeyType == rhs.journeyType)||((this.journeyType!= null)&&this.journeyType.equals(rhs.journeyType))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.category == rhs.category)||((this.category!= null)&&this.category.equals(rhs.category))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))));
    }

}
