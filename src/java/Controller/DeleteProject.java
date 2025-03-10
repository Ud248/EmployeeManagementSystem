package Controller;

import DAO.ProjectDAO;
import DTO.DepartmentDTO;
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
        String projectCodeParam = request.getParameter("projectCode");
        String[] projectCode = projectCodeParam.split(",");
        ProjectDAO pDAO = new ProjectDAO();
        boolean deleteResult = true;
        for (String pCode : projectCode) {
            if (!pDAO.delete(new Project(pCode))) {
                deleteResult = false;
            }
        }
        HttpSession session = request.getSession();
        String deleteMsg = "";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        if (deleteResult) {
            int totalProject = (int) request.getServletContext().getAttribute("totalProject") - projectCode.length;
            request.getServletContext().setAttribute("totalProject", totalProject);

            int totalPagesPro = (int) Math.ceil((double) totalProject / 10);

            session.setAttribute("totalPagesPro", totalPagesPro);
            session.setAttribute("currentPagePro", 1);

            List<ProjectDTO> projects = pDAO.selectAllProjectDTO(1, 10);
            List<DepartmentDTO> departments = new DAO.DepartmentDAO().selectDepartmentsByPage(1, 10);
            request.getServletContext().setAttribute("projects", projects);
            request.getServletContext().setAttribute("departments", departments);

            deleteMsg = "Delete project " + String.join(", ", projectCode) + " successfully!";
        } else {
            deleteMsg = "Delete project " + String.join(", ", projectCode) + " failed! Please try again!";
        }

        session.setAttribute("actionMsg", deleteMsg);
        response.sendRedirect("admin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
