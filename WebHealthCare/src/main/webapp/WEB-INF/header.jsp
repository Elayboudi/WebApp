<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Votre Titre</title>
    <!-- Ajoutez ici vos liens CSS, scripts, etc. -->
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
                        <c:choose>
                            <c:when test="${empty user}">
                                <li class="has-sub">
                                    <a href="javascript:void(0)">Wanna Know How?</a>
                                    <ul class="sub-menu">
                                        <li><a href="Login.jsp"> Log In</a></li>
                                        <li><a href="Signup.jsp">Sign up</a></li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="LogoutServlet">Log Out</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        
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
