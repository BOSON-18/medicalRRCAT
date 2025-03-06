package com.medical.rrcat.service.config;

public class Query {

    private String getEmployee = "SELECT * FROM m_employee WHERE e_ccno=?";
    private String beneficiaryQuery = "SELECT * FROM m_beneficiaries WHERE ccno = ?";
    private String claimQuery = "SELECT * FROM m_claim WHERE emp_ccno = ?";


    public String getGetEmployee() {
        return getEmployee;
    }

    public String getBeneficiaryQuery() {
        return beneficiaryQuery;
    }

    public String getClaimQuery(){
        return claimQuery;
    }
}
