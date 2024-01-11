<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Form.Questions" %>
<%@ page import="Form.formulaire" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="Form.DiagnosticResultCalculator" %>
<%@ page import="Form.QuestionsDAO" %>
<%@ page import="DAO.DAOFactory" %>
<%@ page import="Form.FormDAO" %>
<%@ page import="Form.FormDaoImpl" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="User.User" %>
<%

User user = (User) session.getAttribute("user");
%>

<%

List<Questions> questions = (List<Questions>) session.getAttribute("questions");
List<formulaire> responses = (List<formulaire>) session.getAttribute("responses");
String diagnosticResult = (String) request.getAttribute("diagnosticResult");


DiagnosticResultCalculator resultCalculator = new DiagnosticResultCalculator();
int totalScore = resultCalculator.calculateTotalScore(questions, responses);
String resultCategory = resultCalculator.calculateResult(request.getParameterMap());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rapport de Diagnostic</title>
    <style>
        body {
            background-color: #D9EAFE;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 20px;
        }

        .center-text {
            text-align: center;
        }

        h2, p {
            color: #333;
        }

        p {
            color: #555;
        }

        h3 {
            color: #333;
        }

        strong {
            color: #FE96A0;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            color: #555;
        }

        .return-home-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #FE96A0;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 15px;
            text-align: center
        }

        .return-home-btn:hover {
            background-color: #D77088;
        }
        
    </style>
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
                        <li class="scroll-to-section"><a href="#contact-section">Contact Us</a></li> 
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
<br>

<h2 class="center-text">Rapport de Diagnostic <%= user.getusername() %></h2>

<p class="center-text">Résultat du Diagnostic : <%= diagnosticResult %></p>

<h3>Réponses aux Questions :</h3>

<% for (Questions question : questions) { %>
    <p><strong><%= question.getQuestion() %></strong></p>
    <p>Réponse(s) :</p>
    <ul>
        <% for (formulaire reponse : responses) {
               if (reponse.getid_question() == question.getId_question()) { %>
            <li><%= reponse.getresponse() %></li>
        <% } } %>
    </ul>
<% } %>

<p>Résultat Global : <%= resultCategory %></p>

<div class="center-text">
    <a href="home.jsp" class="return-home-btn">Retour à Home</a>
</div>

</body>
</html>
