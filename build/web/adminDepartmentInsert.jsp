<%-- 
    Document   : adminDepartmentUpdate
    Created on : Feb 28, 2025, 11:24:24 AM
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <style>

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

            .error{
                color: red;
                text-align: center;
            }

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

            /* Hiệu ứng hover */
            .insert-button:hover {
                background-color: #218838; /* Màu tối hơn khi hover */
                transform: scale(1.05);
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

            .detail {
                font-weight: normal;
                display: inline-block;
                vertical-align: top;
                width: calc(100% - 175px); /* Chiều rộng cố định tính toán từ width của label + margin */
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

        </style>
    </head>
    <body>

        <div class="container   ">
            <div class="modal-content p-4">
                <h3 class="modal-title mx-auto">INSERT NEW DEPARTMENT</h3>
                <br/>
                <div class="modal-body">
                    <form action="insert-department" method="post">
                        <div class="mb-3">
                            <h5 class="section-header">GENERAL INFORMATION</h5>
<!--                            <div class="row mb-3 align-items-center">
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Department ID:</label>
                                </div>                                
                                <div class="col-md-8">
                                    <span class="detail"></span>
                                </div>
                            </div>-->

                            <div class="row mb-3"> 
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Department Name:</label>
                                </div>                                
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="departmentName" value="${departmentName}" required>
                                </div>
                                <div class="error">${errorNameMsg}</div>
                            </div>
                        </div>

                        <div class="section-divider"></div>

                        <div class="mb-3">
                            <h5 class="section-header">WORK INFORMATION</h5>
                            <div class="row mb-3">
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Open Time:</label>
                                </div>                               
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="openTime" value="${openTime}" required>
                                </div>
                                <div class="error">${errorOpenTimeMsg}</div>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Telephone:</label>
                                </div>                                
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="telephone" value="${telephone}" required>
                                </div>
                                <div class="error">${errorTelephoneMsg}</div>
                            </div>     

                        </div>

                        <div class="section-divider"></div>

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
                                    <div class="error">${errorDescriptionMsg}</div>
                                </div>
                            </div>

                        </div>
                        <input type="submit" class="insert-button" value="Insert"/>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function addInputTag() {
                let container = document.getElementById("description-container");
                let btn = document.getElementById("btn-add-description");

                let div = document.createElement("div");
                div.className = "d-flex mb-2";

                let inputTag = document.createElement("input");
                inputTag.type = "text";
                inputTag.className = "form-control";
                inputTag.name = "description";
                inputTag.value = "";
                inputTag.style.width = "97%";

                let removeBtn = document.createElement("button");
                removeBtn.type = "button";
                removeBtn.className = "btn btn-danger ms-2 d-flex align-items-center justify-content-center";
                removeBtn.style.width = "35px";
                removeBtn.style.height = "35px";
                removeBtn.onclick = function () {
                    removeInputTag(this);
                };

                let icon = document.createElement("i");
                icon.className = "fa fa-minus";

                removeBtn.appendChild(icon);

                div.appendChild(inputTag);
                div.appendChild(removeBtn);

                container.insertBefore(div, btn);
            }

            function removeInputTag(button) {
                let parentDiv = button.parentElement;
                parentDiv.remove();
                saveToLocalStorage();
            }

            function goBack(departmentId) {
                let frame = window.parent.document.getElementById('viewDepartmentFrame');
                frame.src = "viewdepartment?departmentId=" + departmentId;
            }

        </script>

    </body>
</html>
