package com.medical.rrcat.service.config;

public class Query {

    private String getEmployee = "SELECT * FROM m_employee WHERE e_ccno=?";
    private String beneficiaryQuery = "SELECT * FROM m_beneficiaries WHERE ccno = ?";
    private String claimQuery = "SELECT * FROM m_claim WHERE emp_ccno = ?";

    private String insertClaim = "INSERT INTO m_claim (emp_title,emp_name,emp_desg,emp_divn,emp_secn,emp_lab,emp_group,emp_ccno,bene_name,chss_char,bene_rela,amt_claimed,amt_approved,insert_date,insert_ccno,insert_userid,insert_ip,curr_stat,doct_ccno,file_id) Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private String insertBill = "INSERT INTO t_bills (pk_claim,bill_date,store_name,insert_date) VALUES (?,?,?,?)";
    private String insertPrescriptions = "Insert INTO t_pres (pk_claim,pres_date,pres_agency,pres_doct) VALUES (?,?,?,?)";

    private String operationQuery = "SELECT oper_name FROM r_operations WHERE operCode= ?";
    private String setStageQuery = "INSERT INTO t_stages (pk_claim,dr_ccno,oper_name,insert_date,insert_by_ccno,insert_by_userid) VALUES(?,?,?,?,?,?)";

    public String getGetEmployee() {
        return getEmployee;
    }

    public String getBeneficiaryQuery() {
        return beneficiaryQuery;
    }

    public String getClaimQuery() {
        return claimQuery;
    }

    public String getInsertPrescriptions() {
        return insertPrescriptions;
    }

    public String getInsertBill() {
        return insertBill;
    }

    public String getInsertClaim() {
        return insertClaim;
    }

    public String getOperationQuery() {
        return operationQuery;
    }

    public String getSetStageQuery() {
        return setStageQuery;
    }
}
