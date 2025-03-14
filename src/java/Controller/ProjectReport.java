package Controller;

import DAO.ProjectDAO;
import Model.Project;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProjectReport", urlPatterns = {"/project-report"})
public class ProjectReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            ProjectDAO projectDAO = new ProjectDAO();
            ArrayList<Project> projects = projectDAO.selectReport();

            // Kiểm tra nếu danh sách rỗng, thêm các dự án mẫu để debug
            if (projects == null || projects.isEmpty()) {
                projects = new ArrayList<>();

                // Tạo dữ liệu mẫu để test
                Project p1 = new Project();
                p1.setProjectName("Dự án mẫu A");
                p1.setCompletion(75);
                p1.setBudget(10000);
                p1.setProfit(2000);

                Project p2 = new Project();
                p2.setProjectName("Dự án mẫu B");
                p2.setCompletion(100);
                p2.setBudget(5000);
                p2.setProfit(1000);

                Project p3 = new Project();
                p3.setProjectName("Dự án mẫu C");
                p3.setCompletion(30);
                p3.setBudget(8000);
                p3.setProfit(-500);

                projects.add(p1);
                projects.add(p2);
                projects.add(p3);
            }

            List<Map<String, Object>> projectData = new ArrayList<>();
            for (Project project : projects) {
                Map<String, Object> map = new HashMap<>();
                map.put("projectName", project.getProjectName());
                map.put("completion", project.getCompletion());
                map.put("budget", project.getBudget());
                map.put("profit", project.getProfit());

                // Tính toán budgetStatus dựa trên budget và profit
                String budgetStatus;
                if (project.getProfit() > 0) {
                    budgetStatus = "Under Budget";
                } else if (project.getProfit() == 0) {
                    budgetStatus = "On Budget";
                } else {
                    budgetStatus = "Over Budget";
                }
                map.put("budgetStatus", budgetStatus);

                projectData.add(map);
            }

            String json = new Gson().toJson(projectData);
            response.getWriter().write(json);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "true");
            errorResponse.put("message", e.getMessage());

            String json = new Gson().toJson(errorResponse);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(json);
        }
    }
}
