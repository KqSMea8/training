/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern15.state;

import seker.pattern15.state.states.CloseState;
import seker.pattern15.state.states.IState;

/**
 * 状态属性
 * 
 * @author liuxinjian
 * @since 2013-3-5
 */
public class ContextImpl implements IContext {
    
    private IState state;
    
    public IState getState() {
        return state;
    }

    @Override
    public void setState(IState state) {
        this.state = state;
    }
    
    public ContextImpl() {
        setState(CloseState.getInstance());
    }

    @Override
    public void changeState() {
        state.changeState(this);
    }

    @Override
    public void showMsg() {
        System.out.println(state.getMsg());
    }

}
