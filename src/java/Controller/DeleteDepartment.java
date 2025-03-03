/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DepartmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author anhnn
 */
public class DeleteDepartment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String departmentIdParam = request.getParameter("departmentId");
        String[] departmentId = departmentIdParam.split(",");
        for (String id : departmentId) {
            
        }
//        boolean deleteResult = new DepartmentDAO().delete(departmentId);
//        if (deleteResult) {
//            response.sendRedirect("admin.jsp?status=success");
//        } else {
//            response.sendRedirect("admin.jsp?status=failure");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
