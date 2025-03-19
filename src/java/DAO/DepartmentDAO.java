/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DepartmentDTO;
import Model.Department;
import Utils.JDBCUtil;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ud
 */
public class DepartmentDAO implements DAOInterface<Department> {

    @Override
    public ArrayList<Department> selectAll() {
        ArrayList<Department> result = new ArrayList<>();
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Department";
            PreparedStatement st = con.prepareStatement(sql);

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int departmentId = rs.getInt("DepartmentID");
                String departmentCode = rs.getString("DepartmentCode");
                String departmentName = rs.getString("DepartmentName");
                String description = rs.getString("Description");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String telephone = rs.getString("Tel");
                Department d = new Department(departmentId, departmentCode, departmentName, description, startTime, endTime, telephone);
                result.add(d);
            }

            //B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Department selectById(Department t) {
        Department result = null;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "SELECT * FROM Department WHERE DepartmentID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getDepartmentId());

            //B3: Thực thi câu lệnh sql
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            //B4: Xử lý kết quả truy vấn
            while (rs.next()) {
                int departmentId = rs.getInt("DepartmentID");
                String departmentCode = rs.getString("DepartmentCode");
                String departmentName = rs.getString("DepartmentName");
                String description = rs.getString("Description");
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String telephone = rs.getString("Tel");
                result = new Department(departmentId, departmentCode, departmentName, description, startTime, endTime, telephone);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public DepartmentDTO selectByEmployeeCode(String code) {
        DepartmentDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT \n"
                    + "	d.DepartmentCode, \n"
                    + "	d.DepartmentName, \n"
                    + "	d.Description, \n"
                    + "	d.StartTime, \n"
                    + "	d.EndTime, \n"
                    + "	COALESCE(CONCAT(e.LastName, ' ', e.FirstName), '') as ManagerName,\n"
                    + "	COALESCE(d.Tel, '') Tel,\n"
                    + "	COALESCE((SELECT COUNT(*) FROM Employee e WHERE e.DepartmentID = d.DepartmentID), 0) as TotalEmployee,\n"
                    + "	COALESCE((SELECT SUM(BasicSalary) FROM Employee e WHERE e.DepartmentID = d.DepartmentID), 0) as CostPerMonth\n"
                    + "FROM Department d \n"
                    + "LEFT JOIN Employee e ON e.DepartmentID = d.DepartmentID and e.PositionID = 2\n"
                    + "WHERE d.DepartmentCode = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, code);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = new DepartmentDTO(
                        rs.getString("DepartmentCode"),
                        rs.getString("DepartmentName"),
                        rs.getString("Description"),
                        rs.getTime("StartTime").toLocalTime(),
                        rs.getTime("EndTime").toLocalTime(),
                        rs.getString("ManagerName"),
                        rs.getString("Tel"),
                        rs.getInt("TotalEmployee"),
                        rs.getDouble("CostPerMonth")
                );
            }

            // B5: Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Department t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO Department(DepartmentName, Description, Tel, StartTime, EndTime)\n"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getDepartmentName());
            st.setString(2, t.getDescription());
            st.setString(3, t.getTelephone());
            st.setTime(4, Time.valueOf(t.getStartTime()));
            st.setTime(5, Time.valueOf(t.getEndTime()));

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
    public boolean delete(Department t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "DELETE FROM Department WHERE DepartmentID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getDepartmentId());

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

    public boolean deleteAllByCode(String[] codes) {
        int[] result = null;
        int totalRowsDeleted = 0;
        Connection con = JDBCUtil.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "DELETE FROM Department WHERE DepartmentCode=?";
            PreparedStatement st = con.prepareStatement(sql);
            for (String code : codes) {
                st.setString(1, code);
                st.addBatch();
            }
            result = st.executeBatch();
            for (int row : result) {
                totalRowsDeleted += row;
            }
            if (totalRowsDeleted != codes.length) {
                throw new SQLException();
            }
            con.commit();
            st.close();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            try {
                con.rollback();
                return false;
            } catch (SQLException ex) {
                Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return totalRowsDeleted == codes.length;
    }

    @Override
    public boolean update(Department t) {
        int result = 0;
        try {
            //B1: Tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            //B2: Tạo ra đối tượng PreparedStatement
            String sql = "UPDATE Department\n"
                    + "SET DepartmentName=?, Description=?, StartTime=?, EndTime=?, Tel=?\n"
                    + "WHERE DepartmentCode=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getDepartmentName());
            st.setString(2, t.getDescription());
            st.setTime(3, Time.valueOf(t.getStartTime()));
            st.setTime(4, Time.valueOf(t.getEndTime()));
            st.setString(5, t.getTelephone());
            st.setString(6, t.getDepartmentCode());

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

    public List<DepartmentDTO> selectDepartmentsByPage(int page, int itemsPerPage) {
        List<DepartmentDTO> result = new ArrayList<>();
        String sql = "SELECT \n"
                + "	d.DepartmentCode, \n"
                + "	d.DepartmentName, \n"
                + "	d.Description, \n"
                + "	d.StartTime, \n"
                + "	d.EndTime, \n"
                + "	COALESCE(CONCAT(e.LastName, ' ', e.FirstName), '') as ManagerName,\n"
                + "	COALESCE(d.Tel, '') Tel,\n"
                + "	COALESCE((SELECT COUNT(*) FROM Employee e WHERE e.DepartmentID = d.DepartmentID), 0) as TotalEmployee,\n"
                + "	COALESCE((SELECT SUM(BasicSalary) FROM Employee e WHERE e.DepartmentID = d.DepartmentID), 0) as CostPerMonth\n"
                + "FROM Department d \n"
                + "LEFT JOIN Employee e ON e.DepartmentID = d.DepartmentID and e.PositionID = 2\n"
                + "ORDER BY d.DepartmentID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, (page - 1) * itemsPerPage);
            st.setInt(2, itemsPerPage);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new DepartmentDTO(
                        rs.getString("DepartmentCode"),
                        rs.getString("DepartmentName"),
                        rs.getString("Description"),
                        rs.getTime("StartTime").toLocalTime(),
                        rs.getTime("EndTime").toLocalTime(),
                        rs.getString("ManagerName"),
                        rs.getString("Tel"),
                        rs.getInt("TotalEmployee"),
                        rs.getDouble("CostPerMonth")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getTotalDepartments() {
        String sql = "SELECT COUNT(*) AS total FROM Department";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getDepartmentNameByCode(String code) {
        String sql = "SELECT DepartmentName FROM Department WHERE DepartmentCode = ?";
        Connection con = JDBCUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            String departmentName = rs.getString("DepartmentName");
            JDBCUtil.closeConnection(con);
            return departmentName;
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isExistPhoneNumber(String tel) {
        String sql = "SELECT 1 FROM Department WHERE Tel = ?";
        boolean isExist = false;
        Connection con = JDBCUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tel);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                isExist = true;
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }
    
    public boolean isExistPhoneNumber(String tel, String DepartmentCode) {
        String sql = "SELECT 1 FROM Department WHERE Tel = ? and DepartmentCode != ?";
        boolean isExist = false;
        Connection con = JDBCUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tel);
            st.setString(2, DepartmentCode);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                isExist = true;
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }

}
