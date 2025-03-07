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

        <div class="content">
            <div style="padding: 20px 20px 0px 20px">
                <div class="d-flex">
                    <div class="toolbar">
                        <input type="text" class="search-box" id="searchName" placeholder="Search With Department Name"/>
                    </div>

                    <button id="searchButton" class="search-btn">Search</button>
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
                            <th>ID</th>
                            <th>Department Name</th>
                            <th>Open Time</th>
                            <th>Manager</th>
                            <th>Telephone</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="d" items="${applicationScope.departments}" varStatus="st">
                            <tr>
                                <td style="text-align: center;">
                                    <input type="checkbox" class="rowCheckbox" value="${d.getDepartmentId()}" onchange="toggleDeleteMode()">
                                </td>
                                <td style="text-align: center;">${st.count}</td>
                                <td><a onclick="openPopup('viewDepartmentPopup', ${st.count}, event)">${d.getDepartmentName()}</a></td>
                                <td style="text-align: center">${d.getOpenTime()}</td>
                                <td>${d.getManagerName()}</td>
                                <td style="text-align: center">${d.getTelephone()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="pagination">
                    <c:if test="${currentPageDep > 1}">
                        <a href="load-data?pageDep=${currentPageDep - 1}">&laquo; Previous</a>
                    </c:if>

                    <c:forEach begin="1" end="${totalPagesDep}" var="i">
                        <a href="load-data?pageDep=${i}" 
                           class="${i == currentPageDep ? 'active' : ''}">${i}</a>
                    </c:forEach>

                    <c:if test="${currentPageDep < totalPagesDep}">
                        <a href="load-data?pageDep=${currentPageDep + 1}">Next &raquo;</a>
                    </c:if>
                </div>

                <div class="page-info">
                    Showing page ${currentPageDep} of ${totalPagesDep}
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
                        window.location.href = "deletedepartment?departmentId=" + selectedIds;
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
