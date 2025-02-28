<%-- 
    Document   : viewEmployee
    Created on : Feb 23, 2025, 10:02:41 AM
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
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Employee</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </head>

    <%
        String employeeCode = request.getAttribute("employeeCode") +"";
        String fullname = request.getAttribute("fullname") +"";
        String birthDate = request.getAttribute("birthDate") +"";
        String gender = request.getAttribute("gender") +"";
        String tel = request.getAttribute("tel") +"";
        String address = request.getAttribute("address") +"";
        String positionName = request.getAttribute("positionName") +"";
        String departmentName = request.getAttribute("departmentName") +"";
        String basicSalary = request.getAttribute("basicSalary") +"";
        String username = request.getAttribute("username") +"";
        String password = request.getAttribute("password") +"";
    %>
    <body>
        <div class="container   ">
            <div class="modal-content p-4">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto">VIEW EMPLOYEE DETAILS</h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <!-- Cột bên trái -->
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label class="form-label">Employee Code</label>
                                <input type="text" class="form-control" value="<%=employeeCode%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Full Name</label>
                                <input type="text" class="form-control" value="<%=fullname%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Birthdate</label>
                                <input type="date" class="form-control" value="<%=birthDate%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Gender</label>
                                <input type="text" class="form-control" value="<%=gender%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Telephone</label>
                                <input type="text" class="form-control" value="<%=tel%>" readonly>
                            </div>
                        </div>

                        <!-- Cột bên phải -->
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label class="form-label">Position Name</label>
                                <input type="text" class="form-control" value="<%=positionName%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Department Name</label>
                                <input type="text" class="form-control" value="<%=departmentName%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Basic Salary</label>
                                <input type="text" class="form-control" value="<%=basicSalary%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Username</label>                               
                                <input type="text" class="form-control" value="<%=username%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input type="text   " class="form-control" value="<%=password%>" readonly>
                            </div>
                        </div>


                        <div class="col-md-12">
                            <label class="form-label">Address</label>
                            <input type="text" class="form-control" value="<%=address%>" readonly>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
