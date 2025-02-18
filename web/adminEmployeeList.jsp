<%-- 
    Document   : adminEmployeeList
    Created on : Feb 18, 2025, 5:31:37 PM
    Author     : anhnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdminEmployeeList.css"/>
    </head>
    <body>
        <div class="container">
            <h2 class="page-title">Employee List</h2>

            <div class="toolbar">
                <input type="text" 
                       class="search-box" 
                       id="searchName" 
                       placeholder="Search With FirstName">

                <button class="new-employee-btn" onclick="location.href = 'newEmployee.jsp'">
                    <i class="fas fa-plus"></i> New Employee
                </button>
            </div>

            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Gender</th>
                        <th>Birth Date</th>
                        <th>Telephone</th>
                        <th>Position Name</th>
                        <th>Department Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>${employee.getEmployeeCode()}</td>
                        <td>${employee.getFullName()}</td>
                        <td>${employee.getGender()}</td>
                        <td>${employee.getBirthDate()}</td>
                        <td>${employee.getTel()}</td>
                        <td>${employee.getPositionId()}</td>
                        <td>${employee.getDepartmentId()}</td>
                        <td>
                            <button class="btn btn-view" onclick="viewEmployee(${employee.id})">
                                <i class="fas fa-eye"></i>
                            </button>
                            <button class="btn btn-edit" onclick="editEmployee(${employee.id})">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button class="btn btn-delete" onclick="deleteEmployee(${employee.id})">
                                <i class="fas fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="?page=${currentPage - 1}">Prev</a>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <span class="current-page">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}">Next</a>
                </c:if>

                <select class="items-per-page" onchange="changeItemsPerPage(this.value)">
                    <option value="5" ${itemsPerPage == 5 ? 'selected' : ''}>5</option>
                    <option value="10" ${itemsPerPage == 10 ? 'selected' : ''}>10</option>
                    <option value="20" ${itemsPerPage == 20 ? 'selected' : ''}>20</option>
                </select>
            </div>
        </div>
    </body>
</html>
