<%-- 
    Document   : adminProjectReport
    Created on : Mar 12, 2025, 10:49:34 PM
    Author     : nongt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Project Report</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <h2>Completed Projects Per Department</h2>

        <div style="width:600px; height:500px;">
            <canvas id="myChart"></canvas>
        </div>

        <script>
            const ctx = document.getElementById('myChart').getContext('2d');

            let labels = [];
            let data = [];
            let backgroundColors = [
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(153, 102, 255, 0.6)',
                'rgba(255, 159, 64, 0.6)',
                'rgba(199, 199, 199, 0.6)'
            ];

            <c:forEach var="entry" items="${projectPerDept}">
            labels.push("${entry.key}");
            data.push(${entry.value});
            </c:forEach>

            new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: labels,
                    datasets: [{
                            label: 'Completed Projects',
                            data: data,
                            backgroundColor: backgroundColors,
                            borderColor: 'rgba(255, 255, 255, 1)',
                            borderWidth: 1
                        }]
                }
            });
        </script>
    </body>
</html>
