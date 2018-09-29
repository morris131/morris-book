package com.morris.pattern.builder;

public class RobotDirector {

    public Robot construct(RobotBuilder robotBuilder) {
        robotBuilder.buildHead();
        robotBuilder.buildBody();
        robotBuilder.buildHand();
        robotBuilder.buildFoot();
        return robotBuilder.getResult();
    }

}
