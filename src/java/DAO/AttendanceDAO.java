/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Attendance;
import java.util.ArrayList;
import java.sql.*;
import Utils.JDBCUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Ud
 */
public class AttendanceDAO implements DAOInterface<Attendance> {

    @Override
    public ArrayList<Attendance> selectAll() {
        ArrayList<Attendance> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Attendance";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int attendanceId = rs.getInt("AttendanceID");
                int employeeId = rs.getInt("EmployeeID");
                LocalDate checkInDate = rs.getDate("CheckInDate").toLocalDate();
                LocalTime checkInTime = rs.getTimestamp("CheckInTime").toLocalDateTime().toLocalTime();
                String status = rs.getString("Status");
                Attendance a = new Attendance(attendanceId, employeeId, checkInDate, checkInTime, status);
                result.add(a);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Attendance selectById(Attendance t) {
        Attendance result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Attendance WHERE AttendanceID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getAttendanceId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int attendanceId = rs.getInt("AttendanceID");
                int employeeId = rs.getInt("EmployeeID");
                LocalDate checkInDate = rs.getDate("CheckInDate").toLocalDate();
                LocalTime checkInTime = rs.getTimestamp("CheckInTime").toLocalDateTime().toLocalTime();
                String status = rs.getString("Status");
                result = new Attendance(attendanceId, employeeId, checkInDate, checkInTime, status);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Attendance t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Attendance(EmployeeID, CheckInDate, CheckInTime, Status)\n"
                    + "VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setDate(2, Date.valueOf(t.getCheckInDate()));
            st.setTimestamp(3, Timestamp.valueOf(t.getCheckInTime().atDate(t.getCheckInDate())));
            st.setString(4, t.getStatus());

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
    public boolean delete(Attendance t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE FROM Attendance WHERE AttendanceID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getAttendanceId());

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
    public boolean update(Attendance t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Attendance\n"
                    + "SET EmployeeID=?, CheckInDate=?, CheckInTime=?, Status=?\n"
                    + "WHERE AttendanceID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setDate(2, Date.valueOf(t.getCheckInDate()));
            st.setTimestamp(3, Timestamp.valueOf(t.getCheckInTime().atDate(t.getCheckInDate())));
            st.setString(4, t.getStatus());
            st.setInt(5, t.getAttendanceId());

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
