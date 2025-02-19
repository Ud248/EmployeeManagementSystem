/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import Model.Employee;
import Model.Work;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ud
 */
@WebServlet(name = "LoadData", urlPatterns = { "/load-data" })
public class LoadData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        boolean isAdmin = new AccountDAO().isAdmin(username);
        String url = "";
        if (isAdmin) {
            url = "admin.jsp";
            EmployeeDAO eDao = new EmployeeDAO();
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

            // Calculate pagination values
            int totalEmployees = eDao.getTotalEmployees();
            int totalPages = (int) Math.ceil((double) totalEmployees / itemsPerPage);

            // Ensure currentPage is within valid range
            if (currentPage < 1) {
                currentPage = 1;
            }
            if (currentPage > totalPages) {
                currentPage = totalPages;
            }

            // Get paginated list of employees
            List<EmployeeDTO> employees = eDao.selectEmployeesByPage(currentPage, itemsPerPage);
            session.setAttribute("employees", employees);
            session.setAttribute("currentPage", currentPage);
            session.setAttribute("totalPages", totalPages);
            session.setAttribute("itemsPerPage", itemsPerPage);
            session.setAttribute("totalEmployees", totalEmployees);
        } else {
            url = "employee.jsp";
        }
//        WorkDAO w = new WorkDAO();
//        ArrayList<Work> works = w.selectAll();
//        request.getServletContext().setAttribute("works", works);
//        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
