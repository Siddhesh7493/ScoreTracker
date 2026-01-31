
package com.cricket.controller;

import com.cricket.dao.ReportDAO;
import com.cricket.util.ScoreSummary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/scoreboard")
public class ScoreboardServlet extends HttpServlet {
    private ReportDAO reportDAO = new ReportDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int matchId = Integer.parseInt(request.getParameter("matchId"));
        ScoreSummary summary = reportDAO.getLiveScore(matchId);
        
        request.setAttribute("score", summary);
        request.getRequestDispatcher("scoreboard.jsp").forward(request, response);
    }
}