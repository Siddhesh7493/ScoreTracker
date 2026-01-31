<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.cricket.util.Player" %>
<html>
<head>
    <title>Scoring Dashboard | ScoreTracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <header>
        <h1>🏏 ScoreTracker Scorer</h1>
    </header>

    <div class="container">
        <% String matchId = request.getParameter("matchId"); %>
        <div class="card">
            <h2>Live Scoring - Match ID: <%= matchId %></h2>
            
            <form action="addBall" method="post">
                <input type="hidden" name="matchId" value="<%= matchId %>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px; background: #f9fafb; padding: 15px; border-radius: 8px; margin-bottom: 20px;">
                    <div>
                        <label><strong>On Strike (Striker):</strong></label>
                        <select name="strikerId" required>
                            <option value="">Select Batsman</option>
                            <option value="101">Virat Kohli</option>
                            <option value="102">Rohit Sharma</option>
                        </select>
                    </div>
                    <div>
                        <label><strong>Off Strike (Non-Striker):</strong></label>
                        <select name="nonStrikerId" required>
                            <option value="">Select Batsman</option>
                            <option value="102">Rohit Sharma</option>
                            <option value="101">Virat Kohli</option>
                        </select>
                    </div>
                    <div style="grid-column: span 2;">
                        <label><strong>Current Bowler:</strong></label>
                        <select name="bowlerId" required>
                            <option value="">Select Bowler</option>
                            <option value="201">Jasprit Bumrah</option>
                            <option value="202">Mohammed Shami</option>
                        </select>
                    </div>
                </div>

                <hr style="border: 0; border-top: 1px solid #eee; margin: 20px 0;">

                <label>Runs:</label>
                <select name="runs">
                    <option value="0">0 (Dot Ball)</option>
                    <option value="1">1 Run</option>
                    <option value="2">2 Runs</option>
                    <option value="3">3 Runs</option>
                    <option value="4">4 (Boundary)</option>
                    <option value="6">6 (Maximum)</option>
                </select>

                <div style="margin: 15px 0;">
                    <label>Extras:</label><br>
                    <input type="checkbox" name="isWide"> Wide (+1 & Re-ball) 
                    <input type="checkbox" name="isNoBall" style="margin-left: 20px;"> No-Ball (+1 & Re-ball)
                </div>

                <div style="margin: 15px 0; color: #ef4444; font-weight: bold;">
                    <input type="checkbox" name="isWicket"> ☝️ Wicket / Out!
                </div>

                <button type="submit">Submit Ball</button>
            </form>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <a href="scoreboard?matchId=<%= matchId %>" target="_blank" class="button-link" style="text-decoration: none; color: var(--primary); font-weight: bold;">
                📊 Open Live Scoreboard (Viewer Link)
            </a>
        </div>
    </div>
</body>
</html>