
package com.qapitol.jsw.apitest.pojos.order.customerDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "key",
    "title",
    "salutation",
    "firstName",
    "lastName",
    "streetName",
    "streetNumber",
    "additionalStreetInfo",
    "postalCode",
    "city",
    "region",
    "state",
    "country",
    "company",
    "department",
    "building",
    "apartment",
    "pOBox",
    "phone",
    "mobile",
    "email",
    "fax",
    "additionalAddressInfo",
    "externalId",
    "custom"
})
@Generated("jsonschema2pojo")
public class Address {

    @JsonProperty("id")
    private String id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("title")
    private Object title;
    @JsonProperty("salutation")
    private Object salutation;
    @JsonProperty("firstName")
    private Object firstName;
    @JsonProperty("lastName")
    private Object lastName;
    @JsonProperty("streetName")
    private Object streetName;
    @JsonProperty("streetNumber")
    private Object streetNumber;
    @JsonProperty("additionalStreetInfo")
    private Object additionalStreetInfo;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("region")
    private String region;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("company")
    private String company;
    @JsonProperty("department")
    private Object department;
    @JsonProperty("building")
    private Object building;
    @JsonProperty("apartment")
    private Object apartment;
    @JsonProperty("pOBox")
    private Object pOBox;
    @JsonProperty("phone")
    private Object phone;
    @JsonProperty("mobile")
    private Object mobile;
    @JsonProperty("email")
    private Object email;
    @JsonProperty("fax")
    private Object fax;
    @JsonProperty("additionalAddressInfo")
    private Object additionalAddressInfo;
    @JsonProperty("externalId")
    private Object externalId;
    @JsonProperty("custom")
    private Custom custom;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("title")
    public Object getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Object title) {
        this.title = title;
    }

    @JsonProperty("salutation")
    public Object getSalutation() {
        return salutation;
    }

    @JsonProperty("salutation")
    public void setSalutation(Object salutation) {
        this.salutation = salutation;
    }

    @JsonProperty("firstName")
    public Object getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public Object getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("streetName")
    public Object getStreetName() {
        return streetName;
    }

    @JsonProperty("streetName")
    public void setStreetName(Object streetName) {
        this.streetName = streetName;
    }

    @JsonProperty("streetNumber")
    public Object getStreetNumber() {
        return streetNumber;
    }

    @JsonProperty("streetNumber")
    public void setStreetNumber(Object streetNumber) {
        this.streetNumber = streetNumber;
    }

    @JsonProperty("additionalStreetInfo")
    public Object getAdditionalStreetInfo() {
        return additionalStreetInfo;
    }

    @JsonProperty("additionalStreetInfo")
    public void setAdditionalStreetInfo(Object additionalStreetInfo) {
        this.additionalStreetInfo = additionalStreetInfo;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("department")
    public Object getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(Object department) {
        this.department = department;
    }

    @JsonProperty("building")
    public Object getBuilding() {
        return building;
    }

    @JsonProperty("building")
    public void setBuilding(Object building) {
        this.building = building;
    }

    @JsonProperty("apartment")
    public Object getApartment() {
        return apartment;
    }

    @JsonProperty("apartment")
    public void setApartment(Object apartment) {
        this.apartment = apartment;
    }

    @JsonProperty("pOBox")
    public Object getpOBox() {
        return pOBox;
    }

    @JsonProperty("pOBox")
    public void setpOBox(Object pOBox) {
        this.pOBox = pOBox;
    }

    @JsonProperty("phone")
    public Object getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(Object phone) {
        this.phone = phone;
    }

    @JsonProperty("mobile")
    public Object getMobile() {
        return mobile;
    }

    @JsonProperty("mobile")
    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    @JsonProperty("email")
    public Object getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(Object email) {
        this.email = email;
    }

    @JsonProperty("fax")
    public Object getFax() {
        return fax;
    }

    @JsonProperty("fax")
    public void setFax(Object fax) {
        this.fax = fax;
    }

    @JsonProperty("additionalAddressInfo")
    public Object getAdditionalAddressInfo() {
        return additionalAddressInfo;
    }

    @JsonProperty("additionalAddressInfo")
    public void setAdditionalAddressInfo(Object additionalAddressInfo) {
        this.additionalAddressInfo = additionalAddressInfo;
    }

    @JsonProperty("externalId")
    public Object getExternalId() {
        return externalId;
    }

    @JsonProperty("externalId")
    public void setExternalId(Object externalId) {
        this.externalId = externalId;
    }

    @JsonProperty("custom")
    public Custom getCustom() {
        return custom;
    }

    @JsonProperty("custom")
    public void setCustom(Custom custom) {
        this.custom = custom;
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
        sb.append(Address.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("key");
        sb.append('=');
        sb.append(((this.key == null)?"<null>":this.key));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("salutation");
        sb.append('=');
        sb.append(((this.salutation == null)?"<null>":this.salutation));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("streetName");
        sb.append('=');
        sb.append(((this.streetName == null)?"<null>":this.streetName));
        sb.append(',');
        sb.append("streetNumber");
        sb.append('=');
        sb.append(((this.streetNumber == null)?"<null>":this.streetNumber));
        sb.append(',');
        sb.append("additionalStreetInfo");
        sb.append('=');
        sb.append(((this.additionalStreetInfo == null)?"<null>":this.additionalStreetInfo));
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
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(',');
        sb.append("company");
        sb.append('=');
        sb.append(((this.company == null)?"<null>":this.company));
        sb.append(',');
        sb.append("department");
        sb.append('=');
        sb.append(((this.department == null)?"<null>":this.department));
        sb.append(',');
        sb.append("building");
        sb.append('=');
        sb.append(((this.building == null)?"<null>":this.building));
        sb.append(',');
        sb.append("apartment");
        sb.append('=');
        sb.append(((this.apartment == null)?"<null>":this.apartment));
        sb.append(',');
        sb.append("pOBox");
        sb.append('=');
        sb.append(((this.pOBox == null)?"<null>":this.pOBox));
        sb.append(',');
        sb.append("phone");
        sb.append('=');
        sb.append(((this.phone == null)?"<null>":this.phone));
        sb.append(',');
        sb.append("mobile");
        sb.append('=');
        sb.append(((this.mobile == null)?"<null>":this.mobile));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("fax");
        sb.append('=');
        sb.append(((this.fax == null)?"<null>":this.fax));
        sb.append(',');
        sb.append("additionalAddressInfo");
        sb.append('=');
        sb.append(((this.additionalAddressInfo == null)?"<null>":this.additionalAddressInfo));
        sb.append(',');
        sb.append("externalId");
        sb.append('=');
        sb.append(((this.externalId == null)?"<null>":this.externalId));
        sb.append(',');
        sb.append("custom");
        sb.append('=');
        sb.append(((this.custom == null)?"<null>":this.custom));
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
        result = ((result* 31)+((this.lastName == null)? 0 :this.lastName.hashCode()));
        result = ((result* 31)+((this.country == null)? 0 :this.country.hashCode()));
        result = ((result* 31)+((this.city == null)? 0 :this.city.hashCode()));
        result = ((result* 31)+((this.postalCode == null)? 0 :this.postalCode.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.building == null)? 0 :this.building.hashCode()));
        result = ((result* 31)+((this.streetName == null)? 0 :this.streetName.hashCode()));
        result = ((result* 31)+((this.company == null)? 0 :this.company.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.department == null)? 0 :this.department.hashCode()));
        result = ((result* 31)+((this.fax == null)? 0 :this.fax.hashCode()));
        result = ((result* 31)+((this.key == null)? 0 :this.key.hashCode()));
        result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
        result = ((result* 31)+((this.pOBox == null)? 0 :this.pOBox.hashCode()));
        result = ((result* 31)+((this.streetNumber == null)? 0 :this.streetNumber.hashCode()));
        result = ((result* 31)+((this.custom == null)? 0 :this.custom.hashCode()));
        result = ((result* 31)+((this.mobile == null)? 0 :this.mobile.hashCode()));
        result = ((result* 31)+((this.externalId == null)? 0 :this.externalId.hashCode()));
        result = ((result* 31)+((this.additionalAddressInfo == null)? 0 :this.additionalAddressInfo.hashCode()));
        result = ((result* 31)+((this.firstName == null)? 0 :this.firstName.hashCode()));
        result = ((result* 31)+((this.phone == null)? 0 :this.phone.hashCode()));
        result = ((result* 31)+((this.additionalStreetInfo == null)? 0 :this.additionalStreetInfo.hashCode()));
        result = ((result* 31)+((this.salutation == null)? 0 :this.salutation.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.region == null)? 0 :this.region.hashCode()));
        result = ((result* 31)+((this.apartment == null)? 0 :this.apartment.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address) == false) {
            return false;
        }
        Address rhs = ((Address) other);
        return ((((((((((((((((((((((((((((this.lastName == rhs.lastName)||((this.lastName!= null)&&this.lastName.equals(rhs.lastName)))&&((this.country == rhs.country)||((this.country!= null)&&this.country.equals(rhs.country))))&&((this.city == rhs.city)||((this.city!= null)&&this.city.equals(rhs.city))))&&((this.postalCode == rhs.postalCode)||((this.postalCode!= null)&&this.postalCode.equals(rhs.postalCode))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.building == rhs.building)||((this.building!= null)&&this.building.equals(rhs.building))))&&((this.streetName == rhs.streetName)||((this.streetName!= null)&&this.streetName.equals(rhs.streetName))))&&((this.company == rhs.company)||((this.company!= null)&&this.company.equals(rhs.company))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.department == rhs.department)||((this.department!= null)&&this.department.equals(rhs.department))))&&((this.fax == rhs.fax)||((this.fax!= null)&&this.fax.equals(rhs.fax))))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))))&&((this.pOBox == rhs.pOBox)||((this.pOBox!= null)&&this.pOBox.equals(rhs.pOBox))))&&((this.streetNumber == rhs.streetNumber)||((this.streetNumber!= null)&&this.streetNumber.equals(rhs.streetNumber))))&&((this.custom == rhs.custom)||((this.custom!= null)&&this.custom.equals(rhs.custom))))&&((this.mobile == rhs.mobile)||((this.mobile!= null)&&this.mobile.equals(rhs.mobile))))&&((this.externalId == rhs.externalId)||((this.externalId!= null)&&this.externalId.equals(rhs.externalId))))&&((this.additionalAddressInfo == rhs.additionalAddressInfo)||((this.additionalAddressInfo!= null)&&this.additionalAddressInfo.equals(rhs.additionalAddressInfo))))&&((this.firstName == rhs.firstName)||((this.firstName!= null)&&this.firstName.equals(rhs.firstName))))&&((this.phone == rhs.phone)||((this.phone!= null)&&this.phone.equals(rhs.phone))))&&((this.additionalStreetInfo == rhs.additionalStreetInfo)||((this.additionalStreetInfo!= null)&&this.additionalStreetInfo.equals(rhs.additionalStreetInfo))))&&((this.salutation == rhs.salutation)||((this.salutation!= null)&&this.salutation.equals(rhs.salutation))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.region == rhs.region)||((this.region!= null)&&this.region.equals(rhs.region))))&&((this.apartment == rhs.apartment)||((this.apartment!= null)&&this.apartment.equals(rhs.apartment))));
    }

}
