/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Ud
 */
public class BonusPenalty {

    private int recordId, employeeId;
    private boolean recordType; //true là thưởng false là phạt
    private double amount;
    private String reason;
    private LocalDate recordDate;

    public BonusPenalty() {
    }

    public BonusPenalty(int recordId, int employeeId, boolean recordType, double amount, String reason, LocalDate recordDate) {
        this.recordId = recordId;
        this.employeeId = employeeId;
        this.recordType = recordType;
        this.amount = amount;
        this.reason = reason;
        this.recordDate = recordDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isRecordType() {
        return recordType;
    }

    public void setRecordType(boolean recordType) {
        this.recordType = recordType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

}
