package com.company.organization.utils;

public interface BaseURL {
    String BASE_URL = "/api/v1";
    String AUTH_URL = BASE_URL + "/auth";
    String ORGANIZATION_URL = BASE_URL + "/organization";
    String PRODUCT_URL = BASE_URL + "/product";
    String BRANCH_URL = BASE_URL + "/branch";
    String WAREHOUSE_URL = BASE_URL + "/warehouse";
    String REGISTER_URL = "/register";
    String ACTIVATE_URL = "/activate";
    String GET_ACCESS_TOKEN_URL = "/get/accessToken";
    String REFRESH_TOKEN_URL = "/refresh/token";
    String CODE_RESEND_URL = "/code/resend";
    String FORGOT_PASSWORD_URL = "/forget/password";
    String FORGOT_PASSWORD_ACTIVATE_URL = "/forget/password/activate";
    String CREATE_URL = "/create";
    String UPDATE_URL = "/update/id/{id:.*}";
    String DELETE_URL = "/delete/id/{id:.*}";
    String GET_URL = "/get/id/{id:.*}";
    String GET_ALL_URL = "/get/all";
    String GET_ALL_PAGEABLE_URL = "/get/all/fixed";

}
