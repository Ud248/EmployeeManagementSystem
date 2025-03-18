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
        String deadLineStr = request.getParameter("deadLine");
        String budgetStr = request.getParameter("budget");
        String profitStr = request.getParameter("profit");
        int departmentId = 0;

        String errorNameMsg = "";
        String errorDescriptionMsg = "";
        String errorStartDateMsg = "";
        String errorDeadLineMsg = "";
        String errorBudgetMsg = "";
        String errorDepartmentNameMsg = "";
        String errorProfitMsg = "";

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
        LocalDate deadLine = null;
        try {
            startDate = LocalDate.parse(startDateStr);
            deadLine = LocalDate.parse(deadLineStr);
            if (deadLine != null && deadLine.isBefore(startDate)) {
                errorDeadLineMsg = "Dead Line must be after Start Date.";
            }
        } catch (Exception e) {
            errorStartDateMsg = "Invalid startDate format";
            errorDeadLineMsg = "Invalid deadLine format";
        }
        try {
            departmentId = Integer.parseInt(request.getParameter("department"));
        } catch (NumberFormatException e) {
            errorDepartmentNameMsg = "Please select a valid department";
        }
        double budget = 0, profit = 0;
        try {
            if (budgetStr == null || budgetStr.isEmpty()) {
                errorBudgetMsg = "Budget must not be empty.";
            } else {
                budget = Double.parseDouble(budgetStr);
                if (budget < 0) {
                    errorBudgetMsg = "Budget cannot be negative.";
                }
            }
            if (profitStr == null || profitStr.isEmpty()) {
                errorProfitMsg = "Profit must not be empty.";
            } else {
                profit = Double.parseDouble(profitStr);
                if (profit < 0) {
                    errorProfitMsg = "Profit cannot be negative.";
                }
            }
        } catch (NumberFormatException e) {
            errorBudgetMsg = "Budget must be a valid number.";
            errorProfitMsg = "Profit must be a valid number.";
        }
        if (!errorNameMsg.isEmpty() || !errorStartDateMsg.isEmpty() || !errorBudgetMsg.isEmpty() || !errorDepartmentNameMsg.isEmpty() || !errorProfitMsg.isEmpty()) {
            System.out.println("Validation errors found, returning to form");
            request.setAttribute("errorNameMsg", errorNameMsg);
            request.setAttribute("errorDescriptionMsg", errorDescriptionMsg);
            request.setAttribute("errorStartDateMsg", errorStartDateMsg);
            request.setAttribute("errorDeadLineMsg", errorDeadLineMsg);
            request.setAttribute("errorBudgetMsg", errorBudgetMsg);
            request.setAttribute("errorProfitMsg", errorProfitMsg);
            request.setAttribute("projectName", projectName);
            request.setAttribute("startDate", startDate);
            request.setAttribute("deadLine", deadLine);
            request.setAttribute("budget", budgetStr);
            request.setAttribute("profit", profitStr);
            request.setAttribute("descriptionArray", descriptionArray);
            request.setAttribute("departmentId", departmentId);
            request.getRequestDispatcher("adminProjectInsert.jsp").forward(request, response);
        } else {
            description = ProjectUtil.getDescription(descriptionArray);
            String insertMsg = "";
            HttpSession session = request.getSession();
            boolean insertResult = pDAO.insert(new Project(projectName, description, startDate, deadLine, budget, profit, departmentId));
            if (insertResult) {
                int totalProject = (int) request.getServletContext().getAttribute("totalProject") + 1;
                request.getServletContext().setAttribute("totalProject", totalProject);

                insertMsg = "Add new department " + projectName + " successfully!";
            } else {
                insertMsg = "Add new project " + projectName + " failed. Please try again!";
            }
            session.setAttribute("actionMsg", insertMsg);
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("window.parent.location.href = 'show-department?page=1';");
            out.println("</script>");
            out.close();
        }
    }
}
