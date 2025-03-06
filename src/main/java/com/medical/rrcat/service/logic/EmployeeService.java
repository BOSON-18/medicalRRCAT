package com.medical.rrcat.service.logic;

import com.medical.rrcat.model.*;

import com.medical.rrcat.service.config.PostgresDBUtil;
import com.medical.rrcat.service.config.Query;
import jakarta.mail.Multipart;
import jakarta.servlet.http.Part;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

        EmployeeModel employee = null;
        Query q = new Query();
    public EmployeeModel getEmployee(int ccno) {

        try {
            PostgresDBUtil pg = new PostgresDBUtil();
            Connection con = pg.connect();
            String query = q.getGetEmployee();

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, ccno);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                employee = new EmployeeModel();

                employee.setE_ccno(result.getInt("e_ccno"));
                employee.setE_title(result.getString("e_title"));
                employee.setE_name(result.getString("e_name"));
                employee.setE_desg_code(result.getInt("e_desg_code"));
                employee.setE_desg_desc(result.getString("e_desg_desc"));
                employee.setE_divn_code(result.getString("e_divn_code"));
                employee.setE_divn_desc(result.getString("e_divn_desc"));
                employee.setE_secn_code(result.getString("e_secn_code"));
                employee.setE_secn_desc(result.getString("e_secn_desc"));
                employee.setE_lab_code(result.getString("e_lab_code"));
                employee.setE_lab_desc(result.getString("e_lab_desc"));
                employee.setE_grp_code(result.getString("e_grp_code"));
                employee.setE_grp_desc(result.getString("e_grp_desc"));
                employee.setE_email(result.getString("e_email"));
                employee.setE_mobile(result.getString("e_mobile"));

                List<BeneficiaryModel> beneficiaries = new ArrayList<>();
                String beneficiaryQuery = q.getBeneficiaryQuery();
                PreparedStatement beneficiaryStmt = con.prepareStatement(beneficiaryQuery);
                beneficiaryStmt.setInt(1, ccno);
                ResultSet beneficiaryResult = beneficiaryStmt.executeQuery();

                while (beneficiaryResult.next()) {
                    BeneficiaryModel beneficiary = new BeneficiaryModel();
                    beneficiary.setCcno(beneficiaryResult.getInt("ccno"));
                    beneficiary.setChss_char(beneficiaryResult.getString("chss_char").charAt(0));
                    beneficiary.setRela_code(beneficiaryResult.getInt("rela_code"));
                    beneficiary.setBene_title(beneficiaryResult.getString("bene_title"));
                    beneficiary.setActive_from(beneficiaryResult.getDate("active_from"));
                    beneficiary.setBene_name(beneficiaryResult.getString("bene_name"));
                    beneficiary.setBene_sex(beneficiaryResult.getString("bene_sex").charAt(0));
                    beneficiary.setBene_dob(beneficiaryResult.getDate("bene_dob"));
                    beneficiary.setBene_aadhar(beneficiaryResult.getString("bene_aadhar"));
                    beneficiaries.add(beneficiary);
                }
                employee.setE_beneficiaries(beneficiaries);

                beneficiaryResult.close();
                beneficiaryStmt.close();

                List<ClaimModel> claims = new ArrayList<>();
                String claimQuery = q.getClaimQuery();
                PreparedStatement claimStmt = con.prepareStatement(claimQuery);
                claimStmt.setInt(1, ccno);
                ResultSet claimResult = claimStmt.executeQuery();

                while (claimResult.next()) {
                    ClaimModel claim = new ClaimModel();
                    claim.setPk_claim(claimResult.getInt("pk_claim"));
                    claim.setEmp_title(claimResult.getString("emp_title"));
                    claim.setEmp_name(claimResult.getString("emp_name"));
                    claim.setEmp_desg(claimResult.getString("emp_desg"));
                    claim.setEmp_divn(claimResult.getString("emp_divn"));
                    claim.setEmp_secn(claimResult.getString("emp_secn"));
                    claim.setEmp_lab(claimResult.getString("emp_lab"));
                    claim.setEmp_group(claimResult.getString("emp_group"));
                    claim.setBene_name(claimResult.getString("bene_name"));
                    claim.setAmt_claimed(claimResult.getInt("amt_claimed"));
                    claim.setAmt_approved(claimResult.getInt("amt_approved"));
                    claims.add(claim);
                }
                employee.setE_claims(claims);

                claimResult.close();
                claimStmt.close();


            }
            result.close();
            statement.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return employee;
    }



    public ClaimModel submitForm(String emp_name, String designation, String division, int ccno, BeneficiaryModel beneficiary, BillModel bill, PrescriptionModel[] prescriptions){

        ClaimModel claim=null;

        try{
            PostgresDBUtil pg = new PostgresDBUtil();
            Connection con = pg.connect();





        }catch(Exception e){
            System.out.println(e);
        }






        return claim;
    }

}
