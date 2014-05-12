/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern09.decorator.decorators;

import seker.pattern09.decorator.IPrintable;

/**
 * 
 * @author liuxinjian
 * @since 2013-3-4
 */
public class HeaderDecorator extends OrderDecorator {

    /**
     * @param printable
     */
    public HeaderDecorator(IPrintable printable) {
        super(printable);
    }
    
    @Override
    public void print() {
        System.out.println("发票头部");
        super.print();
    }

}
