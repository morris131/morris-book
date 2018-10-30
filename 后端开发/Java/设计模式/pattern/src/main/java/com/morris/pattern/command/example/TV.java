package com.morris.pattern.command.example;

public class TV implements HouseholdAppliances {
    @Override
    public void on() {
        System.out.println("the TV on...");
    }

    @Override
    public void off() {
        System.out.println("the TV off...");
    }
}
