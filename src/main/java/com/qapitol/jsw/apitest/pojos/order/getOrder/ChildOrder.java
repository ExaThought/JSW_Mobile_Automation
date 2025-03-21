
package com.qapitol.jsw.apitest.pojos.order.getOrder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sellerOrderNumber",
    "sellerName",
    "currentState",
    "nextSellerOrderStates",
    "sellerKey"
})
@Generated("jsonschema2pojo")
public class ChildOrder {

    @JsonProperty("sellerOrderNumber")
    private String sellerOrderNumber;
    @JsonProperty("sellerName")
    private String sellerName;
    @JsonProperty("currentState")
    private String currentState;
    @JsonProperty("nextSellerOrderStates")
    private List<NextSellerOrderState> nextSellerOrderStates = new ArrayList<NextSellerOrderState>();
    @JsonProperty("sellerKey")
    private String sellerKey;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sellerOrderNumber")
    public String getSellerOrderNumber() {
        return sellerOrderNumber;
    }

    @JsonProperty("sellerOrderNumber")
    public void setSellerOrderNumber(String sellerOrderNumber) {
        this.sellerOrderNumber = sellerOrderNumber;
    }

    @JsonProperty("sellerName")
    public String getSellerName() {
        return sellerName;
    }

    @JsonProperty("sellerName")
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @JsonProperty("currentState")
    public String getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("nextSellerOrderStates")
    public List<NextSellerOrderState> getNextSellerOrderStates() {
        return nextSellerOrderStates;
    }

    @JsonProperty("nextSellerOrderStates")
    public void setNextSellerOrderStates(List<NextSellerOrderState> nextSellerOrderStates) {
        this.nextSellerOrderStates = nextSellerOrderStates;
    }

    @JsonProperty("sellerKey")
    public String getSellerKey() {
        return sellerKey;
    }

    @JsonProperty("sellerKey")
    public void setSellerKey(String sellerKey) {
        this.sellerKey = sellerKey;
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
        sb.append(ChildOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sellerOrderNumber");
        sb.append('=');
        sb.append(((this.sellerOrderNumber == null)?"<null>":this.sellerOrderNumber));
        sb.append(',');
        sb.append("sellerName");
        sb.append('=');
        sb.append(((this.sellerName == null)?"<null>":this.sellerName));
        sb.append(',');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
        sb.append(',');
        sb.append("nextSellerOrderStates");
        sb.append('=');
        sb.append(((this.nextSellerOrderStates == null)?"<null>":this.nextSellerOrderStates));
        sb.append(',');
        sb.append("sellerKey");
        sb.append('=');
        sb.append(((this.sellerKey == null)?"<null>":this.sellerKey));
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
        result = ((result* 31)+((this.sellerOrderNumber == null)? 0 :this.sellerOrderNumber.hashCode()));
        result = ((result* 31)+((this.nextSellerOrderStates == null)? 0 :this.nextSellerOrderStates.hashCode()));
        result = ((result* 31)+((this.sellerKey == null)? 0 :this.sellerKey.hashCode()));
        result = ((result* 31)+((this.sellerName == null)? 0 :this.sellerName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ChildOrder) == false) {
            return false;
        }
        ChildOrder rhs = ((ChildOrder) other);
        return (((((((this.sellerOrderNumber == rhs.sellerOrderNumber)||((this.sellerOrderNumber!= null)&&this.sellerOrderNumber.equals(rhs.sellerOrderNumber)))&&((this.nextSellerOrderStates == rhs.nextSellerOrderStates)||((this.nextSellerOrderStates!= null)&&this.nextSellerOrderStates.equals(rhs.nextSellerOrderStates))))&&((this.sellerKey == rhs.sellerKey)||((this.sellerKey!= null)&&this.sellerKey.equals(rhs.sellerKey))))&&((this.sellerName == rhs.sellerName)||((this.sellerName!= null)&&this.sellerName.equals(rhs.sellerName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))));
    }

}
