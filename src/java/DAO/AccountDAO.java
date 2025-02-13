/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Utils.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Ud
 */
public class AccountDAO implements DAOInterface<Account> {

    @Override
    public ArrayList<Account> selectAll() {
        ArrayList<Account> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Account";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int accountId = rs.getInt("AccountID");
                int employeeId = rs.getInt("EmployeeID");
                String username = rs.getString("Username");
                String password = rs.getString("[Password]");
                boolean isAdmin = rs.getInt("IsAdmin") == 1;
                Account a = new Account(accountId, employeeId, username, password, isAdmin);
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
    public Account selectById(Account t) {
        Account result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Account WHERE AccountID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getAccountId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int accountId = rs.getInt("AccountID");
                int employeeId = rs.getInt("EmployeeID");
                String username = rs.getString("Username");
                String password = rs.getString("[Password]");
                boolean isAdmin = rs.getInt("IsAdmin") == 1;
                result = new Account(accountId, employeeId, username, password, isAdmin);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Account t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Account(EmployeeID, Username, Password, IsAdmin)\n"
                    + "VALUES(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setString(2, t.getUsername());
            st.setString(3, t.getPassword());
            st.setInt(4, t.isIsAdmin() ? 1 : 0);

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
    public boolean delete(Account t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE from Account WHERE AccountID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getAccountId());

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
    public boolean update(Account t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Account\n"
                    + "SET EmployeeID=?, Username=?, Password=?, IsAdmin=?\n"
                    + "WHERE AccountID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getEmployeeId());
            st.setString(2, t.getUsername());
            st.setString(3, t.getPassword());
            st.setInt(4, t.isIsAdmin() ? 1 : 0);
            st.setInt(5, t.getAccountId());

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
