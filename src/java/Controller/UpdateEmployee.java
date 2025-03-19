/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import DTO.DepartmentDTO;
import DTO.EmployeeDTO;
import Model.Department;
import Model.Employee;
import Model.Position;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ud
 */
@WebServlet(name = "UpdateEmployee", urlPatterns = {"/update-employee"})
public class UpdateEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeCode = request.getParameter("employeeCode");

        EmployeeDAO eDao = new EmployeeDAO();
        EmployeeDTO e = eDao.selectByEmployeeCode(new Employee(employeeCode));

        request.setAttribute("employeeCode", e.getEmployeeCode());
        request.setAttribute("fullname", e.getFullname());
        request.setAttribute("birthdate", e.getBirthDate());
        request.setAttribute("gender", e.getGender());
        request.setAttribute("tel", e.getTel());
        request.setAttribute("address", e.getAddress());
        request.setAttribute("positionId", e.getPositionId());
        request.setAttribute("departmentId", e.getDepartmentId());
        request.setAttribute("basicSalary", e.getBasicSalary());
        request.setAttribute("username", e.getUsername());
        request.setAttribute("password", e.getPassword());
        request.getRequestDispatcher("adminEmployeeUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = "";
        String url = "";
        HttpSession session = request.getSession();

        String fullname = request.getParameter("fullname");
        String birthdateStr = request.getParameter("birthdate");
        String gender = request.getParameter("gender");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String employeeCode = request.getParameter("employeeCode");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int positionId = 0, departmentId = 0, basicSalary = 0;

        EmployeeDAO eDao = new EmployeeDAO();

        String[] fullnameArray = fullname.split("\\s+");
        String lastname = fullnameArray[0];
        String firstname = "";
        for (int i = 1; i < fullnameArray.length; i++) {
            firstname += fullnameArray[i] + " ";
        }
        firstname = firstname.trim();

        LocalDate birthdate = null;
        try {
            birthdate = LocalDate.parse(birthdateStr);
        } catch (Exception e) {
            error += "Invalid birthdate format.<br>";
        }
        if (eDao.isExistPhoneNumberForUpdate(tel, employeeCode)) {
            error += "This phone number is already registered.<br>";
        }

        try {
            positionId = Integer.parseInt(request.getParameter("positionId"));
        } catch (NumberFormatException e) {
            error += "Please select a valid position.<br>";
        }

        try {
            departmentId = Integer.parseInt(request.getParameter("departmentId"));
        } catch (NumberFormatException e) {
            error += "Please select a valid department.<br>";
        }
        try {
            basicSalary = Integer.parseInt(request.getParameter("basicSalary"));
        } catch (NumberFormatException e) {
            error += "Please input a valid basic salary.<br>";
        }

        if (basicSalary <= 0) {
            error += "Basic salary must be greater than 0.<br>";
        }

        if (eDao.isExistManagerInDepartmentForUpdate(departmentId, employeeCode) && positionId == 2) {
            error += "Selected Department already has manager. One Department can have only one manager.";
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("fullname", fullname);
            request.setAttribute("birthdate", birthdateStr);
            request.setAttribute("gender", gender);
            request.setAttribute("tel", tel);
            request.setAttribute("address", address);
            request.setAttribute("positionId", positionId);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("basicSalary", basicSalary);
            request.setAttribute("employeeCode", employeeCode);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("adminEmployeeUpdate.jsp").forward(request, response);
        } else {
            Employee e = new Employee(employeeCode, firstname, lastname, birthdate, gender, tel, address, positionId, departmentId, basicSalary);
            boolean success = eDao.update(e);
            if (success) {
                if(employeeCode.equals(((EmployeeDTO)session.getAttribute("employee")).getEmployeeCode())){
                    session.setAttribute("employee", eDao.selectByEmployeeCode(new Employee(employeeCode, firstname, lastname, birthdate, gender, tel, address, positionId, departmentId, basicSalary)));
                }
                response.sendRedirect("view-employee?employeeCode=" + employeeCode + "&successMsg=Update successful!");
            } else {
                response.sendRedirect("view-employee?employeeCode=" + employeeCode + "&errorMsg=Update failed. Please try again!");
            }
        }
    }
}
