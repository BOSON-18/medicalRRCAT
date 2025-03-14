package com.medical.rrcat.controller;


import com.medical.rrcat.service.logic.DoctorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctorClaims")
public class DoctorClaimsServlet extends HttpServlet {

    private com.medical.rrcat.service.logic.DoctorService doctorService;

    @Override
    public void init() throws ServletException {
        doctorService = new DoctorService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pageParam = request.getParameter("page");
        int page = 0;

        try {
            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
            }


            doctorService.getDoctorClaims();

            // Return JSON response for AJAX requests
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": true, \"page\": " + page + "}");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid page number");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error loading claims: " + e.getMessage());
        }
    }
}
