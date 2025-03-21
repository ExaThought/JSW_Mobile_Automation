
package com.qapitol.jsw.apitest.pojos.order.updateShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customerOrderId",
    "sellerGstin",
    "invoiceDate",
    "actualDispatchDate",
    "actualDeliveryDate",
    "currentState",
    "deliveryType",
    "id",
    "shipmentInvoiceDetails",
    "modeOfShipment",
    "sellerOrderId",
    "invoiceDueDate",
    "lineItemsDetails",
    "vehicleDetails"
})
@Generated("jsonschema2pojo")
public class UpdateShipment {

    @JsonProperty("customerOrderId")
    private Object customerOrderId;
    @JsonProperty("sellerGstin")
    private String sellerGstin;
    @JsonProperty("invoiceDate")
    private String invoiceDate;
    @JsonProperty("actualDispatchDate")
    private String actualDispatchDate;
    @JsonProperty("actualDeliveryDate")
    private String actualDeliveryDate;
    @JsonProperty("currentState")
    private CurrentState currentState;
    @JsonProperty("deliveryType")
    private DeliveryType deliveryType;
    @JsonProperty("id")
    private Object id;
    @JsonProperty("shipmentInvoiceDetails")
    private ShipmentInvoiceDetails shipmentInvoiceDetails;
    @JsonProperty("modeOfShipment")
    private String modeOfShipment;
    @JsonProperty("sellerOrderId")
    private Object sellerOrderId;
    @JsonProperty("invoiceDueDate")
    private String invoiceDueDate;
    @JsonProperty("lineItemsDetails")
    private List<LineItemsDetail> lineItemsDetails = new ArrayList<LineItemsDetail>();
    @JsonProperty("vehicleDetails")
    private VehicleDetails vehicleDetails;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("customerOrderId")
    public Object getCustomerOrderId() {
        return customerOrderId;
    }

    @JsonProperty("customerOrderId")
    public void setCustomerOrderId(Object customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    @JsonProperty("sellerGstin")
    public String getSellerGstin() {
        return sellerGstin;
    }

    @JsonProperty("sellerGstin")
    public void setSellerGstin(String sellerGstin) {
        this.sellerGstin = sellerGstin;
    }

    @JsonProperty("invoiceDate")
    public String getInvoiceDate() {
        return invoiceDate;
    }

    @JsonProperty("invoiceDate")
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @JsonProperty("actualDispatchDate")
    public String getActualDispatchDate() {
        return actualDispatchDate;
    }

    @JsonProperty("actualDispatchDate")
    public void setActualDispatchDate(String actualDispatchDate) {
        this.actualDispatchDate = actualDispatchDate;
    }

    @JsonProperty("actualDeliveryDate")
    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    @JsonProperty("actualDeliveryDate")
    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    @JsonProperty("currentState")
    public CurrentState getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("deliveryType")
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Object id) {
        this.id = id;
    }

    @JsonProperty("shipmentInvoiceDetails")
    public ShipmentInvoiceDetails getShipmentInvoiceDetails() {
        return shipmentInvoiceDetails;
    }

    @JsonProperty("shipmentInvoiceDetails")
    public void setShipmentInvoiceDetails(ShipmentInvoiceDetails shipmentInvoiceDetails) {
        this.shipmentInvoiceDetails = shipmentInvoiceDetails;
    }

    @JsonProperty("modeOfShipment")
    public String getModeOfShipment() {
        return modeOfShipment;
    }

    @JsonProperty("modeOfShipment")
    public void setModeOfShipment(String modeOfShipment) {
        this.modeOfShipment = modeOfShipment;
    }

    @JsonProperty("sellerOrderId")
    public Object getSellerOrderId() {
        return sellerOrderId;
    }

    @JsonProperty("sellerOrderId")
    public void setSellerOrderId(Object sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    @JsonProperty("invoiceDueDate")
    public String getInvoiceDueDate() {
        return invoiceDueDate;
    }

    @JsonProperty("invoiceDueDate")
    public void setInvoiceDueDate(String invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    @JsonProperty("lineItemsDetails")
    public List<LineItemsDetail> getLineItemsDetails() {
        return lineItemsDetails;
    }

    @JsonProperty("lineItemsDetails")
    public void setLineItemsDetails(List<LineItemsDetail> lineItemsDetails) {
        this.lineItemsDetails = lineItemsDetails;
    }

    @JsonProperty("vehicleDetails")
    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    @JsonProperty("vehicleDetails")
    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
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
        sb.append(UpdateShipment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerOrderId");
        sb.append('=');
        sb.append(((this.customerOrderId == null)?"<null>":this.customerOrderId));
        sb.append(',');
        sb.append("sellerGstin");
        sb.append('=');
        sb.append(((this.sellerGstin == null)?"<null>":this.sellerGstin));
        sb.append(',');
        sb.append("invoiceDate");
        sb.append('=');
        sb.append(((this.invoiceDate == null)?"<null>":this.invoiceDate));
        sb.append(',');
        sb.append("actualDispatchDate");
        sb.append('=');
        sb.append(((this.actualDispatchDate == null)?"<null>":this.actualDispatchDate));
        sb.append(',');
        sb.append("actualDeliveryDate");
        sb.append('=');
        sb.append(((this.actualDeliveryDate == null)?"<null>":this.actualDeliveryDate));
        sb.append(',');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("shipmentInvoiceDetails");
        sb.append('=');
        sb.append(((this.shipmentInvoiceDetails == null)?"<null>":this.shipmentInvoiceDetails));
        sb.append(',');
        sb.append("modeOfShipment");
        sb.append('=');
        sb.append(((this.modeOfShipment == null)?"<null>":this.modeOfShipment));
        sb.append(',');
        sb.append("sellerOrderId");
        sb.append('=');
        sb.append(((this.sellerOrderId == null)?"<null>":this.sellerOrderId));
        sb.append(',');
        sb.append("invoiceDueDate");
        sb.append('=');
        sb.append(((this.invoiceDueDate == null)?"<null>":this.invoiceDueDate));
        sb.append(',');
        sb.append("lineItemsDetails");
        sb.append('=');
        sb.append(((this.lineItemsDetails == null)?"<null>":this.lineItemsDetails));
        sb.append(',');
        sb.append("vehicleDetails");
        sb.append('=');
        sb.append(((this.vehicleDetails == null)?"<null>":this.vehicleDetails));
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
        result = ((result* 31)+((this.actualDeliveryDate == null)? 0 :this.actualDeliveryDate.hashCode()));
        result = ((result* 31)+((this.vehicleDetails == null)? 0 :this.vehicleDetails.hashCode()));
        result = ((result* 31)+((this.customerOrderId == null)? 0 :this.customerOrderId.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.modeOfShipment == null)? 0 :this.modeOfShipment.hashCode()));
        result = ((result* 31)+((this.invoiceDate == null)? 0 :this.invoiceDate.hashCode()));
        result = ((result* 31)+((this.invoiceDueDate == null)? 0 :this.invoiceDueDate.hashCode()));
        result = ((result* 31)+((this.lineItemsDetails == null)? 0 :this.lineItemsDetails.hashCode()));
        result = ((result* 31)+((this.actualDispatchDate == null)? 0 :this.actualDispatchDate.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        result = ((result* 31)+((this.shipmentInvoiceDetails == null)? 0 :this.shipmentInvoiceDetails.hashCode()));
        result = ((result* 31)+((this.sellerGstin == null)? 0 :this.sellerGstin.hashCode()));
        result = ((result* 31)+((this.sellerOrderId == null)? 0 :this.sellerOrderId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UpdateShipment) == false) {
            return false;
        }
        UpdateShipment rhs = ((UpdateShipment) other);
        return ((((((((((((((((this.actualDeliveryDate == rhs.actualDeliveryDate)||((this.actualDeliveryDate!= null)&&this.actualDeliveryDate.equals(rhs.actualDeliveryDate)))&&((this.vehicleDetails == rhs.vehicleDetails)||((this.vehicleDetails!= null)&&this.vehicleDetails.equals(rhs.vehicleDetails))))&&((this.customerOrderId == rhs.customerOrderId)||((this.customerOrderId!= null)&&this.customerOrderId.equals(rhs.customerOrderId))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.modeOfShipment == rhs.modeOfShipment)||((this.modeOfShipment!= null)&&this.modeOfShipment.equals(rhs.modeOfShipment))))&&((this.invoiceDate == rhs.invoiceDate)||((this.invoiceDate!= null)&&this.invoiceDate.equals(rhs.invoiceDate))))&&((this.invoiceDueDate == rhs.invoiceDueDate)||((this.invoiceDueDate!= null)&&this.invoiceDueDate.equals(rhs.invoiceDueDate))))&&((this.lineItemsDetails == rhs.lineItemsDetails)||((this.lineItemsDetails!= null)&&this.lineItemsDetails.equals(rhs.lineItemsDetails))))&&((this.actualDispatchDate == rhs.actualDispatchDate)||((this.actualDispatchDate!= null)&&this.actualDispatchDate.equals(rhs.actualDispatchDate))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))))&&((this.shipmentInvoiceDetails == rhs.shipmentInvoiceDetails)||((this.shipmentInvoiceDetails!= null)&&this.shipmentInvoiceDetails.equals(rhs.shipmentInvoiceDetails))))&&((this.sellerGstin == rhs.sellerGstin)||((this.sellerGstin!= null)&&this.sellerGstin.equals(rhs.sellerGstin))))&&((this.sellerOrderId == rhs.sellerOrderId)||((this.sellerOrderId!= null)&&this.sellerOrderId.equals(rhs.sellerOrderId))));
    }

}
