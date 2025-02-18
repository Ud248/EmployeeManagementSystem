<%-- 
    Document   : Admin
    Created on : Feb 17, 2025, 10:43:20 AM
    Author     : anhnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdmin.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Lấy tất cả các mục menu
                const menuItems = document.querySelectorAll('.menu li');

                // Lặp qua mỗi mục và thêm sự kiện click
                menuItems.forEach(item => {
                    item.addEventListener('click', function () {
                        // Xóa lớp 'selected' khỏi tất cả mục
                        menuItems.forEach(i => i.classList.remove('selected'));

                        // Thêm lớp 'selected' cho mục được nhấp
                        item.classList.add('selected');
                    });
                });
            });

//content
            // src/main/webapp/js/employee.js
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
        <div class="sidebar">
            <div class="avatar">
                <img src="./image/tai-anh-phong-canh-dep-49.jpg" alt="alt"/>
                <h4>Welcome ${sessionScope.username}</h4>
            </div>
            <ul class="menu">
                <li><a href="#">Employee List</a></li>
                <li><a href="#">Work Schedule</a></li>
                <li><a href="#">Attendance Report</a></li>
            </ul>
        </div>
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
        </div>
    </body>
</html>
