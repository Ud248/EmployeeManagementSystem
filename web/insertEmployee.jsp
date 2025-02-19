<%-- 
    Document   : insertEmployee
    Created on : Feb 15, 2025, 2:29:02 PM
    Author     : Ud
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insert Employee</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <%
        String error = "" + request.getAttribute("error");
        error = error.equals("null") ? "": error;
    %>
    <body>
        <div class="modal-content">
            <div class="modal-header">
                <h5 style="margin-left: 32%" class="modal-title" id="insertEmployeeLabel">INSERT NEW EMPLOYEE</h5>
            </div>
            <div class="modal-body">
                <form action="insert-employee" method="post">
                    <div class="mb-3">
                        <label for="firstName" class="form-label">First name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" required>
                    </div>
                    <div class="mb-3">
                        <label for="lastName" class="form-label">Last name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" required>
                    </div>
                    <div class="mb-3">
                        <label for="birthdate" class="form-label">Birthdate</label>
                        <input type="date" class="form-control" id="birthdate" name="birthdate" required>
                    </div>
                    <div class="mb-3">
                        <label for="gender" class="form-label">Gender</label>
                        <select class="form-select" id="gender" name="gender">
                            <option value="None">--Select gender--</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="telephone" class="form-label">Telephone</label>
                        <input type="tel" class="form-control" id="telephone" name="telephone" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address" required>
                    </div>
                    <div class="mb-3">
                        <label for="position" class="form-label">Position</label>
                        <select class="form-select" aria-label="Default select example" id="position" name="position" required>
                            <option selected value="None">Select Employee Position</option>
                            <c:forEach var="position" items="${listPosition}">
                                <option value="${position.getPositionId()}">${position.getPositionName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="department" class="form-label">Department</label>
                        <select class="form-select" aria-label="Default select example" id="department" name="department" required>
                            <option selected value="None">Select Employee Department</option>
                            <c:forEach var="department" items="${listDepartment}">
                                <option value="${department.getDepartmentID()}">${department.getDepartmentName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Insert</button>
                    </div>
                    <div style="color:red">
                        <%=error%>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
