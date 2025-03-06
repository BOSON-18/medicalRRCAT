package com.medical.rrcat.model;

import java.util.Date;

public class PrescriptionModel {

    private int pk_claim;
    private Date pres_date;
    private String pres_dr;
    private int dr_ccno;

    public PrescriptionModel(int pk_claim, Date pres_date, String pres_dr, int dr_ccno) {
        this.pk_claim = pk_claim;
        this.pres_date = pres_date;
        this.pres_dr = pres_dr;
        this.dr_ccno = dr_ccno;
    }

    public int getPk_claim() {
        return pk_claim;
    }

    public void setPk_claim(int pk_claim) {
        this.pk_claim = pk_claim;
    }

    public Date getPres_date() {
        return pres_date;
    }

    public void setPres_date(Date pres_date) {
        this.pres_date = pres_date;
    }

    public String getPres_dr() {
        return pres_dr;
    }

    public void setPres_dr(String pres_dr) {
        this.pres_dr = pres_dr;
    }

    public int getDr_ccno() {
        return dr_ccno;
    }

    public void setDr_ccno(int dr_ccno) {
        this.dr_ccno = dr_ccno;
    }
}
