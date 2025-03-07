/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DTO.ProjectDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author nongt
 */
public class ViewProject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectId = request.getParameter("projectId");
        int index = Integer.parseInt(projectId) - 1;
        ArrayList<ProjectDTO> projects = (ArrayList<ProjectDTO>) request.getServletContext().getAttribute("projects");
        ProjectDTO project = projects.get(index);
        DecimalFormat df = new DecimalFormat("#,###.##");
        request.setAttribute("projectId", projectId);
        request.setAttribute("projectName", project.getProjectName());
        request.setAttribute("description", project.getDescription());
        request.setAttribute("completion", project.getCompletion() + "%");
        request.setAttribute("startDate", project.getStartDate());
        request.setAttribute("endDate", project.getEndDate());
        request.setAttribute("budget", df.format(project.getBudget()) + " VND");
        request.setAttribute("profit", df.format(project.getProfit()) + " VND");
        request.setAttribute("departmentId", project.getDepartmentId());
        request.getRequestDispatcher("adminProjectView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
