package com.medical.rrcat.controller;



import com.medical.rrcat.model.BeneficiaryModel;
import com.medical.rrcat.model.BillModel;
import com.medical.rrcat.model.PrescriptionModel;

import com.medical.rrcat.service.logic.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editClaim")
@MultipartConfig
public class EditCLaimServlet extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeService = new EmployeeService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Extract parameters from request
            int pkClaim = Integer.parseInt(request.getParameter("pkClaim"));
            BeneficiaryModel beneficiary = extractBeneficiary(request);
            BillModel[] bills = extractBills(request);
            PrescriptionModel[] prescriptions = extractPrescriptions(request);
            int amtClaimed = Integer.parseInt(request.getParameter("amt_claimed"));
            String userId = request.getParameter("userId");
            String ipAddress = request.getRemoteAddr();
            int doctorCcno = Integer.parseInt(request.getParameter("doctor_ccno"));

            // File upload handling
            Part filePart = request.getPart("file");
            String fileName = getSubmittedFileName(filePart);

            // Call service method
            boolean success = employeeService.editForm(pkClaim, beneficiary, bills, prescriptions,
                    amtClaimed, userId, ipAddress, doctorCcno,
                    filePart, fileName);

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Claim updated successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Failed to update claim");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error processing request: " + e.getMessage());
        }
    }

    private BeneficiaryModel extractBeneficiary(HttpServletRequest request) {
        BeneficiaryModel beneficiary = new BeneficiaryModel();
        beneficiary.setBene_name(request.getParameter("bene_name"));
        beneficiary.setChss_char(request.getParameter("chss_char").charAt(0));
        beneficiary.setRela_code(Integer.parseInt(request.getParameter("rela_code")));
        return beneficiary;
    }

    private BillModel[] extractBills(HttpServletRequest request) {
        List<BillModel> bills = new ArrayList<>();
        int i = 1;
        while (request.getParameter("bill_date_" + i) != null) {
            BillModel bill = new BillModel();
            bill.setBill_date(Date.valueOf(request.getParameter("bill_date_" + i)));
            bill.setStore_name(request.getParameter("store_name_" + i));
            bills.add(bill);
            i++;
        }
        return bills.toArray(new BillModel[0]);
    }

    private PrescriptionModel[] extractPrescriptions(HttpServletRequest request) {
        List<PrescriptionModel> prescriptions = new ArrayList<>();
        int i = 1;
        while (request.getParameter("pres_date_" + i) != null) {
            PrescriptionModel prescription = new PrescriptionModel();
            prescription.setPres_date(Date.valueOf(request.getParameter("pres_date_" + i)));
            prescription.setPres_dr(request.getParameter("pres_dr_" + i));
            prescription.setDr_ccno(Integer.parseInt(request.getParameter("dr_ccno_" + i)));
            prescriptions.add(prescription);
            i++;
        }
        return prescriptions.toArray(new PrescriptionModel[0]);
    }

    private String getSubmittedFileName(Part part) {
        return part.getSubmittedFileName();
    }
}
