<%-- 
    Document   : updateEmployee
    Created on : Feb 15, 2025, 9:02:17 PM
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
        <title>Update Employee</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </head>

    <body>
        <div class="container">
            <div class="modal-content p-4">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto">UPDATE EMPLOYEE DETAILS</h3>
                </div>
                <div class="modal-body">
                    <form action="update-employee" method="POST">
                        <div class="row">
                            <!-- Cột bên trái -->
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label class="form-label">Employee Code</label>
                                    <input type="text" class="form-control" value="${empty employeeCode ? '' : employeeCode}" name="employeeCode" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Full Name</label>
                                    <input type="text" class="form-control" value="${empty fullname ? '' : fullname}" name="fullname">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Birthdate</label>
                                    <input type="date" class="form-control" value="${empty birthDate ? '' : birthDate}" name="birthdate">
                                </div>
                                <div class="mb-3">
                                    <label for="gender" class="form-label">Gender</label>
                                    <select class="form-select" id="gender" name="gender">
                                        <option value="Nam" ${gender == 'Nam' ? 'selected="selected"' : ''}>Nam</option>
                                        <option value="Nữ" ${gender == 'Nữ ' ? 'selected="selected"' : ''}>Nữ</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Telephone</label>
                                    <input type="text" class="form-control" value="${empty tel ? '' : tel}" name="tel">
                                </div>
                            </div>

                            <!-- Cột bên phải -->
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label class="form-label">Position Name</label>
                                    <select class="form-select" id="positionId" name="positionId" required>
                                        <c:forEach var="position" items="${requestScope.listPosition}">
                                            <option value="${position.positionId}" ${positionId == position.positionId ? 'selected="selected"' : ''}>
                                                ${position.positionName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Department Name</label>
                                    <select class="form-select" id="departmentId" name="departmentId" required>
                                        <c:forEach var="department" items="${requestScope.listDepartment}">
                                            <option value="${department.departmentId}" ${departmentId == department.departmentId ? 'selected="selected"' : ''}>
                                                ${department.departmentName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Basic Salary</label>
                                    <input type="text" class="form-control" value="${empty basicSalary ? '0' : basicSalary}" name="basicSalary">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Username</label>                               
                                    <input type="text" class="form-control" value="${empty username ? '' : username}" name="username" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Password</label>
                                    <input type="text   " class="form-control" value="${empty password ? '' : password}" name="password" readonly>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <label class="form-label">Address</label>
                                <input type="text" class="form-control" value="${empty address ? '' : address}" name="address">
                            </div>
                        </div>
                        <br>
                        <div class="modal-footer justify-content-center">
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger mt-3">
                                ${error}
                            </div>
                        </c:if>

                        <c:if test="${not empty successMsg}">
                            <div class="alert alert-success mt-3">
                                ${successMsg}
                            </div>
                        </c:if>
                        
                    </form>         
                </div>
            </div>
        </div>
    </body>
</html>
