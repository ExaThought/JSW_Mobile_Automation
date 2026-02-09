
package com.qapitol.jsw.apitest.pojos.order.customerDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "firstName",
    "userEmail",
    "phoneNumber",
    "lastName",
    "companyName",
    "razorPayName",
    "razorPayVaAccountNumber",
    "razorPayIFSC",
    "defaultShippingAddressId",
    "defaultBillingAddressId",
    "addresses",
    "salesClassification",
    "sfAccountId",
    "gstIn",
    "erpOrderCreationEnabled",
    "erpBackwardFlowEnabled",
    "erpEnableUsers",
    "zohoFlagEnabled",
    "zohoEnableUsers",
    "gtmDate",
    "creditFlag",
    "isDirectCustomer",
    "isEligibleSellerCredit",
    "isLedgerEnable",
    "allowLinkTDC",
    "isCentralCartSloEnabled",
    "isCentralCartStoEnabled",
    "isCentralCartRcEnabled"
})
@Generated("jsonschema2pojo")
public class CustomerDetailsResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("userEmail")
    private String userEmail;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("razorPayName")
    private String razorPayName;
    @JsonProperty("razorPayVaAccountNumber")
    private String razorPayVaAccountNumber;
    @JsonProperty("razorPayIFSC")
    private String razorPayIFSC;
    @JsonProperty("defaultShippingAddressId")
    private String defaultShippingAddressId;
    @JsonProperty("defaultBillingAddressId")
    private String defaultBillingAddressId;
    @JsonProperty("addresses")
    private List<Address> addresses = new ArrayList<Address>();
    @JsonProperty("salesClassification")
    private String salesClassification;
    @JsonProperty("sfAccountId")
    private String sfAccountId;
    @JsonProperty("gstIn")
    private String gstIn;
    @JsonProperty("erpOrderCreationEnabled")
    private Boolean erpOrderCreationEnabled;
    @JsonProperty("erpBackwardFlowEnabled")
    private Boolean erpBackwardFlowEnabled;
    @JsonProperty("erpEnableUsers")
    private List<Object> erpEnableUsers = new ArrayList<Object>();
    @JsonProperty("zohoFlagEnabled")
    private Boolean zohoFlagEnabled;
    @JsonProperty("zohoEnableUsers")
    private List<Object> zohoEnableUsers = new ArrayList<Object>();
    @JsonProperty("gtmDate")
    private String gtmDate;
    @JsonProperty("creditFlag")
    private Boolean creditFlag;
    @JsonProperty("isDirectCustomer")
    private Boolean isDirectCustomer;
    @JsonProperty("isEligibleSellerCredit")
    private Boolean isEligibleSellerCredit;
    @JsonProperty("isLedgerEnable")
    private Boolean isLedgerEnable;
    @JsonProperty("allowLinkTDC")
    private Boolean allowLinkTDC;
    @JsonProperty("isCentralCartSloEnabled")
    private Boolean isCentralCartSloEnabled;
    @JsonProperty("isCentralCartStoEnabled")
    private Boolean isCentralCartStoEnabled;
    @JsonProperty("isCentralCartRcEnabled")
    private Boolean isCentralCartRcEnabled;
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

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("userEmail")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty("userEmail")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("razorPayName")
    public String getRazorPayName() {
        return razorPayName;
    }

    @JsonProperty("razorPayName")
    public void setRazorPayName(String razorPayName) {
        this.razorPayName = razorPayName;
    }

    @JsonProperty("razorPayVaAccountNumber")
    public String getRazorPayVaAccountNumber() {
        return razorPayVaAccountNumber;
    }

    @JsonProperty("razorPayVaAccountNumber")
    public void setRazorPayVaAccountNumber(String razorPayVaAccountNumber) {
        this.razorPayVaAccountNumber = razorPayVaAccountNumber;
    }

    @JsonProperty("razorPayIFSC")
    public String getRazorPayIFSC() {
        return razorPayIFSC;
    }

    @JsonProperty("razorPayIFSC")
    public void setRazorPayIFSC(String razorPayIFSC) {
        this.razorPayIFSC = razorPayIFSC;
    }

    @JsonProperty("defaultShippingAddressId")
    public String getDefaultShippingAddressId() {
        return defaultShippingAddressId;
    }

    @JsonProperty("defaultShippingAddressId")
    public void setDefaultShippingAddressId(String defaultShippingAddressId) {
        this.defaultShippingAddressId = defaultShippingAddressId;
    }

    @JsonProperty("defaultBillingAddressId")
    public String getDefaultBillingAddressId() {
        return defaultBillingAddressId;
    }

    @JsonProperty("defaultBillingAddressId")
    public void setDefaultBillingAddressId(String defaultBillingAddressId) {
        this.defaultBillingAddressId = defaultBillingAddressId;
    }

    @JsonProperty("addresses")
    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonProperty("addresses")
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonProperty("salesClassification")
    public String getSalesClassification() {
        return salesClassification;
    }

    @JsonProperty("salesClassification")
    public void setSalesClassification(String salesClassification) {
        this.salesClassification = salesClassification;
    }

    @JsonProperty("sfAccountId")
    public String getSfAccountId() {
        return sfAccountId;
    }

    @JsonProperty("sfAccountId")
    public void setSfAccountId(String sfAccountId) {
        this.sfAccountId = sfAccountId;
    }

    @JsonProperty("gstIn")
    public String getGstIn() {
        return gstIn;
    }

    @JsonProperty("gstIn")
    public void setGstIn(String gstIn) {
        this.gstIn = gstIn;
    }

    @JsonProperty("erpOrderCreationEnabled")
    public Boolean getErpOrderCreationEnabled() {
        return erpOrderCreationEnabled;
    }

    @JsonProperty("erpOrderCreationEnabled")
    public void setErpOrderCreationEnabled(Boolean erpOrderCreationEnabled) {
        this.erpOrderCreationEnabled = erpOrderCreationEnabled;
    }

    @JsonProperty("erpBackwardFlowEnabled")
    public Boolean getErpBackwardFlowEnabled() {
        return erpBackwardFlowEnabled;
    }

    @JsonProperty("erpBackwardFlowEnabled")
    public void setErpBackwardFlowEnabled(Boolean erpBackwardFlowEnabled) {
        this.erpBackwardFlowEnabled = erpBackwardFlowEnabled;
    }

    @JsonProperty("erpEnableUsers")
    public List<Object> getErpEnableUsers() {
        return erpEnableUsers;
    }

    @JsonProperty("erpEnableUsers")
    public void setErpEnableUsers(List<Object> erpEnableUsers) {
        this.erpEnableUsers = erpEnableUsers;
    }

    @JsonProperty("zohoFlagEnabled")
    public Boolean getZohoFlagEnabled() {
        return zohoFlagEnabled;
    }

    @JsonProperty("zohoFlagEnabled")
    public void setZohoFlagEnabled(Boolean zohoFlagEnabled) {
        this.zohoFlagEnabled = zohoFlagEnabled;
    }

    @JsonProperty("zohoEnableUsers")
    public List<Object> getZohoEnableUsers() {
        return zohoEnableUsers;
    }

    @JsonProperty("zohoEnableUsers")
    public void setZohoEnableUsers(List<Object> zohoEnableUsers) {
        this.zohoEnableUsers = zohoEnableUsers;
    }

    @JsonProperty("gtmDate")
    public String getGtmDate() {
        return gtmDate;
    }

    @JsonProperty("gtmDate")
    public void setGtmDate(String gtmDate) {
        this.gtmDate = gtmDate;
    }

    @JsonProperty("creditFlag")
    public Boolean getCreditFlag() {
        return creditFlag;
    }

    @JsonProperty("creditFlag")
    public void setCreditFlag(Boolean creditFlag) {
        this.creditFlag = creditFlag;
    }

    @JsonProperty("isDirectCustomer")
    public Boolean getIsDirectCustomer() {
        return isDirectCustomer;
    }

    @JsonProperty("isDirectCustomer")
    public void setIsDirectCustomer(Boolean isDirectCustomer) {
        this.isDirectCustomer = isDirectCustomer;
    }

    @JsonProperty("isEligibleSellerCredit")
    public Boolean getIsEligibleSellerCredit() {
        return isEligibleSellerCredit;
    }

    @JsonProperty("isEligibleSellerCredit")
    public void setIsEligibleSellerCredit(Boolean isEligibleSellerCredit) {
        this.isEligibleSellerCredit = isEligibleSellerCredit;
    }

    @JsonProperty("isLedgerEnable")
    public Boolean getIsLedgerEnable() {
        return isLedgerEnable;
    }

    @JsonProperty("isLedgerEnable")
    public void setIsLedgerEnable(Boolean isLedgerEnable) {
        this.isLedgerEnable = isLedgerEnable;
    }

    @JsonProperty("allowLinkTDC")
    public Boolean getAllowLinkTDC() {
        return allowLinkTDC;
    }

    @JsonProperty("allowLinkTDC")
    public void setAllowLinkTDC(Boolean allowLinkTDC) {
        this.allowLinkTDC = allowLinkTDC;
    }

    @JsonProperty("isCentralCartSloEnabled")
    public Boolean getIsCentralCartSloEnabled() {
        return isCentralCartSloEnabled;
    }

    @JsonProperty("isCentralCartSloEnabled")
    public void setIsCentralCartSloEnabled(Boolean isCentralCartSloEnabled) {
        this.isCentralCartSloEnabled = isCentralCartSloEnabled;
    }

    @JsonProperty("isCentralCartStoEnabled")
    public Boolean getIsCentralCartStoEnabled() {
        return isCentralCartStoEnabled;
    }

    @JsonProperty("isCentralCartStoEnabled")
    public void setIsCentralCartStoEnabled(Boolean isCentralCartStoEnabled) {
        this.isCentralCartStoEnabled = isCentralCartStoEnabled;
    }

    @JsonProperty("isCentralCartRcEnabled")
    public Boolean getIsCentralCartRcEnabled() {
        return isCentralCartRcEnabled;
    }

    @JsonProperty("isCentralCartRcEnabled")
    public void setIsCentralCartRcEnabled(Boolean isCentralCartRcEnabled) {
        this.isCentralCartRcEnabled = isCentralCartRcEnabled;
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
        sb.append(CustomerDetailsResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("userEmail");
        sb.append('=');
        sb.append(((this.userEmail == null)?"<null>":this.userEmail));
        sb.append(',');
        sb.append("phoneNumber");
        sb.append('=');
        sb.append(((this.phoneNumber == null)?"<null>":this.phoneNumber));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("companyName");
        sb.append('=');
        sb.append(((this.companyName == null)?"<null>":this.companyName));
        sb.append(',');
        sb.append("razorPayName");
        sb.append('=');
        sb.append(((this.razorPayName == null)?"<null>":this.razorPayName));
        sb.append(',');
        sb.append("razorPayVaAccountNumber");
        sb.append('=');
        sb.append(((this.razorPayVaAccountNumber == null)?"<null>":this.razorPayVaAccountNumber));
        sb.append(',');
        sb.append("razorPayIFSC");
        sb.append('=');
        sb.append(((this.razorPayIFSC == null)?"<null>":this.razorPayIFSC));
        sb.append(',');
        sb.append("defaultShippingAddressId");
        sb.append('=');
        sb.append(((this.defaultShippingAddressId == null)?"<null>":this.defaultShippingAddressId));
        sb.append(',');
        sb.append("defaultBillingAddressId");
        sb.append('=');
        sb.append(((this.defaultBillingAddressId == null)?"<null>":this.defaultBillingAddressId));
        sb.append(',');
        sb.append("addresses");
        sb.append('=');
        sb.append(((this.addresses == null)?"<null>":this.addresses));
        sb.append(',');
        sb.append("salesClassification");
        sb.append('=');
        sb.append(((this.salesClassification == null)?"<null>":this.salesClassification));
        sb.append(',');
        sb.append("sfAccountId");
        sb.append('=');
        sb.append(((this.sfAccountId == null)?"<null>":this.sfAccountId));
        sb.append(',');
        sb.append("gstIn");
        sb.append('=');
        sb.append(((this.gstIn == null)?"<null>":this.gstIn));
        sb.append(',');
        sb.append("erpOrderCreationEnabled");
        sb.append('=');
        sb.append(((this.erpOrderCreationEnabled == null)?"<null>":this.erpOrderCreationEnabled));
        sb.append(',');
        sb.append("erpBackwardFlowEnabled");
        sb.append('=');
        sb.append(((this.erpBackwardFlowEnabled == null)?"<null>":this.erpBackwardFlowEnabled));
        sb.append(',');
        sb.append("erpEnableUsers");
        sb.append('=');
        sb.append(((this.erpEnableUsers == null)?"<null>":this.erpEnableUsers));
        sb.append(',');
        sb.append("zohoFlagEnabled");
        sb.append('=');
        sb.append(((this.zohoFlagEnabled == null)?"<null>":this.zohoFlagEnabled));
        sb.append(',');
        sb.append("zohoEnableUsers");
        sb.append('=');
        sb.append(((this.zohoEnableUsers == null)?"<null>":this.zohoEnableUsers));
        sb.append(',');
        sb.append("gtmDate");
        sb.append('=');
        sb.append(((this.gtmDate == null)?"<null>":this.gtmDate));
        sb.append(',');
        sb.append("creditFlag");
        sb.append('=');
        sb.append(((this.creditFlag == null)?"<null>":this.creditFlag));
        sb.append(',');
        sb.append("isDirectCustomer");
        sb.append('=');
        sb.append(((this.isDirectCustomer == null)?"<null>":this.isDirectCustomer));
        sb.append(',');
        sb.append("isEligibleSellerCredit");
        sb.append('=');
        sb.append(((this.isEligibleSellerCredit == null)?"<null>":this.isEligibleSellerCredit));
        sb.append(',');
        sb.append("isLedgerEnable");
        sb.append('=');
        sb.append(((this.isLedgerEnable == null)?"<null>":this.isLedgerEnable));
        sb.append(',');
        sb.append("allowLinkTDC");
        sb.append('=');
        sb.append(((this.allowLinkTDC == null)?"<null>":this.allowLinkTDC));
        sb.append(',');
        sb.append("isCentralCartSloEnabled");
        sb.append('=');
        sb.append(((this.isCentralCartSloEnabled == null)?"<null>":this.isCentralCartSloEnabled));
        sb.append(',');
        sb.append("isCentralCartStoEnabled");
        sb.append('=');
        sb.append(((this.isCentralCartStoEnabled == null)?"<null>":this.isCentralCartStoEnabled));
        sb.append(',');
        sb.append("isCentralCartRcEnabled");
        sb.append('=');
        sb.append(((this.isCentralCartRcEnabled == null)?"<null>":this.isCentralCartRcEnabled));
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
        result = ((result* 31)+((this.addresses == null)? 0 :this.addresses.hashCode()));
        result = ((result* 31)+((this.companyName == null)? 0 :this.companyName.hashCode()));
        result = ((result* 31)+((this.zohoEnableUsers == null)? 0 :this.zohoEnableUsers.hashCode()));
        result = ((result* 31)+((this.zohoFlagEnabled == null)? 0 :this.zohoFlagEnabled.hashCode()));
        result = ((result* 31)+((this.isLedgerEnable == null)? 0 :this.isLedgerEnable.hashCode()));
        result = ((result* 31)+((this.erpOrderCreationEnabled == null)? 0 :this.erpOrderCreationEnabled.hashCode()));
        result = ((result* 31)+((this.isCentralCartRcEnabled == null)? 0 :this.isCentralCartRcEnabled.hashCode()));
        result = ((result* 31)+((this.creditFlag == null)? 0 :this.creditFlag.hashCode()));
        result = ((result* 31)+((this.razorPayName == null)? 0 :this.razorPayName.hashCode()));
        result = ((result* 31)+((this.erpBackwardFlowEnabled == null)? 0 :this.erpBackwardFlowEnabled.hashCode()));
        result = ((result* 31)+((this.userEmail == null)? 0 :this.userEmail.hashCode()));
        result = ((result* 31)+((this.gtmDate == null)? 0 :this.gtmDate.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.razorPayIFSC == null)? 0 :this.razorPayIFSC.hashCode()));
        result = ((result* 31)+((this.sfAccountId == null)? 0 :this.sfAccountId.hashCode()));
        result = ((result* 31)+((this.razorPayVaAccountNumber == null)? 0 :this.razorPayVaAccountNumber.hashCode()));
        result = ((result* 31)+((this.erpEnableUsers == null)? 0 :this.erpEnableUsers.hashCode()));
        result = ((result* 31)+((this.isCentralCartSloEnabled == null)? 0 :this.isCentralCartSloEnabled.hashCode()));
        result = ((result* 31)+((this.defaultShippingAddressId == null)? 0 :this.defaultShippingAddressId.hashCode()));
        result = ((result* 31)+((this.gstIn == null)? 0 :this.gstIn.hashCode()));
        result = ((result* 31)+((this.defaultBillingAddressId == null)? 0 :this.defaultBillingAddressId.hashCode()));
        result = ((result* 31)+((this.firstName == null)? 0 :this.firstName.hashCode()));
        result = ((result* 31)+((this.isEligibleSellerCredit == null)? 0 :this.isEligibleSellerCredit.hashCode()));
        result = ((result* 31)+((this.phoneNumber == null)? 0 :this.phoneNumber.hashCode()));
        result = ((result* 31)+((this.allowLinkTDC == null)? 0 :this.allowLinkTDC.hashCode()));
        result = ((result* 31)+((this.isDirectCustomer == null)? 0 :this.isDirectCustomer.hashCode()));
        result = ((result* 31)+((this.salesClassification == null)? 0 :this.salesClassification.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.isCentralCartStoEnabled == null)? 0 :this.isCentralCartStoEnabled.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CustomerDetailsResponse) == false) {
            return false;
        }
        CustomerDetailsResponse rhs = ((CustomerDetailsResponse) other);
        return (((((((((((((((((((((((((((((((this.lastName == rhs.lastName)||((this.lastName!= null)&&this.lastName.equals(rhs.lastName)))&&((this.addresses == rhs.addresses)||((this.addresses!= null)&&this.addresses.equals(rhs.addresses))))&&((this.companyName == rhs.companyName)||((this.companyName!= null)&&this.companyName.equals(rhs.companyName))))&&((this.zohoEnableUsers == rhs.zohoEnableUsers)||((this.zohoEnableUsers!= null)&&this.zohoEnableUsers.equals(rhs.zohoEnableUsers))))&&((this.zohoFlagEnabled == rhs.zohoFlagEnabled)||((this.zohoFlagEnabled!= null)&&this.zohoFlagEnabled.equals(rhs.zohoFlagEnabled))))&&((this.isLedgerEnable == rhs.isLedgerEnable)||((this.isLedgerEnable!= null)&&this.isLedgerEnable.equals(rhs.isLedgerEnable))))&&((this.erpOrderCreationEnabled == rhs.erpOrderCreationEnabled)||((this.erpOrderCreationEnabled!= null)&&this.erpOrderCreationEnabled.equals(rhs.erpOrderCreationEnabled))))&&((this.isCentralCartRcEnabled == rhs.isCentralCartRcEnabled)||((this.isCentralCartRcEnabled!= null)&&this.isCentralCartRcEnabled.equals(rhs.isCentralCartRcEnabled))))&&((this.creditFlag == rhs.creditFlag)||((this.creditFlag!= null)&&this.creditFlag.equals(rhs.creditFlag))))&&((this.razorPayName == rhs.razorPayName)||((this.razorPayName!= null)&&this.razorPayName.equals(rhs.razorPayName))))&&((this.erpBackwardFlowEnabled == rhs.erpBackwardFlowEnabled)||((this.erpBackwardFlowEnabled!= null)&&this.erpBackwardFlowEnabled.equals(rhs.erpBackwardFlowEnabled))))&&((this.userEmail == rhs.userEmail)||((this.userEmail!= null)&&this.userEmail.equals(rhs.userEmail))))&&((this.gtmDate == rhs.gtmDate)||((this.gtmDate!= null)&&this.gtmDate.equals(rhs.gtmDate))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.razorPayIFSC == rhs.razorPayIFSC)||((this.razorPayIFSC!= null)&&this.razorPayIFSC.equals(rhs.razorPayIFSC))))&&((this.sfAccountId == rhs.sfAccountId)||((this.sfAccountId!= null)&&this.sfAccountId.equals(rhs.sfAccountId))))&&((this.razorPayVaAccountNumber == rhs.razorPayVaAccountNumber)||((this.razorPayVaAccountNumber!= null)&&this.razorPayVaAccountNumber.equals(rhs.razorPayVaAccountNumber))))&&((this.erpEnableUsers == rhs.erpEnableUsers)||((this.erpEnableUsers!= null)&&this.erpEnableUsers.equals(rhs.erpEnableUsers))))&&((this.isCentralCartSloEnabled == rhs.isCentralCartSloEnabled)||((this.isCentralCartSloEnabled!= null)&&this.isCentralCartSloEnabled.equals(rhs.isCentralCartSloEnabled))))&&((this.defaultShippingAddressId == rhs.defaultShippingAddressId)||((this.defaultShippingAddressId!= null)&&this.defaultShippingAddressId.equals(rhs.defaultShippingAddressId))))&&((this.gstIn == rhs.gstIn)||((this.gstIn!= null)&&this.gstIn.equals(rhs.gstIn))))&&((this.defaultBillingAddressId == rhs.defaultBillingAddressId)||((this.defaultBillingAddressId!= null)&&this.defaultBillingAddressId.equals(rhs.defaultBillingAddressId))))&&((this.firstName == rhs.firstName)||((this.firstName!= null)&&this.firstName.equals(rhs.firstName))))&&((this.isEligibleSellerCredit == rhs.isEligibleSellerCredit)||((this.isEligibleSellerCredit!= null)&&this.isEligibleSellerCredit.equals(rhs.isEligibleSellerCredit))))&&((this.phoneNumber == rhs.phoneNumber)||((this.phoneNumber!= null)&&this.phoneNumber.equals(rhs.phoneNumber))))&&((this.allowLinkTDC == rhs.allowLinkTDC)||((this.allowLinkTDC!= null)&&this.allowLinkTDC.equals(rhs.allowLinkTDC))))&&((this.isDirectCustomer == rhs.isDirectCustomer)||((this.isDirectCustomer!= null)&&this.isDirectCustomer.equals(rhs.isDirectCustomer))))&&((this.salesClassification == rhs.salesClassification)||((this.salesClassification!= null)&&this.salesClassification.equals(rhs.salesClassification))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.isCentralCartStoEnabled == rhs.isCentralCartStoEnabled)||((this.isCentralCartStoEnabled!= null)&&this.isCentralCartStoEnabled.equals(rhs.isCentralCartStoEnabled))));
    }

}
