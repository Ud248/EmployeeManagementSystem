<%-- 
    Document   : welcome
    Created on : Mar 5, 2025, 1:05:35 PM
    Author     : Ud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.DepartmentDAO" %>
<%@ page import="DAO.EmployeeDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://unpkg.com/@phosphor-icons/web"></script>  
        <link rel="stylesheet" href="./css/styleWelcome.css">  
        <style>

        </style>
    </head>

    <%
        EmployeeDAO eDao = new EmployeeDAO();
        DepartmentDAO dDao = new DepartmentDAO();
        int totalEmployees = eDao.getTotalEmployees();
        int totalDepartments = dDao.getTotalDepartments();  
    %>

    <body>
        <div class="container-fluid container-fluid-custom">
            <!-- Hero Section -->
            <div class="hero-section">
                <h1>Welcome to Employee & Department Management System</h1>
                <p>Effortless, efficient, and professional employee management</p>
                <a href="javascript:void(0);" onclick="loadContent('adminEmployeeList')" class="btn btn-primary btn-lg">Start now</a>
            </div>

            <div class="row mt-5">
                <div class="col-md-5">
                    <img src="image/EmployeePoster.jpg" class="img-fluid rounded" alt="Teamwork" style="width: 100%; height: 360px">
                </div>
                <div class="col-md-7">
                    <h2 style="text-align: center; font-size: 24px; font-weight: bold;">About the Employee & Department Management System</h2>
                    <div class="description">
                        <p>Employee & Department Management System is an optimal solution that helps businesses organize and oversee their workforce professionally. 
                            With a user-friendly interface, the system supports employee management, account authorization, performance tracking, and detailed reporting.</p>
                        <ul class="description_ul">
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

        <script>
            window.onload = function () {
                function formatDate(date) {
                    const options = {weekday: 'short', day: '2-digit', month: 'short', year: 'numeric'};
                    return date.toLocaleDateString('en-GB', options);
                }
                document.getElementById('current-date').textContent = formatDate(new Date());
            };
        </script>
    </body>
</html>




