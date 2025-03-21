
package com.qapitol.jsw.apitest.pojos.order.createShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineitemId",
    "plannedDelivery"
})
@Generated("jsonschema2pojo")
public class LineItemsDetail {

    @JsonProperty("lineitemId")
    private Object lineitemId;
    @JsonProperty("plannedDelivery")
    private PlannedDelivery plannedDelivery;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("lineitemId")
    public Object getLineitemId() {
        return lineitemId;
    }

    @JsonProperty("lineitemId")
    public void setLineitemId(Object lineitemId) {
        this.lineitemId = lineitemId;
    }

    @JsonProperty("plannedDelivery")
    public PlannedDelivery getPlannedDelivery() {
        return plannedDelivery;
    }

    @JsonProperty("plannedDelivery")
    public void setPlannedDelivery(PlannedDelivery plannedDelivery) {
        this.plannedDelivery = plannedDelivery;
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
        sb.append(LineItemsDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lineitemId");
        sb.append('=');
        sb.append(((this.lineitemId == null)?"<null>":this.lineitemId));
        sb.append(',');
        sb.append("plannedDelivery");
        sb.append('=');
        sb.append(((this.plannedDelivery == null)?"<null>":this.plannedDelivery));
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
        result = ((result* 31)+((this.plannedDelivery == null)? 0 :this.plannedDelivery.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemsDetail) == false) {
            return false;
        }
        LineItemsDetail rhs = ((LineItemsDetail) other);
        return ((((this.lineitemId == rhs.lineitemId)||((this.lineitemId!= null)&&this.lineitemId.equals(rhs.lineitemId)))&&((this.plannedDelivery == rhs.plannedDelivery)||((this.plannedDelivery!= null)&&this.plannedDelivery.equals(rhs.plannedDelivery))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
