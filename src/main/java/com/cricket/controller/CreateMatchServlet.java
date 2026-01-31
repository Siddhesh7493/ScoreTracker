
package com.cricket.controller;

import com.cricket.dao.MatchDAO;
import com.cricket.util.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/createMatch")
public class CreateMatchServlet extends HttpServlet {
    private MatchDAO matchDAO = new MatchDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String teamA = request.getParameter("teamA");
        String teamB = request.getParameter("teamB");
        String venue = request.getParameter("venue");
     // Inside doPost of CreateMatchServlet.java
      

        Match match = new Match();
        match.setTeamA(teamA);
        match.setTeamB(teamB);
        match.setVenue(venue);
        match.setStatus("live");

        if (matchDAO.createMatch(match)) {
            response.sendRedirect("dashboard.jsp?msg=Match Created");
        } else {
            response.sendRedirect("createMatch.jsp?error=Failed to Create");
        }
    }
}