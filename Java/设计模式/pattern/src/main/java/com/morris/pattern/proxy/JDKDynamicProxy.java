package com.morris.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKDynamicProxy implements InvocationHandler {
	
	private Object object;
	
	public JDKDynamicProxy(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("method:" + method.getName() + ", args:" + args);
		System.out.println("before invoke UserServiceImpl login()");
		method.invoke(object, args);
		System.out.println("after invoke UserServiceImpl login()");
		return null;
	}

}
