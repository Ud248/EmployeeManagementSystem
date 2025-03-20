/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author anhnn
 */
public class ShowEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemsPerPage = (int) request.getServletContext().getAttribute("itemsPerPage");
        String strCurrentPage = request.getParameter("page");
        int currentPage = Integer.parseInt(strCurrentPage);
        int totalEmployee = 0;
        EmployeeDAO empDAO = new EmployeeDAO();
        List<EmployeeDTO> employeeDTOs = null;

        String search = request.getParameter("search");

        if (search != null && !search.isEmpty()) {
            totalEmployee = empDAO.getTotalEmployees(search);
            employeeDTOs = empDAO.selectEmployeesByPage(currentPage, itemsPerPage, search);
            request.setAttribute("search", search);
            request.setAttribute("highlightSearch", "true"); 
        } else {
            totalEmployee = empDAO.getTotalEmployees();
            employeeDTOs = empDAO.selectEmployeesByPage(currentPage, itemsPerPage);
        }
        int totalPage = (int) Math.ceil(1.0 * totalEmployee / itemsPerPage);
        request.getServletContext().setAttribute("totalEmployee", totalEmployee);
        request.setAttribute("employeeDTOs", employeeDTOs);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("adminEmployeeManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
