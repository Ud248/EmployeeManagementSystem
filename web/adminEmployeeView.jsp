<%-- 
    Document   : adminEmployeeView
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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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

    <style>
        body{
            height: 100vh;
            background-color: #EFEFEF;
        }

        .form-label {
            text-align: left;
            font-weight: bold;
            padding-left: 15px;
            width: 170px; /* Thay đổi từ min-width thành width cố định */
            display: inline-block;
            margin-right: 25px;
            vertical-align: top;
        }

        /* Normal text for details instead of bold */
        .detail {
            font-weight: normal;
            display: inline-block;
            vertical-align: top;
            width: calc(100% - 175px); /* Chiều rộng cố định tính toán từ width của label + margin */

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
        .edit-button {
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
        .edit-button:hover {
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
    </style>

    <body>
        <div class="container">
            <div class="modal-content" style="padding: 1.5rem 1.5rem 1rem 1.5rem">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto">VIEW EMPLOYEE DETAILS</h3>
                </div>
                <div class="modal-body">
                    <!-- PERSONAL INFORMATION SECTION -->
                    <div class="mb-3">
                        <h5 class="section-header">PERSONAL INFORMATION</h5>
                        <div class="row">
                            <!-- Left Column -->
                            <div class="col-md-6">
                                <div class="field-container">
                                    <label class="form-label">Employee Code:</label>
                                    <span class="detail">${employeeCode}</span>
                                </div>

                                <div class="field-container">
                                    <label class="form-label">Full Name:</label>
                                    <span class="detail">${fullname}</span>
                                </div>

                                <div class="field-container">
                                    <label class="form-label">Birth Date:</label>
                                    <span class="detail">${birthDate}</span>
                                </div>
                            </div>

                            <!-- Right Column -->
                            <div class="col-md-6">
                                <div class="field-container">
                                    <label class="form-label">Gender:</label>
                                    <span class="detail">${gender}</span>
                                </div>

                                <div class="field-container">
                                    <label class="form-label">Telephone:</label>
                                    <span class="detail">${tel}</span>
                                </div>

                                <div class="field-container">
                                    <label class="form-label">Address:</label>
                                    <span class="detail">${address}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="section-divider"></div>

                    <!-- EMPLOYMENT INFORMATION SECTION -->
                    <div class="mb-3">
                        <h5 class="section-header">EMPLOYMENT INFORMATION</h5>
                        <div class="row">
                            <!-- Left Column -->
                            <div class="col-md-6">
                                <div class="field-container">
                                    <label class="form-label">Position Name:</label>
                                    <span class="detail">${positionName}</span>
                                </div>

                                <div class="field-container">
                                    <label class="form-label">Department Name:</label>
                                    <span class="detail">${departmentName}</span>
                                </div>
                            </div>

                            <!-- Right Column -->
                            <div class="col-md-6">
                                <div class="field-container">
                                    <label class="form-label">Basic Salary:</label>
                                    <span class="detail">${basicSalary}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="section-divider"></div>

                    <!-- ACCOUNT INFORMATION SECTION -->
                    <div class="mb-3">
                        <h5 class="section-header">ACCOUNT INFORMATION</h5>
                        <div class="row">
                            <!-- Left Column -->
                            <div class="col-md-6">
                                <div class="field-container">
                                    <label class="form-label">Username:</label>
                                    <span class="detail">${username}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <button type="button" class="edit-button" onclick="switchToUpdatePopup('${employeeCode}')">Edit</button>
                </div>
            </div>
        </div>

        <script>
            function switchToUpdatePopup(employeeCode) {
                let frame = window.parent.document.getElementById('viewEmployeeFrame');
                frame.src = "update-employee?employeeCode=" + employeeCode;
            }

            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                const successMsg = urlParams.get('successMsg');
                const errorMsg = urlParams.get('errorMsg');

                if (successMsg) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Thành công!',
                        text: successMsg,
                        showConfirmButton: false,
                        timer: 1500
                    });
                }

                if (errorMsg) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Thất bại!',
                        text: errorMsg,
                        showConfirmButton: true
                    });
                }
            };

            
        </script>
    </body>
</html>