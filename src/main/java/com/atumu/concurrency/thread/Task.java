package com.atumu.concurrency.thread;

/**
 * Created by Administrator on 2016/10/27.
 */
public class Task implements Runnable {

    @Override
    public void run() {
        try{
            int numero=Integer.parseInt("TTT");
        }catch (Exception e){
            System.out.println("抛出异常");
            System.out.println("线程id: " + Thread.currentThread().getId());
            System.out.println("线程status: " + Thread.currentThread().getState());
            e.printStackTrace();
        }


    }

}