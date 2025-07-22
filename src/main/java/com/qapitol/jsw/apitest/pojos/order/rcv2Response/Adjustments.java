
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "advance",
    "credit",
    "tolerance"
})
@Generated("jsonschema2pojo")
public class Adjustments {

    @JsonProperty("advance")
    private Advance advance;
    @JsonProperty("credit")
    private Credit credit;
    @JsonProperty("tolerance")
    private Tolerance tolerance;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("advance")
    public Advance getAdvance() {
        return advance;
    }

    @JsonProperty("advance")
    public void setAdvance(Advance advance) {
        this.advance = advance;
    }

    @JsonProperty("credit")
    public Credit getCredit() {
        return credit;
    }

    @JsonProperty("credit")
    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    @JsonProperty("tolerance")
    public Tolerance getTolerance() {
        return tolerance;
    }

    @JsonProperty("tolerance")
    public void setTolerance(Tolerance tolerance) {
        this.tolerance = tolerance;
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
        sb.append(Adjustments.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("advance");
        sb.append('=');
        sb.append(((this.advance == null)?"<null>":this.advance));
        sb.append(',');
        sb.append("credit");
        sb.append('=');
        sb.append(((this.credit == null)?"<null>":this.credit));
        sb.append(',');
        sb.append("tolerance");
        sb.append('=');
        sb.append(((this.tolerance == null)?"<null>":this.tolerance));
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
        result = ((result* 31)+((this.credit == null)? 0 :this.credit.hashCode()));
        result = ((result* 31)+((this.tolerance == null)? 0 :this.tolerance.hashCode()));
        result = ((result* 31)+((this.advance == null)? 0 :this.advance.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Adjustments) == false) {
            return false;
        }
        Adjustments rhs = ((Adjustments) other);
        return (((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.credit == rhs.credit)||((this.credit!= null)&&this.credit.equals(rhs.credit))))&&((this.tolerance == rhs.tolerance)||((this.tolerance!= null)&&this.tolerance.equals(rhs.tolerance))))&&((this.advance == rhs.advance)||((this.advance!= null)&&this.advance.equals(rhs.advance))));
    }

}
