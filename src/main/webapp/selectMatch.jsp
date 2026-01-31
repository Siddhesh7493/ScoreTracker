<%@ page import="java.util.List, com.cricket.util.Match" %>
<html>
<head><title>Select Match</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>🏏 Select a Live Match to Score</h2>
    <ul>
        <% 
            List<Match> matches = (List<Match>) request.getAttribute("matches");
            if(matches != null) {
                for(Match m : matches) {
        %>
            <li>
                <%= m.getTeamA() %> vs <%= m.getTeamB() %> 
                <a href="scoreEntry.jsp?matchId=<%= m.getMatchId() %>">Select</a>
            </li>
        <%      } 
            } 
        %>
    </ul>
</body>
</html>