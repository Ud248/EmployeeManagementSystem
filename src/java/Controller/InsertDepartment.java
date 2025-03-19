/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DepartmentDAO;
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
        String departmentName = request.getParameter("departmentName").trim();
        String telephone = request.getParameter("telephone");
        String openTime = request.getParameter("openTime");
        String[] descriptionArray = request.getParameterValues("description");
        String description = "";
        LocalTime startTime = null;
        LocalTime endTime = null;

        String error = "";
        if (departmentName.isEmpty()) {
            error += "Department Name must not be empty <br/>";
        }
        if (DepartmentUtil.isEmptyDescription(descriptionArray)) {
            error += "Description must be not empty <br/>";
        }
        if (dDao.isExistPhoneNumber(telephone)) {
            error += "This phone number is already registered <br/>";
        }
        if (!openTime.matches(REGEX_OPENTIME)) {
            error += "Invalid open time's format (HH:mm - HH:mm) <br/>";
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

                insertMsg = "Add new department " + departmentName + " successfully!";

            } else {
                insertMsg = "Add new department " + departmentName + " failed. Please try again!";

            }
            session.setAttribute("actionMsg", insertMsg);
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("window.parent.location.href = 'show-department?page=1';");
            out.println("</script>");
            out.close();
        }
    }

}
