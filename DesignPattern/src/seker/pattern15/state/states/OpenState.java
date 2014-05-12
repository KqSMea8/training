/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.pattern15.state.states;

import seker.pattern15.state.IContext;

/**
 * 开启状态
 * @author liuxinjian
 * @since 2013-3-5
 */
public class OpenState implements IState {

    private String msg = "开启状态";
    
    @Override
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    @Override
    public void changeState(IContext context) {
        context.setState(CloseState.getInstance());
    }

    static class SingleHolder {
        static OpenState instance = new OpenState();
    }
    
    public static OpenState getInstance() {
        return SingleHolder.instance;
    }
    
    private OpenState() {
        
    }

}
