
package com.qapitol.jsw.apitest.pojos.order.createDO;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "psId",
    "lineItemId",
    "plannedQuantity",
    "measurementUnit",
    "releasedQuantity",
    "transactionStatus",
    "totalLineItemQuantity",
})
@Generated("jsonschema2pojo")
public class PsLineItemsDetail {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("psId")
    private Integer psId;
    @JsonProperty("lineItemId")
    private Integer lineItemId;
    @JsonProperty("plannedQuantity")
    private String plannedQuantity;
    @JsonProperty("measurementUnit")
    private String measurementUnit;
    @JsonProperty("totalLineItemQuantity")
    private Double totalLineItemQuantity;
    @JsonProperty("releasedQuantity")
    private Integer releasedQuantity;
    @JsonProperty("transactionStatus")
    private String transactionStatus;
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

    @JsonProperty("psId")
    public Integer getPsId() {
        return psId;
    }

    @JsonProperty("psId")
    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    @JsonProperty("lineItemId")
    public Integer getLineItemId() {
        return lineItemId;
    }

    @JsonProperty("lineItemId")
    public void setLineItemId(Integer lineItemId) {
        this.lineItemId = lineItemId;
    }

    @JsonProperty("plannedQuantity")
    public String getPlannedQuantity() {
        return plannedQuantity;
    }

    @JsonProperty("plannedQuantity")
    public void setPlannedQuantity(String plannedQuantity) {
        this.plannedQuantity = plannedQuantity;
    }

    @JsonProperty("measurementUnit")
    public String getMeasurementUnit() {
        return measurementUnit;
    }

    @JsonProperty("measurementUnit")
    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    @JsonProperty("totalLineItemQuantity")
    public Double getTotalLineItemQuantity() {
        return totalLineItemQuantity;
    }

    @JsonProperty("totalLineItemQuantity")
    public void setTotalLineItemQuantity(Double totalLineItemQuantity) {
        this.totalLineItemQuantity = totalLineItemQuantity;
    }

    @JsonProperty("releasedQuantity")
    public Integer getReleasedQuantity() {
        return releasedQuantity;
    }

    @JsonProperty("releasedQuantity")
    public void setReleasedQuantity(Integer releasedQuantity) {
        this.releasedQuantity = releasedQuantity;
    }

    @JsonProperty("transactionStatus")
    public String getTransactionStatus() {
        return transactionStatus;
    }

    @JsonProperty("transactionStatus")
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
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
        sb.append(PsLineItemsDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("psId");
        sb.append('=');
        sb.append(((this.psId == null)?"<null>":this.psId));
        sb.append(',');
        sb.append("lineItemId");
        sb.append('=');
        sb.append(((this.lineItemId == null)?"<null>":this.lineItemId));
        sb.append(',');
        sb.append("plannedQuantity");
        sb.append('=');
        sb.append(((this.plannedQuantity == null)?"<null>":this.plannedQuantity));
        sb.append(',');
        sb.append("measurementUnit");
        sb.append('=');
        sb.append(((this.measurementUnit == null)?"<null>":this.measurementUnit));
        sb.append(',');
        sb.append("totalLineItemQuantity");
        sb.append('=');
        sb.append(((this.totalLineItemQuantity == null)?"<null>":this.totalLineItemQuantity));
        sb.append(',');
        sb.append("releasedQuantity");
        sb.append('=');
        sb.append(((this.releasedQuantity == null)?"<null>":this.releasedQuantity));
        sb.append(',');
        sb.append("transactionStatus");
        sb.append('=');
        sb.append(((this.transactionStatus == null)?"<null>":this.transactionStatus));
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
        result = ((result* 31)+((this.releasedQuantity == null)? 0 :this.releasedQuantity.hashCode()));
        result = ((result* 31)+((this.lineItemId == null)? 0 :this.lineItemId.hashCode()));
        result = ((result* 31)+((this.totalLineItemQuantity == null)? 0 :this.totalLineItemQuantity.hashCode()));
        result = ((result* 31)+((this.transactionStatus == null)? 0 :this.transactionStatus.hashCode()));
        result = ((result* 31)+((this.plannedQuantity == null)? 0 :this.plannedQuantity.hashCode()));
        result = ((result* 31)+((this.psId == null)? 0 :this.psId.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.measurementUnit == null)? 0 :this.measurementUnit.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PsLineItemsDetail) == false) {
            return false;
        }
        PsLineItemsDetail rhs = ((PsLineItemsDetail) other);
        return (((((((((this.releasedQuantity == rhs.releasedQuantity)||((this.releasedQuantity!= null)&&this.releasedQuantity.equals(rhs.releasedQuantity)))&&((this.lineItemId == rhs.lineItemId)||((this.lineItemId!= null)&&this.lineItemId.equals(rhs.lineItemId))))&&((this.totalLineItemQuantity == rhs.totalLineItemQuantity)||((this.totalLineItemQuantity!= null)&&this.totalLineItemQuantity.equals(rhs.totalLineItemQuantity)))&&((this.transactionStatus == rhs.transactionStatus)||((this.transactionStatus!= null)&&this.transactionStatus.equals(rhs.transactionStatus))))&&((this.plannedQuantity == rhs.plannedQuantity)||((this.plannedQuantity!= null)&&this.plannedQuantity.equals(rhs.plannedQuantity))))&&((this.psId == rhs.psId)||((this.psId!= null)&&this.psId.equals(rhs.psId))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.measurementUnit == rhs.measurementUnit)||((this.measurementUnit!= null)&&this.measurementUnit.equals(rhs.measurementUnit))));
    }

}
