/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import Model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Ud
 */
@WebServlet(name="ViewEmployee", urlPatterns={"/view-employee"})
public class ViewEmployee extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String employeeCode = request.getParameter("employeeCode");
        
        EmployeeDAO eDao = new EmployeeDAO();
        EmployeeDTO e = eDao.selectByEmployeeCode(new Employee(employeeCode));
        
        request.setAttribute("employeeCode", e.getEmployeeCode());
        request.setAttribute("fullname", e.getFullname());
        request.setAttribute("birthDate", e.getBirthDate());
        request.setAttribute("gender", e.getGender());
        request.setAttribute("tel", e.getTel());
        request.setAttribute("address", e.getAddress());
        request.setAttribute("positionName", e.getPositionName());
        request.setAttribute("departmentName", e.getDepartmentName());
        request.setAttribute("basicSalary", e.getBasicSalary());
        request.setAttribute("username", e.getUsername());
        request.setAttribute("password", e.getPassword());
        
        request.getRequestDispatcher("adminEmployeeView.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    }
}
