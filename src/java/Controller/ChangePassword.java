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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String url = "";
        String error = "";

        HttpSession session = request.getSession();
        Object obj = session.getAttribute("employee");
        EmployeeDTO employee = null;
        if (obj != null) {
            employee = (EmployeeDTO) obj;
        }
        if (employee == null) {
            error += "Bạn chưa đăng nhập vào hệ thống!";
            url = "changePassword.jsp";
        } else {
            if (!oldPassword.equals(employee.getPassword())) {
                error = "Mật khẩu hiện tại không chính xác!";
                url = "changePassword.jsp";
            } else {
                employee.setPassword(newPassword);
                AccountDAO accoundDao = new AccountDAO();
                if (accoundDao.update(employee)) {
                    error = "Mật khẩu đã thay đổi thành công";
                    url = "changePassword.jsp";
                } else {
                    error = "Quá trình thay đổi mật khẩu không thành công !";
                    url = "/changePassword.jsp";
                }
            }
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher(url).forward(request, response);
    }
}
