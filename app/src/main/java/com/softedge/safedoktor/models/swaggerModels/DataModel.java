package com.softedge.safedoktor.models.swaggerModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel<T> {
    @SerializedName("content")
    @Expose
    private T content = null;

    public T getContent() {
        return content;
    }
}
