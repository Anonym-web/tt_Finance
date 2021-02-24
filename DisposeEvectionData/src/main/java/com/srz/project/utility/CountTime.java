package com.srz.project.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CountTime {
    // 计算小时
    public static long countHour(String time1, String time2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (format.parse(time2).getTime() - format.parse(time1).getTime()) / (1000 * 60 * 60);
    }

    // 计算天数（并加入逻辑判断）
    public static long countDay(String time1, String time2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if ((format.parse(time2).getTime() - format.parse(time1 + 2).getTime()) / (1000 * 60 * 60 * 24) >= 2)
            return (format.parse(time2).getTime() - format.parse(time1 + 2).getTime()) / (1000 * 60 * 60 * 24) - 1;
        else
            return 0;
    }
}
