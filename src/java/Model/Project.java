/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author nongt
 */
public class Project {

    private int projectId;
    private String projectCode;
    private String projectName;
    private String description;
    private int completion;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate deadLine;
    private double budget;
    private double profit;
    private int departmentId;

    public Project() {
    }

    public Project(String projectCode) {
        this.projectCode = projectCode;
    }

    public Project(int projectId, String projectCode, String projectName, String description, int completion, LocalDate startDate, LocalDate endDate, LocalDate deadLine, double budget, double profit, int departmentId) {
        this.projectId = projectId;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.description = description;
        this.completion = completion;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadLine = deadLine;
        this.budget = budget;
        this.profit = profit;
        this.departmentId = departmentId;
    }

    //insert
    public Project(String projectName, String description, LocalDate startDate, LocalDate deadLine, double budget, double profit, int departmentId) {
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.budget = budget;
        this.profit = profit;
        this.departmentId = departmentId;
    }

    //update
    public Project(String projectCode, String projectName, String description, int completion, LocalDate startDate, LocalDate deadLine, double budget, double profit) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.description = description;
        this.completion = completion;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.budget = budget;
        this.profit = profit;
    }

    //report
    public Project(String projectName, int completion, double budget, double profit) {
        this.projectName = projectName;
        this.completion = completion;
        this.budget = budget;
        this.profit = profit;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Project{" + "projectId=" + projectId + ", projectCode=" + projectCode + ", projectName=" + projectName + ", description=" + description + ", completion=" + completion + ", startDate=" + startDate + ", endDate=" + endDate + ", deadLine=" + deadLine + ", budget=" + budget + ", profit=" + profit + ", departmentId=" + departmentId + '}';
    }

}
