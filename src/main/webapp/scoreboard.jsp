<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cricket.util.ScoreSummary" %>
<html>
<head>
    <title>LED Live Scoreboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script>setTimeout(function(){ location.reload(); }, 5000);</script>
    <style>
        body { background: #0f172a; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .led-container { background: #000; border: 8px solid #334155; border-radius: 15px; width: 80%; padding: 40px; color: #fbbf24; font-family: 'Courier New', monospace; box-shadow: 0 0 50px rgba(0,0,0,0.5); }
        .match-id { color: #94a3b8; font-size: 1.5rem; text-align: center; margin-bottom: 20px; letter-spacing: 5px; }
        .main-line { display: flex; justify-content: center; align-items: center; border-bottom: 2px solid #1e293b; padding-bottom: 20px; margin-bottom: 20px; }
        .runs { font-size: 10rem; line-height: 1; }
        .wickets { font-size: 10rem; line-height: 1; margin: 0 40px; }
        .overs { font-size: 4rem; color: #fff; text-align: center; display: block; }
        .player-row { display: flex; justify-content: space-between; font-size: 2.5rem; color: #fff; margin-top: 20px; }
    </style>
</head>
<body>
    <div class="led-container">
        <% ScoreSummary s = (ScoreSummary) request.getAttribute("score"); 
           if (s != null) { %>
            <div class="match-id">MATCH ID: <%= request.getParameter("matchId") %></div>
            <div class="main-line">
                <span class="runs"><%= s.getTotalRuns() %></span>
                <span class="wickets" style="color: #ef4444;">-</span>
                <span class="runs"><%= s.getTotalWickets() %></span>
            </div>
            <span class="overs">OVERS: <%= s.getOvers() %></span>
            <div class="player-row">
                <div>BAT: <span style="color: #fbbf24;"><%= (s.getStrikerName() != null && !s.getStrikerName().isEmpty()) ? s.getStrikerName() + "*" : "---" %></span> (<%= s.getStrikerRuns() %>)</div>
                <div>BOWL: <span style="color: #fbbf24;"><%= (s.getBowlerName() != null && !s.getBowlerName().isEmpty()) ? s.getBowlerName() : "---" %></span></div>
            </div>
        <% } %>
    </div>
</body>
</html>