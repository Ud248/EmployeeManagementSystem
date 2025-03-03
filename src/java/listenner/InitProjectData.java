/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listenner;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import DTO.DepartmentDTO;
import DTO.EmployeeDTO;
import Model.Department;
import Model.Position;
import java.util.List;

@WebListener
public class InitProjectData implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Position> listPosition = new DAO.PositionDAO().selectAll();
        List<Department> listDepartment = new DAO.DepartmentDAO().selectAll();
        List<EmployeeDTO> employees = new DAO.EmployeeDAO().selectEmployeesByPage(1, 10);
        List<DepartmentDTO> departments = new DAO.DepartmentDAO().selectDepartmentsByPage();
        sce.getServletContext().setAttribute("listPosition", listPosition);
        sce.getServletContext().setAttribute("listDepartment", listDepartment);
        sce.getServletContext().setAttribute("employees", employees);
        sce.getServletContext().setAttribute("departments", departments);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
