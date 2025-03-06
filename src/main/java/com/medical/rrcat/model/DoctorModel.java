package com.medical.rrcat.model;

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

    public int getDr_ccno() {
        return dr_ccno;
    }

    public String getDr_code() {
        return dr_code;
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
}
