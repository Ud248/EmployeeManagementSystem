<%-- 
    Document   : changePassword
    Created on : Feb 15, 2025, 10:18:01 AM
    Author     : Ud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" href="css/styleChangePassword.css">
        <title>Change Password</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="form-header">
                <div class="titles">
                    <div class="title-change-password">Change Password</div>
                </div>
            </div>
            <!-- LOGIN FORM -->
            <form action="#" class="change-password-form">
                <div class="input-box">
                    <input type="text" class="input-field" id="username" required>
                    <label for="username" class="label">Username</label>
                </div>
                <div class="input-box">
                    <input type="password" class="input-field" id="password" required>
                    <label for="password" class="label">Password</label>
                </div>
                <div class="input-box">
                    <input type="password" class="input-field" id="password2" required>
                    <label for="password2" class="label">Confirmn password</label>
                </div>
                <div class="input-box">
                    <button class="btn-submit">Change Password</button>
                </div>
            </form>
        </div>
    </body>
</html>
