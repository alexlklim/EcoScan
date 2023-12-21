package com.alex.ecoscan.managers;

import android.icu.text.SimpleDateFormat;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateMng{

    public static String getCurrentTimeLikeString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }


    public static String extractHoursAndMinutes(String dateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date;
        try {
            date = inputFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        // Format the Date object to the desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM H:mm", Locale.getDefault());
        String outputDateString = outputFormat.format(date);;
        return outputDateString;
    }
}
