/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern06.facade;

/**
 * 
 * 代理模式
 * 注意和其他设计模式的区别
 * 
 * 代理模式
 * 1 代理模式中的原类和代理类继承同一父类；
 * 2 原类对象与代理类对象接口相同，功能一致；
 * 3 起到了隐藏原类的作用。
 * 
 * 适配器模式
 * 1 只有适配器继承目标接口；
 * 2 将原有类接口转换为目标代码需求的接口。
 * 
 * 外观模式
 * 是多个类的集成、统一适配。
 * 
 * @author liuxinjian
 * @since 2013-1-26
 */
public class Client {
    public static void main(String[] args) {
        Computer facade = new Computer();
        facade.startComputer();
    }
}
