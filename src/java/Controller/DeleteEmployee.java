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
        String employeeCodeParam = request.getParameter("employeeCode");
        String[] employeeCode = employeeCodeParam.split(",");
        EmployeeDAO eDao = new EmployeeDAO();
        boolean deleteResult = true;
        for (String eCode : employeeCode) {
            if (!eDao.delete(new Employee(eCode))) {
                deleteResult = false;
            }
        }
        HttpSession session = request.getSession();
        String deleteMsg = "";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if (deleteResult) {
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
            request.getServletContext().setAttribute("employees", employees);
            session.setAttribute("totalEmployees", totalEmployees);
            deleteMsg = "Delete employee " + String.join(", ", employeeCode) + " successfully!";

        } else {
            deleteMsg = "Delete employee " + String.join(", ", employeeCode) + " failed! Please try again!";
        }
        session.setAttribute("actionMsg", deleteMsg);
        response.sendRedirect("admin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
