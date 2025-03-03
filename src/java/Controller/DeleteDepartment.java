/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DepartmentDAO;
import DTO.DepartmentDTO;
import DTO.EmployeeDTO;
import Model.Department;
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
public class DeleteDepartment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departmentIdParam = request.getParameter("departmentId");
        String[] departmentId = departmentIdParam.split(",");
        DepartmentDAO dDAO = new DepartmentDAO();
        boolean deleteResult = true;
        for (String id : departmentId) {
            if (!dDAO.delete(Integer.parseInt(id))) {
                deleteResult = false;
            }
        }
        if (deleteResult) {
            List<DepartmentDTO> departments = dDAO.selectDepartmentsByPage();
            List<Department> listDepartment = dDAO.selectAll();
            List<EmployeeDTO> employees = new DAO.EmployeeDAO().selectEmployeesByPage(1, 10);
            request.getServletContext().setAttribute("employees", employees);
            request.getServletContext().setAttribute("departments", departments);
            request.getServletContext().setAttribute("listDepartment", listDepartment);
            response.sendRedirect("admin.jsp?successMsg=Department deleted successfully!");
        } else {
            response.sendRedirect("admin.jsp?errorMsg=Failed to delete department. Please try again!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
