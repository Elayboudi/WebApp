<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Publication d'un Blog</title>
</head>
<body>
    <h2>Publication d'un Blog</h2>
    <form action="PublishBlogServlet" method="post">
        <label for="userId">ID Utilisateur:</label>
        <input type="text" id="userId" name="userId" required><br>
        <label for="title">Titre:</label>
        <input type="text" id="title" name="title" required><br>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea><br>
        <label for="image">URL de l'image:</label>
        <input type="text" id="image" name="image" required><br>
        <button type="submit">Publier</button>
    </form>
</body>
</html>
