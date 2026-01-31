<%@ page import="com.cricket.util.ScoreSummary" %>
<html>
<head>
    <title>Live Scoreboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <header><h1>🏏 ScoreTracker Live</h1></header>
    
    <div class="container">
        <div class="card" style="text-align:center;">
            <% 
                // Get the summary object passed by the Servlet
                ScoreSummary s = (ScoreSummary) request.getAttribute("score"); 
                if(s != null) { 
            %>
                <h2>Current Score</h2>
                <div class="live-score-box">
                    <span class="score-text">
                        <%= s.getTotalRuns() %> / <%= s.getTotalWickets() %>
                    </span>
                </div>
                <p>Match ID: <%= request.getParameter("matchId") %></p>
            <% } else { %>
                <div class="card">
                    <h2>⚠️ No Data Available</h2>
                    <p>Please ensure you accessed this page through the dashboard.</p>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>