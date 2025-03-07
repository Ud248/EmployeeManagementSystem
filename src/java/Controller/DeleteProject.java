package Controller;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import Model.Project;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author nongt
 */
public class DeleteProject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectIdParam = request.getParameter("projectId");
        String[] projectIds = projectIdParam.split(",");
        ProjectDAO pDAO = new ProjectDAO();
        boolean deleteResult = true;
        String deleteMsg = "";
        HttpSession session = request.getSession();

        for (String id : projectIds) {
            if (!pDAO.delete(new Project(Integer.parseInt(id), "", "", 0, null, null, 0, 0, 0))) {
                deleteResult = false;
            }
        }

        if (deleteResult) {
            int totalProjects = (int) request.getServletContext().getAttribute("totalProjects") - projectIds.length;
            int totalPagesProject = (int) Math.ceil((double) totalProjects / 10);

            session.setAttribute("currentPageProject", 1);
            session.setAttribute("totalPagesProject", totalPagesProject);

            deleteMsg = "Deleted project(s) " + String.join(", ", projectIds) + " successfully!";

            List<ProjectDTO> projects = pDAO.selectProjectsByPage(1, 10);
            List<Project> listProject = pDAO.selectAll();
            request.getServletContext().setAttribute("projects", projects);
            request.getServletContext().setAttribute("listProject", listProject);

        } else {
            deleteMsg = "Delete project(s) " + String.join(", ", projectIds) + " failed! Please try again!";
        }

        session.setAttribute("actionMsg", deleteMsg);
        response.sendRedirect("admin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
