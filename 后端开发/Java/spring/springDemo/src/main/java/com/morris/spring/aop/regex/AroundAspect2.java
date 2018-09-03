package com.morris.spring.aop.regex;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AroundAspect2 {
	
	@Pointcut("execution(* com.morris.spring.aop.regex.GreetingImpl.*(..))")
	public void aroundAll() {} 
	
	@Before("aroundAll()")
	private void afterReturning() {
		System.out.println("after invoke method around returnValue");
	}

	@After("aroundAll()")
	private void before() {
		System.out.println("before invoke around args");
	}

}
