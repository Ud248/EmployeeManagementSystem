/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DTO.EmployeeDTO;
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
 * @author Ud
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/change-password"})
public class ChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String error = "";
        String changePasswordMsg = "";

        HttpSession session = request.getSession();

        Object obj = session.getAttribute("employee");
        EmployeeDTO employee = null;
        if (obj != null) {
            employee = (EmployeeDTO) obj;
        }
        if (!oldPassword.equals(employee.getPassword())) {
            error = "The old password is incorrect!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } else {
            employee.setPassword(newPassword);
            AccountDAO accoundDao = new AccountDAO();
            if (accoundDao.update(employee)) {
                changePasswordMsg = "Change password successfully !";
            } else {
                changePasswordMsg = "Change password failed !";
            }
            session.setAttribute("actionMsg", changePasswordMsg);
            response.sendRedirect("change-password");
        }

    }
}
