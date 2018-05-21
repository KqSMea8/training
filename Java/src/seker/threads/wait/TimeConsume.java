/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.threads.wait;

import static seker.threads.wait.WaitTest.*;

/**
 * @author xinjian.lxj
 *
 */
public class TimeConsume implements Runnable {
    private final Object lock;
    private final long millis;
    
    public TimeConsume(Object lock, long millis) {
        this.lock = lock;
        this.millis = millis;
    }

    @Override
    public void run() {
        System.out.println("Time: " + _TIME_() + " Thread:" + Thread.currentThread().getName() + " begin.");
        
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Time: " + _TIME_() + " Thread:" + Thread.currentThread().getName() + " end.");
        synchronized (lock) {
            lock.notifyAll();
        }

    }

}
