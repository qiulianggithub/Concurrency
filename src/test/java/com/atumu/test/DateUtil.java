package com.atumu.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试SimpleDateFormat线程安全问题
 * User: zhanglin
 * Date: 2016/3/20
 * Time: 14:06
 */
public class DateUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss:SSS");

    public static boolean check1(String time) {
        String s = DateTime.parse(time, format).toString("yyyy-MM-dd HH:mm:ss:SSS");
        boolean b = s.equalsIgnoreCase(time);
        if (!b) {
            System.out.printf("time:%s - s:%s\n", time, s);
        }
        return b;
    }

    public static boolean check2(String time) throws ParseException {
        String s = formatDate(parse(time));
        boolean b = s.equalsIgnoreCase(time);
        if (!b) {
            System.out.printf("time:%s - s:%s\n", time, s);
        }
        return b;
    }

    public static String formatDate(Date date) throws ParseException {
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException {

        return sdf.parse(strDate);
    }
}
