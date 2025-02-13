/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.*;

/**
 *
 * @author Ud
 */
public class JDBCUtil {

    private final static String serverName = "localhost";
    private final static String dbName = "ASM_PRJ301";
    private final static String portNumber = "1433";
    private final static String instance = "";
    private final static String userID = "sa";
    private final static String password = "123";

    public static Connection getConnection() {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userID, password);
        } catch (SQLException ex) {
            System.out.println("Connection error! " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found! " + ex.getMessage());
        }
        return null;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Connection connection = JDBCUtil.getConnection()) {
            if (connection != null) {
                System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
            } else {
                System.out.println("Kết nối không thành công.");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
