<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cricket.util.ScoreSummary" %>
<html>
<head>
    <title>Professional Scorer | ScoreTracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <header><h1>🏏 Scorer Dashboard</h1></header>
    <div class="container">
        <% 
    		ScoreSummary s = (ScoreSummary) request.getAttribute("matchState"); 
    		boolean isNewBatsmanNeeded = true;
    		if (s != null && s.getStrikerName() != null && !s.getStrikerName().trim().isEmpty()) {
        	isNewBatsmanNeeded = false;
    		}
    		String matchId = request.getParameter("matchId");
		%>

        <div class="card">
            <h2 style="margin-bottom: 20px;">Match ID: <%= matchId %> | Overs: <%= (s != null) ? s.getOversFormatted() : "0.0" %></h2>
            
            <form action="addBall" method="post">
                <input type="hidden" name="matchId" value="<%= matchId %>">
                
                <div class="row" style="background: <%= isNewBatsmanNeeded ? "#fee2e2" : "#f1f5f9" %>; padding: 20px; border-radius: 8px; margin-bottom: 20px; border: <%= isNewBatsmanNeeded ? "2px solid #ef4444" : "none" %>;">
                    <div style="margin-bottom: 15px;">
                        <label><strong>Striker <%= isNewBatsmanNeeded ? "<span style='color:red;'>(OUT - ENTER NEW)</span>" : "(On Strike)" %>:</strong></label>
                        <input type="text" name="strikerName" 
                               value="<%= (!isNewBatsmanNeeded) ? s.getStrikerName() : "" %>" 
                               placeholder="Enter Batsman Name" required 
                               style="width: 100%; padding: 10px; border: <%= isNewBatsmanNeeded ? "2px solid #ef4444" : "1px solid #ccc" %>;">
                    </div>
                    
                    <div style="margin-bottom: 15px;">
                        <label><strong>Non-Striker (Off-Strike):</strong></label>
                        <input type="text" name="nonStrikerName" value="<%= (s != null && s.getNonStrikerName() != null) ? s.getNonStrikerName() : "" %>" placeholder="Enter Non-Striker Name" style="width: 100%; padding: 10px;">
                    </div>

                    <div>
                        <label><strong>Current Bowler:</strong></label>
                        <input type="text" name="bowlerName" value="<%= (s != null && s.getBowlerName() != null) ? s.getBowlerName() : "" %>" placeholder="Enter Bowler Name" required style="width: 100%; padding: 10px;">
                    </div>
                </div>

                <div class="run-grid" style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 12px;">
                    <% int[] runs = {0, 1, 2, 3, 4, 6}; 
                       for(int r : runs) { %>
                        <button type="submit" name="runs" value="<%= r %>" class="btn" style="padding: 15px; font-weight: bold; background: #10b981; color: white; border: none; border-radius: 5px; cursor: pointer;">
                            <%= r %>
                        </button>
                    <% } %>
                </div>

                <div style="margin-top: 20px; display: flex; gap: 20px; justify-content: center;">
                    <label><input type="checkbox" name="isWide"> Wide</label>
                    <label><input type="checkbox" name="isNoBall"> No-Ball</label>
                    <label style="color: #ef4444;"><input type="checkbox" name="isWicket"> <strong>Wicket!</strong></label>
                </div>
            </form>

            <hr style="margin: 20px 0; border: 0; border-top: 1px solid #e2e8f0;">
            <div style="text-align: center;">
                <a href="scoreboard?matchId=<%= matchId %>" target="_blank" style="text-decoration: none; color: #1e3a8a; font-weight: bold;">
                    📊 Open Live LED Scoreboard
                </a>
            </div>
        </div>
    </div>
</body>
</html>