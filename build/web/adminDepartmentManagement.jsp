<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdminDepartmentManagement.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="content">
            <div style="padding: 10px 20px 0px 20px">
                <div class="toolbar">
                    <input type="text" 
                           class="search-box" 
                           id="searchName" 
                           placeholder="Search With FirstName">

                    <!--                    <button class="new-employee-btn" onclick="openPopup('insertEmployeePopup')">
                                            <i class="fas fa-plus"></i> New Employee
                                        </button>-->
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Department Name</th>
                            <th>Open Time</th>
                            <th>Manager</th>
                            <th>Telephone</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="d" items="${sessionScope.departments}">
                            <tr>
                                <td style="text-align: center;">${d.getDepartmentId()}</td>
                                <td>${d.getDepartmentName()}</td>
                                <td style="text-align: center">${d.getOpenTime()}</td>
                                <td>${d.getManagerName()}</td>
                                <td style="text-align: center">${d.getTelephone()}</td>
                                <td class="action_button">
                                    <form id="formHidden_${d.getDepartmentId()}" style="display: none;" >
                                        <input type="hidden" name="departmentId" value="${d.getDepartmentId()}"/>

                                    </form>
                                    <button class="btn btn-view" onclick="submitFormByAction('viewdepartment', ${d.getDepartmentId()}, 'get')">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                    <button class="btn btn-edit" onclick="submitFormByAction('updatedepartment', ${d.getDepartmentId()}, 'get')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-delete" onclick="submitFormByAction('deletedepartment', ${d.getDepartmentId()}, 'get')">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            function submitFormByAction(action, departmentId, method) {
                let form = document.getElementById("formHidden_" + departmentId);
                if (action === "deletedepartment") {
                    if (!confirm("Are you sure you want to delete department " + departmentId + "?")) {
                        return;
                    }
                }
                form.action = action;
                form.method = method;
                form.submit();
            }
        </script>
    </body>
</html>
