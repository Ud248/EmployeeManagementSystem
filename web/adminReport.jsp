<%-- 
    Document   : adminProjectReport
    Created on : Mar 12, 2025, 10:49:34 PM
    Author     : nongt
--%>
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
        <meta charset="UTF-8">
        <title>Report Dashboard</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="./css/styleReport.css">
        <script src="https://unpkg.com/@phosphor-icons/web"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>

        <jsp:include page="./layout/sidebar.jsp" />

        <div class="container">
            <div class="header">
                <h2>Report Dashboard</h2>
            </div>

            <div class="controls">
                <form id="reportForm" action="show-report" method="get">
                    <label for="reportType">Report Type:</label>
                    <select name="report" onchange="this.form.submit()">
                        <option value="employee" ${requestScope.report == 'employee' ? 'selected' : ''}>Employee Report</option>
                        <option value="project" ${requestScope.report == 'project' ? 'selected' : ''}>Project Report</option>
                    </select>
                </form>
            </div>
            <div class="stats-container"></div>
            <br>
            <div class="main-content">
                <div class="left-panel">
                    <div class="chart-box">
                        <div class="chart-title" id ="tittleMainChart"></div>
                        <div class="chart-container">
                            <canvas id="mainChart"></canvas>
                        </div>
                    </div>                    
                </div>
                <div class="right-panel">
                    <div class="chart-box radar">
                        <div class="chart-title" id="tittleSubChart"></div>
                        <div class="chart-container">
                            <canvas id="pieChart"></canvas>
                        </div>
                    </div>                    
                </div>
            </div>
        </div>

        <script>
            window.onload = function () {
                const report = "${requestScope.report != null ?  requestScope.report : "employee"}";

                if (report === "employee") {
                    document.getElementById("tittleMainChart").textContent = "Basic Salary Per Employee";
                    document.getElementById("tittleSubChart").textContent = "Total Basic Salary Per Position";

                    const salaryLabels = JSON.parse('${requestScope.salaryLabels}');
                    const salaryData = JSON.parse('${requestScope.salaryData}');
                    const totalSalaryLabels = JSON.parse('${requestScope.totalSalaryLabels}');
                    const totalSalaryData = JSON.parse('${requestScope.totalSalaryData}');
                    barChart(salaryLabels, salaryData, "Basic Salary");
                    pieChart(totalSalaryLabels, totalSalaryData, "Total Basic Salary");

                    const totalDirector = '${requestScope.totalDirector}';
                    const totalManager = '${requestScope.totalManager}';
                    const totalEmployee = '${requestScope.totalEmployee}';
                    const totalBasicSalary = '${requestScope.totalBasicSalary}';
                    populateStats(totalDirector, totalManager, totalEmployee, totalBasicSalary, report);

                } else {
                    document.getElementById("tittleMainChart").textContent = "Percent Completion of Project";
                    document.getElementById("tittleSubChart").textContent = "Total Project Completed";

                    const completionLabels = JSON.parse('${requestScope.completionLabels}');
                    const completionData = JSON.parse('${requestScope.completionData}');
                    const totalProjectLabels = JSON.parse('${requestScope.totalProjectLabels}');
                    const totalProjectData = JSON.parse('${requestScope.totalProjectData}');
                    barChart(completionLabels, completionData, "Completion (%)");
                    pieChart(totalProjectLabels, totalProjectData, "Completed Projects");

                    const totalCompletedProject = '${requestScope.totalCompletedProject}';
                    const totalBudget = '${requestScope.totalBudget}';
                    const totalProfit = '${requestScope.totalProfit}';
                    const topDepartment = '${requestScope.topDepartment}';
                    populateStats(totalCompletedProject, totalBudget, totalProfit, topDepartment, report);
                }
            };

            function generateColor(index, total) {
                const baseColors = [
                    "#36A2EB", "#FF6384", "#FFCE56", "#4BC0C0", "#9966FF",
                    "#FF9F40", "#C9CBCF", "#7FC97F", "#BEAED4", "#FDC086"
                ];
                if (index < baseColors.length) {
                    return baseColors[index];
                } else {
                    const hue = (index * 137.5) % 360;
                    const saturation = 65 + (index % 3) * 10;
                    const lightness = 55 + (index % 5) * 3;
                    return `hsl(${hue}, ${saturation}%, ${lightness}%)`;
                }
            }

            function barChart(labels, data, labelMsg) {
                const ctx = document.getElementById("mainChart").getContext("2d");
                if (window.mainChart instanceof Chart) {
                    window.mainChart.destroy();
                }

                const backgroundColors = data.map((_, index) => {
                    return generateColor(index, data.length);
                });
                window.mainChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: labelMsg,
                                data: data,
                                backgroundColor: backgroundColors,
                                borderColor: backgroundColors,
                                borderWidth: 1,
                                hoverOffset: 10
                            }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                display: true,
                                position: "top",
                                labels: {font: {size: 11}, color: "#333"}
                            },
                            tooltip: {
                                enabled: true,
                                backgroundColor: "#000",
                                titleColor: "#fff",
                                bodyColor: "#fff",
                                cornerRadius: 8
                            }
                        },
                        animation: {duration: 1000, easing: "easeOutQuad"},
                        scales: {
                            x: {
                                ticks: {color: "#333", font: {size: 11}},
                                grid: {display: false}
                            },
                            y: {
                                beginAtZero: true,
                                ticks: {color: "#333", font: {size: 11}},
                                grid: {color: "rgba(200, 200, 200, 0.2)"}
                            }
                        }
                    }
                });
            }

            function pieChart(labels, data, labelMsg) {
                const ctx = document.getElementById("pieChart").getContext("2d");
                if (window.pieChart instanceof Chart) {
                    window.pieChart.destroy();
                }

                const backgroundColors = labels.map((_, index) => {
                    return generateColor(index, labels.length);
                });
                window.pieChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: labelMsg,
                                data: data,
                                backgroundColor: backgroundColors,
                                borderColor: backgroundColors,
                                borderWidth: 1,
                                hoverOffset: 10
                            }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                display: true,
                                position: "top",
                                labels: {font: {size: 11}, color: "#333"}
                            },
                            tooltip: {
                                enabled: true,
                                backgroundColor: "#000",
                                titleColor: "#fff",
                                bodyColor: "#fff",
                                cornerRadius: 8
                            }
                        },
                        animation: {duration: 1000, easing: "easeOutQuad"}
                    }
                });
            }

            function populateStats(stat1, stat2, stat3, stat4, report) {
                const statsContainer = document.querySelector(".stats-container");
                statsContainer.innerHTML = '';
                let statsData = [];
                if (report === "project") {
                    statsData = [
                        {value: stat1, label: "Total Completed Project"},
                        {value: Number(stat2).toLocaleString(), label: "Total Budget"},
                        {value: Number(stat3).toLocaleString(), label: "Total Profit"},
                        {value: stat4, label: "Top Department of number of completed Project"},
                    ];
                } else if (report === "employee") {
                    statsData = [
                        {value: stat1, label: "Total Director Position"},
                        {value: stat2, label: "Total Manager Position"},
                        {value: stat3, label: "Total Employee Position"},
                        {value: stat4, label: "Total Basic Salary"}
                    ];
                }

                statsData.forEach(stat => {
                    const statBox = document.createElement('div');
                    statBox.className = "stat-box";
                    const statValue = document.createElement('div');
                    statValue.className = "stat-value";
                    statValue.textContent = stat.value;
                    const statLabel = document.createElement('div');
                    statLabel.className = "stat-label";
                    statLabel.textContent = stat.label;
                    statBox.appendChild(statValue);
                    statBox.appendChild(statLabel);
                    statsContainer.appendChild(statBox);
                });
            }
            document.getElementById("reportForm").dispatchEvent(new Event('submit'));
        </script>
    </body>
</html>