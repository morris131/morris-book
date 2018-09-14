package com.morris.spring.inject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserConfigTest {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(UserConfig.class);
    }
}
