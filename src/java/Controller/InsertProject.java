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

        String error = "";
        
        if (projectName.trim().isEmpty()) {
            error += "Project Name must be not empty field <br/>";
        }
        if (ProjectUtil.isEmptyDescription(descriptionArray)) {
            error += "Description must be not empty <br/>";
        }
        if (startDateStr == null || startDateStr.trim().isEmpty()) {
            error += "startDate is required <br/>";
        }

        LocalDate startDate = null;
        LocalDate deadLine = null;
        try {
            startDate = LocalDate.parse(startDateStr);
            deadLine = LocalDate.parse(deadLineStr);
            if (deadLine != null && deadLine.isBefore(startDate)) {
                error += "Dead Line must be after Start Date. <br/>";
            }
        } catch (Exception e) {
            error += "Invalid startDate format <br/>";
            error += "Invalid deadLine format <br/>";
        }
        try {
            departmentId = Integer.parseInt(request.getParameter("department"));
        } catch (NumberFormatException e) {
            error += "Please select a valid department <br/>";
        }
        double budget = 0, profit = 0;
        try {
            if (budgetStr == null || budgetStr.isEmpty()) {
                error += "Budget must not be empty. <br/>";
            } else {
                budget = Double.parseDouble(budgetStr);
                if (budget < 0) {
                    error += "Budget cannot be negative. <br/>";
                }
            }
            if (profitStr == null || profitStr.isEmpty()) {
                error += "Profit must not be empty. <br/>";
            } else {
                profit = Double.parseDouble(profitStr);
                if (profit < 0) {
                    error += "Profit cannot be negative. <br/>";
                }
            }
        } catch (NumberFormatException e) {
            error += "Budget must be a valid number. <br/>";
            error += "Profit must be a valid number. <br/>";
        }
        if (!error.isEmpty() ) {
            System.out.println("Validation errors found, returning to form");
            request.setAttribute("error", error);
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
            out.println("window.parent.location.href = 'show-project?page=1';");
            out.println("</script>");
            out.close();
        }
    }
}
