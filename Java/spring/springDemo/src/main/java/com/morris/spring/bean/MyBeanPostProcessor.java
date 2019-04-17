package com.morris.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
    public Object postProcessBeforeInitialization(Object bean,
            String beanName) throws BeansException {       
        System.out.println("invoke MyBeanPostProcessor postProcessBeforeInitialization,bean:" + bean+",beanName:"+beanName);
        return bean;
    }

	@Override
    public Object postProcessAfterInitialization(Object bean,
            String beanName) throws BeansException {
		System.out.println("invoke MyBeanPostProcessor postProcessAfterInitialization,bean:" + bean+",beanName:"+beanName);
        return bean;
    }

}
