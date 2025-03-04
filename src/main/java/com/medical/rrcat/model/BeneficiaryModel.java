package com.medical.rrcat.model;

import java.util.Date;

public class BeneficiaryModel {
    
    private int ccno;
    private char chss_char;
    private int rela_code;
    private String bene_title;
    private String bene_name;
    private char bene_sex;
    private Date bene_dob;
    private String bene_aadhar;
    private String bene_bld_grp;
    private char bene_rh_fact;
    private Date active_from;
    private Date active_to;

    public BeneficiaryModel() {}

    public BeneficiaryModel(int ccno, char chss_char, int rela_code, String bene_title, String bene_name, 
                            char bene_sex, Date bene_dob, String bene_aadhar, String bene_bld_grp, 
                            char bene_rh_fact, Date active_from, Date active_to) {
        this.ccno = ccno;
        this.chss_char = chss_char;
        this.rela_code = rela_code;
        this.bene_title = bene_title;
        this.bene_name = bene_name;
        this.bene_sex = bene_sex;
        this.bene_dob = bene_dob;
        this.bene_aadhar = bene_aadhar;
        this.bene_bld_grp = bene_bld_grp;
        this.bene_rh_fact = bene_rh_fact;
        this.active_from = active_from;
        this.active_to = active_to;
    }

    public int getCcno() {
        return ccno;
    }

    public void setCcno(int ccno) {
        this.ccno = ccno;
    }

    public char getChss_char() {
        return chss_char;
    }

    public void setChss_char(char chss_char) {
        this.chss_char = chss_char;
    }

    public int getRela_code() {
        return rela_code;
    }

    public void setRela_code(int rela_code) {
        this.rela_code = rela_code;
    }

    public String getBene_title() {
        return bene_title;
    }

    public void setBene_title(String bene_title) {
        this.bene_title = bene_title;
    }

    public String getBene_name() {
        return bene_name;
    }

    public void setBene_name(String bene_name) {
        this.bene_name = bene_name;
    }

    public char getBene_sex() {
        return bene_sex;
    }

    public void setBene_sex(char bene_sex) {
        this.bene_sex = bene_sex;
    }

    public Date getBene_dob() {
        return bene_dob;
    }

    public void setBene_dob(Date bene_dob) {
        this.bene_dob = bene_dob;
    }

    public String getBene_aadhar() {
        return bene_aadhar;
    }

    public void setBene_aadhar(String bene_aadhar) {
        this.bene_aadhar = bene_aadhar;
    }

    public String getBene_bld_grp() {
        return bene_bld_grp;
    }

    public void setBene_bld_grp(String bene_bld_grp) {
        this.bene_bld_grp = bene_bld_grp;
    }

    public char getBene_rh_fact() {
        return bene_rh_fact;
    }

    public void setBene_rh_fact(char bene_rh_fact) {
        this.bene_rh_fact = bene_rh_fact;
    }

    public Date getActive_from() {
        return active_from;
    }

    public void setActive_from(Date active_from) {
        this.active_from = active_from;
    }

    public Date getActive_to() {
        return active_to;
    }

    public void setActive_to(Date active_to) {
        this.active_to = active_to;
    }
}
