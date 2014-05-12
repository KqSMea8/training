/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern15.state.states;

import seker.pattern15.state.IContext;

/**
 * 关闭状态
 * @author liuxinjian
 * @since 2013-3-5
 */
public class CloseState implements IState {

    private String msg = "关闭状态";
    
    @Override
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    @Override
    public void changeState(IContext context) {
        context.setState(OpenState.getInstance());
    }

    static class SingleHolder {
        static CloseState instance = new CloseState();
    }
    
    public static CloseState getInstance() {
        return SingleHolder.instance;
    }
    
    private CloseState() {
        
    }

}
