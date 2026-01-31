<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cricket.util.User" %>
<html>
<head>
    <title>Dashboard | ScoreTracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <header>
        <h1>🏏 ScoreTracker</h1>
        <span>Welcome, <strong><%= user.getUsername() %></strong> (<%= user.getRole() %>)</span>
    </header>

    <div class="container">
        <div class="card">
            <h2>Quick Actions</h2>
            <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 15px; margin-top: 20px;">
                
                <% if(user.getRole().equals("admin")) { %>
                    <button onclick="location.href='createMatch.jsp'">🆕 Create New Match</button>
                <% } %>
                
                <button onclick="location.href='selectMatch'" style="background-color: var(--primary);">📊 View Live Matches</button>
                
                <button onclick="location.href='logout.jsp'" style="background-color: #ef4444; grid-column: span 2;">Logout</button>
            </div>
        </div>

        <div class="card">
            <h2>Recent Activity</h2>
            <p style="color: #6b7280;">Welcome to your Cricket Management Dashboard. Select an action above to get started.</p>
        </div>
    </div>
</body>
</html>