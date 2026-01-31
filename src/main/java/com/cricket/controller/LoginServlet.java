package com.cricket.controller;

import com.cricket.dao.UserDAO;
import com.cricket.util.User; // Matches your Project Explorer screenshot
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // DEBUG: Check what is being received from the JSP
        System.out.println("Login Attempt - Username: " + user + ", Password: " + pass);

        User validatedUser = userDAO.validateUser(user, pass);

        if (validatedUser != null) {
            // DEBUG: Verification of successful DB match
            System.out.println("Success: User " + validatedUser.getUsername() + " found with role " + validatedUser.getRole());
            
            HttpSession session = request.getSession();
            session.setAttribute("user", validatedUser);
            response.sendRedirect("dashboard.jsp");
        } else {
            // DEBUG: Failure notification
            System.out.println("Failure: No match found in 'users' table for these credentials.");
            response.sendRedirect("login.jsp?error=Invalid Credentials");
        }
    }
}