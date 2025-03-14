package com.medical.rrcat.service.logic;

import com.medical.rrcat.model.ClaimModel;
import com.medical.rrcat.model.DoctorModel;
import com.medical.rrcat.service.config.PostgresDBUtil;
import com.medical.rrcat.service.config.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DoctorService {

    DoctorModel doctor = null;
    Query q = new Query();
    int offset = -100;

    //Frontend pe check krna if pageno*100 > size of respective array then only db call else get from array
    public void getDoctorClaims() {


        try {
            PostgresDBUtil pg = new PostgresDBUtil();
            Connection con = pg.connect();

            PreparedStatement claimStatement = con.prepareStatement(q.getDoctorClaimQuery());
            claimStatement.setInt(1, doctor.getDr_ccno());
            claimStatement.setInt(2, offset += 100);


            ResultSet claims = claimStatement.executeQuery();
            int currentClaimId = -1;

            while (claims.next()) {

                int claimId = claims.getInt("c_id");
                int op_code = claims.getInt("op_code");
                if (op_code == 0) continue; // save me hai doctor ko nhi dikhana


                if (claimId != currentClaimId) {
                    currentClaimId = claimId;

                    ClaimModel claim = new ClaimModel();

                    claim.setPk_claim(claims.getInt("pk_claim"));
                    claim.setEmp_title(claims.getString("emp_title"));
                    claim.setEmp_name(claims.getString("emp_name"));
                    claim.setEmp_desg(claims.getString("emp_desg"));
                    claim.setEmp_divn(claims.getString("emp_divn"));
                    claim.setEmp_secn(claims.getString("emp_secn"));
                    claim.setEmp_lab(claims.getString("emp_lab"));
                    claim.setEmp_group(claims.getString("emp_group"));
                    claim.setBene_name(claims.getString("bene_name"));
                    claim.setAmt_claimed(claims.getInt("amt_claimed"));
                    claim.setAmt_approved(claims.getInt("amt_approved"));


                    if (op_code == 1) {
                        ArrayList<ClaimModel> active = doctor.getPendingClaims();
                        active.add(claim);
                        doctor.setPendingClaims(active);
                    } else {
                        ArrayList<ClaimModel> notActive = doctor.getReviewedClaims();
                        notActive.add(claim);
                        doctor.setReviewedClaims(notActive);
                    }
                }

            }
            claims.close();
            con.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public DoctorModel getDoctor(int ccno) {

        try {

            PostgresDBUtil pg = new PostgresDBUtil();
            Connection con = pg.connect();


            PreparedStatement doctorStatement = con.prepareStatement(q.getDoctorQuery());
            doctorStatement.setInt(1, ccno);

            ResultSet result = doctorStatement.executeQuery();

            if (result.next()) {
                doctor = new DoctorModel();

                doctor.setDr_ccno(ccno);
                doctor.setDr_code(result.getString("dr_code"));
                doctor.setDr_order(result.getInt("dr_code"));
                doctor.setDr_name(result.getString("dr_name"));
                doctor.setIncharge(result.getBoolean("dr_incharge"));
            } else {
                throw new Exception("Doctor Not Found");
            }
            // Populating the doctor with the corresponding claims array
            getDoctorClaims();

            con.close();
            doctorStatement.close();
            return doctor;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void doctorOperation(int op_code,ClaimModel claim) {

        PostgresDBUtil pg = new PostgresDBUtil();
        Connection con = null;
        PreparedStatement statement=null;
        try {

            con= pg.connect();
            statement= con.prepareStatement(q.getDoctorOperation());



            statement.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
