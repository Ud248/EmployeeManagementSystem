/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
        String sql = "SELECT * FROM Work";
        ArrayList<Work> list = new ArrayList<>();
        int employeeId = 0;
        int ShiftId = 0;
        LocalDate workDate = null;
        List<Object> key = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
        Map<List<Object>, List<Integer>> map = new HashMap<>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                employeeId = rs.getInt("EmployeeID");
                ShiftId = rs.getInt("ShiftID");
                workDate = rs.getDate("WorkDate").toLocalDate();
                key.add(ShiftId);
                key.add(workDate);
                if (!map.containsKey(key)) {
                    value.add(employeeId);
                    map.put(key, value);
                } else {
                    value = map.get(key);
                    value.add(employeeId);
                    map.put(key, value);
                }
                key.clear();
                value.clear();
            }
            map.forEach((k, v) -> {
                list.add(new Work((int) k.get(0), (LocalDate) k.get(1), (ArrayList<Integer>) v));
            });
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Work selectById(Work t) {
        return null;
    }

//    public ArrayList<Work> selectByDate(LocalDate t) {
//        Connection con = JDBCUtil.getConnection();
//        String sql = "SELECT * FROM Work WHERE WorkDate = ?";
//        ArrayList<Work> result = new ArrayList<>();
//        try {
//            PreparedStatement st = con.prepareStatement("sql");
//            st.setDate(1, Date.valueOf(t));
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                int shiftId = rs.getInt("ShiftID");
//                int employeeId = rs.getInt("EmployeeID");
//                LocalDate workDate = rs.getDate("WorkDate").toLocalDate();
//                result.add(new Work(shiftId, employeeId, workDate));
//            }
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    @Override
    public boolean insert(Work t) {
        Connection con = JDBCUtil.getConnection();
        String sql = "INSERT INTO Work(ShiftID, WorkDate, EmployeeID) VALUES(?, ?, ?)";
        int result = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            for (int i = 0; i < t.getEmployeeId().size(); i++) {
                st.setInt(1, t.getShiftId());
                st.setDate(2, Date.valueOf(t.getWorkDate()));
                st.setInt(3, t.getEmployeeId().get(i));
                result = st.executeUpdate();
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
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
}
