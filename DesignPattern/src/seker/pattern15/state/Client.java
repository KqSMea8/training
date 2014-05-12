/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern15.state;

/**
 * 
 * @author liuxinjian
 * @since 2013-3-5
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 创建环境
         */
        IContext context = new ContextImpl();
        context.showMsg();
        
        /**
         * 改变状态
         */
        context.changeState();
        context.showMsg();
        
        context.changeState();
        context.showMsg();
        
        context.changeState();
        context.showMsg();
    }

}
