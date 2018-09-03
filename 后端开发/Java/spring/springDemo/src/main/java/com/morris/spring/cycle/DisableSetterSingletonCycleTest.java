package com.morris.spring.cycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DisableSetterSingletonCycleTest {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-cycle-setter.xml");
		context.setAllowCircularReferences(false); // 禁止循环依赖
		context.refresh(); // 不refresh不生效，原因是之前bean factory已经refresh了
		context.close();
	}

}
