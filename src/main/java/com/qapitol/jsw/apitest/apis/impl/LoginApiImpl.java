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

        // No sales-opp branch here, deliberately. These headers go on every client for an
        // address, and on pre-prod salesOppUrl IS ccpUrl - matching it here sent the sales-opp
        // X-API-KEY instead of access_token on every CCP call (build 280: 19 cart 401s). The key
        // is attached only by BaseApiImpl.salesOppApi(), to the sales-opp proxy alone.

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
