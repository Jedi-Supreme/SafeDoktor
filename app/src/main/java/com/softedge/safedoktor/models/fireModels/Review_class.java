package com.softedge.safedoktor.models.fireModels;

import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;

public class Review_class {

    public static final String TABLE = "REVIEWS";
    public static final String ID = Biography.ID;
    public static final String COMMENT = "comment";
    public static final String RATING = "rating";
    public static final String QNS_NUMBER = "qns_number";
    public static final String DOCTOR_ID = "doctor_id";

    private String comment;
    private int qn_number;
    private int review_rating;
    private String doctor_id;

    public Review_class(String doctor_id, int qn_number, int review_rating, String comment) {
        this.comment = comment;
        this.qn_number = qn_number;
        this.review_rating = review_rating;
        this.doctor_id = doctor_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getReview_rating() {
        return review_rating;
    }

    public void setReview_rating(int review_rating) {
        this.review_rating = review_rating;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getQn_number() {
        return qn_number;
    }

    public void setQn_number(int qn_number) {
        this.qn_number = qn_number;
    }
}
