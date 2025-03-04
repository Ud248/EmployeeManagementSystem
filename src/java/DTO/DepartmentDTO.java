/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Utils.DepartmentUtil;
import java.time.LocalTime;

/**
 *
 * @author anhnn
 */
public class DepartmentDTO {

    private int departmentId;
    private String departmentName;
    private String description;
    private String openTime;
    private String managerName;
    private String telephone;
    private int totalEmployee;
    private double costPerMonth;

    public DepartmentDTO(int departmentId, String departmentName, String description, LocalTime startTime, LocalTime endTime, String managerName, String telephone, int totalEmployee, double costPerMonth) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
        this.openTime = DepartmentUtil.formatOpenTime(startTime, endTime);
        this.managerName = managerName;
        this.telephone = telephone;
        this.totalEmployee = totalEmployee;
        this.costPerMonth = costPerMonth;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public double getCostPerMonth() {
        return costPerMonth;
    }

    public void setCost(double costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(int totalEmployee) {
        this.totalEmployee = totalEmployee;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

}
