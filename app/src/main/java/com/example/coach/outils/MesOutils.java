package com.example.coach.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {
    /**
     * reçoit une date au format String et la convertit au format Date
     * @param uneDate au format String
     * @param expectedPattern pour formater la date
     * @return date convertie au format Date
     */
    public static Date convertStringToDate(String uneDate, String expectedPattern){
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * reçoit une date au format String et la convertit au format Date avec pattern précis
     * @param uneDate au format String
     * @return date convertie au format Date
     */
    public static Date convertStringToDate(String uneDate){
        String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        return convertStringToDate(uneDate, expectedPattern);
    }

    /**
     * reçoit une date au format Date et la convertit au format String
     * @param uneDate au format Date
     * @return date convertie au format String
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);
    }
}
