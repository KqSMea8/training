/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.threads.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xinjian.lxj
 *
 */
public class FixedThreadPoolTest {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executor =  Executors.newFixedThreadPool(2);
        
        
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        });
        
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        });
        
        System.gc();
        System.gc();
        executor.shutdown();
        System.out.println("executor.shutdown()");
        
        System.gc();
        System.gc();
        System.out.println("end.");
    }
    
}
