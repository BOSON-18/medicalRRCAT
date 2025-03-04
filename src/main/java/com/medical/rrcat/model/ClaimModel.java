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
    private int doct_ccno;
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
    private BigInteger file_id;
    private String insert_ip;
    private int insert_ccno;
    private String insert_userid;
    private PrescriptionModel[] prescriptions;
    private BillModel[] bills;
    // skipped agencies part rn



    
    
}
