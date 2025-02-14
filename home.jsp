<%-- 
    Document   : home
    Created on : Feb 7, 2025, 2:37:53 PM
    Author     : nvtha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of Students</h1>
        <table border="1">
            <thead>
            <th>No.</th>
            <th>ID</th>
            <th>Name</th>
            </thead>
            <tbody>
                <c:forEach var="st" items="${list}" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${st.getStudentID()}</td>
                        <td>${st.getStudentName()}</td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>
