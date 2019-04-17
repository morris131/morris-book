# 模板引擎

在动态HTML实现上Spring Boot依然可以完美胜任，并且提供了多种模板引擎的默认配置支持，所以在推荐的模板引擎下，我们可以很快的上手开发动态网站。

Spring Boot提供了默认配置的模板引擎主要有以下几种：

* ThymeleafFreeMarker
* Velocity
* Groovy
* Mustache

Spring Boot建议使用这些模板引擎，避免使用JSP，若一定要使用JSP将无法实现Spring Boot的多种特性，具体可见后文：支持JSP的配置。

当你使用上述模板引擎中的任何一个，它们默认的模板配置路径为：src/main/resources/templates。


1. 新建spring boot工程，添加模块引擎依赖

详细代码参考[pom.xml](projects/chapter2/pom.xml)

```xml
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
	</dependency>
```

2. 编写控制层HelloController

详细代码参考[HelloController.java](projects/chapter2/src/main/java/com/morris/web/HelloController.java)

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
        model.addAttribute("msg", "hello world");
        return "hello";
    }
}

```

3. 编写html页面

详细代码参考[hello.html](projects/chapter2/src/main/resources/templates/hello.html)

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
</head>
<body>
<h1 th:text="${msg}">Hello World</h1>
</body>
</html>
```

4. 启动程序
直接打开html页面展现Hello World，但是启动程序后，访问http://localhost:8080/hello，则是展示HelloController中msg的值：hello world，做到了不破坏HTML自身内容的数据逻辑分离。

5. Thymeleaf的参数配置

如有需要修改默认配置的时候，只需复制下面要修改的属性到application.properties中，并修改成需要的值，如修改模板文件的扩展名，修改默认的模板路径等
```bash
# Enable template caching.
spring.thymeleaf.cache=true 
# Check that the templates location exists.
spring.thymeleaf.check-template-location=true 
# Content-Type value.
spring.thymeleaf.content-type=text/html 
# Enable MVC Thymeleaf view resolution.
spring.thymeleaf.enabled=true 
# Template encoding.
spring.thymeleaf.encoding=UTF-8 
# Comma-separated list of view names that should be excluded from resolution.
spring.thymeleaf.excluded-view-names= 
# Template mode to be applied to templates. See also StandardTemplateModeHandlers.
spring.thymeleaf.mode=HTML5 
# Prefix that gets prepended to view names when building a URL.
spring.thymeleaf.prefix=classpath:/templates/ 
# Suffix that gets appended to view names when building a URL.
spring.thymeleaf.suffix=.html  
spring.thymeleaf.template-resolver-order= 
# Order of the template resolver in the chain.
spring.thymeleaf.view-names=
# Comma-separated list of view names that can be resolved.
```
