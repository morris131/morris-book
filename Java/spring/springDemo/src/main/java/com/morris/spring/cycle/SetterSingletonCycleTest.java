package com.morris.spring.cycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterSingletonCycleTest {
	
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("spring-cycle-setter-singleton.xml");
	}

}
