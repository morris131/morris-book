package com.morris.pattern.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibDynamicProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("method:" + method.getName() + ", args:" + args);
		System.out.println("before invoke CglibDynamicProxy intercept");
		proxy.invokeSuper(obj, args);
		System.out.println("after invoke CglibDynamicProxy intercept");
		
		return null;
	}
}
