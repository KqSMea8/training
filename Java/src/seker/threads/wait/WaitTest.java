/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.threads.wait;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xinjian.lxj
 *
 */
public class WaitTest {
    
    private static final long SECOND = 1000;
    
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    public static String _TIME_() {
        return sdf.format(new Date());
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        Object lock = new Object();
        
        System.out.println("Time: " + _TIME_() + " Thread:" + Thread.currentThread().getName() + " begin.");
        synchronized (lock) {
            //new Thread(new TimeConsume(lock, SECOND), "Thread-1").start();
            //new Thread(new TimeConsume(lock, SECOND * 3), "Thread-3").start();
            new Thread(new TimeConsume(lock, SECOND * 6), "Thread-6").start();
            
            try {
                lock.wait(SECOND * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Time: " + _TIME_() + " Thread:" + Thread.currentThread().getName() + " end.");
    }
}
