package com.morris.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKDynamicProxyDemo {
	
	public static void main(String[] args) {
		IUserService userService = new UserServiceImpl();
		InvocationHandler handler = new JDKDynamicProxy(userService);
		IUserService proxy = (IUserService) Proxy.newProxyInstance(JDKDynamicProxyDemo.class.getClassLoader(), new Class[]{IUserService.class}, handler);
		proxy.login("morris");
	}

}
