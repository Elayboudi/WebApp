<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="Comment.Comment" %>

<!DOCTYPE html>
<html>
<head>
    <title>Comments</title>
    <!-- Ajoutez les styles CSS et scripts JavaScript nécessaires pour la boîte modale -->
    <link rel="stylesheet" href="path/to/modal-styles.css">
    <script src="path/to/modal-scripts.js"></script>
</head>
<body>
    <h2>Comments</h2>

    <c:if test="${not empty comments}">
        <ul>
            <c:forEach var="comment" items="${comments}">
                <li>
                    <p>${comment.comment}</p>
                    <p>Posted by: ${comment.user.username}</p>
                    <p>Date: ${comment.date}</p>
                    <c:if test="${user != null && user.id == comment.user.id}">
                        
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${user != null}">
        <!-- Boîte modale pour l'édition -->
    
        <!-- Section pour ajouter un commentaire -->
        <div>
            <h3>Ajouter un commentaire</h3>
            <form action="AddCommentServlet" method="post">
                <label for="newComment">Comment:</label>
                <textarea id="newComment" name="comment" required></textarea><br>
                
    

<button type="submit" name="blogId" value="${blog.getID()}">Ajouter Commentaire</button>


            </form>
        </div>
    </c:if>

    <script>
        // Fonctions JavaScript pour ouvrir et fermer la boîte modale
        function openEditModal(commentId, commentText) {
            document.getElementById('editCommentId').value = commentId;
            document.getElementById('editComment').value = commentText;
            document.getElementById('editModal').style.display = 'block';
        }

        function closeEditModal() {
            document.getElementById('editModal').style.display = 'none';
        }
    </script>
</body>
</html>
