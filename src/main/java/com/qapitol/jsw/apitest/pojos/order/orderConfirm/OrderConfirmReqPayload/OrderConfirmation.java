
package com.qapitol.jsw.apitest.pojos.order.orderConfirm.OrderConfirmReqPayload;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderNumber",
    "orderStatus",
    "opsSpoc",
    "childOrders",
    "isVerifiedPO",
    "isVerifiedSO",
    "isGstDetailsVerified",
    "sendCustomerEmail",
    "sendSellerEmail",
    "sellerEmailAdditionalText"
})
@Generated("jsonschema2pojo")
public class OrderConfirmation {

    @JsonProperty("orderNumber")
    private String orderNumber;
    @JsonProperty("orderStatus")
    private String orderStatus;
    @JsonProperty("opsSpoc")
    private String opsSpoc;
    @JsonProperty("childOrders")
    private List<ChildOrder> childOrders = new ArrayList<ChildOrder>();
    @JsonProperty("isVerifiedPO")
    private Boolean isVerifiedPO;
    @JsonProperty("isVerifiedSO")
    private Boolean isVerifiedSO;
    @JsonProperty("isGstDetailsVerified")
    private Boolean isGstDetailsVerified;
    @JsonProperty("sendCustomerEmail")
    private Boolean sendCustomerEmail;
    @JsonProperty("sendSellerEmail")
    private Boolean sendSellerEmail;
    @JsonProperty("sellerEmailAdditionalText")
    private String sellerEmailAdditionalText;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("orderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @JsonProperty("orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("orderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("opsSpoc")
    public String getOpsSpoc() {
        return opsSpoc;
    }

    @JsonProperty("opsSpoc")
    public void setOpsSpoc(String opsSpoc) {
        this.opsSpoc = opsSpoc;
    }

    @JsonProperty("childOrders")
    public List<ChildOrder> getChildOrders() {
        return childOrders;
    }

    @JsonProperty("childOrders")
    public void setChildOrders(List<ChildOrder> childOrders) {
        this.childOrders = childOrders;
    }

    @JsonProperty("isVerifiedPO")
    public Boolean getIsVerifiedPO() {
        return isVerifiedPO;
    }

    @JsonProperty("isVerifiedPO")
    public void setIsVerifiedPO(Boolean isVerifiedPO) {
        this.isVerifiedPO = isVerifiedPO;
    }

    @JsonProperty("isVerifiedSO")
    public Boolean getIsVerifiedSO() {
        return isVerifiedSO;
    }

    @JsonProperty("isVerifiedSO")
    public void setIsVerifiedSO(Boolean isVerifiedSO) {
        this.isVerifiedSO = isVerifiedSO;
    }

    @JsonProperty("isGstDetailsVerified")
    public Boolean getIsGstDetailsVerified() {
        return isGstDetailsVerified;
    }

    @JsonProperty("isGstDetailsVerified")
    public void setIsGstDetailsVerified(Boolean isGstDetailsVerified) {
        this.isGstDetailsVerified = isGstDetailsVerified;
    }

    @JsonProperty("sendCustomerEmail")
    public Boolean getSendCustomerEmail() {
        return sendCustomerEmail;
    }

    @JsonProperty("sendCustomerEmail")
    public void setSendCustomerEmail(Boolean sendCustomerEmail) {
        this.sendCustomerEmail = sendCustomerEmail;
    }

    @JsonProperty("sendSellerEmail")
    public Boolean getSendSellerEmail() {
        return sendSellerEmail;
    }

    @JsonProperty("sendSellerEmail")
    public void setSendSellerEmail(Boolean sendSellerEmail) {
        this.sendSellerEmail = sendSellerEmail;
    }

    @JsonProperty("sellerEmailAdditionalText")
    public String getSellerEmailAdditionalText() {
        return sellerEmailAdditionalText;
    }

    @JsonProperty("sellerEmailAdditionalText")
    public void setSellerEmailAdditionalText(String sellerEmailAdditionalText) {
        this.sellerEmailAdditionalText = sellerEmailAdditionalText;
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
        sb.append(OrderConfirmation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderNumber");
        sb.append('=');
        sb.append(((this.orderNumber == null)?"<null>":this.orderNumber));
        sb.append(',');
        sb.append("orderStatus");
        sb.append('=');
        sb.append(((this.orderStatus == null)?"<null>":this.orderStatus));
        sb.append(',');
        sb.append("opsSpoc");
        sb.append('=');
        sb.append(((this.opsSpoc == null)?"<null>":this.opsSpoc));
        sb.append(',');
        sb.append("childOrders");
        sb.append('=');
        sb.append(((this.childOrders == null)?"<null>":this.childOrders));
        sb.append(',');
        sb.append("isVerifiedPO");
        sb.append('=');
        sb.append(((this.isVerifiedPO == null)?"<null>":this.isVerifiedPO));
        sb.append(',');
        sb.append("isVerifiedSO");
        sb.append('=');
        sb.append(((this.isVerifiedSO == null)?"<null>":this.isVerifiedSO));
        sb.append(',');
        sb.append("isGstDetailsVerified");
        sb.append('=');
        sb.append(((this.isGstDetailsVerified == null)?"<null>":this.isGstDetailsVerified));
        sb.append(',');
        sb.append("sendCustomerEmail");
        sb.append('=');
        sb.append(((this.sendCustomerEmail == null)?"<null>":this.sendCustomerEmail));
        sb.append(',');
        sb.append("sendSellerEmail");
        sb.append('=');
        sb.append(((this.sendSellerEmail == null)?"<null>":this.sendSellerEmail));
        sb.append(',');
        sb.append("sellerEmailAdditionalText");
        sb.append('=');
        sb.append(((this.sellerEmailAdditionalText == null)?"<null>":this.sellerEmailAdditionalText));
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
        result = ((result* 31)+((this.isVerifiedSO == null)? 0 :this.isVerifiedSO.hashCode()));
        result = ((result* 31)+((this.orderNumber == null)? 0 :this.orderNumber.hashCode()));
        result = ((result* 31)+((this.sellerEmailAdditionalText == null)? 0 :this.sellerEmailAdditionalText.hashCode()));
        result = ((result* 31)+((this.sendSellerEmail == null)? 0 :this.sendSellerEmail.hashCode()));
        result = ((result* 31)+((this.orderStatus == null)? 0 :this.orderStatus.hashCode()));
        result = ((result* 31)+((this.childOrders == null)? 0 :this.childOrders.hashCode()));
        result = ((result* 31)+((this.sendCustomerEmail == null)? 0 :this.sendCustomerEmail.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.isGstDetailsVerified == null)? 0 :this.isGstDetailsVerified.hashCode()));
        result = ((result* 31)+((this.opsSpoc == null)? 0 :this.opsSpoc.hashCode()));
        result = ((result* 31)+((this.isVerifiedPO == null)? 0 :this.isVerifiedPO.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderConfirmation) == false) {
            return false;
        }
        OrderConfirmation rhs = ((OrderConfirmation) other);
        return ((((((((((((this.isVerifiedSO == rhs.isVerifiedSO)||((this.isVerifiedSO!= null)&&this.isVerifiedSO.equals(rhs.isVerifiedSO)))&&((this.orderNumber == rhs.orderNumber)||((this.orderNumber!= null)&&this.orderNumber.equals(rhs.orderNumber))))&&((this.sellerEmailAdditionalText == rhs.sellerEmailAdditionalText)||((this.sellerEmailAdditionalText!= null)&&this.sellerEmailAdditionalText.equals(rhs.sellerEmailAdditionalText))))&&((this.sendSellerEmail == rhs.sendSellerEmail)||((this.sendSellerEmail!= null)&&this.sendSellerEmail.equals(rhs.sendSellerEmail))))&&((this.orderStatus == rhs.orderStatus)||((this.orderStatus!= null)&&this.orderStatus.equals(rhs.orderStatus))))&&((this.childOrders == rhs.childOrders)||((this.childOrders!= null)&&this.childOrders.equals(rhs.childOrders))))&&((this.sendCustomerEmail == rhs.sendCustomerEmail)||((this.sendCustomerEmail!= null)&&this.sendCustomerEmail.equals(rhs.sendCustomerEmail))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.isGstDetailsVerified == rhs.isGstDetailsVerified)||((this.isGstDetailsVerified!= null)&&this.isGstDetailsVerified.equals(rhs.isGstDetailsVerified))))&&((this.opsSpoc == rhs.opsSpoc)||((this.opsSpoc!= null)&&this.opsSpoc.equals(rhs.opsSpoc))))&&((this.isVerifiedPO == rhs.isVerifiedPO)||((this.isVerifiedPO!= null)&&this.isVerifiedPO.equals(rhs.isVerifiedPO))));
    }

}
