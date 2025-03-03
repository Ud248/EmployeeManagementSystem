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
                <span class="close-btn" onclick="closePopup('viewEmployeePopup')">&times;</span>
                <iframe id="viewEmployeeFrame" src="viewEmployee.jsp"></iframe>
            </div>
        </div>

        <div id="updateEmployeePopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopup('viewEmployeePopup')">&times;</span>
                <iframe id="updateEmployeeFrame" src="updateEmployee.jsp"></iframe>
            </div>
        </div>

        <div class="content">
            <div style="padding: 20px 20px 0px 20px">
                <div class="toolbar">
                    <h3 class="title_table">Employee List</h3>

                    <button class="new-employee-btn" onclick="openPopup('insertEmployeePopup')">
                        <i class="fas fa-plus"></i> New Employee
                    </button>
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th>Employee Code</th>
                            <th>Full Name</th>
                            <th>Telephone</th>
                            <th>Position Name</th>
                            <th>Department Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="e" items="${applicationScope.employees}">
                            <tr>
                                <td>${e.getEmployeeCode()}</td>
                                <td>${e.getFullname()}</td>
                                <td>${e.getTel()}</td>
                                <td>${e.getPositionName()}</td>
                                <td>${e.getDepartmentName()}</td>
                                <td class="action_button">
                                    <button class="btn btn-view" onclick="openViewPopup('viewEmployeePopup', '${e.getEmployeeCode()}')">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                    <button class="btn btn-edit" onclick="openUpdatePopup('updateEmployeePopup', '${e.getEmployeeCode()}')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-delete" onclick="deleteEmployee('${e.getEmployeeCode()}')">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
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
            function deleteEmployee(employeeCode) {
                if (confirm("Are you sure you want to delete employee " + employeeCode + "?")) {
                    // Gửi yêu cầu DELETE đến Servlet
                    fetch("delete-employee?employeeCode=" + employeeCode, {method: "GET"})
                            .then(response => {
                                if (response.ok) {
                                    return response.text();
                                }
                                throw new Error('Network response was not ok');
                            })
                            .then(data => {
                                alert(data); // Hiển thị thông báo xóa thành công/thất bại
                                // Tải lại trang để cập nhật danh sách
                                location.reload();
                            })
                            .catch(error => {
                                console.error("Error:", error);
                                alert("Có lỗi xảy ra khi xóa nhân viên");
                            });
                }
            }

            function openPopup(id) {
                document.getElementById(id).style.display = 'flex';
            }

            function openViewPopup(id, employeeCode = null) {
                let popup = document.getElementById(id);

                if (employeeCode && id === 'viewEmployeePopup') {
                    document.getElementById('viewEmployeeFrame').src = "view-employee?employeeCode=" + employeeCode;
                }

                popup.style.display = 'flex';
            }

            function openUpdatePopup(id, employeeCode = null) {
                let popup = document.getElementById(id);

                if (employeeCode && id === 'updateEmployeePopup') {
                    document.getElementById('updateEmployeeFrame').src = "update-employee?employeeCode=" + employeeCode;
                }

                popup.style.display = 'flex';
            }

            function closePopup(id) {
                document.getElementById(id).style.display = 'none';
            }

            function closePopupAndReload(id) {
                document.getElementById(id).style.display = 'none';
                if (id === 'insertEmployeePopup' || id === 'updateEmployeePopup') {
                    location.reload();
                }
            }

// Đóng popup khi bấm ra ngoài, chỉ reload nếu không phải popup View
            document.addEventListener('click', function (event) {
                let popups = document.querySelectorAll('.popup');

                popups.forEach(popup => {
                    if (event.target === popup) {
                        popup.style.display = 'none';
                        if (popup.id === 'insertEmployeePopup' || popup.id === 'updateEmployeePopup') {
                            location.reload();
                        }
                    }
                });
            });

        </script>
    </body>
</html>
