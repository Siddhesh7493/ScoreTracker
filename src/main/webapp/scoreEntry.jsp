<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Scoring Dashboard | ScoreTracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <header><h1>🏏 ScoreTracker Scorer</h1></header>
    <div class="container">
        <div class="card">
            <h2>Match ID: <%= request.getParameter("matchId") %></h2>
            <form action="addBall" method="post">
                <input type="hidden" name="matchId" value="<%= request.getParameter("matchId") %>">
                
                <div style="margin-bottom: 15px;">
                    <label><strong>Striker Name:</strong></label>
                    <input type="text" name="strikerName" placeholder="Enter Batsman Name" required 
                           style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px;">
                </div>

                <div style="margin-bottom: 15px;">
                    <label><strong>Bowler Name:</strong></label>
                    <input type="text" name="bowlerName" placeholder="Enter Bowler Name" required 
                           style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px;">
                </div>

                <label><strong>Runs:</strong></label>
                <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 10px; margin: 10px 0;">
                    <button type="submit" name="runs" value="0" class="btn">0</button>
                    <button type="submit" name="runs" value="1" class="btn">1</button>
                    <button type="submit" name="runs" value="2" class="btn">2</button>
                    <button type="submit" name="runs" value="3" class="btn">3</button>
                    <button type="submit" name="runs" value="4" class="btn">4</button>
                    <button type="submit" name="runs" value="6" class="btn">6</button>
                </div>

                <div style="margin: 15px 0; background: #f8fafc; padding: 10px; border-radius: 5px;">
                    <input type="checkbox" name="isWide"> Wide 
                    <input type="checkbox" name="isNoBall" style="margin-left: 15px;"> No-Ball
                    <input type="checkbox" name="isWicket" style="margin-left: 15px;"> Wicket!
                </div>
            </form>
            <hr>
            <div style="text-align: center;">
                <a href="scoreboard?matchId=<%= request.getParameter("matchId") %>" target="_blank" style="color: var(--primary); font-weight: bold;">
                    📊 Open Live Scoreboard
                </a>
            </div>
        </div>
    </div>
</body>
</html>