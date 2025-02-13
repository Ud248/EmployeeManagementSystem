/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ud
 */
public class Insurance {

    private int insuranceId, employeeId;
    private String insuranceNumber, type;
    private LocalDate expiryDate;
    private double pricePerMonth;

    public Insurance() {
    }

    public Insurance(int insuranceId, int employeeId, String insuranceNumber, String type, LocalDate expiryDate, double pricePerMonth) {
        this.insuranceId = insuranceId;
        this.employeeId = employeeId;
        this.insuranceNumber = insuranceNumber;
        this.type = type;
        this.expiryDate = expiryDate;
        this.pricePerMonth = pricePerMonth;
    }

    public int getInsuranceID() {
        return insuranceId;
    }

    public void setInsuranceID(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getEmployeeID() {
        return employeeId;
    }

    public void setEmployeeID(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

}
