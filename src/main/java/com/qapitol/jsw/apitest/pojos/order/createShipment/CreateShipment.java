
package com.qapitol.jsw.apitest.pojos.order.createShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currentState",
    "plannedDispatchDate",
    "deliveryType",
    "modeOfShipment",
    "sellerOrderId",
    "warehouseId",
    "shipmentNumber",
    "vehicleDetails",
    "lineItemsDetails",
    "sellerGstin"
})
@Generated("jsonschema2pojo")
public class CreateShipment {

    @JsonProperty("currentState")
    private CurrentState currentState;
    @JsonProperty("plannedDispatchDate")
    private Object plannedDispatchDate;
    @JsonProperty("deliveryType")
    private DeliveryType deliveryType;
    @JsonProperty("modeOfShipment")
    private String modeOfShipment;
    @JsonProperty("sellerOrderId")
    private Object sellerOrderId;
    @JsonProperty("warehouseId")
    private String warehouseId;
    @JsonProperty("shipmentNumber")
    private Object shipmentNumber;
    @JsonProperty("vehicleDetails")
    private VehicleDetails vehicleDetails;
    @JsonProperty("lineItemsDetails")
    private List<LineItemsDetail> lineItemsDetails = new ArrayList<LineItemsDetail>();
    @JsonProperty("sellerGstin")
    private String sellerGstin;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("currentState")
    public CurrentState getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("plannedDispatchDate")
    public Object getPlannedDispatchDate() {
        return plannedDispatchDate;
    }

    @JsonProperty("plannedDispatchDate")
    public void setPlannedDispatchDate(Object plannedDispatchDate) {
        this.plannedDispatchDate = plannedDispatchDate;
    }

    @JsonProperty("deliveryType")
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
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

    @JsonProperty("warehouseId")
    public String getWarehouseId() {
        return warehouseId;
    }

    @JsonProperty("warehouseId")
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    @JsonProperty("shipmentNumber")
    public Object getShipmentNumber() {
        return shipmentNumber;
    }

    @JsonProperty("shipmentNumber")
    public void setShipmentNumber(Object shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    @JsonProperty("vehicleDetails")
    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    @JsonProperty("vehicleDetails")
    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    @JsonProperty("lineItemsDetails")
    public List<LineItemsDetail> getLineItemsDetails() {
        return lineItemsDetails;
    }

    @JsonProperty("lineItemsDetails")
    public void setLineItemsDetails(List<LineItemsDetail> lineItemsDetails) {
        this.lineItemsDetails = lineItemsDetails;
    }

    @JsonProperty("sellerGstin")
    public String getSellerGstin() {
        return sellerGstin;
    }

    @JsonProperty("sellerGstin")
    public void setSellerGstin(String sellerGstin) {
        this.sellerGstin = sellerGstin;
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
        sb.append(CreateShipment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
        sb.append(',');
        sb.append("plannedDispatchDate");
        sb.append('=');
        sb.append(((this.plannedDispatchDate == null)?"<null>":this.plannedDispatchDate));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("modeOfShipment");
        sb.append('=');
        sb.append(((this.modeOfShipment == null)?"<null>":this.modeOfShipment));
        sb.append(',');
        sb.append("sellerOrderId");
        sb.append('=');
        sb.append(((this.sellerOrderId == null)?"<null>":this.sellerOrderId));
        sb.append(',');
        sb.append("warehouseId");
        sb.append('=');
        sb.append(((this.warehouseId == null)?"<null>":this.warehouseId));
        sb.append(',');
        sb.append("shipmentNumber");
        sb.append('=');
        sb.append(((this.shipmentNumber == null)?"<null>":this.shipmentNumber));
        sb.append(',');
        sb.append("vehicleDetails");
        sb.append('=');
        sb.append(((this.vehicleDetails == null)?"<null>":this.vehicleDetails));
        sb.append(',');
        sb.append("lineItemsDetails");
        sb.append('=');
        sb.append(((this.lineItemsDetails == null)?"<null>":this.lineItemsDetails));
        sb.append(',');
        sb.append("sellerGstin");
        sb.append('=');
        sb.append(((this.sellerGstin == null)?"<null>":this.sellerGstin));
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
        result = ((result* 31)+((this.lineItemsDetails == null)? 0 :this.lineItemsDetails.hashCode()));
        result = ((result* 31)+((this.vehicleDetails == null)? 0 :this.vehicleDetails.hashCode()));
        result = ((result* 31)+((this.warehouseId == null)? 0 :this.warehouseId.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.modeOfShipment == null)? 0 :this.modeOfShipment.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        result = ((result* 31)+((this.plannedDispatchDate == null)? 0 :this.plannedDispatchDate.hashCode()));
        result = ((result* 31)+((this.shipmentNumber == null)? 0 :this.shipmentNumber.hashCode()));
        result = ((result* 31)+((this.sellerOrderId == null)? 0 :this.sellerOrderId.hashCode()));
        result = ((result* 31)+((this.sellerGstin == null)? 0 :this.sellerGstin.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreateShipment) == false) {
            return false;
        }
        CreateShipment rhs = ((CreateShipment) other);
        return ((((((((((((this.lineItemsDetails == rhs.lineItemsDetails)||((this.lineItemsDetails!= null)&&this.lineItemsDetails.equals(rhs.lineItemsDetails)))&&((this.vehicleDetails == rhs.vehicleDetails)||((this.vehicleDetails!= null)&&this.vehicleDetails.equals(rhs.vehicleDetails))))&&((this.warehouseId == rhs.warehouseId)||((this.warehouseId!= null)&&this.warehouseId.equals(rhs.warehouseId))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.modeOfShipment == rhs.modeOfShipment)||((this.modeOfShipment!= null)&&this.modeOfShipment.equals(rhs.modeOfShipment))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))))&&((this.plannedDispatchDate == rhs.plannedDispatchDate)||((this.plannedDispatchDate!= null)&&this.plannedDispatchDate.equals(rhs.plannedDispatchDate))))&&((this.shipmentNumber == rhs.shipmentNumber)||((this.shipmentNumber!= null)&&this.shipmentNumber.equals(rhs.shipmentNumber))))&&((this.sellerOrderId == rhs.sellerOrderId)||((this.sellerOrderId!= null)&&this.sellerOrderId.equals(rhs.sellerOrderId))))&&((this.sellerGstin == rhs.sellerGstin)||((this.sellerGstin!= null)&&this.sellerGstin.equals(rhs.sellerGstin))));
    }

}
