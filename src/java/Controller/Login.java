/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import Model.Account;
import Model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author anhnn
 */
//@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String url = "";

        Account a = new Account();
        a.setUsername(username);
        a.setPassword(password);

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.selectByUsernameAndPassword(a);
        if (account != null) {
            EmployeeDAO employeeDao = new EmployeeDAO();
            EmployeeDTO employee = employeeDao.selectDTOById(account.getEmployeeId());
            HttpSession session = request.getSession();
            session.setAttribute("employee", employee);
            session.setAttribute("username", account.getUsername());
            if (accountDAO.isAdmin(username)) {
                session.setAttribute("isAdmin", true);
                url = "welcome";
            }
        } else {
            request.setAttribute("username", username);
            request.setAttribute("error", "Incorrect username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
