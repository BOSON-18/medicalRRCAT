package com.medical.rrcat.model;

public class OperationsModel {

    private int oper_code;
    private String oper_name;
    private String oper_desc;
    private boolean isActive;
    private int order_by;

    public int getOper_code() {
        return oper_code;
    }

    public String getOper_name() {
        return oper_name;
    }

    public String getOper_desc() {
        return oper_desc;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getOrder_by() {
        return order_by;
    }
}
