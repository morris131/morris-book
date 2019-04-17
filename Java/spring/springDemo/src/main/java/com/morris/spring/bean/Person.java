package com.morris.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;	
import org.springframework.context.ApplicationContextAware;

public class Person implements BeanNameAware, BeanFactoryAware, ApplicationContextAware	, InitializingBean, DisposableBean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void init() {
		System.out.println("invoke Person init()");
	}
	
	public void myDestroy() {
		System.out.println("invoke Person myDestroy()");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("invoke DisposableBean destroy()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("invoke InitializingBean afterPropertiesSet()");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("invoke ApplicationContextAware setApplicationContext(): " + arg0);		
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("invoke BeanFactoryAware setBeanFactory(): " + arg0);
	}

	@Override
	public void setBeanName(String arg0) {
		System.out.println("invoke BeanNameAware setBeanName(): " + arg0);
	}

}
