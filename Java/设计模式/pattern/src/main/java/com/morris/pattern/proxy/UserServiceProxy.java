package com.morris.pattern.proxy;

public class UserServiceProxy implements IUserService {
	
	private IUserService userService = new UserServiceImpl();

	@Override
	public void login(String name) {
		System.out.println("before invoke UserServiceImpl login()");
		userService.login(name);
		System.out.println("after invoke UserServiceImpl login()");
	}

}
