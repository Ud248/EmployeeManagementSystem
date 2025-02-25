<%-- 
    Document   : Admin
    Created on : Feb 17, 2025, 10:43:20 AM
    Author     : anhnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdmin.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    </head>
    <body>
        <div class="sidebar">
            <div class="avatar">
                <img src="./image/tai-anh-phong-canh-dep-49.jpg" alt="alt"/>
                <h4>Welcome ${sessionScope.username}</h4>
            </div>
            <ul class="menu">
                <li><a href="javascript:void(0);" onclick="loadContent('adminEmployeeList')">Employee List</a></li>
                <li><a href="javascript:void(0);" onclick="loadContent('adminWorkSchedule')">Work Schedule</a></li>
                <li><a href="javascript:void(0);" onclick="loadContent('attendanceReport')">Attendance Report</a></li>
            </ul>
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
        </script>

    </body>
</html>


