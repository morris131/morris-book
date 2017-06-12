1. ʹ��IDEA�½�һ��spring boot��Ŀ

![1.jpg](images/1.jpg "")
![2.jpg](images/2.jpg "")
![3.jpg](images/3.jpg "")
![4.jpg](images/4.jpg "")
![5.jpg](images/5.jpg "")
![6.jpg](images/6.jpg "")

2. ���õ���ĿĿ¼�ṹ����:

![7.jpg](images/7.jpg "")

ͨ�����沽������˻�����Ŀ�Ĵ���������ͼ��ʾ��Spring Boot�Ļ����ṹ�������ļ�������·�������û�������Ŀʱ��д��Group���в��죩��

src/main/java�µĳ�����ڣ�Chapter1Application

src/main/resources�µ������ļ���application.properties

src/test/�µĲ�����ڣ�Chapter1ApplicationTests

���ɵ�Chapter1Application��Chapter1ApplicationTests�඼����ֱ��������������ǰ��������Ŀ������Ŀǰ����Ŀδ����κ����ݷ��ʻ�Webģ�飬������ڼ�����Spring֮��������С�

3. ����webģ��
��ǰ��pom.xml�������£�������������ģ�飺

spring-boot-starter������ģ�飬�����Զ�����֧�֡���־��YAML

spring-boot-starter-test������ģ�飬����JUnit��Hamcrest��Mockito

����Webģ�飬�����spring-boot-starter-webģ��

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.morris</groupId>
	<artifactId>chapter1</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>chapter1</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.4.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>


</project>


```

4. ��дHelloWorld����

```java
package com.morris.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lian.chen on 2017/6/12.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("hello")
    public String hello() {
        return "hello world";
    }
}

```

5. ����Chapter1Application�е�main������������Ŀ


6. ��������� http://localhost:8080/hello ��ʾ hello world

