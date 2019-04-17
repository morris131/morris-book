package com.morris.pattern.state.impl;

public abstract class State {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    protected abstract void handle1();

    protected abstract void handle2();

}
