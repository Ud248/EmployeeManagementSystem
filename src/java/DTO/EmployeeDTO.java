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
    private String gender, tel, address;
    private String positionName, departmentName;
    private int positionId, departmentId;
    private int basicSalary;
    private String username, password;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeCode, String fullname, String tel, String positionName, String departmentName) {
        this.employeeCode = employeeCode;
        this.fullname = fullname;
        this.tel = tel;
        this.positionName = positionName;
        this.departmentName = departmentName;
    }

    public EmployeeDTO(String employeeCode, String fullname, LocalDate birthDate, String gender, String tel, String address, String positionName, String departmentName, int positionId, int departmentId, int basicSalary, String username, String password) {
        this.employeeCode = employeeCode;
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.tel = tel;
        this.address = address;
        this.positionName = positionName;
        this.departmentName = departmentName;
        this.positionId = positionId;
        this.departmentId = departmentId;
        this.basicSalary = basicSalary;
        this.username = username;
        this.password = password;
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

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPositionId() {
        return positionId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" + "employeeCode=" + employeeCode + ", fullname=" + fullname + ", birthDate=" + birthDate + ", gender=" + gender + ", tel=" + tel + ", address=" + address + ", positionName=" + positionName + ", departmentName=" + departmentName + ", positionId=" + positionId + ", departmentId=" + departmentId + ", basicSalary=" + basicSalary + ", username=" + username + ", password=" + password + '}';
    }   
}
