<%@ page import="java.util.List" %>
<%@ page import="Form.Questions" %>
<%@ page import="Form.QuestionsDAO" %>
<%@ page import="Form.QuestionsDaoImpl" %>
<%@ page import="Form.ResponseMode" %>
<%@ page import="DAO.DAOFactory" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="User.User" %>
<%

User user = (User) session.getAttribute("user");
%>
<%
DAOFactory daoFactory = DAOFactory.getInstance();
QuestionsDAO questionsDAO = new QuestionsDaoImpl(daoFactory);


List<Questions> questionsList = questionsDAO.getQuestionsWithChoices();


List<ResponseMode> responseModes = questionsDAO.getResponseModes();


Map<Integer, Integer> responseModesMap = responseModes.stream()
        .collect(Collectors.toMap(ResponseMode::getIdMode, ResponseMode::getIdMode));
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Questions Form</title>
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

        h1, h2 {
            margin: 0;
        }

        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        form {
            background: #C6DFFE;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 80%; 
            max-width: 800px; 
            overflow-y: auto; 
            max-height: 70vh; 
        }

        .question-container {
            margin-bottom: 20px;
        }

        .choices-container {
            display: block; 
            margin-top: 10px;
        }

        .choice {
            margin-bottom: 5px; 
            display: flex;
            align-items: center; 
        }

        input[type="text"],
        input[type="checkbox"],
        input[type="radio"] {
            width: auto; 
            margin-right: 5px; 
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

        label {
            display: inline-block;
            text-align: left;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <h1>Remplissez le formulaire soigneusement <%= user.getusername() %></h1>
    <br>

    <form action="ProcessDiagnosticFormServlet" method="post">
        <% for (Questions question : questionsList) { %>
        <div class="question-container">
            <label><%= question.getQuestion() %>:</label>

            <% int idMode = responseModesMap.get(question.getId_mode()); %>
            <% if (2 == idMode) { %>
                <!-- Checkbox type -->
                <div class="choices-container">
                    <% List<String> choices = question.getChoices(); %>
                    <% for (String choice : choices) { %>
                        <div class="choice">
                            <input type="checkbox" name="<%= question.getId_question() %>" value="<%= choice %>">
                            <%= choice %>
                        </div>
                    <% } %>
                </div>
            <% } else if (3 == idMode) { %>
                <!-- Radio type -->
                <div class="choices-container">
                    <% List<String> choices = question.getChoices(); %>
                    <% for (String choice : choices) { %>
                        <div class="choice">
                            <input type="radio" name="<%= question.getId_question() %>" value="<%= choice %>">
                            <%= choice %>
                        </div>
                    <% } %>
                </div>
            <% } else if (1 == idMode) { %>
                <!-- Text type -->
                <input type="text" name="<%= question.getId_question() %>">
            <% } %>
        </div>
        <% } %>

        <input type="submit" value="Enregistrer">
    </form>
</body>
</html>
