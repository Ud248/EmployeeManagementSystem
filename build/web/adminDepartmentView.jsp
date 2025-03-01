<%-- 
    Document   : adminViewDepartment
    Created on : Feb 26, 2025, 3:16:35 PM
    Author     : anhnn
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/styleAdminDepartmentView.css"/>
    </head>
    <body>

        <button type="button" class="btn-back" onclick="goBack()">Back</button>

        <h2 style="text-align: center">Department Detail</h2>

        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">Department ID</label>
                    <input type="text" class="form-control" value="${departmentId}" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Manager</label>
                    <input type="text" class="form-control" value="${managerName}" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Open Time</label>
                    <input type="text" class="form-control" value="${openTime}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Description</label>                               
                    <ul style="color: green">
                        <c:forEach var="sub" items="${descriptionArray}">
                            <li>${sub}</li>
                            </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">Department Name</label>
                    <input type="text" class="form-control" value="${departmentName}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Telephone</label>
                    <input type="text" class="form-control" value="${telephone}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Total Employee</label>
                    <input type="text" class="form-control" value="${totalEmployee}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Cost Per Month</label>                               
                    <ul style="color: red">
                        <li>${costPerMonth}</li>
                    </ul>
                </div>

            </div>

        </div>

        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>
