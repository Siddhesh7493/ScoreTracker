<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cricket.util.ScoreSummary, java.util.List" %>
<html>
<head>
    <title>Professional Scorer | ScoreTracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
        .ball-circle {
            display: inline-block;
            width: 35px; height: 35px;
            line-height: 35px;
            border-radius: 50%;
            text-align: center;
            margin-right: 8px;
            font-weight: bold;
            background: #e2e8f0;
            color: #1e293b;
        }
        .ball-wicket { background: #ef4444; color: white; }
        .ball-boundary { background: #10b981; color: white; }
        .history-section { margin-top: 25px; padding: 15px; background: #fff; border-radius: 12px; border: 1px solid #e2e8f0; }
    </style>
</head>
<body>
    <header><h1>🏏 Scorer Dashboard</h1></header>
    <div class="container">
        <% 
            ScoreSummary s = (ScoreSummary) request.getAttribute("matchState"); 
            boolean isNewBatsmanNeeded = (s != null && (s.getStrikerName() == null || s.getStrikerName().isEmpty()));
            String matchId = request.getParameter("matchId");
        %>

        <div class="card">
            <h2 style="margin-bottom: 20px;">Match ID: <%= matchId %> | Overs: <%= (s != null) ? s.getOvers() : "0.0" %></h2>
            
            <form action="addBall" method="post">
                <input type="hidden" name="matchId" value="<%= matchId %>">
                
                <div class="row" style="background: <%= isNewBatsmanNeeded ? "#fee2e2" : "#f1f5f9" %>; padding: 20px; border-radius: 8px; margin-bottom: 20px; border: <%= isNewBatsmanNeeded ? "2px solid #ef4444" : "none" %>;">
                    <div style="margin-bottom: 15px;">
                        <label><strong>Striker <%= isNewBatsmanNeeded ? "<span style='color:red;'>(OUT - ENTER NEW)</span>" : "(On Strike)" %>:</strong></label>
                        <input type="text" name="strikerName" 
                               value="<%= (!isNewBatsmanNeeded && s != null) ? s.getStrikerName() : "" %>" 
                               placeholder="Enter New Batsman Name" required 
                               style="width: 100%; padding: 10px; border: <%= isNewBatsmanNeeded ? "2px solid #ef4444" : "1px solid #ccc" %>;">
                    </div>
                    
                    <div style="margin-bottom: 15px;">
                        <label><strong>Non-Striker:</strong></label>
                        <input type="text" name="nonStrikerName" value="<%= (s != null && s.getNonStrikerName() != null) ? s.getNonStrikerName() : "" %>" placeholder="Off-strike player" style="width: 100%; padding: 10px;">
                    </div>

                    <div>
                        <label><strong>Current Bowler:</strong></label>
                        <input type="text" name="bowlerName" value="<%= (s != null && s.getBowlerName() != null) ? s.getBowlerName() : "" %>" placeholder="Bowler name" required style="width: 100%; padding: 10px;">
                    </div>
                </div>

                <div class="run-grid" style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 12px;">
                    <% int[] runs = {0, 1, 2, 3, 4, 6}; 
                       for(int r : runs) { %>
                        <button type="submit" name="runs" value="<%= r %>" 
                                style="padding: 15px; font-size: 1.2rem; cursor: pointer; border-radius: 8px; border: none; background: #10b981; color: white;">
                            <%= r %>
                        </button>
                    <% } %>
                </div>

                <div style="margin-top: 20px; display: flex; gap: 20px; align-items: center; justify-content: center;">
                    <label><input type="checkbox" name="isWide"> Wide</label>
                    <label><input type="checkbox" name="isNoBall"> No-Ball</label>
                    <label style="color: #ef4444;"><input type="checkbox" name="isWicket"> <strong>Wicket!</strong></label>
                </div>
            </form>

            <div class="history-section">
                <h3 style="margin-bottom: 10px; color: #64748b; font-size: 0.9rem; text-transform: uppercase;">Recent Balls (This Over)</h3>
                <div style="display: flex; align-items: center;">
                    <% 
                        // Mock history - in a full implementation, you'd pass a List<Ball> here
                        String[] history = {"0", "1", "4", "W", "2", "0"}; 
                        for(String ball : history) {
                            String cssClass = "ball-circle";
                            if(ball.equals("W")) cssClass += " ball-wicket";
                            else if(ball.equals("4") || ball.equals("6")) cssClass += " ball-boundary";
                    %>
                        <span class="<%= cssClass %>"><%= ball %></span>
                    <% } %>
                </div>
            </div>

            <hr style="margin: 20px 0; border: 0; border-top: 1px solid #e2e8f0;">
            <div style="text-align: center;">
                <a href="scoreboard?matchId=<%= matchId %>" target="_blank" style="text-decoration: none; color: #1e3a8a; font-weight: bold;">
                    📊 Open Public LED Scoreboard
                </a>
            </div>
        </div>
    </div>
</body>
</html>