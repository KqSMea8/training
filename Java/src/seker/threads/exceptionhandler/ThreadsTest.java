/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.threads.exceptionhandler;

/**
 * @author xinjian.lxj
 *
 */
public class ThreadsTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                double i = 12 / 0;// 抛出异常的地方
                System.out.println(i);
            }
        }, "worker thread").start();
        
        String[] ary = new String[] {"Hello Exception."};
        System.out.println(ary[1]);
    }
    
    
}
