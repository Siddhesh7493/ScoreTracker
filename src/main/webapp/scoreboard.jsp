<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cricket.util.ScoreSummary" %>
<html>
<head>
    <title>LED Live Scoreboard | ScoreTracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
        body { 
            background: #0f172a; 
            display: flex; 
            justify-content: center; 
            align-items: center; 
            height: 100vh; 
            margin: 0; 
            font-family: 'Courier New', monospace;
        }
        .led-board { 
            background: #000; 
            border: 8px solid #334155; 
            border-radius: 15px; 
            width: 90%; 
            max-width: 1000px;
            padding: 40px; 
            color: #fbbf24; 
            box-shadow: 0 0 50px rgba(0,0,0,0.7); 
        }
        .main-score { 
            font-size: 10rem; 
            line-height: 1; 
            text-align: center;
            border-bottom: 2px solid #1e293b; 
            padding-bottom: 20px; 
            margin-bottom: 20px; 
        }
        .overs-display { 
            font-size: 3.5rem; 
            color: #94a3b8; 
            display: block; 
            margin-top: 10px;
        }
        .player-row { 
            display: flex; 
            justify-content: space-between; 
            align-items: flex-end;
            margin-top: 30px; 
        }
        .batsmen-side { text-align: left; line-height: 1.4; }
        .bowler-side { text-align: right; line-height: 1.2; }
        .striker { font-size: 3rem; color: #fff; }
        .non-striker { font-size: 2.2rem; color: #64748b; }
        .bowler-name { font-size: 3rem; color: #fbbf24; text-transform: uppercase; }
        .bowler-stats { font-size: 2rem; color: #fff; margin-top: 5px; }
        .stat-label { font-size: 1rem; color: #475569; letter-spacing: 2px; }
    </style>
    <script>
        // Auto-refresh every 5 seconds for real-time feel
        setTimeout(function(){ location.reload(); }, 5000);
    </script>
</head>
<body>
    <div class="led-board">
        <% 
            ScoreSummary s = (ScoreSummary) request.getAttribute("score"); 
            if (s != null) { 
        %>
            <div style="text-align: center; color: #475569; font-size: 1.2rem; letter-spacing: 4px; margin-bottom: 10px;">
                MATCH ID: <%= request.getParameter("matchId") %>
            </div>

            <div class="main-score">
                <%= s.getTotalRuns() %> - <%= s.getTotalWickets() %>
                <span class="overs-display">OVERS: <%= s.getOversFormatted() %></span>
            </div>
            
            <div class="player-row">
                <div class="batsmen-side">
                    <div class="striker">
                        <%= (s.getStrikerName() != null) ? s.getStrikerName() : "---" %>* <span style="color: #fbbf24;"><%= s.getStrikerRuns() %>(<%= s.getStrikerBalls() %>)</span>
                    </div>
                    <div class="non-striker">
                        <%= (s.getNonStrikerName() != null) ? s.getNonStrikerName() : "---" %> 
                        <span><%= s.getNonStrikerRuns() %>(<%= s.getNonStrikerBalls() %>)</span>
                    </div>
                </div>
                
                <div class="bowler-side">
                    <div class="stat-label">CURRENT BOWLER</div>
                    <div class="bowler-name"><%= (s.getBowlerName() != null) ? s.getBowlerName() : "---" %></div>
                    <div class="bowler-stats">
                        <%= s.getBowlingStat() %>
                    </div>
                    <div class="stat-label">(O-D-R-W)</div>
                </div>
            </div>
        <% } else { %>
            <div style="text-align: center; font-size: 2rem;">WAITING FOR LIVE DATA...</div>
        <% } %>
    </div>
    <div style="margin-top: 25px; border-top: 1px solid #1e293b; padding-top: 15px;">
    <div style="color: #64748b; font-size: 1rem; text-transform: uppercase; margin-bottom: 10px;">Fall of Wickets</div>
    <div style="display: flex; gap: 20px; color: #fff; font-size: 1.2rem; flex-wrap: wrap;">
        <% 
            if (s.getWicketHistory() != null && !s.getWicketHistory().isEmpty()) {
                for (String wxt : s.getWicketHistory()) { 
        %>
            <span style="background: #1e293b; padding: 5px 12px; border-radius: 4px;">
                <%= wxt %>
            </span>
        <% 
                }
            } else { 
        %>
            <span style="color: #475569;">No wickets fallen yet</span>
        <% } %>
    </div>
</div>
</body>
</html>