package com.morris.spring.aop;

public class UserServiceImpl implements IUserService {

	@Override
	public String login(String name) {
		return "hello " + name;
	}

}
