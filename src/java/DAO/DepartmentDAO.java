/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DepartmentDTO;
import Model.Department;
import Utils.JDBCUtil;
import Utils.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ud
 */
public class DepartmentDAO implements DAOInterface<Department> {

    @Override
    public ArrayList<Department> selectAll() {
        ArrayList<Department> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Department";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int departmentId = rs.getInt("DepartmentID");
                String departmentName = rs.getString("DepartmentName");
                String description = rs.getString("Description");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String telephone = rs.getString("Tel");
                Department d = new Department(departmentId, departmentName, description, startTime, endTime, telephone);
                result.add(d);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Department selectById(Department t) {
        Department result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Department WHERE DepartmentID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getDepartmentId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int departmentId = rs.getInt("DepartmentID");
                String departmentName = rs.getString("DepartmentName");
                String description = rs.getString("Description");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String telephone = rs.getString("Tel");
                result = new Department(departmentId, departmentName, description, startTime, endTime, telephone);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Department t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Department(DepartmentName, Description, Tel, StartTime, EndTime)\n"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getDepartmentName());
            st.setString(2, t.getDescription());
            st.setString(3, t.getTelephone());
            st.setTime(4, Time.valueOf(t.getStartTime()));
            st.setTime(5, Time.valueOf(t.getEndTime()));

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
    public boolean delete(Department t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE FROM Department WHERE DepartmentID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getDepartmentId());

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

    public boolean delete(int id) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM Department WHERE DepartmentID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(Department t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Department\n"
                    + "SET DepartmentName=?, Description=?, StartTime=?, EndTime=?\n"
                    + "WHERE DepartmentID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getDepartmentName());
            st.setString(2, t.getDescription());
            st.setTime(3, Time.valueOf(t.getStartTime()));
            st.setTime(4, Time.valueOf(t.getEndTime()));
            st.setInt(5, t.getDepartmentId());

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

    public List<DepartmentDTO> selectDepartmentsByPage(int page, int itemsPerPage) {
        List<DepartmentDTO> result = new ArrayList<>();
        String sql = "SELECT \n"
                + "	d.DepartmentID, \n"
                + "	d.DepartmentName, \n"
                + "	d.Description, \n"
                + "	d.StartTime, \n"
                + "	d.EndTime, \n"
                + "	COALESCE(CONCAT(e.LastName, ' ', e.FirstName), '') as ManagerName,\n"
                + "	COALESCE(d.Tel, '') Tel,\n"
                + "	COALESCE((SELECT COUNT(*) FROM Employee e WHERE e.DepartmentID = d.DepartmentID), 0) as TotalEmployee,\n"
                + "	COALESCE((SELECT SUM(BasicSalary) FROM Employee e WHERE e.DepartmentID = d.DepartmentID), 0) as CostPerMonth\n"
                + "FROM Department d \n"
                + "LEFT JOIN Employee e ON e.DepartmentID = d.DepartmentID and e.PositionID = 2\n"
                + "ORDER BY d.DepartmentID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, (page - 1) * itemsPerPage);
            st.setInt(2, itemsPerPage);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new DepartmentDTO(
                        rs.getInt("DepartmentID"),
                        rs.getString("DepartmentName"),
                        rs.getString("Description"),
                        rs.getTime("StartTime").toLocalTime(),
                        rs.getTime("EndTime").toLocalTime(),
                        rs.getString("ManagerName"),
                        rs.getString("Tel"),
                        rs.getInt("TotalEmployee"),
                        rs.getDouble("CostPerMonth")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getTotalDepartments() {
        String sql = "SELECT COUNT(*) AS total FROM Department";
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

    public String getDepartmentNameById(int id) {
        String sql = "SELECT DepartmentName FROM Department WHERE DepartmentID = ?";
        Connection con = JDBCUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            String departmentName = rs.getString("DepartmentName");
            JDBCUtil.closeConnection(con);
            return departmentName;
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
