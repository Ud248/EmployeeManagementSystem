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
import java.util.List;
import java.util.Map;

/**
 *
 * @author nongt
 */
public class ShowReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String report = request.getParameter("report");

        switch (report) {
            case "employee" -> {
                EmployeeDAO employeeDAO = new EmployeeDAO();
                Map<String, Integer> employeeData = employeeDAO.getTopNBasicSalary(10);
                Map<String, Integer> totalSalaryPerPosition = employeeDAO.getTotalBasicSalaryPerPosition();
                int totalDirector = employeeDAO.getTotalEmployeeInPosition("GD");
                int totalEmployee = employeeDAO.getTotalEmployeeInPosition("NV");
                int totalManager = employeeDAO.getTotalEmployeeInPosition("QL");
                int totalBasicSalary = employeeDAO.getTotalBasicSalary();
                
                List<String> salaryLabelsList = new ArrayList<>(employeeData.keySet());
                String salaryLabels = new Gson().toJson(salaryLabelsList);
                request.setAttribute("salaryLabels", salaryLabels);
                
                List<String> totalSalaryLabelsList = new ArrayList<>(totalSalaryPerPosition.keySet());
                String totalSalaryLabels = new Gson().toJson(totalSalaryLabelsList);
                request.setAttribute("totalSalaryLabels", totalSalaryLabels);

                request.setAttribute("salaryData", new ArrayList<>(employeeData.values()));
                request.setAttribute("totalSalaryData", new ArrayList<>(totalSalaryPerPosition.values()));
                request.setAttribute("totalDirector", totalDirector);
                request.setAttribute("totalEmployee", totalEmployee);
                request.setAttribute("totalManager", totalManager);
                request.setAttribute("totalBasicSalary", totalBasicSalary);
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

                List<String> completionLabelsList = new ArrayList<>(completion.keySet());
                String completionLabels = new Gson().toJson(completionLabelsList);
                request.setAttribute("completionLabels", completionLabels);
                
                List<String> totalProjectLabelsList = new ArrayList<>(totalProject.keySet());
                String totalProjectLabels = new Gson().toJson(totalProjectLabelsList);
                request.setAttribute("totalProjectLabels", totalProjectLabels);

                request.setAttribute("completionData", new ArrayList<>(completion.values()));
                request.setAttribute("totalProjectData", new ArrayList<>(totalProject.values()));
                request.setAttribute("totalCompletedProject", totalCompletedProject);
                request.setAttribute("totalBudget", totalBudget);
                request.setAttribute("totalProfit", totalProfit);
                request.setAttribute("topDepartment", topDepartment);
                request.setAttribute("totalCompletedProOfTopDep", totalCompletedProOfTopDep);
            }
            default -> {
                return;
            }
        }
        request.setAttribute("report", report);
        request.getRequestDispatcher("adminReport.jsp").forward(request, response);
    }

//    public static void main(String[] args) {
//        ProjectDAO projectDAO = new ProjectDAO();
//        Map<String, Double> top5Projects = projectDAO.getTopProfitPerProjectName();
//        System.out.println(top5Projects);
//    }
}
