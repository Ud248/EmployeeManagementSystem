<%-- 
    Document   : sidebar.jsp
    Created on : Mar 15, 2025, 10:45:34 PM
    Author     : anhnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="sidebar">
            <div class="avatar">
                <img src="./image/${sessionScope.employee.gender == 'Nam' ? 'MaleAvatar.png' : 'FemaleAvatar.png'}" alt="Avatar">
                <p class="position">${sessionScope.employee.positionName}</p>
                <h3 class="name">${sessionScope.employee.fullname}</h3>
            </div>


            <div class="main"> 
                <h4 class="title-side-bar">Main</h4>
                <ul class="menu">
                    <li>
                        <a href="show-employee?page=1">
                            <i class="ph ph-user-list"></i>
                            <span class="text">Employee Management</span>
                        </a>
                    </li>
                    <li>
                        <a href="show-department?page=1" >
                            <i class="ph ph-users-four"></i>    
                            <span class="text">Department Management</span>
                        </a>
                    </li>
                    <li>
                        <a href="show-project?page=1">
                            <i class="ph-bold ph-clipboard-text"></i>
                            <span class="text">Project Management</span>
                        </a>
                    </li>
                    <li>
                        <a href="" >
                            <i class="ph-bold ph-clipboard-text"></i>
                            <span class="text">Report Project</span>
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
    </body>
</html>
