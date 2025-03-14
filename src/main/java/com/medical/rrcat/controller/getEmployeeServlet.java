package com.medical.rrcat.controller;


import com.medical.rrcat.model.EmployeeModel;
import com.medical.rrcat.service.logic.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employee")
public class getEmployeeServlet extends HttpServlet {

    private com.medical.rrcat.service.logic.EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ccnoParam = request.getParameter("ccno");
        if (ccnoParam == null || ccnoParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CCNO parameter is required");
            return;
        }

        try {
            int ccno = Integer.parseInt(ccnoParam);
            EmployeeModel employee = employeeService.getEmployee(ccno);

            if (employee == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
                return;
            }

            // Set employee as request attribute
            request.setAttribute("employee", employee);

            // Forward to JSP
            request.getRequestDispatcher("/employee.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid CCNO format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving employee data");
            // Log the actual exception details here
        }
    }
}
