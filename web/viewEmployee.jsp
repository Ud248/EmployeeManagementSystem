<%-- 
    Document   : viewEmployee
    Created on : Feb 23, 2025, 10:02:41 AM
    Author     : Ud
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Employee</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container mt-5">
            <div class="modal-content p-4">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto">VIEW EMPLOYEE DETAILS</h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <!-- Cột bên trái -->
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label class="form-label">First Name</label>
                                <input type="text" class="form-control" value="${employee.firstName}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Last Name</label>
                                <input type="text" class="form-control" value="${employee.lastName}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Birthdate</label>
                                <input type="date" class="form-control" value="${employee.birthdate}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Gender</label>
                                <input type="text" class="form-control" value="${employee.gender}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Telephone</label>
                                <input type="text" class="form-control" value="${employee.telephone}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input type="text" class="form-control" value="${employee.email}" readonly>
                            </div>
                        </div>

                        <!-- Cột bên phải -->
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label class="form-label">Position</label>
                                <input type="text" class="form-control" value="${employee.positionName}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Department</label>
                                <input type="text" class="form-control" value="${employee.departmentName}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Basic Salary</label>
                                <input type="text" class="form-control" value="${employee.basicSalary}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Address</label>
                                <textarea class="form-control" rows="3" readonly>${employee.address}</textarea>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Date of Joining</label>
                                <input type="text" class="form-control" value="${employee.dateOfJoining}" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer justify-content-center">
                        <a href="employee-list" class="btn btn-secondary">Back</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
