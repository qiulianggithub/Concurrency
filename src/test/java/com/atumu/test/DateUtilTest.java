package com.atumu.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;

/**
 * 测试SimpleDateFormat线程安全问题
 * User: zhanglin
 * Date: 2016/3/20
 * Time: 14:07
 */
public class DateUtilTest {

    public static class TestSimpleDateFormatThreadSafe extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName() + ":" + DateUtil.parse("2016-03-20 14:07:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            //new TestSimpleDateFormatThreadSafe().start();
        }

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        System.out.printf(DateTime.parse("2016-03-20 14:07:20", format).toString());
    }
}
