
package com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "number",
    "currentState",
    "nextShipmentStates",
    "plannedDispatchDate",
    "shipmentInvoiceDetails",
    "deliveryType",
    "modeOfShipment",
    "sellerOrderId",
    "vehicleDetails",
    "sellerOrderNumber",
    "lineItemsDetails",
    "customerOrderId",
    "customerOrderNumber",
    "shipmentInvoiceLedgerPayoutEntriesFlag"
})
@Generated("jsonschema2pojo")
public class ShipmentDetails {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("number")
    private String number;
    @JsonProperty("currentState")
    private CurrentState currentState;
    @JsonProperty("nextShipmentStates")
    private List<NextShipmentState> nextShipmentStates = new ArrayList<NextShipmentState>();
    @JsonProperty("plannedDispatchDate")
    private String plannedDispatchDate;
    @JsonProperty("shipmentInvoiceDetails")
    private ShipmentInvoiceDetails shipmentInvoiceDetails;
    @JsonProperty("deliveryType")
    private DeliveryType deliveryType;
    @JsonProperty("modeOfShipment")
    private String modeOfShipment;
    @JsonProperty("sellerOrderId")
    private Integer sellerOrderId;
    @JsonProperty("vehicleDetails")
    private VehicleDetails vehicleDetails;
    @JsonProperty("sellerOrderNumber")
    private String sellerOrderNumber;
    @JsonProperty("lineItemsDetails")
    private List<LineItem> lineItemsDetails = new ArrayList<LineItem>();
    @JsonProperty("customerOrderId")
    private Integer customerOrderId;
    @JsonProperty("customerOrderNumber")
    private String customerOrderNumber;
    @JsonProperty("shipmentInvoiceLedgerPayoutEntriesFlag")
    private Boolean shipmentInvoiceLedgerPayoutEntriesFlag;
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

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("currentState")
    public CurrentState getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("nextShipmentStates")
    public List<NextShipmentState> getNextShipmentStates() {
        return nextShipmentStates;
    }

    @JsonProperty("nextShipmentStates")
    public void setNextShipmentStates(List<NextShipmentState> nextShipmentStates) {
        this.nextShipmentStates = nextShipmentStates;
    }

    @JsonProperty("plannedDispatchDate")
    public String getPlannedDispatchDate() {
        return plannedDispatchDate;
    }

    @JsonProperty("plannedDispatchDate")
    public void setPlannedDispatchDate(String plannedDispatchDate) {
        this.plannedDispatchDate = plannedDispatchDate;
    }

    @JsonProperty("shipmentInvoiceDetails")
    public ShipmentInvoiceDetails getShipmentInvoiceDetails() {
        return shipmentInvoiceDetails;
    }

    @JsonProperty("shipmentInvoiceDetails")
    public void setShipmentInvoiceDetails(ShipmentInvoiceDetails shipmentInvoiceDetails) {
        this.shipmentInvoiceDetails = shipmentInvoiceDetails;
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
    public Integer getSellerOrderId() {
        return sellerOrderId;
    }

    @JsonProperty("sellerOrderId")
    public void setSellerOrderId(Integer sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    @JsonProperty("vehicleDetails")
    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    @JsonProperty("vehicleDetails")
    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    @JsonProperty("sellerOrderNumber")
    public String getSellerOrderNumber() {
        return sellerOrderNumber;
    }

    @JsonProperty("sellerOrderNumber")
    public void setSellerOrderNumber(String sellerOrderNumber) {
        this.sellerOrderNumber = sellerOrderNumber;
    }

    @JsonProperty("lineItemsDetails")
    public List<LineItem> getLineItemsDetails() {
        return lineItemsDetails;
    }

    @JsonProperty("lineItemsDetails")
    public void setLineItemsDetails(List<LineItem> lineItemsDetails) {
        this.lineItemsDetails = lineItemsDetails;
    }

    @JsonProperty("customerOrderId")
    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    @JsonProperty("customerOrderId")
    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    @JsonProperty("customerOrderNumber")
    public String getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    @JsonProperty("customerOrderNumber")
    public void setCustomerOrderNumber(String customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    @JsonProperty("shipmentInvoiceLedgerPayoutEntriesFlag")
    public Boolean getShipmentInvoiceLedgerPayoutEntriesFlag() {
        return shipmentInvoiceLedgerPayoutEntriesFlag;
    }

    @JsonProperty("shipmentInvoiceLedgerPayoutEntriesFlag")
    public void setShipmentInvoiceLedgerPayoutEntriesFlag(Boolean shipmentInvoiceLedgerPayoutEntriesFlag) {
        this.shipmentInvoiceLedgerPayoutEntriesFlag = shipmentInvoiceLedgerPayoutEntriesFlag;
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
        sb.append(ShipmentDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("number");
        sb.append('=');
        sb.append(((this.number == null)?"<null>":this.number));
        sb.append(',');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
        sb.append(',');
        sb.append("nextShipmentStates");
        sb.append('=');
        sb.append(((this.nextShipmentStates == null)?"<null>":this.nextShipmentStates));
        sb.append(',');
        sb.append("plannedDispatchDate");
        sb.append('=');
        sb.append(((this.plannedDispatchDate == null)?"<null>":this.plannedDispatchDate));
        sb.append(',');
        sb.append("shipmentInvoiceDetails");
        sb.append('=');
        sb.append(((this.shipmentInvoiceDetails == null)?"<null>":this.shipmentInvoiceDetails));
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
        sb.append("vehicleDetails");
        sb.append('=');
        sb.append(((this.vehicleDetails == null)?"<null>":this.vehicleDetails));
        sb.append(',');
        sb.append("sellerOrderNumber");
        sb.append('=');
        sb.append(((this.sellerOrderNumber == null)?"<null>":this.sellerOrderNumber));
        sb.append(',');
        sb.append("lineItemsDetails");
        sb.append('=');
        sb.append(((this.lineItemsDetails == null)?"<null>":this.lineItemsDetails));
        sb.append(',');
        sb.append("customerOrderId");
        sb.append('=');
        sb.append(((this.customerOrderId == null)?"<null>":this.customerOrderId));
        sb.append(',');
        sb.append("customerOrderNumber");
        sb.append('=');
        sb.append(((this.customerOrderNumber == null)?"<null>":this.customerOrderNumber));
        sb.append(',');
        sb.append("shipmentInvoiceLedgerPayoutEntriesFlag");
        sb.append('=');
        sb.append(((this.shipmentInvoiceLedgerPayoutEntriesFlag == null)?"<null>":this.shipmentInvoiceLedgerPayoutEntriesFlag));
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
        result = ((result* 31)+((this.vehicleDetails == null)? 0 :this.vehicleDetails.hashCode()));
        result = ((result* 31)+((this.customerOrderId == null)? 0 :this.customerOrderId.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.modeOfShipment == null)? 0 :this.modeOfShipment.hashCode()));
        result = ((result* 31)+((this.customerOrderNumber == null)? 0 :this.customerOrderNumber.hashCode()));
        result = ((result* 31)+((this.sellerOrderNumber == null)? 0 :this.sellerOrderNumber.hashCode()));
        result = ((result* 31)+((this.number == null)? 0 :this.number.hashCode()));
        result = ((result* 31)+((this.lineItemsDetails == null)? 0 :this.lineItemsDetails.hashCode()));
        result = ((result* 31)+((this.nextShipmentStates == null)? 0 :this.nextShipmentStates.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.shipmentInvoiceLedgerPayoutEntriesFlag == null)? 0 :this.shipmentInvoiceLedgerPayoutEntriesFlag.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        result = ((result* 31)+((this.shipmentInvoiceDetails == null)? 0 :this.shipmentInvoiceDetails.hashCode()));
        result = ((result* 31)+((this.plannedDispatchDate == null)? 0 :this.plannedDispatchDate.hashCode()));
        result = ((result* 31)+((this.sellerOrderId == null)? 0 :this.sellerOrderId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipmentDetails) == false) {
            return false;
        }
        ShipmentDetails rhs = ((ShipmentDetails) other);
        return (((((((((((((((((this.vehicleDetails == rhs.vehicleDetails)||((this.vehicleDetails!= null)&&this.vehicleDetails.equals(rhs.vehicleDetails)))&&((this.customerOrderId == rhs.customerOrderId)||((this.customerOrderId!= null)&&this.customerOrderId.equals(rhs.customerOrderId))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.modeOfShipment == rhs.modeOfShipment)||((this.modeOfShipment!= null)&&this.modeOfShipment.equals(rhs.modeOfShipment))))&&((this.customerOrderNumber == rhs.customerOrderNumber)||((this.customerOrderNumber!= null)&&this.customerOrderNumber.equals(rhs.customerOrderNumber))))&&((this.sellerOrderNumber == rhs.sellerOrderNumber)||((this.sellerOrderNumber!= null)&&this.sellerOrderNumber.equals(rhs.sellerOrderNumber))))&&((this.number == rhs.number)||((this.number!= null)&&this.number.equals(rhs.number))))&&((this.lineItemsDetails == rhs.lineItemsDetails)||((this.lineItemsDetails!= null)&&this.lineItemsDetails.equals(rhs.lineItemsDetails))))&&((this.nextShipmentStates == rhs.nextShipmentStates)||((this.nextShipmentStates!= null)&&this.nextShipmentStates.equals(rhs.nextShipmentStates))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.shipmentInvoiceLedgerPayoutEntriesFlag == rhs.shipmentInvoiceLedgerPayoutEntriesFlag)||((this.shipmentInvoiceLedgerPayoutEntriesFlag!= null)&&this.shipmentInvoiceLedgerPayoutEntriesFlag.equals(rhs.shipmentInvoiceLedgerPayoutEntriesFlag))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))))&&((this.shipmentInvoiceDetails == rhs.shipmentInvoiceDetails)||((this.shipmentInvoiceDetails!= null)&&this.shipmentInvoiceDetails.equals(rhs.shipmentInvoiceDetails))))&&((this.plannedDispatchDate == rhs.plannedDispatchDate)||((this.plannedDispatchDate!= null)&&this.plannedDispatchDate.equals(rhs.plannedDispatchDate))))&&((this.sellerOrderId == rhs.sellerOrderId)||((this.sellerOrderId!= null)&&this.sellerOrderId.equals(rhs.sellerOrderId))));
    }

}
