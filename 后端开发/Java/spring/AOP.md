---
title: AOP
date: 2018-09-13 15:02:17
categories: spring
tags: [spring, aop, 通知]
---

# AOP
AOP(Aspect Oriented Programming)，即面向切面编程。所谓"切面"，简单说就是那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块之间的耦合度，并有利于未来的可操作性和可维护性。

AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多处，而各处基本相似，比如权限认证、日志、事物。AOP的作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。

## AOP 术语
- 切面(Aspect):切面是通知和切点的结合。通知和切点定义了切面的全部内容——它是什么，在何时何处完成其功能。
- 通知(Advice):通知定义了切面是什么以及何时使用，应该应用在某个方法被调用之前？之后？还是抛出异常时？等等。
- 连接点(JointPoint):连接点是在应用执行过程中能够插入切面的一个点。
- 切入点(Pointcut):切点有助于缩小切面所通知的连接点的范围。如果说通知定义了切面的“什么”和“何时”的话，那么切点就定义了“何处”，切点会匹配通知所要织入的一个或多个连接点，一般常用正则表达式定义所匹配的类和方法名称来指定这些切点。
- 引入(Introduction):引入允许我们向现有的类添加新方法或属性，从而无需修改这些现有类的情况下，让他们具有新的行为和状态。
- 织入(Weaving): 织入是把切面应用到目标对象并创建新的代理对象的过程。切面在指定的连接点被织入到目标对象中。

说明：织入是对方法的增强，引入是对类的增强。

## 通知的类型
- 前置通知: 在一个方法执行之前，执行通知。
- 后置通知: 在一个方法执行之后，不考虑其结果，执行通知。
- 返回后通知:	在一个方法执行之后，只有在方法成功完成时，才能执行通知。
- 抛出异常后通知:在一个方法执行之后，只有在方法退出抛出异常时，才能执行通知。
- 环绕通知:在方法调用之前和之后，执行通知。

## spring提供的通知
Spring支持五种类型的通知：
- Before(前)：org.apringframework.aop.MethodBeforeAdvice
- After-returning(返回后)：org.springframework.aop.AfterReturningAdvice
- After-throwing(抛出后)：org.springframework.aop.ThrowsAdvice
- Arround(周围)：org.aopaliance.intercept.MethodInterceptor
- Introduction(引入)：org.springframework.aop.IntroductionInterceptor

说明：周围通知，他是由AOP Alliance中的接口定义的而非Spring,周围通知相当于前通知、返回后通知的结合。

## 基于代理的AOP

接口IUserService
```
package com.morris.spring.aop.agent;

public interface IUserService {
	String hello(String name);
}
```
接口IUserService实现类UserServiceImpl

```
package com.morris.spring.aop.agent;

public class UserServiceImpl implements IUserService {

	@Override
	public String hello(String name) {
		return "hello " + name;
	}
}
```
通知实现类UserServiceBeforeAfterAdvice

```
package com.morris.spring.aop.agent;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class UserServiceBeforeAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("after invoke method " + method.getName() + " returnValue:" + returnValue);
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("before invoke method " + method.getName() + " args:" + args);
	}

}
```
测试类

```
	@Test
	public void testBeforeAfterAdvice() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new UserServiceImpl());
		proxyFactory.addAdvice(new UserServiceBeforeAfterAdvice());
		
		IUserService userService = (IUserService) proxyFactory.getProxy();
		
		System.out.println(userService.hello("morris"));
	}
```
运行结果如下：

```
before invoke method hello args:[Ljava.lang.Object;@f6c48ac
after invoke method hello returnValue:hello morris
hello morris
```

下面将使用环绕通知改造UserServiceBeforeAfterAdvice类。环绕通知类需要实现 org.aopalliance.intercept.MethodInterceptor接口。注意，这个接口不是 Spring 提供的。

环绕通知类UserServiceAroundAdvice
```
package com.morris.spring.aop.agent;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserServiceAroundAdvice implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        Object returnValue = invocation.proceed();
        afterReturning(returnValue, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return returnValue;
    }

    private void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after invoke method " + method.getName() + " returnValue:" + returnValue);
    }

    private void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before invoke method " + method.getName() + " args:" + args);
    }

}
```
测试类：

```
	@Test
	public void testAroundAdvice() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new UserServiceImpl());
		proxyFactory.addAdvice(new GreetingAroundAdvice());
		
		IUserService userService = (IUserService) proxyFactory.getProxy();
		
		System.out.println(userService.hello("morris"));
	}

```

## 声明式通知
通过Spring配置文件配置上面的bean。

spring-aop-around.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">

	<bean id="proxyFactoryBean" class="org.springframework.aop.framework.ProxyFactoryBean">
		<property name="interfaces" value="com.morris.spring.aop.agent.IUserService"></property>
		<property name="interceptorNames" value="userServiceAroundAdvice"></property>
		<property name="targetName" value="userServiceImpl"></property>
	</bean>
	
	<bean id="userServiceAroundAdvice" class="com.morris.spring.aop.agent.UserServiceAroundAdvice"></bean>
	<bean id="userServiceImpl" class="com.morris.spring.aop.agent.UserServiceImpl"></bean>
     
</beans>
```
测试类：

```
	@Test
	public void testConfigAround() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-around.xml");
		IUserService userService = (IUserService) context.getBean("proxyFactoryBean"); // 直接取出代理对象
		System.out.println(userService.hello("morris"));
		context.close();
	}	
```

## 使用基于正则表达式的SpringAOP切面类
上面的写法会拦截IUserService中所有的方法，使用正则表达式来定义切点。

为IUserService增加两个方法如下：
IUserService.java
```
package com.morris.spring.aop.agent;

public interface IUserService {
	String hello(String name);

	String hi(String name);

	String bye(String name);
}
```
UserServiceImpl.java
```
package com.morris.spring.aop.agent;

public class UserServiceImpl implements IUserService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public String hi(String name) {
        return "hi " + name;
    }

    @Override
    public String bye(String name) {
        return "bye " + name;
    }

}
```
spring-aop-regex.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">

	<bean id="userServiceImpl" class="com.morris.spring.aop.agent.UserServiceImpl" /> <!-- 目标对象 -->
		
	<bean id="userServiceAroundAdvice" class="com.morris.spring.aop.agent.UserServiceAroundAdvice"></bean>  <!-- 通知 -->

	<bean id="regexpMethodPointcutAdvisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor"> <!-- 切面 -->
		<property name="patterns" value="com.morris.spring.aop.agent.UserServiceImpl.h.*"></property> <!-- 切点 -->
		<property name="advice" ref="userServiceAroundAdvice"></property>
	</bean>
	
	<bean id="proxyFactoryBean" class="org.springframework.aop.framework.ProxyFactoryBean"> <!-- 代理对象 -->
		<property name="interfaces" value="com.morris.spring.aop.agent.IUserService"></property>
		<property name="interceptorNames" value="regexpMethodPointcutAdvisor"></property>
		<property name="targetName" value="userServiceImpl"></property>
	</bean>
</beans>
```
在上面的InterceptorNames属性不再是原来的增强，而是一个定义好的切面regexpMethodPointcutAdvisor，切面里面还用正则表达式定义了一个切点，即拦截userServiceImpl类中以h开头的方法。

测试代码如下：
```
	@Test
	public void testRegex() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-regex.xml");

		IUserService userService = (IUserService) context.getBean("proxyFactoryBean");

		userService.hello("morris");
		userService.hi("jack");
		userService.bye("lisa");

		context.close();
	}
```

改造上面的类，使用aop自动代理，不再需要手动配置代理对象。配置文件如下：

spring-aop-regex-auto.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xsi:schemaLocation="
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">

	<bean id="userServiceImpl" class="com.morris.spring.aop.agent.UserServiceImpl" /> <!-- 目标对象 -->

	<bean id="userServiceAroundAdvice" class="com.morris.spring.aop.agent.UserServiceAroundAdvice"></bean>  <!-- 通知 -->

	<bean id="regexpMethodPointcutAdvisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor"> <!-- 切面 -->
		<property name="patterns" value="com.morris.spring.aop.agent.UserServiceImpl.h.*"></property> <!-- 切点 -->
		<property name="advice" ref="userServiceAroundAdvice"></property>
	</bean>

	<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator">
		<property name="optimize" value="true"></property>
	</bean>
</beans>
```

## 基于 AOP 的 @AspectJ
为UserServiceImpl添加注解@Component。

编写切面AroundAspect.java
```
package com.morris.spring.aop.agent;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AroundAspect {
	
	@Pointcut("execution(* com.morris.spring.aop.agent.UserServiceImpl.h*(..))")
	public void aroundAll() {} 
	
	@Before("aroundAll()")
	private void afterReturning() {
		System.out.println("after invoke method around returnValue");
	}

	@After("aroundAll()")
	private void before() {
		System.out.println("before invoke around args");
	}
}
```
spring-aop-aspect.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/aop 
            http://www.springframework.org/schema/aop/spring-aop.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context.xsd">

	<context:component-scan base-package="com.morris.spring.aop.agent"></context:component-scan>
	
	<aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy>

     
</beans>
```

测试代码：

```
	@Test
	public void testAspect() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aspect.xml");

		IUserService userService = (IUserService) context.getBean("userServiceImpl");

		userService.hello("morris");
		userService.hi("jack");
		userService.bye("li0");

		context.close();
	}
```

将AroundAspect改写为xml形式。

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/aop 
            http://www.springframework.org/schema/aop/spring-aop.xsd">

	<bean id="userServiceImpl" class="com.morris.spring.aop.agent.UserServiceImpl"/>

	<bean id="aroundAspect" class="com.morris.spring.aop.agent.AroundAspect" />

	<aop:config>
		<aop:aspect ref="aroundAspect" >
			<aop:before method="before" pointcut="execution(* com.morris.spring.aop.agent.UserServiceImpl.h*(..))"/>
			<aop:after-returning method="afterReturning" pointcut="execution(* com.morris.spring.aop.agent.UserServiceImpl.h*(..))"/>
		</aop:aspect>
	</aop:config>
</beans>
```

## 引入通知
引入是对类的增强，是为需要方法的类添加属性和方法。可以用一个已存在的类让他实现另外的接口。

GreetingIntroAdvice.java
```
package com.morris.spring.aop.intro;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor
		implements IApoloy {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void saySorry(String name) {
		System.out.println(name + ", I am sorry !");		
	}

}
```
spring-aop-introduction.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">

	<bean id="userServiceImpl" class="com.morris.spring.aop.agent.UserServiceImpl"/>

	<bean id="userServiceIntroAdvice" class="com.morris.spring.aop.agent.UserServiceIntroAdvice"/>

	<bean id="proxyFactoryBean" class="org.springframework.aop.framework.ProxyFactoryBean">
		<property name="interfaces" value="com.morris.spring.aop.agent.ISayService"></property>
		<property name="interceptorNames" value="userServiceIntroAdvice"></property>
		<property name="targetName" value="userServiceImpl"></property>
		<property name="proxyTargetClass" value="true"></property>
	</bean>

</beans>
```
proxyTargetClass默认为false，设置为true用CGLIB代理，否则后面无法将ISayService转为IUserService。

测试代码：
```
    @Test
	public void testIntroductionAdvice(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-introduction.xml");
		ISayService sayService = (ISayService) context.getBean("proxyFactoryBean");
		sayService.say("morris");
		
		IUserService userService = (IUserService) sayService;
		userService.hello("morris");
		context.close();

	}
```
