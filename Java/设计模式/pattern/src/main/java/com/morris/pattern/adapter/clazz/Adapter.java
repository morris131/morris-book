package com.morris.pattern.adapter.clazz;

import com.morris.pattern.adapter.DC5V;

public class Adapter extends DC5V {

    private AC220V ac220V  = new AC220V();

    @Override
    public void output5v() {
        ac220V.output220v();
    }
}
