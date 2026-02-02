package com.cricket.controller;

import com.cricket.dao.ReportDAO;
import com.cricket.util.ScoreSummary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/scoreEntry")
public class ScoreEntryServlet extends HttpServlet {
    private ReportDAO reportDAO = new ReportDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int matchId = Integer.parseInt(request.getParameter("matchId"));
            
            // Use ReportDAO to get the current match state (names, runs, etc.)
            ScoreSummary matchState = reportDAO.getLiveScore(matchId);
            
            // Pass the state to the JSP
            request.setAttribute("matchState", matchState);
            request.getRequestDispatcher("scoreEntry.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("selectMatch?error=InvalidMatch");
        }
    }
}