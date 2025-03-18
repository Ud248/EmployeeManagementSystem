/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import DAO.ProjectDAO;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nongt
 */
public class ShowReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reportType = request.getParameter("report");
        Map<String, Object> jsonResponse = new HashMap<>();

        switch (reportType) {
            case "employee" -> {
                EmployeeDAO employeeDAO = new EmployeeDAO();
                Map<String, Integer> employeeData = employeeDAO.getBasicSalaryPerEmployeeCode();
                Map<String, Integer> totalSalaryPerPosition = employeeDAO.getTotalBasicSalaryPerPosition();
                int totalDirector = employeeDAO.getTotalDirectorPosition();
                int totalEmployee = employeeDAO.getTotalEmployeePosition();
                int totalManager = employeeDAO.getTotalManagerPosition();
                int totalBasicSalary = employeeDAO.getTotalBasicSalary();

                jsonResponse.put("salaryLabels", new ArrayList<>(employeeData.keySet()));
                jsonResponse.put("salaryData", new ArrayList<>(employeeData.values()));
                jsonResponse.put("totalSalaryLabels", new ArrayList<>(totalSalaryPerPosition.keySet()));
                jsonResponse.put("totalSalaryData", new ArrayList<>(totalSalaryPerPosition.values()));
                jsonResponse.put("totalDirector", totalDirector);
                jsonResponse.put("totalEmployee", totalEmployee);
                jsonResponse.put("totalManager", totalManager);
                jsonResponse.put("totalBasicSalary", totalBasicSalary);
            }
            case "project" -> {
                ProjectDAO projectDAO = new ProjectDAO();
                Map<String, Integer> completion = projectDAO.getCompletionPerProject();
                Map<String, Integer> totalProject = projectDAO.getTotalProjectPerDepartment();
                int totalCompletedProject = projectDAO.getTotalProjectCompleted();
                Double totalBudget = projectDAO.getTotalBudget();
                Double totalProfit = projectDAO.getTotalProfit();
                String topDepartment = projectDAO.getTopDepByCompletedPro();
                int totalCompletedProOfTopDep = projectDAO.getCountCompletedProByTopDep();

                jsonResponse.put("completionLabels", new ArrayList<>(completion.keySet()));
                jsonResponse.put("completionData", new ArrayList<>(completion.values()));
                jsonResponse.put("totalProjectLabels", new ArrayList<>(totalProject.keySet()));
                jsonResponse.put("totalProjectData", new ArrayList<>(totalProject.values()));
                jsonResponse.put("totalCompletedProject", totalCompletedProject);
                jsonResponse.put("totalBudget", totalBudget);
                jsonResponse.put("totalProfit", totalProfit);
                jsonResponse.put("topDepartment", topDepartment);
                jsonResponse.put("totalCompletedProOfTopDep", totalCompletedProOfTopDep);
            }
            default -> {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(jsonResponse));
    }

    public static void main(String[] args) {
        ProjectDAO projectDAO = new ProjectDAO();
        Map<String, Double> top5Projects = projectDAO.getTopProfitPerProjectName();
        System.out.println(top5Projects);
    }
}
