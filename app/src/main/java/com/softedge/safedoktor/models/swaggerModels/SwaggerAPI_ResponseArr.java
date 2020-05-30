package com.softedge.safedoktor.models.swaggerModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SwaggerAPI_ResponseArr<T> {
    @SerializedName("data")
    @Expose
    private T data;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
