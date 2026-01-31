<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 1. Get the current session
    HttpSession userSession = request.getSession(false);
    
    if (userSession != null) {
        // 2. Clear all attributes and invalidate the session
        userSession.invalidate();
    }
    
    // 3. Redirect back to login page with a success message
    response.sendRedirect("login.jsp?msg=You have been logged out successfully");
%>