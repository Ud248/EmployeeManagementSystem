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
        <link rel="stylesheet" href="./css/styleAdminDepartmentUpdate.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    </head>
    <body>
        <button type="button" class="btn-back" onclick="goBack()">Back</button>

        <h2 style="text-align: center">Department Update</h2>

        <form action="updatedepartment" method="post">
            <div class="row">
                <div class="col-md-12">

                    <div class="mb-3">
                        <input type="hidden" name="departmentId" value="${departmentId}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Department Name</label>
                        <span style="font-style: italic; font-size: 70%">(requirement: Not empty field)</span>
                        <input type="text" class="form-control" name="departmentName" value="${departmentName}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Telephone</label>
                        <span style="font-style: italic; font-size: 70%">(requirement: Not empty field, Telephone must have 10 numbers from 0 to 9)</span>
                        <input type="text" class="form-control" name="telephone" value="${telephone}" required>
                        <c:if test="${not empty errorTelephoneMsg}">
                            <div style="color: red">${errorTelephoneMsg}</div>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Open Time</label>
                        <span style="font-style: italic; font-size: 70%">(requirement: Not empty field, Open Time must be in format 'HH:mm - HH:mm')</span>
                        <input type="text" class="form-control" name="openTime" value="${openTime}" required>
                        <c:if test="${not empty errorOpenTimeMsg}">
                            <div style="color: red">${errorOpenTimeMsg}</div>
                        </c:if>
                    </div>

                    <div class="mb-3" id="description-container">
                        <label class="form-label">Description</label> 
                        <span style="font-style: italic; font-size: 70%">(requirement: Not empty field)</span>

                        <c:forEach var="sub" items="${descriptionArray}" varStatus="status">
                            <div class="d-flex mb-2">
                                <input type="text" class="form-control" name="description" value="${sub}" style="width: 97%"/>
                                <button type="button" class="btn btn-danger ms-2 d-flex align-items-center justify-content-center" style="width: 35px; height: 35px;" onclick="removeInputTag(this)">
                                    <i class="fa fa-minus"></i>
                                </button>
                            </div>
                        </c:forEach>

                        <button type="button" id="btn-add-description" class="btn btn-dark rounded-circle d-flex align-items-center justify-content-center mt-2" style="width: 30px; height: 30px;" onclick="addInputTag()">
                            <i class="fa fa-plus"></i>
                        </button>

                        <c:if test="${not empty errorDescriptionMsg}">
                            <div style="color: red">${errorDescriptionMsg}</div>
                        </c:if>
                    </div>

                    <input type="submit" value="Update" class="btn-update"/>
                    <div style="color: red">${errorMsg} </div>

                </div>
            </div>
        </form>

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

        </script>

    </body>
</html>
