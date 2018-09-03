package com.morris.spring.aop.intro;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor
		implements IApoloy {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void saySorry(String name) {
		System.out.println(name + ", I am sorry !");		
	}

}

