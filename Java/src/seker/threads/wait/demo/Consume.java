/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.threads.wait.demo;

import java.util.List;

/**
 * @author xinjian.lxj
 * 
 */
public class Consume implements Runnable {
    private List<Object> container = null;
    private int count;

    public Consume(List<Object> lst) {
        this.container = lst;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (container) {
                if (container.size() == 0) {
                    try {
                        container.wait();// 容器为空，放弃锁，等待生产
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                container.remove(0);
                container.notify();
                System.out.println("我吃了" + (++count) + "个");
            }
        }
    }
}
