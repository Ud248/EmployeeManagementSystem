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
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        String deleteMsg = "";
        HttpSession session = request.getSession();
        ArrayList<Integer> ids = new ArrayList<>();
        for (String id : departmentId) {
            ids.add(Integer.parseInt(id));
        }
        deleteResult = dDAO.deleteAllByID(ids);
        if (deleteResult) {
            int totalDepartment = (int) request.getServletContext().getAttribute("totalDepartment") - departmentId.length;
            int totalPagesDep = (int) Math.ceil((double) totalDepartment / 10);

            session.setAttribute("currentPageDep", 1);
            session.setAttribute("totalPagesDep", totalPagesDep);

            deleteMsg = "Delete department " + String.join(", ", departmentId) + " successfully!";

            List<DepartmentDTO> departments = dDAO.selectDepartmentsByPage(1, 10);
            List<Department> listDepartment = dDAO.selectAll();
            List<EmployeeDTO> employees = new DAO.EmployeeDAO().selectEmployeesByPage(1, 10);
            request.getServletContext().setAttribute("totalDepartment", totalDepartment);
            request.getServletContext().setAttribute("employees", employees);
            request.getServletContext().setAttribute("departments", departments);
            request.getServletContext().setAttribute("listDepartment", listDepartment);

        } else {
            deleteMsg = "Delete department " + String.join(", ", departmentId) + " failed! Please try again!";
        }
        session.setAttribute("actionMsg", deleteMsg);
        response.sendRedirect("admin.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
