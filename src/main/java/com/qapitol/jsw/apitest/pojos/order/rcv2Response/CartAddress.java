
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sf_address_id",
    "ct_address_id",
    "address_type",
    "street_line1",
    "street_line2",
    "pin_code",
    "city",
    "region",
    "state",
    "company",
    "mobile",
    "name",
    "email"
})
@Generated("jsonschema2pojo")
public class CartAddress {

    @JsonProperty("sf_address_id")
    private String sfAddressId;
    @JsonProperty("ct_address_id")
    private String ctAddressId;
    @JsonProperty("address_type")
    private String addressType;
    @JsonProperty("street_line1")
    private String streetLine1;
    @JsonProperty("street_line2")
    private String streetLine2;
    @JsonProperty("pin_code")
    private String pinCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("region")
    private String region;
    @JsonProperty("state")
    private String state;
    @JsonProperty("company")
    private String company;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sf_address_id")
    public String getSfAddressId() {
        return sfAddressId;
    }

    @JsonProperty("sf_address_id")
    public void setSfAddressId(String sfAddressId) {
        this.sfAddressId = sfAddressId;
    }

    @JsonProperty("ct_address_id")
    public String getCtAddressId() {
        return ctAddressId;
    }

    @JsonProperty("ct_address_id")
    public void setCtAddressId(String ctAddressId) {
        this.ctAddressId = ctAddressId;
    }

    @JsonProperty("address_type")
    public String getAddressType() {
        return addressType;
    }

    @JsonProperty("address_type")
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @JsonProperty("street_line1")
    public String getStreetLine1() {
        return streetLine1;
    }

    @JsonProperty("street_line1")
    public void setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    @JsonProperty("street_line2")
    public String getStreetLine2() {
        return streetLine2;
    }

    @JsonProperty("street_line2")
    public void setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    @JsonProperty("pin_code")
    public String getPinCode() {
        return pinCode;
    }

    @JsonProperty("pin_code")
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("mobile")
    public String getMobile() {
        return mobile;
    }

    @JsonProperty("mobile")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
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
        sb.append(CartAddress.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sfAddressId");
        sb.append('=');
        sb.append(((this.sfAddressId == null)?"<null>":this.sfAddressId));
        sb.append(',');
        sb.append("ctAddressId");
        sb.append('=');
        sb.append(((this.ctAddressId == null)?"<null>":this.ctAddressId));
        sb.append(',');
        sb.append("addressType");
        sb.append('=');
        sb.append(((this.addressType == null)?"<null>":this.addressType));
        sb.append(',');
        sb.append("streetLine1");
        sb.append('=');
        sb.append(((this.streetLine1 == null)?"<null>":this.streetLine1));
        sb.append(',');
        sb.append("streetLine2");
        sb.append('=');
        sb.append(((this.streetLine2 == null)?"<null>":this.streetLine2));
        sb.append(',');
        sb.append("pinCode");
        sb.append('=');
        sb.append(((this.pinCode == null)?"<null>":this.pinCode));
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
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
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
        result = ((result* 31)+((this.city == null)? 0 :this.city.hashCode()));
        result = ((result* 31)+((this.addressType == null)? 0 :this.addressType.hashCode()));
        result = ((result* 31)+((this.mobile == null)? 0 :this.mobile.hashCode()));
        result = ((result* 31)+((this.sfAddressId == null)? 0 :this.sfAddressId.hashCode()));
        result = ((result* 31)+((this.ctAddressId == null)? 0 :this.ctAddressId.hashCode()));
        result = ((result* 31)+((this.pinCode == null)? 0 :this.pinCode.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.company == null)? 0 :this.company.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.region == null)? 0 :this.region.hashCode()));
        result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CartAddress) == false) {
            return false;
        }
        CartAddress rhs = ((CartAddress) other);
        return (((((((((((((((this.streetLine2 == rhs.streetLine2)||((this.streetLine2 != null)&&this.streetLine2 .equals(rhs.streetLine2)))&&((this.streetLine1 == rhs.streetLine1)||((this.streetLine1 != null)&&this.streetLine1 .equals(rhs.streetLine1))))&&((this.city == rhs.city)||((this.city!= null)&&this.city.equals(rhs.city))))&&((this.addressType == rhs.addressType)||((this.addressType!= null)&&this.addressType.equals(rhs.addressType))))&&((this.mobile == rhs.mobile)||((this.mobile!= null)&&this.mobile.equals(rhs.mobile))))&&((this.sfAddressId == rhs.sfAddressId)||((this.sfAddressId!= null)&&this.sfAddressId.equals(rhs.sfAddressId))))&&((this.ctAddressId == rhs.ctAddressId)||((this.ctAddressId!= null)&&this.ctAddressId.equals(rhs.ctAddressId))))&&((this.pinCode == rhs.pinCode)||((this.pinCode!= null)&&this.pinCode.equals(rhs.pinCode))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.company == rhs.company)||((this.company!= null)&&this.company.equals(rhs.company))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.region == rhs.region)||((this.region!= null)&&this.region.equals(rhs.region))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))));
    }

}
