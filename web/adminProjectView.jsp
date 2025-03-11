<%-- 
    Document   : adminProjectView
    Created on : Mar 5, 2025, 11:29:30 PM
    Author     : nongt
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
        <title>View Project</title>
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
                width: 200px;
                display: inline-block;
                margin-right: 25px;
                vertical-align: top;
            }
            .detail {
                font-weight: normal;
                display: inline-block;
                vertical-align: top;
                width: calc(100% - 175px);
            }
            .section-header {
                font-weight: bold;
                color: green;
                margin-bottom: 15px;
            }
            .section-divider {
                height: 1px;
                background-color: #dee2e6;
                margin: 25px 0;
            }
            .edit-button {
                background-color: #28a745;
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
            .edit-button:hover {
                background-color: #218838;
                transform: scale(1.05);
            }
            .info-group {
                margin-bottom: 15px;
            }
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
                    <h3 class="modal-title mx-auto">VIEW PROJECT DETAILS</h3>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <h5 class="section-header">GENERAL INFORMATION</h5>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="field-container">
                                    <label class="form-label">Project ID:</label>
                                    <span class="detail">${projectId}</span>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <div class="field-container">
                                    <label class="form-label">Project Name:</label>
                                    <span class="detail">${projectName}</span>
                                </div>
                                <div class="field-container">
                                    <label class="form-label">Department Name:</label>
                                    <span class="detail">${departmentName}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="section-divider"></div>
                    <div class="mb-3">
                        <h5 class="section-header">PROJECT INFORMATION</h5>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="field-container">
                                    <label class="form-label">Start Date:</label>
                                    <span class="detail">${startDate}</span>
                                </div>
                                <div class="field-container">
                                    <label class="form-label">End Date:</label>
                                    <span class="detail">${endDate}</span>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <div class="field-container">
                                    <label class="form-label">Budget:</label>
                                    <span class="detail">${budget}</span>
                                </div>
                                <div class="field-container">
                                    <label class="form-label">Profit:</label>
                                    <span class="detail">${profit}</span>
                                </div>
                                <div class="field-container">
                                    <label class="form-label">Percent Completion:</label>
                                    <span class="detail">${completion}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="section-divider"></div>
                    <div class="mb-3">
                        <h5 class="section-header">OTHER INFORMATION</h5>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="field-container">
                                    <label class="form-label">Description:</label>
                                    <div class="detail">
                                        <ul>
                                            <c:forEach var="desc" items="${descriptionArray}">
                                                <li>${desc}</li>
                                                </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="edit-button" onclick="switchToUpdatePopup('${projectCode}')">Edit</button>
                </div>
            </div>
        </div>
        <script>
            function switchToUpdatePopup(projectCode) {
                let frame = window.parent.document.getElementById('viewProjectFrame');
                console.log("ok");
                frame.src = "update-project?projectCode=" + projectCode;
            }
            
            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                const successMsg = urlParams.get('successMsg');
                const errorMsg = urlParams.get('errorMsg');

                if (successMsg) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Success!',
                        text: successMsg,
                        showConfirmButton: false,
                        timer: 1500
                    });
                }

                if (errorMsg) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Failed!',
                        text: errorMsg,
                        showConfirmButton: true
                    });
                }
            };
        </script>
    </body>
</html>