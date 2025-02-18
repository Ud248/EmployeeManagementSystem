<%-- 
    Document   : Admin
    Created on : Feb 17, 2025, 10:43:20 AM
    Author     : anhnn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdminEmployeeList.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <script>
            function searchEmployees() {
                const input = document.getElementById("searchName");
                const filter = input.value.toUpperCase();
                const table = document.querySelector(".table");
                const rows = table.getElementsByTagName("tr");

                for (let i = 1; i < rows.length; i++) {
                    const firstNameCell = rows[i].getElementsByTagName("td")[2]; // First Name column
                    if (firstNameCell) {
                        const txtValue = firstNameCell.textContent || firstNameCell.innerText;
                        rows[i].style.display = txtValue.toUpperCase().indexOf(filter) > -1 ? "" : "none";
                    }
                }
            }

            function viewEmployee(id) {
                window.location.href = 'viewEmployee?id=' + id;
            }

            function editEmployee(id) {
                window.location.href = 'editEmployee?id=' + id;
            }

            function deleteEmployee(id) {
                if (confirm('Bạn có chắc chắn muốn xóa nhân viên này?')) {
                    window.location.href = 'deleteEmployee?id=' + id;
                }
            }

            function changeItemsPerPage(value) {
                window.location.href = '?page=1&items=' + value;
            }

// Debounce function để tối ưu search
            function debounce(func, wait) {
                let timeout;
                return function executedFunction(...args) {
                    const later = () => {
                        clearTimeout(timeout);
                        func(...args);
                    };
                    clearTimeout(timeout);
                    timeout = setTimeout(later, wait);
                };
            }

// Áp dụng debounce cho search
            const debouncedSearch = debounce(searchEmployees, 300);

// Thêm event listener khi document đã load
            document.addEventListener('DOMContentLoaded', function () {
                const searchInput = document.getElementById('searchName');
                if (searchInput) {
                    searchInput.addEventListener('input', debouncedSearch);
                }
            });
        </script>
    </head>
    <body>
        <div class="content">
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
                        <c:forEach var="e" items="${employees}">
                            <tr>
                                <td>${e.getEmployeeCode()}</td>
                                <td>${e.getFullName()}</td>
                                <td>${e.getGender()}</td>
                                <td>${e.getBirthDate()}</td>
                                <td>${e.getTel()}</td>
                                <td>${e.getPositionId()}</td>
                                <td>${e.getDepartmentId()}</td>
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
        </div>
    </body>
</html>
