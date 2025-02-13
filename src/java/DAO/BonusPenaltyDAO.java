/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.BonusPenalty;
import java.util.ArrayList;
import Utils.JDBCUtil;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author Ud
 */
public class BonusPenaltyDAO implements DAOInterface<BonusPenalty> {

    @Override
    public ArrayList<BonusPenalty> selectAll() {
        ArrayList<BonusPenalty> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM BonusPenalty";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int recordId = rs.getInt("RecordID");
                int employeeId = rs.getInt("EmployeeID");
                boolean recordType = rs.getInt("RecordType") == 1;
                double amount = rs.getDouble("Amount");
                String reason = rs.getString("Reason");
                LocalDate recordDate = rs.getDate("RecordDate").toLocalDate();
                BonusPenalty bp = new BonusPenalty(recordId, employeeId, recordType, amount, reason, recordDate);
                result.add(bp);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public BonusPenalty selectById(BonusPenalty t) {
        BonusPenalty result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM BonusPenalty WHERE RecordID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getRecordId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int recordId = rs.getInt("RecordID");
                int employeeId = rs.getInt("EmployeeID");
                boolean recordType = rs.getInt("RecordType") == 1;
                double amount = rs.getDouble("Amount");
                String reason = rs.getString("Reason");
                LocalDate recordDate = rs.getDate("RecordDate").toLocalDate();
                result = new BonusPenalty(recordId, employeeId, recordType, amount, reason, recordDate);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(BonusPenalty t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO BonusPenalty(EmployeeID, RecordType, Amount, Reason, RecordDate)\n"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setInt(2, t.isRecordType() ? 1 : 0);
            st.setDouble(3, t.getAmount());
            st.setString(4, t.getReason());
            st.setDate(5, Date.valueOf(t.getRecordDate()));

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
    public boolean delete(BonusPenalty t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE FROM BonusPenalty WHERE RecordID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getRecordId());

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
    public boolean update(BonusPenalty t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE BonusPenalty\n"
                    + "SET EmployeeID=?, RecordType=?, Amount=?, Reason=?, RecordDate=?\n"
                    + "WHERE RecordID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setInt(2, t.isRecordType() ? 1 : 0);
            st.setDouble(3, t.getAmount());
            st.setString(4, t.getReason());
            st.setDate(5, Date.valueOf(t.getRecordDate()));
            st.setInt(6, t.getRecordId());

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
