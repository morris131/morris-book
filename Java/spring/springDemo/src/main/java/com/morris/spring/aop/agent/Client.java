package com.morris.spring.aop.agent;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	
	@Test
	public void testBeforeAfterAdvice() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new UserServiceImpl()); // 目标对象
		proxyFactory.addAdvice(new UserServiceBeforeAfterAdvice()); // 通知
		
		IUserService userService = (IUserService) proxyFactory.getProxy();
		
		System.out.println(userService.hello("morris"));
	}
	
	@Test
	public void testAroundAdvice() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new UserServiceImpl());
		
		IUserService userService = (IUserService) proxyFactory.getProxy();
		
		System.out.println(userService.hello("morris"));
	}
	
	@Test
	public void testConfigAround() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-around.xml");
		IUserService userService = (IUserService) context.getBean("proxyFactoryBean"); // 直接取出代理对象
		System.out.println(userService.hello("morris"));
		context.close();
	}

	@Test
	public void testRegex() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-regex.xml");

		IUserService userService = (IUserService) context.getBean("proxyFactoryBean");

		userService.hello("morris");
		userService.hi("jack");
		userService.bye("lisa");

		context.close();
	}

	@Test
	public void testRegexAuto() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-regex-auto.xml");

		IUserService userService = (IUserService) context.getBean("userServiceImpl");

		userService.hello("morris");
		userService.hi("jack");
		userService.bye("li0");

		context.close();
	}

	@Test
	public void testAspect() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aspect.xml");

		IUserService userService = (IUserService) context.getBean("userServiceImpl");

		userService.hello("morris");
		userService.hi("jack");
		userService.bye("li0");

		context.close();
	}

	@Test
	public void testAspect2() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aspect2.xml");

		IUserService userService = (IUserService) context.getBean("userServiceImpl");

		userService.hello("morris");
		userService.hi("jack");
		userService.bye("li0");

		context.close();
	}

	@Test
	public void testIntroductionAdvice(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-introduction.xml");
		ISayService sayService = (ISayService) context.getBean("proxyFactoryBean");
		sayService.say("morris");

		IUserService userService = (IUserService) sayService;
		userService.hello("morris");
		context.close();

	}
}
