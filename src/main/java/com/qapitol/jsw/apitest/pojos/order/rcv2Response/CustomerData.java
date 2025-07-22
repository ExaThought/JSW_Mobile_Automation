
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customerId",
    "name",
    "email",
    "phoneNumber",
    "companyName",
    "gstIn",
    "beneficiaryName",
    "beneficiaryAccountNum",
    "ifscCode",
    "customerVAN",
    "virtualAccountId",
    "salesClassification",
    "bankName",
    "testUser",
    "userEmail",
    "opportunityOwnerEmail",
    "razorPayBankName",
    "sfAccountId",
    "customerPan",
    "customerAccountType",
    "businessDivision",
    "isPBDistributor"
})
@Generated("jsonschema2pojo")
public class CustomerData {

    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("gstIn")
    private String gstIn;
    @JsonProperty("beneficiaryName")
    private String beneficiaryName;
    @JsonProperty("beneficiaryAccountNum")
    private String beneficiaryAccountNum;
    @JsonProperty("ifscCode")
    private String ifscCode;
    @JsonProperty("customerVAN")
    private String customerVAN;
    @JsonProperty("virtualAccountId")
    private String virtualAccountId;
    @JsonProperty("salesClassification")
    private String salesClassification;
    @JsonProperty("bankName")
    private Object bankName;
    @JsonProperty("testUser")
    private Boolean testUser;
    @JsonProperty("userEmail")
    private String userEmail;
    @JsonProperty("opportunityOwnerEmail")
    private Object opportunityOwnerEmail;
    @JsonProperty("razorPayBankName")
    private String razorPayBankName;
    @JsonProperty("sfAccountId")
    private String sfAccountId;
    @JsonProperty("customerPan")
    private String customerPan;
    @JsonProperty("customerAccountType")
    private String customerAccountType;
    @JsonProperty("businessDivision")
    private String businessDivision;
    @JsonProperty("isPBDistributor")
    private Boolean isPBDistributor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("gstIn")
    public String getGstIn() {
        return gstIn;
    }

    @JsonProperty("gstIn")
    public void setGstIn(String gstIn) {
        this.gstIn = gstIn;
    }

    @JsonProperty("beneficiaryName")
    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    @JsonProperty("beneficiaryName")
    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    @JsonProperty("beneficiaryAccountNum")
    public String getBeneficiaryAccountNum() {
        return beneficiaryAccountNum;
    }

    @JsonProperty("beneficiaryAccountNum")
    public void setBeneficiaryAccountNum(String beneficiaryAccountNum) {
        this.beneficiaryAccountNum = beneficiaryAccountNum;
    }

    @JsonProperty("ifscCode")
    public String getIfscCode() {
        return ifscCode;
    }

    @JsonProperty("ifscCode")
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    @JsonProperty("customerVAN")
    public String getCustomerVAN() {
        return customerVAN;
    }

    @JsonProperty("customerVAN")
    public void setCustomerVAN(String customerVAN) {
        this.customerVAN = customerVAN;
    }

    @JsonProperty("virtualAccountId")
    public String getVirtualAccountId() {
        return virtualAccountId;
    }

    @JsonProperty("virtualAccountId")
    public void setVirtualAccountId(String virtualAccountId) {
        this.virtualAccountId = virtualAccountId;
    }

    @JsonProperty("salesClassification")
    public String getSalesClassification() {
        return salesClassification;
    }

    @JsonProperty("salesClassification")
    public void setSalesClassification(String salesClassification) {
        this.salesClassification = salesClassification;
    }

    @JsonProperty("bankName")
    public Object getBankName() {
        return bankName;
    }

    @JsonProperty("bankName")
    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    @JsonProperty("testUser")
    public Boolean getTestUser() {
        return testUser;
    }

    @JsonProperty("testUser")
    public void setTestUser(Boolean testUser) {
        this.testUser = testUser;
    }

    @JsonProperty("userEmail")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty("userEmail")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty("opportunityOwnerEmail")
    public Object getOpportunityOwnerEmail() {
        return opportunityOwnerEmail;
    }

    @JsonProperty("opportunityOwnerEmail")
    public void setOpportunityOwnerEmail(Object opportunityOwnerEmail) {
        this.opportunityOwnerEmail = opportunityOwnerEmail;
    }

    @JsonProperty("razorPayBankName")
    public String getRazorPayBankName() {
        return razorPayBankName;
    }

    @JsonProperty("razorPayBankName")
    public void setRazorPayBankName(String razorPayBankName) {
        this.razorPayBankName = razorPayBankName;
    }

    @JsonProperty("sfAccountId")
    public String getSfAccountId() {
        return sfAccountId;
    }

    @JsonProperty("sfAccountId")
    public void setSfAccountId(String sfAccountId) {
        this.sfAccountId = sfAccountId;
    }

    @JsonProperty("customerPan")
    public String getCustomerPan() {
        return customerPan;
    }

    @JsonProperty("customerPan")
    public void setCustomerPan(String customerPan) {
        this.customerPan = customerPan;
    }

    @JsonProperty("customerAccountType")
    public String getCustomerAccountType() {
        return customerAccountType;
    }

    @JsonProperty("customerAccountType")
    public void setCustomerAccountType(String customerAccountType) {
        this.customerAccountType = customerAccountType;
    }

    @JsonProperty("businessDivision")
    public String getBusinessDivision() {
        return businessDivision;
    }

    @JsonProperty("businessDivision")
    public void setBusinessDivision(String businessDivision) {
        this.businessDivision = businessDivision;
    }

    @JsonProperty("isPBDistributor")
    public Boolean getIsPBDistributor() {
        return isPBDistributor;
    }

    @JsonProperty("isPBDistributor")
    public void setIsPBDistributor(Boolean isPBDistributor) {
        this.isPBDistributor = isPBDistributor;
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
        sb.append(CustomerData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerId");
        sb.append('=');
        sb.append(((this.customerId == null)?"<null>":this.customerId));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("phoneNumber");
        sb.append('=');
        sb.append(((this.phoneNumber == null)?"<null>":this.phoneNumber));
        sb.append(',');
        sb.append("companyName");
        sb.append('=');
        sb.append(((this.companyName == null)?"<null>":this.companyName));
        sb.append(',');
        sb.append("gstIn");
        sb.append('=');
        sb.append(((this.gstIn == null)?"<null>":this.gstIn));
        sb.append(',');
        sb.append("beneficiaryName");
        sb.append('=');
        sb.append(((this.beneficiaryName == null)?"<null>":this.beneficiaryName));
        sb.append(',');
        sb.append("beneficiaryAccountNum");
        sb.append('=');
        sb.append(((this.beneficiaryAccountNum == null)?"<null>":this.beneficiaryAccountNum));
        sb.append(',');
        sb.append("ifscCode");
        sb.append('=');
        sb.append(((this.ifscCode == null)?"<null>":this.ifscCode));
        sb.append(',');
        sb.append("customerVAN");
        sb.append('=');
        sb.append(((this.customerVAN == null)?"<null>":this.customerVAN));
        sb.append(',');
        sb.append("virtualAccountId");
        sb.append('=');
        sb.append(((this.virtualAccountId == null)?"<null>":this.virtualAccountId));
        sb.append(',');
        sb.append("salesClassification");
        sb.append('=');
        sb.append(((this.salesClassification == null)?"<null>":this.salesClassification));
        sb.append(',');
        sb.append("bankName");
        sb.append('=');
        sb.append(((this.bankName == null)?"<null>":this.bankName));
        sb.append(',');
        sb.append("testUser");
        sb.append('=');
        sb.append(((this.testUser == null)?"<null>":this.testUser));
        sb.append(',');
        sb.append("userEmail");
        sb.append('=');
        sb.append(((this.userEmail == null)?"<null>":this.userEmail));
        sb.append(',');
        sb.append("opportunityOwnerEmail");
        sb.append('=');
        sb.append(((this.opportunityOwnerEmail == null)?"<null>":this.opportunityOwnerEmail));
        sb.append(',');
        sb.append("razorPayBankName");
        sb.append('=');
        sb.append(((this.razorPayBankName == null)?"<null>":this.razorPayBankName));
        sb.append(',');
        sb.append("sfAccountId");
        sb.append('=');
        sb.append(((this.sfAccountId == null)?"<null>":this.sfAccountId));
        sb.append(',');
        sb.append("customerPan");
        sb.append('=');
        sb.append(((this.customerPan == null)?"<null>":this.customerPan));
        sb.append(',');
        sb.append("customerAccountType");
        sb.append('=');
        sb.append(((this.customerAccountType == null)?"<null>":this.customerAccountType));
        sb.append(',');
        sb.append("businessDivision");
        sb.append('=');
        sb.append(((this.businessDivision == null)?"<null>":this.businessDivision));
        sb.append(',');
        sb.append("isPBDistributor");
        sb.append('=');
        sb.append(((this.isPBDistributor == null)?"<null>":this.isPBDistributor));
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
        result = ((result* 31)+((this.virtualAccountId == null)? 0 :this.virtualAccountId.hashCode()));
        result = ((result* 31)+((this.razorPayBankName == null)? 0 :this.razorPayBankName.hashCode()));
        result = ((result* 31)+((this.customerVAN == null)? 0 :this.customerVAN.hashCode()));
        result = ((result* 31)+((this.companyName == null)? 0 :this.companyName.hashCode()));
        result = ((result* 31)+((this.customerAccountType == null)? 0 :this.customerAccountType.hashCode()));
        result = ((result* 31)+((this.bankName == null)? 0 :this.bankName.hashCode()));
        result = ((result* 31)+((this.gstIn == null)? 0 :this.gstIn.hashCode()));
        result = ((result* 31)+((this.businessDivision == null)? 0 :this.businessDivision.hashCode()));
        result = ((result* 31)+((this.phoneNumber == null)? 0 :this.phoneNumber.hashCode()));
        result = ((result* 31)+((this.beneficiaryName == null)? 0 :this.beneficiaryName.hashCode()));
        result = ((result* 31)+((this.opportunityOwnerEmail == null)? 0 :this.opportunityOwnerEmail.hashCode()));
        result = ((result* 31)+((this.beneficiaryAccountNum == null)? 0 :this.beneficiaryAccountNum.hashCode()));
        result = ((result* 31)+((this.customerId == null)? 0 :this.customerId.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.isPBDistributor == null)? 0 :this.isPBDistributor.hashCode()));
        result = ((result* 31)+((this.salesClassification == null)? 0 :this.salesClassification.hashCode()));
        result = ((result* 31)+((this.userEmail == null)? 0 :this.userEmail.hashCode()));
        result = ((result* 31)+((this.testUser == null)? 0 :this.testUser.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.ifscCode == null)? 0 :this.ifscCode.hashCode()));
        result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
        result = ((result* 31)+((this.sfAccountId == null)? 0 :this.sfAccountId.hashCode()));
        result = ((result* 31)+((this.customerPan == null)? 0 :this.customerPan.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CustomerData) == false) {
            return false;
        }
        CustomerData rhs = ((CustomerData) other);
        return ((((((((((((((((((((((((this.virtualAccountId == rhs.virtualAccountId)||((this.virtualAccountId!= null)&&this.virtualAccountId.equals(rhs.virtualAccountId)))&&((this.razorPayBankName == rhs.razorPayBankName)||((this.razorPayBankName!= null)&&this.razorPayBankName.equals(rhs.razorPayBankName))))&&((this.customerVAN == rhs.customerVAN)||((this.customerVAN!= null)&&this.customerVAN.equals(rhs.customerVAN))))&&((this.companyName == rhs.companyName)||((this.companyName!= null)&&this.companyName.equals(rhs.companyName))))&&((this.customerAccountType == rhs.customerAccountType)||((this.customerAccountType!= null)&&this.customerAccountType.equals(rhs.customerAccountType))))&&((this.bankName == rhs.bankName)||((this.bankName!= null)&&this.bankName.equals(rhs.bankName))))&&((this.gstIn == rhs.gstIn)||((this.gstIn!= null)&&this.gstIn.equals(rhs.gstIn))))&&((this.businessDivision == rhs.businessDivision)||((this.businessDivision!= null)&&this.businessDivision.equals(rhs.businessDivision))))&&((this.phoneNumber == rhs.phoneNumber)||((this.phoneNumber!= null)&&this.phoneNumber.equals(rhs.phoneNumber))))&&((this.beneficiaryName == rhs.beneficiaryName)||((this.beneficiaryName!= null)&&this.beneficiaryName.equals(rhs.beneficiaryName))))&&((this.opportunityOwnerEmail == rhs.opportunityOwnerEmail)||((this.opportunityOwnerEmail!= null)&&this.opportunityOwnerEmail.equals(rhs.opportunityOwnerEmail))))&&((this.beneficiaryAccountNum == rhs.beneficiaryAccountNum)||((this.beneficiaryAccountNum!= null)&&this.beneficiaryAccountNum.equals(rhs.beneficiaryAccountNum))))&&((this.customerId == rhs.customerId)||((this.customerId!= null)&&this.customerId.equals(rhs.customerId))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.isPBDistributor == rhs.isPBDistributor)||((this.isPBDistributor!= null)&&this.isPBDistributor.equals(rhs.isPBDistributor))))&&((this.salesClassification == rhs.salesClassification)||((this.salesClassification!= null)&&this.salesClassification.equals(rhs.salesClassification))))&&((this.userEmail == rhs.userEmail)||((this.userEmail!= null)&&this.userEmail.equals(rhs.userEmail))))&&((this.testUser == rhs.testUser)||((this.testUser!= null)&&this.testUser.equals(rhs.testUser))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.ifscCode == rhs.ifscCode)||((this.ifscCode!= null)&&this.ifscCode.equals(rhs.ifscCode))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))))&&((this.sfAccountId == rhs.sfAccountId)||((this.sfAccountId!= null)&&this.sfAccountId.equals(rhs.sfAccountId))))&&((this.customerPan == rhs.customerPan)||((this.customerPan!= null)&&this.customerPan.equals(rhs.customerPan))));
    }

}
