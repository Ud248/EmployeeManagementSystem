/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.time.LocalTime;

/**
 *
 * @author anhnn
 */
public class DepartmentUtil {

    public static boolean isEmptyDescription(String[] arrayDescription) {
        if (arrayDescription == null) {
            return true;
        }
        for (String s : arrayDescription) {
            if (!s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidOpenTimeValue(LocalTime startTime, LocalTime endTime) {
        if (endTime.isAfter(startTime)) {
            return true;
        }
        return false;
    }

    public static String formatOpenTime(LocalTime startTime, LocalTime endTime) {
        return startTime.toString() + " - " + endTime.toString();
    }

    public static String getDescription(String[] descriptionArray) {
        String description = "";
        for (int i = 0; i < descriptionArray.length; i++) {
            if (!descriptionArray[i].endsWith(".")) {
                descriptionArray[i] += ".";
            }
            if (!descriptionArray[i].isEmpty()) {
                description += descriptionArray[i];
            }
        }
        return description;
    }

    public static LocalTime[] getStartAndEndTime(String openTime) {
        String[] timeStringArray = openTime.split("-");
        LocalTime[] localTimeArray = new LocalTime[2];
        for (int i = 0; i < 2; i++) {
            localTimeArray[i] = LocalTime.parse(timeStringArray[i].trim());
        }
        return localTimeArray;
    }
}
