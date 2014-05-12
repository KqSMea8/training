/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern15.state;

import seker.pattern15.state.states.IState;

/**
 * 上下文接口
 * 
 * @author liuxinjian
 * @since 2013-3-5
 */
public interface IContext {
    /**
     * 设置状态
     * @param state
     */
    void setState(IState state);
    
    /**
     * 更改状态方法
     */
    void changeState();
    
    /**
     * 调用状态数据
     */
    void showMsg();
}
