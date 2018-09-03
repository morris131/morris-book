package com.morris.spring.aop.intro;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.morris.spring.aop.regex.IGreeting;

public class Client {
	
	@Test
	public void testIntroductionAdvice(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-introduction.xml");
		
		IGreeting greeting = (IGreeting) context.getBean("springProxy");
	
		greeting.sayHello("morris");
	
		IApoloy apoloyProxy = (IApoloy)greeting;	
		
		apoloyProxy.saySorry("morris");	
		
		context.close();
		
	}


}
