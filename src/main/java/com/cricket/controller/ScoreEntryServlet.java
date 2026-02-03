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
            String idParam = request.getParameter("matchId");
            if (idParam == null) {
                response.sendRedirect("selectMatch?error=NoMatchSelected");
                return;
            }

            int matchId = Integer.parseInt(idParam);
            
            // Fetch state from DAO
            ScoreSummary matchState = reportDAO.getLiveScore(matchId);
            
            // Safety: If matchState is null (match doesn't exist), initialize a blank one
            if (matchState == null) {
                matchState = new ScoreSummary();
            }

            request.setAttribute("matchState", matchState);
            request.getRequestDispatcher("scoreEntry.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            // Redirect back with error if something fails
            response.sendRedirect("selectMatch?error=SystemError");
        }
    }
}