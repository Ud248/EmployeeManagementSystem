package Controller;

import DAO.EmployeeDAO;
import DTO.DepartmentDTO;
import DTO.EmployeeDTO;
import Model.Employee;
import java.io.IOException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "InsertEmployee", urlPatterns = {"/insert-employee"})
public class InsertEmployee extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String error = "";
        EmployeeDAO eDao = new EmployeeDAO();

        String fullname = request.getParameter("fullname");
        String birthdateStr = request.getParameter("birthdate");
        String gender = request.getParameter("gender");
        String tel = request.getParameter("telephone");
        String address = request.getParameter("address");
        int positionId = 0, departmentId = 0, basicSalary = 0;
        HttpSession session = request.getSession();
        String insertMsg = "";

        String firstName = "", lastName = "";
        if (fullname == null || fullname.trim().isEmpty()) {
            error += "Full name is required.<br>";
        } else {
            String[] fullnameArray = fullname.split("\\s+");
            lastName = fullnameArray[0];
            for (int i = 1; i < fullnameArray.length; i++) {
                firstName += fullnameArray[i] + " ";
            }
            firstName = firstName.trim();
        }

        if (birthdateStr == null || birthdateStr.trim().isEmpty()) {
            error += "Birthdate is required.<br>";
        }

        LocalDate birthdate = null;
        try {
            birthdate = LocalDate.parse(birthdateStr);
        } catch (Exception e) {
            error += "Invalid birthdate format.<br>";
        }

        if (gender.equals("None")) {
            error += "Please select your gender.<br>";
        }

        if (tel == null || tel.trim().isEmpty()) {
            error += "Telephone is required.<br>";
        } else if (eDao.isExistPhoneNumber(tel)) {
            error += "This phone number is already registered.<br>";
        }

        if (address == null || address.trim().isEmpty()) {
            error += "Address is required.<br>";
        }

        try {
            positionId = Integer.parseInt(request.getParameter("position"));
        } catch (NumberFormatException e) {
            error += "Please select a valid position.<br>";
        }

        try {
            departmentId = Integer.parseInt(request.getParameter("department"));
        } catch (NumberFormatException e) {
            error += "Please select a valid department.<br>";
        }

        try {
            basicSalary = Integer.parseInt(request.getParameter("basicSalary"));
            if (basicSalary <= 0) {
                error += "Basic salary must be greater than 0.<br>";
            }
        } catch (NumberFormatException e) {
            error += "Please input a valid basic salary.<br>";
        }

        if (eDao.isExistManagerInDepartment(departmentId) && positionId == 2) {
            error += "Selected Department already has manager. One Department can have only one manager.";
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("fullname", fullname);
            request.setAttribute("birthdateStr", birthdateStr);
            request.setAttribute("gender", gender);
            request.setAttribute("tel", tel);
            request.setAttribute("address", address);
            request.setAttribute("positionId", positionId);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("basicSalary", request.getParameter("basicSalary"));
            request.getRequestDispatcher("adminEmployeeInsert.jsp").forward(request, response);
        } else {
            boolean insertResult = eDao.insert(new Employee(firstName, lastName, birthdate, gender, tel, address, positionId, departmentId, basicSalary));

            if (insertResult) {
                int totalEmployee = (int) request.getServletContext().getAttribute("totalEmployee") + 1;
                request.getServletContext().setAttribute("totalEmployee", totalEmployee);

                insertMsg = "Add new employee " + fullname + " successfully!";
            } else {
                insertMsg = "Add new employee " + fullname + " failed! Please try again!";
            }
            session.setAttribute("actionMsg", insertMsg);
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("window.parent.location.href = 'show-employee?page=1';");
            out.println("</script>");
            out.close();
        }
    }
}
