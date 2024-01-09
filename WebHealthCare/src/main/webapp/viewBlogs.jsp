<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Suivi.SuiviDAO, User.User, Comment.GetAllCommentsByBlogServlet,Comment.Comment" %>
<%  User user = (User) session.getAttribute("user"); %>
<%@ page import="DAO.UserDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Blogs</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background-color: #D9EAFE; /* Arrière-plan rose clair */
        }

        html {
            box-sizing: border-box;
        }

        *, *:before, *:after {
            box-sizing: inherit;
        }

        .blog-list {
            list-style: none;
            padding: 0;
        }

        .blog-item {
            background: #C6DFFE;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            margin-bottom: 20px;
            display: flex;
            align-items: center; /* Aligner les éléments verticalement */
        }

        .blog-image {
            max-width: 10%;
            height: auto;
            border-radius: 5px;
            margin-right: 20px; /* Ajouter de l'espace entre l'image et le texte */
        }

        .blog-info {
            flex: 1; /* Prendre autant d'espace que possible */
        }

        .blog-title, .blog-description, .blog-creator {
            margin: 0;
        }

        .blog-creator {
            margin-top: 10px; /* Ajouter de l'espace en haut du nom de l'auteur */
        }


        /* Ajoutez d'autres styles selon vos besoins */
    </style>
     <!-- Meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:100,200,300,400,500,600,700,800,900" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Styles supplémentaires -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-eduwell-style.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/lightbox.css">
</head>
<header class="header-area header-sticky">
    <!-- Contenu de l'en-tête -->
    <div class="container">
        <div class="row">
            <div class="col-12">
                <!-- Navigation principale -->
                <nav class="main-nav">
                    <!-- Logo -->
                    <a href="home.jsp" class="logo">
                        <img src="assets/images/logo1.png" style="width: 20%; height: auto;" alt="EduWell Template">
                        <span style="font-size: 14px;">HealthCare</span> 
                    </a>
                    
                    <!-- Menu -->
                    <ul class="nav">
                        <!-- Liens de navigation -->
                        <li class="scroll-to-section"><a href="home.jsp" class="active">Home</a></li>
                        <li class="scroll-to-section"><a href="#services">Services</a></li>
                        <li class="scroll-to-section"><a href="#courses">About Endometriosis</a></li>
                        
                        <!-- Connexion/Inscription -->
                        <% if (user == null) { %> 
                            <li class="has-sub">
                                <a href="javascript:void(0)">Wanna Know How?</a>
                                <ul class="sub-menu">
                                    <li><a href="Login.jsp"> Log In</a></li>
                                    <li><a href="Signup.jsp">Sign up</a></li>
                                </ul>
                            </li>
                        <% } else { %>
                            <li>
                                <a href="LogoutServlet">Log Out</a>
                            </li>
                        <% } %>
                        
                        <!-- Autres liens -->
                        <li class="scroll-to-section"><a href="#testimonials">Informations</a></li> 
                         <li ><a href="ViewBlogsServlet">Our Blogs</a></li> 
                    </ul>        
                    <!-- Bouton de menu pour les petits écrans -->
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                </nav>
            </div>
        </div>
    </div>
</header>

<body>
<br>
<br>
<br>
<br>
<br>
    <h2>Liste des Blogs</h2>

    <c:if test="${not empty blogs}">
        <ul class="blog-list">
        
            <c:forEach var="blog" items="${blogs}">
                <li class="blog-item">
                    <!-- Image à gauche -->
                    <img class="blog-image" src="${blog.image}" alt="Image du blog ${blog.title}">
                    <!-- Informations à droite -->
                    <div class="blog-info">
                        <h3 class="blog-title">Titre : ${blog.title}</h3>
                        <p class="blog-description">Description : ${blog.description}</p>
                        <p class="blog-creator">Créé par : ${blog.user.getusername()}</p>
                        <h3>Les commentaires:</h3>
                        <!-- Afficher les commentaires -->
                        <ul>
                        
                            <c:forEach var="comment" items="${blog.comments}">
                                <li>
                                   <p>Posted by:  ${comment.user.username} : ${comment.getComment()}
<br>Date: ${comment.getDate()}</p>
  
                                    
                                    <!-- Ajoutez d'autres détails du commentaire si nécessaire -->
                                </li>
                            </c:forEach>
                          </ul>
         <form action="AddCommentServlet" method="post">
            <input type="hidden" name="blogId" id="blogId" value="${blog.getID()}">
            <textarea name="comment" id="comment" placeholder="Ajouter un commentaire" required></textarea>
            <button type="submit">Ajouter</button>
        </form>
                       
                        
                        <!-- Ajoutez d'autres détails du blog si nécessaire -->
                    </div>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty blogs}">
        <p>Aucun blog disponible.</p>
    </c:if>
</body>
</html>
