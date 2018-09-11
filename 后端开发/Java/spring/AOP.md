## AOP
AOP(Aspect Oriented Programming)，即面向切面编程。所谓"切面"，简单说就是那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块之间的耦合度，并有利于未来的可操作性和可维护性。

AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多处，而各处基本相似，比如权限认证、日志、事物。AOP的作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。

### AOP 术语
- Aspect(切面):切面是通知和切点的结合。通知和切点定义了切面的全部内容——它是什么，在何时何处完成其功能。
- Advice(通知):通知定义了切面是什么以及何时使用，应该应用在某个方法被调用之前？之后？还是抛出异常时？等等。
- JointPoint(连接点):连接点是在应用执行过程中能够插入切面的一个点。
- Pointcut(切入点):切点有助于缩小切面所通知的连接点的范围。如果说通知定义了切面的“什么”和“何时”的话，那么切点就定义了“何处”，切点会匹配通知所要织入的一个或多个连接点，一般常用正则表达式定义所匹配的类和方法名称来指定这些切点。
- AOP代理：AOP框架创建的对象，代理就是目标对象的加强。
- 引入 Introduction:引入允许我们向现有的类添加新方法或属性，从而无需修改这些现有类的情况下，让他们具有新的行为和状态。
- 织入 Weaving:在过去我常常把织入与引入的概念混淆，我是这样来辨别的，“引入”我把它看做是一个定义，也就是一个名词，而“织入”我把它看做是一个动作，一个动词，也就是切面在指定的连接点被织入到目标对象中。

### 通知的类型
- 前置通知: 在一个方法执行之前，执行通知。
- 后置通知: 在一个方法执行之后，不考虑其结果，执行通知。
- 返回后通知:	在一个方法执行之后，只有在方法成功完成时，才能执行通知。
- 抛出异常后通知:在一个方法执行之后，只有在方法退出抛出异常时，才能执行通知。
- 环绕通知:在方法调用之前和之后，执行通知。

### 编程方式通知

接口IUserService
```
package com.morris.spring.aop;

public interface IUserService {
	
	String login(String name);

}

```
接口IUserService实现类UserServiceImpl

```
package com.morris.spring.aop;

public class UserServiceImpl implements IUserService {

	@Override
	public String login(String name) {
		return "hello " + name;
	}

}

```
通知实现类UserServiceBeforeAfterAdvice

```
package com.morris.spring.aop;

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
		
		System.out.println(userService.login("morris"));
	}
```
运行结果如下：

```
before invoke method login args:[Ljava.lang.Object;@6debcae2
after invoke method login returnValue:hello morris
hello morris
```

下面将使用环绕通知改造UserServiceBeforeAfterAdvice类。环绕通知类需要实现 org.aopalliance.intercept.MethodInterceptor接口。注意，这个接口不是 Spring 提供的。

环绕通知类UserServiceAroundAdvice
```
package com.morris.spring.aop;

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
		proxyFactory.addAdvice(new UserServiceAroundAdvice());
		
		IUserService userService = (IUserService) proxyFactory.getProxy();
		
		System.out.println(userService.login("morris"));
	}

```
运行结果如下：

```
before invoke method login args:[Ljava.lang.Object;@4cdbe50f
after invoke method login returnValue:hello morris
hello morris

```

### 声明式通知
通过Spring配置文件配置bean。

配置文件 spring-aop-around.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
    xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
            http://www.springframework.org/schema/aop 
            http://www.springframework.org/schema/beans/spring-aop-4.3.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<bean id="proxyFactoryBean" class="org.springframework.aop.framework.ProxyFactoryBean">
		<property name="interfaces" value="com.morris.spring.aop.IUserService"></property>
		<property name="interceptorNames" value="userServiceAroundAdvice"></property>
		<property name="targetName" value="userServiceImpl"></property>
	</bean>
	
	<bean id="userServiceAroundAdvice" class="com.morris.spring.aop.UserServiceAroundAdvice"></bean>  
	<bean id="userServiceImpl" class="com.morris.spring.aop.UserServiceImpl"></bean>  
     
</beans>
```
测试类：

```
	@Test
	public void testConfigAround() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop-around.xml");
		IUserService userService = (IUserService) context.getBean("proxyFactoryBean");
		System.out.println(userService.login("morris"));
		context.close();
	}
```
运行结果如下：

```
before invoke method login args:[Ljava.lang.Object;@396e2f39
after invoke method login returnValue:hello morris
hello morris
```

引入通知：上面的增强仅仅是对方法增强，也就是织入，对类的增强才能叫做引入增强，比如说不让UserServiceImpl去直接实现IUserService接口，靠Spring引入增强来动态实现。

```
package com.morris.spring.aop.intro;

public interface IGreeting {
	void sayHello(String name);
}
```

```
package com.morris.spring.aop.intro;

public class GreetingImpl implements IGreeting{

	@Override
	public void sayHello(String name) {
		System.out.println("hello " + name);
	}

}
```
增强的接口

```
package com.morris.spring.aop.intro;

public interface IApoloy {
	void saySorry(String name);
}

```
引入增强的类

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
配置文件 spring-aop-introduction.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
            http://www.springframework.org/schema/aop 
            http://www.springframework.org/schema/beans/spring-aop-4.3.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<bean id="greetingIntroAdvice"
		class="com.morris.spring.aop.intro.GreetingIntroAdvice">
	</bean>

	<bean id="greetingImpl"
		class="com.morris.spring.aop.intro.GreetingImpl">
	</bean>

	<bean id="springProxy"
		class="org.springframework.aop.framework.ProxyFactoryBean">
		<property name="interfaces"
			value="com.morris.spring.aop.intro.IApoloy" />
		<property name="target" ref="greetingImpl" />
		<property name="interceptorNames" value="greetingIntroAdvice"></property>
		<property name="proxyTargetClass" value="true"></property>
	</bean>

</beans>

```
场景类：

```
package com.morris.spring.aop.intro;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	
	@Test
	public void testIntroductionAdvice(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-introduction.xml");
		
		IGreeting greeting = (IGreeting) context.getBean("springProxy");
	
		greeting.sayHello("morris");
	
		IApoloy apoloyProxy = (IApoloy)greeting;	
		
		apoloyProxy.saySorry("morris");	
		
		context.close();
		
	}


}

```
proxyTargetClass属性表示是否代理目标类，默认是false，也就是代理接口，上面一个例子的配置就是没有这一项属性所以用JDK动态代理，现在是true即使用CGLib动态代理。

### 使用基于正则表达式的SpringAOP切面类

```
package com.morris.spring.aop.regex;

public interface IGreeting {
	void sayHello(String name);
	void saySorry(String name);
	void bye(String name);
}

```

```
package com.morris.spring.aop.regex;

public class GreetingImpl implements IGreeting{

	@Override
	public void sayHello(String name) {
		System.out.println("hello " + name);
	}

	@Override
	public void saySorry(String name) {	
		System.out.println("sorry " + name);
	}

	@Override
	public void bye(String name) {
		System.out.println("bye " + name);
	}

}

```

```
package com.morris.spring.aop.regex;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingAroundAdvice implements MethodInterceptor{

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


```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
    xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
            http://www.springframework.org/schema/aop 
            http://www.springframework.org/schema/beans/spring-aop-4.3.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-4.3.xsd">

		
	<bean id="greetingAroundAdvice" class="com.morris.spring.aop.regex.GreetingAroundAdvice"></bean>  
	<bean id="greetingImpl" class="com.morris.spring.aop.regex.GreetingImpl"></bean>  
	
	<bean id="regexpMethodPointcutAdvisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
		<property name="patterns" value="com.morris.spring.aop.regex.GreetingImpl.say.*"></property>
		<property name="advice" ref="greetingAroundAdvice"></property>
	</bean>
	
	<bean id="proxyFactoryBean" class="org.springframework.aop.framework.ProxyFactoryBean">
		<property name="interfaces" value="com.morris.spring.aop.regex.IGreeting"></property>
		<property name="interceptorNames" value="regexpMethodPointcutAdvisor"></property>
		<property name="targetName" value="greetingImpl"></property>
	</bean>

     
</beans>
```


```
package com.morris.spring.aop.regex;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	
	@Test
	public void testRegex() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-regex.xml");
		
		IGreeting greeting = (IGreeting) context.getBean("proxyFactoryBean");
		
		greeting.sayHello("morris");
		greeting.saySorry("jack");
		greeting.bye("lisa");
		
		context.close();
	}

}

```
在上面的InterceptorNames属性不再是原来的增强，而是一个定义好的切面regexpMethodPointcutAdvisor，切面里面还用正则表达式定义了一个切点，即拦截GreetingImpl类中以say开头的方法。

改造上面的类，使用aop自动代理。
为GreetingImpl和GreetingAroundAdvice加上注解@Component，配置文件如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
    xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
            http://www.springframework.org/schema/aop 
            http://www.springframework.org/schema/beans/spring-aop-4.3.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<context:component-scan base-package="com.morris.spring.aop.regex"></context:component-scan>
	
	<bean id="regexpMethodPointcutAdvisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
		<property name="patterns" value="com.morris.spring.aop.regex.GreetingImpl.say.*"></property>
		<property name="advice" ref="greetingAroundAdvice"></property>
	</bean>
	
	<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator">
		<property name="optimize" value="true"></property>
	</bean>

     
</beans>
```

### 基于 AOP 的 @AspectJ

```
package com.morris.spring.aop.regex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AroundAspect {
	
	@Around("execution(* com.morris.spring.aop.regex.IGreeting.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		before(joinPoint.getArgs(), joinPoint.getTarget());
		Object returnValue = joinPoint.proceed();
		afterReturning(returnValue);
		return returnValue;
	}
	
	private void afterReturning(Object returnValue) throws Throwable {
		System.out.println("after invoke method around returnValue:" + returnValue);
	}

	private void before(Object[] args, Object target) throws Throwable {
		System.out.println("before invoke around args:" + args);
	}

}

```

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
    xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/aop 
            http://www.springframework.org/schema/aop/spring-aop.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context.xsd">

	<context:component-scan base-package="com.morris.spring.aop.regex"></context:component-scan>
	
	<aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy>

     
</beans>
```
测试代码：

```
	@Test
	public void testAspect() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aspect.xml");
		
		IGreeting greeting = (IGreeting) context.getBean("greetingImpl");
		
		greeting.sayHello("morris");
		greeting.saySorry("jack");
		greeting.bye("li0");
		
		context.close();
	}
```

继续简化：

```
package com.morris.spring.aop.regex;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AroundAspect2 {
	
	@Pointcut("execution(* com.morris.spring.aop.regex.GreetingImpl.*(..))")
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
