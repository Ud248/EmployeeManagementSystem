/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author nongt
 */
public class ProjectDTO {

    private String projectCode;
    private String projectName;
    private String description;
    private String departmentName;
    private int completion;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate deadLine;
    private double budget;
    private double profit;

    public ProjectDTO() {
    }

    public ProjectDTO(String projectCode, String projectName, String description, String departmentName, int completion, LocalDate startDate, LocalDate endDate, LocalDate deadLine, double budget, double profit) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.description = description;
        this.departmentName = departmentName;
        this.completion = completion;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadLine = deadLine;
        this.budget = budget;
        this.profit = profit;
    }

    public ProjectDTO(String projectCode, String projectName, String departmentName, LocalDate startDate, LocalDate deadLine) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.departmentName = departmentName;
        this.startDate = startDate;
        this.deadLine = deadLine;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    @Override
    public String toString() {
        return "ProjectDTO{" + "projectCode=" + projectCode + ", projectName=" + projectName + ", description=" + description + ", departmentName=" + departmentName + ", completion=" + completion + ", startDate=" + startDate + ", endDate=" + endDate + ", deadLine=" + deadLine + ", budget=" + budget + ", profit=" + profit + '}';
    }

}
