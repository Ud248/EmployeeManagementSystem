/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        AccountDAO accountDAO = new AccountDAO();
        boolean isTrueAccount = accountDAO.isTrueAccount(username, password);
        if (isTrueAccount) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            boolean isAdmin = accountDAO.isAdmin(username);
            System.out.println(isAdmin);
            if (isAdmin) {
                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("employee.jsp");
            }
        } else {
            request.setAttribute("username", username);
            request.setAttribute("error", "Incorrect username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
