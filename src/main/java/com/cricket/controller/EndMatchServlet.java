
package com.cricket.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import com.cricket.util.DBConnection;

@WebServlet("/endMatch")
public class EndMatchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String matchId = request.getParameter("matchId");
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("UPDATE matches SET status='completed' WHERE match_id=?");
            pst.setInt(1, Integer.parseInt(matchId));
            pst.executeUpdate();
            response.sendRedirect("dashboard.jsp?msg=Match Ended");
        } catch (Exception e) { e.printStackTrace(); }
    }
}