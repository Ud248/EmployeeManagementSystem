/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Salary;
import java.util.ArrayList;
import java.sql.*;
import Utils.JDBCUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Ud
 */
public class SalaryDAO implements DAOInterface<Salary> {

    @Override
    public ArrayList<Salary> selectAll() {
        ArrayList<Salary> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Salary";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int salaryId = rs.getInt("SalaryID");
                int employeeId = rs.getInt("EmployeeID");
                LocalDate payPeriodStart = rs.getDate("PayPeriodStart").toLocalDate();
                LocalDate payPeriodEnd = rs.getDate("PayPeriodEnd").toLocalDate();
                double netSalary = rs.getDouble("NetSalary");
                String status = rs.getString("Status");
                LocalDateTime createdAt = rs.getTimestamp("CreatedAt").toLocalDateTime();
                Salary s = new Salary(salaryId, employeeId, payPeriodStart, payPeriodEnd, netSalary, status, createdAt);
                result.add(s);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Salary selectById(Salary t) {
        Salary result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Salary WHERE SalaryID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getSalaryId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int salaryId = rs.getInt("SalaryID");
                int employeeId = rs.getInt("EmployeeID");
                LocalDate payPeriodStart = rs.getDate("PayPeriodStart").toLocalDate();
                LocalDate payPeriodEnd = rs.getDate("PayPeriodEnd").toLocalDate();
                double netSalary = rs.getDouble("NetSalary");
                String status = rs.getString("Status");
                LocalDateTime createdAt = rs.getTimestamp("CreatedAt").toLocalDateTime();
                result = new Salary(salaryId, employeeId, payPeriodStart, payPeriodEnd, netSalary, status, createdAt);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Salary t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Salary(EmployeeID, PayPeriodStart, PayPeriodEnd, NetSalary, Status, CreatedAt)\n"
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setDate(2, Date.valueOf(t.getPayPeriodStart()));
            st.setDate(3, Date.valueOf(t.getPayPeriodEnd()));
            st.setDouble(4, t.getNetSalary());
            st.setString(5, t.getStatus());
            st.setTimestamp(6, Timestamp.valueOf(t.getCreatedAt()));

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
    public boolean delete(Salary t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE FROM Salary WHERE SalaryID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getSalaryId());

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
    public boolean update(Salary t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Salary\n"
                    + "SET EmployeeID=?, PayPeriodStart=?, PayPeriodEnd=?, NetSalary=?, Status=?, CreatedAt=?\n"
                    + "WHERE SalaryID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setDate(2, Date.valueOf(t.getPayPeriodStart()));
            st.setDate(3, Date.valueOf(t.getPayPeriodEnd()));
            st.setDouble(4, t.getNetSalary());
            st.setString(5, t.getStatus());
            st.setTimestamp(6, Timestamp.valueOf(t.getCreatedAt()));
            st.setInt(7, t.getSalaryId());

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
