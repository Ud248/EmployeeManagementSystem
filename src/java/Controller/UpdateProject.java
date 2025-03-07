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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nongt
 */
public class UpdateProject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectId = request.getParameter("projectId");
        int index = Integer.parseInt(projectId) - 1;

        HttpSession session = request.getSession();
        ProjectDTO proj = ((List<ProjectDTO>) request.getServletContext().getAttribute("projects")).get(index);

        DecimalFormat df = new DecimalFormat("#,###.##");

        request.setAttribute("projectId", projectId);
        request.setAttribute("projectName", proj.getProjectName());
        request.setAttribute("description", proj.getDescription());
        request.setAttribute("completion", proj.getCompletion());
        request.setAttribute("startDate", proj.getStartDate());
        request.setAttribute("endDate", proj.getEndDate());
        request.setAttribute("budget", df.format(proj.getBudget()));
        request.setAttribute("profit", df.format(proj.getProfit()));
        request.setAttribute("departmentId", proj.getDepartmentId());

        request.getRequestDispatcher("adminProjectUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        String projectName = request.getParameter("projectName");
        String description = request.getParameter("description");
        int completion = Integer.parseInt(request.getParameter("completion"));
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = request.getParameter("endDate") != null && !request.getParameter("endDate").isEmpty()
                ? LocalDate.parse(request.getParameter("endDate"))
                : null;
        double budget = Double.parseDouble(request.getParameter("budget"));
        double profit = Double.parseDouble(request.getParameter("profit"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        HttpSession session = request.getSession();

        String errorNameMsg = "";
        String errorCompletionMsg = "";
        String errorBudgetMsg = "";
        String errorDateMsg = "";

        if (projectName.trim().isEmpty()) {
            errorNameMsg = "Project Name must not be empty.";
        }
        if (completion < 0 || completion > 100) {
            errorCompletionMsg = "Completion must be between 0 and 100.";
        }
        if (budget < 0) {
            errorBudgetMsg = "Budget must be a positive number.";
        }
        if (endDate != null && endDate.isBefore(startDate)) {
            errorDateMsg = "End Date must be after Start Date.";
        }

        if (!errorNameMsg.isEmpty() || !errorCompletionMsg.isEmpty() || !errorBudgetMsg.isEmpty() || !errorDateMsg.isEmpty()) {
            request.setAttribute("errorNameMsg", errorNameMsg);
            request.setAttribute("errorCompletionMsg", errorCompletionMsg);
            request.setAttribute("errorBudgetMsg", errorBudgetMsg);
            request.setAttribute("errorDateMsg", errorDateMsg);

            request.setAttribute("projectId", projectId);
            request.setAttribute("projectName", projectName);
            request.setAttribute("description", description);
            request.setAttribute("completion", completion);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("budget", budget);
            request.setAttribute("profit", profit);
            request.setAttribute("departmentId", departmentId);

            request.getRequestDispatcher("adminProjectUpdate.jsp").forward(request, response);
        } else {
            boolean updateResult = new ProjectDAO().update(new Project(
                    projectId, projectName, description, completion, startDate, endDate, budget, profit, departmentId));

            if (updateResult) {
                session.setAttribute("currentPageProj", 1);
                List<ProjectDTO> projects = new ProjectDAO().selectProjectsByPage(1, 10);
                request.getServletContext().setAttribute("projects", projects);
                response.sendRedirect("viewproject?projectId=" + projectId + "&successMsg=Update successful!");
            } else {
                response.sendRedirect("viewproject?projectId=" + projectId + "&errorMsg=Update failed. Please try again!");
            }
        }
    }
}
