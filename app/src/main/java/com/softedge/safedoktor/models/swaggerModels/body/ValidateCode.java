package com.softedge.safedoktor.models.swaggerModels.body;

/**
 * Created by nab on 8/18/17.
 */

public class ValidateCode {
    private String code;
    private String mobilephone;

    public ValidateCode(String code, String mobilephone) {
        this.code = code;
        this.mobilephone = mobilephone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
}
