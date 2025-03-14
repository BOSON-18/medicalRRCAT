package com.medical.rrcat.controller;

import com.medical.rrcat.service.config.MongoDBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PDFDownloadServletMon", urlPatterns = {"/servlet/PDFDownloadServletMon", "/catintra/servlet/PDFDownloadServletMon"})
public class PDFDownloadServletMon extends HttpServlet {

    private final MongoDBUtil mongoDBUtil = new MongoDBUtil();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileId = request.getParameter("fileId");
        if (fileId == null || fileId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File ID is required");
            return;
        }

        try {
            byte[] pdfContent = mongoDBUtil.downloadPdfContent(fileId);
            if (pdfContent != null) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=\"file.pdf\"");
                response.getOutputStream().write(pdfContent);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid file ID format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving file");
        }
    }
}
