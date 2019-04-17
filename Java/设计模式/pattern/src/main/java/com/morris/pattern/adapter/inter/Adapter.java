package com.morris.pattern.adapter.inter;

import com.morris.pattern.adapter.DC5V;

public class Adapter extends DC5V implements AC220V {

    @Override
    public void output5v() {
        output220v();
    }

    @Override
    public void output220v() {
        System.out.println("AC220V");
    }
}
