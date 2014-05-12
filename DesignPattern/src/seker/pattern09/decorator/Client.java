/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern09.decorator;

import seker.pattern09.decorator.decorators.FooterDecorator;
import seker.pattern09.decorator.decorators.HeaderDecorator;

/**
 * Decorator常被翻译成"装饰",我觉得翻译成"油漆工"更形象点,
 * 油漆工(decorator)是用来刷油漆的,那么被刷油漆的对象我们称decoratee.
 * 这两种实体在Decorator模式中是必须的.
 * 
 * Decorator定义（别名包装器Wrapper）: 动态给一个对象添加一些额外的职责,就象在墙上刷油漆.
 * 使用Decorator模式相比用生成子类方式达到功能的扩充显得更为灵活.   
 * 
 * 为什么使用Decorator? 
 * 我们通常可以使用继承来实现功能的拓展,如果这些需要拓展的功能的种类很繁多,那么势必生成很多子类,增加系统的复杂性,
 * 同时,使用继承实现功能拓展,我们必须可预见这些拓展功能,这些功能是编译时就确定了,是静态的.
 * 
 * 使用Decorator的理由是:
 * 这些功能需要由用户动态决定加入的方式和时机.Decorator提供了"即插即用"的方法,在运行期间决定何时增加何种功能.
 * 
 * @author liuxinjian
 * @since 2013-3-4
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 测试原始类
        IPrintable order = new Order();
        order.print();
        System.out.println("-------打印完毕-------\n");
        
        // 测试装饰头部的类
        IPrintable headerOrder = new HeaderDecorator(new Order());
        headerOrder.print();
        System.out.println("-------打印完毕-------\n");
        
        // 测试装饰头部和尾部的类
        IPrintable headerAndFooterOrder = new FooterDecorator(new HeaderDecorator(new Order()));
        headerAndFooterOrder.print();
        System.out.println("-------打印完毕-------\n");
    }

}
