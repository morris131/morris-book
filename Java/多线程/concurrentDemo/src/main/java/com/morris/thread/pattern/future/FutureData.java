package com.morris.thread.pattern.future;

public class FutureData implements Data {

    private String result = null;

    private RealData realData = new RealData();

    private Object object = new Object();

    @Override
    public String getRequest() {
        if(null != result) {
            return  result;
        }
        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
