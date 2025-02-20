<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdminEmployeeList.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <style>


            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
                gap: 10px;
            }

            .pagination a {
                padding: 8px 12px;
                border: 1px solid #ddd;
                text-decoration: none;
                color: #333;
            }

            .pagination a.active {
                background-color: #007bff;
                color: white;
                border-color: #007bff;
            }

            .pagination a:hover:not(.active) {
                background-color: #f0f0f0;
            }

            .page-info {
                text-align: center;
                margin-top: 10px;
                color: #666;
            }

            /* Style cho popup */
            .popup {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;
            }

            .popup-content {
                background: white;
                width: 600px;
                height: 86%;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                position: relative;
            }

            .popup-content iframe {
                width: 100%;
                height: 90%;
                border: none;
            }

            .close-btn {
                position: absolute;
                top: 10px;
                right: 15px;
                font-size: 20px;
                cursor: pointer;
            }
        </style>
        
        <script>
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

            function openPopup() {
                document.getElementById('insertEmployeePopup').style.display = 'flex';
            }

            function closePopup() {
                document.getElementById('insertEmployeePopup').style.display = 'none';
                location.reload();
            }

            // Đóng popup khi bấm ra ngoài
            document.addEventListener('click', function (event) {
                let popup = document.getElementById('insertEmployeePopup');
                let popupContent = document.querySelector('.popup-content');

                if (event.target === popup) {
                    closePopup();
                }
            });
        </script>
    </head>
    <body>
        <div id="insertEmployeePopup" class="popup">
            <div class="popup-content">
                <span class="close-btn" onclick="closePopup()">&times;</span>
                <iframe id="insertEmployeeFrame" src="insertEmployee.jsp"></iframe>
            </div>
        </div>

        <div class="content">
            <div style="padding: 20px 20px 0px 20px">
                <h2 class="page-title">Employee List</h2>

                <div class="toolbar">
                    <input type="text" 
                           class="search-box" 
                           id="searchName" 
                           placeholder="Search With FirstName">

                    <button class="new-employee-btn" onclick="openPopup()">
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
                        <c:forEach var="e" items="${sessionScope.employees}">
                            <tr>
                                <td>${e.getEmployeeCode()}</td>
                                <td>${e.getFullname()}</td>
                                <td>${e.getGender()}</td>
                                <td>${e.getFormattedBirthDate()}</td>
                                <td>${e.getTel()}</td>
                                <td>${e.getPositionName()}</td>
                                <td>${e.getDepartmentName()}</td>
                                <td>
                                    <button class="btn btn-view" onclick="viewEmployee(${e.getEmployeeCode()})">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                    <button class="btn btn-edit" onclick="editEmployee(${e.getEmployeeCode()})">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-delete" onclick="deleteEmployee(${e.getEmployeeCode()})">
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
    </div>
</body>
</html>
