package com.morris.spring.aop.regex;

import org.springframework.stereotype.Component;

@Component
public class GreetingImpl implements IGreeting{

	@Override
	public void sayHello(String name) {
		System.out.println("hello " + name);
	}

	@Override
	public void saySorry(String name) {	
		System.out.println("sorry " + name);
	}

	@Override
	public void bye(String name) {
		System.out.println("bye " + name);
	}

}
