package com.morris.pattern.observer.demo;

public class NewspaperOffice extends Subject {

    public void publishNews() {
        super.notifyObservers("第三次世界大战爆发了");
    }

}
