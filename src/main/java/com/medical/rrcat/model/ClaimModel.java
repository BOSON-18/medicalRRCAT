/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.rrcat.model;

import java.math.BigInteger;

/**
 *
 * @author Deesh
 */
public class ClaimModel {
    
    private int pk_claim;
    private int amt_claimed;
    private int amt_approved;
    private  DoctorModel[] doctors;
    private String emp_title;
    private String emp_name;
    private String emp_desg;
    private String emp_divn;
    private String emp_secn;
    private String emp_lab;
    private String emp_group;
    private int emp_ccno;
    private String bene_name;
    private char chss_char;
    private String bene_rela;
    private String curr_stat;
    private String file_id;
    private String file_hash;
    private String insert_ip;
    private int insert_ccno;
    private String insert_userid;
    private PrescriptionModel[] prescriptions;
    private BillModel[] bills;
    private StageModel[] stages;

    public ClaimModel() {
    }

    public ClaimModel(String emp_title, int pk_claim, int amt_claimed, int amt_approved, DoctorModel[] doctors, String emp_name, String emp_desg, String emp_divn, String emp_secn, String emp_lab, String emp_group, int emp_ccno, String bene_name, char chss_char, String bene_rela, String curr_stat, String file_id, String insert_ip, int insert_ccno, String insert_userid, PrescriptionModel[] prescriptions, BillModel[] bills, StageModel[] stages) {
        this.emp_title = emp_title;
        this.pk_claim = pk_claim;
        this.amt_claimed = amt_claimed;
        this.amt_approved = amt_approved;
        this.doctors = doctors;
        this.emp_name = emp_name;
        this.emp_desg = emp_desg;
        this.emp_divn = emp_divn;
        this.emp_secn = emp_secn;
        this.emp_lab = emp_lab;
        this.emp_group = emp_group;
        this.emp_ccno = emp_ccno;
        this.bene_name = bene_name;
        this.chss_char = chss_char;
        this.bene_rela = bene_rela;
        this.curr_stat = curr_stat;
        this.file_id = file_id;
        this.insert_ip = insert_ip;
        this.insert_ccno = insert_ccno;
        this.insert_userid = insert_userid;
        this.prescriptions = prescriptions;
        this.bills = bills;
        this.stages = stages;
    }

    public String getFile_hash() {
        return file_hash;
    }

    public void setFile_hash(String file_hash) {
        this.file_hash = file_hash;
    }

    public int getAmt_claimed() {
        return amt_claimed;
    }

    public void setAmt_claimed(int amt_claimed) {
        this.amt_claimed = amt_claimed;
    }

    public int getPk_claim() {
        return pk_claim;
    }

    public void setPk_claim(int pk_claim) {
        this.pk_claim = pk_claim;
    }

    public int getAmt_approved() {
        return amt_approved;
    }

    public void setAmt_approved(int amt_approved) {
        this.amt_approved = amt_approved;
    }

    public DoctorModel[] getDoctors() {
        return doctors;
    }

    public void setDoctors(DoctorModel[] doctors) {
        this.doctors = doctors;
    }

    public String getEmp_title() {
        return emp_title;
    }

    public void setEmp_title(String emp_title) {
        this.emp_title = emp_title;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_desg() {
        return emp_desg;
    }

    public void setEmp_desg(String emp_desg) {
        this.emp_desg = emp_desg;
    }

    public String getEmp_divn() {
        return emp_divn;
    }

    public void setEmp_divn(String emp_divn) {
        this.emp_divn = emp_divn;
    }

    public String getEmp_secn() {
        return emp_secn;
    }

    public void setEmp_secn(String emp_secn) {
        this.emp_secn = emp_secn;
    }

    public String getEmp_lab() {
        return emp_lab;
    }

    public void setEmp_lab(String emp_lab) {
        this.emp_lab = emp_lab;
    }

    public String getEmp_group() {
        return emp_group;
    }

    public void setEmp_group(String emp_group) {
        this.emp_group = emp_group;
    }

    public int getEmp_ccno() {
        return emp_ccno;
    }

    public void setEmp_ccno(int emp_ccno) {
        this.emp_ccno = emp_ccno;
    }

    public String getBene_name() {
        return bene_name;
    }

    public void setBene_name(String bene_name) {
        this.bene_name = bene_name;
    }

    public char getChss_char() {
        return chss_char;
    }

    public void setChss_char(char chss_char) {
        this.chss_char = chss_char;
    }

    public String getBene_rela() {
        return bene_rela;
    }

    public void setBene_rela(String bene_rela) {
        this.bene_rela = bene_rela;
    }

    public String getCurr_stat() {
        return curr_stat;
    }

    public void setCurr_stat(String curr_stat) {
        this.curr_stat = curr_stat;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getInsert_ip() {
        return insert_ip;
    }

    public void setInsert_ip(String insert_ip) {
        this.insert_ip = insert_ip;
    }

    public int getInsert_ccno() {
        return insert_ccno;
    }

    public void setInsert_ccno(int insert_ccno) {
        this.insert_ccno = insert_ccno;
    }

    public String getInsert_userid() {
        return insert_userid;
    }

    public void setInsert_userid(String insert_userid) {
        this.insert_userid = insert_userid;
    }

    public PrescriptionModel[] getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(PrescriptionModel[] prescriptions) {
        this.prescriptions = prescriptions;
    }

    public BillModel[] getBills() {
        return bills;
    }

    public void setBills(BillModel[] bills) {
        this.bills = bills;
    }

    public StageModel[] getStages() {
        return stages;
    }

    public void setStages(StageModel[] stages) {
        this.stages = stages;
    }
}
