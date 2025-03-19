/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DepartmentDAO;
import DTO.DepartmentDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import Model.Department;
import Utils.DepartmentUtil;
import java.util.List;

/**
 *
 * @author anhnn
 */
public class UpdateDepartment extends HttpServlet {

    private final String REGEX_TELEPHONE = "^\\d{10}$";
    private final String REGEX_OPENTIME = "^([01]\\d|2[0-3]):[0-5]\\d - ([01]\\d|2[0-3]):[0-5]\\d$";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departmentCode = request.getParameter("departmentCode");

        DepartmentDTO dep = new DepartmentDAO().selectByEmployeeCode(departmentCode);
        String departmentName = dep.getDepartmentName();
        String description = dep.getDescription();
        String openTime = dep.getOpenTime();
        String managerName = dep.getManagerName();
        String telephone = dep.getTelephone();
        String totalEmployee = dep.getTotalEmployee() + "";
        String[] descriptionArray = description.split("\\.");
        for (int i = 0; i < descriptionArray.length; i++) {
            descriptionArray[i] = descriptionArray[i].trim() + ".";
        }
        DecimalFormat df = new DecimalFormat("#,###.##");
        String costPerMonth = df.format(dep.getCostPerMonth());

        request.setAttribute("departmentCode", departmentCode);
        request.setAttribute("departmentName", departmentName);
        request.setAttribute("description", description);
        request.setAttribute("openTime", openTime);
        request.setAttribute("managerName", managerName);
        request.setAttribute("telephone", telephone);
        request.setAttribute("costPerMonth", costPerMonth);
        request.setAttribute("totalEmployee", totalEmployee);
        request.setAttribute("descriptionArray", descriptionArray);
        request.getRequestDispatcher("adminDepartmentUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departmentCode = request.getParameter("departmentCode");
        String departmentName = request.getParameter("departmentName");
        String telephone = request.getParameter("telephone");
        String openTime = request.getParameter("openTime");
        String[] descriptionArray = request.getParameterValues("description");
        String description = "";
        LocalTime startTime = null;
        LocalTime endTime = null;
        
        String error= "";
        if (departmentName.trim().isEmpty()) {
            error += "Department Name must be not empty field <br/>";
        }
        if (openTime.trim().isEmpty()) {
            error += "Open Time must be not empty field <br/>";
        }
        if (telephone.trim().isEmpty()) {
            error += "Telephone must be not empty field <br/>";
        }
        if (DepartmentUtil.isEmptyDescription(descriptionArray)) {
            error += "Description must be not empty <br/>";
        }
        if (!telephone.matches(REGEX_TELEPHONE)) {
            error += "Invalid telephone's format (10 characters contain number from 0 to 9) <br/>";
        }
        if (!openTime.matches(REGEX_OPENTIME)) {
            error +="Invalid open time's format (HH:mm - HH:mm) <br/>";
        } else {
            LocalTime[] localTimeArray = DepartmentUtil.getStartAndEndTime(openTime);
            startTime = localTimeArray[0];
            endTime = localTimeArray[1];
            if (!DepartmentUtil.isValidOpenTimeValue(startTime, endTime)) {
                error += "End Time must be after Start Time <br/>";
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("departmentCode", departmentCode);
            request.setAttribute("departmentName", departmentName);
            request.setAttribute("telephone", telephone);
            request.setAttribute("openTime", openTime);
            request.setAttribute("descriptionArray", descriptionArray);
            request.getRequestDispatcher("adminDepartmentUpdate.jsp").forward(request, response);
        } else {
            description = DepartmentUtil.getDescription(descriptionArray);
            Department dep = new Department(departmentCode, departmentName, description, startTime, endTime, telephone);
            boolean updateResult = new DepartmentDAO().update(dep);
            if (updateResult) {
                List<Department> listDepartment = new DAO.DepartmentDAO().selectAll();
                request.getServletContext().setAttribute("listDepartment", listDepartment);

                response.sendRedirect("view-department?departmentCode=" + departmentCode + "&successMsg=Update successful!");
            } else {
                response.sendRedirect("view-department?departmentCode=" + departmentCode + "&errorMsg=Update failed. Please try again!");
            }
        }
    }

}
