/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Employee;
import Utils.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author Ud
 */
public class EmployeeDAO implements DAOInterface<Employee> {

    @Override
    public ArrayList<Employee> selectAll() {
        ArrayList<Employee> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Employee";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
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
                Employee e = new Employee(employeeId, employeeCode, firstName, lastName, birthDate, gender, tel, address, positionId, departmentId);
                result.add(e);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Employee> selectAll(int page, int itemsPerPage) {
        ArrayList<Employee> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Employee LIMIT ? OFFSET ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, itemsPerPage);
            st.setInt(2, (page - 1) * itemsPerPage);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
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
                Employee e = new Employee(employeeId, employeeCode, firstName, lastName, birthDate, gender, tel, address, positionId, departmentId);
                result.add(e);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getTotalEmployees() {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Employee");
            if (rs.next()) {
                return rs.getInt(1);
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
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Employee WHERE EmployeeID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
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
                result = new Employee(employeeId, employeeCode, firstName, lastName, birthDate, gender, tel, address, positionId, departmentId);
            }

            //B5: Đóng kết nối
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
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Employee(EmployeeCode, FirstName, LastName, BirthDate, Gender, Tel, Address, PositionID, DepartmentID)\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
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

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            result = st.executeUpdate();

            //B4: Xử lý kết quả truy vấn        
            System.out.println("You executed: " + sql);
            System.out.println("There are " + result + " rows affected!");

            //B5: Đóng kết nối
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
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE from Employee WHERE EmployeeID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            result = st.executeUpdate();

            //B4: Xử lý kết quả truy vấn        
            System.out.println("You executed: " + sql);
            System.out.println("There are " + result + " rows affected!");

            //B5: Đóng kết nối
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
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Employee\n"
                    + "SET EmployeeCode=?, FirstName=?, LastName=?, BirthDate=?, Gender=?, Tel=?, Address=?, PositionID=?, DepartmentID=?\n"
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
            st.setInt(10, t.getEmployeeId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            result = st.executeUpdate();

            //B4: Xử lý kết quả truy vấn        
            System.out.println("You executed: " + sql);
            System.out.println("There are " + result + " rows affected!");

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

}
