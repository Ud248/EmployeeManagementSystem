/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhnn
 */
public class ShowProject extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemsPerPage = (int) request.getServletContext().getAttribute("itemsPerPage");
        int totalProject = (int) request.getServletContext().getAttribute("totalProject");

        String strCurrentPage = request.getParameter("page");
        int currentPage = Integer.parseInt(strCurrentPage);

        int totalPage = (int) Math.ceil(1.0 * totalProject / itemsPerPage);
        ProjectDAO prjDAO = new ProjectDAO();
        List<ProjectDTO> projectDTOs = (ArrayList<ProjectDTO>) prjDAO.selectAllProjectDTO(currentPage, itemsPerPage);

        request.setAttribute("projectDTOs", projectDTOs);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("adminProjectManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
