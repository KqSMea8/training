/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern09.decorator;

/**
 * 被装饰者：decoratee
 * @author liuxinjian
 * @since 2013-3-4
 */
public class Order implements IPrintable{

    @Override
    public void print() {
        System.out.println("发票的主体部分");
    }
}
