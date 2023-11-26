<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
</head>
<body>
    <h1>Reset Password</h1>
    <form action="ResetPasswordServlet" method="post">
        New Password:
        <input type="password" name="newPassword" required>
        Confirm New Password:
        <input type="password" name="confirmPassword" required>
        <input type="hidden" name="token" value="<%= request.getParameter("token") %>">
        <input type="submit" value="Reset Password">
    </form>
</body>
</html>
