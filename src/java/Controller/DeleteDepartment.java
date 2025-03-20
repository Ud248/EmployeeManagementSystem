/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DepartmentDAO;
import Model.Department;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author anhnn
 */
public class DeleteDepartment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departmentCodeParam = request.getParameter("departmentCode");
        int currentPage = Integer.valueOf(request.getParameter("page"));

        String[] arrDepartmentCode = departmentCodeParam.split(",");

        DepartmentDAO dDAO = new DepartmentDAO();
        boolean deleteResult = true;
        String deleteMsg = "";
        HttpSession session = request.getSession();
        
        deleteResult = dDAO.deleteAllByCode(arrDepartmentCode);
        if (deleteResult) {
            int itemsPerPage = (int) request.getServletContext().getAttribute("itemsPerPage");
            int totalDepartment = (int) request.getServletContext().getAttribute("totalDepartment") - arrDepartmentCode.length;
            int totalPage = (int) Math.ceil((double) totalDepartment / itemsPerPage);

            if (currentPage > totalPage) {
                currentPage = totalPage;
            }

            deleteMsg = "Delete department " + String.join(", ", arrDepartmentCode) + " successfully!";

            List<Department> listDepartment = dDAO.selectAll();

            request.getServletContext().setAttribute("totalDepartment", totalDepartment);
            request.getServletContext().setAttribute("listDepartment", listDepartment);

        } else {
            deleteMsg = "Delete department " + String.join(", ", arrDepartmentCode) + " failed! Please try again!";
        }
        session.setAttribute("actionMsg", deleteMsg);
        response.sendRedirect("show-department?page=" + currentPage);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
