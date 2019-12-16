package com.softedge.safedoktor.models.retrofitModels;

public class employee_login {

    public static final String DefaultUID = "safe.doktor";
    public static final String DefaultPass = "safe.doktor@nchs_SE2019";

    private String username;
    private String password;

    public employee_login(String username, String password) {
        this.username = username;
        this.password = password;
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
}
