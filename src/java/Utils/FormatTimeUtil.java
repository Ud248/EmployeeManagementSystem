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
public class FormatTimeUtil {

    public static String formatOpenTime(LocalTime startTime, LocalTime endTime) {
        return startTime.toString() + " - " + endTime.toString();
    }
}
