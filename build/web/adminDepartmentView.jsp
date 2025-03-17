<%-- 
    Document   : adminViewDepartment
    Created on : Feb 26, 2025, 3:16:35 PM
    Author     : anhnn
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
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Department</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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
    </head>
    <body>

        <div class="container">
            <div class="modal-content" style="padding: 1.5rem 1.5rem 1rem 1.5rem">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto">VIEW DEPARTMENT DETAILS</h3>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <h5 class="section-header">GENERAL INFORMATION</h5>
                        <div class="row">
                            <!-- Left Column -->
                            <div class="col-md-5">
                                <div class="field-container">
                                    <label class="form-label">Department Code:</label>
                                    <span class="detail">${departmentCode}</span>
                                </div>
                            </div>

                            <!-- Right Column -->
                            <div class="col-md-7">
                                <div class="field-container">
                                    <label class="form-label">Department Name:</label>
                                    <span class="detail">${departmentName}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="section-divider"></div>

                    <div class="mb-3">
                        <h5 class="section-header">WORK INFORMATION</h5>
                        <div class="row">
                            <!-- Left Column -->
                            <div class="col-md-5">
                                <div class="field-container">
                                    <label class="form-label">Manager:</label>
                                    <span class="detail">${managerName}</span>
                                </div>
                                <div class="field-container">
                                    <label class="form-label">Total Employee:</label>
                                    <span class="detail">${totalEmployee}</span>
                                </div>
                            </div>

                            <!-- Right Column -->
                            <div class="col-md-7">
                                <div class="field-container">
                                    <label class="form-label">Open Time:</label>
                                    <span class="detail">${openTime}</span>
                                </div>

                                <div class="field-container">
                                    <label class="form-label">Telephone:</label>
                                    <span class="detail">${telephone}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="section-divider"></div>

                    <div class="mb-3">
                        <h5 class="section-header">OTHER INFORMATION</h5>
                        <div class="row">
                            <!-- Left Column -->
                            <div class="col-md-12">
                                <div class="field-container">
                                    <label class="form-label">Description:</label>
                                    <div class="detail" >
                                        <ul>
                                            <c:forEach var="d" items="${descriptionArray}">
                                                <li>${d}</li>
                                                </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <!-- Right Column -->
                            <div class="col-md-12">
                                <div class="field-container">
                                    <label class="form-label">Cost Per Month:</label>
                                    <span class="detail">${costPerMonth}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <button type="button" class="edit-button" onclick="switchToUpdatePopup('${departmentCode}')">Edit</button>
                </div>
            </div>
        </div>

        <script>
            function switchToUpdatePopup(departmentCode) {
                let frame = window.parent.document.getElementById('viewDepartmentFrame');
                frame.src = "update-department?departmentCode=" + departmentCode;
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
