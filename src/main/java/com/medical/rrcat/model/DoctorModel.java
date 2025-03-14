package com.medical.rrcat.model;

import java.util.ArrayList;
import java.util.Date;

public class DoctorModel {

    private int dr_ccno;
    private String dr_code;
    private int dr_order;
    private boolean isActive;
    private String dr_name;
    private Date from_date;
    private Date to_date;
    private Date insert_date;
    private boolean isIncharge;
    private boolean isRmc;

    private ArrayList<ClaimModel> pendingClaims;
    private ArrayList<ClaimModel> reviewedClaims;


    public int getDr_ccno() {
        return dr_ccno;
    }

    public String getDr_code() {
        return dr_code;
    }

    public ArrayList<ClaimModel> getReviewedClaims() {
        return reviewedClaims;
    }

    public void setReviewedClaims(ArrayList<ClaimModel> reviewedClaims) {
        this.reviewedClaims = reviewedClaims;
    }

    public ArrayList<ClaimModel> getPendingClaims() {
        return pendingClaims;
    }

    public void setPendingClaims(ArrayList<ClaimModel> pendingClaims) {
        this.pendingClaims = pendingClaims;
    }

    public int getDr_order() {
        return dr_order;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getDr_name() {
        return dr_name;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public Date getInsert_date() {
        return insert_date;
    }

    public boolean isIncharge() {
        return isIncharge;
    }

    public boolean isRmc() {
        return isRmc;
    }

    public void setDr_ccno(int dr_ccno) {
        this.dr_ccno = dr_ccno;
    }

    public void setDr_code(String dr_code) {
        this.dr_code = dr_code;
    }

    public void setDr_order(int dr_order) {
        this.dr_order = dr_order;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDr_name(String dr_name) {
        this.dr_name = dr_name;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }

    public void setIncharge(boolean incharge) {
        isIncharge = incharge;
    }

    public void setRmc(boolean rmc) {
        isRmc = rmc;
    }
}
