<%-- 
    Document   : adminWorkSchedule
    Created on : Feb 18, 2025, 5:32:30 PM
    Author     : anhnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleAdminWorkSchedule.css"/>
    </head>
    <body>
        <div class="contentWS">
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
                                    <button type="submit" class="btn-attend">Cháº¥m cÃ´ng</button>
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
    </body>
</html>
