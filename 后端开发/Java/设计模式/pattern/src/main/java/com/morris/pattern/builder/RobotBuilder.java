package com.morris.pattern.builder;

public interface RobotBuilder {

    void buildHead();

    void buildBody();

    void buildHand();

    void buildFoot();

    Robot getResult();

}
