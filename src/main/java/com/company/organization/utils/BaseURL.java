package com.company.organization.utils;

public interface BaseURL {
    String BASE_URL = "/api/v1";
    String AUTH_URL = BASE_URL + "/auth";
    String REGISTER_URL = "/register";
    String ACTIVATE_URL = "/activate";
    String GET_ACCESS_TOKEN_URL = "/get/accessToken";
    String REFRESH_TOKEN_URL = "/refresh/token";
    String CODE_RESEND_URL = "/code/resend";
    String FORGOT_PASSWORD_URL = "/forget/password";
    String FORGOT_PASSWORD_ACTIVATE_URL = "/forget/password/activate";


}
