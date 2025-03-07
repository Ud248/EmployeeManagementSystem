/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author nongt
 */
import java.time.LocalDate;

public class ProjectUtil {

    public static int calculateCompletion(int completedTask, int totalTask) {
        if (totalTask <= 0) {
            return 0;
        }
        int completion = (completedTask * 100) / totalTask;
        return Math.min(completion, 100);
    }

    public static long daysRemaining(LocalDate endDate) {
        if (endDate == null) {
            return -1;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), endDate);
    }

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

    public static boolean isValidProjectDuration(LocalDate startDate, LocalDate endDate) {
        return endDate.isAfter(startDate);
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

    public static boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return !endDate.isBefore(startDate);
    }

    public static String formatStartDate(LocalDate startDate, LocalDate endDate) {
        return startDate.toString() + " - " + endDate.toString();
    }
}
