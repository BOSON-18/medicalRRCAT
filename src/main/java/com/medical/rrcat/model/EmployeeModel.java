package com.medical.rrcat.model;

import java.util.List;

public class EmployeeModel {
    
    private int e_ccno;
    private String e_title;
    private String e_name;
    private int e_desg_code;
    private String e_desg_desc;
    private String e_divn_code;
    private String e_divn_desc;
    private String e_secn_code;
    private String e_secn_desc;
    private String e_lab_code;
    private String e_lab_desc;
    private String e_grp_code;
    private String e_grp_desc;
    private List<BeneficiaryModel> e_beneficiaries; 
    private List<ClaimModel> e_claims;
    private String e_email;
    private String e_mobile;

    public EmployeeModel() {}

    public EmployeeModel(int e_ccno, String e_title, String e_name, int e_desg_code, String e_desg_desc, 
                         String e_divn_code, String e_divn_desc, String e_secn_code, String e_secn_desc,
                         String e_lab_code, String e_lab_desc, String e_grp_code, String e_grp_desc,
                         List<BeneficiaryModel> e_beneficiaries, List<ClaimModel> e_claims, 
                         String e_email, String e_mobile) {
        this.e_ccno = e_ccno;
        this.e_title = e_title;
        this.e_name = e_name;
        this.e_desg_code = e_desg_code;
        this.e_desg_desc = e_desg_desc;
        this.e_divn_code = e_divn_code;
        this.e_divn_desc = e_divn_desc;
        this.e_secn_code = e_secn_code;
        this.e_secn_desc = e_secn_desc;
        this.e_lab_code = e_lab_code;
        this.e_lab_desc = e_lab_desc;
        this.e_grp_code = e_grp_code;
        this.e_grp_desc = e_grp_desc;
        this.e_beneficiaries = e_beneficiaries;
        this.e_claims = e_claims;
        this.e_email = e_email;
        this.e_mobile = e_mobile;
    }

    public int getE_ccno() {
        return e_ccno;
    }

    public void setE_ccno(int e_ccno) {
        this.e_ccno = e_ccno;
    }

    public String getE_title() {
        return e_title;
    }

    public void setE_title(String e_title) {
        this.e_title = e_title;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public int getE_desg_code() {
        return e_desg_code;
    }

    public void setE_desg_code(int e_desg_code) {
        this.e_desg_code = e_desg_code;
    }

    public String getE_desg_desc() {
        return e_desg_desc;
    }

    public void setE_desg_desc(String e_desg_desc) {
        this.e_desg_desc = e_desg_desc;
    }

    public String getE_divn_code() {
        return e_divn_code;
    }

    public void setE_divn_code(String e_divn_code) {
        this.e_divn_code = e_divn_code;
    }

    public String getE_divn_desc() {
        return e_divn_desc;
    }

    public void setE_divn_desc(String e_divn_desc) {
        this.e_divn_desc = e_divn_desc;
    }

    public String getE_secn_code() {
        return e_secn_code;
    }

    public void setE_secn_code(String e_secn_code) {
        this.e_secn_code = e_secn_code;
    }

    public String getE_secn_desc() {
        return e_secn_desc;
    }

    public void setE_secn_desc(String e_secn_desc) {
        this.e_secn_desc = e_secn_desc;
    }

    public String getE_lab_code() {
        return e_lab_code;
    }

    public void setE_lab_code(String e_lab_code) {
        this.e_lab_code = e_lab_code;
    }

    public String getE_lab_desc() {
        return e_lab_desc;
    }

    public void setE_lab_desc(String e_lab_desc) {
        this.e_lab_desc = e_lab_desc;
    }

    public String getE_grp_code() {
        return e_grp_code;
    }

    public void setE_grp_code(String e_grp_code) {
        this.e_grp_code = e_grp_code;
    }

    public String getE_grp_desc() {
        return e_grp_desc;
    }

    public void setE_grp_desc(String e_grp_desc) {
        this.e_grp_desc = e_grp_desc;
    }

    public List<BeneficiaryModel> getE_beneficiaries() {
        return e_beneficiaries;
    }

    public void setE_beneficiaries(List<BeneficiaryModel> e_beneficiaries) {
        this.e_beneficiaries = e_beneficiaries;
    }

    public List<ClaimModel> getE_claims() {
        return e_claims;
    }

    public void setE_claims(List<ClaimModel> e_claims) {
        this.e_claims = e_claims;
    }

    public String getE_email() {
        return e_email;
    }

    public void setE_email(String e_email) {
        this.e_email = e_email;
    }

    public String getE_mobile() {
        return e_mobile;
    }

    public void setE_mobile(String e_mobile) {
        this.e_mobile = e_mobile;
    }
}
