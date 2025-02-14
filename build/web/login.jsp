<%-- 
    Document   : login
    Created on : Feb 14, 2025, 12:57:59 PM
    Author     : Ud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" href="css/styleLogin.css">
        <title>Login</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="form-header">
                <div class="titles">
                    <div class="title-login">Login</div>
                </div>
            </div>
            <!-- LOGIN FORM -->
            <form action="#" class="login-form">
                <div class="input-box">
                    <input type="text" class="input-field" id="username" required>
                    <label for="username" class="label">Username</label>
                </div>
                <div class="input-box">
                    <input type="password" class="input-field" id="password" required>
                    <label for="password" class="label">Password</label>
                </div>
                <div class="input-box">
                    <button class="btn-submit" id="SignInBtn">Log In <i class='bx bx-log-in' ></i></button>
                </div>
            </form>
        </div>
    </body>
</html>
