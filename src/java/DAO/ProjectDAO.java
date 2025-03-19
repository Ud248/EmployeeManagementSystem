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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6).toLocalDate(),
                        rs.getDate(7).toLocalDate(),
                        rs.getDate(8).toLocalDate(),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11)));
            }

            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ProjectDTO> selectAllProjectDTO(int page, int itemsPerPage) {
        ArrayList<ProjectDTO> result = new ArrayList<>();
        String sql = "select\n"
                + "p.ProjectCode,\n"
                + "p.ProjectName,\n"
                + "p.Description,\n"
                + "COALESCE(d.DepartmentName, '') AS DepartmentName,\n"
                + "p.StartDate,\n"
                + "p.EndDate,\n"
                + "p.DeadLine,\n"
                + "p.Completion,\n"
                + "p.Budget,\n"
                + "p.Profit\n"
                + "from Project p\n"
                + "left join Department d on p.DepartmentID = d.DepartmentID\n"
                + "ORDER BY p.Completion DESC, p.ProjectCode ASC \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {

            Connection conn = JDBCUtil.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, (page - 1) * itemsPerPage);
            st.setInt(2, itemsPerPage);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ProjectDTO project = new ProjectDTO();

                project.setProjectCode(rs.getString("ProjectCode"));
                project.setProjectName(rs.getString("ProjectName"));
                project.setDescription(rs.getString("Description"));
                project.setDepartmentName(rs.getString("DepartmentName"));
                project.setStartDate(rs.getDate("StartDate").toLocalDate());
                Date endDate = rs.getDate("EndDate");
                if (endDate != null) {
                    project.setEndDate(endDate.toLocalDate());
                }
                Date deadLine = rs.getDate("DeadLine");
                if (deadLine != null) {
                    project.setDeadLine(deadLine.toLocalDate());
                }
                project.setCompletion(rs.getInt("Completion"));
                project.setBudget(rs.getDouble("Budget"));
                project.setProfit(rs.getDouble("Profit"));
                result.add(project);
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
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6).toLocalDate(),
                        rs.getDate(7).toLocalDate(),
                        rs.getDate(8).toLocalDate(),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11));
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ProjectDTO selectByProjectCode(Project p) {
        ProjectDTO result = null;
        String sql = "SELECT \n"
                + "p.ProjectCode, \n"
                + "p.ProjectName, \n"
                + "p.Description, \n"
                + "p.Completion, \n"
                + "p.StartDate, \n"
                + "p.EndDate, \n"
                + "p.DeadLine, \n"
                + "p.Budget, \n"
                + "p.Profit,\n"
                + "COALESCE(d.DepartmentName, '') AS DepartmentName\n"
                + "FROM Project p\n"
                + "LEFT JOIN Department d ON p.DepartmentID = d.DepartmentID\n"
                + "WHERE p.ProjectCode = ?";
        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, p.getProjectCode());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String projectCode = rs.getString("ProjectCode");
                String projectName = rs.getString("ProjectName");
                String description = rs.getString("Description");
                int completion = rs.getInt("Completion");
                LocalDate startDate = rs.getDate("StartDate").toLocalDate();
                Date endDateSql = rs.getDate("EndDate");
                LocalDate endDate = (endDateSql != null) ? endDateSql.toLocalDate() : null;
                LocalDate deadLine = rs.getDate("DeadLine").toLocalDate();
                double budget = rs.getDouble("Budget");
                double profit = rs.getDouble("Profit");
                String departmentName = rs.getString("DepartmentName");
                result = new ProjectDTO(projectCode, projectName, description, departmentName, completion, startDate, endDate, deadLine, budget, profit);
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
            String sql = "INSERT INTO Project(ProjectName, Description, StartDate, DeadLine, Budget, Profit, DepartmentID)\n"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, p.getProjectName());
            st.setString(2, p.getDescription());
            st.setDate(3, Date.valueOf(p.getStartDate()));
            st.setDate(4, Date.valueOf(p.getDeadLine()));
            st.setDouble(5, p.getBudget());
            st.setDouble(6, p.getProfit());
            st.setInt(7, p.getDepartmentId());
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
            String sql = "DELETE from Project WHERE ProjectCode = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, p.getProjectCode());
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
        Date endDate = null;
        try {
            if (p.getEndDate() != null) {
                endDate = Date.valueOf(p.getEndDate());
            }
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE Project\n"
                    + "SET ProjectName = ?, Description = ?, Completion = ?, StartDate = ?, EndDate = ?, DeadLine = ?, Budget = ?, Profit = ?\n"
                    + "WHERE ProjectCode = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, p.getProjectName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getCompletion());
            st.setDate(4, Date.valueOf(p.getStartDate()));
            st.setDate(5, endDate);
            st.setDate(6, Date.valueOf(p.getDeadLine()));
            st.setDouble(7, p.getBudget());
            st.setDouble(8, p.getProfit());
            st.setString(9, p.getProjectCode());
            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public Map<String, Integer> getTotalProjectPerDepartment() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT \n"
                + "    d.DepartmentName, \n"
                + "    COUNT(p.ProjectID) AS TotalCompletedProject\n"
                + "FROM Project p\n"
                + "JOIN Department d ON p.DepartmentID = d.DepartmentID\n"
                + "WHERE p.Completion = 100\n"
                + "GROUP BY d.DepartmentName\n"
                + "UNION \n"
                + "SELECT d.DepartmentName, 0 \n"
                + "FROM Department d\n"
                + "WHERE d.DepartmentID NOT IN (\n"
                + "    SELECT DISTINCT p.DepartmentID \n"
                + "    FROM Project p \n"
                + "    WHERE p.Completion = 100);";
        Connection con = JDBCUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("DepartmentName"), rs.getInt("TotalCompletedProject"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Map<String, Integer> getTotalBudgetPerDepartment() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT \n"
                + "    d.DepartmentName, \n"
                + "    COALESCE(SUM(p.Budget), 0) AS TotalBudget\n"
                + "FROM Department d\n"
                + "LEFT JOIN Project p ON p.DepartmentID = d.DepartmentID\n"
                + "GROUP BY d.DepartmentName;";
        Connection con = JDBCUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("DepartmentName"), rs.getInt("TotalBudget"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Map<String, Integer> getCompletionPerProject() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT \n"
                + "    ProjectCode, \n"
                + "    COALESCE(SUM(Completion), 0) AS TotalCompletion\n"
                + "FROM Project\n"
                + "WHERE Completion < 100 \n"
                + "GROUP BY ProjectCode;";
        Connection con = JDBCUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("ProjectCode"), rs.getInt("TotalCompletion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public Map<String, Double> getTopProfitPerProjectName() {
        Map<String, Double> map = new HashMap<>();
        String sql = "SELECT TOP(5) ProjectName, Profit\n"
                + "FROM Project\n"
                + "ORDER BY Profit DESC;";
        Connection con = JDBCUtil.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("ProjectName"), rs.getDouble("Profit"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
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

    public int getTotalProjectCompleted() {
        String sql = "SELECT COUNT(*) AS TotalCompletedProjects\n"
                + "FROM Project\n"
                + "WHERE Completion = 100;";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalCompletedProjects");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTotalBudget() {
        String sql = "SELECT SUM(Budget) AS TotalBudget\n"
                + "FROM Project;";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("TotalBudget");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTotalProfit() {
        String sql = "SELECT SUM(Profit) AS TotalProfit\n"
                + "FROM Project;";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("TotalProfit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getTopDepByCompletedPro() {
        String sql = "SELECT top(1) d.DepartmentName\n"
                + "FROM Department d\n"
                + "JOIN Project p ON d.DepartmentID = p.DepartmentID\n"
                + "WHERE p.Completion = 100\n"
                + "GROUP BY d.DepartmentName\n"
                + "ORDER BY COUNT(p.ProjectID) DESC;";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("DepartmentName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCountCompletedProByTopDep() {
        String sql = "SELECT COUNT(p.ProjectID) AS CompletedProjects\n"
                + "FROM Project p\n"
                + "WHERE p.Completion = 100\n"
                + "AND p.DepartmentID = (\n"
                + "    SELECT TOP (1) d.DepartmentID\n"
                + "    FROM Department d\n"
                + "    JOIN Project p2 ON d.DepartmentID = p2.DepartmentID\n"
                + "    WHERE p2.Completion = 100\n"
                + "    GROUP BY d.DepartmentID\n"
                + "    ORDER BY COUNT(p2.ProjectID) DESC);";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("CompletedProjects");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
