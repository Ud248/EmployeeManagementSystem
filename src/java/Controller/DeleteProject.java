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
        int currentPage = Integer.valueOf(request.getParameter("page"));

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
            int itemsPerPage = (int) request.getServletContext().getAttribute("itemsPerPage");
            int totalProject = (int) request.getServletContext().getAttribute("totalProject") - projectCode.length;

            int totalPage = (int) Math.ceil((double) totalProject / itemsPerPage);

            if (currentPage > totalPage) {
                currentPage = totalPage;
            }

            request.getServletContext().setAttribute("totalProject", totalProject);

            deleteMsg = "Delete project " + String.join(", ", projectCode) + " successfully!";
        } else {
            deleteMsg = "Delete project " + String.join(", ", projectCode) + " failed! Please try again!";
        }

        session.setAttribute("actionMsg", deleteMsg);
        response.sendRedirect("show-project?page=" + currentPage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
