
package com.qapitol.jsw.apitest.pojos.order.ctCashAndCarryAdvRequestPayload;

import java.util.LinkedHashMap;
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
    "WIDTH_MM",
    "THICKNESS_MM",
    "LENGTH_MM"
})
@Generated("jsonschema2pojo")
public class Attributes {

    @JsonProperty("WIDTH_MM")
    private String widthMm;
    @JsonProperty("THICKNESS_MM")
    private String thicknessMm;
    @JsonProperty("LENGTH_MM")
    private String lengthMm;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("WIDTH_MM")
    public String getWidthMm() {
        return widthMm;
    }

    @JsonProperty("WIDTH_MM")
    public void setWidthMm(String widthMm) {
        this.widthMm = widthMm;
    }

    @JsonProperty("THICKNESS_MM")
    public String getThicknessMm() {
        return thicknessMm;
    }

    @JsonProperty("THICKNESS_MM")
    public void setThicknessMm(String thicknessMm) {
        this.thicknessMm = thicknessMm;
    }

    @JsonProperty("LENGTH_MM")
    public String getLengthMm() {
        return lengthMm;
    }

    @JsonProperty("LENGTH_MM")
    public void setLengthMm(String lengthMm) {
        this.lengthMm = lengthMm;
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
        sb.append(Attributes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("widthMm");
        sb.append('=');
        sb.append(((this.widthMm == null)?"<null>":this.widthMm));
        sb.append(',');
        sb.append("thicknessMm");
        sb.append('=');
        sb.append(((this.thicknessMm == null)?"<null>":this.thicknessMm));
        sb.append(',');
        sb.append("lengthMm");
        sb.append('=');
        sb.append(((this.lengthMm == null)?"<null>":this.lengthMm));
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
        result = ((result* 31)+((this.thicknessMm == null)? 0 :this.thicknessMm.hashCode()));
        result = ((result* 31)+((this.lengthMm == null)? 0 :this.lengthMm.hashCode()));
        result = ((result* 31)+((this.widthMm == null)? 0 :this.widthMm.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Attributes) == false) {
            return false;
        }
        Attributes rhs = ((Attributes) other);
        return (((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.thicknessMm == rhs.thicknessMm)||((this.thicknessMm!= null)&&this.thicknessMm.equals(rhs.thicknessMm))))&&((this.lengthMm == rhs.lengthMm)||((this.lengthMm!= null)&&this.lengthMm.equals(rhs.lengthMm))))&&((this.widthMm == rhs.widthMm)||((this.widthMm!= null)&&this.widthMm.equals(rhs.widthMm))));
    }

}
