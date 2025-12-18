
package com.qapitol.jsw.apitest.pojos.order.appTesting;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "location",
    "businessName",
    "businessContactNumber",
    "ownerName",
    "ownerContactNumber",
    "jswPreferred",
    "addressLine1",
    "addressLine2",
    "pincode",
    "city",
    "district",
    "state",
    "averageRating",
    "ownerEmail",
    "accountType",
    "selectedAddressId",
    "accountRecordType",
    "customerType",
    "businessDivision",
    "mmid",
    "isAcceptedPrivacyPolicy",
    "isAcceptedTermsConditions",
    "isAcceptedWhatsappConsent",
    "acceptedTermsConditionsTimeStamp",
    "acceptedPrivacyPolicyTimeStamp",
    "acceptedWhatsappConsentTimeStamp",
    "ctId",
    "sfId",
    "isCreditUser",
    "gstin",
    "panCardNumber",
    "permissions",
    "modules"
})
@Generated("jsonschema2pojo")
public class AppResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("location")
    private Object location;
    @JsonProperty("businessName")
    private String businessName;
    @JsonProperty("businessContactNumber")
    private String businessContactNumber;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("ownerContactNumber")
    private String ownerContactNumber;
    @JsonProperty("jswPreferred")
    private Boolean jswPreferred;
    @JsonProperty("addressLine1")
    private String addressLine1;
    @JsonProperty("addressLine2")
    private Object addressLine2;
    @JsonProperty("pincode")
    private Integer pincode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("district")
    private String district;
    @JsonProperty("state")
    private String state;
    @JsonProperty("averageRating")
    private Object averageRating;
    @JsonProperty("ownerEmail")
    private String ownerEmail;
    @JsonProperty("accountType")
    private String accountType;
    @JsonProperty("selectedAddressId")
    private String selectedAddressId;
    @JsonProperty("accountRecordType")
    private String accountRecordType;
    @JsonProperty("customerType")
    private String customerType;
    @JsonProperty("businessDivision")
    private String businessDivision;
    @JsonProperty("mmid")
    private String mmid;
    @JsonProperty("isAcceptedPrivacyPolicy")
    private Boolean isAcceptedPrivacyPolicy;
    @JsonProperty("isAcceptedTermsConditions")
    private Boolean isAcceptedTermsConditions;
    @JsonProperty("isAcceptedWhatsappConsent")
    private Boolean isAcceptedWhatsappConsent;
    @JsonProperty("acceptedTermsConditionsTimeStamp")
    private String acceptedTermsConditionsTimeStamp;
    @JsonProperty("acceptedPrivacyPolicyTimeStamp")
    private String acceptedPrivacyPolicyTimeStamp;
    @JsonProperty("acceptedWhatsappConsentTimeStamp")
    private String acceptedWhatsappConsentTimeStamp;
    @JsonProperty("ctId")
    private String ctId;
    @JsonProperty("sfId")
    private String sfId;
    @JsonProperty("isCreditUser")
    private Boolean isCreditUser;
    @JsonProperty("gstin")
    private String gstin;
    @JsonProperty("panCardNumber")
    private String panCardNumber;
    @JsonProperty("permissions")
    private Permissions permissions;
    @JsonProperty("modules")
    private List<String> modules = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("location")
    public Object getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Object location) {
        this.location = location;
    }

    @JsonProperty("businessName")
    public String getBusinessName() {
        return businessName;
    }

    @JsonProperty("businessName")
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @JsonProperty("businessContactNumber")
    public String getBusinessContactNumber() {
        return businessContactNumber;
    }

    @JsonProperty("businessContactNumber")
    public void setBusinessContactNumber(String businessContactNumber) {
        this.businessContactNumber = businessContactNumber;
    }

    @JsonProperty("ownerName")
    public String getOwnerName() {
        return ownerName;
    }

    @JsonProperty("ownerName")
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @JsonProperty("ownerContactNumber")
    public String getOwnerContactNumber() {
        return ownerContactNumber;
    }

    @JsonProperty("ownerContactNumber")
    public void setOwnerContactNumber(String ownerContactNumber) {
        this.ownerContactNumber = ownerContactNumber;
    }

    @JsonProperty("jswPreferred")
    public Boolean getJswPreferred() {
        return jswPreferred;
    }

    @JsonProperty("jswPreferred")
    public void setJswPreferred(Boolean jswPreferred) {
        this.jswPreferred = jswPreferred;
    }

    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("addressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @JsonProperty("addressLine2")
    public Object getAddressLine2() {
        return addressLine2;
    }

    @JsonProperty("addressLine2")
    public void setAddressLine2(Object addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @JsonProperty("pincode")
    public Integer getPincode() {
        return pincode;
    }

    @JsonProperty("pincode")
    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("district")
    public String getDistrict() {
        return district;
    }

    @JsonProperty("district")
    public void setDistrict(String district) {
        this.district = district;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("averageRating")
    public Object getAverageRating() {
        return averageRating;
    }

    @JsonProperty("averageRating")
    public void setAverageRating(Object averageRating) {
        this.averageRating = averageRating;
    }

    @JsonProperty("ownerEmail")
    public String getOwnerEmail() {
        return ownerEmail;
    }

    @JsonProperty("ownerEmail")
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @JsonProperty("accountType")
    public String getAccountType() {
        return accountType;
    }

    @JsonProperty("accountType")
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @JsonProperty("selectedAddressId")
    public String getSelectedAddressId() {
        return selectedAddressId;
    }

    @JsonProperty("selectedAddressId")
    public void setSelectedAddressId(String selectedAddressId) {
        this.selectedAddressId = selectedAddressId;
    }

    @JsonProperty("accountRecordType")
    public String getAccountRecordType() {
        return accountRecordType;
    }

    @JsonProperty("accountRecordType")
    public void setAccountRecordType(String accountRecordType) {
        this.accountRecordType = accountRecordType;
    }

    @JsonProperty("customerType")
    public String getCustomerType() {
        return customerType;
    }

    @JsonProperty("customerType")
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @JsonProperty("businessDivision")
    public String getBusinessDivision() {
        return businessDivision;
    }

    @JsonProperty("businessDivision")
    public void setBusinessDivision(String businessDivision) {
        this.businessDivision = businessDivision;
    }

    @JsonProperty("mmid")
    public String getMmid() {
        return mmid;
    }

    @JsonProperty("mmid")
    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    @JsonProperty("isAcceptedPrivacyPolicy")
    public Boolean getIsAcceptedPrivacyPolicy() {
        return isAcceptedPrivacyPolicy;
    }

    @JsonProperty("isAcceptedPrivacyPolicy")
    public void setIsAcceptedPrivacyPolicy(Boolean isAcceptedPrivacyPolicy) {
        this.isAcceptedPrivacyPolicy = isAcceptedPrivacyPolicy;
    }

    @JsonProperty("isAcceptedTermsConditions")
    public Boolean getIsAcceptedTermsConditions() {
        return isAcceptedTermsConditions;
    }

    @JsonProperty("isAcceptedTermsConditions")
    public void setIsAcceptedTermsConditions(Boolean isAcceptedTermsConditions) {
        this.isAcceptedTermsConditions = isAcceptedTermsConditions;
    }

    @JsonProperty("isAcceptedWhatsappConsent")
    public Boolean getIsAcceptedWhatsappConsent() {
        return isAcceptedWhatsappConsent;
    }

    @JsonProperty("isAcceptedWhatsappConsent")
    public void setIsAcceptedWhatsappConsent(Boolean isAcceptedWhatsappConsent) {
        this.isAcceptedWhatsappConsent = isAcceptedWhatsappConsent;
    }

    @JsonProperty("acceptedTermsConditionsTimeStamp")
    public String getAcceptedTermsConditionsTimeStamp() {
        return acceptedTermsConditionsTimeStamp;
    }

    @JsonProperty("acceptedTermsConditionsTimeStamp")
    public void setAcceptedTermsConditionsTimeStamp(String acceptedTermsConditionsTimeStamp) {
        this.acceptedTermsConditionsTimeStamp = acceptedTermsConditionsTimeStamp;
    }

    @JsonProperty("acceptedPrivacyPolicyTimeStamp")
    public String getAcceptedPrivacyPolicyTimeStamp() {
        return acceptedPrivacyPolicyTimeStamp;
    }

    @JsonProperty("acceptedPrivacyPolicyTimeStamp")
    public void setAcceptedPrivacyPolicyTimeStamp(String acceptedPrivacyPolicyTimeStamp) {
        this.acceptedPrivacyPolicyTimeStamp = acceptedPrivacyPolicyTimeStamp;
    }

    @JsonProperty("acceptedWhatsappConsentTimeStamp")
    public String getAcceptedWhatsappConsentTimeStamp() {
        return acceptedWhatsappConsentTimeStamp;
    }

    @JsonProperty("acceptedWhatsappConsentTimeStamp")
    public void setAcceptedWhatsappConsentTimeStamp(String acceptedWhatsappConsentTimeStamp) {
        this.acceptedWhatsappConsentTimeStamp = acceptedWhatsappConsentTimeStamp;
    }

    @JsonProperty("ctId")
    public String getCtId() {
        return ctId;
    }

    @JsonProperty("ctId")
    public void setCtId(String ctId) {
        this.ctId = ctId;
    }

    @JsonProperty("sfId")
    public String getSfId() {
        return sfId;
    }

    @JsonProperty("sfId")
    public void setSfId(String sfId) {
        this.sfId = sfId;
    }

    @JsonProperty("isCreditUser")
    public Boolean getIsCreditUser() {
        return isCreditUser;
    }

    @JsonProperty("isCreditUser")
    public void setIsCreditUser(Boolean isCreditUser) {
        this.isCreditUser = isCreditUser;
    }

    @JsonProperty("gstin")
    public String getGstin() {
        return gstin;
    }

    @JsonProperty("gstin")
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @JsonProperty("panCardNumber")
    public String getPanCardNumber() {
        return panCardNumber;
    }

    @JsonProperty("panCardNumber")
    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    @JsonProperty("permissions")
    public Permissions getPermissions() {
        return permissions;
    }

    @JsonProperty("permissions")
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    @JsonProperty("modules")
    public List<String> getModules() {
        return modules;
    }

    @JsonProperty("modules")
    public void setModules(List<String> modules) {
        this.modules = modules;
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
        sb.append(AppResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null)?"<null>":this.location));
        sb.append(',');
        sb.append("businessName");
        sb.append('=');
        sb.append(((this.businessName == null)?"<null>":this.businessName));
        sb.append(',');
        sb.append("businessContactNumber");
        sb.append('=');
        sb.append(((this.businessContactNumber == null)?"<null>":this.businessContactNumber));
        sb.append(',');
        sb.append("ownerName");
        sb.append('=');
        sb.append(((this.ownerName == null)?"<null>":this.ownerName));
        sb.append(',');
        sb.append("ownerContactNumber");
        sb.append('=');
        sb.append(((this.ownerContactNumber == null)?"<null>":this.ownerContactNumber));
        sb.append(',');
        sb.append("jswPreferred");
        sb.append('=');
        sb.append(((this.jswPreferred == null)?"<null>":this.jswPreferred));
        sb.append(',');
        sb.append("addressLine1");
        sb.append('=');
        sb.append(((this.addressLine1 == null)?"<null>":this.addressLine1));
        sb.append(',');
        sb.append("addressLine2");
        sb.append('=');
        sb.append(((this.addressLine2 == null)?"<null>":this.addressLine2));
        sb.append(',');
        sb.append("pincode");
        sb.append('=');
        sb.append(((this.pincode == null)?"<null>":this.pincode));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("district");
        sb.append('=');
        sb.append(((this.district == null)?"<null>":this.district));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("averageRating");
        sb.append('=');
        sb.append(((this.averageRating == null)?"<null>":this.averageRating));
        sb.append(',');
        sb.append("ownerEmail");
        sb.append('=');
        sb.append(((this.ownerEmail == null)?"<null>":this.ownerEmail));
        sb.append(',');
        sb.append("accountType");
        sb.append('=');
        sb.append(((this.accountType == null)?"<null>":this.accountType));
        sb.append(',');
        sb.append("selectedAddressId");
        sb.append('=');
        sb.append(((this.selectedAddressId == null)?"<null>":this.selectedAddressId));
        sb.append(',');
        sb.append("accountRecordType");
        sb.append('=');
        sb.append(((this.accountRecordType == null)?"<null>":this.accountRecordType));
        sb.append(',');
        sb.append("customerType");
        sb.append('=');
        sb.append(((this.customerType == null)?"<null>":this.customerType));
        sb.append(',');
        sb.append("businessDivision");
        sb.append('=');
        sb.append(((this.businessDivision == null)?"<null>":this.businessDivision));
        sb.append(',');
        sb.append("mmid");
        sb.append('=');
        sb.append(((this.mmid == null)?"<null>":this.mmid));
        sb.append(',');
        sb.append("isAcceptedPrivacyPolicy");
        sb.append('=');
        sb.append(((this.isAcceptedPrivacyPolicy == null)?"<null>":this.isAcceptedPrivacyPolicy));
        sb.append(',');
        sb.append("isAcceptedTermsConditions");
        sb.append('=');
        sb.append(((this.isAcceptedTermsConditions == null)?"<null>":this.isAcceptedTermsConditions));
        sb.append(',');
        sb.append("isAcceptedWhatsappConsent");
        sb.append('=');
        sb.append(((this.isAcceptedWhatsappConsent == null)?"<null>":this.isAcceptedWhatsappConsent));
        sb.append(',');
        sb.append("acceptedTermsConditionsTimeStamp");
        sb.append('=');
        sb.append(((this.acceptedTermsConditionsTimeStamp == null)?"<null>":this.acceptedTermsConditionsTimeStamp));
        sb.append(',');
        sb.append("acceptedPrivacyPolicyTimeStamp");
        sb.append('=');
        sb.append(((this.acceptedPrivacyPolicyTimeStamp == null)?"<null>":this.acceptedPrivacyPolicyTimeStamp));
        sb.append(',');
        sb.append("acceptedWhatsappConsentTimeStamp");
        sb.append('=');
        sb.append(((this.acceptedWhatsappConsentTimeStamp == null)?"<null>":this.acceptedWhatsappConsentTimeStamp));
        sb.append(',');
        sb.append("ctId");
        sb.append('=');
        sb.append(((this.ctId == null)?"<null>":this.ctId));
        sb.append(',');
        sb.append("sfId");
        sb.append('=');
        sb.append(((this.sfId == null)?"<null>":this.sfId));
        sb.append(',');
        sb.append("isCreditUser");
        sb.append('=');
        sb.append(((this.isCreditUser == null)?"<null>":this.isCreditUser));
        sb.append(',');
        sb.append("gstin");
        sb.append('=');
        sb.append(((this.gstin == null)?"<null>":this.gstin));
        sb.append(',');
        sb.append("panCardNumber");
        sb.append('=');
        sb.append(((this.panCardNumber == null)?"<null>":this.panCardNumber));
        sb.append(',');
        sb.append("permissions");
        sb.append('=');
        sb.append(((this.permissions == null)?"<null>":this.permissions));
        sb.append(',');
        sb.append("modules");
        sb.append('=');
        sb.append(((this.modules == null)?"<null>":this.modules));
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
        result = ((result* 31)+((this.selectedAddressId == null)? 0 :this.selectedAddressId.hashCode()));
        result = ((result* 31)+((this.ctId == null)? 0 :this.ctId.hashCode()));
        result = ((result* 31)+((this.city == null)? 0 :this.city.hashCode()));
        result = ((result* 31)+((this.businessName == null)? 0 :this.businessName.hashCode()));
        result = ((result* 31)+((this.mmid == null)? 0 :this.mmid.hashCode()));
        result = ((result* 31)+((this.gstin == null)? 0 :this.gstin.hashCode()));
        result = ((result* 31)+((this.isAcceptedWhatsappConsent == null)? 0 :this.isAcceptedWhatsappConsent.hashCode()));
        result = ((result* 31)+((this.acceptedPrivacyPolicyTimeStamp == null)? 0 :this.acceptedPrivacyPolicyTimeStamp.hashCode()));
        result = ((result* 31)+((this.customerType == null)? 0 :this.customerType.hashCode()));
        result = ((result* 31)+((this.ownerName == null)? 0 :this.ownerName.hashCode()));
        result = ((result* 31)+((this.permissions == null)? 0 :this.permissions.hashCode()));
        result = ((result* 31)+((this.averageRating == null)? 0 :this.averageRating.hashCode()));
        result = ((result* 31)+((this.addressLine1 == null)? 0 :this.addressLine1 .hashCode()));
        result = ((result* 31)+((this.addressLine2 == null)? 0 :this.addressLine2 .hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.pincode == null)? 0 :this.pincode.hashCode()));
        result = ((result* 31)+((this.isCreditUser == null)? 0 :this.isCreditUser.hashCode()));
        result = ((result* 31)+((this.accountType == null)? 0 :this.accountType.hashCode()));
        result = ((result* 31)+((this.isAcceptedTermsConditions == null)? 0 :this.isAcceptedTermsConditions.hashCode()));
        result = ((result* 31)+((this.isAcceptedPrivacyPolicy == null)? 0 :this.isAcceptedPrivacyPolicy.hashCode()));
        result = ((result* 31)+((this.jswPreferred == null)? 0 :this.jswPreferred.hashCode()));
        result = ((result* 31)+((this.businessDivision == null)? 0 :this.businessDivision.hashCode()));
        result = ((result* 31)+((this.modules == null)? 0 :this.modules.hashCode()));
        result = ((result* 31)+((this.ownerEmail == null)? 0 :this.ownerEmail.hashCode()));
        result = ((result* 31)+((this.acceptedWhatsappConsentTimeStamp == null)? 0 :this.acceptedWhatsappConsentTimeStamp.hashCode()));
        result = ((result* 31)+((this.panCardNumber == null)? 0 :this.panCardNumber.hashCode()));
        result = ((result* 31)+((this.district == null)? 0 :this.district.hashCode()));
        result = ((result* 31)+((this.businessContactNumber == null)? 0 :this.businessContactNumber.hashCode()));
        result = ((result* 31)+((this.acceptedTermsConditionsTimeStamp == null)? 0 :this.acceptedTermsConditionsTimeStamp.hashCode()));
        result = ((result* 31)+((this.location == null)? 0 :this.location.hashCode()));
        result = ((result* 31)+((this.accountRecordType == null)? 0 :this.accountRecordType.hashCode()));
        result = ((result* 31)+((this.sfId == null)? 0 :this.sfId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.ownerContactNumber == null)? 0 :this.ownerContactNumber.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AppResponse) == false) {
            return false;
        }
        AppResponse rhs = ((AppResponse) other);
        return ((((((((((((((((((((((((((((((((((((this.selectedAddressId == rhs.selectedAddressId)||((this.selectedAddressId!= null)&&this.selectedAddressId.equals(rhs.selectedAddressId)))&&((this.ctId == rhs.ctId)||((this.ctId!= null)&&this.ctId.equals(rhs.ctId))))&&((this.city == rhs.city)||((this.city!= null)&&this.city.equals(rhs.city))))&&((this.businessName == rhs.businessName)||((this.businessName!= null)&&this.businessName.equals(rhs.businessName))))&&((this.mmid == rhs.mmid)||((this.mmid!= null)&&this.mmid.equals(rhs.mmid))))&&((this.gstin == rhs.gstin)||((this.gstin!= null)&&this.gstin.equals(rhs.gstin))))&&((this.isAcceptedWhatsappConsent == rhs.isAcceptedWhatsappConsent)||((this.isAcceptedWhatsappConsent!= null)&&this.isAcceptedWhatsappConsent.equals(rhs.isAcceptedWhatsappConsent))))&&((this.acceptedPrivacyPolicyTimeStamp == rhs.acceptedPrivacyPolicyTimeStamp)||((this.acceptedPrivacyPolicyTimeStamp!= null)&&this.acceptedPrivacyPolicyTimeStamp.equals(rhs.acceptedPrivacyPolicyTimeStamp))))&&((this.customerType == rhs.customerType)||((this.customerType!= null)&&this.customerType.equals(rhs.customerType))))&&((this.ownerName == rhs.ownerName)||((this.ownerName!= null)&&this.ownerName.equals(rhs.ownerName))))&&((this.permissions == rhs.permissions)||((this.permissions!= null)&&this.permissions.equals(rhs.permissions))))&&((this.averageRating == rhs.averageRating)||((this.averageRating!= null)&&this.averageRating.equals(rhs.averageRating))))&&((this.addressLine1 == rhs.addressLine1)||((this.addressLine1 != null)&&this.addressLine1 .equals(rhs.addressLine1))))&&((this.addressLine2 == rhs.addressLine2)||((this.addressLine2 != null)&&this.addressLine2 .equals(rhs.addressLine2))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.pincode == rhs.pincode)||((this.pincode!= null)&&this.pincode.equals(rhs.pincode))))&&((this.isCreditUser == rhs.isCreditUser)||((this.isCreditUser!= null)&&this.isCreditUser.equals(rhs.isCreditUser))))&&((this.accountType == rhs.accountType)||((this.accountType!= null)&&this.accountType.equals(rhs.accountType))))&&((this.isAcceptedTermsConditions == rhs.isAcceptedTermsConditions)||((this.isAcceptedTermsConditions!= null)&&this.isAcceptedTermsConditions.equals(rhs.isAcceptedTermsConditions))))&&((this.isAcceptedPrivacyPolicy == rhs.isAcceptedPrivacyPolicy)||((this.isAcceptedPrivacyPolicy!= null)&&this.isAcceptedPrivacyPolicy.equals(rhs.isAcceptedPrivacyPolicy))))&&((this.jswPreferred == rhs.jswPreferred)||((this.jswPreferred!= null)&&this.jswPreferred.equals(rhs.jswPreferred))))&&((this.businessDivision == rhs.businessDivision)||((this.businessDivision!= null)&&this.businessDivision.equals(rhs.businessDivision))))&&((this.modules == rhs.modules)||((this.modules!= null)&&this.modules.equals(rhs.modules))))&&((this.ownerEmail == rhs.ownerEmail)||((this.ownerEmail!= null)&&this.ownerEmail.equals(rhs.ownerEmail))))&&((this.acceptedWhatsappConsentTimeStamp == rhs.acceptedWhatsappConsentTimeStamp)||((this.acceptedWhatsappConsentTimeStamp!= null)&&this.acceptedWhatsappConsentTimeStamp.equals(rhs.acceptedWhatsappConsentTimeStamp))))&&((this.panCardNumber == rhs.panCardNumber)||((this.panCardNumber!= null)&&this.panCardNumber.equals(rhs.panCardNumber))))&&((this.district == rhs.district)||((this.district!= null)&&this.district.equals(rhs.district))))&&((this.businessContactNumber == rhs.businessContactNumber)||((this.businessContactNumber!= null)&&this.businessContactNumber.equals(rhs.businessContactNumber))))&&((this.acceptedTermsConditionsTimeStamp == rhs.acceptedTermsConditionsTimeStamp)||((this.acceptedTermsConditionsTimeStamp!= null)&&this.acceptedTermsConditionsTimeStamp.equals(rhs.acceptedTermsConditionsTimeStamp))))&&((this.location == rhs.location)||((this.location!= null)&&this.location.equals(rhs.location))))&&((this.accountRecordType == rhs.accountRecordType)||((this.accountRecordType!= null)&&this.accountRecordType.equals(rhs.accountRecordType))))&&((this.sfId == rhs.sfId)||((this.sfId!= null)&&this.sfId.equals(rhs.sfId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.ownerContactNumber == rhs.ownerContactNumber)||((this.ownerContactNumber!= null)&&this.ownerContactNumber.equals(rhs.ownerContactNumber))));
    }

}
