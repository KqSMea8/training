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
public class FooterDecorator extends OrderDecorator {

    /**
     * @param printable
     */
    public FooterDecorator(IPrintable printable) {
        super(printable);
    }
    
    @Override
    public void print() {
        super.print();
        System.out.println("发票尾部");
    }

}
