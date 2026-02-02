package com.cricket.controller;

import com.cricket.dao.UserDAO;
import com.cricket.util.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        User authenticatedUser = userDAO.validateUser(user, pass);

        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser);
            session.setAttribute("role", authenticatedUser.getRole());

            // Role-Based Redirection
            if ("admin".equals(authenticatedUser.getRole())) {
                response.sendRedirect("dashboard.jsp"); // Admin sees match creation
            } else if ("scorer".equals(authenticatedUser.getRole())) {
                response.sendRedirect("selectMatch"); // Scorer goes to match selection
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid Credentials");
        }
    }
}