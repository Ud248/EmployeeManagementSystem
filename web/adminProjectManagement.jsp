<%-- 
    Document   : adminProjectManagement
    Created on : Mar 5, 2025, 11:23:41 PM
    Author     : nongt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("employee") == null) {
        response.sendRedirect("login");
        return;
    }
    else if(!(boolean)session.getAttribute("isAdmin")){
        response.sendRedirect("403Error.jsp");
        return;
    }
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Management</title>
        <link rel="stylesheet" href="css/styleAdminDepartmentManagement.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="icon" type="image/x-icon" href="./image/Logo.jpg">
        <script src="https://unpkg.com/@phosphor-icons/web"></script>
    </head>
    <body>

        <jsp:include page="./layout/sidebar.jsp" />

        <div class="content">
            <div style="padding: 20px 20px 0px 20px">
                <div class="head">
                    <h1 class="title_table">Project List</h1>
                </div>
                <div class="action-btn">
                    <button id="deleteButton" class="delete-btn" disabled><i class="ph ph-trash"></i>Delete</button>
                    <button class="new-employee-btn" onclick="openPopup('insertProjectPopup')">
                        <i class="fas fa-plus"></i> New Project
                    </button>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th class="select-column"><input type="checkbox" id="selectAll" onchange="toggleDeleteMode()"></th>
                            <th>Project Code</th>
                            <th>Project Name</th>
                            <th>Department Name</th>
                            <th>Start Date</th>
                            <th>Dead Line</th>
                            <th>Completion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${projectDTOs}">
                            <tr>
                                <td class="select-column" style="text-align: center;">
                                    <input type="checkbox" class="rowCheckbox" value="${p.getProjectCode()}" onchange="toggleDeleteMode()">
                                </td>
                                <td>${p.getProjectCode()}</td>
                                <td><a href="#" onclick="openViewPopup('viewProjectPopup', '${p.getProjectCode()}', event)">${p.getProjectName()}</a></td>
                                <td>${p.getDepartmentName()}</td>
                                <td class="select-column" style="text-align: center">${p.getStartDate()}</td>
                                <td class="select-column" style="text-align: center">${p.getDeadLine()}</td>
                                <td class="select-column" style="text-align: center">${p.getCompletion()}%</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="show-project?page=${currentPage - 1}">&laquo; Previous</a>
                    </c:if>
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <a href="show-project?page=${i}" 
                           class="${i == currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>
                    <c:if test="${currentPage < totalPage}">
                        <a href="show-project?page=${currentPage + 1}">Next &raquo;</a>
                    </c:if>
                </div>
                <div class="page-info">
                    Showing page ${currentPage} of ${totalPage} 
                    (Total: ${applicationScope.totalProject} projects)
                </div>
            </div>                
        </div>
        <div id="insertProjectPopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopupAndReload('insertProjectPopup')">&times;</span>
                <iframe id="insertProjectFrame" src="adminProjectInsert.jsp"></iframe>
            </div>
        </div>
        <div id="viewProjectPopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopupAndReload('viewProjectPopup')">&times;</span>
                <iframe id="viewProjectFrame"></iframe>
            </div>
        </div>

        <% 
            String actionMsg = (String) session.getAttribute("actionMsg");
            if (actionMsg != null) {
            session.removeAttribute("actionMsg");
        %>
        <script>
            Swal.fire({
                icon: "<%= actionMsg.contains("successfully") ? "success" : "error" %>",
                title: "Notification",
                text: "<%= actionMsg %>",
                timer: 2000,
                showConfirmButton: false
            });
        </script>
        <%
            }
        %>

        <script>
            function openPopup(id) {
                document.getElementById(id).style.display = 'flex';
            }

            function openViewPopup(id, projectCode = null, event) {
                event.preventDefault();
                let popup = document.getElementById(id);

                if (projectCode && id === 'viewProjectPopup') {
                    document.getElementById('viewProjectFrame').src = "view-project?projectCode=" + projectCode;
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

            function toggleDeleteMode() {
                let checkboxes = document.querySelectorAll(".rowCheckbox");
                let deleteButton = document.getElementById("deleteButton");
                let activeDeleteButton = Array.from(checkboxes).some(cb => cb.checked);
                deleteButton.disabled = !activeDeleteButton;
            }

            document.getElementById('selectAll').addEventListener('change', function () {
                let checkboxes = document.querySelectorAll('.rowCheckbox');
                checkboxes.forEach(cb => cb.checked = this.checked);
                if (!this.checked) {
                    deleteButton.disabled = true;
                } else {
                    deleteButton.disabled = false;
                }
            });

            function getValueChecked() {
                let checkboxes = document.querySelectorAll(".rowCheckbox:checked");
                let selectedIds = [];
                checkboxes.forEach(cb => {
                    selectedIds.push(cb.value);
                });
                return selectedIds;
            }

            document.getElementById("deleteButton").addEventListener("click", function () {
                Swal.fire({
                    title: "Bạn có chắc chắn muốn xóa?",
                    text: "Hành động này không thể hoàn tác!",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#d33",
                    cancelButtonColor: "#3085d6",
                    confirmButtonText: "Xóa",
                    cancelButtonText: "Hủy"
                }).then((result) => {
                    if (result.isConfirmed) {
                        let selectedIds = getValueChecked().join(",");
                        window.location.href = "delete-project?page=" + ${currentPage} + "&projectCode=" + selectedIds;
                    }
                });
            });

            document.getElementById('searchButton').addEventListener('click', function () {
                let searchName = document.getElementById('searchName').value;
                window.location.href = "search-project?name=" + encodeURIComponent(searchName);
            });
        </script>
    </body>
</html>