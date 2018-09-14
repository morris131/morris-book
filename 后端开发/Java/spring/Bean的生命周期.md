---
title: Bean的生命周期
date: 2018-09-12 14:29:22
categories: spring
tags: [spring,生命周期]
---

# Bean的生命周期

## POJO
- PO（Persistent Object）：持久对象，与数据库中的表相映射的Java对象，对应数据库中某个表中的一条记录。PO不包含业务逻辑和数据逻辑，就是一个Entity。
- VO（Value Object）：值对象,通常用于业务层之间的数据传递，和PO一样也是仅仅包含数据而已。但应是抽象出的业务对象，可以和表对应，也可以不，这根据业务的需要。
- BO（Business Object）：业务对象层，封装业务逻辑的java对象，通过调用DAO方法，结合PO,VO进行业务操作。它就是一个对PO的组合，也可以就是PO，只是出发点是满足业务的传输对象。
- DTO（Data Transfer Object）：数据传输对象，单纯用来数据传输的对象。
- POJO（Plain Ordinary Java Object）：简单的Java对象，实际就是普通JavaBeans。包含DTO、VO 、BO、PO等。

## Bean的作用域
在Spring中，那些组成应用程序的主体及由Spring IoC容器所管理的对象，被称之为Bean。简单地讲，bean就是由IoC容器初始化、装配及管理的对象，除此之外，bean就与应用程序中的其他对象没有什么区别了。而bean的定义以及bean相互间的依赖关系将通过配置元数据来描述。

下面就是Spring直接支持的五种作用域了，当然开发者也可以自己定制作用域。
- singleton：容器中仅存在一个对象，默认值
- prototype：每调用一次getBean()，都返回一个新的对象
- request：每一个HTTP请求会产生一个Bean对象
- session：同一个Http Session共用一个Bean	
- global session：类似于seesion作用域，仅在portletweb应用中有意义

说明：request,session以及global session这三个作用域都是只有在基于web的SpringApplicationContext实现的（比如XmlWebApplicationContext）中才能使用。 如果开发者仅仅在常规的Spring IoC容器中比如ClassPathXmlApplicationContext中使用这些作用域，那么将会抛出一个IllegalStateException来说明使用了未知的作用域。

## Bean的生命周期
下面是Spring中Bean的完整生命周期：
![Alt text](https://github.com/morris131/morris-bookwiki/raw/master/%E5%90%8E%E7%AB%AF%E5%BC%80%E5%8F%91/Java/spring/images/Spring%20bean%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.png)

Bean实例生命周期的执行过程如下：
1. Spring对bean进行实例化；
2. Spring将值和bean的引用注入到bean对应的属性中；
3. 如果bean实现了BeanNameAware接口，Spring将bean的ID传递给setBean-Name()方法；
4. 如果bean实现了BeanFactoryAware接口，Spring将调用setBeanFactory()方法，将BeanFactory容器实例传入；
5. 如果bean实现了ApplicationContextAware接口，Spring将调用setApplicationContext()方法，将bean所在的应用上下文的引用传入进来；
6. 如果有BeanPostProcessors和Bean关联，那么其postProcessBeforeInitialization()方法将被调用。
7. 如果bean实现了InitializingBean接口，Spring将调用它们的after-PropertiesSet()方法。
8. 如果bean使用init-method声明了初始化方法，该方法也会被调用；
9. 如果有BeanPostProcessors与Bean关联，那么其postProcessAfterInitialization()方法将被调用。
10. 此时，bean已经准备就绪，可以被应用程序使用了，它们将一直驻留在应用上下文中，直到该应用上下文被销毁；
11. 如果bean实现了DisposableBean接口，Spring将调用它的destroy()接口方法。
12. 如果bean使用destroy-method声明了销毁方法，该方法也会被调用。

说明：Spring是不会完全管理原型Bean的生命周期的：Spring容器只会初始化，配置以及装载这些Bean，传递给Client。但是之后就不会再去管原型Bean之后的动作了。 
也就是说，初始化生命周期回调方法在所有作用域的Bean是都会调用的，但是销毁生命周期回调方法在原型Bean是不会调用的。所以，客户端代码必须注意清理原型Bean以及释放原型Bean所持有的一些资源。可以通过使用自定义的bean post-processor来让Spring释放掉原型Bean所持有的资源。

## 演示代码

Person.java
```
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

```

MyBeanPostProcessor.java
```
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

```

spring-lifecycle.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
    xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-4.3.xsd">
                    
     <bean id="person" destroy-method="myDestroy" init-method="init" class="com.morris.spring.bean.Person">
        <property name="name">
            <value>jack</value>
        </property>
    </bean>
    
    <!-- 配置自定义的后置处理器 -->
     <bean id="postProcessor" class="com.morris.spring.bean.MyBeanPostProcessor" />
</beans>
```
BeanLifeCycleTest.java
```
package com.morris.spring.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleTest {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-lifecycle.xml");
		
		Person person = (Person) context.getBean("person");
		
		System.out.println(person.getName());
		
		context.close();
	}

}

```
运行结果如下：
```
"D:\Program Files\Java\jdk1.8.0_172\bin\java.exe" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2018.1.4\lib\idea_rt.jar=61410:D:\Program Files\JetBrains\IntelliJ IDEA 2018.1.4\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java\jdk1.8.0_172\jre\lib\charsets.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\deploy.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\cldrdata.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\dnsns.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\jaccess.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\jfxrt.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\localedata.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\nashorn.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunec.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunpkcs11.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\zipfs.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\javaws.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\jce.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\jfr.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\jfxswt.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\jsse.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\management-agent.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\plugin.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\resources.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\rt.jar;D:\gitPrj\morris-book\后端开发\Java\spring\springDemo\target\classes;C:\Users\wj65651\.m2\repository\org\springframework\spring-context\5.0.8.RELEASE\spring-context-5.0.8.RELEASE.jar;C:\Users\wj65651\.m2\repository\org\springframework\spring-aop\5.0.8.RELEASE\spring-aop-5.0.8.RELEASE.jar;C:\Users\wj65651\.m2\repository\org\springframework\spring-beans\5.0.8.RELEASE\spring-beans-5.0.8.RELEASE.jar;C:\Users\wj65651\.m2\repository\org\springframework\spring-core\5.0.8.RELEASE\spring-core-5.0.8.RELEASE.jar;C:\Users\wj65651\.m2\repository\org\springframework\spring-jcl\5.0.8.RELEASE\spring-jcl-5.0.8.RELEASE.jar;C:\Users\wj65651\.m2\repository\org\springframework\spring-expression\5.0.8.RELEASE\spring-expression-5.0.8.RELEASE.jar;C:\Users\wj65651\.m2\repository\junit\junit\4.11\junit-4.11.jar;C:\Users\wj65651\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\wj65651\.m2\repository\org\aspectj\aspectjrt\1.9.1\aspectjrt-1.9.1.jar;C:\Users\wj65651\.m2\repository\org\aspectj\aspectjweaver\1.9.1\aspectjweaver-1.9.1.jar" com.morris.spring.bean.BeanLifeCycleTest
九月 12, 2018 4:32:13 下午 org.springframework.context.support.AbstractApplicationContext prepareRefresh
信息: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@7ab2bfe1: startup date [Wed Sep 12 16:32:13 CST 2018]; root of context hierarchy
九月 12, 2018 4:32:13 下午 org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
信息: Loading XML bean definitions from class path resource [spring-lifecycle.xml]
invoke BeanNameAware setBeanName(): person
invoke BeanFactoryAware setBeanFactory(): org.springframework.beans.factory.support.DefaultListableBeanFactory@6b2fad11: defining beans [person,postProcessor]; root of factory hierarchy
invoke ApplicationContextAware setApplicationContext(): org.springframework.context.support.ClassPathXmlApplicationContext@7ab2bfe1: startup date [Wed Sep 12 16:32:13 CST 2018]; root of context hierarchy
invoke MyBeanPostProcessor postProcessBeforeInitialization,bean:com.morris.spring.bean.Person@7085bdee,beanName:person
invoke InitializingBean afterPropertiesSet()
invoke Person init()
invoke MyBeanPostProcessor postProcessAfterInitialization,bean:com.morris.spring.bean.Person@7085bdee,beanName:person
jack
九月 12, 2018 4:32:13 下午 org.springframework.context.support.AbstractApplicationContext doClose
invoke DisposableBean destroy()
invoke Person myDestroy()
信息: Closing org.springframework.context.support.ClassPathXmlApplicationContext@7ab2bfe1: startup date [Wed Sep 12 16:32:13 CST 2018]; root of context hierarchy

Process finished with exit code 0

```