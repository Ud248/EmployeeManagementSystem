/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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

/**
 *
 * @author anhnn
 */
public class ViewDepartment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departmentId = request.getParameter("departmentId");
        int index = Integer.parseInt(departmentId) - 1;

        DepartmentDTO dep = ((ArrayList<DepartmentDTO>) request.getServletContext().getAttribute("departments")).get(index);

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
        request.getRequestDispatcher("adminDepartmentView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
