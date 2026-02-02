package com.cricket.controller;

import com.cricket.util.ScoreLog;
import com.cricket.service.ScoreService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addBall")
public class AddBallServlet extends HttpServlet {
    private ScoreService scoreService = new ScoreService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int matchId = Integer.parseInt(request.getParameter("matchId"));
        int runs = Integer.parseInt(request.getParameter("runs"));
        
        // Capture all names for manual state tracking
        String striker = request.getParameter("strikerName");
        String nonStriker = request.getParameter("nonStrikerName");
        String bowler = request.getParameter("bowlerName");

        ScoreLog log = new ScoreLog();
        log.setMatchId(matchId);
        log.setRuns(runs);
        log.setStrikerName(striker);
        log.setBowlerName(bowler);
        log.setWide(request.getParameter("isWide") != null);
        log.setNoBall(request.getParameter("isNoBall") != null);
        log.setWicket(request.getParameter("isWicket") != null);

        // Process logic and update non-striker in match state
        if (scoreService.processBall(log, nonStriker)) {
            response.sendRedirect("scoreEntry?matchId=" + matchId);
        }
    }
}