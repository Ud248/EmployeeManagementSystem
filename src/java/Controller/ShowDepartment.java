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
public class ShowDepartment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemsPerPage = (int) request.getServletContext().getAttribute("itemsPerPage");
        int totalDepartment = (int) request.getServletContext().getAttribute("totalDepartment");
        
        String strCurrentPage = request.getParameter("page");
        int currentPage = Integer.parseInt(strCurrentPage);

        int totalPage = (int) Math.ceil(1.0*totalDepartment / itemsPerPage);

        DepartmentDAO depDAO = new DepartmentDAO();
        List<DepartmentDTO> departmentDTOs = depDAO.selectDepartmentsByPage(currentPage, itemsPerPage);

        request.setAttribute("departmentDTOs", departmentDTOs);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("adminDepartmentManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
