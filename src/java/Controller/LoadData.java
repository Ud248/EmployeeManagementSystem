/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import DAO.ProjectDAO;
import DTO.DepartmentDTO;
import DTO.EmployeeDTO;
import DTO.ProjectDTO;
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
            url = "welcome";

            EmployeeDAO eDao = new EmployeeDAO();
            DepartmentDAO dDAO = new DepartmentDAO();
            ProjectDAO pDAO = new ProjectDAO();
            int itemsPerPage = 10;

//phan trang employee
            int currentPageEmployee = 1;
            try {
                String pageParam = request.getParameter("pageEmployee");
                if (pageParam != null && !pageParam.isEmpty()) {
                    currentPageEmployee = Integer.parseInt(pageParam);
                }
            } catch (NumberFormatException e) {
                // Use default value
            }

            int positionIdFilter = 0, departmentIdFilter = 0;
            if ("true".equals(request.getParameter("reset"))) {
                session.removeAttribute("positionIdFilter");
                session.removeAttribute("departmentIdFilter");
            } else {
                positionIdFilter = (session.getAttribute("positionIdFilter") != null) ? (int) session.getAttribute("positionIdFilter") : 0;
                departmentIdFilter = (session.getAttribute("departmentIdFilter") != null) ? (int) session.getAttribute("departmentIdFilter") : 0;
                String positionIdFilterParam = request.getParameter("position");
                if (positionIdFilterParam != null && !positionIdFilterParam.isEmpty()) {
                    try {
                        positionIdFilter = Integer.parseInt(positionIdFilterParam);
                    } catch (NumberFormatException e) {
                        positionIdFilter = 0;
                    }
                }

                String departmentIdFilterParam = request.getParameter("department");
                if (departmentIdFilterParam != null && !departmentIdFilterParam.isEmpty()) {
                    try {
                        departmentIdFilter = Integer.parseInt(departmentIdFilterParam);
                    } catch (NumberFormatException e) {
                        departmentIdFilter = 0;
                    }
                }
            }

            //int totalEmployee = eDao.getTotalEmployeesForFilter(departmentIdFilter, positionIdFilter);
            //int totalPagesEmployee = (int) Math.ceil((double) totalEmployee / itemsPerPage);
            if (currentPageEmployee < 1) {
                currentPageEmployee = 1;
            }
//            if (currentPageEmployee > totalPagesEmployee) {
//                currentPageEmployee = totalPagesEmployee;
//            }

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

            //List<EmployeeDTO> employees = eDao.selectEmployeesByPageForFilter(currentPageEmployee, itemsPerPage, departmentIdFilter, positionIdFilter);
            List<DepartmentDTO> departments = dDAO.selectDepartmentsByPage(currentPageDep, itemsPerPage);
            List<ProjectDTO> projects = pDAO.selectAllProjectDTO(currentPagePro, itemsPerPage);

            //session.setAttribute("employees", employees);
            request.getServletContext().setAttribute("departments", departments);
            request.getServletContext().setAttribute("projects", projects);

            session.setAttribute("positionIdFilter", positionIdFilter);
            session.setAttribute("departmentIdFilter", departmentIdFilter);
            session.setAttribute("currentPageEmployee", currentPageEmployee);
//            session.setAttribute("totalPagesEmployee", totalPagesEmployee);
//            session.setAttribute("totalEmployee", totalEmployee);

            session.setAttribute("totalPagesDep", totalPagesDep);
            session.setAttribute("currentPageDep", currentPageDep);

            session.setAttribute("currentPagePro", currentPagePro);
            session.setAttribute("totalPagesPro", totalPagesPro);

            session.setAttribute("itemsPerPage", itemsPerPage);

        } else {
            url = "employee.jsp";
        }
        session.setAttribute("isAdmin", isAdmin);
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
