package com.softedge.safedoktor.models.swaggerModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class rSwaggerAPI<T> {

    @SerializedName("data")
    @Expose
    private DataModel<T> data;

    public DataModel<T> getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }
}
