package com.morris.pattern.observer.demo;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private List<Observer> observers = new ArrayList<>();

    protected void attach(Observer observer) {
        observers.add(observer);
    }

    protected void detach(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String news) {
        for(Observer observer: observers) {
            observer.update(news);
        }
    }
}
