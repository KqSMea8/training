/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.net.single;

import java.net.URL;


/**
 * 
 * @author seker
 * @since 2013年11月22日
 */
public class UrlRequestRunnable implements Runnable{
    @Override
    public void run() {
        try {
            new URL("http://www.cqwu.edu.cn");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
