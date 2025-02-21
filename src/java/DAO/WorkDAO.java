/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Employee;
import Model.Work;
import java.util.ArrayList;
import Utils.JDBCUtil;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anhnn
 */
public class WorkDAO implements DAOInterface<Work> {

    @Override
    public ArrayList<Work> selectAll() {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT w.ShiftID, w.WorkDate, e.EmployeeID, e.EmployeeCode, e.FirstName, e.LastName, "
                + "e.BirthDate, e.Gender, e.Tel, e.Address, e.PositionId, e.DepartmentId "
                + "FROM Work w "
                + "JOIN Employee e ON w.EmployeeID = e.EmployeeID "
                + "ORDER BY w.WorkDate, w.ShiftID";

        Map<String, Work> workMap = new HashMap<>();
        try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int shiftId = rs.getInt("ShiftID");
                LocalDate workDate = rs.getDate("WorkDate").toLocalDate();
                String key = shiftId + "_" + workDate;
                Work work = workMap.getOrDefault(key, new Work(shiftId, workDate, new ArrayList<>()));
                Employee employee = new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("EmployeeCode"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getDate("BirthDate").toLocalDate(),
                        rs.getString("Gender"),
                        rs.getString("Tel"),
                        rs.getString("Address"),
                        rs.getInt("PositionId"),
                        rs.getInt("DepartmentId"),
                        rs.getInt("BasicSalary")
                );
                work.getEmployees().add(employee);
                workMap.put(key, work);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(workMap.values());
    }

    @Override
    public Work selectById(Work t) {
        return null;
    }

    @Override
    public boolean insert(Work work) {
        Connection con = JDBCUtil.getConnection();
        String sql = "INSERT INTO Work (EmployeeID, ShiftID, WorkDate) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            for (Employee employee : work.getEmployees()) {
                stmt.setInt(1, employee.getEmployeeId());
                stmt.setInt(2, work.getShiftId());
                stmt.setDate(3, Date.valueOf(work.getWorkDate()));

                stmt.addBatch();
            }
            int[] result = stmt.executeBatch();
            if (result.length > 0) {
                JDBCUtil.closeConnection(con);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(Work t) {
//        Connection con = JDBCUtil.getConnection();
//        String sql = "DELETE FROM Work WHERE WorkID = ?";
//        int result = 0;
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setInt(1, t.getWorkId());
//            result = st.executeUpdate();
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    public boolean deleteByDate(Work t) {
        Connection con = JDBCUtil.getConnection();
        String sql = "DELETE FROM Work WHERE WorkDate = ?";
        int result = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setDate(1, Date.valueOf(t.getWorkDate()));
            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Work t) {
//        Connection con = JDBCUtil.getConnection();
//        String sql = "UPDATE Work SET ShiftID = ?, EmployeeId = ?, WorkDate = ? WHERE WorkID = ?";
//        int result = 0;
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setInt(1, t.getShiftId());
//            st.setInt(2, t.getEmployeeId());
//            st.setDate(3, Date.valueOf(t.getWorkDate()));
//            st.setInt(4, t.getWorkId());
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result > 0;
        return false;
    }

//    public boolean 
}
