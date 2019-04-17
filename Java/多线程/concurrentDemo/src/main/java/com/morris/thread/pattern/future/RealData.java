package com.morris.thread.pattern.future;

import java.util.concurrent.TimeUnit;

public class RealData implements Data {
    @Override
    public String getRequest() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "morris";
    }
}
