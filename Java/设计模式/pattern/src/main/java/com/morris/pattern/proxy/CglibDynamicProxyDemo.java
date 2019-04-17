package com.morris.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;

public class CglibDynamicProxyDemo {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class); // 不能用enhancer.setSuperclass(IUserService.class);
		enhancer.setCallback(new CglibDynamicProxy());
		
		IUserService userService = (IUserService) enhancer.create();
		userService.login("morris");
	}
}
