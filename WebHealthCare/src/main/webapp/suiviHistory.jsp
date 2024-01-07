<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Suivi History</title>
</head>
<body>

<h2>Suivi History</h2>

<c:if test="${not empty suivis}">
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Degré de Douleur</th>
            <!-- Ajoutez d'autres colonnes en fonction de votre modèle de données -->
        </tr>
        <c:forEach var="suivi" items="${suivis}">
            <tr>
                <td>${suivi.id}</td>
                <td>${suivi.degreDouleur}</td>
                <!-- Ajoutez d'autres colonnes en fonction de votre modèle de données -->
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${empty suivis}">
    <p>Aucun suivi disponible.</p>
</c:if>

</body>
</html>
