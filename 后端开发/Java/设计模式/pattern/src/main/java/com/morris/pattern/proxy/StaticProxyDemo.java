package com.morris.pattern.proxy;

public class StaticProxyDemo {
	
	public static void main(String[] args) {
		UserServiceProxy proxy = new UserServiceProxy();
		proxy.login("morris");
	}

}
