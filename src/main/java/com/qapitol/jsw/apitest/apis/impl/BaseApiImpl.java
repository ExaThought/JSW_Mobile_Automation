package com.qapitol.jsw.apitest.apis.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.api.client.ApiClientFactory;
import com.qapitol.sauron.api.providers.LoginService;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.data.ContextData;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;


import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;

public abstract class BaseApiImpl {

    protected final SimpleLogger log = SauronLogger.getLogger();
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected ApiClientFactory ccpUrl() {
        return withCurrentHeaders(ApiClientFactory.getInstance(ConfigUtil.getSecretValue("ccpUrl")));
    }

    /**
     * Recomputes the factory's auth headers from the LoginService providers.
     *
     * <p>Sauron computes them only when it first builds the factory for an address, and caches the
     * factory per thread, so every later call on that thread sent the first access_token it ever
     * got - build 280 sent one token for three hours and every cart call came back 401. Token
     * caches the token itself, so recomputing per call costs no extra login.
     */
    private static ApiClientFactory withCurrentHeaders(ApiClientFactory factory) {
        Map<String, String> headers = new HashMap<>();
        for (LoginService loginService : ServiceLoader.load(LoginService.class)) {
            headers.putAll(loginService.login());
            headers.putAll(loginService.login(factory));
        }
        factory.getJaxrsClientFactoryBean().setHeaders(headers);
        return factory;
    }
    protected ApiClientFactory appUrl(){
        return ApiClientFactory.getInstance(ConfigUtil.getSecretValue("appBaseUrl"));
    }
    /**
     * Central-cart internal API.
     *
     * <p>resolveOrThrow, not getSecretValue: the property is not configured on every environment,
     * and passing null to getInstance produced an unrelated failure much later. This names the
     * missing property at the point of use instead.
     */
    protected ApiClientFactory centralCartCcpUrl() {
        return ApiClientFactory.getInstance(ConfigUtil.resolveOrThrow("centralCartCcpUrl"));
    }

    /**
     * Client for the Salesforce -> CT sales-opp sync (data-sync/import/sales-opp), the ONLY
     * client that carries salesOppApiKey.
     *
     * <p>Returns a proxy rather than the factory on purpose. Sauron shares one factory per address
     * and per thread, and on pre-prod salesOppUrl is the same address as ccpUrl - so the key is
     * set on the factory only long enough to build this one proxy, and the factory's normal
     * headers are restored before returning. Every other client built from it, now or later,
     * never sees the key. The proxy keeps its own copy of the headers it was built with.
     *
     * <p>No key configured: the proxy uses the address's normal headers, as before.
     *
     * <p>Thread safety: the set-create-restore below is safe ONLY because Sauron keeps factories in
     * a ThreadLocal, so no other thread can build a client from this factory between the set and
     * the restore. If factories are ever shared across threads, this must move to a client of its
     * own (e.g. a separate JAXRSClientFactoryBean) or the key could leak into a concurrent call.
     */
    protected <T> T salesOppApi(Class<T> api) {
        ApiClientFactory factory = ApiClientFactory.getInstance(ConfigUtil.resolveOrThrow("salesOppUrl"));
        String salesOppApiKey = ConfigUtil.getSecretValue("salesOppApiKey");
        if (salesOppApiKey == null || salesOppApiKey.trim().isEmpty()) {
            return withCurrentHeaders(factory).create(api);
        }
        factory.getJaxrsClientFactoryBean().setHeaders(Map.of("X-API-KEY", salesOppApiKey));
        try {
            return factory.create(api);
        } finally {
            withCurrentHeaders(factory);
        }
    }

    /**
     * Experience-middleware base URL. Resolves from the `middlewareUrl` property, so the
     * same code hits preprod or qa depending on how the environment is configured:
     *   preprod -> https://experience-middleware.preprod.jswonemsme.com/
     *   qa      -> https://experience-middleware.qa.jswone.in/   (MIDDLEWARE_BASE_URL env var)
     */
    protected ApiClientFactory middlewareUrl() {
        return ApiClientFactory.getInstance(ConfigUtil.resolveOrThrow("middlewareUrl"));
    }
    protected ApiClientFactory getOmsUrl() {
        return ApiClientFactory.getInstance(Objects.requireNonNull(ConfigUtil.getSecretValue("omsBaseUrl")));
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
