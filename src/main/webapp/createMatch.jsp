<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Create Match | ScoreTracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <header>
        <h1>🏏 ScoreTracker</h1>
    </header>

    <div class="container">
        <div class="card">
            <h2>🆕 Setup New Match</h2>
            <form action="createMatch" method="post">
                <label>Team A</label>
                <input type="text" name="teamA" placeholder="Enter Team A Name" required>
                
                <label>Team B</label>
                <input type="text" name="teamB" placeholder="Enter Team B Name" required>
                
                <label>Venue</label>
                <input type="text" name="venue" placeholder="Stadium Name">
                
                <button type="submit">Start Match</button>
            </form>
            
            <div style="margin-top: 20px; text-align: center;">
                <a href="dashboard.jsp" style="text-decoration: none; color: #1e3a8a;">← Back to Dashboard</a>
            </div>
        </div>
    </div>
</body>
</html>