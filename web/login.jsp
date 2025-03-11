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
        <link rel="icon" type="image/x-icon" href="image/Logo.jpg">
    </head>
    <body>
        <% 
        String error = request.getAttribute("error") + "";
        if(error.equals("null")){
        error = "";
            }
        %>
        <div class="wrapper">
            <div class="form-header">
                <div class="titles">
                    <div class="title-login">Login</div>
                </div>
            </div>
            <!-- LOGIN FORM -->
            <form action="login" class="login-form" method="post">
                <div class="input-box">
                    <input type="text" class="input-field" id="username" name="username" value="${username}" required>
                    <label for="username" class="label">Username</label>
                </div>
                <div class="input-box">
                    <input type="password" class="input-field" id="password" name="password" required>
                    <label for="password" class="label">Password</label>
                </div>
                <div class="input-box">
                    <button class="btn-submit" id="SignInBtn">Log In <i class='bx bx-log-in' ></i></button>
                </div>
                <div style="text-align: center"><span style="color: red"><%=error %></span></div>
            </form>
        </div>
    </body>
</html>
