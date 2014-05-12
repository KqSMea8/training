/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern15.state.states;

import seker.pattern15.state.IContext;

/**
 * 状态接口
 * 
 * @author liuxinjian
 * @since 2013-3-5
 */
public interface IState {

    /**
     * 获得描述
     * @return
     */
    String getMsg();
    
    /**
     * 更改状态
     */
    void changeState(IContext context);
}
