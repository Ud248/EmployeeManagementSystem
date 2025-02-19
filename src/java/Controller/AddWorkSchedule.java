/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import DAO.WorkDAO;
import Model.Employee;
import Model.Work;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author anhnn
 */
public class AddWorkSchedule extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("scheduleDate");
        LocalDate localDate = LocalDate.parse(date);
        String[] morning = request.getParameterValues("morningShift");
        String[] afternoon = request.getParameterValues("afternoonShift");
        String[] night = request.getParameterValues("nightShift");
        EmployeeDAO e = new EmployeeDAO();
        boolean checkMorning = false;
        boolean checkAfternoon = false;
        boolean checkNight = false;
        WorkDAO w = new WorkDAO();
        ArrayList<Work> works = (ArrayList<Work>) request.getServletContext().getAttribute("works");
        Work work = null;
        ArrayList<Employee> list = new ArrayList<>();
        
        for (String x : morning) {
            int id = Integer.parseInt(x);
            list.add(e.selectById(id));
        }
        work = new Work(1, localDate, list);
        checkMorning = w.insert(work);
        works.add(work);
        list.clear();

        for (String x : afternoon) {
            int id = Integer.parseInt(x);
            list.add(e.selectById(id));
        }
        work = new Work(2, localDate, list);
        checkAfternoon = w.insert(work);
        works.add(work);
        list.clear();

        for (String x : night) {
            int id = Integer.parseInt(x);
            list.add(e.selectById(id));
        }
        work = new Work(3, localDate, list);
        checkNight = w.insert(work);
        works.add(work);
        
        if (checkMorning && checkAfternoon && checkNight) {
            request.getSession().setAttribute("Message", "Thêm lịch làm việc thành công!");
        } else {
            request.getSession().setAttribute("Message", "Thêm lịch làm việc thất bại!");
        }
        request.getServletContext().setAttribute("works", works);
        response.sendRedirect("admin.jsp");
    }

}
