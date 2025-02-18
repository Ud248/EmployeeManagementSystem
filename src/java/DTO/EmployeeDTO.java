/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ud
 */
public class EmployeeDTO {

    private String employeeCode, fullname;
    private LocalDate birthDate;
    private String gender, tel;
    private String positionName, departmentName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeCode, String fullname, LocalDate birthDate, String gender, String tel, String positionName, String departmentName) {
        this.employeeCode = employeeCode;
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.tel = tel;
        this.positionName = positionName;
        this.departmentName = departmentName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFormattedBirthDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return birthDate.format(formatter);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" + "employeeCode=" + employeeCode + ", fullname=" + fullname + ", birthDate=" + birthDate + ", gender=" + gender + ", tel=" + tel + ", positionName=" + positionName + ", departmentName=" + departmentName + '}';
    }
    
    
}
