package com.morris.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class UserServiceBeforeAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("after invoke method " + method.getName() + " returnValue:" + returnValue);
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("before invoke method " + method.getName() + " args:" + args);
	}

}
