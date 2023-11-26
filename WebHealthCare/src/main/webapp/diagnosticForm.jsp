<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- diagnosticForm.jsp -->
<%@ page import="User.User" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
// Récupérer l'objet user depuis la session
User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- ... Autres balises HEAD ... -->
</head>
<body>
    <h2>Formulaire de diagnostic pour <%= user.getusername() %></h2>
    <form action="processDiagnosticForm" method="post">
        <!-- Question 1 -->
        <label for="q1">Question 1 : Quand est-ce que vous commencez vos règles ?</label>
        <input type="radio" name="q1" value="oui">Avant lage de 11 ans
        <input type="radio" name="q1" value="non">À partir de 11 ans
        <br>

        <!-- Question 2 -->
        <label for="q2">Question 2 : Quelle est la durée moyenne de votre cycle menstruel ?</label>
        <input type="radio" name="q2" value="moins">Moins de 27 jours
        <input type="radio" name="q2" value="plus"> Plus de 27 jours
        <input type="radio" name="q2" value="neutre"> Je sais pas 

        <br>
         <!-- Question 3-->
        <label for="q3">Question 3 :Avez-vous des antécédents familiaux d'endométriose?</label>
        <input type="radio" name="q3" value="oui">Oui
        <input type="radio" name="q3" value="non"> Non
       
        <br>
         <!-- Question 4 -->
        <label for="q4">Question 4 : Avez-vous déià accouché?</label>
        <input type="radio" name="q4" value="oui">Oui
        <input type="radio" name="q4" value="non"> Non
        
        <br>
         <!-- Question 5 -->
        <label for="q5">Question 5 : Avez-vous des difficultés à tomber enceinte?</label>
        <input type="radio" name="q5" value="oui">Oui
        <input type="radio" name="q5" value="non">Non
        
        <br>
         <!-- Question 2 -->
        <label for="q2">Question 6 : Indice de masse corporelle (IMC) ?</label>
        <input type="radio" name="q6" >Entrez votre poids en kg 
        <input type="radio" name="q6" > Entrez votre taille en cm
       
        <br>
         <!-- Question 2 -->
        <label for="q7">Question 7: Quelle est l'intensité de vos douleurs abdominales/pelviennes ?</label>
        <input type="radio" name="q7" value="">0-2
        <input type="radio" name="q7" value=""> 3-5
        <input type="radio" name="q7" value=""> 6-8
        <input type="radio" name="q7" value=""> 9-10

        <br>
        <br>
         <!-- Question 2 -->
        <label for="q8">Question 8: Quand ressentez-vous des douleurs abdominales ou pelviennes ?</label>
        <input type="radio" name="q8" value="">lier aux périodes menstruelles
        <input type="radio" name="q8" value=""> lier aux ovulations
        <input type="radio" name="q8" value=""> Sans lien avec la période menstruelle ou l'ovulation
       
        <br>
        <br>
         <!-- Question 2 -->
        <label for="q9">Question 9: Quelle est la sévérité des douleurs pendant les rapports sexuels ?</label>
        <input type="radio" name="q7" value="">0-2
        <input type="radio" name="q7" value=""> 3-5
        <input type="radio" name="q7" value=""> 6-8
        <input type="radio" name="q7" value=""> 9-10

        <br>
        <br>
         <!-- Question 2 -->
        <label for="q10">Question 10: Qu'est-ce qui aggrave vos douleurs ?</label>
        <input type="radio" name="q7" value="">Les mouvements intestinaux
        <input type="radio" name="q7" value="">  La vessie pleine
        <input type="radio" name="q7" value=""> La miction (uriner)
        <input type="radio" name="q7" value=""> L'orgasme
        <input type="radio" name="q7" value=""> Sans lien avec quoi que ce soit
        <br>
        <br>
         <!-- Question 2 -->
     <label for="q11">Question 11: Durée de la période menstruelle ?</label>
        <input type="radio" name="q7" value="">1 à 7 jours
        <input type="radio" name="q7" value=""> Plus de 7 jours
        <br>
        <br>
         <!-- Question 2 -->
        <label for="q12">Question 12: Nature des périodes menstruelles ?</label>
        <input type="radio" name="q7" value="">Abondantes
        <input type="radio" name="q7" value="">  Modérées
        <input type="radio" name="q7" value=""> Légères
        <br>
        <!-- Question 2 -->

        <label for="q13">Question 13:  Quel est le schéma de votre cycle menstruel ?</label>
        <input type="radio" name="q7" value="">Régulier
        <input type="radio" name="q7" value=""> Irrégulier
        <input type="radio" name="q7" value="">Saignement ou spotting entre les périodes
        <br>  
      <!-- Question 2 -->
        <label for="q14">Question 14: Avez-vous ?</label>
        <input type="radio" name="q7" value="">Du sang dans vos selles
        <input type="radio" name="q7" value="">   La diarrhée
        <input type="radio" name="q7" value="">La constipation
        <input type="radio" name="q7" value="">Des ballonnements abdominaux
        <br>
         <label for="q15">Question 15 : Avez-vous déià été victime d'abus physique ou sexuel ?</label>
        <input type="radio" name="q1" value="oui">Oui
        <input type="radio" name="q1" value="non">Non
        <br>
        <!-- Ajoutez d'autres questions selon vos besoins -->

        <input type="submit" value="Soumettre">
    </form>

    <!-- ... Reste du contenu de la page ... -->
</body>
</html>

