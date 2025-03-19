package Controller;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import Model.Project;
import Utils.ProjectUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author nongt
 */
@WebServlet(name = "UpdateProject", urlPatterns = {"/update-project"})

public class UpdateProject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectCode = request.getParameter("projectCode");

        ProjectDAO pDao = new ProjectDAO();
        ProjectDTO project = pDao.selectByProjectCode(new Project(projectCode));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String completion = project.getCompletion() + "";
        String startDate = project.getStartDate() + "";
        String endDate = "Chưa hoàn thiện";
        if (Integer.parseInt(completion) == 100) {
            endDate = LocalDate.now().format(formatter) + "";
        }
        LocalDate deadLine = project.getDeadLine();
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
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("deadLine", deadLine);
        request.setAttribute("completion", completion);
        request.setAttribute("budget", budget);
        request.setAttribute("profit", profit);
        request.setAttribute("descriptionArray", descriptionArray);

        request.getRequestDispatcher("adminProjectUpdate.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectCode = request.getParameter("projectCode");
        String projectName = request.getParameter("projectName");
        String[] descriptionArray = request.getParameterValues("description");
        String description = "";
        String completionStr = request.getParameter("completion");
        String budgetStr = request.getParameter("budget").replace(",", "");
        String profitStr = request.getParameter("profit").replace(",", "");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = null;
        if (!completionStr.isEmpty() && Integer.parseInt(completionStr) == 100) {
            endDate = LocalDate.now();
        }
        LocalDate deadLine = request.getParameter("deadLine") != null && !request.getParameter("deadLine").isEmpty()
                ? LocalDate.parse(request.getParameter("deadLine")) : null;
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        String error = "";

        if (projectName.trim().isEmpty()) {
            error += "Project Name must not be empty. <br/>";
        }
        if (ProjectUtil.isEmptyDescription(descriptionArray)) {
            error += "Description must not be empty. <br/>";
        }
        int completion = 0;
        try {
            completion = Integer.parseInt(completionStr);
        } catch (NumberFormatException e) {
            error += "Completion must be a valid number. <br/>";
        }
        if (completion < 0 || completion > 100) {
            error += "Completion must be between 0 and 100. <br/>";
        }
        double budget = 0, profit = 0;
        try {
            budget = Double.parseDouble(budgetStr);
        } catch (NumberFormatException e) {
            error += "Budget must be a valid number. <br/>";
        }

        try {
            profit = Double.parseDouble(profitStr);
        } catch (NumberFormatException e) {
            error += "Profit must be a valid number. <br/>";
        }

        if (budget < 0) {
            error += "Budget must be a positive number. <br/>";
        }
        if (profit < 0) {
            error += "Profit must be a positive number. <br/>";
        }
        if (deadLine != null && deadLine.isBefore(startDate)) {
            error += "Dead Line must be after Start Date. <br/>";
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("projectCode", projectCode);
            request.setAttribute("projectName", projectName);
            request.setAttribute("descriptionArray", descriptionArray);
            request.setAttribute("completion", completionStr);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("deadLine", deadLine);
            request.setAttribute("budget", budgetStr);
            request.setAttribute("profit", profitStr);
            request.setAttribute("departmentId", departmentId);

            request.getRequestDispatcher("adminProjectUpdate.jsp").forward(request, response);
        } else {
            description = ProjectUtil.getDescription(descriptionArray);
            boolean updateResult = new ProjectDAO().update(new Project(projectCode, projectName, description, completion, startDate, endDate, deadLine, budget, profit));
            if (updateResult) {
                response.sendRedirect("view-project?projectCode=" + projectCode + "&successMsg=Update successful!");
            } else {
                response.sendRedirect("view-project?projectCode=" + projectCode + "&errorMsg=Update failed. Please try again!");
            }
        }
    }
}
