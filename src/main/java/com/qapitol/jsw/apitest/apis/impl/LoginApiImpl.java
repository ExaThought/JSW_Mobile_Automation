package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.jsw.utils.Token;
import com.qapitol.sauron.api.client.ApiClientFactory;
import com.qapitol.sauron.api.providers.LoginService;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class LoginApiImpl implements LoginService {

    @Override
    public Map<String, String> login() {
        return Map.of();
    }

    @Override
    public Map<String, String> login(ApiClientFactory apiClientFactory) {
        String ccpUrl = Objects.requireNonNull(ConfigUtil.getSecretValue("ccpUrl"));
        String omsUrl = Objects.requireNonNull(ConfigUtil.getSecretValue("omsUrl"));
        String centralCartCcpUrl = ConfigUtil.getSecretValue("centralCartCcpUrl");

        // Salesforce -> CT sales-opp sync. Authenticated with a service key, not a user token,
        // so it is matched BEFORE the ccpUrl branch below - on environments where salesOppUrl
        // and ccpUrl are the same address, only the presence of a key separates the two.
        // No key configured => fall through and keep the historical access_token behaviour.
        String salesOppUrl = ConfigUtil.getSecretValue("salesOppUrl");
        String salesOppApiKey = ConfigUtil.getSecretValue("salesOppApiKey");
        if (salesOppUrl != null
                && salesOppUrl.equalsIgnoreCase(apiClientFactory.getAddress())
                && salesOppApiKey != null
                && !salesOppApiKey.trim().isEmpty()) {
            String X_API_KEY = "X-API-KEY";
            return Map.of(X_API_KEY, salesOppApiKey);
        }

        if (ccpUrl.equalsIgnoreCase(apiClientFactory.getAddress())) {
            String ACCESS_TOKEN = "access_token";
            return Map.of(ACCESS_TOKEN, Token.getCCPToken());
        }
        // Null-checked: centralCartCcpUrl is not configured on every environment, and an
        // unguarded equalsIgnoreCase here made a missing property surface as a
        // NullPointerException on every API call that was not ccpUrl.
        if (centralCartCcpUrl != null && centralCartCcpUrl.equalsIgnoreCase(apiClientFactory.getAddress())) {
            String X_API_KEY = "X-API-KEY";
            String Request_ID = "X-Request-ID";
            String ACCESS_TOKEN = "access_token";
            String requestId = UUID.randomUUID().toString();
            return Map.of(ACCESS_TOKEN, Token.getCCPToken(),
                    X_API_KEY, ConfigUtil.getSecretValue("XApiKey"),
                    Request_ID, requestId);
        }
        if (omsUrl.equalsIgnoreCase(apiClientFactory.getAddress())) {
            String AUTHORIZATION = "Authorization";
            // Logs in via Token.getOmsToken() when the OMS login properties are configured,
            // otherwise falls back to the statically configured omsAuthToken.
            return Map.of(AUTHORIZATION, Token.getOmsAuthorizationHeader());
        }
        // Experience-middleware (xp-mi) - same access_token contract as CCP.
        String middlewareUrl = ConfigUtil.getSecretValue("middlewareUrl");
        if (middlewareUrl != null && middlewareUrl.equalsIgnoreCase(apiClientFactory.getAddress())) {
            String ACCESS_TOKEN = "access_token";
            return Map.of(ACCESS_TOKEN, Token.getCCPToken());
        }
        return Map.of();
    }
}
