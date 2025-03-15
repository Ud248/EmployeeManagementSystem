/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import Model.Project;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author nongt
 */
@WebServlet(name = "ViewProject", urlPatterns = {"/view-project"})
public class ViewProject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectCode = request.getParameter("projectCode");

        ProjectDAO pDao = new ProjectDAO();
        ProjectDTO project = pDao.selectByProjectCode(new Project(projectCode));

        String completion = project.getCompletion() + "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = project.getStartDate().format(formatter);
        String formattedEndDate = "Chưa hoàn thiện";
        if (Integer.parseInt(completion) == 100) {
            formattedEndDate = LocalDate.now().format(formatter) + "";
        }
        String formattedDeadLine = project.getDeadLine().format(formatter);
        String description = project.getDescription();
        String[] descriptionArray = description.split("\\.");
        for (int i = 0; i < descriptionArray.length; i++) {
            descriptionArray[i] = descriptionArray[i].trim() + ".";
        }
        DecimalFormat df = new DecimalFormat("#,###.##");
        String budget = df.format(project.getBudget());
        String profit = df.format(project.getProfit());

        request.setAttribute("projectCode", project.getProjectCode());
        request.setAttribute("projectName", project.getProjectName());
        request.setAttribute("departmentName", project.getDepartmentName());
        request.setAttribute("description", description);
        request.setAttribute("startDate", formattedStartDate);
        request.setAttribute("endDate", formattedEndDate);
        request.setAttribute("deadLine", formattedDeadLine);
        request.setAttribute("completion", completion);
        request.setAttribute("budget", budget);
        request.setAttribute("profit", profit);
        request.setAttribute("descriptionArray", descriptionArray);

        request.getRequestDispatcher("adminProjectView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
