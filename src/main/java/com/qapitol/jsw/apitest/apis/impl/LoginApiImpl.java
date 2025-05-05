package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.sauron.api.client.ApiClientFactory;
import com.qapitol.sauron.api.providers.LoginService;
import com.qapitol.sauron.common.configuration.Config;

import java.util.Map;
import java.util.UUID;

public class LoginApiImpl implements LoginService {

    @Override
    public Map<String, String> login() {
        return Map.of();
    }

    @Override
    public Map<String, String> login(ApiClientFactory apiClientFactory) {
        String ccpUrl = Config.getConfigProperty("ccpUrl");
        String omsUrl = Config.getConfigProperty("omsUrl");
        String zohoUrl = Config.getConfigProperty("zohoUrl");
        String centralCartCcpUrl=Config.getConfigProperty("centralCartCcpUrl");
        if(ccpUrl.equalsIgnoreCase(apiClientFactory.getAddress())){
            String ACCESS_TOKEN = "access_token";
            return Map.of(ACCESS_TOKEN, Config.getConfigProperty("customerPortalAuthToken"));
        }
        if(centralCartCcpUrl.equalsIgnoreCase(apiClientFactory.getAddress())){
            String X_API_KEY = "X-API-KEY";
            String Request_ID="X-Request-ID";
            String ACCESS_TOKEN = "access_token";
            String requestId = UUID.randomUUID().toString();
            return Map.of(ACCESS_TOKEN, Config.getConfigProperty("customerPortalAuthToken"),
                    X_API_KEY, Config.getConfigProperty("XApiKey"),
                    Request_ID,requestId);
        }

        if (omsUrl.equalsIgnoreCase(apiClientFactory.getAddress())){
            String AUTHORIZATION = "Authorization";
            return Map.of(AUTHORIZATION, Config.getConfigProperty("omsAuthToken"));
        }
        if (zohoUrl.equalsIgnoreCase(apiClientFactory.getAddress())){
            String COOKIE = "Cookie";
            return Map.of(COOKIE, Config.getConfigProperty("zohoCookie"));
        }
        return Map.of();
    }
}
