package com.morris.pattern.builder;

public class ConcreRobotBuilder implements  RobotBuilder{

    private Robot robot = new Robot();

    @Override
    public void buildHead() {
        robot.setHead("头");
    }

    @Override
    public void buildBody() {
        robot.setBody("身体");
    }

    @Override
    public void buildHand() {
        robot.setHand("手");
    }

    @Override
    public void buildFoot() {
        robot.setFoot("脚");
    }

    @Override
    public Robot getResult() {
        return robot;
    }
}
