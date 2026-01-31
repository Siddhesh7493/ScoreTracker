package com.cricket.controller;

import com.cricket.util.ScoreLog;
import com.cricket.service.ScoreService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addBall")
public class AddBallServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ScoreService scoreService = new ScoreService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // 1. Extract IDs from the form
            int matchId = Integer.parseInt(request.getParameter("matchId"));
            int runs = Integer.parseInt(request.getParameter("runs"));
            
            // New: Extracting Player IDs
            String strikerIdStr = request.getParameter("strikerId");
            String nonStrikerIdStr = request.getParameter("nonStrikerId");
            String bowlerIdStr = request.getParameter("bowlerId");

            // 2. Check for Extras and Wicket
            boolean isWide = request.getParameter("isWide") != null;
            boolean isNoBall = request.getParameter("isNoBall") != null;
            boolean isWicket = request.getParameter("isWicket") != null;

            // 3. Create the ScoreLog object
            ScoreLog log = new ScoreLog();
            log.setMatchId(matchId);
            log.setRuns(runs);
            log.setWide(isWide);
            log.setNoBall(isNoBall);
            log.setWicket(isWicket);
            
            // New: Store player context in the log (Optional: Add these fields to ScoreLog.java if needed)
            // log.setStrikerId(Integer.parseInt(strikerIdStr));
            // log.setBowlerId(Integer.parseInt(bowlerIdStr));

            // 4. Pass to Service Layer for Rule Processing
            boolean success = scoreService.processBall(log);

            if (success) {
                // Redirect back to entry page with success message
                response.sendRedirect("scoreEntry.jsp?matchId=" + matchId + "&msg=Ball Recorded");
            } else {
                response.sendRedirect("scoreEntry.jsp?matchId=" + matchId + "&error=Database Error");
            }
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("dashboard.jsp?error=Invalid Input Data");
        }
    }
}