/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import DTO.DepartmentDTO;
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
        int currentPage = Integer.valueOf(request.getParameter("page"));
        String employeeCodeParam = request.getParameter("employeeCode");

        String[] arrEmployeeCode = employeeCodeParam.split(",");

        EmployeeDAO eDao = new EmployeeDAO();
        boolean deleteResult = true;
        HttpSession session = request.getSession();
        String deleteMsg = "";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        deleteResult = eDao.deleteAllByID(arrEmployeeCode);

        if (deleteResult) {
            int itemsPerPage = (int) request.getServletContext().getAttribute("itemsPerPage");
            int totalEmployee = (int) request.getServletContext().getAttribute("totalEmployee") - arrEmployeeCode.length;
            int totalPage = (int) Math.ceil((double) totalEmployee / itemsPerPage);

            if (currentPage > totalPage) {
                currentPage = totalPage;
            }

            deleteMsg = "Delete employee " + String.join(", ", arrEmployeeCode) + " successfully!";

            request.getServletContext().setAttribute("totalEmployee", totalEmployee);

        } else {
            deleteMsg = "Delete employee " + String.join(", ", arrEmployeeCode) + " failed! Please try again!";
        }
        session.setAttribute("actionMsg", deleteMsg);
        response.sendRedirect("show-employee?page=" + currentPage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
