<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta charset="UTF-8">
    <title>Modifier Utilisateur</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: #D9EAFE; /* Arrière-plan rose clair */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background: #C6DFFE;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 30%;
        }

        .logo {
            margin-bottom: 20px;
        }

        .form-group {
            width: 100%;
            margin-bottom: 20px;
            position: relative;
        }

        .form-group label {
            display: block;
            text-align: left;
            font-size: 18px;
        }

        .icon-input {
            position: relative;
        }

        .icon-input i {
            position: absolute;
            left: 10px;
            top: 50%;
            transform: translateY(-50%);
            color: #FE96A0; /* Couleur de l'icône */
            z-index: 1;
        }

        .icon-input input {
            width: 90%;
            padding-left: 30px; /* Ajustez la marge gauche pour laisser de l'espace à l'icône */
            border: 2px solid #FE96A0; /* Couleur de bordure de l'icône */
            border-radius: 4px;
        }

        .btn-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
            margin-top: 10px; /* Ajout de marges au-dessus du bouton Home */
        }

        .btn, .home-btn {
            flex: 1;
            background: #FE96A0; /* Couleur rose vive */
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            text-decoration: none; /* Ajout de cette ligne pour enlever le soulignement du lien */
            display: inline-block;
            text-align: center;
        }

        .btn:hover, .home-btn:hover {
            background: #FE96A0; /* Couleur rose foncé au survol */
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Modifier Vos Informations</h2>

        <form action="ModifyUserServlet" method="post">
            <div class="form-group">
                <div class="icon-input">
                    <i class="fas fa-user"></i>
                    <input type="text" id="username" name="username" value="${user.username}" required>
                </div>
            </div>
            <div class="form-group">
                <div class="icon-input">
                    <i class="fas fa-envelope"></i>
                    <input type="text" id="email" name="email" value="${user.email}" required>
                </div>
            </div>
            <div class="form-group">
                <div class="icon-input">
                    <i class="fas fa-lock"></i>
                    <input type="password" id="password" name="password" value="${user.password}" required>
                </div>
            </div>

            <input type="submit" class="btn" value="Modifier">
        </form>

        <div class="btn-container">
            <!-- Bouton "Home" avec le même style que le bouton "Modifier" -->
            <a href="home.jsp" class="home-btn">Home</a>
        </div>
    </div>
</body>
</html>
