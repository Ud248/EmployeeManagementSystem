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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Department</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <style>
            .detail{
                font-weight: bolder;
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

            .form-label {
                text-align: right;
            }

            .detail {
                font-weight: bolder;
            }

        </style>
    </head>
    <body>

        <div class="container   ">
            <div class="modal-content p-4">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto">VIEW DEPARTMENT DETAIL</h3>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <h5 style="color: green">General Information</h5>
                        <div class="row mb-2">
                            <div class="col-md-4 text-end">
                                <label class="form-label">Department ID:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="detail">${departmentId}</span>
                            </div>
                        </div>

                        <div class="row mb-2">
                            <div class="col-md-4 text-end">
                                <label class="form-label">Department Name:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="detail">${departmentName}</span>
                            </div>
                        </div>
                    </div>
                    <br/>

                    <div class="mb-3">
                        <h5 style="color: green">Work Information</h5>
                        <div class="row mb-2">
                            <div class="col-md-4 text-end">
                                <label class="form-label">Open Time:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="detail">${openTime}</span>
                            </div>
                        </div>

                        <div class="row mb-2">
                            <div class="col-md-4 text-end">
                                <label class="form-label">Manager:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="detail">${managerName}</span>
                            </div>
                        </div>

                        <div class="row mb-2">
                            <div class="col-md-4 text-end">
                                <label class="form-label">Telephone:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="detail">${telephone}</span>
                            </div>
                        </div>
                    </div>
                    <br/>

                    <div class="mb-3">
                        <h5 style="color: green">Other Information</h5>
                        <div class="row mb-2">
                            <div class="col-md-4 text-end">
                                <label class="form-label">Description:</label>
                            </div>
                            <div class="col-md-8">
                                <ul style="margin: 0; padding-left: 20px; font-weight: bolder">
                                    <c:forEach var="sub" items="${descriptionArray}">
                                        <li>${sub}</li>
                                        </c:forEach>
                                </ul>
                            </div>
                        </div>

                        <div class="row mb-2">
                            <div class="col-md-4 text-end">
                                <label class="form-label">Cost Per Month:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="detail">${costPerMonth}</span>
                            </div>
                        </div>
                    </div>

                    <button type="button" class="edit-button" onclick="switchToUpdatePopup(${departmentId})">Edit</button>
                </div>
            </div>
        </div>

        <script>
            function switchToUpdatePopup(departmentId) {
                let frame = window.parent.document.getElementById('viewDepartmentFrame');
                frame.src = "updatedepartment?departmentId=" + departmentId;
            }
        </script>
    </body>
</html>
