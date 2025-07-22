
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "errors",
    "warnings",
    "messages"
})
@Generated("jsonschema2pojo")
public class SummaryComments {

    @JsonProperty("errors")
    private Object errors;
    @JsonProperty("warnings")
    private Object warnings;
    @JsonProperty("messages")
    private List<String> messages = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("errors")
    public Object getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(Object errors) {
        this.errors = errors;
    }

    @JsonProperty("warnings")
    public Object getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(Object warnings) {
        this.warnings = warnings;
    }

    @JsonProperty("messages")
    public List<String> getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<String> messages) {
        this.messages = messages;
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
        sb.append(SummaryComments.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("errors");
        sb.append('=');
        sb.append(((this.errors == null)?"<null>":this.errors));
        sb.append(',');
        sb.append("warnings");
        sb.append('=');
        sb.append(((this.warnings == null)?"<null>":this.warnings));
        sb.append(',');
        sb.append("messages");
        sb.append('=');
        sb.append(((this.messages == null)?"<null>":this.messages));
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
        result = ((result* 31)+((this.messages == null)? 0 :this.messages.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.errors == null)? 0 :this.errors.hashCode()));
        result = ((result* 31)+((this.warnings == null)? 0 :this.warnings.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SummaryComments) == false) {
            return false;
        }
        SummaryComments rhs = ((SummaryComments) other);
        return (((((this.messages == rhs.messages)||((this.messages!= null)&&this.messages.equals(rhs.messages)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.errors == rhs.errors)||((this.errors!= null)&&this.errors.equals(rhs.errors))))&&((this.warnings == rhs.warnings)||((this.warnings!= null)&&this.warnings.equals(rhs.warnings))));
    }

}
