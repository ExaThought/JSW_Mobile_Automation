
package com.qapitol.jsw.apitest.pojos.order.customerDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "obj",
    "typeId"
})
@Generated("jsonschema2pojo")
public class Type {

    @JsonProperty("id")
    private String id;
    @JsonProperty("obj")
    private Object obj;
    @JsonProperty("typeId")
    private String typeId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("obj")
    public Object getObj() {
        return obj;
    }

    @JsonProperty("obj")
    public void setObj(Object obj) {
        this.obj = obj;
    }

    @JsonProperty("typeId")
    public String getTypeId() {
        return typeId;
    }

    @JsonProperty("typeId")
    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
        sb.append(Type.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("obj");
        sb.append('=');
        sb.append(((this.obj == null)?"<null>":this.obj));
        sb.append(',');
        sb.append("typeId");
        sb.append('=');
        sb.append(((this.typeId == null)?"<null>":this.typeId));
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
        result = ((result* 31)+((this.typeId == null)? 0 :this.typeId.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.obj == null)? 0 :this.obj.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Type) == false) {
            return false;
        }
        Type rhs = ((Type) other);
        return (((((this.typeId == rhs.typeId)||((this.typeId!= null)&&this.typeId.equals(rhs.typeId)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.obj == rhs.obj)||((this.obj!= null)&&this.obj.equals(rhs.obj))));
    }

}
