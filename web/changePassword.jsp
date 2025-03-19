<%-- 
    Document   : changePassword
    Created on : Feb 15, 2025, 10:18:01 AM
    Author     : Ud
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="icon" type="image/x-icon" href="image/Logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" href="css/styleChangePassword.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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
                    <input type="password" class="input-field" id="confirmNewPassword" name="confirmNewPassword" required>
                    <label for="confirmNewPassword" class="label">Confirm new password</label>
                </div>
                <span id="err" style="color: red; margin: 10px 0px 0px 0px; font-size: 15px"></span>
                <span style="color: red; margin: 10px 0px 0px 0px; font-size: 15px;">${empty error ? '' : error}</span>
                <div class="input-box">
                    <button class="btn-submit" disabled>Change Password</button>
                    <a href="welcome" class="cancel_btn">Cancel</a>
                </div>
            </form>
        </div>

        <% 
            String actionMsg = (String) session.getAttribute("actionMsg");
            if (actionMsg != null) {
            session.removeAttribute("actionMsg");
        %>
        <script>
            Swal.fire({
                icon: "<%= actionMsg.contains("successfully") ? "success" : "error" %>",
                title: "Notification",
                text: "<%= actionMsg %>",
                timer: 2000,
                showConfirmButton: false
            });
        </script>
        <%
            }
        %>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const newPassword = document.getElementById("newPassword");
                const confirmNewPassword = document.getElementById("confirmNewPassword");
                const errorSpan = document.getElementById("err");
                const submitButton = document.querySelector(".btn-submit");

                function validatePasswords() {
                    if (newPassword.value !== confirmNewPassword.value) {
                        errorSpan.textContent = "New password and Confirm password do not match!";
                        submitButton.disabled = true;
                    } else {
                        errorSpan.textContent = "";
                        submitButton.disabled = false;
                    }
                }

                newPassword.addEventListener("input", validatePasswords);
                confirmNewPassword.addEventListener("input", validatePasswords);
            });
        </script>
    </body>
</html>


