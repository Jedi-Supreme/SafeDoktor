package com.softedge.care_assist.models.retrofitModels;

public class token_ReqBody {

    public static final String GRANT_TYPE = "grant_type";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";

    private String username;
    private String password;
    private final String grant_type;
    private final String client_id;
    private final String client_secret;

    public token_ReqBody(String username, String password) {
        this.username = username;
        this.password = password;
        this.grant_type = "password";
        this.client_id = "carewex-web";
        this.client_secret = "41abae52-6490-4957-803c-49e2a5f75e78";
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }
}
