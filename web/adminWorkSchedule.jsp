<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Thêm thư viện Select2 -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>

        <link rel="stylesheet" href="./css/styleAdminWorkSchedule.css"/>
    </head>
    <body>
        <div class="container mt-4">
            <div class="title text-center mb-3">
                <h2>Work Schedule List</h2>
            </div>

            <!-- Các nút chức năng -->
            <div class="text-center mb-3">
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAdd">Add Work Schedule</button>
                <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#modalUpdate">Update Work Schedule</button>
                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#modalDelete">Delete Work Schedule</button>
            </div>

            <!-- Bảng dữ liệu -->
            <div class="table-responsive">
                <table class="table table-bordered text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Date</th>
                            <th>WeekDay</th>
                            <th>Morning Shift</th>
                            <th>Afternoon Shift</th>
                            <th>Night Shift</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>2025-02-18</td>
                            <td>Monday</td>
                            <td>John Doe</td>
                            <td>Jane Doe</td>
                            <td>Mike Smith</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Modal: Add Work Schedule -->
        <div class="modal fade" id="modalAdd" tabindex="-1" aria-labelledby="modalAddLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Add Work Schedule</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form action="addWorkSchedule" method="POST">
                            <div class="mb-3">
                                <label class="form-label">Date:</label>
                                <input type="date" class="form-control" name="scheduleDate" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Morning Shift:</label>
                                <select class="form-select select2" name="morningShift" multiple style="width:100%;">
                                    <c:forEach var="emp" items="${applicationScope.employees}">
                                        <option value="${emp.employeeId}">${emp.getLastName()} ${emp.getFirstName()} ${emp.getEmployeeCode()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Afternoon Shift:</label>
                                <select class="form-select select2" name="afternoonShift" multiple style="width:100%;">
                                    <c:forEach var="emp" items="${applicationScope.employees}">
                                        <option value="${emp.employeeId}">${emp.getLastName()} ${emp.getFirstName()} ${emp.getEmployeeCode()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Night Shift:</label>
                                <select class="form-select select2" name="nightShift" multiple style="width:100%;">
                                    <c:forEach var="emp" items="${applicationScope.employees}">
                                        <option value="${emp.employeeId}">${emp.getLastName()} ${emp.getFirstName()} ${emp.getEmployeeCode()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal: Update Work Schedule -->
        <div class="modal fade" id="modalUpdate" tabindex="-1" aria-labelledby="modalUpdateLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Update Work Schedule</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form action="updateWorkSchedule" method="POST">
                            <div class="mb-3">
                                <label class="form-label">ID:</label>
                                <input type="text" class="form-control" name="scheduleId" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Date:</label>
                                <input type="date" class="form-control" name="scheduleDate">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Morning Shift:</label>
                                <input type="text" class="form-control" name="morningShift">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Afternoon Shift:</label>
                                <input type="text" class="form-control" name="afternoonShift">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Night Shift:</label>
                                <input type="text" class="form-control" name="nightShift">
                            </div>
                            <button type="submit" class="btn btn-warning">Update</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal: Delete Work Schedule -->
        <div class="modal fade" id="modalDelete" tabindex="-1" aria-labelledby="modalDeleteLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Delete Work Schedule</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form action="deleteWorkSchedule" method="POST">
                            <div class="mb-3">
                                <label class="form-label">ID:</label>
                                <input type="text" class="form-control" name="scheduleId" required>
                            </div>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                $('.select2').select2({
                    theme: 'bootstrap-5', // Áp dụng giao diện Bootstrap 5
                    placeholder: "Chọn Nhân Viên",
                    allowClear: true
                });
            });
        </script>

    </body>
</html>

<script>
    $(document).ready(function () {
        $('.select2').select2({
            placeholder: "Chọn nhân viên...",
            allowClear: true,
            dropdownParent: $('#modalAdd') // Fix lỗi khi dùng trong modal
        });
    });
</script>

