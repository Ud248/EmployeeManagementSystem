/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Shift;
import Utils.JDBCUtil;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author anhnn
 */
public class ShiftDAO implements DAOInterface<Shift> {
    
    @Override
    public ArrayList<Shift> selectAll() {
        ArrayList<Shift> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Shift";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int shiftId = rs.getInt("ShiftID");
                String shiftName = rs.getString("ShiftName");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                Shift a = new Shift(shiftId, shiftName, startTime, endTime);
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
    public Shift selectById(Shift t) {
        Shift result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Shift WHERE ShiftID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getShiftId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int shiftId = rs.getInt("ShiftID");
                String shiftName = rs.getString("ShiftName");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                result = new Shift(shiftId, shiftName, startTime, endTime);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean insert(Shift t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Shift(ShiftName, StartTime, EndTime)\n"
                    + "VALUES(?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getShiftName());
            st.setTime(2, Time.valueOf(t.getStartTime()));
            st.setTime(3, Time.valueOf(t.getEndTime()));

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
    public boolean delete(Shift t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE from Shift WHERE ShiftID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getShiftId());

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
    public boolean update(Shift t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Shift\n"
                    + "SET ShiftName=?, StartTime=?, EndTime=?\n"
                    + "WHERE ShiftID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getShiftName());
            st.setTime(2, Time.valueOf(t.getStartTime()));
            st.setTime(3, Time.valueOf(t.getEndTime()));
            st.setInt(4, t.getShiftId());

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
