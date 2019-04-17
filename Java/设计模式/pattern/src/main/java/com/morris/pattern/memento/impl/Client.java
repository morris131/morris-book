package com.morris.pattern.memento.impl;

public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("aaa");
        System.out.println(originator.getState());

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        originator.setState("bbb");
        System.out.println(originator.getState());
        originator.restoreMemento(caretaker.getMemento());

        System.out.println(originator.getState());
    }

}
