package com.morris.spring.aop.regex;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	
	@Test
	public void testRegex() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-regex.xml");
		
		IGreeting greeting = (IGreeting) context.getBean("proxyFactoryBean");
		
		greeting.sayHello("morris");
		greeting.saySorry("jack");
		greeting.bye("lisa");
		
		context.close();
	}
	
	@Test
	public void testRegexAuto() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-regex-auto.xml");
		
		IGreeting greeting = (IGreeting) context.getBean("greetingImpl");
		
		greeting.sayHello("morris");
		greeting.saySorry("jack");
		greeting.bye("li0");
		
		context.close();
	}
	
	@Test
	public void testAspect() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aspect.xml");
		
		IGreeting greeting = (IGreeting) context.getBean("greetingImpl");
		
		greeting.sayHello("morris");
		greeting.saySorry("jack");
		greeting.bye("li0");
		
		context.close();
	}
	
	@Test
	public void testAspect2() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aspect.xml");
		
		IGreeting greeting = (IGreeting) context.getBean("greetingImpl");
		
		greeting.sayHello("morris");
		greeting.saySorry("jack");
		greeting.bye("li0");
		
		context.close();
	}

}
