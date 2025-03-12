/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DepartmentDAO;
import DTO.DepartmentDTO;
import Model.Department;
import Utils.DepartmentUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhnn
 */
public class InsertDepartment extends HttpServlet {

    private final String REGEX_TELEPHONE = "^\\d{10}$";
    private final String REGEX_OPENTIME = "^([01]\\d|2[0-3]):[0-5]\\d - ([01]\\d|2[0-3]):[0-5]\\d$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartmentDAO dDao = new DepartmentDAO();
        String departmentName = request.getParameter("departmentName");
        String telephone = request.getParameter("telephone");
        String openTime = request.getParameter("openTime");
        String[] descriptionArray = request.getParameterValues("description");
        String description = "";
        LocalTime startTime = null;
        LocalTime endTime = null;

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
            request.setAttribute("departmentName", departmentName);
            request.setAttribute("telephone", telephone);
            request.setAttribute("openTime", openTime);
            request.setAttribute("descriptionArray", descriptionArray);
            request.getRequestDispatcher("adminDepartmentInsert.jsp").forward(request, response);
        } else {
            description = DepartmentUtil.getDescription(descriptionArray);
            String insertMsg = "";
            HttpSession session = request.getSession();
            Department dep = new Department(departmentName, description, startTime, endTime, telephone);
            boolean insertResult = dDao.insert(dep);
            if (insertResult) {
                List<Department> listDepartment = dDao.selectAll();
                request.getServletContext().setAttribute("listDepartment", listDepartment);
                int totalDepartment = (int) request.getServletContext().getAttribute("totalDepartment") + 1;
                request.getServletContext().setAttribute("totalDepartment", totalDepartment);

                int totalPagesDep = (int) Math.ceil((double) totalDepartment / 10);

                session.setAttribute("totalPagesDep", totalPagesDep);
                session.setAttribute("currentPageDep", 1);

                insertMsg = "Add new department " + departmentName + " successfully!";

                List<DepartmentDTO> departments = new DAO.DepartmentDAO().selectDepartmentsByPage(1, 10);
                request.getServletContext().setAttribute("departments", departments);

            } else {
                insertMsg = "Add new department " + departmentName + " failed. Please try again!";

            }
            session.setAttribute("actionMsg", insertMsg);
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("window.parent.location.reload();");
            out.println("window.close();");
            out.println("</script>");
            out.close();
        }
    }

}
