
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ctLineItemId",
    "sapLineItemId"
})
@Generated("jsonschema2pojo")
public class LineItemIdentifier {

    @JsonProperty("ctLineItemId")
    private String ctLineItemId;
    @JsonProperty("sapLineItemId")
    private String sapLineItemId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("ctLineItemId")
    public String getCtLineItemId() {
        return ctLineItemId;
    }

    @JsonProperty("ctLineItemId")
    public void setCtLineItemId(String ctLineItemId) {
        this.ctLineItemId = ctLineItemId;
    }

    @JsonProperty("sapLineItemId")
    public String getSapLineItemId() {
        return sapLineItemId;
    }

    @JsonProperty("sapLineItemId")
    public void setSapLineItemId(String sapLineItemId) {
        this.sapLineItemId = sapLineItemId;
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
        sb.append(LineItemIdentifier.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ctLineItemId");
        sb.append('=');
        sb.append(((this.ctLineItemId == null)?"<null>":this.ctLineItemId));
        sb.append(',');
        sb.append("sapLineItemId");
        sb.append('=');
        sb.append(((this.sapLineItemId == null)?"<null>":this.sapLineItemId));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.ctLineItemId == null)? 0 :this.ctLineItemId.hashCode()));
        result = ((result* 31)+((this.sapLineItemId == null)? 0 :this.sapLineItemId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemIdentifier) == false) {
            return false;
        }
        LineItemIdentifier rhs = ((LineItemIdentifier) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.ctLineItemId == rhs.ctLineItemId)||((this.ctLineItemId!= null)&&this.ctLineItemId.equals(rhs.ctLineItemId))))&&((this.sapLineItemId == rhs.sapLineItemId)||((this.sapLineItemId!= null)&&this.sapLineItemId.equals(rhs.sapLineItemId))));
    }

}
