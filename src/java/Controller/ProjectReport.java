package Controller;

import DAO.ProjectDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet(name = "ProjectReport", urlPatterns = {"/project-report"})
public class ProjectReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectDAO projectDAO = new ProjectDAO();
        Map<String, Integer> projectPerDept = projectDAO.getTotalProjectPerDepartment();
        request.setAttribute("projectPerDept", projectPerDept);
        request.getRequestDispatcher("adminProjectReport.jsp").forward(request, response);
    }
}
