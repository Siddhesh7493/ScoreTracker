package com.cricket.controller;

import com.cricket.dao.MatchDAO;
import com.cricket.util.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectMatch")
public class SelectMatchServlet extends HttpServlet {
    private MatchDAO matchDAO = new MatchDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Match> liveMatches = matchDAO.getLiveMatches();
        request.setAttribute("matches", liveMatches);
        request.getRequestDispatcher("selectMatch.jsp").forward(request, response);
    }
}