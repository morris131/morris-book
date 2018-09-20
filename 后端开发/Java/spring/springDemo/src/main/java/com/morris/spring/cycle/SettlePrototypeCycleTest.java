
package com.morris.spring.cycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SettlePrototypeCycleTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-cycle-setter-prototype.xml");
		context.getBean("a"); // //此时必须要获取Spring管理的实例，因为现在scope="prototype" 只有请求获取的时候才会实例化对象
		context.close();
	}
}
