<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="Blog.Blog" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vos Blogs</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #D9EAFE; /* Light blue background */
            color: #333; /* Dark gray text color */
            margin: 0;
        }

        h2 {
            margin: 20px;
            color: #FE96A0; /* Pink heading color */
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            background: #C6DFFE; /* Light blue background for each blog */
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 20px;
        }

        .blog-content {
            flex: 1;
            margin-right: 20px;
        }

        img {
            max-width: 100%;
            max-height: 200px;
            border-radius: 5px;
        }

        .button-container {
            display: flex;
            align-items: center;
        }

        .action-button {
            background-color: #FE96A0; /* Pink button color */
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            margin-right: 10px;
            cursor: pointer;
        }

        a.back-link {
            display: block;
            margin-top: 20px;
            color: #FE96A0; /* Pink color for back link */
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h2>Vos Blogs</h2>
    
    <c:if test="${not empty userBlogs}">
        <ul>
            <c:forEach var="blog" items="${userBlogs}">
                <li>
                    <div class="blog-content">
                        <h3>Titre : ${blog.getTitle()}</h3>
                        <p>Description : ${blog.getDescription()}</p>
                    </div>
                    <img src="${blog.getImage()}" alt="Image du blog ${blog.getTitle()}">
                    <div class="button-container">
                        <button class="action-button" onclick="location.href='UpdateBlogServlet?blogId=${blog.getID()}'">Modifier</button>
                        <button class="action-button" onclick="location.href='DeleteBlogServlet?blogId=${blog.getID()}'">Supprimer</button>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    
    <a href="${pageContext.request.contextPath}/user-blogs" class="back-link">Retour</a>
</body>
</html>
