/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import DAO.ProjectDAO;
import DAO.WorkDAO;
import DTO.DepartmentDTO;
import DTO.EmployeeDTO;
import DTO.ProjectDTO;
import Model.Department;
import Model.Employee;
import Model.Position;
import Model.Project;
import Model.Work;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ud
 */
@WebServlet(name = "LoadData", urlPatterns = {"/load-data"})
public class LoadData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("employee");
        EmployeeDTO employee = null;
        if (obj != null) {
            employee = (EmployeeDTO) obj;
        }
        boolean isAdmin = new AccountDAO().isAdmin(session.getAttribute("username") + "");
        String url = "";
        if (isAdmin) {
            url = "admin.jsp";
            EmployeeDAO eDao = new EmployeeDAO();
            DepartmentDAO dDAO = new DepartmentDAO();
            ProjectDAO pDAO = new ProjectDAO();
            int currentPage = 1;
            int itemsPerPage = 10;
            try {
                String pageParam = request.getParameter("page");
                if (pageParam != null && !pageParam.isEmpty()) {
                    currentPage = Integer.parseInt(pageParam);
                }
            } catch (NumberFormatException e) {
                // Use default value
            }
            int totalEmployees = eDao.getTotalEmployees();
            int totalPages = (int) Math.ceil((double) totalEmployees / itemsPerPage);
            if (currentPage < 1) {
                currentPage = 1;
            }
            if (currentPage > totalPages) {
                currentPage = totalPages;
            }

            //phân trang dep
            int currentPageDep = 1;
            try {
                String pageParam = request.getParameter("pageDep");
                if (pageParam != null && !pageParam.isEmpty()) {
                    currentPageDep = Integer.parseInt(pageParam);
                }
            } catch (NumberFormatException e) {
                // Use default value
            }
            int totalDepartment = (int) request.getServletContext().getAttribute("totalDepartment");
            int totalPagesDep = (int) Math.ceil((double) totalDepartment / itemsPerPage);
            if (currentPageDep < 1) {
                currentPageDep = 1;
            }
            if (currentPageDep > totalPagesDep) {
                currentPageDep = totalPagesDep;
            }

            //phân trang project
            int currentPagePro = 1;
            try {
                String pageParam = request.getParameter("pagePro");
                if (pageParam != null && !pageParam.isEmpty()) {
                    currentPagePro = Integer.parseInt(pageParam);
                }
            } catch (NumberFormatException e) {
                // Use default value
            }
            int totalProject = (int) request.getServletContext().getAttribute("totalProject");
            int totalPagesPro = (int) Math.ceil((double) totalProject / itemsPerPage);
            if (currentPagePro < 1) {
                currentPagePro = 1;
            }
            if (currentPagePro > totalPagesPro) {
                currentPagePro = totalPagesPro;
            }
            
            List<EmployeeDTO> employees = eDao.selectEmployeesByPage(currentPage, itemsPerPage);
            List<DepartmentDTO> departments = dDAO.selectDepartmentsByPage(currentPageDep, itemsPerPage);
            List<ProjectDTO> projects = pDAO.selectAllProjectDTO(currentPagePro, itemsPerPage);

            request.getServletContext().setAttribute("employees", employees);
            request.getServletContext().setAttribute("departments", departments);
            request.getServletContext().setAttribute("projects", projects);

            session.setAttribute("currentPage", currentPage);
            session.setAttribute("totalPages", totalPages);
            session.setAttribute("totalEmployees", totalEmployees);

            session.setAttribute("totalPagesDep", totalPagesDep);
            session.setAttribute("currentPageDep", currentPageDep);

            session.setAttribute("currentPagePro", currentPagePro);
            session.setAttribute("totalPagesPro", totalPagesPro);

            session.setAttribute("itemsPerPage", itemsPerPage);

        } else {
            url = "employee.jsp";
            WorkDAO w = new WorkDAO();
            ArrayList<Work> works = w.selectAll();
            request.getServletContext().setAttribute("works", works);
        }
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
