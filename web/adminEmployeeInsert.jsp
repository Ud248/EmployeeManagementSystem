<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insert Employee</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <%
    if (session.getAttribute("employee") == null) {
        response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập, chuyển về trang login
        return;
    }
        String error = "" + request.getAttribute("error");
        error = error.equals("null") ? "": error;
        String successMsg = "" + request.getAttribute("successMsg");
        successMsg = successMsg.equals("null") ? "": successMsg;
        String fullname = "" + request.getAttribute("fullname");
        fullname = fullname.equals("null") ? "": fullname;
        String birthdateStr = "" + request.getAttribute("birthdateStr");
        birthdateStr = birthdateStr.equals("null") ? "": birthdateStr;
        String gender = "" + request.getAttribute("gender");
        gender = gender.equals("null") ? "": gender;
        String tel = "" + request.getAttribute("tel");
        tel = tel.equals("null") ? "": tel;
        String address = "" + request.getAttribute("address");
        address = address.equals("null") ? "": address;
        String positionId = "" + request.getAttribute("positionId");
        positionId = positionId.equals("null") ? "": positionId;
        String departmentId = "" + request.getAttribute("departmentId");
        departmentId = departmentId.equals("null") ? "": departmentId;
        String basicSalary = "" + request.getAttribute("basicSalary");
        basicSalary = basicSalary.equals("null") ? "": basicSalary;
    %>

    <style>
        :root {
            --primary-color: #0D1936;
            --secondary-color: #535354;
            --background-color: #EFEFEF;
            --shadow-color: rgba(0, 0, 0, 0.1);
            --white-color: #FFF;
            --black-color: #000;
            --input-border-color: #E3E4E6;
            --transition-3s: 0.3s;
        }

        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body{
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: var(--background-color);
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
        .insert-button {
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
        .insert-button:hover {
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
        <div style="max-width: 1030px;">
            <div class="modal-content">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto" style="font-weight: 600;">INSERT NEW EMPLOYEE</h3>
                </div>
                <form id="employeeForm" action="insert-employee" method="post">
                    <div class="modal-body">
                        <!-- PERSONAL INFORMATION SECTION -->
                        <div class="mb-3">
                            <h5 class="section-header">PERSONAL INFORMATION</h5>
                            <div class="row">
                                <!-- Left Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Full Name:</label>
                                        <input type="text" class="form-control detail" id="fullname" name="fullname" required value="<%=fullname%>">
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Birth Date:</label>                               
                                        <input type="date" class="form-control detail" id="birthdate" name="birthdate" required value="<%=birthdateStr%>">
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Telephone:</label>
                                        <input type="tel" class="form-control detail" id="telephone" name="telephone" required 
                                               pattern="0[0-9]{9}" title="Telephone must start with 0 and have 10 digits." 
                                               value="<%=tel%>">
                                    </div>
                                </div>

                                <!-- Right Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Gender:</label>
                                        <select class="form-select detail" name="gender">
                                            <option value="None">Select gender</option>
                                            <option value="Nam" ${gender == 'Nam' ? 'selected="selected"' : ''}>Nam</option>
                                            <option value="Nữ" ${gender == 'Nữ' ? 'selected="selected"' : ''}>Nữ</option>
                                        </select>
                                    </div>


                                    <div class="field-container">
                                        <label class="form-label">Address:</label>
                                        <textarea class="form-control detail" id="address" name="address" value required rows="3"><%=address%></textarea>
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
                                        <select class="form-select detail" id="position" name="position" required autocomplete="off">
                                            <option selected value="None">Select Employee Position</option>
                                            <c:forEach var="position" items="${applicationScope.listPosition}">
                                                <option value="${position.positionId}" 
                                                        ${positionId == position.positionId ? 'selected="selected"' : ''}>
                                                    ${position.positionName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="field-container">
                                        <label class="form-label">Department Name:</label>
                                        <select class="form-select detail" id="department" name="department" required autocomplete="off">
                                            <option selected value="None">Select Employee Department</option>
                                            <c:forEach var="department" items="${applicationScope.listDepartment}">
                                                <option value="${department.departmentId}" 
                                                        ${departmentId == department.departmentId ? 'selected="selected"' : ''}>
                                                    ${department.departmentName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <!-- Right Column -->
                                <div class="col-md-6">
                                    <div class="field-container">
                                        <label class="form-label">Basic Salary:</label>
                                        <input type="number" class="form-control detail" name="basicSalary" value="${basicSalary}">
                                    </div>
                                </div>
                            </div>
                        </div> 

                        <div>
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger mt-3">
                                    ${error}
                                </div>
                            </c:if>

                            <c:if test="${not empty successMsg}">
                                <div class="alert alert-success mt-3">
                                    ${successMsg}
                                </div>
                            </c:if>
                        </div>
                                    
                        <div>
                            <button type="submit" class="insert-button">Insert</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>                   
    </body>
</html>
