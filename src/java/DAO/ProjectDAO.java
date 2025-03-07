/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ProjectDTO;
import Model.Project;
import Utils.JDBCUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nongt
 */
public class ProjectDAO implements DAOInterface<Project> {

    @Override
    public ArrayList<Project> selectAll() {
        ArrayList<Project> result = new ArrayList<>();
        String sql = "select * from Project";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Project(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getDate(6).toLocalDate(),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getInt(9)));
            }

            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ProjectDTO> selectAllProjectDTO(int page, int itemsPerPage) {
        ArrayList<ProjectDTO> result = new ArrayList<>();
        String sql = "select p.ProjectID, p.ProjectName, d.DepartmentName, p.StartDate\n"
                + "from Project p\n"
                + "left join Department d on p.DepartmentID = d.DepartmentID\n"
                + "ORDER BY p.ProjectID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {

            Connection conn = JDBCUtil.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, (page - 1) * itemsPerPage);
            st.setInt(2, itemsPerPage);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result.add(new ProjectDTO(rs.getInt("ProjectID"),
                        rs.getString("ProjectName"),
                        rs.getString("DepartmentName"),
                        rs.getDate("StartDate").toLocalDate()));
            }

            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Project selectById(Project t) {
        Project result = null;
        String sql = "SELECT * FROM Department WHERE ProjectID=?";
        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getProjectId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = new Project(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getDate(6).toLocalDate(),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getInt(9));
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Project p) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO Project(ProjectName, Description, StartDate, Budget)\n"
                    + "VALUES(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, p.getProjectName());
            st.setString(2, p.getDescription());
            st.setDate(3, Date.valueOf(p.getStartDate()));
            st.setDouble(4, p.getBudget());
            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean delete(Project p) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE from Project WHERE ProjectID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, p.getProjectId());
            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(Project p) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE Project\n"
                    + "SET ProjectName = ?, Description = ?, Completion = ?, StartDate = ?, EndDate = ?, Budget = ?, Profit = ?, DepartmentID = ?\n"
                    + "WHERE ProjectID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, p.getProjectName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getCompletion());
            st.setDate(4, Date.valueOf(p.getStartDate()));
            st.setDate(5, Date.valueOf(p.getEndDate()));
            st.setDouble(6, p.getBudget());
            st.setDouble(7, p.getProfit());
            st.setInt(8, p.getDepartmentId());
            st.setInt(9, p.getProjectId());
            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public int getTotalProjects() {
        String sql = "SELECT COUNT(*) AS total FROM Project";
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
}
