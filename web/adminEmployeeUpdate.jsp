<%-- 
    Document   : adminEmployeeUpdate
    Created on : Feb 15, 2025, 9:02:17 PM
    Author     : Ud
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("employee") == null) {
        response.sendRedirect("admin.jsp");
        return;
    }
    else if(!(boolean)session.getAttribute("isAdmin")){
        response.sendRedirect("403Error.jsp");
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

    <style>
        
        body{
            height: 100vh;
            background-color: #EFEFEF;
        }

        .form-label {
            text-align: left;
            font-weight: bold;
            padding-left: 15px;
            width: 220px; /* Thay đổi từ min-width thành width cố định */
            display: inline-block;
            margin-right: 25px;
            vertical-align: top;
        }

        /* Normal text for details instead of bold */
        .detail {
            font-weight: normal;
            display: inline-block;
            vertical-align: top;
            width: 74%; /* Chiều rộng cố định tính toán từ width của label + margin */
        }

        /* Bold section headers */
        .section-header {
            font-weight: bold;
            color: green;
            margin-bottom: 15px;
        }

        /* Horizontal divider */
        .section-divider {
            height: 1px;
            background-color: #dee2e6;
            margin: 25px 0;
        }

        /* CSS cho nút Edit */
        .submit-button {
            background-color: #28a745; /* Xanh lá */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s, transform 0.2s;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
            margin-left: 45%;
        }

        /* Hiệu ứng hover */
        .submit-button:hover {
            background-color: #218838; /* Màu tối hơn khi hover */
            transform: scale(1.05);
        }

        /* Add spacing between info groups */
        .info-group {
            margin-bottom: 15px;
        }

        /* Field container styling */
        .field-container {
            display: flex;
            align-items: flex-start;
            margin-bottom: 10px;
            padding-right: 15px;
        }

        .field-container span{
            width: 75%;
        }
    </style>

    <body>
        <div class="container">
            <div class="modal-content p-4">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto">UPDATE EMPLOYEE DETAILS</h3>
                </div>
                <div class="modal-body">
                    <form action="update-employee" method="POST">
                        <!-- PERSONAL INFORMATION SECTION -->
                        <div class="mb-3">
                            <h5 class="section-header">PERSONAL INFORMATION</h5>
                            <div class="row">
                                <!-- Left Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Employee Code:</label>
                                        <span class="detail">${employeeCode}</span>
                                        <input type="hidden" name="employeeCode" value="${employeeCode}">
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Full Name:</label>
                                        <input type="text" class="form-control detail" value="${empty fullname ? '' : fullname}" name="fullname" required>
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Birth Date:</label>
                                        <input type="date" class="form-control detail" value="${empty birthdate ? '' : birthdate}" name="birthdate" required>
                                    </div>
                                </div>

                                <!-- Right Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Gender:</label>
                                        <select class="form-select detail" id="gender" name="gender" required>
                                            <option value="Nam" ${gender == 'Nam' ? 'selected="selected"' : ''}>Nam</option>
                                            <option value="Nữ" ${gender == 'Nữ ' ? 'selected="selected"' : ''}>Nữ</option>
                                        </select>
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Telephone:</label>
                                        <input type="tel" class="form-control detail" id="tel" name="tel" required 
                                               pattern="0[0-9]{9}" title="Telephone must start with 0 and have 10 digits." 
                                               value="${empty tel ? '' : tel}" required>
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Address:</label>
                                        <input type="text" class="form-control detail" value="${empty address ? '' : address}" name="address" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--<div class="section-divider"></div>-->

                        <!-- EMPLOYMENT INFORMATION SECTION -->
                        <div class="mb-3">
                            <h5 class="section-header">EMPLOYMENT INFORMATION</h5>
                            <div class="row">
                                <!-- Left Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Position Name:</label>
                                        <select class="form-select detail" id="positionId" name="positionId" required>
                                            <c:forEach var="position" items="${applicationScope.listPosition}">
                                                <option value="${position.positionId}" ${positionId == position.positionId ? 'selected="selected"' : ''}>
                                                    ${position.positionName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Department Name:</label>
                                        <select class="form-select detail" id="departmentId" name="departmentId" required>
                                            <c:forEach var="department" items="${applicationScope.listDepartment}">
                                                <option value="${department.departmentId}" ${departmentId == department.departmentId ? 'selected="selected"' : ''}>
                                                    ${department.departmentName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <!-- Right Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Basic Salary:</label>
                                        <input type="text" class="form-control detail" value="${empty basicSalary ? '0' : basicSalary}" name="basicSalary" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--<div class="section-divider"></div>-->

                        <!-- ACCOUNT INFORMATION SECTION -->
                        <div class="mb-3">
                            <h5 class="section-header">ACCOUNT INFORMATION</h5>
                            <div class="row">
                                <!-- Left Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Username:</label>
                                        <span class="detail">${username}</span>
                                        <input type="hidden" name="username" value="${username}">
                                    </div>
                                </div>

                                <!-- Right Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Password:</label>
                                        <span class="detail">${password}</span>
                                        <input type="hidden" name="password" value="${password}">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger mt-3">
                                ${error}
                            </div>
                        </c:if>

                        <button type="submit" class="submit-button">Update</button>  

                    </form>     
                </div>
            </div>
        </div>
    </body>
</html>
