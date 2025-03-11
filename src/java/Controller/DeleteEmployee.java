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
        String employeeCodeParam = request.getParameter("employeeCode");
        String[] employeeCode = employeeCodeParam.split(",");
        EmployeeDAO eDao = new EmployeeDAO();
        boolean deleteResult = true;
        HttpSession session = request.getSession();
        String deleteMsg = "";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        deleteResult = eDao.deleteAllByID(employeeCode);

        if (deleteResult) {
            int currentPageEmployee = (int) session.getAttribute("currentPageEmployee");
            int positionIdFilter = (int) session.getAttribute("positionIdFilter");
            int departmentIdFilter = (int) session.getAttribute("departmentIdFilter");
            int totalEmployee = eDao.getTotalEmployeesForFilter(departmentIdFilter, positionIdFilter);
            int totalPagesEmployee = (int) Math.ceil((double) totalEmployee / 10);
            if (currentPageEmployee > totalPagesEmployee) {
                --currentPageEmployee;
                session.setAttribute("currentPageEmployee", currentPageEmployee);
            }
            List<EmployeeDTO> employees = eDao.selectEmployeesByPageForFilter(currentPageEmployee, 10, departmentIdFilter, positionIdFilter);
            List<DepartmentDTO> departments = new DAO.DepartmentDAO().selectDepartmentsByPage(1, 10);

            session.setAttribute("totalEmployee", totalEmployee);
            session.setAttribute("totalPagesEmployee", totalPagesEmployee);
            session.setAttribute("employees", employees);

            request.getServletContext().setAttribute("departments", departments);

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
