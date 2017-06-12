1. 新建一个maven项目，目录结构如下:

![2017-06-12_104547.jpg](后端开发/Java/SpringBoot/images/2017-06-12_104547.jpg "")

2. pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.morris</groupId>
	<artifactId>boot</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>boot</name>
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

3. BootApplication.java

```java
package com.morris.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}

```

4. IndexController.java

```java
package com.morris.boot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lian.chen on 2017/6/12.
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "hello world";
    }
}

```

5. 运行BootApplication中的main方法，启动项目
```bash
"D:\Program Files\Java\jdk1.8.0_101\bin\java" -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2017.1.4\lib\idea_rt.jar=58349:D:\Program Files\JetBrains\IntelliJ IDEA 2017.1.4\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java\jdk1.8.0_101\jre\lib\charsets.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\deploy.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\cldrdata.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\dnsns.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\jaccess.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\jfxrt.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\localedata.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\nashorn.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunec.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunpkcs11.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\zipfs.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\javaws.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\jce.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\jfr.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\jfxswt.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\jsse.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\management-agent.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\plugin.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\resources.jar;D:\Program Files\Java\jdk1.8.0_101\jre\lib\rt.jar;D:\ideaPrj\boot\target\classes;C:\Users\lian.chen\.m2\repository\org\springframework\boot\spring-boot-starter-web\1.5.4.RELEASE\spring-boot-starter-web-1.5.4.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\boot\spring-boot-starter\1.5.4.RELEASE\spring-boot-starter-1.5.4.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\boot\spring-boot\1.5.4.RELEASE\spring-boot-1.5.4.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\1.5.4.RELEASE\spring-boot-autoconfigure-1.5.4.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\boot\spring-boot-starter-logging\1.5.4.RELEASE\spring-boot-starter-logging-1.5.4.RELEASE.jar;C:\Users\lian.chen\.m2\repository\ch\qos\logback\logback-classic\1.1.11\logback-classic-1.1.11.jar;C:\Users\lian.chen\.m2\repository\ch\qos\logback\logback-core\1.1.11\logback-core-1.1.11.jar;C:\Users\lian.chen\.m2\repository\org\slf4j\jcl-over-slf4j\1.7.25\jcl-over-slf4j-1.7.25.jar;C:\Users\lian.chen\.m2\repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;C:\Users\lian.chen\.m2\repository\org\slf4j\log4j-over-slf4j\1.7.25\log4j-over-slf4j-1.7.25.jar;C:\Users\lian.chen\.m2\repository\org\yaml\snakeyaml\1.17\snakeyaml-1.17.jar;C:\Users\lian.chen\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\1.5.4.RELEASE\spring-boot-starter-tomcat-1.5.4.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.15\tomcat-embed-core-8.5.15.jar;C:\Users\lian.chen\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.15\tomcat-embed-el-8.5.15.jar;C:\Users\lian.chen\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.15\tomcat-embed-websocket-8.5.15.jar;C:\Users\lian.chen\.m2\repository\org\hibernate\hibernate-validator\5.3.5.Final\hibernate-validator-5.3.5.Final.jar;C:\Users\lian.chen\.m2\repository\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;C:\Users\lian.chen\.m2\repository\org\jboss\logging\jboss-logging\3.3.1.Final\jboss-logging-3.3.1.Final.jar;C:\Users\lian.chen\.m2\repository\com\fasterxml\classmate\1.3.3\classmate-1.3.3.jar;C:\Users\lian.chen\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.8.8\jackson-databind-2.8.8.jar;C:\Users\lian.chen\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.8.0\jackson-annotations-2.8.0.jar;C:\Users\lian.chen\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.8.8\jackson-core-2.8.8.jar;C:\Users\lian.chen\.m2\repository\org\springframework\spring-web\4.3.9.RELEASE\spring-web-4.3.9.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\spring-aop\4.3.9.RELEASE\spring-aop-4.3.9.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\spring-beans\4.3.9.RELEASE\spring-beans-4.3.9.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\spring-context\4.3.9.RELEASE\spring-context-4.3.9.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\spring-webmvc\4.3.9.RELEASE\spring-webmvc-4.3.9.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\springframework\spring-expression\4.3.9.RELEASE\spring-expression-4.3.9.RELEASE.jar;C:\Users\lian.chen\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;C:\Users\lian.chen\.m2\repository\org\springframework\spring-core\4.3.9.RELEASE\spring-core-4.3.9.RELEASE.jar" com.morris.boot.BootApplication

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.4.RELEASE)

2017-06-12 10:47:49.195  INFO 4872 --- [           main] com.morris.boot.BootApplication          : Starting BootApplication on PC-20151216HZDM with PID 4872 (D:\ideaPrj\boot\target\classes started by lian.chen in D:\ideaPrj\boot)
2017-06-12 10:47:49.195  INFO 4872 --- [           main] com.morris.boot.BootApplication          : No active profile set, falling back to default profiles: default
2017-06-12 10:47:49.244  INFO 4872 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@8646db9: startup date [Mon Jun 12 10:47:49 CST 2017]; root of context hierarchy
2017-06-12 10:47:50.181  INFO 4872 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-06-12 10:47:50.197  INFO 4872 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2017-06-12 10:47:50.197  INFO 4872 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.15
2017-06-12 10:47:50.262  INFO 4872 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-06-12 10:47:50.262  INFO 4872 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1018 ms
2017-06-12 10:47:50.329  INFO 4872 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-06-12 10:47:50.329  INFO 4872 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-06-12 10:47:50.329  INFO 4872 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-06-12 10:47:50.329  INFO 4872 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-06-12 10:47:50.329  INFO 4872 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-06-12 10:47:50.505  INFO 4872 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@8646db9: startup date [Mon Jun 12 10:47:49 CST 2017]; root of context hierarchy
2017-06-12 10:47:50.554  INFO 4872 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/]}" onto public java.lang.String com.morris.boot.web.IndexController.index()
2017-06-12 10:47:50.570  INFO 4872 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-06-12 10:47:50.570  INFO 4872 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-06-12 10:47:50.571  INFO 4872 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-06-12 10:47:50.571  INFO 4872 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-06-12 10:47:50.587  INFO 4872 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-06-12 10:47:50.685  INFO 4872 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-06-12 10:47:50.718  INFO 4872 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-06-12 10:47:50.718  INFO 4872 --- [           main] com.morris.boot.BootApplication          : Started BootApplication in 1.687 seconds (JVM running for 1.99)

```

6. 浏览器输入 http://localhost:8080/ 显示 hello world

