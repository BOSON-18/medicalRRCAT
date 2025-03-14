package com.medical.rrcat.service.config;

public class Query {

    private String getEmployee = "SELECT * FROM m_employee WHERE e_ccno=?";
    private String beneficiaryQuery = "SELECT * FROM m_beneficiaries WHERE ccno = ?";
    private String claimQuery = "SELECT * FROM m_claim WHERE emp_ccno = ?";

    private String insertClaim = "INSERT INTO m_claim (emp_title,emp_name,emp_desg,emp_divn,emp_secn,emp_lab,emp_group,emp_ccno,bene_name,chss_char,bene_rela,amt_claimed,amt_approved,insert_date,insert_ccno,insert_userid,insert_ip,curr_stat,doct_ccno,file_id,file_hash) Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String editClaimQuery = "UPDATE m_claim SET bene_name = ?, chss_char = ?, bene_rela = ?, amt_claimed = ?, "
            + "insert_date = ?, insert_ip = ?, doct_ccno = ?, file_id = ?, file_hash = ? "
            + "WHERE pk_claim = ?";
    private String insertBill = "INSERT INTO t_bills (pk_claim,bill_date,store_name,insert_date) VALUES (?,?,?,?)";
    private String insertPrescriptions = "Insert INTO t_pres (pk_claim,pres_date,pres_agency,pres_doct) VALUES (?,?,?,?)";
    private String updateBill = "UPDATE bills SET bill_date = ?, store_name = ?, insert_date = ? WHERE pk_claim = ?;";
    private String updatePrescription = "UPDATE c_pres SET pres_date = ?, pres_dr = ? , pres_dr_ccno = ? WHERE pk_claim = ?";


    private String operationQuery = "SELECT oper_name FROM r_operations WHERE operCode= ?";
    private String setStageQuery = "INSERT INTO t_stages (pk_claim,dr_ccno,op_code,op_name,insert_date,insert_by_ccno,insert_by_userid) VALUES(?,?,?,?,?,?,?)";

    private String updateStageQuery = "UPDATE c_stage SET dr_ccno=?,op_code=?,op_name=?,insert_date=? WHERE c_id = ? ";


    private String doctorQuery = "SELECT * FROM m_doctor WHERE dr_ccno = ?";
    private String claimQueryDoctor =" SELECT * FROM m_claim where c_id = ?";
    private String doctorClaimQuery =  "SELECT cs.*, mc.* FROM c_stage cs " +
            "JOIN m_claim mc ON cs.c_id = mc.c_id " +
            "WHERE cs.dr_ccno = ? " +
            "ORDER BY cs.insert_date DESC, cs.c_id " +
            "LIMIT 100 OFFSET ?";

    private String doctorOperation = "";

    public String getDoctorOperation() {
        return doctorOperation;
    }

    public String getDoctorClaimQuery() {
        return doctorClaimQuery;
    }

    public String getClaimQueryDoctor() {
        return claimQueryDoctor;
    }

    public String getDoctorQuery() {
        return doctorQuery;
    }

    public String getUpdateStageQuery() {
        return updateStageQuery;
    }

    public String getEditClaimQuery() {
        return editClaimQuery;
    }

    public String getUpdateBill() {
        return updateBill;
    }

    public String getUpdatePrescription() {
        return updatePrescription;
    }


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
