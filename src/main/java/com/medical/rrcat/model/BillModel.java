package com.medical.rrcat.model;

import java.util.Date;

public class BillModel {

    private int pk_claim;
    private Date bill_date;
    private String store_name;
    private Date insert_date;

    public BillModel(int pk_claim, Date bill_date, String store_name, Date insert_date) {
        this.pk_claim = pk_claim;
        this.bill_date = bill_date;
        this.store_name = store_name;
        this.insert_date = insert_date;
    }

    public int getPk_claim() {
        return pk_claim;
    }

    public void setPk_claim(int pk_claim) {
        this.pk_claim = pk_claim;
    }

    public Date getBill_date() {
        return bill_date;
    }

    public void setBill_date(Date bill_date) {
        this.bill_date = bill_date;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Date getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }
}
