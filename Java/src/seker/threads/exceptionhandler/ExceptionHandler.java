/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.threads.exceptionhandler;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @author xinjian.lxj
 * 
 */
public class ExceptionHandler implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.out.println("This thread is:" + Thread.currentThread().getName() + ", The exception thread is:" + thread.getName() + ", Message:" + throwable.getMessage());
    }
}
