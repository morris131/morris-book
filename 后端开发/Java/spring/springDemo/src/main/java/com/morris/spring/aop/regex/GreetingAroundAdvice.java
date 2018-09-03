package com.morris.spring.aop.regex;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class GreetingAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
		Object returnValue = invocation.proceed();
		afterReturning(returnValue, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
		return returnValue;
	}
	
	private void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("after invoke method " + method.getName() + " returnValue:" + returnValue);
	}

	private void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("before invoke method " + method.getName() + " args:" + args);
	}

}
