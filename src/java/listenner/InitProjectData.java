/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listenner;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import Model.Department;
import Model.Position;
import java.util.List;

@WebListener
public class InitProjectData implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Position> listPosition = new DAO.PositionDAO().selectAll();
        List<Department> listDepartment = new DAO.DepartmentDAO().selectAll();

        int itemsPerPage = 10;

        int totalEmployee = new DAO.EmployeeDAO().getTotalEmployees();
        int totalDepartment = new DAO.DepartmentDAO().getTotalDepartments();
        int totalProject = new DAO.ProjectDAO().getTotalProjects();

        sce.getServletContext().setAttribute("listPosition", listPosition);
        sce.getServletContext().setAttribute("listDepartment", listDepartment);

        sce.getServletContext().setAttribute("itemsPerPage", itemsPerPage);

        sce.getServletContext().setAttribute("totalEmployee", totalEmployee);
        sce.getServletContext().setAttribute("totalDepartment", totalDepartment);
        sce.getServletContext().setAttribute("totalProject", totalProject);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
