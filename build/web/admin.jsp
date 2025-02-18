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
//content
    // src/main/webapp/js/employee.js
    function searchEmployees() {
        const input = document.getElementById("searchName");
        const filter = input.value.toUpperCase();
        const table = document.querySelector(".table");
        const rows = table.getElementsByTagName("tr");

        for (let i = 1; i < rows.length; i++) {
            const firstNameCell = rows[i].getElementsByTagName("td")[2]; // First Name column
            if (firstNameCell) {
                const txtValue = firstNameCell.textContent || firstNameCell.innerText;
                rows[i].style.display = txtValue.toUpperCase().indexOf(filter) > -1 ? "" : "none";
            }
        }
    }

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

    function changeItemsPerPage(value) {
        window.location.href = '?page=1&items=' + value;
    }

// Debounce function để tối ưu search
    function debounce(func, wait) {
        let timeout;
        return function executedFunction(...args) {
            const later = () => {
                clearTimeout(timeout);
                func(...args);
            };
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
        };
    }

// Áp dụng debounce cho search
    const debouncedSearch = debounce(searchEmployees, 300);

// Thêm event listener khi document đã load
    document.addEventListener('DOMContentLoaded', function () {
        const searchInput = document.getElementById('searchName');
        if (searchInput) {
            searchInput.addEventListener('input', debouncedSearch);
        }
    });
</script>

<script>
    // Hàm tải trang con vào #main-content
    function loadContent(page) {
        $.ajax({
            url: page + ".jsp",
            success: function (data) {
                $("#main-content").html(data);
                localStorage.setItem("currentPage", page); // Lưu trạng thái vào localStorage
            },
            error: function () {
                $("#main-content").html("<p>Không thể tải nội dung.</p>");
            }
        });
    }

    // Khi trang tải xong, kiểm tra trạng thái trước đó và load trang tương ứng
    $(document).ready(function () {
        let savedPage = localStorage.getItem("currentPage");
        if (!savedPage) {
            savedPage = "adminEmployeeList"; // Mặc định mở adminEmployeeList
        }
        loadContent(savedPage);
    });
</script>
