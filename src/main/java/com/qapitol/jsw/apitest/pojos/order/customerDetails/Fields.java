
package com.qapitol.jsw.apitest.pojos.order.customerDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "streetLine1",
    "isPrimaryAddress",
    "modifiedBy",
    "shipToParty",
    "createdAt",
    "modifiedAt",
    "streetLine2"
})
@Generated("jsonschema2pojo")
public class Fields {

    @JsonProperty("streetLine1")
    private String streetLine1;
    @JsonProperty("isPrimaryAddress")
    private Boolean isPrimaryAddress;
    @JsonProperty("modifiedBy")
    private String modifiedBy;
    @JsonProperty("shipToParty")
    private String shipToParty;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("modifiedAt")
    private String modifiedAt;
    @JsonProperty("streetLine2")
    private String streetLine2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("streetLine1")
    public String getStreetLine1() {
        return streetLine1;
    }

    @JsonProperty("streetLine1")
    public void setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    @JsonProperty("isPrimaryAddress")
    public Boolean getIsPrimaryAddress() {
        return isPrimaryAddress;
    }

    @JsonProperty("isPrimaryAddress")
    public void setIsPrimaryAddress(Boolean isPrimaryAddress) {
        this.isPrimaryAddress = isPrimaryAddress;
    }

    @JsonProperty("modifiedBy")
    public String getModifiedBy() {
        return modifiedBy;
    }

    @JsonProperty("modifiedBy")
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @JsonProperty("shipToParty")
    public String getShipToParty() {
        return shipToParty;
    }

    @JsonProperty("shipToParty")
    public void setShipToParty(String shipToParty) {
        this.shipToParty = shipToParty;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("modifiedAt")
    public String getModifiedAt() {
        return modifiedAt;
    }

    @JsonProperty("modifiedAt")
    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @JsonProperty("streetLine2")
    public String getStreetLine2() {
        return streetLine2;
    }

    @JsonProperty("streetLine2")
    public void setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
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
        sb.append(Fields.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("streetLine1");
        sb.append('=');
        sb.append(((this.streetLine1 == null)?"<null>":this.streetLine1));
        sb.append(',');
        sb.append("isPrimaryAddress");
        sb.append('=');
        sb.append(((this.isPrimaryAddress == null)?"<null>":this.isPrimaryAddress));
        sb.append(',');
        sb.append("modifiedBy");
        sb.append('=');
        sb.append(((this.modifiedBy == null)?"<null>":this.modifiedBy));
        sb.append(',');
        sb.append("shipToParty");
        sb.append('=');
        sb.append(((this.shipToParty == null)?"<null>":this.shipToParty));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("modifiedAt");
        sb.append('=');
        sb.append(((this.modifiedAt == null)?"<null>":this.modifiedAt));
        sb.append(',');
        sb.append("streetLine2");
        sb.append('=');
        sb.append(((this.streetLine2 == null)?"<null>":this.streetLine2));
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
        result = ((result* 31)+((this.streetLine2 == null)? 0 :this.streetLine2 .hashCode()));
        result = ((result* 31)+((this.streetLine1 == null)? 0 :this.streetLine1 .hashCode()));
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.modifiedAt == null)? 0 :this.modifiedAt.hashCode()));
        result = ((result* 31)+((this.isPrimaryAddress == null)? 0 :this.isPrimaryAddress.hashCode()));
        result = ((result* 31)+((this.modifiedBy == null)? 0 :this.modifiedBy.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.shipToParty == null)? 0 :this.shipToParty.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Fields) == false) {
            return false;
        }
        Fields rhs = ((Fields) other);
        return (((((((((this.streetLine2 == rhs.streetLine2)||((this.streetLine2 != null)&&this.streetLine2 .equals(rhs.streetLine2)))&&((this.streetLine1 == rhs.streetLine1)||((this.streetLine1 != null)&&this.streetLine1 .equals(rhs.streetLine1))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.modifiedAt == rhs.modifiedAt)||((this.modifiedAt!= null)&&this.modifiedAt.equals(rhs.modifiedAt))))&&((this.isPrimaryAddress == rhs.isPrimaryAddress)||((this.isPrimaryAddress!= null)&&this.isPrimaryAddress.equals(rhs.isPrimaryAddress))))&&((this.modifiedBy == rhs.modifiedBy)||((this.modifiedBy!= null)&&this.modifiedBy.equals(rhs.modifiedBy))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.shipToParty == rhs.shipToParty)||((this.shipToParty!= null)&&this.shipToParty.equals(rhs.shipToParty))));
    }

}
