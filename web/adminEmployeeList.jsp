<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("employee") == null) {
        response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập, chuyển về trang login
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdminEmployeeList.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    </head>

    <body>
        <div id="insertEmployeePopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopupAndReload('insertEmployeePopup')">&times;</span>
                <iframe id="insertEmployeeFrame" src="insertEmployee.jsp"></iframe>
            </div>
        </div>

        <div id="viewEmployeePopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopupAndReload('viewEmployeePopup')">&times;</span>
                <iframe id="viewEmployeeFrame" src="viewEmployee.jsp"></iframe>
            </div>
        </div>

        <div id="updateEmployeePopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopupAndReload('viewEmployeePopup')">&times;</span>
                <iframe id="updateEmployeeFrame" src="updateEmployee.jsp"></iframe>
            </div>
        </div>

        <div class="content">
            <div style="padding: 20px 20px 0px 20px">
                <div class="toolbar">
                    <h3 class="title_table">Employee List</h3>
                </div>

                <div class="action-btn">
                    <button id="deleteButton" class="delete-btn" onclick="toggleDeleteMode()">Delete</button>
                    <button class="new-employee-btn" onclick="openPopup('insertEmployeePopup')">
                        <i class="fas fa-plus"></i> New Employee
                    </button> 
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th class="select-column"><input type="checkbox" id="selectAll" style="display: none;"></th>
                            <th>Employee Code</th>
                            <th>Full Name</th>
                            <th>Telephone</th>
                            <th>Position Name</th>
                            <th>Department Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="e" items="${applicationScope.employees}">
                            <tr>
                                <td class="select-column" style="text-align: center;">
                                    <input type="checkbox" class="rowCheckbox" value="${e.getEmployeeCode()}" style="display: none;">
                                </td>
                                <td><a href="#" onclick="openViewPopup('viewEmployeePopup', '${e.getEmployeeCode()}', event)">${e.getEmployeeCode()}</a></td>
                                <td>${e.getFullname()}</td>
                                <td>${e.getTel()}</td>
                                <td>${e.getPositionName()}</td>
                                <td>${e.getDepartmentName()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


                <!-- Pagination -->
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="load-data?page=${currentPage - 1}">&laquo; Previous</a>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="load-data?page=${i}" 
                           class="${i == currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="load-data?page=${currentPage + 1}">Next &raquo;</a>
                    </c:if>
                </div>

                <div class="page-info">
                    Showing page ${currentPage} of ${totalPages}
                    (Total: ${totalEmployees} employees)
                </div>
            </div>
        </div>
        <script>
            //delete 
            if (typeof deleteMode === 'undefined') {
                var deleteMode = false;
            }

            function toggleDeleteMode() {
                deleteMode = !deleteMode;

                let checkboxes = document.querySelectorAll('.rowCheckbox');
                let selectAll = document.getElementById('selectAll');

                if (deleteMode) {
                    checkboxes.forEach(cb => cb.style.display = 'inline');
                    selectAll.style.display = 'inline';
                    document.getElementById('deleteButton').textContent = 'Confirm Delete';
                } else {
                    let selectedIds = [];
                    checkboxes.forEach(cb => {
                        if (cb.checked) {
                            selectedIds.push(cb.value);
                        }
                    });

                    if (selectedIds.length > 0) {
                        if (confirm("Are you sure you want to delete these employees?")) {
                            window.location.href = "delete-employee?employeeCode=" + selectedIds.join(',');
                        }
                    }

                    checkboxes.forEach(cb => {
                        cb.style.display = 'none';
                        cb.checked = false;
                    });
                    selectAll.style.display = 'none';
                    document.getElementById('deleteButton').textContent = 'Delete';
                }
            }

            document.getElementById('selectAll').addEventListener('change', function () {
                let checkboxes = document.querySelectorAll('.rowCheckbox');
                checkboxes.forEach(cb => cb.checked = this.checked);
            });

            function openPopup(id) {
                document.getElementById(id).style.display = 'flex';
            }

            function openViewPopup(id, employeeCode = null, event) {
                event.preventDefault();
                let popup = document.getElementById(id);

                if (employeeCode && id === 'viewEmployeePopup') {
                    document.getElementById('viewEmployeeFrame').src = "view-employee?employeeCode=" + employeeCode;
                }

                popup.style.display = 'flex';
            }

            function openUpdatePopup(id, employeeCode = null) {
                event.preventDefault();
                let popup = document.getElementById(id);

                if (employeeCode && id === 'updateEmployeePopup') {
                    document.getElementById('updateEmployeeFrame').src = "update-employee?employeeCode=" + employeeCode;
                }

                popup.style.display = 'flex';
            }

            function closePopupAndReload(id) {
                document.getElementById(id).style.display = 'none';
                location.reload();
            }

            document.addEventListener('click', function (event) {
                let popups = document.querySelectorAll('.popup');

                popups.forEach(popup => {
                    if (event.target === popup) {
                        popup.style.display = 'none';
                        location.reload();
                    }
                });
            });
        </script>
    </body>
</html>
