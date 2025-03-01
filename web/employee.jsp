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
        <link rel="stylesheet" href="./css/styleEmployee.css">
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
                    <li data-target="work-schedule">
                        <a href="#">
                            <i class="ph-bold ph-calendar-blank"></i>
                            <span class="text">Work Schedule</span>
                        </a>
                    </li>
                    <li data-target="attendance-report">
                        <a href="#">
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

        <div class="content">
            <div id="work-schedule" class="content-section">
                <div class="title">
                    <h2>Work Schedule List</h2>
                </div>

                <div>
                    <table>
                        <thead>
                            <tr style="text-align:center">
                                <th style="width: 25px; padding-bottom:20px">ID</th>
                                <th style="width: 55px; padding-bottom:20px">Date</th>
                                <th style="width: 35px; padding-bottom:20px">WeekDay</th>
                                <th style="width: 80px; padding-bottom:20px">Morning Shift</th>
                                <th style="width: 80px; padding-bottom:20px">Afternoon Shift</th>
                                <th style="width: 80px; padding-bottom:20px">Night Shift</th>
                                <th style="width: 40px; padding-bottom:20px"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr style="text-align:center">
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>
                                    <form action="attendance" method="POST" onsubmit="hideButton(this)">
                                        <input type="hidden" name="scheduleId" value="${schedule.id}" />
                                        <button type="submit" class="btn-attend">Chấm công</button>
                                    </form>
                                </td>

                            </tr>

                            <tr style="text-align:center">
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div id="attendance-report" class="content-section">
                <h2>Attendance Report</h2>

            </div>
        </div>

    </div>
</body>
</html>
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

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const menuItems = document.querySelectorAll('.menu li');
        const contentSections = document.querySelectorAll('.content-section');

        // Ẩn tất cả nội dung trừ phần đầu tiên
        contentSections.forEach(section => section.style.display = "none");
        document.getElementById("work-schedule").style.display = "block"; // Hiển thị nội dung mặc định

        menuItems.forEach(item => {
            item.addEventListener('click', function () {
                // Xóa lớp 'selected' khỏi tất cả mục
                menuItems.forEach(i => i.classList.remove('selected'));

                // Thêm lớp 'selected' cho mục được nhấp
                item.classList.add('selected');

                // Ẩn tất cả nội dung
                contentSections.forEach(section => section.style.display = "none");

                // Hiển thị nội dung tương ứng
                const target = item.getAttribute("data-target");
                document.getElementById(target).style.display = "block";
            });
        });
    });
</script>

<script>
    function hideButton(form) {
        // Ẩn nút sau khi form được gửi
        var button = form.querySelector('button');
        button.style.display = 'none';  // Ẩn nút bằng cách thay đổi display
    }
</script>
