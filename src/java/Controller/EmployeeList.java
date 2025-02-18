/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import Model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Ud
 */
@WebServlet(name = "EmployeeList", urlPatterns = {"/employee-list"})
public class EmployeeList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDAO eDao = new EmployeeDAO();
        int page = 1;
        int itemsPerPage = 5;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // Use default value
        }

        try {
            itemsPerPage = Integer.parseInt(request.getParameter("items"));
        } catch (NumberFormatException e) {
            // Use default value
        }

        List<Employee> employees = eDao.selectAll(page, itemsPerPage);
        int totalEmployees = eDao.getTotalEmployees();
        int totalPages = (int) Math.ceil((double) totalEmployees / itemsPerPage);

        request.setAttribute("employees", employees);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemsPerPage", itemsPerPage);

        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
