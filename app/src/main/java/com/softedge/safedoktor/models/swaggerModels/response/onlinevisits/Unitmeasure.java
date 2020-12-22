
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Unitmeasure {

    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("isactive")
    @Expose
    private boolean isactive;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("presentation")
    @Expose
    private Presentation presentation;
    @SerializedName("presentationid")
    @Expose
    private int presentationid;
    @SerializedName("typeid")
    @Expose
    private int typeid;

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public int getPresentationid() {
        return presentationid;
    }

    public void setPresentationid(int presentationid) {
        this.presentationid = presentationid;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

}
