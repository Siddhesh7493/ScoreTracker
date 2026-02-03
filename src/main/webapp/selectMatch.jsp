<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.cricket.util.Match" %>
<html>
<head>
    <title>Select Match | ScoreTracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
        body { background: #f8fafc; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        .match-list { max-width: 600px; margin: 50px auto; padding: 20px; }
        .match-card { 
            background: white; 
            padding: 20px; 
            margin-bottom: 15px; 
            border-radius: 12px; 
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1); 
            display: flex; 
            justify-content: space-between; 
            align-items: center;
        }
        .match-info { font-size: 1.2rem; font-weight: 600; color: #1e293b; }
        .venue { font-size: 0.9rem; color: #64748b; margin-top: 4px; }
        .btn-score { 
            background-color: #10b981; 
            color: white; 
            padding: 10px 20px; 
            border-radius: 8px; 
            text-decoration: none; 
            font-weight: bold; 
            transition: background 0.2s;
        }
        .btn-score:hover { background-color: #059669; }
        .no-matches { text-align: center; color: #64748b; padding: 40px; }
    </style>
</head>
<body>
    <header style="background: #1e3a8a; color: white; padding: 20px; text-align: center;">
        <h1>🏏 Scorer Assignment</h1>
    </header>

    <div class="match-list">
        <h2 style="margin-bottom: 25px; color: #334155;">Active Live Matches</h2>
        
        <% 
            List<Match> matches = (List<Match>) request.getAttribute("matches");
            if (matches != null && !matches.isEmpty()) {
                for(Match m : matches) { 
        %>
            <div class="match-card">
                <div>
                    <div class="match-info"><%= m.getTeamA() %> <span style="color: #94a3b8;">vs</span> <%= m.getTeamB() %></div>
                    <div class="venue">📍 <%= m.getVenue() != null ? m.getVenue() : "General Grounds" %></div>
                </div>
                <a href="${pageContext.request.contextPath}/scoreEntry?matchId=<%= m.getMatchId() %>" class="btn-score">
    Start Scoring
				</a>            
				</div>
        <% 
                }
            } else { 
        %>
            <div class="no-matches card">
                <p>No live matches found. Ask Admin to create a match.</p>
            </div>
        <% } %>
    </div>
</body>
</html>