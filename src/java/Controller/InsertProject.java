/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import Model.Project;
import Utils.ProjectUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author nongt
 */
@WebServlet(name = "InsertProject", urlPatterns = {"/insert-project"})

public class InsertProject extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProjectDAO pDAO = new ProjectDAO();

        String projectName = request.getParameter("projectName");
        String[] descriptionArray = request.getParameterValues("description");
        String description = "";
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String budgetStr = request.getParameter("budget");
        int departmentId = 0;

        String errorNameMsg = "";
        String errorDescriptionMsg = "";
        String errorStartDateMsg = "";
        String errorEndDateMsg = "";
        String errorBudgetMsg = "";
        String errorDepartmentNameMsg = "";

        if (projectName.trim().isEmpty()) {
            errorNameMsg = "Project Name must be not empty field";
        }
        if (ProjectUtil.isEmptyDescription(descriptionArray)) {
            errorDescriptionMsg = "Description must be not empty";
        }
        if (startDateStr == null || startDateStr.trim().isEmpty()) {
            errorStartDateMsg = "startDate is required";
        }

        LocalDate startDate = null;
        LocalDate endDate = null;
        try {
            startDate = LocalDate.parse(startDateStr);
            endDate = LocalDate.parse(endDateStr);
            if (endDate != null && endDate.isBefore(startDate)) {
                errorEndDateMsg = "End Date must be after Start Date.";
            }
        } catch (Exception e) {
            errorStartDateMsg = "Invalid startDate format";
            errorEndDateMsg = "Invalid endDate format";
        }
        try {
            departmentId = Integer.parseInt(request.getParameter("department"));
        } catch (NumberFormatException e) {
            errorDepartmentNameMsg = "Please select a valid department";
        }
        double budget = 0;
        try {
            if (budgetStr == null || budgetStr.isEmpty()) {
                errorBudgetMsg = "Budget must not be empty.";
            } else {
                budget = Double.parseDouble(budgetStr);
                if (budget < 0) {
                    errorBudgetMsg = "Budget cannot be negative.";
                }
            }
        } catch (NumberFormatException e) {
            errorBudgetMsg = "Budget must be a valid number.";
        }
        if (!errorNameMsg.isEmpty() || !errorStartDateMsg.isEmpty() || !errorBudgetMsg.isEmpty() || !errorDepartmentNameMsg.isEmpty()) {
            System.out.println("Validation errors found, returning to form");
            request.setAttribute("errorNameMsg", errorNameMsg);
            request.setAttribute("errorDescriptionMsg", errorDescriptionMsg);
            request.setAttribute("errorStartDateMsg", errorStartDateMsg);
            request.setAttribute("errorEndDateMsg", errorEndDateMsg);
            request.setAttribute("errorBudgetMsg", errorBudgetMsg);
            request.setAttribute("projectName", projectName);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("budget", budgetStr);
            request.setAttribute("descriptionArray", descriptionArray);
            request.setAttribute("departmentId", departmentId);
            request.getRequestDispatcher("adminProjectInsert.jsp").forward(request, response);
        } else {
            description = ProjectUtil.getDescription(descriptionArray);
            String insertMsg = "";
            HttpSession session = request.getSession();
            boolean insertResult = pDAO.insert(new Project(projectName, description, startDate, endDate, budget, departmentId));
            if (insertResult) {
                int totalProject = (int) request.getServletContext().getAttribute("totalProject") + 1;
                request.getServletContext().setAttribute("totalProject", totalProject);

                int totalPagesPro = (int) Math.ceil((double) totalProject / 10);

                session.setAttribute("totalPagesPro", totalPagesPro);
                session.setAttribute("currentPagePro", 1);

                insertMsg = "Add new department " + projectName + " successfully!";

                List<ProjectDTO> projects = new DAO.ProjectDAO().selectAllProjectDTO(1, 10);
                request.getServletContext().setAttribute("projects", projects);

            } else {
                insertMsg = "Add new project " + projectName + " failed. Please try again!";
            }
            session.setAttribute("actionMsg", insertMsg);
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("window.parent.location.reload();");
            out.println("window.close();");
            out.println("</script>");
            out.close();
        }
    }
}
