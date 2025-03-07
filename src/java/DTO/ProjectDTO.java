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

    private int projectId;
    private String projectName;
    private String description;
    private String departmentName;
    private int completion;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private double profit;

    public ProjectDTO() {
    }

    public ProjectDTO(int projectId, String projectName, String description, String departmentName, int completion, LocalDate startDate, LocalDate endDate, double budget, double profit) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.departmentName = departmentName;
        this.completion = completion;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.profit = profit;
    }

    public ProjectDTO(int projectId, String projectName, String departmentName, LocalDate startDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.departmentName = departmentName;
        this.startDate = startDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

}
