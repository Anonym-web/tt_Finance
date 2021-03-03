package com.anonym.parsedome.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 王培忠
 */
public class DateUtils {

    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
}
