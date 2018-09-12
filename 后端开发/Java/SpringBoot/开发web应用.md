# ģ������

�ڶ�̬HTMLʵ����Spring Boot��Ȼ��������ʤ�Σ������ṩ�˶���ģ�������Ĭ������֧�֣��������Ƽ���ģ�������£����ǿ��Ժܿ�����ֿ�����̬��վ��

Spring Boot�ṩ��Ĭ�����õ�ģ��������Ҫ�����¼��֣�

* ThymeleafFreeMarker
* Velocity
* Groovy
* Mustache

Spring Boot����ʹ����Щģ�����棬����ʹ��JSP����һ��Ҫʹ��JSP���޷�ʵ��Spring Boot�Ķ������ԣ�����ɼ����ģ�֧��JSP�����á�

����ʹ������ģ�������е��κ�һ��������Ĭ�ϵ�ģ������·��Ϊ��src/main/resources/templates��


1. �½�spring boot���̣����ģ����������

��ϸ����ο�[pom.xml](projects/chapter2/pom.xml)

```xml
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
	</dependency>
```

2. ��д���Ʋ�HelloController

��ϸ����ο�[HelloController.java](projects/chapter2/src/main/java/com/morris/web/HelloController.java)

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

3. ��дhtmlҳ��

��ϸ����ο�[hello.html](projects/chapter2/src/main/resources/templates/hello.html)

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

4. ��������
ֱ�Ӵ�htmlҳ��չ��Hello World��������������󣬷���http://localhost:8080/hello������չʾHelloController��msg��ֵ��hello world�������˲��ƻ�HTML�������ݵ������߼����롣

5. Thymeleaf�Ĳ�������

������Ҫ�޸�Ĭ�����õ�ʱ��ֻ�踴������Ҫ�޸ĵ����Ե�application.properties�У����޸ĳ���Ҫ��ֵ�����޸�ģ���ļ�����չ�����޸�Ĭ�ϵ�ģ��·����
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
