/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.EmployeeDTO;
import Model.Employee;
import Utils.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ud
 */
public class EmployeeDAO implements DAOInterface<Employee> {

    @Override
    public ArrayList<Employee> selectAll() {
        ArrayList<Employee> result = new ArrayList<>();
        try {
            // B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Employee";
            PreparedStatement st = con.prepareStatement(sql);

            // B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int employeeId = rs.getInt("EmployeeID");
                String employeeCode = rs.getString("EmployeeCode");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                LocalDate birthDate = rs.getDate("BirthDate").toLocalDate();
                String gender = rs.getString("Gender");
                String tel = rs.getString("Tel");
                String address = rs.getString("Address");
                int positionId = rs.getInt("PositionID");
                int departmentId = rs.getInt("DepartmentID");
                int basicSalary = rs.getInt("BasicSalary");
                Employee e = new Employee(employeeId, employeeCode, firstName, lastName, birthDate, gender, tel,
                        address, positionId, departmentId, basicSalary);
                result.add(e);
            }

            // B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<EmployeeDTO> selectEmployeesByPage(int page, int itemsPerPage) {
        List<EmployeeDTO> result = new ArrayList<>();
        String sql = "SELECT e.EmployeeCode, e.LastName + ' ' + e.FirstName AS Fullname, e.Tel, "
                + "p.PositionName AS PositionName, d.DepartmentName AS DepartmentName "
                + "FROM Employee e "
                + "JOIN Position p ON e.PositionID = p.PositionID "
                + "JOIN Department d ON e.DepartmentID = d.DepartmentID "
                + "ORDER BY e.EmployeeCode "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, (page - 1) * itemsPerPage);
            st.setInt(2, itemsPerPage);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new EmployeeDTO(
                        rs.getString("EmployeeCode"),
                        rs.getString("Fullname"),
                        rs.getString("Tel"),
                        rs.getString("PositionName"),
                        rs.getString("DepartmentName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getTotalEmployees() {
        String sql = "SELECT COUNT(*) AS total FROM Employee";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Employee selectById(Employee t) {
        Employee result = null;
        try {
            // B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Employee WHERE EmployeeID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());

            // B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int employeeId = rs.getInt("EmployeeID");
                String employeeCode = rs.getString("EmployeeCode");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                LocalDate birthDate = rs.getDate("BirthDate").toLocalDate();
                String gender = rs.getString("Gender");
                String tel = rs.getString("Tel");
                String address = rs.getString("Address");
                int positionId = rs.getInt("PositionID");
                int departmentId = rs.getInt("DepartmentID");
                int basicSalary = rs.getInt("BasicSalary");
                result = new Employee(employeeId, employeeCode, firstName, lastName, birthDate, gender, tel,
                        address, positionId, departmentId, basicSalary);
            }

            // B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Employee selectById(int id) {
        Employee result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Employee WHERE EmployeeID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int employeeId = rs.getInt("EmployeeID");
                String employeeCode = rs.getString("EmployeeCode");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                LocalDate birthDate = rs.getDate("BirthDate").toLocalDate();
                String gender = rs.getString("Gender");
                String tel = rs.getString("Tel");
                String address = rs.getString("Address");
                int positionId = rs.getInt("PositionID");
                int departmentId = rs.getInt("DepartmentID");
                int basicSalary = rs.getInt("BasicSalary");
                result = new Employee(employeeId, employeeCode, firstName, lastName, birthDate, gender, tel,
                        address, positionId, departmentId, basicSalary);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Employee t) {
        int result = 0;
        try {
            // B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Employee(FirstName, LastName, BirthDate, Gender, Tel, Address, PositionID, DepartmentID, BasicSalary)\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getFirstName());
            st.setString(2, t.getLastName());
            st.setDate(3, Date.valueOf(t.getBirthDate()));
            st.setString(4, t.getGender());
            st.setString(5, t.getTel());
            st.setString(6, t.getAddress());
            st.setInt(7, t.getPositionId());
            st.setInt(8, t.getDepartmentId());
            st.setInt(9, t.getBasicSalary());

            // B3: Thực thi câu lệnh sql
            System.out.println(sql);
            result = st.executeUpdate();

            // B4: Xử lý kết quả truy vấn
            System.out.println("You executed: " + sql);
            System.out.println("There are " + result + " rows affected!");

            // B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean delete(Employee t) {
        int result = 0;
        try {
            // B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE from Employee WHERE EmployeeCode=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getEmployeeCode());

            // B3: Thực thi câu lệnh sql
            System.out.println(sql);
            result = st.executeUpdate();

            // B4: Xử lý kết quả truy vấn
            System.out.println("You executed: " + sql);
            System.out.println("There are " + result + " rows affected!");

            // B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(Employee t) {
        int result = 0;
        try {
            // B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Employee\n"
                    + "SET EmployeeCode=?, FirstName=?, LastName=?, BirthDate=?, Gender=?, Tel=?, Address=?, PositionID=?, DepartmentID=?, BasicSalary=?\n"
                    + "WHERE EmployeeID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getEmployeeCode());
            st.setString(2, t.getFirstName());
            st.setString(3, t.getLastName());
            st.setDate(4, Date.valueOf(t.getBirthDate()));
            st.setString(5, t.getGender());
            st.setString(6, t.getTel());
            st.setString(7, t.getAddress());
            st.setInt(8, t.getPositionId());
            st.setInt(9, t.getDepartmentId());
            st.setInt(10, t.getBasicSalary());
            st.setInt(11, t.getEmployeeId());

            // B3: Thực thi câu lệnh sql
            System.out.println(sql);
            result = st.executeUpdate();

            // B4: Xử lý kết quả truy vấn
            System.out.println("You executed: " + sql);
            System.out.println("There are " + result + " rows affected!");

            // B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }
}
