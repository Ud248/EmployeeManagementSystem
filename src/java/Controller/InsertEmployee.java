package Controller;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import Model.Employee;
import java.io.IOException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import jakarta.websocket.Session;

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
        int positionId = 0, departmentId = 0, basicSalary = 0;

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

        if (gender.equals("None")) {
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
        try {
            basicSalary = Integer.parseInt(request.getParameter("basicSalary"));
        } catch (NumberFormatException e) {
            error += "Please input a valid basic salary.<br>";
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("birthdateStr", birthdateStr);
            request.setAttribute("gender", gender.equals("Nam") ? "Male" : "Female");
            request.setAttribute("tel", tel);
            request.setAttribute("address", address);
            request.setAttribute("positionId", positionId);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("basicSalary", basicSalary);
            request.getRequestDispatcher("insertEmployee.jsp").forward(request, response);
        } else {
            EmployeeDAO eDao = new EmployeeDAO();
            boolean success = eDao.insert(new Employee(firstName, lastName, birthdate, tel, address, positionId, departmentId, basicSalary));
            if (success) {
                request.setAttribute("successMsg", "Add employee " + lastName + " " + firstName + " successfully!");

                //Update list employee
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
                session.setAttribute("employees", employees);
                session.setAttribute("totalEmployees", totalEmployees);
                request.getRequestDispatcher("insertEmployee.jsp").forward(request, response);
            } else {
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("birthdateStr", birthdateStr);
                request.setAttribute("gender", gender.equals("Nam") ? "Male" : "Female");
                request.setAttribute("tel", tel);
                request.setAttribute("address", address);
                request.setAttribute("positionId", positionId);
                request.setAttribute("departmentId", departmentId);
                request.setAttribute("basicSalary", basicSalary);
                
                request.setAttribute("error", "Failed to insert employee. Please try again.");
                
                request.getRequestDispatcher("insertEmployee.jsp").forward(request, response);
            }
        }
    }
}
