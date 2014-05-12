/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.grammer.bool;

/**
 * @author xinjian.lxj
 *
 */
public class BoolTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new BoolTest().test();
    }
    
    public void test() {
        boolean flag = true;
        for (int i = 0; i < 5; i++) {
            // flag = process() && flag;
            flag = flag && process();
            System.out.println("test(): flag=" + flag);
        }
    }
    
    private boolean process() {
        boolean bRet = false;
        System.out.println("process(): bRet=" + bRet);
        return bRet;
    }

}
