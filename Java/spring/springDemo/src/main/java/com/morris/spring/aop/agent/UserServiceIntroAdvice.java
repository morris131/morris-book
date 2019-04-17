package com.morris.spring.aop.agent;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class UserServiceIntroAdvice extends DelegatingIntroductionInterceptor implements ISayService {
    @Override
    public void say(String name) {
        System.out.println("say " + name);
    }
}
