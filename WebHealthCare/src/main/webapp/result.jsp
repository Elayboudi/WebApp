<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Suivi.SuiviDAO, User.User" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="DAO.DAOFactory" %>

<%
    // Récupération des données depuis la session
    double moyenneDouleur = (Double) session.getAttribute("moyenneDouleur");
    Set<String> symptomes = (Set<String>) session.getAttribute("symptomes");
    Set<String> aggravations = (Set<String>) session.getAttribute("aggravations");
    Set<String> sentiments = (Set<String>) session.getAttribute("sentiments");
    Set<String> localisations = (Set<String>) session.getAttribute("localisations");
    User user = (User) session.getAttribute("user");
    Map<String, Double> symptomesPercentages = (Map<String, Double>) session.getAttribute("symptomesPercentages");
    Map<String, Double> sentimentsPercentages = (Map<String, Double>) session.getAttribute("sentimentsPercentages");
    Map<String, Double> aggravationPercentages = (Map<String, Double>) session.getAttribute("aggravationPercentages");
    Map<String, Double> localisationPercentages = (Map<String, Double>) session.getAttribute("localisationPercentages");

    // Récupération des données pour le graphique d'évolution
    List<String> dates = (List<String>) session.getAttribute("dates");
    List<Integer> degrees = (List<Integer>) session.getAttribute("degrees");
%>

<html>
<head>
    <!-- Titre de la page -->
    <title>Résultat du Suivi</title>
    <!-- Bibliothèque Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    <!-- Styles -->
    <style>
        #container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* 100% de la hauteur de la vue (viewport height) */
        }
.center-text {
            text-align: center;
            color:#FE96A0;
        }
        .center {
            text-align: center;
            
        }
        #localisationsChart {
            width: 100px;
            height: 100px;
        }
        .container {
            display: flex;
            justify-content: space-around;
        }

        .result-container {
            width: 30%; /* Ajustez la largeur selon vos besoins */
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px;
            text-align: center;
        }

        .section-title {
            font-weight: bold;
        }
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

<!-- En-tête de la page avec le logo et la navigation -->
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

<!-- Corps de la page -->
<body style="margin-top: 100px;">
    <!-- Titre et moyenne de douleur -->
    <br>
    <h2 class=center-text>Vos résultats</h2>
    <br>
    <br>
    <h2 class=center>Moyenne de douleur : <%= moyenneDouleur %></h2>

    <!-- Graphique d'évolution de la douleur -->
    <canvas id="evolutionChart"></canvas>

    <!-- Affichage des sentiments -->
    
    <div class="container">
        <!-- Container pour les sentiments -->
        <div class="result-container">
            <p class="center-text section-title">Sentiments :</p>
            <% if (sentiments != null && sentimentsPercentages != null) {
                for (String sentiment : sentiments) {
                    double sentimentPercentage = sentimentsPercentages.get(sentiment);
            %>
                    <p><%= sentiment %> : <%= sentimentPercentage %> %</p>
            <% }
            } else {
                out.println("<p>Aucun symptôme disponible.</p>");
            }
            %>
        </div>

        <!-- Container pour les symptômes -->
        <div class="result-container">
            <p class="center-text section-title">Symptômes :</p>
            <% if (symptomes != null && symptomesPercentages != null) {
                for (String symptome : symptomes) {
                    double symptomePercentage = symptomesPercentages.get(symptome);
            %>
                    <p><%= symptome %> : <%= symptomePercentage %> %</p>
            <% }
            } else {
                out.println("<p>Aucun symptôme disponible.</p>");
            }
            %>
        </div>

        <!-- Container pour les éléments aggravant la douleur -->
        <div class="result-container">
            <p class="center-text section-title">Éléments qui aggravent la douleur :</p>
            <% if (aggravations != null && aggravationPercentages != null) {
                for (String aggravation : aggravations) {
                    double aggravationPercentage = aggravationPercentages.get(aggravation);
            %>
                    <p><%= aggravation %> : <%= aggravationPercentage %> %</p>
            <% }
            } else {
                out.println("<p>Aucun symptôme disponible.</p>");
            }
            %>
        </div>
    </div>

<br>
    <!-- Graphique de localisation des douleurs -->
    <div id="container">
        <div>
            <!-- Titre -->
            <h2 class=center-text >localisations  des douleurs</h2>
            
            <!-- Graphique de localisation des douleurs -->
            <canvas id="localisationsChart" style="width: 100px; height: 100px;"></canvas>

            <!-- Pourcentages des localisations -->
            <p>Pourcentages des localisations :</p>
            <ul>
                <% if (localisationPercentages != null && !localisationPercentages.isEmpty()) {
                    for (Map.Entry<String, Double> entry : localisationPercentages.entrySet()) {
                        String localisation = entry.getKey();
                        double percentage = entry.getValue();
                %>
                        <li><%= localisation %> : <%= percentage %> %</li>
                <% }
                } else {
                %>
                        <li>Aucune donnée de pourcentage de localisation disponible.</li>
                <% }
                %>
            </ul>
        </div>
    </div>

    <!-- Script pour le graphique de localisation -->
    <script>
        var localisationsLabels = [<%
            Map<String, Integer> localisationsCount = new HashMap<>();
            for (String localisation : localisations) {
                localisationsCount.put(localisation, localisationsCount.getOrDefault(localisation, 0) + 1);
            }
            int totalLocalisations = localisations.size();
            for (Map.Entry<String, Integer> entry : localisationsCount.entrySet()) {
                String localisation = entry.getKey();
                int count = entry.getValue();
                double percentage = (count / (double) totalLocalisations) * 100;
                out.print("\"" + localisation + "\",");
            }
        %>];

        var localisationsData = {
            labels: localisationsLabels,
            datasets: [{
                data: [<%
                    for (Map.Entry<String, Double> entry : localisationPercentages.entrySet()) {
                        double percentage = entry.getValue();
                        out.print(percentage + ",");
                    }
                %>],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.7)',
                    'rgba(54, 162, 235, 0.7)',
                    'rgba(255, 206, 86, 0.7)',
                    'rgba(100, 700, 80, 0.7)'
                    // Ajoutez plus de couleurs si nécessaire
                ],
            }],
        };

        var localisationsChart = new Chart(document.getElementById('localisationsChart'), {
            type: 'doughnut',
            data: localisationsData,
        });
    </script>

    <!-- Script pour le graphique d'évolution -->
    <script>
        const dates = <%= new Gson().toJson(dates) %>.reverse();
        const degrees = <%= new Gson().toJson(degrees) %>.reverse();
        console.log("Dates:", dates);
        console.log("Degrees:", degrees);

        const evolutionData = {
            labels: dates,
            datasets: [{
                label: 'Évolution des degrés de douleur',
                data: degrees,
                fill: false,
                cubicInterpolationMode: 'monotone',
                tension: 0.4
            }]
        };
        
        new Chart(document.getElementById('evolutionChart'), {
            type: 'line',
            data: evolutionData,
            options: {
                responsive: true,
                interaction: {
                    intersect: false,
                },
                scales: {
                    x: {
                        display: true,
                        title: {
                            display: true
                        }
                    },
                    y: {
                        display: true,
                        title: {
                            display: true,
                            text: 'Degré de douleur'
                        },
                        suggestedMin: 0,
                        suggestedMax: 10
                    }
                }
            },
        });
    </script>
</body>
</html>
