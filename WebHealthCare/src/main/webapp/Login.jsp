<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <title>Login to PainCare</title>
    <script>
        <% if (request.getAttribute("passwordError") != null) { %>
            document.addEventListener("DOMContentLoaded", function() {
                document.getElementById("password-error").style.display = "block";
            });
        <% } %>
    </script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: #D9EAFE; 
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
            padding-left: 30px; 
            border: 2px solid #FE96A0; 
            border-radius: 4px;
        }

        .btn-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
        }

        .btn {
            flex: 1;
            background: #FE96A0; 
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
        }

        .btn:hover {
            background: #FE96A0;
        }

        .link {
            text-decoration: none;
            color: #FE96A0; 
            font-size: 16px;
        }
        .error-message {
        display: block;
    }
    </style>
</head>

<body>
    <div class="login-container">
        <div class="logo">
          
            <img src="logo.png" alt="Logo PainCare">
        </div>

        <h2 style="color: #444;">Login to <span style="color: #FE96A0;">PainCare</span></h2>

        <form action="LoginServlet" method="post">
            <div class="form-group">
                <div class="icon-input">
                    <i class="fas fa-user"></i>
                    <input type="text" name="username" required placeholder="Enter Your E-mail .....">
                </div>
            </div>
            <div class="form-group">
                <div class="icon-input">
                    <i class="fas fa-lock"></i>
                    <input type="password" name="password" required placeholder="Enter Your Password .....">
                </div>
                 </div>
                <div class="error-message" id="password-error" style="display: none; color: red;">Mot de passe incorrect</div>

           
            <div class="btn-container">
                <button type="submit" class="btn">Log in</button>
            </div>
            <br>
          
            <br>
            <a class="link" href="Signup.jsp">Don't have an account? Sign up</a>
        </form>
    </div>
</body>
</html>