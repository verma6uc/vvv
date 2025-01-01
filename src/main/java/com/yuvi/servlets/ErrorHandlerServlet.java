package com.yuvi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/error", name = "ErrorHandler")
public class ErrorHandlerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        handleError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        handleError(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        
        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
                    break;
                case 500:
                    request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
                    break;
                default:
                    response.sendError(statusCode);
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
