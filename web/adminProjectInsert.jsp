<%-- 
    Document   : adminProjectInsert
    Created on : Mar 5, 2025, 11:24:46 PM
    Author     : nongt
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert New Project</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <style>
            .insert-button {
                background-color: #0d6efd;
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
            .insert-button:hover {
                background-color: #218838;
                transform: scale(1.05);
            }
            .error {
                color: red;
                text-align: center;
            }
            .form-label {
                text-align: left;
                font-weight: bold;
                padding-left: 15px;
                width: 170px;
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
            .custom-error-tooltip {
                display: none;
                position: absolute;
                top: 100%;
                left: 0;
                background-color: rgba(255, 0, 0, 0.9);
                color: white;
                padding: 5px 10px;
                font-size: 14px;
                border-radius: 5px;
                margin-top: 5px;
                white-space: nowrap;
                z-index: 1000;
            }
            .is-invalid + .custom-error-tooltip {
                display: block;
            }
            .is-invalid {
                border: 1px solid red !important;
                background-color: #ffe6e6;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="modal-content p-4">
                <h3 class="modal-title mx-auto">INSERT NEW PROJECT</h3>
                <br/>
                <div class="modal-body">
                    <form action="insert-project" method="post">
                        <div class="row mb-3"> 
                            <div class="col-md-4 text-end">
                                <label class="form-label">Project Name:</label>
                            </div>                                  
                            <div class="col-md-8 position-relative">
                                <input type="text" class="form-control ${not empty errorNameMsg ? 'is-invalid' : ''}" 
                                       name="projectName" value="${projectName}" 
                                       onfocus="hideError(this)" 
                                       onblur="showError(this, '${errorNameMsg}')">
                                <div class="custom-error-tooltip">${errorNameMsg}</div>
                            </div>
                        </div>
                        <div class="section-divider"></div>
                        <div class="mb-3">
                            <h5 class="section-header">PROJECT INFORMATION</h5>
                            <div class="row mb-3">
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Start Project At:</label>
                                </div>
                                <div class="col-md-8">
                                    <input type="date" class="form-control ${not empty errorStartDateMsg ? 'is-invalid' : ''}" name="startDate" value="${startDate}">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Dead Line:</label>
                                </div>
                                <div class="col-md-8">
                                    <input type="date" class="form-control ${not empty errorDeadLineMsg ? 'is-invalid' : ''}" name="deadLine" value="${deadLine}">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Budget:</label>
                                </div>
                                <div class="col-md-8">
                                    <input type="number" class="form-control ${not empty errorBudgetMsg ? 'is-invalid' : ''}" name="budget" value="${budget}">
                                </div>
                            </div>
                            <div class="row mb-3">

                                <div class="col-md-4 text-end">
                                    <label class="form-label">Profit:</label>
                                </div>
                                <div class="col-md-8">
                                    <input type="number" class="form-control ${not empty errorProfitMsg ? 'is-invalid' : ''}" name="profit" value="${profit}">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Department Name</label>
                                </div>                               
                                <div class="col-md-8">
                                    <select class="form-select" id="department" name="department" required autocomplete="off">
                                        <option selected value="None">Select Project Department</option>
                                        <c:forEach var="department" items="${applicationScope.listDepartment}">
                                            <option value="${department.departmentId}" 
                                                    ${departmentId == department.departmentId ? 'selected="selected"' : ''}>
                                                ${department.departmentName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="mb-3">
                                <h5 class="section-header">OTHER INFORMATION</h5>
                                <div class="row mb-3">
                                    <div class="col-md-4 text-end">
                                        <label class="form-label">Description:</label>
                                    </div>                                
                                    <div class="col-md-8" id="description-container">
                                        <c:choose>
                                            <c:when test="${empty descriptionArray}">
                                                <div class="d-flex mb-2">
                                                    <input type="text" class="form-control flex-grow-1" name="description" value="${sub}"/>
                                                    <button type="button" class="btn btn-danger ms-2 d-flex align-items-center justify-content-center" style="width: 35px; height: 35px;" onclick="removeInputTag(this)">
                                                        <i class="fa fa-minus"></i>
                                                    </button>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="sub" items="${descriptionArray}" varStatus="status">
                                                    <div class="d-flex mb-2">
                                                        <input type="text" class="form-control flex-grow-1" name="description" value="${sub}"/>
                                                        <button type="button" class="btn btn-danger ms-2 d-flex align-items-center justify-content-center" style="width: 35px; height: 35px;" onclick="removeInputTag(this)">
                                                            <i class="fa fa-minus"></i>
                                                        </button>
                                                    </div>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                        <button type="button" id="btn-add-description" class="btn btn-dark rounded-circle d-flex align-items-center justify-content-center mt-2" style="width: 30px; height: 30px;" onclick="addInputTag()">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="text-center">
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger mt-3">
                                    ${error}
                                </div>
                            </c:if>
                        </div>
                        <input type="submit" class="insert-button" value="Insert"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
