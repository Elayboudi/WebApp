<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>
    <h1>Forgot Password</h1>
    <form action="ForgotPasswordServlet" method="post">
        Enter your email address:
        <input type="text" name="email" required placeholder="enter your email">
       
        <input type="submit" value="Submit">
    </form>
</body>
</html>
