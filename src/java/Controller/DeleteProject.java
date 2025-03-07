package Controller;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import Model.Project;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author nongt
 */
@WebServlet(name = "DeleteProject", urlPatterns = {"/delete-project"})

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
            int currentPage = 1;
            int itemsPerPage = 10;

            try {
                String pageParam = request.getParameter("page");
                if (pageParam != null && !pageParam.isEmpty()) {
                    currentPage = Integer.parseInt(pageParam);
                }
            } catch (NumberFormatException e) {
                // Use default value
            }

            int totalProjects = (int) request.getServletContext().getAttribute("totalProject") - projectIds.length;
            int totalPagesProject = (int) Math.ceil((double) totalProjects / 10);

            if (currentPage < 1) {
                currentPage = 1;
            }
            if (currentPage > totalPagesProject) {
                currentPage = totalPagesProject;
            }

//            session.setAttribute("currentPagePro", 1);
//            session.setAttribute("totalPagesPro", totalPagesProject);
            List<ProjectDTO> projects = pDAO.selectAllProjectDTO(currentPage, itemsPerPage);
            // List<Project> listProject = pDAO.selectAll();
            request.getServletContext().setAttribute("projects", projects);
            //request.getServletContext().setAttribute("listProject", listProject);
            session.setAttribute("totalPagesPro", totalPagesProject);
            deleteMsg = "Delete project " + String.join(", ", projectIds) + " successfully!";

        } else {
            deleteMsg = "Delete project " + String.join(", ", projectIds) + " failed! Please try again!";
        }

        session.setAttribute("actionMsg", deleteMsg);
        response.sendRedirect("admin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
