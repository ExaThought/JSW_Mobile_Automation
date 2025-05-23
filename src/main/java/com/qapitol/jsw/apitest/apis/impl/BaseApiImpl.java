package com.qapitol.jsw.apitest.apis.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapitol.sauron.api.client.ApiClientFactory;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import com.qapitol.sauron.web.AbstractWebPage;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BaseApiImpl {

    protected final SimpleLogger log = SauronLogger.getLogger();
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected ApiClientFactory ccpUrl() {
        return ApiClientFactory.getInstance(Config.getConfigProperty("ccpUrl"));
    }
    protected ApiClientFactory centralCartCcpUrl() {
        return ApiClientFactory.getInstance(Config.getConfigProperty("centralCartCcpUrl"));
    }

    protected ApiClientFactory getZohoOMSApiClientFactory() {
        return ApiClientFactory.getInstance(Config.getConfigProperty("omsUrl"));
    }

    protected ApiClientFactory getZohoAPIClientFactory() {
        return ApiClientFactory.getInstance((Config.getConfigProperty("zohoUrl")));
    }

    public <T> T getPayloadAsPOJO(String payload, Class<T> t) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(payload, t);
    }

    public <T> T getPayloadJsonFileAsPOJO(String jsonFileName, Class<T> t) throws IOException {
        // Get the user.dir system property (current working directory)
        String userDir = System.getProperty("user.dir");

        // Construct the path to the JSON file
        Path path = Paths.get(userDir, "src", "test", "resources", "api", "request", jsonFileName + ".json");
        return objectMapper.readValue(path.toFile(), t);
    }

    public <T> T getResponseAsPojo(Response response, Class<T> t) {
        return response.readEntity(t);
    }

    public String getResponseAsString(Response response) {
        return response.readEntity(String.class);
    }

    public String getStringFromPojo(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }

    public <T> T getPojoFromJsonString(String jsonString, Class<T> t) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, t);
    }

    public <T> T getPojoFromContextData(String key, Class<T> t) {
        try {
            return getPojoFromJsonString((String) ContextData.getDataFromKey(key), t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void setPojoToContextData(String key, T t) {
        ContextData.addData(key, getStringFromPojo(t));
    }
}
