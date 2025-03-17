<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Management</title>
        <link rel="stylesheet" href="./css/styleAdminEmployeeManagement.css">
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
                    <h1 class="title_table">Employee List</h1>
                </div>

                <div class="action-btn">
                    <button id="deleteButton" class="delete-btn" disabled=""><i class="ph ph-trash"></i>Delete</button>
                    <button class="new-employee-btn" onclick="openPopup('insertEmployeePopup', null, event)">
                        <i class="fas fa-plus"></i> New Employee
                    </button> 
                    <form action="show-employee" method="GET">
                        <input id="search" type="text" name="search" placeholder="Search" value="${search}"/>

                        <button type="submit" class="search_btn" onclick="sendRequestSearch(event)">Search</button>
                    </form>
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th class="select-column"><input type="checkbox" id="selectAll" onchange="toggleDeleteMode()"></th>
                            <th>Employee Code</th>
                            <th>Full Name</th>
                            <th>Telephone</th>
                            <th>Position Name</th>
                            <th>Department Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="e" items="${employeeDTOs}">
                            <tr>
                                <td class="select-column" style="text-align: center;">
                                    <input type="checkbox" class="rowCheckbox" value="${e.getEmployeeCode()}" onchange="toggleDeleteMode()">
                                </td>
                                <td>${e.getEmployeeCode()}</td>
                                <td><a href="#" onclick="openPopup('viewEmployeePopup', '${e.getEmployeeCode()}', event)">${e.getFullname()}</a></td>
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
                        <a href="show-employee?page=${currentPage - 1}">&laquo; Previous</a>
                    </c:if>

                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <a href="show-employee?page=${i}" 
                           class="${i == currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPage}">
                        <a href="show-employee?page=${currentPage + 1}">Next &raquo;</a>
                    </c:if>
                </div>

                <div class="page-info">
                    Showing page ${currentPage} of ${totalPage}
                    (Total: ${applicationScope.totalEmployee} employees)
                </div>
            </div>
        </div>


        <div id="insertEmployeePopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopup('insertEmployeePopup')">&times;</span>
                <iframe id="insertEmployeeFrame" ></iframe>
            </div>
        </div>

        <div id="viewEmployeePopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopup('viewEmployeePopup')">&times;</span>
                <iframe id="viewEmployeeFrame" ></iframe>
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

            function openPopup(id, employeeCode = null, event) {
                event.preventDefault();
                let popup = document.getElementById(id);
                if (employeeCode && id === 'viewEmployeePopup') {
                    document.getElementById('viewEmployeeFrame').src = "view-employee?employeeCode=" + employeeCode;
                } else if (id === 'insertEmployeePopup') {
                    document.getElementById('insertEmployeeFrame').src = 'adminEmployeeInsert.jsp';
                }
                popup.style.display = 'flex';
            }

            function closePopup(id) {
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
                        window.location.href = "delete-employee?page=" + ${currentPage} + "&employeeCode=" + selectedIds;
                    }
                });
            });

            function sendRequestSearch(event) {
                event.preventDefault();
                let searchInputTag = document.getElementById("search");
                if (searchInputTag.value === "") {
                    window.location.href = "show-employee?page=1";
                } else {
                    window.location.href = "show-employee?page=1&search=" + searchInputTag.value;
                }
            }


        </script>
    </body>
</html>
