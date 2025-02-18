/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.EmployeeDAO;
import Model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        boolean isAdmin = new AccountDAO().isAdmin(username);
        String url = "";
        if (isAdmin) {
            url = "admin.jsp";
            EmployeeDAO eDao = new EmployeeDAO();
            int page = 1;
            int itemsPerPage = 5;

            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                // Use default value
            }

            try {
                itemsPerPage = Integer.parseInt(request.getParameter("items"));
            } catch (NumberFormatException e) {
                // Use default value
            }

            List<Employee> employees = eDao.selectAll(page, itemsPerPage);
            int totalEmployees = eDao.getTotalEmployees();
            int totalPages = (int) Math.ceil((double) totalEmployees / itemsPerPage);

            request.getServletContext().setAttribute("employees", employees);
            session.setAttribute("currentPage", page);
            session.setAttribute("totalPages", totalPages);
            session.setAttribute("itemsPerPage", itemsPerPage);
        } else {
            System.out.println("chien lam");
        }

        response.sendRedirect(url);
    }
}
