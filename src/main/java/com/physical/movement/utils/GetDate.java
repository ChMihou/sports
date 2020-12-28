package com.physical.movement.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式化类
 */
public class GetDate {
    public static Date getTimeTomorrow(Integer hour) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date date = sdf.parse(sdf.format(c.getTime()));
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        date = sdf.parse(sdf.format(c.getTime()));
        return date;
    }

    public static Date getTimeToday() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(sdf.format(c.getTime()));
        return date;
    }

    public static Date StringToDate(String date) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.parse(date);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getTimeToday());
        String date = "2020-12-25 17:00:00";
        System.out.println(StringToDate(date));
        System.out.println(getTimeTomorrow(1));
    }
}
