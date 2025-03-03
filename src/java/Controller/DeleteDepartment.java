/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DepartmentDAO;
import DTO.DepartmentDTO;
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
            request.getServletContext().setAttribute("departments", departments);
            response.sendRedirect("admin.jsp?status=success");
        } else {
            response.sendRedirect("admin.jsp?status=failure");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
