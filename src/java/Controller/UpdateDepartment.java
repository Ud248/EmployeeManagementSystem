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

    private DepartmentDTO getDepartmentDTOById(ArrayList<DepartmentDTO> departments, int id) {
        for (DepartmentDTO department : departments) {
            if (department.getDepartmentId() == id) {
                return department;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departmentId = request.getParameter("departmentId");

        HttpSession session = request.getSession();
        ArrayList<DepartmentDTO> departments = (ArrayList<DepartmentDTO>) request.getServletContext().getAttribute("departments");
        DepartmentDTO dep = getDepartmentDTOById(departments, Integer.parseInt(departmentId));
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

        request.setAttribute("departmentId", departmentId);
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
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String departmentName = request.getParameter("departmentName");
        String telephone = request.getParameter("telephone");
        String openTime = request.getParameter("openTime");
        String[] descriptionArray = request.getParameterValues("description");
        String description = "";
        LocalTime startTime = null;
        LocalTime endTime = null;
        HttpSession session = request.getSession();

        String errorTelephoneMsg = "";
        String errorNameMsg = "";
        String errorDescriptionMsg = "";
        String errorOpenTimeMsg = "";
        if (departmentName.trim().isEmpty()) {
            errorNameMsg = "Department Name must be not empty field";
        }
        if (openTime.trim().isEmpty()) {
            errorOpenTimeMsg = "Open Time must be not empty field";
        }
        if (telephone.trim().isEmpty()) {
            errorTelephoneMsg = "Telephone must be not empty field";
        }
        if (DepartmentUtil.isEmptyDescription(descriptionArray)) {
            errorDescriptionMsg = "Description must be not empty";
        }
        if (!telephone.matches(REGEX_TELEPHONE)) {
            errorTelephoneMsg = "Invalid telephone's format (10 characters contain number from 0 to 9)";
        }
        if (!openTime.matches(REGEX_OPENTIME)) {
            errorOpenTimeMsg = "Invalid open time's format (HH:mm - HH:mm)";
        } else {
            LocalTime[] localTimeArray = DepartmentUtil.getStartAndEndTime(openTime);
            startTime = localTimeArray[0];
            endTime = localTimeArray[1];
            if (!DepartmentUtil.isValidOpenTimeValue(startTime, endTime)) {
                errorOpenTimeMsg = "End Time must be after Start Time";
            }
        }
        if (!errorTelephoneMsg.isEmpty() || !errorDescriptionMsg.isEmpty() || !errorOpenTimeMsg.isEmpty()) {
            request.setAttribute("errorNameMsg", errorNameMsg);
            request.setAttribute("errorTelephoneMsg", errorTelephoneMsg);
            request.setAttribute("errorDescriptionMsg", errorDescriptionMsg);
            request.setAttribute("errorOpenTimeMsg", errorOpenTimeMsg);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("departmentName", departmentName);
            request.setAttribute("telephone", telephone);
            request.setAttribute("openTime", openTime);
            request.setAttribute("descriptionArray", descriptionArray);
            request.getRequestDispatcher("adminDepartmentUpdate.jsp").forward(request, response);
        } else {
            description = DepartmentUtil.getDescription(descriptionArray);
            boolean updateResult = new DepartmentDAO().update(new Department(departmentId, departmentName, description, startTime, endTime, telephone));
            if (updateResult) {
                int currentPageDep = (int) session.getAttribute("currentPageDep");
                List<DepartmentDTO> departments = new DAO.DepartmentDAO().selectDepartmentsByPage(currentPageDep, 10);
                request.getServletContext().setAttribute("departments", departments);
                response.sendRedirect("viewdepartment?departmentId=" + departmentId + "&successMsg=Update successful!");
            } else {
                response.sendRedirect("viewdepartment?departmentId=" + departmentId + "&errorMsg=Update failed. Please try again!");
            }
        }
    }

}
