package com.medical.rrcat.service.logic;

import com.medical.rrcat.model.*;

import com.medical.rrcat.service.config.MongoDBUtil;
import com.medical.rrcat.service.config.PostgresDBUtil;
import com.medical.rrcat.service.config.Query;
import jakarta.servlet.http.Part;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.Instant;
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

    public String calculateHashCode(byte[] fileBytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(fileBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getBitStream(Part filePart) {
        byte[] fileBytes;
        try {
            InputStream fileInputStream = filePart.getInputStream();
            fileBytes = fileInputStream.readAllBytes();
            fileInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fileBytes;

    }


    public Boolean submitForm(BeneficiaryModel beneficiary, BillModel[] bills, PrescriptionModel[] prescriptions, int amt_claimed, String userId, String ip_address, int doctor_ccno, Part filePart,
                              String fileName) throws Exception {
        ClaimModel claim = null;
        String operation_name = null;

        try {
            PostgresDBUtil pg = new PostgresDBUtil();
            Connection con = pg.connect();


            PreparedStatement statement = con.prepareStatement(q.getInsertClaim());
            statement.setString(1, employee.getE_title());
            statement.setString(2, employee.getE_name());
            statement.setInt(3, employee.getE_desg_code());
            statement.setString(4, employee.getE_divn_code());
            statement.setString(5, employee.getE_secn_code());
            statement.setString(6, employee.getE_lab_code());
            statement.setString(7, employee.getE_grp_code());
            statement.setInt(7, employee.getE_ccno());
            statement.setString(8, beneficiary.getBene_name());
            statement.setString(9, Character.toString(beneficiary.getChss_char()));
            statement.setString(10, beneficiary.getBene_name());
            statement.setInt(11, amt_claimed);
            statement.setInt(11, 0);
            statement.setTimestamp(12, Timestamp.from(Instant.now()));
            statement.setString(13, userId);
            statement.setString(14, ip_address);

            try {
                PreparedStatement operation = con.prepareStatement(q.getOperationQuery());
                operation.setInt(1, 2);
                ResultSet operationResult = operation.executeQuery();
                while (operationResult.next()) {
                    statement.setString(15, operationResult.getString("oper_name"));
                    operation_name = operationResult.getString("oper_name");
                }
                operationResult.close();
                operation.close();
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }

            statement.setInt(16, doctor_ccno);

            // file upload
            MongoDBUtil mongo = null;
            try {
                mongo = new MongoDBUtil();

                InputStream fileInputStream = filePart.getInputStream();
                byte[] fileBytes = fileInputStream.readAllBytes();
                fileInputStream.close();
                String fileId = mongo.uploadPdfFile(fileBytes, fileName).toHexString();
//                String fileId = mongo.uploadPdfFile(filePath, fileName).toHexString();
                System.out.println("File uploaded successfully with ID: " + fileId);
                statement.setString(17, fileId);

                String fileHash = calculateHashCode(fileBytes);
                System.out.println("File Hash Code -> " + fileHash);
                statement.setString(18, fileHash);

            } catch (Exception mongoError) {
                // Use a logging framework for better logging
                System.err.println("Mongo Error-> " + mongoError);
                mongoError.printStackTrace();
                return false;
            } finally {
                if (mongo != null) {

                    mongo.closeConnection();
                }
            }

            // values set over now execute the query and get the autoGenerated pk_claim
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int pkClaim = resultSet.getInt(1);
                        System.out.println("Auto Generated pk_claim " + pkClaim);
                        statement.close();
                        resultSet.close();

                        // now Bill Table query

                        PreparedStatement billStatement = con.prepareStatement(q.getInsertBill());
                        for (int i = 0; i < bills.length; i++) {
                            BillModel bill = bills[i];
                            billStatement.setInt(1, pkClaim);
                            billStatement.setDate(2, (Date) bill.getBill_date());
                            billStatement.setString(3, bill.getStore_name());
                            billStatement.setDate(4, (Date) Date.from(Instant.now()));
                            int changes = billStatement.executeUpdate();
                            if (changes == 0) {
                                System.out.println("Bill Serial No " + i + " failed to update");
                                return false;
                            }
                        }
                        billStatement.close();

                        PreparedStatement prescriptionStatement = con.prepareStatement(q.getInsertPrescriptions());
                        for (int i = 0; i < prescriptions.length; i++) {
                            PrescriptionModel prescription = prescriptions[i];
                            prescriptionStatement.setInt(1, pkClaim);
                            prescriptionStatement.setDate(2, (Date) prescription.getPres_date());
                            prescriptionStatement.setString(3, prescription.getPres_dr());
                            prescriptionStatement.setInt(4, prescription.getDr_ccno());

                            int changes = prescriptionStatement.executeUpdate();
                            if (changes == 0) {
                                System.out.println("Prescription Serial No " + i + " failed to update");
                                return false;
                            }
                        }
                        prescriptionStatement.close();

                        PreparedStatement stageStatement = con.prepareStatement(q.getSetStageQuery());
                        stageStatement.setInt(1, pkClaim);
                        stageStatement.setInt(2, doctor_ccno);
                        stageStatement.setInt(3, 2);
                        if (operation_name != null) stageStatement.setString(4, operation_name);
                        else System.out.println("OperationName Null");
                        stageStatement.setDate(5, (Date) Date.from(Instant.now()));
                        stageStatement.setInt(6, employee.getE_ccno());
                        stageStatement.setString(7, userId);

                        int changes = stageStatement.executeUpdate();
                        stageStatement.close();
                        if (changes == 0) {
                            System.out.println("Stage insertion Failed");
                            return false;

                        }


                    }
                } catch (Exception e) {
                    System.out.println("Bill Update Error->" + e);
                    return false;
                }
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


        return true;
    }


    public Boolean editForm(int pkClaim, BeneficiaryModel beneficiary, BillModel[] bills, PrescriptionModel[] prescriptions, int amt_claimed, String userId, String ip_address, int doctor_ccno, Part filePart,
                            String fileName) {

        // Form edit kb hoga? -> When either in pending or review stage
        List<ClaimModel> claims = employee.getE_claims();
        ClaimModel claim = null;
        for (ClaimModel it : claims) {
            if (it.getPk_claim() == pkClaim) {
                claim = it;
            }
        }
        if (claim == null) {
            System.out.println("Claim Not Found");
            return false;
        }

        StageModel[] stages = claim.getStages();
        int size = stages.length;
        StageModel stage = stages[size - 1];
        int op_code = stage.getOper_code();

        if (op_code != 1 || op_code != 2 || op_code != 3) {
            //new || pending || review me se ek nhi hai
            System.out.println("Cannot edit form not in pending or review or save stage");
            return false;
        }
        String operation_name = null;

        // ab koi dikkat nhi ab sb kuch kr skte edit krdo form ko

        try {
            PostgresDBUtil pg = new PostgresDBUtil();
            Connection con = pg.connect();

            PreparedStatement statement = con.prepareStatement(q.getEditClaimQuery());
            statement.setString(1, beneficiary.getBene_name());
            statement.setString(2, Character.toString(beneficiary.getChss_char()));
            statement.setInt(3, beneficiary.getRela_code());
            statement.setInt(4, amt_claimed);
            statement.setDate(5, (Date) Date.from(Instant.now()));
            statement.setString(6, ip_address);
            statement.setInt(7, doctor_ccno);
            statement.setInt(10, pkClaim);

            MongoDBUtil mongo = null;
            try {
                byte[] fileBytes = getBitStream(filePart);
                String fileHash = calculateHashCode(fileBytes);

                if (claim.getFile_hash() != fileHash) {
                    mongo = new MongoDBUtil();

                    String fileId = mongo.uploadPdfFile(fileBytes, fileName).toHexString();
                    System.out.println("File uploaded successfully with ID: " + fileId);
                    statement.setString(8, fileId);

                    System.out.println("File Hash : " + fileHash);
                    statement.setString(9, fileHash);

                }
            } catch (Exception mongoError) {
                // Use a logging framework for better logging
                System.err.println("Mongo Error-> " + mongoError);
                mongoError.printStackTrace();
                return false;
            } finally {
                if (mongo != null) {

                    mongo.closeConnection();
                }
            }

            PreparedStatement billStatement = con.prepareStatement(q.getUpdateBill());
            for (int i = 0; i < bills.length; i++) {
                BillModel bill = bills[i];
                billStatement.setInt(4, pkClaim);
                billStatement.setDate(1, (Date) bill.getBill_date());
                billStatement.setString(2, bill.getStore_name());
                billStatement.setDate(3, (Date) Date.from(Instant.now()));
                int changes = billStatement.executeUpdate();
                if (changes == 0) {
                    System.out.println("Bill Serial No " + i + " failed to update");
                    return false;
                }
            }
            billStatement.close();

            PreparedStatement prescriptionStatement = con.prepareStatement(q.getUpdatePrescription());
            for (int i = 0; i < prescriptions.length; i++) {
                PrescriptionModel prescription = prescriptions[i];
                prescriptionStatement.setInt(4, pkClaim);
                prescriptionStatement.setDate(1, (Date) prescription.getPres_date());
                prescriptionStatement.setString(2, prescription.getPres_dr());
                prescriptionStatement.setInt(3, prescription.getDr_ccno());

                int changes = prescriptionStatement.executeUpdate();
                if (changes == 0) {
                    System.out.println("Prescription Serial No " + i + " failed to update");
                    return false;
                }
            }
            prescriptionStatement.close();

            try {
                PreparedStatement operation = con.prepareStatement(q.getOperationQuery());
                operation.setInt(1, 2);
                ResultSet operationResult = operation.executeQuery();
                while (operationResult.next()) {
                    operation_name = operationResult.getString("oper_name");
                }
                operationResult.close();
                operation.close();
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }

            // stage ka if else ke thorugh update kro
            PreparedStatement stageStatement = null;
            if (op_code == 1 || op_code == 2) {
                //save || pending
                stageStatement = con.prepareStatement(q.getUpdateStageQuery());

                stageStatement.setInt(1, doctor_ccno);
                stageStatement.setInt(2, 2);
                stageStatement.setString(3, operation_name);
                stageStatement.setDate(4, (Date) Date.from(Instant.now()));
                stageStatement.setInt(5, pkClaim);

            } else {
                //review me hai
                stageStatement = con.prepareStatement(q.getSetStageQuery());

                stageStatement.setInt(1, pkClaim);
                stageStatement.setInt(2, doctor_ccno);
                stageStatement.setInt(3, 2);
                if (operation_name != null) stageStatement.setString(4, operation_name);
                else System.out.println("OperationName Null");
                stageStatement.setDate(5, (Date) Date.from(Instant.now()));
                stageStatement.setInt(6, employee.getE_ccno());
                stageStatement.setString(7, userId);


            }
            int changes = stageStatement.executeUpdate();
            stageStatement.close();
            if (changes == 0) {
                System.out.println("Stage insertion Failed");
                return false;
            }

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Edit Form Failed");
                return false;
            }

            statement.close();
            con.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return true;
    }

}

