<%-- 
    Document   : Admin
    Created on : Feb 17, 2025, 10:43:20 AM
    Author     : anhnn
--%>
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
        <title>Home page</title>
        <link rel="icon" type="image/x-icon" href="image/logo.png">
        <link rel="stylesheet" href="./css/styleAdmin.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- ICONS -->
        <script src="https://unpkg.com/@phosphor-icons/web"></script>
    </head>

    <body>
        <div class="sidebar">
            <div class="avatar">
                <img src="image/${sessionScope.employee.gender == 'Nam' ? 'MaleAvatar.png' : 'FemaleAvatar.png'}" alt="Employee Avatar">
                <p class="position">${sessionScope.employee.positionName}</p>
                <h3 class="name">${sessionScope.employee.fullname}</h3>
            </div>


            <div class="main"> 
                <h4 class="title-side-bar">Main</h4>
                <ul class="menu">
                    <li>
                        <a href="javascript:void(0);" onclick="loadContent('adminEmployeeList')">
                            <i class="ph-bold ph-list"></i>
                            <span class="text">Employee List</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="loadContent('adminDepartmentManagement')">
                            <i class="ph ph-users-four"></i>    
                            <span class="text">Department Management</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="loadContent('adminWorkSchedule')">
                            <i class="ph-bold ph-calendar-blank"></i>
                            <span class="text">Work Schedule</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="loadContent('attendanceReport')">
                            <i class="ph-bold ph-clipboard-text"></i>
                            <span class="text">Attendance Report</span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="account">
                <h4 class="title-side-bar">Account</h4>
                <ul class="menu">
                    <li>
                        <a href="changePassword.jsp">
                            <i class="ph-bold ph-arrows-clockwise"></i>
                            <span class="text">Change Password</span>
                        </a>
                    </li>
                    <li>
                        <a href="dang-xuat">
                            <i class="icon ph-bold ph-sign-out"></i>
                            <span class="text">Logout</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="content" id="main-content">

        </div>

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
            // Hàm tải trang con vào #main-content
            function loadContent(page) {
                $.ajax({
                    url: page + '.jsp',
                    success: function (data) {
                        $('#main-content').html(data);
                        sessionStorage.setItem('currentPage', page);
                    },
                    error: function () {
                        $('#main-content').html('<p>Không thể tải nội dung.</p>');
                    }
                });
            }
            // Khi trang tải xong, kiểm tra trạng thái trước đó và load trang tương ứng
            $(document).ready(function () {
                let savedPage = sessionStorage.getItem('currentPage');
                if (!savedPage) {
                    $('#main-content').html('<p>Hello</p>');
                } else {
                    loadContent(savedPage);
                }
            });

            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                const successMsg = urlParams.get('successMsg');
                const errorMsg = urlParams.get('errorMsg');

                if (successMsg) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Thành công!',
                        text: successMsg,
                        showConfirmButton: false,
                        timer: 2000
                    });
                }

                if (errorMsg) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Thất bại!',
                        text: errorMsg,
                        showConfirmButton: true
                    });
                }
            };
        </script>

    </body>
</html>


