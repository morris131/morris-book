package com.morris.pattern.builder;

public class Client {
    public static void main(String[] args) {
        RobotDirector director = new RobotDirector();
        Robot robot = director.construct(new ConcreRobotBuilder());
        System.out.println(robot);
    }
}
