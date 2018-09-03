package com.morris.spring.aop.regex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*@Aspect
@Component
public class AroundAspect {
	
	@Around("execution(* com.morris.spring.aop.regex.IGreeting.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		before(joinPoint.getArgs(), joinPoint.getTarget());
		Object returnValue = joinPoint.proceed();
		afterReturning(returnValue);
		return returnValue;
	}
	
	private void afterReturning(Object returnValue) throws Throwable {
		System.out.println("after invoke method around returnValue:" + returnValue);
	}

	private void before(Object[] args, Object target) throws Throwable {
		System.out.println("before invoke around args:" + args);
	}

}*/
