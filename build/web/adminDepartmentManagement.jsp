<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdminDepartmentManagement.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    </head>
    <body>

        <div id="insertDepartmentPopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopup('insertDepartmentPopup')">&times;</span>
                <iframe id="insertDepartmentFrame"></iframe>
            </div>
        </div>

        <div id="viewDepartmentPopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopup('viewDepartmentPopup')">&times;</span>
                <iframe id="viewDepartmentFrame"></iframe>
            </div>
        </div>


        <div class="content">
            <div style="padding: 20px 20px 0px 20px">
                <div class="d-flex">
                    <div class="toolbar">
                        <input type="text" class="search-box" id="searchName" placeholder="Search With Department Name"/>
                    </div>

                    <button id="searchButton" class="search-btn">Search</button>
                </div>

                <div class="action-btn">
                    <button id="deleteButton" class="delete-btn" onclick="toggleDeleteMode()">Delete</button>
                    <button class="new-employee-btn" onclick="openPopup('insertDepartmentPopup', null, event)">
                        <i class="fas fa-plus"></i> New Employee
                    </button> 
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th><input type="checkbox" id="selectAll" style="display: none;"></th>
                            <th>ID</th>
                            <th>Department Name</th>
                            <th>Open Time</th>
                            <th>Manager</th>
                            <th>Telephone</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="d" items="${applicationScope.departments}">
                            <tr>
                                <td style="text-align: center;">
                                    <input type="checkbox" class="rowCheckbox" value="${d.getDepartmentId()}" style="display: none;">
                                </td>
                                <td style="text-align: center;">${d.getDepartmentId()}</td>
                                <td><a onclick="openPopup('viewDepartmentPopup', ${d.getDepartmentId()}, event)">${d.getDepartmentName()}</a></td>
                                <td style="text-align: center">${d.getOpenTime()}</td>
                                <td>${d.getManagerName()}</td>
                                <td style="text-align: center">${d.getTelephone()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            function openPopup(id, departmentId = null, event) {
                event.preventDefault();
                let popup = document.getElementById(id);

                if (departmentId && id === 'viewDepartmentPopup') {
                    document.getElementById('viewDepartmentFrame').src = "viewdepartment?departmentId=" + departmentId;
                } else if (id === 'insertDepartmentPopup') {
                    document.getElementById('insertDepartmentFrame').src = 'adminDepartmentInsert.jsp';
                }

                popup.style.display = 'flex';
            }

            function closePopup(id) {
                document.getElementById(id).style.display = 'none';
                location.reload();
            }

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
                        if (confirm("Are you sure you want to delete these departments?")) {
                            window.location.href = "deletedepartment?departmentId=" + selectedIds.join(',');
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
        </script>
    </body>
</html>
