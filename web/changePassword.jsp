<%-- 
    Document   : changePassword
    Created on : Feb 15, 2025, 10:18:01 AM
    Author     : Ud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("employee") == null) {
        response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập, chuyển về trang login
        return;
    }
%>
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
            <form action="change-password" class="change-password-form" method="post">
                <div class="input-box">
                    <input type="password" class="input-field" id="oldPassword" name="oldPassword" required>
                    <label for="oldPassword" class="label">Old password</label>
                </div>
                <div class="input-box">
                    <input type="password" class="input-field" id="newPassword" name="newPassword" required>
                    <label for="newPassword" class="label">New password</label>
                </div>
                <div class="input-box">
                    <input type="password" class="input-field" id="confirmNewPassword" name="confirmNewPassword" required onkeyup="kiemTraMatKhau()">
                    <span id="msg" style="color: red; margin: 10px 0px 0px 20px; font-size: 15px"></span>
                    <label for="confirmNewPassword" class="label">Confirm new password</label>
                </div>
                <span style="color: red;">${empty error ? '' : error}</span>
                <div class="input-box">
                    <button class="btn-submit">Change Password</button>
                </div>
            </form>
        </div>
    </body>
</html>

<script>
    function kiemTraMatKhau() {
        newPassword = document.getElementById("newPassword").value;
        confirmNewPassword = document.getElementById("confirmNewPassword").value;
        if (newPassword != confirmNewPassword) {
            document.getElementById("msg").innerHTML = "Mật khẩu không khớp!";
            return false;
        } else {
            document.getElementById("msg").innerHTML = "";
            return true;
        }
    }
</script>
