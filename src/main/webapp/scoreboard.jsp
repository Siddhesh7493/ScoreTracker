<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cricket.util.ScoreSummary" %>
<html>
<head>
    <title>LED Scoreboard | ScoreTracker</title>
    <style>
        body { background: #0f172a; display: flex; justify-content: center; padding-top: 50px; font-family: monospace; }
        .led-board { background: #000; border: 10px solid #334155; border-radius: 20px; width: 80%; padding: 40px; color: #fbbf24; text-align: center; }
        .main-score { font-size: 10rem; line-height: 1; border-bottom: 2px solid #1e293b; padding-bottom: 20px; }
        .stats { display: flex; justify-content: space-around; font-size: 2.5rem; color: #fff; margin-top: 20px; }
    </style>
    <script>setTimeout(function(){ location.reload(); }, 5000);</script>
</head>
<body>
    <div class="led-board">
        <% ScoreSummary s = (ScoreSummary) request.getAttribute("score"); 
           if (s != null) { %>
            <div style="font-size: 1.5rem; color: #94a3b8;">MATCH ID: <%= request.getParameter("matchId") %></div>
            <div class="main-score">
                <%= s.getTotalRuns() %> - <%= s.getTotalWickets() %>
                <div style="font-size: 3rem; color: #94a3b8;">OVERS: <%= s.getOvers() %></div>
            </div>
            <div class="stats">
                <div>BAT: <%= s.getStrikerName() != null ? s.getStrikerName() : "---" %>* (<%= s.getStrikerRuns() %>)</div>
                <div style="color: #fbbf24;">BOWL: <%= s.getBowlerName() != null ? s.getBowlerName() : "---" %></div>
            </div>
        <% } %>
    </div>
</body>
</html>