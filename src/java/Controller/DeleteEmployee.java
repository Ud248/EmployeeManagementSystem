/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import Model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Ud
 */
@WebServlet(name = "DeleteEmployee", urlPatterns = {"/delete-employee"})
public class DeleteEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeCode = request.getParameter("employeeCode");
        EmployeeDAO eDao = new EmployeeDAO();

        boolean success = eDao.delete(new Employee(employeeCode));
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if (success) {
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
            request.getServletContext().setAttribute("employees", employees);
            session.setAttribute("totalEmployees", totalEmployees);
            response.getWriter().write("Employee " + employeeCode + " deleted successfully!");
        } else {
            response.getWriter().write("Failed to delete employee " + employeeCode + "!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
