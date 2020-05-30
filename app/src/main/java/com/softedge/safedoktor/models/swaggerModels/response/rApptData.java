package com.softedge.safedoktor.models.swaggerModels.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.softedge.safedoktor.models.swaggerModels.Appointment;

import java.util.List;

public class rApptData {

    @SerializedName("content")
    @Expose
    public List<rAppt_Content> content = null;

    @SerializedName("last")
    @Expose
    public boolean last;

    @SerializedName("totalPages")
    @Expose
    public int totalPages;

    @SerializedName("totalElements")
    @Expose
    public int totalElements;

    @SerializedName("number")
    @Expose
    public int number;

    @SerializedName("sort")
    @Expose
    public Object sort;

    @SerializedName("size")
    @Expose
    public int size;

    @SerializedName("numberOfElements")
    @Expose
    public int numberOfElements;

    @SerializedName("first")
    @Expose
    public boolean first;


}
