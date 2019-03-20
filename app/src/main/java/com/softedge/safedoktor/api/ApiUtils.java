package com.softedge.safedoktor.api;

public class ApiUtils {

    private static final String authrl = "{ip_address:port}/auth/realms/carewex/protocol/openid-connect/token";

    private static final String restURL = "{ip_address:port}/carewex/rest";

    public tokenUtils getToken() {
        return RetroAppClient.getRetroClient(authrl).create(tokenUtils.class);
    }

    public carewexClient getData() {
        return RetroAppClient.getRetroClient(restURL).create(carewexClient.class);
    }
}
