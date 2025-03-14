package com.medical.rrcat.controller;

import com.medical.rrcat.model.DoctorModel;

import com.medical.rrcat.service.logic.DoctorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctorProfile")
public class DoctorProfileServlet extends HttpServlet {

    private com.medical.rrcat.service.logic.DoctorService doctorService;

    @Override
    public void init() throws ServletException {
        doctorService = new DoctorService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ccnoParam = request.getParameter("ccno");
        if (ccnoParam == null || ccnoParam.isEmpty()) {
            request.setAttribute("error", "Doctor CCNO is required");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }

        try {
            int ccno = Integer.parseInt(ccnoParam);
            DoctorModel doctor = doctorService.getDoctor(ccno);

            request.setAttribute("doctor", doctor);
            request.setAttribute("pendingClaims", doctor.getPendingClaims());
            request.setAttribute("reviewedClaims", doctor.getReviewedClaims());

            request.getRequestDispatcher("/WEB-INF/doctorProfile.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Doctor CCNO format");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error retrieving doctor data: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }
}
