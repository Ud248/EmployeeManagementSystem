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

            .detail {
                font-weight: bolder;
            }

            .mb-3 {
                margin-bottom: 0px !important;
            }

            .error{
                color: red;
                text-align: center;
            }

            .update-button {
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
            .update-button:hover {
                background-color: #218838; /* Màu tối hơn khi hover */
                transform: scale(1.05);
            }

        </style>
    </head>
    <body>

        <div class="container   ">
            <div class="modal-content p-4">
                <div class="modal-header d-flex align-items-center justify-content-between w-100">
                    <button onclick="goBack(${departmentId})" class="btn btn-secondary">Back</button>
                    <h3 class="modal-title mx-auto">UPDATE DEPARTMENT DETAIL</h3>
                </div>
                <br/>
                <div class="modal-body">
                    <form action="updatedepartment" method="post">
                        <div class="mb-3">
                            <h5 style="color: green">General Information</h5>
                            <div class="row mb-3 align-items-center">
                                <label class="col-md-4 col-form-label text-md-end">Department ID:</label>
                                <div class="col-md-8">
                                    <span class="detail">${departmentId}</span>
                                    <input type="hidden" name="departmentId" value="${departmentId}"/>
                                </div>
                            </div>

                            <div class="row mb-3"> 
                                <label class="col-md-4 col-form-label text-md-end">Department Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="departmentName" value="${departmentName}" required>
                                </div>
                                <div class="error">${errorNameMsg}</div>
                            </div>
                        </div>
                        <br/>

                        <div class="mb-3">
                            <h5 style="color: green">Work Information</h5>
                            <div class="row mb-3">
                                <label class="col-md-4 col-form-label text-md-end">Open Time:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="openTime" value="${openTime}" required>
                                </div>
                                <div class="error">${errorOpenTimeMsg}</div>
                            </div>

                            <div class="row mb-3 align-items-center">
                                <label class="col-md-4 col-form-label text-md-end">Manager:</label>
                                <div class="col-md-8">
                                    <span class="detail">${managerName}</span>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-md-4 col-form-label text-md-end">Telephone:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="telephone" value="${telephone}" required>
                                </div>
                                <div class="error">${errorTelephoneMsg}</div>
                            </div>     

                            <div class="row mb-3 align-items-center">
                                <label class="col-md-4 col-form-label text-md-end">Total Employee:</label>
                                <div class="col-md-8">
                                    <span class="detail">${totalEmployee}</span>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="mb-3">
                            <h5 style="color: green">Other Information</h5>
                            <div class="row mb-3">
                                <label class="col-md-4 col-form-label text-md-end">Description</label>
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
                                <label class="col-md-4 col-form-label text-md-end">Cost Per Month:</label>
                                <div class="col-md-8">
                                    <span class="detail">${costPerMonth}</span>
                                </div>
                            </div>
                        </div>
                        <input type="submit" class="update-button" value="Update"/>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function goBack() {
                window.location.href = "admin.jsp";
            }

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
