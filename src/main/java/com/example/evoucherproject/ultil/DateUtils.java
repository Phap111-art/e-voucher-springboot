package com.example.evoucherproject.ultil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date stringToDate(){
        return null;
    }

    public static Date currentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 11/3/2024
        calendar.add(Calendar.DATE, 3);
        return calendar.getTime(); // 14/3/2024
    }

    public static boolean isChristmas(LocalDate date) {
        return date.getMonth() == Month.DECEMBER && date.getDayOfMonth() == 25;
    }

    public static boolean isNewYear(LocalDate date) {
        return date.getMonth() == Month.JANUARY && date.getDayOfMonth() == 1;
    }

    public static boolean isGrandOpening(LocalDate date) {
        return date.getMonth() == Month.OCTOBER && date.getDayOfMonth() == 20;
    }
}
