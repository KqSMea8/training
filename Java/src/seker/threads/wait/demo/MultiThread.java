/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.threads.wait.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinjian.lxj
 * 
 */
public class MultiThread {
    private List<Object> container = new ArrayList<Object>();
    public final static int MAX = 5;

    public static void main(String args[]) {
        MultiThread m = new MultiThread();
        new Thread(new Consume(m.getContainer())).start();
        new Thread(new Product(m.getContainer())).start();
    }

    public List<Object> getContainer() {
        return container;
    }

    public void setContainer(List<Object> container) {
        this.container = container;
    }
}
