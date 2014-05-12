/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern09.decorator.decorators;

import seker.pattern09.decorator.IPrintable;

/**
 * 装饰类的父类
 * @author liuxinjian
 * @since 2013-3-4
 */
public abstract class OrderDecorator implements IPrintable {

    private IPrintable printable;
    
    public OrderDecorator(IPrintable printable) {
        super();
        this.printable = printable;
    }
    @Override
    public void print() {
        printable.print();
    }
}
