<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>ScoreTracker | Login</title>
    <link rel="stylesheet" type="text/css" href="	style.css">
</head>
<body>
    <header>
        <h1>🏏 ScoreTracker</h1>		
    </header>
    
    <div class="container">
        <div class="card">
            <h2>Welcome Back</h2>
            <form action="login" method="post">
                <label>Username</label>
                <input type="text" name="username" placeholder="Enter username" required>
                
                <label>Password</label>
                <input type="password" name="password" placeholder="Enter password" required>
                
                <button type="submit">Login to Dashboard</button>
            </form>
            <% if(request.getParameter("error") != null) { %>
                <p style="color:red; text-align:center;"><%= request.getParameter("error") %></p>
            <% } %>
        </div>
    </div>
</body>
</html>