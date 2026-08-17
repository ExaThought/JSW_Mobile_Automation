
package com.qapitol.jsw.apitest.pojos.order.customerProfile;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Response POJO for GET {middlewareUrl}xp-mi/public/v3/customer-profile
 *
 * The envelope fields (success/status/message/data) are mapped explicitly; everything the
 * service adds later lands in {@code data} or {@code additionalProperties}, so a schema
 * change on the middleware side cannot fail deserialization and break the suite.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "success",
        "status",
        "message",
        "data"
})
public class CustomerProfileResponse {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private Map<String, Object> data = new LinkedHashMap<String, Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("data")
    public Map<String, Object> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * Reads a nested value out of {@code data} using a dotted path, e.g.
     * {@code getDataValue("customer.gstIn")} or {@code getDataValue("addresses.0.city")}.
     *
     * @return the resolved value, or {@code null} if any segment of the path is missing.
     */
    @JsonIgnore
    public Object getDataValue(String dottedPath) {
        if (dottedPath == null || dottedPath.isBlank() || data == null) {
            return null;
        }
        Object current = data;
        for (String segment : dottedPath.split("\\.")) {
            if (current instanceof Map) {
                current = ((Map<?, ?>) current).get(segment);
            } else if (current instanceof List) {
                try {
                    List<?> list = (List<?>) current;
                    int index = Integer.parseInt(segment);
                    current = (index >= 0 && index < list.size()) ? list.get(index) : null;
                } catch (NumberFormatException e) {
                    return null;
                }
            } else {
                return null;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * Convenience wrapper over {@link #getDataValue(String)} for string assertions.
     */
    @JsonIgnore
    public String getDataValueAsString(String dottedPath) {
        Object value = getDataValue(dottedPath);
        return (value == null) ? null : String.valueOf(value);
    }

    /**
     * Reads a boolean out of {@code data} by dotted path. Tolerates the service sending
     * either a JSON boolean or the strings "true"/"false".
     *
     * @return the boolean value, or {@code null} when the path is absent or not boolean-like.
     */
    @JsonIgnore
    public Boolean getDataValueAsBoolean(String dottedPath) {
        return toBoolean(getDataValue(dottedPath));
    }

    /**
     * Depth-first search for the first occurrence of {@code key} anywhere in {@code data},
     * regardless of how deeply the middleware nests it. Use when the exact path is unknown
     * or when the service moves the field between releases.
     *
     * @return the matching value, or {@code null} if the key is not present anywhere.
     */
    @JsonIgnore
    public Object findFirst(String key) {
        return findFirst(data, key);
    }

    /**
     * Boolean flavour of {@link #findFirst(String)}, e.g. {@code findFirstBoolean("isTransacting")}.
     */
    @JsonIgnore
    public Boolean findFirstBoolean(String key) {
        return toBoolean(findFirst(key));
    }

    private static Object findFirst(Object node, String key) {
        if (node instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) node;
            if (map.containsKey(key)) {
                return map.get(key);
            }
            for (Object child : map.values()) {
                Object hit = findFirst(child, key);
                if (hit != null) {
                    return hit;
                }
            }
        } else if (node instanceof List) {
            for (Object child : (List<?>) node) {
                Object hit = findFirst(child, key);
                if (hit != null) {
                    return hit;
                }
            }
        }
        return null;
    }

    private static Boolean toBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof String) {
            String text = ((String) value).trim();
            if ("true".equalsIgnoreCase(text)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(text)) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CustomerProfileResponse.class.getName())
                .append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("success").append('=').append(((this.success == null) ? "<null>" : this.success)).append(',');
        sb.append("status").append('=').append(((this.status == null) ? "<null>" : this.status)).append(',');
        sb.append("message").append('=').append(((this.message == null) ? "<null>" : this.message)).append(',');
        sb.append("data").append('=').append(((this.data == null) ? "<null>" : this.data)).append(',');
        sb.append("additionalProperties").append('=')
                .append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties)).append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
