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
        String firstName = "" + request.getAttribute("firstName");
        firstName = firstName.equals("null") ? "": firstName;
        String lastName = "" + request.getAttribute("lastName");
        lastName = lastName.equals("null") ? "": lastName;
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
    <body>
        <div class="container">
            <div class="modal-content" style="padding-top: 35px">
                <div class="modal-header" style="padding-bottom: 15px">
                    <h3 class="modal-title mx-auto" id="insertEmployeeLabel">INSERT NEW EMPLOYEE</h5>
                </div>
                <div class="modal-body">
                    <form action="insert-employee" method="post">
                        <div class="row">
                            <!-- Cột bên trái -->
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="firstName" class="form-label">First Name</label>
                                    <input type="text" class="form-control" id="firstName" name="firstName" required value="<%=firstName%>">
                                </div>
                                <div class="mb-3">
                                    <label for="lastName" class="form-label">Last Name</label>
                                    <input type="text" class="form-control" id="lastName" name="lastName" required value="<%=lastName%>">
                                </div>
                                <div class="mb-3">
                                    <label for="birthdate" class="form-label">Birthdate</label>
                                    <input type="date" class="form-control" id="birthdate" name="birthdate" required value="<%=birthdateStr%>">
                                </div>
                                <div class="mb-3">
                                    <label for="gender" class="form-label">Gender</label>
                                    <select class="form-select" id="gender" name="gender">
                                        <option value="None">--Select gender--</option>
                                        <option value="Nam" ${gender == 'Nam' ? 'selected="selected"' : ''}>Nam</option>
                                        <option value="Nữ" ${gender == 'Nữ' ? 'selected="selected"' : ''}>Nữ</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Cột bên phải -->
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="telephone" class="form-label">Telephone</label>
                                    <input type="tel" class="form-control" id="telephone" name="telephone" required value="<%=tel%>">
                                </div>
                                <div class="mb-3">
                                    <label for="position" class="form-label">Position</label>
                                    <select class="form-select" id="position" name="position" required>
                                        <option selected value="None">Select Employee Position</option>
                                        <c:forEach var="position" items="${sessionScope.listPosition}">
                                            <option value="${position.positionId}" 
                                                    ${positionId == position.positionId ? 'selected="selected"' : ''}>
                                                ${position.positionName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="department" class="form-label">Department</label>
                                    <select class="form-select" id="department" name="department" required>
                                        <option selected value="None">Select Employee Department</option>
                                        <c:forEach var="department" items="${sessionScope.listDepartment}">
                                            <option value="${department.departmentId}" 
                                                    ${departmentId == department.departmentId ? 'selected="selected"' : ''}>
                                                ${department.departmentName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="basicSalary" class="form-label">Basic Salary</label>
                                    <input type="number" class="form-control" id="basicSalary" name="basicSalary" required min="0" value="<%=basicSalary%>">
                                </div>
                            </div>

                            <div class="col-md-12">
                                <label for="address" class="form-label">Address</label>
                                <textarea class="form-control" id="address" name="address" value required rows="3"><%=address%></textarea>
                            </div>
                        </div>
                        <br>
                        <div class="modal-footer justify-content-center">
                            <button type="submit" class="btn btn-primary">Insert</button>
                        </div>

                        <div class="text-center mt-3">
                            <span style="color:red"><%=error%></span>
                            <span style="color:green"><%=successMsg%></span>
                            <br>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
