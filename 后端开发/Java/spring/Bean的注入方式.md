---
title: Bean的注入方式
date: 2018-09-12 18:37:23
categories: spring
tags: [spring]
---

# Bean的注入方式
创建应用对象之间协作关系的行为通常称为装配（wiring）或者注入，这也是依赖注入（DI）的本质。

在Spring中注入bean有多种方式。Spring具有非常大的灵活性，它提供了三种主要的注入机制：
1. 在XML中进行显式配置。
2. 在Java中进行显式配置。
3. 隐式的bean发现机制和自动装配。

## 通过XML注入Bean

### 构造方法注入
通过构造方法注入可以指定属性名，指定参数位置，指定参数类型三种方法注入。
User.java
```
package com.morris.spring.inject;

public class User {
    private String name;

    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

```
通过构造方法为User对象注入name和age属性，spring配置如下：

spring-inject-construct.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">
                    
     <bean id="user" class="com.morris.spring.inject.User">
         <constructor-arg index="0" value="morris"/>
         <constructor-arg index="1" value="20"/>
    </bean>

    <bean id="user2" class="com.morris.spring.inject.User">
        <constructor-arg name="name" value="morris"/>
        <constructor-arg name="age" value="20"/>
    </bean>

    <bean id="user3" class="com.morris.spring.inject.User">
        <constructor-arg type="java.lang.String" value="morris"/>
        <constructor-arg type="java.lang.Integer" value="20"/>
    </bean>
</beans>
```
下面使用c命名空间简化上面的spring配置：

spring-inject-construct-c.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:c="http://www.springframework.org/schema/c"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">
                    
     <bean id="user" class="com.morris.spring.inject.User" c:_0="morris" c:_1="20"/>

    <bean id="user2" class="com.morris.spring.inject.User" c:name="morris" c:age="20"/>
</beans>
```

### set方法注入
还是上面的User类，通过set方法为User对象注入name和age属性。

spring-inject-setter.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">
                    
     <bean id="user" class="com.morris.spring.inject.User">
         <property name="name" value="morris"/>
         <property name="age" value="10"/>
    </bean>

</beans>
```
下面使用p命名空间简化上面的spring配置：

spring-inject-setter-p.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">
                    
     <bean id="user" class="com.morris.spring.inject.User" p:name="morris" p:age="20"/>

</beans>
```

### 数组注入&List集合注入&Map注入&Properties注入
User2.java
```
package com.morris.spring.inject;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class User2 {

    private String[] names;
    private List<String> list;
    private Map<String, String> map;
    private Properties properties;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}

```

spring-inject-complex.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">
                    
    <bean id="user" class="com.morris.spring.inject.User2">
        <property name="names">
            <array>
                <value>张三</value>
                <value>李四</value>
                <value>王五</value>
            </array>
        </property>

        <property name="list">
            <list>
                <value>张三</value>
                <value>李四</value>
                <value>王五</value>
            </list>
        </property>

        <property name="map">
            <map>
                <entry key="one" value="1"/>
                <entry key="two" value="2"/>
                <entry key="three" value="3"/>
            </map>
        </property>

        <property name="properties">
           <props>
               <prop key="username">morris</prop>
               <prop key="password">000000</prop>
           </props>
        </property>
    </bean>

</beans>
```

## 通过java代码注入Bean
创建JavaConfig类的关键在于为其添加@Configuration注解，@Configuration注解表明这个类是一个配置类，该类应该包含在Spring应用上下文中如何创建bean的细节。
```
package com.morris.spring.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public User createUser() {
        return new User();
    }

}

```
@Bean注解会告诉Spring这个方法将会返回一个对象，该对象要注册为Spring应用上下文中的bean。方法体中包含了最终产生bean实例的逻辑。

UserConfigTest.java
```
package com.morris.spring.inject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserConfigTest {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(UserConfig.class);
    }
}

```

## 自动注入Bean
Spring经过下面两步来实现自动化注入：
1. 组件扫描（component scanning）：Spring会自动发现应用上下文中所创建的bean。
2. 自动注入（autowiring）：Spring自动满足bean之间的依赖。

为User类加上注解@Component,表明该类会作为组件类，并告知Spring要为这个类创建bean。
```
@Component
public class User {
```

为UserConfig类加上注解，表明启用组件扫描。@ComponentScan默认会扫描与配置类相同的包。
```
@ComponentScan
@Configuration
public class UserConfig {
```
@ComponentScan的作用相当于在xml中的如下配置：
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.morris.spring.inject"/>

</beans>
```
AutoInjectTest..java
```
package com.morris.spring.inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserConfig.class)
public class AutoInjectTest {

    @Autowired
    private User user;

    @Test
    public void test() {
        Assert.assertNotNull(user);
    }

}

```

## 混合注入
在JavaConfig中引用XML配置:
```
@ImportResource("classpath:spring-inject-setter.xml") 
@Import(SystemConfig.class)
```

在XML配置中引用JavaConfig:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
            http://www.springframework.org/schema/beans 
            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">
                    
     <bean class="com.morris.spring.inject.UserConfig"/>

     <import resource="spring-inject-setter.xml"/>

</beans>
```