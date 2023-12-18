package com.alex.ecoscan.managers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateMng{

    public static String getCurrentTimeLikeString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }


    public static String extractHoursAndMinutes(String date) {
        return null;
    }
}
