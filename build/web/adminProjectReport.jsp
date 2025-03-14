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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    </head>
    <body>
        <div style="width:600px; height:500px;">
            <canvas id="myChart"></canvas>
        </div>

        <script>
            const ctx = document.getElementById('myChart');
            
            let labels = [];
            let data = [];
            
            new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                    datasets: [{
                            label: '# of Votes',
                            data: [12, 19, 3, 5, 2, 3],
                            borderWidth: 1
                        }]
                }
            });
        </script>

    </body>
</html>