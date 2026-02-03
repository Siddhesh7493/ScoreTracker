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
        
        try {
            int matchId = Integer.parseInt(request.getParameter("matchId"));
            int runs = Integer.parseInt(request.getParameter("runs"));
            
            // Capture all three manual names from the scorer panel
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

            // Pass the log and the nonStriker name to the service for processing
            if (scoreService.processBall(log, nonStriker)) {
                response.sendRedirect("scoreEntry?matchId=" + matchId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("selectMatch?error=SubmissionFailed");
        }
    }
}