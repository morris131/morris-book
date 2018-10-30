package com.morris.pattern.command.example;

public class Light implements HouseholdAppliances {
    @Override
    public void on() {
        System.out.println("the light on...");
    }

    @Override
    public void off() {
        System.out.println("the light off...");
    }
}
