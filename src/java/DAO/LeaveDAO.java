/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Leave;
import java.util.ArrayList;
import Utils.JDBCUtil;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Ud
 */
public class LeaveDAO implements DAOInterface<Leave> {

    @Override
    public ArrayList<Leave> selectAll() {
        ArrayList<Leave> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Leave";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int leaveId = rs.getInt("LeaveID");
                int employeeId = rs.getInt("EmployeeID");
                String leaveType = rs.getString("LeaveType");
                LocalDate startDate = rs.getDate("StartDate").toLocalDate();
                LocalDate endDate = rs.getDate("EndDate").toLocalDate();
                int totalDay = rs.getInt("TotalDay");
                String reason = rs.getString("Reason");
                String status = rs.getString("Status");
                LocalDateTime createdAt = rs.getTimestamp("CreatedAt").toLocalDateTime();
                Leave l = new Leave(leaveId, employeeId, leaveType, startDate, endDate, totalDay, reason, status, createdAt);
                result.add(l);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Leave selectById(Leave t) {
        Leave result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Leave WHERE LeaveID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getLeaveId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int leaveId = rs.getInt("LeaveID");
                int employeeId = rs.getInt("EmployeeID");
                String leaveType = rs.getString("LeaveType");
                LocalDate startDate = rs.getDate("StartDate").toLocalDate();
                LocalDate endDate = rs.getDate("EndDate").toLocalDate();
                int totalDay = rs.getInt("TotalDay");
                String reason = rs.getString("Reason");
                String status = rs.getString("Status");
                LocalDateTime createdAt = rs.getTimestamp("CreatedAt").toLocalDateTime();
                result = new Leave(leaveId, employeeId, leaveType, startDate, endDate, totalDay, reason, status, createdAt);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Leave t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Leave(EmployeeID, LeaveType, StartDate, EndDate, TotalDay, Reason, Status, CreatedAt)\n"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setString(2, t.getLeaveType());
            st.setDate(3, Date.valueOf(t.getStartDate()));
            st.setDate(4, Date.valueOf(t.getEndDate()));
            st.setInt(5, t.getTotalDay());
            st.setString(6, t.getReason());
            st.setString(7, t.getStatus());
            st.setTimestamp(8, Timestamp.valueOf(t.getCreatedAt()));

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
    public boolean delete(Leave t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE FROM Leave WHERE LeaveID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getLeaveId());

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
    public boolean update(Leave t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Leave\n"
                    + "SET EmployeeID=?, LeaveType=?, StartDate=?, EndDate=?, TotalDay=?, Reason=?, Status=?, CreatedAt=?\n"
                    + "WHERE LeaveID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setString(2, t.getLeaveType());
            st.setDate(3, Date.valueOf(t.getStartDate()));
            st.setDate(4, Date.valueOf(t.getEndDate()));
            st.setInt(5, t.getTotalDay());
            st.setString(6, t.getReason());
            st.setString(7, t.getStatus());
            st.setTimestamp(8, Timestamp.valueOf(t.getCreatedAt()));
            st.setInt(9, t.getLeaveId());

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
