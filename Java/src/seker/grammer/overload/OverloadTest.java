/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.grammer.overload;

import java.io.IOException;


/**
 * 
 * @author liuxinjian
 * @since 2013-2-4
 */
public class OverloadTest {
    
//    public void fun() throws IOException {
//        
//    }
//    
//    public void fun() throws NullPointerException {
//        
//    }
    
    public void fun() throws IOException, NullPointerException {
        
    }

    public static void main(String[] args) {
    }
}
