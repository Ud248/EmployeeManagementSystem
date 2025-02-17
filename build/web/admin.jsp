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
        </script>
    </head>
    <body>
        <div class="sidebar">
            <div class="avatar">
                <img src="./image/tai-anh-phong-canh-dep-49.jpg" alt="alt"/>
                <h4>Welcome ${sessionScope.username}</h4>
            </div>
            <ul class="menu">
                <li><a href="#">Employee List</a></li>
                <li><a href="#">Work Schedule</a></li>
                <li><a href="#">Attendance Report</a></li>
            </ul>
        </div>
        <div class="content">
            <h1>Nội dung chính</h1>
            <p>Đây là phần nội dung chính của trang.</p>
        </div>
    </body>
</html>
