<%-- 
    Document   : 403Error
    Created on : Mar 11, 2025, 2:48:29 PM
    Author     : Ud
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>403</title>
        <link rel="icon" type="image/x-icon" href="image/Logo.jpg">
    </head>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            background-color: #008CFF;
            color: white;
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        h1 {
            font-size: 100px;
            margin-bottom: 10px;
        }
        h2 {
            font-size: 24px;
            font-weight: bold;
        }
        p {
            font-size: 18px;
            margin-bottom: 20px;
        }
        a {
            color: white;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            margin-top: 10px;
        }
        a:hover {
            text-decoration: underline;
        }
        footer {
            margin-top: 30px;
            font-size: 14px;
        }
    </style>
    <body>
        <h1>403</h1>
        <h2>FORBIDDEN</h2>
        <p>You don't have permission to access this page.</p>
        <c:choose>
            <c:when test="${isAdmin}">
                <a href="admin.jsp">Back to home</a>
            </c:when>
            <c:otherwise>
                <a href="login">Back to Login</a>
            </c:otherwise>
        </c:choose>
    </body>
</html>
