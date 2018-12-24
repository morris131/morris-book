package com.morris.pattern.observer.demo.v2;

import java.util.Observable;

public class NewspaperOffice extends Observable {

    public void publishNews() {
        super.setChanged();
        super.notifyObservers("第三次世界大战爆发了");
    }

}
