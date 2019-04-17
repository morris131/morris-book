package com.morris.pattern.command.example;

public class TVCommand implements  Command {

    private TV tv;

    public TVCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}
