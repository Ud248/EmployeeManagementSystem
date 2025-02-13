/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utils.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import Model.Insurance;

/**
 *
 * @author Ud
 */
public class InsuranceDAO implements DAOInterface<Insurance> {

    @Override
    public ArrayList<Insurance> selectAll() {
        ArrayList<Insurance> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Insurance";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int insuranceId = rs.getInt("InsuranceID");
                int employeeId = rs.getInt("EmployeeID");
                String insuranceNumber = rs.getString("InsuranceNumber");
                String type = rs.getString("Type");
                LocalDate expiryDate = rs.getDate("ExpiryDate").toLocalDate();
                double pricePerMonth = rs.getDouble("PricePerMonth");
                Insurance i = new Insurance(insuranceId, employeeId, insuranceNumber, type, expiryDate, pricePerMonth);
                result.add(i);

            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Insurance selectById(Insurance t) {
        Insurance result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Insurance WHERE InsuranceID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getInsuranceID());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int insuranceId = rs.getInt("InsuranceID");
                int employeeId = rs.getInt("EmployeeID");
                String insuranceNumber = rs.getString("InsuranceNumber");
                String type = rs.getString("Type");
                LocalDate expiryDate = rs.getDate("ExpiryDate").toLocalDate();
                double pricePerMonth = rs.getDouble("PricePerMonth");
                result = new Insurance(insuranceId, employeeId, insuranceNumber, type, expiryDate, pricePerMonth);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Insurance t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Insurance(EmployeeID, InsuranceNumber, Type, ExpiryDate, PricePerMonth)\n"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeID());
            st.setString(2, t.getInsuranceNumber());
            st.setString(3, t.getType());
            st.setDate(4, Date.valueOf(t.getExpiryDate()));
            st.setDouble(5, t.getPricePerMonth());

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
    public boolean delete(Insurance t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE FROM Insurance WHERE InsuranceID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getInsuranceID());

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
    public boolean update(Insurance t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Insurance\n"
                    + "SET EmployeeID=?, InsuranceNumber=?, Type=?, ExpiryDate=?, PricePerMonth=?\n"
                    + "WHERE InsuranceID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeID());
            st.setString(2, t.getInsuranceNumber());
            st.setString(3, t.getType());
            st.setDate(4, Date.valueOf(t.getExpiryDate()));
            st.setDouble(5, t.getPricePerMonth());
            st.setInt(6, t.getInsuranceID());

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
