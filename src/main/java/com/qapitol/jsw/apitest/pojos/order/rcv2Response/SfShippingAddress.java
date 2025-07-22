
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "streetLine",
    "streetLine2",
    "streetLineAddress",
    "postalCode",
    "city",
    "region",
    "state",
    "company",
    "mobile",
    "name",
    "retailerAddressSFId"
})
@Generated("jsonschema2pojo")
public class SfShippingAddress {

    @JsonProperty("id")
    private Object id;
    @JsonProperty("streetLine")
    private Object streetLine;
    @JsonProperty("streetLine2")
    private Object streetLine2;
    @JsonProperty("streetLineAddress")
    private Object streetLineAddress;
    @JsonProperty("postalCode")
    private Object postalCode;
    @JsonProperty("city")
    private Object city;
    @JsonProperty("region")
    private Object region;
    @JsonProperty("state")
    private Object state;
    @JsonProperty("company")
    private Object company;
    @JsonProperty("mobile")
    private Object mobile;
    @JsonProperty("name")
    private Object name;
    @JsonProperty("retailerAddressSFId")
    private Object retailerAddressSFId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Object id) {
        this.id = id;
    }

    @JsonProperty("streetLine")
    public Object getStreetLine() {
        return streetLine;
    }

    @JsonProperty("streetLine")
    public void setStreetLine(Object streetLine) {
        this.streetLine = streetLine;
    }

    @JsonProperty("streetLine2")
    public Object getStreetLine2() {
        return streetLine2;
    }

    @JsonProperty("streetLine2")
    public void setStreetLine2(Object streetLine2) {
        this.streetLine2 = streetLine2;
    }

    @JsonProperty("streetLineAddress")
    public Object getStreetLineAddress() {
        return streetLineAddress;
    }

    @JsonProperty("streetLineAddress")
    public void setStreetLineAddress(Object streetLineAddress) {
        this.streetLineAddress = streetLineAddress;
    }

    @JsonProperty("postalCode")
    public Object getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty("city")
    public Object getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(Object city) {
        this.city = city;
    }

    @JsonProperty("region")
    public Object getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(Object region) {
        this.region = region;
    }

    @JsonProperty("state")
    public Object getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(Object state) {
        this.state = state;
    }

    @JsonProperty("company")
    public Object getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(Object company) {
        this.company = company;
    }

    @JsonProperty("mobile")
    public Object getMobile() {
        return mobile;
    }

    @JsonProperty("mobile")
    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    @JsonProperty("name")
    public Object getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Object name) {
        this.name = name;
    }

    @JsonProperty("retailerAddressSFId")
    public Object getRetailerAddressSFId() {
        return retailerAddressSFId;
    }

    @JsonProperty("retailerAddressSFId")
    public void setRetailerAddressSFId(Object retailerAddressSFId) {
        this.retailerAddressSFId = retailerAddressSFId;
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
        sb.append(SfShippingAddress.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("streetLine");
        sb.append('=');
        sb.append(((this.streetLine == null)?"<null>":this.streetLine));
        sb.append(',');
        sb.append("streetLine2");
        sb.append('=');
        sb.append(((this.streetLine2 == null)?"<null>":this.streetLine2));
        sb.append(',');
        sb.append("streetLineAddress");
        sb.append('=');
        sb.append(((this.streetLineAddress == null)?"<null>":this.streetLineAddress));
        sb.append(',');
        sb.append("postalCode");
        sb.append('=');
        sb.append(((this.postalCode == null)?"<null>":this.postalCode));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("region");
        sb.append('=');
        sb.append(((this.region == null)?"<null>":this.region));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("company");
        sb.append('=');
        sb.append(((this.company == null)?"<null>":this.company));
        sb.append(',');
        sb.append("mobile");
        sb.append('=');
        sb.append(((this.mobile == null)?"<null>":this.mobile));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("retailerAddressSFId");
        sb.append('=');
        sb.append(((this.retailerAddressSFId == null)?"<null>":this.retailerAddressSFId));
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
        result = ((result* 31)+((this.city == null)? 0 :this.city.hashCode()));
        result = ((result* 31)+((this.postalCode == null)? 0 :this.postalCode.hashCode()));
        result = ((result* 31)+((this.mobile == null)? 0 :this.mobile.hashCode()));
        result = ((result* 31)+((this.retailerAddressSFId == null)? 0 :this.retailerAddressSFId.hashCode()));
        result = ((result* 31)+((this.streetLine == null)? 0 :this.streetLine.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.company == null)? 0 :this.company.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.region == null)? 0 :this.region.hashCode()));
        result = ((result* 31)+((this.streetLineAddress == null)? 0 :this.streetLineAddress.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SfShippingAddress) == false) {
            return false;
        }
        SfShippingAddress rhs = ((SfShippingAddress) other);
        return ((((((((((((((this.streetLine2 == rhs.streetLine2)||((this.streetLine2 != null)&&this.streetLine2 .equals(rhs.streetLine2)))&&((this.city == rhs.city)||((this.city!= null)&&this.city.equals(rhs.city))))&&((this.postalCode == rhs.postalCode)||((this.postalCode!= null)&&this.postalCode.equals(rhs.postalCode))))&&((this.mobile == rhs.mobile)||((this.mobile!= null)&&this.mobile.equals(rhs.mobile))))&&((this.retailerAddressSFId == rhs.retailerAddressSFId)||((this.retailerAddressSFId!= null)&&this.retailerAddressSFId.equals(rhs.retailerAddressSFId))))&&((this.streetLine == rhs.streetLine)||((this.streetLine!= null)&&this.streetLine.equals(rhs.streetLine))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.company == rhs.company)||((this.company!= null)&&this.company.equals(rhs.company))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.region == rhs.region)||((this.region!= null)&&this.region.equals(rhs.region))))&&((this.streetLineAddress == rhs.streetLineAddress)||((this.streetLineAddress!= null)&&this.streetLineAddress.equals(rhs.streetLineAddress))));
    }

}
