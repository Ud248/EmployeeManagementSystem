<%-- 
    Document   : adminProjectUpdate
    Created on : Mar 5, 2025, 11:25:41 PM
    Author     : nongt
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

            body{
                height: 100vh;
                background-color: #EFEFEF;
            }

            .form-label {
                text-align: left;
                font-weight: bold;
                padding-left: 15px;
                width: 220px; /* Thay đổi từ min-width thành width cố định */
                display: inline-block;
                margin-right: 25px;
                vertical-align: top;
            }

            /* Normal text for details instead of bold */
            .detail {
                font-weight: normal;
                display: inline-block;
                vertical-align: top;
                width: 74%; /* Chiều rộng cố định tính toán từ width của label + margin */
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
            .submit-button {
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
            .submit-button:hover {
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

            .field-container span{
                width: 75%;
            }

        </style>
    </head>
    <body>

        <div class="container">
            <div class="modal-content" style="padding: 1.5rem 1.5rem 1rem 1.5rem">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h4 class="modal-title mx-auto">UPDATE DEPARTMENT DETAIL</h4>
                </div>
                <div class="modal-body">
                    <form action="updatedepartment" method="post">
                        <div class="mb-3">
                            <h5 class="section-header">GENERAL INFORMATION</h5>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Department ID:</label>
                                        <span class="detail">${departmentId}</span>
                                        <input type="hidden" name="departmentId" value="${departmentId}"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Department Name:</label>
                                        <input type="text" class="form-control detail" name="departmentName" value="${departmentName}" required>
                                        <div class="error">${errorNameMsg}</div>
                                    </div>
                                </div>                                
                            </div>
                        </div>

                        <div class="section-divider"></div>

                        <div class="mb-3">
                            <h5 class="section-header">WORK INFORMATION</h5>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Open Time:</label>
                                        <input type="text" class="form-control detail" name="openTime" value="${openTime}" required>
                                        <div class="error">${errorOpenTimeMsg}</div>
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Manager:</label>
                                        <span class="detail">${manager}</span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Telephone:</label>
                                        <input type="text" class="form-control" name="telephone" value="${telephone}" required>
                                        <div class="error">${errorTelephoneMsg}</div>
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Total Employee:</label>
                                        <span class="detail">${totalEmployee}</span>
                                    </div>
                                </div>
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
                                    <c:forEach var="sub" items="${descriptionArray}" varStatus="status">
                                        <div class="d-flex mb-2">
                                            <input type="text" class="form-control flex-grow-1" name="description" value="${sub}"/>
                                            <button type="button" class="btn btn-danger ms-2 d-flex align-items-center justify-content-center" style="width: 35px; height: 35px;" onclick="removeInputTag(this)">
                                                <i class="fa fa-minus"></i>
                                            </button>
                                        </div>
                                    </c:forEach>

                                    <button type="button" id="btn-add-description" class="btn btn-dark rounded-circle d-flex align-items-center justify-content-center mt-2" style="width: 30px; height: 30px;" onclick="addInputTag()">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                    <div class="error">${errorDescriptionMsg}</div>
                                </div>
                            </div>

                            <div class="row mb-3 align-items-center">
                                <div class="col-md-4 text-end">
                                    <label class="form-label">Cost Per Month:</label>
                                </div>                                
                                <div class="col-md-8">
                                    <span class="detail">${costPerMonth}</span>
                                </div>
                            </div>
                        </div>
                        <input type="submit" class="submit-button" value="Update"/>
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
