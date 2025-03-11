package com.medical.rrcat.model;

import java.util.Date;

public class StageModel {

    private int pk_claim;
    private int dr_ccno;
    private int oper_code;
    private String oper_name;
    private Date insert_date;
    private int inert_by_ccno;
    private String insert_by_userid;

    public StageModel(int pk_claim, int dr_ccno, int oper_code, Date insert_date, int inert_by_ccno, String insert_by_userid) {
        this.pk_claim = pk_claim;
        this.dr_ccno = dr_ccno;
        this.oper_code = oper_code;
        this.insert_date = insert_date;
        this.inert_by_ccno = inert_by_ccno;
        this.insert_by_userid = insert_by_userid;
    }

    public String getOper_name() {
        return oper_name;
    }

    public void setOper_name(String oper_name) {
        this.oper_name = oper_name;
    }

    public int getPk_claim() {
        return pk_claim;
    }

    public void setPk_claim(int pk_claim) {
        this.pk_claim = pk_claim;
    }

    public int getDr_ccno() {
        return dr_ccno;
    }

    public void setDr_ccno(int dr_ccno) {
        this.dr_ccno = dr_ccno;
    }

    public int getOper_code() {
        return oper_code;
    }

    public void setOper_code(int oper_code) {
        this.oper_code = oper_code;
    }

    public Date getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }

    public int getInert_by_ccno() {
        return inert_by_ccno;
    }

    public void setInert_by_ccno(int inert_by_ccno) {
        this.inert_by_ccno = inert_by_ccno;
    }

    public String getInsert_by_userid() {
        return insert_by_userid;
    }

    public void setInsert_by_userid(String insert_by_userid) {
        this.insert_by_userid = insert_by_userid;
    }
}
