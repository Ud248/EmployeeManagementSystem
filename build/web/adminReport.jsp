<%-- 
    Document   : adminProjectReport
    Created on : Mar 12, 2025, 10:49:34 PM
    Author     : nongt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <form id="reportForm">
                    <label for="reportType">Report Type:</label>
                    <select id="reportType" name="reportType">
                        <option value="employee">Employee Report</option>
                        <option value="project">Project Report</option>
                    </select>
                    <button type="submit">Load Report</button>
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
            document.getElementById("reportForm").addEventListener("submit", function (event) {
                event.preventDefault();
                const reportType = document.getElementById("reportType").value;
                fetch("report?reportType=" + reportType)
                        .then(response => response.json())
                        .then(data => {
                            const tittleMainChart = document.getElementById("tittleMainChart");
                            const tittleSubChart = document.getElementById("tittleSubChart");
                            if (reportType === "employee") {
                                tittleMainChart.textContent = "Basic Salary Per Employee";
                                tittleSubChart.textContent = "Total Basic Salary Per Positon";
                                BasicSalaryPerEmployeeChart(data);
                                TotalBasicSalaryPerPositonChart(data);
                                populateStats(data, "employee");
                            } else if (reportType === "project") {
                                tittleMainChart.textContent = "Percent Completion of Project";
                                tittleSubChart.textContent = "Total Project completed";
                                CompletionStatusChart(data);
                                TotalCompletedProjectChart(data);
                                populateStats(data, "project");
                            }
                        })
                        .catch(error => console.error("Error fetching data:", error));
            });
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

            function CompletionStatusChart(data) {
                const ctx = document.getElementById("mainChart").getContext("2d");
                if (window.mainChart instanceof Chart) {
                    window.mainChart.destroy();
                }

                const backgroundColors = data.completionData.map((_, index) => {
                    return generateColor(index, data.completionData.length);
                });
                window.mainChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: data.completionLabels,
                        datasets: [{
                                label: "Completion (%)",
                                data: data.completionData,
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

            function TotalCompletedProjectChart(data) {
                const ctx = document.getElementById("pieChart").getContext("2d");
                if (window.pieChart instanceof Chart) {
                    window.pieChart.destroy();
                }

                const backgroundColors = data.totalProjectLabels.map((_, index) => {
                    return generateColor(index, data.totalProjectLabels.length);
                });
                window.pieChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: data.totalProjectLabels,
                        datasets: [{
                                label: "Completed Projects",
                                data: data.totalProjectData,
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

            function BasicSalaryPerEmployeeChart(data) {
                const ctx = document.getElementById("mainChart").getContext("2d");
                if (window.mainChart instanceof Chart) {
                    window.mainChart.destroy();
                }

                const backgroundColors = data.salaryData.map((_, index) => {
                    return generateColor(index, data.salaryData.length);
                });
                window.mainChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: data.salaryLabels,
                        datasets: [{
                                label: "Basic Salary",
                                data: data.salaryData,
                                backgroundColor: backgroundColors,
                                borderColor: backgroundColors,
                                borderWidth: 2,
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
            function TotalBasicSalaryPerPositonChart(data) {
                const ctx = document.getElementById("pieChart").getContext("2d");
                if (window.pieChart instanceof Chart) {
                    window.pieChart.destroy();
                }

                const backgroundColors = data.totalSalaryLabels.map((_, index) => {
                    return generateColor(index, data.totalSalaryLabels.length);
                });
                window.pieChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: data.totalSalaryLabels,
                        datasets: [{
                                label: "Basic Salary",
                                data: data.totalSalaryData,
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

            function populateStats(data, reportType) {
                const statsContainer = document.querySelector(".stats-container");
                statsContainer.innerHTML = '';
                let statsData = [];
                if (reportType === "project") {
                    statsData = [
                        {value: data.totalCompletedProject, label: "Total Completed Project"},
                        {value: data.totalBudget.toLocaleString(), label: "Total budget"},
                        {value: data.totalProfit.toLocaleString(), label: "Total profit"},
                        {value: data.topDepartment, label: "Top Department of number of completed Project"},
                    ];
                } else if (reportType === "employee") {
                    statsData = [
                        {value: data.totalDirector, label: "Total Director Position"},
                        {value: data.totalEmployee, label: "Total Employee Position"},
                        {value: data.totalManager, label: "Total Manager Position"},
                        {value: data.totalBasicSalary.toLocaleString(), label: "Total Basic Salary"}
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
        </script>
    </body>
</html>