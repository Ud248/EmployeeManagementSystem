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
        String successMsg = "" + request.getAttribute("successMsg");
        successMsg = successMsg.equals("null") ? "": successMsg;
    %>
    <body>
        <div class="container mt-5">
            <div class="modal-content p-4">
                <div class="modal-header">
                    <h3 class="modal-title mx-auto" id="insertEmployeeLabel">INSERT NEW EMPLOYEE</h5>
                </div>
                <br>
                <div class="modal-body">
                    <form action="insert-employee" method="post">
                        <div class="row">
                            <!-- Cột bên trái -->
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="firstName" class="form-label">First Name</label>
                                    <input type="text" class="form-control" id="firstName" name="firstName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="lastName" class="form-label">Last Name</label>
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
                                    <label for="address" class="form-label">Address</label>
                                    <textarea class="form-control" id="address" name="address" required rows="3"></textarea>
                                </div>

                            </div>

                            <!-- Cột bên phải -->
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="telephone" class="form-label">Telephone</label>
                                    <input type="tel" class="form-control" id="telephone" name="telephone" required>
                                </div>
                                <div class="mb-3">
                                    <label for="position" class="form-label">Position</label>
                                    <select class="form-select" id="position" name="position" required>
                                        <option selected value="None">Select Employee Position</option>
                                        <c:forEach var="position" items="${listPosition}">
                                            <option value="${position.getPositionId()}">${position.getPositionName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="department" class="form-label">Department</label>
                                    <select class="form-select" id="department" name="department" required>
                                        <option selected value="None">Select Employee Department</option>
                                        <c:forEach var="department" items="${listDepartment}">
                                            <option value="${department.getDepartmentID()}">${department.getDepartmentName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="basicSalary" class="form-label">Basic Salary</label>
                                    <input type="number" class="form-control" id="basicSalary" name="basicSalary" required min="0">
                                </div>
                                <div class="mb-3" style="margin: 16% 0 0 30%; padding: 3% 15%; scale: 110%">
                                    <button type="submit" class="btn btn-primary">Insert</button>
                                </div>
                            </div>
                        </div>



                        <div class="text-center mt-3">
                            <span style="color:red"><%=error%></span>
                            <span style="color:green"><%=successMsg%></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
