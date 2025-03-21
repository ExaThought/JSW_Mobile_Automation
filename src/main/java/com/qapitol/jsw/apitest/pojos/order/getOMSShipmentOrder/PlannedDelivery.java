
package com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "quantity",
    "amount",
    "shipmentId",
    "measurementId",
    "lineitemId"
})
@Generated("jsonschema2pojo")
public class PlannedDelivery {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("quantity")
    private Double quantity;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("shipmentId")
    private Integer shipmentId;
    @JsonProperty("measurementId")
    private Object measurementId;
    @JsonProperty("lineitemId")
    private Integer lineitemId;
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

    @JsonProperty("quantity")
    public Double getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("shipmentId")
    public Integer getShipmentId() {
        return shipmentId;
    }

    @JsonProperty("shipmentId")
    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    @JsonProperty("measurementId")
    public Object getMeasurementId() {
        return measurementId;
    }

    @JsonProperty("measurementId")
    public void setMeasurementId(Object measurementId) {
        this.measurementId = measurementId;
    }

    @JsonProperty("lineitemId")
    public Integer getLineitemId() {
        return lineitemId;
    }

    @JsonProperty("lineitemId")
    public void setLineitemId(Integer lineitemId) {
        this.lineitemId = lineitemId;
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
        sb.append(PlannedDelivery.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null)?"<null>":this.quantity));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("shipmentId");
        sb.append('=');
        sb.append(((this.shipmentId == null)?"<null>":this.shipmentId));
        sb.append(',');
        sb.append("measurementId");
        sb.append('=');
        sb.append(((this.measurementId == null)?"<null>":this.measurementId));
        sb.append(',');
        sb.append("lineitemId");
        sb.append('=');
        sb.append(((this.lineitemId == null)?"<null>":this.lineitemId));
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
        result = ((result* 31)+((this.lineitemId == null)? 0 :this.lineitemId.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.quantity == null)? 0 :this.quantity.hashCode()));
        result = ((result* 31)+((this.shipmentId == null)? 0 :this.shipmentId.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.measurementId == null)? 0 :this.measurementId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PlannedDelivery) == false) {
            return false;
        }
        PlannedDelivery rhs = ((PlannedDelivery) other);
        return ((((((((this.lineitemId == rhs.lineitemId)||((this.lineitemId!= null)&&this.lineitemId.equals(rhs.lineitemId)))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.quantity == rhs.quantity)||((this.quantity!= null)&&this.quantity.equals(rhs.quantity))))&&((this.shipmentId == rhs.shipmentId)||((this.shipmentId!= null)&&this.shipmentId.equals(rhs.shipmentId))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.measurementId == rhs.measurementId)||((this.measurementId!= null)&&this.measurementId.equals(rhs.measurementId))));
    }

}
