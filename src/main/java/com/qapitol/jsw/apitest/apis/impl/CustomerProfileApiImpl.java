package com.qapitol.jsw.apitest.apis.impl;

import com.qapitol.jsw.apitest.apis.RequestApi;
import com.qapitol.jsw.apitest.pojos.order.customerProfile.CustomerProfileResponse;
import com.qapitol.jsw.utils.ConfigUtil;
import io.qameta.allure.Allure;

/**
 * GET {middlewareUrl}xp-mi/public/v3/customer-profile
 *
 * The base URL is environment driven, so the same call covers both:
 *   preprod -> https://experience-middleware.preprod.jswonemsme.com/xp-mi/public/v3/customer-profile
 *   qa      -> https://experience-middleware.qa.jswone.in/xp-mi/public/v3/customer-profile
 *
 * Auth is handled by LoginApiImpl, which matches the middleware base URL and injects
 * `access_token: <Token.getCCPToken()>` on every request through this client.
 */
public class CustomerProfileApiImpl extends BaseApiImpl {

    private static final String CUSTOMER_PROFILE_PATH = "xp-mi/public/v3/customer-profile";

    public CustomerProfileResponse getCustomerProfile() {
        String endpoint = ConfigUtil.resolveOrThrow("middlewareUrl") + CUSTOMER_PROFILE_PATH;
        log.info("Calling customer profile API: GET " + endpoint);

        RequestApi requestApi = middlewareUrl().create(RequestApi.class);
        CustomerProfileResponse response = requestApi.getCustomerProfile();

        log.info("Customer profile response: " + response);
        Allure.addAttachment("GET " + endpoint, String.valueOf(response));
        return response;
    }
}
