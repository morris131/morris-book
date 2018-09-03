package com.morris.spring.aop;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.morris.spring.aop.regex.GreetingAroundAdvice;

public class Client {
	
	@Test
	public void testBeforeAfterAdvice() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new UserServiceImpl());
		proxyFactory.addAdvice(new UserServiceBeforeAfterAdvice());
		
		IUserService userService = (IUserService) proxyFactory.getProxy();
		
		System.out.println(userService.login("morris"));
	}
	
	@Test
	public void testAroundAdvice() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new UserServiceImpl());
		proxyFactory.addAdvice(new GreetingAroundAdvice());
		
		IUserService userService = (IUserService) proxyFactory.getProxy();
		
		System.out.println(userService.login("morris"));
	}
	
	@Test
	public void testConfigAround() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-around.xml");
		IUserService userService = (IUserService) context.getBean("proxyFactoryBean");
		System.out.println(userService.login("morris"));
		context.close();
	}	

}
