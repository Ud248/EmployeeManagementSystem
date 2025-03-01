/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DepartmentDAO;
import DTO.DepartmentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import Model.Department;

/**
 *
 * @author anhnn
 */
public class UpdateDepartment extends HttpServlet {

    private String REGEX_TELEPHONE = "^\\d{10}$";
    private String REGEX_OPENTIME = "^([01]\\d|2[0-3]):[0-5]\\d - ([01]\\d|2[0-3]):[0-5]\\d$";

//    private boolean isValidTime(String) {
//
//    }
    private boolean isEmptyDescription(String[] arrayDescription) {
        if (arrayDescription == null) {
            return true;
        }
        for (String s : arrayDescription) {
            if (!s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String getDescription(String[] descriptionArray) {
        String description = "";
        for (int i = 0; i < descriptionArray.length; i++) {
            if (!descriptionArray[i].endsWith(".")) {
                descriptionArray[i] += ".";
            }
            if (!descriptionArray[i].isEmpty()) {
                description += descriptionArray[i];
            }
        }
        return description;
    }

    private LocalTime[] getStartAndEndTime(String openTime) {
        String[] timeStringArray = openTime.split("-");
        LocalTime[] localTimeArray = new LocalTime[2];
        for (int i = 0; i < 2; i++) {
            localTimeArray[i] = LocalTime.parse(timeStringArray[i].trim());
        }
        return localTimeArray;
    }

    private boolean isValidOpenTimeValue(LocalTime startTime, LocalTime endTime) {
        if (endTime.isAfter(startTime)) {
            return true;
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departmentId = request.getParameter("departmentId");
        int index = Integer.parseInt(departmentId) - 1;

        HttpSession session = request.getSession();
        DepartmentDTO dep = ((ArrayList<DepartmentDTO>) session.getAttribute("departments")).get(index);
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

        String errorTelephoneMsg = "";
        String errorDescriptionMsg = "";
        String errorOpenTimeMsg = "";

        if (isEmptyDescription(descriptionArray)) {
            errorDescriptionMsg = "Description must be not empty";
        }
        if (!telephone.matches(REGEX_TELEPHONE)) {
            errorTelephoneMsg = "Invalid telephone's format";
        }
        if (!openTime.matches(REGEX_OPENTIME)) {
            errorOpenTimeMsg = "Invalid open time's format";
        } else {
            LocalTime[] localTimeArray = getStartAndEndTime(openTime);
            startTime = localTimeArray[0];
            endTime = localTimeArray[1];
            if (!isValidOpenTimeValue(startTime, endTime)) {
                errorOpenTimeMsg = "End Time must be after Start Time";
            }
        }
        if (!errorTelephoneMsg.isEmpty() || !errorDescriptionMsg.isEmpty() || !errorOpenTimeMsg.isEmpty()) {
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
            description = getDescription(descriptionArray);
            boolean updateResult = new DepartmentDAO().update(new Department(departmentId, departmentName, description, startTime, endTime, telephone));
            if (updateResult) {
                response.sendRedirect("admin.jsp?status=success");
            } else {
                response.sendRedirect("admin.jsp?status=failure");
            }
        }
    }

}
