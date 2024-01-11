<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="User.User" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Publication d'un Blog</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background-color: #D9EAFE; 
        }

        html {
            box-sizing: border-box;
        }

        *, *:before, *:after {
            box-sizing: inherit;
        }

        h2 {
            margin: 20px 0;
        }

        form {
            background: #C6DFFE;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 80%; 
            max-width: 800px; 
            margin: auto; 
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input[type="text"],
        textarea {
            width: 100%; 
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        input[type="file"] {
            margin-top: 10px; 
        }

        button {
            background: #FE96A0; 
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            margin-top: 15px; 
        }

        button:hover {
            background: #FE96A0; 
        }
    </style>
</head>
<body>
    <h2>Publication d'un Blog <%= user.getusername() %></h2>
    <form action="PublishBlogServlet" method="post" enctype="multipart/form-data">
        <label for="title">Titre :</label>
        <input type="text" id="title" name="title" required>

        <label for="description">Description :</label>
        <textarea id="description" name="description" required></textarea>

        <label for="image">Choisir une image :</label>
        <input type="file" name="image" accept="image/*">

        <button type="submit">Publier</button>
    </form>
</body>
</html>
