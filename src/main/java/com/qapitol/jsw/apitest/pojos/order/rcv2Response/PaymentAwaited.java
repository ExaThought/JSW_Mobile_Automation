
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "type",
    "version",
    "amount",
    "state",
    "paymentMethod",
    "paymentInterface",
    "transactions",
    "paymentLink",
    "paymentInitiator",
    "invoiceDueDate",
    "paymentCreationDate",
    "invoiceNumber",
    "shipmentNumber"
})
@Generated("jsonschema2pojo")
public class PaymentAwaited {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("amount")
    private Amount amount;
    @JsonProperty("state")
    private String state;
    @JsonProperty("paymentMethod")
    private Object paymentMethod;
    @JsonProperty("paymentInterface")
    private Object paymentInterface;
    @JsonProperty("transactions")
    private List<Object> transactions = new ArrayList<Object>();
    @JsonProperty("paymentLink")
    private Object paymentLink;
    @JsonProperty("paymentInitiator")
    private String paymentInitiator;
    @JsonProperty("invoiceDueDate")
    private Object invoiceDueDate;
    @JsonProperty("paymentCreationDate")
    private String paymentCreationDate;
    @JsonProperty("invoiceNumber")
    private Object invoiceNumber;
    @JsonProperty("shipmentNumber")
    private Object shipmentNumber;
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

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("amount")
    public Amount getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("paymentMethod")
    public Object getPaymentMethod() {
        return paymentMethod;
    }

    @JsonProperty("paymentMethod")
    public void setPaymentMethod(Object paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @JsonProperty("paymentInterface")
    public Object getPaymentInterface() {
        return paymentInterface;
    }

    @JsonProperty("paymentInterface")
    public void setPaymentInterface(Object paymentInterface) {
        this.paymentInterface = paymentInterface;
    }

    @JsonProperty("transactions")
    public List<Object> getTransactions() {
        return transactions;
    }

    @JsonProperty("transactions")
    public void setTransactions(List<Object> transactions) {
        this.transactions = transactions;
    }

    @JsonProperty("paymentLink")
    public Object getPaymentLink() {
        return paymentLink;
    }

    @JsonProperty("paymentLink")
    public void setPaymentLink(Object paymentLink) {
        this.paymentLink = paymentLink;
    }

    @JsonProperty("paymentInitiator")
    public String getPaymentInitiator() {
        return paymentInitiator;
    }

    @JsonProperty("paymentInitiator")
    public void setPaymentInitiator(String paymentInitiator) {
        this.paymentInitiator = paymentInitiator;
    }

    @JsonProperty("invoiceDueDate")
    public Object getInvoiceDueDate() {
        return invoiceDueDate;
    }

    @JsonProperty("invoiceDueDate")
    public void setInvoiceDueDate(Object invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    @JsonProperty("paymentCreationDate")
    public String getPaymentCreationDate() {
        return paymentCreationDate;
    }

    @JsonProperty("paymentCreationDate")
    public void setPaymentCreationDate(String paymentCreationDate) {
        this.paymentCreationDate = paymentCreationDate;
    }

    @JsonProperty("invoiceNumber")
    public Object getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(Object invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @JsonProperty("shipmentNumber")
    public Object getShipmentNumber() {
        return shipmentNumber;
    }

    @JsonProperty("shipmentNumber")
    public void setShipmentNumber(Object shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
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
        sb.append(PaymentAwaited.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("paymentMethod");
        sb.append('=');
        sb.append(((this.paymentMethod == null)?"<null>":this.paymentMethod));
        sb.append(',');
        sb.append("paymentInterface");
        sb.append('=');
        sb.append(((this.paymentInterface == null)?"<null>":this.paymentInterface));
        sb.append(',');
        sb.append("transactions");
        sb.append('=');
        sb.append(((this.transactions == null)?"<null>":this.transactions));
        sb.append(',');
        sb.append("paymentLink");
        sb.append('=');
        sb.append(((this.paymentLink == null)?"<null>":this.paymentLink));
        sb.append(',');
        sb.append("paymentInitiator");
        sb.append('=');
        sb.append(((this.paymentInitiator == null)?"<null>":this.paymentInitiator));
        sb.append(',');
        sb.append("invoiceDueDate");
        sb.append('=');
        sb.append(((this.invoiceDueDate == null)?"<null>":this.invoiceDueDate));
        sb.append(',');
        sb.append("paymentCreationDate");
        sb.append('=');
        sb.append(((this.paymentCreationDate == null)?"<null>":this.paymentCreationDate));
        sb.append(',');
        sb.append("invoiceNumber");
        sb.append('=');
        sb.append(((this.invoiceNumber == null)?"<null>":this.invoiceNumber));
        sb.append(',');
        sb.append("shipmentNumber");
        sb.append('=');
        sb.append(((this.shipmentNumber == null)?"<null>":this.shipmentNumber));
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
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.paymentInitiator == null)? 0 :this.paymentInitiator.hashCode()));
        result = ((result* 31)+((this.paymentLink == null)? 0 :this.paymentLink.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.transactions == null)? 0 :this.transactions.hashCode()));
        result = ((result* 31)+((this.paymentCreationDate == null)? 0 :this.paymentCreationDate.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.invoiceDueDate == null)? 0 :this.invoiceDueDate.hashCode()));
        result = ((result* 31)+((this.paymentInterface == null)? 0 :this.paymentInterface.hashCode()));
        result = ((result* 31)+((this.invoiceNumber == null)? 0 :this.invoiceNumber.hashCode()));
        result = ((result* 31)+((this.paymentMethod == null)? 0 :this.paymentMethod.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.shipmentNumber == null)? 0 :this.shipmentNumber.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentAwaited) == false) {
            return false;
        }
        PaymentAwaited rhs = ((PaymentAwaited) other);
        return ((((((((((((((((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount)))&&((this.paymentInitiator == rhs.paymentInitiator)||((this.paymentInitiator!= null)&&this.paymentInitiator.equals(rhs.paymentInitiator))))&&((this.paymentLink == rhs.paymentLink)||((this.paymentLink!= null)&&this.paymentLink.equals(rhs.paymentLink))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.transactions == rhs.transactions)||((this.transactions!= null)&&this.transactions.equals(rhs.transactions))))&&((this.paymentCreationDate == rhs.paymentCreationDate)||((this.paymentCreationDate!= null)&&this.paymentCreationDate.equals(rhs.paymentCreationDate))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.invoiceDueDate == rhs.invoiceDueDate)||((this.invoiceDueDate!= null)&&this.invoiceDueDate.equals(rhs.invoiceDueDate))))&&((this.paymentInterface == rhs.paymentInterface)||((this.paymentInterface!= null)&&this.paymentInterface.equals(rhs.paymentInterface))))&&((this.invoiceNumber == rhs.invoiceNumber)||((this.invoiceNumber!= null)&&this.invoiceNumber.equals(rhs.invoiceNumber))))&&((this.paymentMethod == rhs.paymentMethod)||((this.paymentMethod!= null)&&this.paymentMethod.equals(rhs.paymentMethod))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.shipmentNumber == rhs.shipmentNumber)||((this.shipmentNumber!= null)&&this.shipmentNumber.equals(rhs.shipmentNumber))));
    }

}
