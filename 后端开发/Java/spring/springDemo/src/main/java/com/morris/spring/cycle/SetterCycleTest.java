package com.morris.spring.cycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterCycleTest {
	
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:spring-cycle-setter.xml");
	}

}
