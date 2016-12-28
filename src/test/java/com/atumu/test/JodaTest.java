package com.atumu.test;

import org.joda.time.DateTime;

import java.text.ParseException;

/**
 * 分别测试joda-time和Jdk-DateFormat的并发性
 * User: zhanglin
 * Date: 2016/3/20
 * Time: 14:47
 */
public class JodaTest {

    public static void main(String[] args) {
        /*for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            if (!DateUtil.check1(DateUtil.formatDate(new Date()))) {
                                break;
                            }
                        } catch (ParseException e) {
                            //e.printStackTrace();
                        }
                    }
                }
            }).start();
        }*/


        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            if (!DateUtil.check2(DateTime.now().toString(DateUtil.format))) {
                                break;
                            }
                        } catch (ParseException e) {
                            //e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }
}
