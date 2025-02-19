package Controller;

import DAO.EmployeeDAO;
import Model.Employee;
import java.io.IOException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "InsertEmployee", urlPatterns = {"/insert-employee"})
public class InsertEmployee extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String error = "";
        String url = "";

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthdateStr = request.getParameter("birthdate");
        String gender = request.getParameter("gender");
        String tel = request.getParameter("telephone");
        String address = request.getParameter("address");
        int positionId = 0, departmentId = 0;

        // Kiểm tra các trường bắt buộc
        if (firstName == null || firstName.trim().isEmpty()) {
            error += "First name is required.<br>";
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            error += "Last name is required.<br>";
        }
        if (birthdateStr == null || birthdateStr.trim().isEmpty()) {
            error += "Birthdate is required.<br>";
        }

        LocalDate birthdate = null;
        try {
            birthdate = LocalDate.parse(birthdateStr);
        } catch (Exception e) {
            error += "Invalid birthdate format.<br>";
        }

        if (gender == null || gender.equals("None")) {
            error += "Please select your gender.<br>";
        } else {
            gender = gender.equals("Male") ? "Nam" : "Nữ";
        }

        if (tel == null || tel.trim().isEmpty()) {
            error += "Telephone is required.<br>";
        }
        if (address == null || address.trim().isEmpty()) {
            error += "Address is required.<br>";
        }

        try {
            positionId = Integer.parseInt(request.getParameter("position"));
        } catch (NumberFormatException e) {
            error += "Please select a valid position.<br>";
        }

        try {
            departmentId = Integer.parseInt(request.getParameter("department"));
        } catch (NumberFormatException e) {
            error += "Please select a valid department.<br>";
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("insertEmployee.jsp").forward(request, response);
        } else {
            EmployeeDAO eDao = new EmployeeDAO();
            boolean success = eDao.insert(new Employee(firstName, lastName, birthdate, gender, tel, address, positionId, departmentId));

            if (success) {
                response.sendRedirect("employee-list");  // Chuyển hướng đến danh sách nhân viên
            } else {
                request.setAttribute("error", "Failed to insert employee. Please try again.");
                request.getRequestDispatcher("insertEmployee.jsp").forward(request, response);
            }
        }
    }
}
