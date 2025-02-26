/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ud
 */
public class Employee {

    private int employeeId;
    private String employeeCode, firstName, lastName;
    private LocalDate birthDate;
    private String gender, tel, address;
    private int positionId, departmentId;
    private int basicSalary;

    public Employee() {
    }

    public Employee(String employeeCode) {
        this.employeeCode = employeeCode;
    }
    
    //Use when insert Employee
    public Employee(String firstName, String lastName, LocalDate birthDate, String tel, String address, int positionId, int departmentId, int basicSalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.tel = tel;
        this.address = address;
        this.positionId = positionId;
        this.departmentId = departmentId;
        this.basicSalary = basicSalary;
    }

    //Use when update Employee
    public Employee(String employeeCode, String firstName, String lastName, LocalDate birthDate, String gender, String tel, String address, int positionId, int departmentId, int basicSalary) {
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.tel = tel;
        this.address = address;
        this.positionId = positionId;
        this.departmentId = departmentId;
        this.basicSalary = basicSalary;
    }

    public Employee(int employeeId, String employeeCode, String firstName, String lastName, LocalDate birthDate, String gender, String tel, String address, int positionId, int departmentId, int basicSalary) {
        this.employeeId = employeeId;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.tel = tel;
        this.address = address;
        this.positionId = positionId;
        this.departmentId = departmentId;
        this.basicSalary = basicSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFormattedBirthDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return birthDate.format(formatter);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getFullName() {
        return this.getLastName() + " " + this.getFirstName();
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }
}
