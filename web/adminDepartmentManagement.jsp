<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("employee") == null) {
        response.sendRedirect("admin.jsp");
        return;
    }
    else if(!(boolean)session.getAttribute("isAdmin")){
        response.sendRedirect("403Error.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Department Management</title>
        <link rel="stylesheet" href="./css/styleAdminDepartmentManagement.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="./image/Logo.jpg">
        <script src="https://unpkg.com/@phosphor-icons/web"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>

        <jsp:include page="./layout/sidebar.jsp" />

        <div class="content">
            <div style="padding: 20px 20px 0px 20px">
                <div class="head">
                    <h1 class="title_table">Department List</h1>
                </div>

                <div class="action-btn">
                    <button id="deleteButton" class="delete-btn" disabled=""><i class="ph ph-trash"></i>Delete</button>
                    <button class="new-employee-btn" onclick="openPopup('insertDepartmentPopup', null, event)">
                        <i class="fas fa-plus"></i> New Department
                    </button> 
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th><input type="checkbox" id="selectAll"></th>
                            <th>Department Code</th>
                            <th>Department Name</th>
                            <th>Open Time</th>
                            <th>Manager</th>
                            <th>Telephone</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="d" items="${departmentDTOs}" varStatus="st">
                            <tr>
                                <td style="text-align: center;">
                                    <input type="checkbox" class="rowCheckbox" value="${d.getDepartmentCode()}" onchange="toggleDeleteMode()">
                                </td>
                                <td>${d.getDepartmentCode()}</td>
                                <td><a onclick="openPopup('viewDepartmentPopup', '${d.getDepartmentCode()}', event)" style="cursor: pointer;">${d.getDepartmentName()}</a></td>
                                <td style="text-align: center">${d.getOpenTime()}</td>
                                <td>${d.getManagerName()}</td>
                                <td style="text-align: center">${d.getTelephone()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="show-department?page=${currentPage - 1}">&laquo; Previous</a>
                    </c:if>

                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <a href="show-department?page=${i}" 
                           class="${i == currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPage}">
                        <a href="show-department?page=${currentPage + 1}">Next &raquo;</a>
                    </c:if>
                </div>

                <div class="page-info">
                    Showing page ${currentPage} of ${totalPage}
                    (Total: ${applicationScope.totalDepartment} departments)
                </div>
            </div>
        </div>

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
            function openPopup(id, departmentCode = null, event) {
                event.preventDefault();
                let popup = document.getElementById(id);
                if (departmentCode && id === 'viewDepartmentPopup') {
                    document.getElementById('viewDepartmentFrame').src = "view-department?departmentCode=" + departmentCode;
                } else if (id === 'insertDepartmentPopup') {
                    document.getElementById('insertDepartmentFrame').src = 'adminDepartmentInsert.jsp';
                }

                popup.style.display = 'flex';
            }

            function closePopup(id) {
                document.getElementById(id).style.display = 'none';
                location.reload();
            }

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
                        window.location.href = "delete-department?page=" + ${currentPage} + "&departmentCode=" + selectedIds;
                    }
                });
            });

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
