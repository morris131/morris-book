1. �½�spring boot��Ŀ����дpom.xml

��ϸ����ο�[pom.xml](projects/chapter3/pom.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.morris</groupId>
    <artifactId>chapter3</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>chapter3</name>
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

        <!-- servlet����. -->

        <dependency>

            <groupId>javax.servlet</groupId>

            <artifactId>javax.servlet-api</artifactId>

            <scope>provided</scope>

        </dependency>

        <dependency>

            <groupId>javax.servlet</groupId>

            <artifactId>jstl</artifactId>

        </dependency>


        <!-- tomcat��֧��.-->

        <dependency>

            <groupId>org.springframework.boot</groupId>

            <artifactId>spring-boot-starter-tomcat</artifactId>


        </dependency>

        <dependency>

            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
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
> tomcat-embed-jasper��spring-boot-starter-tomcat������Ҫ��<scope>provided</scope>����

2. ��дHelloController

��ϸ����ο�[HelloController.java](projects/chapter3/src/main/java/com/morris/web/HelloController.java)

```java
package com.morris.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lian.chen on 2017/6/15.
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg","hello world");
        return "hello";
    }


}

```

3. ��дjspҳ��

��ϸ����ο�[hello.jsp](projects/chapter3/src/main/webapp/WEB-INF/jsp/hello.jsp)

```html
<!DOCTYPE html>
<html lang="en">
<body>
<h1>Message: ${msg}</h1>
</body>
</html>
```

4. �������ļ�application.properties�����

��ϸ����ο�[application.properties](projects/chapter3/src/main/resources/application.properties)

```bash
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```

5. ������Ŀ

����������� http://localhost:8080/hello