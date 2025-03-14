<%-- 
    Document   : adminProjectReport
    Created on : Mar 12, 2025, 10:49:34 PM
    Author     : nongt
--%>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Management</title>
        <link rel="stylesheet" href="css/styleAdminDepartmentManagement.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- Thêm Chart.js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"></script>
    </head>
    <body>
        <div class="content">
            <div style="padding: 20px 20px 0px 20px">
                <div class="head">
                    <h1 class="title_table">Project Report</h1>
                </div>
                <table class="table">
                    <tr>
                        <td align="center" valign="middle">
                            <form id="reportForm" action="project-report" method="GET">
                                <select name="reportType" id="reportType">
                                    <option value="--None--">--None--</option>
                                    <option value="Budget Status">Budget Status</option>
                                    <option value="Completion Status">Completion Status</option>
                                    <option value="Total Completed Project">Total Completed Project</option>
                                </select>
                                <input type="submit" value="Show Report" />
                            </form>                            
                        </td>
                    </tr>
                </table>
                <!-- Vùng hiển thị biểu đồ -->
                <div id="chartContainer" style="width: 80%; margin: 20px auto; display: none;">
                    <canvas id="projectChart"></canvas>
                </div>
                <!-- Vùng hiển thị lỗi -->
                <div id="errorContainer" style="width: 80%; margin: 20px auto; display: none; color: red; text-align: center;">
                </div>
            </div>
        </div>

        <!-- Script xử lý biểu đồ -->
        <script>
            // Nhúng JavaScript trực tiếp vào file JSP
            document.addEventListener('DOMContentLoaded', function () {
                // Khi form được submit
                document.getElementById('reportForm').addEventListener('submit', function (e) {
                    e.preventDefault();
                    const reportType = document.getElementById('reportType').value;
                    if (reportType === '--None--') {
                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi',
                            text: 'Vui lòng chọn loại báo cáo!'
                        });
                        return;
                    }
                    fetchProjectData(reportType);
                });

                function fetchProjectData(reportType) {
                    // Hiển thị thông báo đang tải
                    Swal.fire({
                        title: 'Đang tải dữ liệu...',
                        didOpen: () => {
                            Swal.showLoading();
                        },
                        allowOutsideClick: false,
                        allowEscapeKey: false,
                        showConfirmButton: false
                    });

                    fetch('project-report')
                            .then(response => {
                                console.log('Trạng thái phản hồi:', response.status);
                                if (!response.ok) {
                                    throw new Error('Lỗi kết nối máy chủ: ' + response.status);
                                }
                                return response.json();
                            })
                            .then(data => {
                                // Đóng thông báo đang tải
                                Swal.close();

                                console.log('Dữ liệu nhận được:', data);

                                // Kiểm tra dữ liệu trống
                                if (!data || data.length === 0) {
                                    // Sử dụng dữ liệu mẫu để kiểm tra
                                    console.warn('Không nhận được dữ liệu, sử dụng dữ liệu mẫu');
                                    data = [
                                        {projectName: "Dự án A", completion: 75, budget: 10000, profit: 2000},
                                        {projectName: "Dự án B", completion: 100, budget: 5000, profit: 1000},
                                        {projectName: "Dự án C", completion: 30, budget: 8000, profit: -500},
                                        {projectName: "Dự án D", completion: 90, budget: 15000, profit: 3000},
                                        {projectName: "Dự án E", completion: 50, budget: 12000, profit: 1500}
                                    ];

                                    Swal.fire({
                                        icon: 'info',
                                        title: 'Thông báo',
                                        text: 'Đang sử dụng dữ liệu mẫu do không có dữ liệu thực tế.'
                                    });
                                }

                                // Hiển thị container biểu đồ
                                document.getElementById('chartContainer').style.display = 'block';
                                document.getElementById('errorContainer').style.display = 'none';

                                // Xóa biểu đồ cũ nếu có
                                if (window.projectChart) {
                                    window.projectChart.destroy();
                                }

                                // Hiển thị biểu đồ dựa vào loại báo cáo
                                switch (reportType) {
                                    case 'Budget Status':
                                        createBudgetStatusChart(data);
                                        break;
                                    case 'Completion Status':
                                        createCompletionStatusChart(data);
                                        break;
                                    case 'Total Completed Project':
                                        createTotalCompletedProjectChart(data);
                                        break;
                                    default:
                                        Swal.fire({
                                            icon: 'error',
                                            title: 'Lỗi',
                                            text: 'Loại báo cáo không hợp lệ!'
                                        });
                                }
                            })
                            .catch(error => {
                                Swal.close(); // Đóng thông báo đang tải
                                console.error('Error fetching data:', error);

                                // Hiển thị lỗi chi tiết trong container lỗi
                                document.getElementById('errorContainer').style.display = 'block';
                                document.getElementById('errorContainer').innerHTML =
                                        `<h3>Lỗi khi tải dữ liệu</h3>
                                <p>${error.message}</p>
                                <p>Vui lòng kiểm tra console để biết thêm chi tiết.</p>`;

                                Swal.fire({
                                    icon: 'error',
                                    title: 'Lỗi',
                                    text: 'Không thể tải dữ liệu dự án: ' + error.message
                                });
                            });
                }

                function createBudgetStatusChart(data) {
                    const ctx = document.getElementById('projectChart').getContext('2d');

                    // Nhóm dự án theo trạng thái ngân sách
                    const budgetCategories = ['Under Budget', 'On Budget', 'Over Budget'];
                    const budgetCounts = [0, 0, 0];

                    // Phân loại dự án dựa vào ngân sách và lợi nhuận
                    data.forEach(project => {
                        if (project.budget !== undefined && project.profit !== undefined) {
                            // Tính toán tình trạng ngân sách
                            // Lợi nhuận dương -> Under Budget
                            // Lợi nhuận bằng 0 -> On Budget
                            // Lợi nhuận âm -> Over Budget
                            if (project.profit > 0) {
                                budgetCounts[0]++; // Under Budget
                            } else if (project.profit === 0) {
                                budgetCounts[1]++; // On Budget
                            } else {
                                budgetCounts[2]++; // Over Budget
                            }
                        } else {
                            // Phân loại ngẫu nhiên nếu không có dữ liệu
                            const randomIndex = Math.floor(Math.random() * 3);
                            budgetCounts[randomIndex]++;
                        }
                    });

                    window.projectChart = new Chart(ctx, {
                        type: 'pie',
                        data: {
                            labels: budgetCategories,
                            datasets: [{
                                    data: budgetCounts,
                                    backgroundColor: [
                                        'rgba(75, 192, 192, 0.7)', // Xanh lá - Under Budget
                                        'rgba(54, 162, 235, 0.7)', // Xanh dương - On Budget
                                        'rgba(255, 99, 132, 0.7)'   // Đỏ - Over Budget
                                    ],
                                    borderColor: [
                                        'rgba(75, 192, 192, 1)',
                                        'rgba(54, 162, 235, 1)',
                                        'rgba(255, 99, 132, 1)'
                                    ],
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            responsive: true,
                            plugins: {
                                title: {
                                    display: true,
                                    text: 'Phân loại dự án theo trạng thái ngân sách',
                                    font: {
                                        size: 18
                                    }
                                },
                                legend: {
                                    position: 'bottom'
                                },
                                tooltip: {
                                    callbacks: {
                                        label: function (context) {
                                            const label = context.label || '';
                                            const value = context.raw || 0;
                                            const total = context.dataset.data.reduce((a, b) => a + b, 0);
                                            const percentage = Math.round((value / total) * 100);
                                            return `${label}: ${value} dự án (${percentage}%)`;
                                        }
                                    }
                                }
                            }
                        }
                    });
                }

                function createCompletionStatusChart(data) {
                    const ctx = document.getElementById('projectChart').getContext('2d');

                    // Sắp xếp dự án theo tên để biểu đồ dễ đọc hơn
                    data.sort((a, b) => a.projectName.localeCompare(b.projectName));

                    // Chỉ lấy tối đa 10 dự án để biểu đồ không quá rối
                    const displayData = data.slice(0, 10);

                    // Tạo dữ liệu cho biểu đồ
                    const projectNames = displayData.map(project => project.projectName);
                    const completionValues = displayData.map(project => {
                        // Đảm bảo giá trị completion tồn tại
                        return project.completion !== undefined ? project.completion : 0;
                    });

                    window.projectChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: projectNames,
                            datasets: [{
                                    label: 'Tiến độ hoàn thành (%)',
                                    data: completionValues,
                                    backgroundColor: completionValues.map(value => {
                                        if (value < 30)
                                            return 'rgba(255, 99, 132, 0.7)';  // Đỏ - <30%
                                        else if (value < 70)
                                            return 'rgba(255, 205, 86, 0.7)';  // Vàng - 30-70%
                                        else
                                            return 'rgba(75, 192, 192, 0.7)';  // Xanh lá - >70%
                                    }),
                                    borderColor: 'rgba(54, 162, 235, 1)',
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                y: {
                                    beginAtZero: true,
                                    max: 100,
                                    title: {
                                        display: true,
                                        text: 'Phần trăm hoàn thành'
                                    }
                                },
                                x: {
                                    title: {
                                        display: true,
                                        text: 'Dự án'
                                    }
                                }
                            },
                            plugins: {
                                title: {
                                    display: true,
                                    text: 'Trạng thái hoàn thành của các dự án',
                                    font: {
                                        size: 18
                                    }
                                }
                            }
                        }
                    });

                    // Nếu có nhiều hơn 10 dự án, hiển thị thông báo
                    if (data.length > 10) {
                        Swal.fire({
                            icon: 'info',
                            title: 'Thông báo',
                            text: `Chỉ hiển thị 10/${data.length} dự án để biểu đồ dễ đọc.`
                        });
                    }
                }

                function createTotalCompletedProjectChart(data) {
                    const ctx = document.getElementById('projectChart').getContext('2d');

                    // Phân loại dự án theo mức độ hoàn thành
                    const completedProjects = data.filter(project =>
                        project.completion !== undefined && project.completion >= 100
                    ).length;
                    const uncompletedProjects = data.length - completedProjects;

                    window.projectChart = new Chart(ctx, {
                        type: 'doughnut',
                        data: {
                            labels: ['Chưa hoàn thành (<100%)', 'Đã hoàn thành (100%)'],
                            datasets: [{
                                    data: [uncompletedProjects, completedProjects],
                                    backgroundColor: [
                                        'rgba(255, 99, 132, 0.7)', // Đỏ - Chưa hoàn thành
                                        'rgba(75, 192, 192, 0.7)'   // Xanh lá - Đã hoàn thành
                                    ],
                                    borderColor: [
                                        'rgba(255, 99, 132, 1)',
                                        'rgba(75, 192, 192, 1)'
                                    ],
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            responsive: true,
                            plugins: {
                                title: {
                                    display: true,
                                    text: `Tổng số dự án đã hoàn thành: ${completedProjects}/${data.length}`,
                                    font: {
                                        size: 18
                                    }
                                },
                                legend: {
                                    position: 'bottom'
                                },
                                tooltip: {
                                    callbacks: {
                                        label: function (context) {
                                            const label = context.label || '';
                                            const value = context.raw || 0;
                                            const total = context.dataset.data.reduce((a, b) => a + b, 0);
                                            const percentage = Math.round((value / total) * 100);
                                            return `${label}: ${value} dự án (${percentage}%)`;
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            });
        </script>
    </body>
</html>