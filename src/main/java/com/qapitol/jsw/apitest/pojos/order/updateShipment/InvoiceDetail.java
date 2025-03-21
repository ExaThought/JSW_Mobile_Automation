
package com.qapitol.jsw.apitest.pojos.order.updateShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "invoiceNumber",
    "invoicedDeliveryDate",
    "invoiceDate",
    "invoiceDueDate",
    "files",
    "currentState"
})
@Generated("jsonschema2pojo")
public class InvoiceDetail {

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    @JsonProperty("invoicedDeliveryDate")
    private String invoicedDeliveryDate;
    @JsonProperty("invoiceDate")
    private String invoiceDate;
    @JsonProperty("invoiceDueDate")
    private String invoiceDueDate;
    @JsonProperty("files")
    private Object files;
    @JsonProperty("currentState")
    private Object currentState;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @JsonProperty("invoicedDeliveryDate")
    public String getInvoicedDeliveryDate() {
        return invoicedDeliveryDate;
    }

    @JsonProperty("invoicedDeliveryDate")
    public void setInvoicedDeliveryDate(String invoicedDeliveryDate) {
        this.invoicedDeliveryDate = invoicedDeliveryDate;
    }

    @JsonProperty("invoiceDate")
    public String getInvoiceDate() {
        return invoiceDate;
    }

    @JsonProperty("invoiceDate")
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @JsonProperty("invoiceDueDate")
    public String getInvoiceDueDate() {
        return invoiceDueDate;
    }

    @JsonProperty("invoiceDueDate")
    public void setInvoiceDueDate(String invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    @JsonProperty("files")
    public Object getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(Object files) {
        this.files = files;
    }

    @JsonProperty("currentState")
    public Object getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(Object currentState) {
        this.currentState = currentState;
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
        sb.append(InvoiceDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("invoiceNumber");
        sb.append('=');
        sb.append(((this.invoiceNumber == null)?"<null>":this.invoiceNumber));
        sb.append(',');
        sb.append("invoicedDeliveryDate");
        sb.append('=');
        sb.append(((this.invoicedDeliveryDate == null)?"<null>":this.invoicedDeliveryDate));
        sb.append(',');
        sb.append("invoiceDate");
        sb.append('=');
        sb.append(((this.invoiceDate == null)?"<null>":this.invoiceDate));
        sb.append(',');
        sb.append("invoiceDueDate");
        sb.append('=');
        sb.append(((this.invoiceDueDate == null)?"<null>":this.invoiceDueDate));
        sb.append(',');
        sb.append("files");
        sb.append('=');
        sb.append(((this.files == null)?"<null>":this.files));
        sb.append(',');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
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
        result = ((result* 31)+((this.invoiceNumber == null)? 0 :this.invoiceNumber.hashCode()));
        result = ((result* 31)+((this.files == null)? 0 :this.files.hashCode()));
        result = ((result* 31)+((this.invoicedDeliveryDate == null)? 0 :this.invoicedDeliveryDate.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.invoiceDate == null)? 0 :this.invoiceDate.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        result = ((result* 31)+((this.invoiceDueDate == null)? 0 :this.invoiceDueDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InvoiceDetail) == false) {
            return false;
        }
        InvoiceDetail rhs = ((InvoiceDetail) other);
        return ((((((((this.invoiceNumber == rhs.invoiceNumber)||((this.invoiceNumber!= null)&&this.invoiceNumber.equals(rhs.invoiceNumber)))&&((this.files == rhs.files)||((this.files!= null)&&this.files.equals(rhs.files))))&&((this.invoicedDeliveryDate == rhs.invoicedDeliveryDate)||((this.invoicedDeliveryDate!= null)&&this.invoicedDeliveryDate.equals(rhs.invoicedDeliveryDate))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.invoiceDate == rhs.invoiceDate)||((this.invoiceDate!= null)&&this.invoiceDate.equals(rhs.invoiceDate))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))))&&((this.invoiceDueDate == rhs.invoiceDueDate)||((this.invoiceDueDate!= null)&&this.invoiceDueDate.equals(rhs.invoiceDueDate))));
    }

}
