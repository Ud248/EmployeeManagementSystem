<%-- 
    Document   : welcome
    Created on : Mar 5, 2025, 1:05:35 PM
    Author     : Ud
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.DepartmentDAO" %>
<%@ page import="DAO.EmployeeDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Admin</title>
        <script src="https://unpkg.com/@phosphor-icons/web"></script>  
        <link rel="stylesheet" href="./css/styleWelcome.css">  
        <link rel="icon" type="image/x-icon" href="./image/Logo.jpg">
    </head>

    <body>
        <c:if test="${sessionScope.employee != null}">
        <jsp:include page="./layout/sidebar.jsp" />
        </c:if>
        
        <div class="container-main">
            <!-- Hero Section -->
            <div class="hero-section">
                <h1>Welcome to Employee & Department Management System</h1>
                <p>Effortless, efficient, and professional employee management</p>
                <a href="login" class="btn-primary">Start now</a>
            </div>

            <div class="content-row">
                <div class="image-column">
                    <img src="image/EmployeePoster.jpg" class="poster-image" alt="Teamwork">
                </div>
                <div class="text-column">
                    <h2 class="section-title">About the Employee & Department Management System</h2>
                    <div class="description">
                        <p>Employee & Department Management System is an optimal solution that helps businesses organize and oversee their workforce professionally. 
                            With a user-friendly interface, the system supports employee management, account authorization, performance tracking, and detailed reporting.</p>
                        <ul class="description-list">
                            <li>📌 <strong>Easy Employee Management:</strong> Store and update employee information systematically.</li>
                            <li>📌 <strong>Efficient Department Management:</strong> Monitor organizational structure and allocate personnel effectively.</li>
                            <li>📌 <strong>Comprehensive Statistical Reports:</strong> Help businesses make accurate decisions based on aggregated data.</li>
                            <li>📌 <strong>Seamless Integration:</strong> Expand functionality and connect with other systems effortlessly.</li>
                        </ul>
                        <p>The system not only saves businesses time but also enhances work efficiency, driving sustainable organizational growth.</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>




