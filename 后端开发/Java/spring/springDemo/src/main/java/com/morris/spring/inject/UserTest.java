package com.morris.spring.inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    public static void main(String[] args) {
        //new ClassPathXmlApplicationContext("spring-inject-construct.xml");
        new ClassPathXmlApplicationContext("spring-inject-construct-c.xml");
        //new ClassPathXmlApplicationContext("spring-inject-setter.xml");
    }
}
