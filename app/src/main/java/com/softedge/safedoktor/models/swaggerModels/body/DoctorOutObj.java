package com.softedge.safedoktor.models.swaggerModels.body;

public class DoctorOutObj{
    private Userphoto picture;
    private UserAccount doctor;
    private int slotcount;

    public int getSlotcount() {
        return slotcount;
    }

    public void setSlotcount(int slotcount) {
        this.slotcount = slotcount;
    }

    public Userphoto getPicture() {
        return picture;
    }

    public void setPicture(Userphoto picture) {
        this.picture = picture;
    }

    public UserAccount getDoctor() {
        return doctor;
    }

    public void setDoctor(UserAccount doctor) {
        this.doctor = doctor;
    }
}
