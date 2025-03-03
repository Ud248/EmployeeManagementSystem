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

        <div id="insertEmployeePopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopupAndReload('insertEmployeePopup')">&times;</span>
                <iframe id="insertEmployeeFrame"></iframe>
            </div>
        </div>

        <div id="viewDepartmentPopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopup('viewDepartmentPopup')">&times;</span>
                <iframe id="viewDepartmentFrame"></iframe>
            </div>
        </div>


        <div class="content">
            <div style="padding: 10px 20px 0px 20px">
                <div class="d-flex">
                    <div class="toolbar">
                        <input type="text" 
                               class="search-box" 
                               id="searchName" 
                               placeholder="Search With FirstName">
                    </div>

                    <button id="searchButton" class="search-btn">Search</button>
                </div>

                <button id="deleteButton" class="delete-btn" onclick="toggleDeleteMode()">Delete</button>
                <br/>

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
                                <td><a onclick="openViewPopup('viewDepartmentPopup', ${d.getDepartmentId()}, event)">${d.getDepartmentName()}</a></td>
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
            function submitFormByAction(action, departmentId, method) {
                let form = document.getElementById("formHidden_" + departmentId);
                if (action === "deletedepartment") {
                    if (!confirm("Are you sure you want to delete department " + departmentId + "?")) {
                        return;
                    }
                }
                form.action = action;
                form.method = method;
                form.submit();
            }

            function openViewPopup(id, departmentId = null, event) {
                event.preventDefault();
                let popup = document.getElementById(id);

                if (departmentId && id === 'viewDepartmentPopup') {
                    document.getElementById('viewDepartmentFrame').src = "viewdepartment?departmentId=" + departmentId;
                }

                popup.style.display = 'flex';
            }

            function closePopup(id) {
                document.getElementById(id).style.display = 'none';
                location.reload();
            }

            function closePopupAndReload(id) {
                document.getElementById(id).style.display = 'none';
                if (id === 'insertEmployeePopup' || id === 'updateEmployeePopup') {
                    location.reload();
                }
            }

            let deleteMode = false;

            function toggleDeleteMode() {
                deleteMode = !deleteMode;

                let checkboxes = document.querySelectorAll('.rowCheckbox');
                let selectAll = document.getElementById('selectAll');

                if (deleteMode) {
                    // Hiện checkbox
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
                            // Gửi danh sách ID về server để xử lý xóa
                            window.location.href = "deletedepartment?departmentId=" + selectedIds.join(',');
                        }
                    }

                    // Ẩn lại checkbox
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
