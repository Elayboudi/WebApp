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
    <title>Formulaire de Suivi</title>
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

        input[type="checkbox"],
        input[type="number"] {
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background: #FE96A0;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            margin-top: 15px; 
        }

        input[type="submit"]:hover {
            background: #FE96A0; 
        }
         .center {
            text-align: center;
    </style>
</head>
<body>
    <h2 class =center>Formulaire de Suivi pour <%= user.getusername() %></h2>
    <br>
     <br>
     <br>
     <br>
    
    <form action="ProcessSuiviFormServlet" method="post">

        
        <label for="localisation_douleurs">Localisation des douleurs :</label>
        <input type="checkbox" name="localisation_douleurs" value="Abdomen">Abdomen
        <input type="checkbox" name="localisation_douleurs" value="Dos">Dos
        <input type="checkbox" name="localisation_douleurs" value="Poitrine">Poitrine
        <input type="checkbox" name="localisation_douleurs" value="Tete">Tete
        <input type="checkbox" name="localisation_douleurs" value="Cou">Cou
        <input type="checkbox" name="localisation_douleurs" value="Hanches">Hanches
        <br>

        <label for="symptomes">Symptômes :</label>
        <input type="checkbox" name="symptomes" value="Crampes">Crampes
        <input type="checkbox" name="symptomes" value="Seins sensibles">Seins sensibles
        <input type="checkbox" name="symptomes" value="Mal de tete">Mal de tete
        <input type="checkbox" name="symptomes" value="Acné">Acné
        <input type="checkbox" name="symptomes" value="Fatigue">Fatigue
        <input type="checkbox" name="symptomes" value="Ballonements">Ballonements
        <input type="checkbox" name="symptomes" value="Envie de manger">Envie de manger
        <br>

       
        <label for="ce_qui_aggrave_douleur">Ce qui aggrave la douleur :</label>
        <input type="checkbox" name="ce_qui_aggrave_douleur" value="Manque de sommeil">Manque de sommeil
        <input type="checkbox" name="ce_qui_aggrave_douleur" value="Assis">Assis
        <input type="checkbox" name="ce_qui_aggrave_douleur" value="Debout">Debout
        <input type="checkbox" name="ce_qui_aggrave_douleur" value="Stress">Stress
        <input type="checkbox" name="ce_qui_aggrave_douleur" value="Marche">Marche
        <input type="checkbox" name="ce_qui_aggrave_douleur" value="Exercice">Exercice
        <input type="checkbox" name="ce_qui_aggrave_douleur" value="Miction">Miction
        <br>

       
        <label for="sentiments">Sentiments :</label>
        <input type="checkbox" name="sentiments" value="Anxieux">Anxieux
        <input type="checkbox" name="sentiments" value="Déprimé">Déprimé
        <input type="checkbox" name="sentiments" value="Etourdi">Etourdi
        <input type="checkbox" name="sentiments" value="Vomissements">Vomissements
        <input type="checkbox" name="sentiments" value="Diarhée">Diarhée
      
        <label for="degre_douleur">Degré de douleur :</label>
        <input type="range" name="degre_douleur" min="0" max="10">
        <br>

       

        <input type="submit" value="Soumettre">
    </form>

   
</body>
</html>
