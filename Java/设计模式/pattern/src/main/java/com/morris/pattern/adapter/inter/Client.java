package com.morris.pattern.adapter.inter;

import com.morris.pattern.adapter.DC5V;

public class Client {
    public static void main(String[] args) {
        DC5V dc5V = new Adapter();
        dc5V.output5v();
    }
}
