<%-- 
    Document   : updateEmployee
    Created on : Feb 15, 2025, 9:02:17 PM
    Author     : Ud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" href="css/styleUpdateEmployee.css">
        <title>Insert Employee</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="form-header">
                <div class="titles">
                    <div class="title-update-employee">UPDATE EMPLOYEE</div>
                </div>
            </div>
            <!-- LOGIN FORM -->
            <form action="#" class="update-employee-form">
                <div class="input-box">
                    <input type="text" class="input-field" id="fullname" required>
                    <label for="fullname" class="label">Full name</label>
                </div>
                <div class="input-box">
                    <input type="date" class="input-field" id="birthdate" required>
                    <label for="birthdate" class="label">Birthdate</label>
                </div>
                <div class="input-box">
                    <label class="label" for="gender">Gender</label> 
                    <select class="input-field" id="gender" name="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
                <div class="input-box">
                    <input type="tel" class="input-field" id="telephone" required>
                    <label for="telephone" class="label">Telephone</label>
                </div>
                <div class="input-box">
                    <input type="text" class="input-field" id="address" required>
                    <label for="address" class="label">Address</label>
                </div>
                <div class="input-box">
                    <button class="btn-submit">Update</button>
                </div>
            </form>
        </div>
    </body>
</html>

