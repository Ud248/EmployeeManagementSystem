/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import Model.Department;
import Model.Employee;
import Model.Position;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ud
 */
@WebServlet(name = "UpdateEmployee", urlPatterns = {"/update-employee"})
public class UpdateEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeCode = request.getParameter("employeeCode");

        EmployeeDAO eDao = new EmployeeDAO();
        EmployeeDTO e = eDao.selectByEmployeeCode(new Employee(employeeCode));

        request.setAttribute("employeeCode", e.getEmployeeCode());
        request.setAttribute("fullname", e.getFullname());
        request.setAttribute("birthdate", e.getBirthDate());
        request.setAttribute("gender", e.getGender());
        request.setAttribute("tel", e.getTel());
        request.setAttribute("address", e.getAddress());
        request.setAttribute("positionId", e.getPositionId());
        request.setAttribute("departmentId", e.getDepartmentId());
        request.setAttribute("basicSalary", e.getBasicSalary());
        request.setAttribute("username", e.getUsername());
        request.setAttribute("password", e.getPassword());

        // Nhận successMsg từ request nếu có
        String successMsg = request.getParameter("successMsg");
        if (successMsg != null) {
            request.setAttribute("successMsg", successMsg);
        }

        request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = "";
        String url = "";

        String fullname = request.getParameter("fullname");
        String birthdateStr = request.getParameter("birthdate");
        String gender = request.getParameter("gender");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String employeeCode = request.getParameter("employeeCode");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int positionId = 0, departmentId = 0, basicSalary = 0;

        EmployeeDAO eDao = new EmployeeDAO();

        // Kiểm tra các trường bắt buộc
        if (fullname == null || fullname.trim().isEmpty()) {
            error += "Fullname name is required.<br>";
        }
        String[] fullnameArray = fullname.split("\\s+");
        String lastname = fullnameArray[0];
        String firstname = "";
        for (int i = 1; i < fullnameArray.length; i++) {
            firstname += fullnameArray[i] + " ";
        }
        firstname = firstname.trim();

        if (birthdateStr == null || birthdateStr.trim().isEmpty()) {
            error += "Birthdate is required.<br>";
        }

        LocalDate birthdate = null;
        try {
            birthdate = LocalDate.parse(birthdateStr);
        } catch (Exception e) {
            error += "Invalid birthdate format.<br>";
        }

        if (tel == null || tel.trim().isEmpty()) {
            error += "Telephone is required.<br>";
        } else if (!tel.matches("^0\\d{9}$")) {
            error += "Telephone must have 10 digits and start with 0.<br>";
        } else if (eDao.isExistPhoneNumberForUpdate(tel, employeeCode)) {
            error += "This phone number is already registered.<br>";
        }

        if (address == null || address.trim().isEmpty()) {
            error += "Address is required.<br>";
        }

        try {
            positionId = Integer.parseInt(request.getParameter("positionId"));
        } catch (NumberFormatException e) {
            error += "Please select a valid position.<br>";
        }

        try {
            departmentId = Integer.parseInt(request.getParameter("departmentId"));
        } catch (NumberFormatException e) {
            error += "Please select a valid department.<br>";
        }
        try {
            basicSalary = Integer.parseInt(request.getParameter("basicSalary"));
        } catch (NumberFormatException e) {
            error += "Please input a valid basic salary.<br>";
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("fullname", fullname);
            request.setAttribute("birthdate", birthdateStr);
            request.setAttribute("gender", gender);
            request.setAttribute("tel", tel);
            request.setAttribute("address", address);
            request.setAttribute("positionId", positionId);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("basicSalary", basicSalary);
            request.setAttribute("employeeCode", employeeCode);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
        } else {
            boolean success = eDao.update(new Employee(employeeCode, firstname, lastname, birthdate, gender, tel, address, positionId, departmentId, basicSalary));
            if (success) {
                int currentPage = 1;
                int itemsPerPage = 10;

                try {
                    String pageParam = request.getParameter("page");
                    if (pageParam != null && !pageParam.isEmpty()) {
                        currentPage = Integer.parseInt(pageParam);
                    }
                } catch (NumberFormatException e) {
                    // Use default value
                }

                // Calculate pagination values
                int totalEmployees = eDao.getTotalEmployees();
                int totalPages = (int) Math.ceil((double) totalEmployees / itemsPerPage);

                // Ensure currentPage is within valid range
                if (currentPage < 1) {
                    currentPage = 1;
                }
                if (currentPage > totalPages) {
                    currentPage = totalPages;
                }
                List<EmployeeDTO> employees = eDao.selectEmployeesByPage(currentPage, itemsPerPage);
                HttpSession session = request.getSession();
                request.getServletContext().setAttribute("employees", employees);
                session.setAttribute("totalEmployees", totalEmployees);

                response.sendRedirect("update-employee?employeeCode=" + employeeCode + "&successMsg=Update successful!");
            } else {
                request.setAttribute("error", "Failed to update employee. Please try again.");
                request.setAttribute("fullname", fullname);
                request.setAttribute("birthdate", birthdateStr);
                request.setAttribute("gender", gender);
                request.setAttribute("tel", tel);
                request.setAttribute("address", address);
                request.setAttribute("positionId", positionId);
                request.setAttribute("departmentId", departmentId);
                request.setAttribute("basicSalary", basicSalary);
                request.setAttribute("employeeCode", employeeCode);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
            }
        }
    }
}
