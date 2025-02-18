/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhnn
 */
public class Work {

    private int shiftId;
    private LocalDate workDate;
    private DayOfWeek weekDay;
    List<Integer> employeeId;

    public Work(int shiftId, LocalDate workDate) {
        this.shiftId = shiftId;
        this.employeeId = new ArrayList<>();
        this.workDate = workDate;
        this.weekDay = workDate.getDayOfWeek();
    }

    public Work(int shiftId,  LocalDate workDate, ArrayList<Integer> employeeId) {
        this.shiftId = shiftId;
        this.employeeId = new ArrayList<>();
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.weekDay = workDate.getDayOfWeek();
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public List<Integer> getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(List<Integer> employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }
    
}
