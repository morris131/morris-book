package com.morris.pattern.proxy;

public class UserServiceImpl implements IUserService {

	@Override
	public void login(String name) {
		System.out.println(name + " is logining...");
	}

}
