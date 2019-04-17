package com.morris.spring.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleTest {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-lifecycle.xml");
		
		Person person = (Person) context.getBean("person");
		
		System.out.println(person.getName());
		
		context.close();
	}

}
