package com.morris.spring.aop.agent;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public String hi(String name) {
        return "hi " + name;
    }

    @Override
    public String bye(String name) {
        return "bye " + name;
    }

}
