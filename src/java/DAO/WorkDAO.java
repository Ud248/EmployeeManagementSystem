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

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int workId = rs.getInt("WorkID");
                int employeeId = rs.getInt("EmployeeID");
                int ShiftId = rs.getInt("ShiftID");
                LocalDate workDate = rs.getDate("WorkDate").toLocalDate();
                Work w = new Work(workId, ShiftId, employeeId, workDate);
                list.add(w);
            }
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

    public ArrayList<Work> selectByDate(LocalDate t) {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM Work WHERE WorkDate = ?";
        ArrayList<Work> result = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("sql");
            st.setDate(1, Date.valueOf(t));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int workId = rs.getInt("WorkID");
                int shiftId = rs.getInt("ShiftID");
                int employeeId = rs.getInt("EmployeeID");
                LocalDate workDate = rs.getDate("WorkDate").toLocalDate();
                result.add(new Work(workId, shiftId, employeeId, workDate));
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Work t) {
        Connection con = JDBCUtil.getConnection();
        String sql = "INSERT INTO Work VALUES(?, ?, ?)";
        int result = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getShiftId());
            st.setInt(1, t.getEmployeeId());
            st.setDate(1, Date.valueOf(t.getWorkDate()));
            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean delete(Work t) {
        Connection con = JDBCUtil.getConnection();
        String sql = "DELETE FROM Work WHERE WorkID = ?";
        int result = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getWorkId());
            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(Work t) {
        Connection con = JDBCUtil.getConnection();
        String sql = "UPDATE Work SET ShiftID = ?, EmployeeId = ?, WorkDate = ? WHERE WorkID = ?";
        int result = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getShiftId());
            st.setInt(2, t.getEmployeeId());
            st.setDate(3, Date.valueOf(t.getWorkDate()));
            st.setInt(4, t.getWorkId());
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }
}
