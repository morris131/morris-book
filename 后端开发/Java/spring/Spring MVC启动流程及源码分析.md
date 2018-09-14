---
title: Spring MVC启动流程及源码分析
date: 2018-09-14 14:38:22
categories: spring
tags: [spring,mvc]
---

# Spring MVC启动流程及源码分析

## web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app
        version="3.0"
        xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">


  <display-name>Web Application</display-name>

  <!--全局变量配置-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring.xml</param-value>
  </context-param>

  <!--监听器-->
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!--解决乱码问题的filter-->
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!--Restful前端控制器-->
  <servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
  </servlet>

  <servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>
```

## WEB应用初始化流程
下面是一个web应用启动后初始化的步骤：
1. 创建和初始化<listener>元素标记的listener
2. 对于所有事件监听器，如果实现了ServletContextListener接口，将会执行其实现的contextInitialized()方法
3. 创建和初始化<filter>元素标记的filter，并调用其init()方法
4. 根据<load-on-startup>的权值按顺序创建和初始化<servlet>元素标记的servlet，并调用其init()方法

## Listener的初始化过程
web.xml中首先定义了<context-param>标签，用于配置一个全局变量，<context-param>标签的内容读取后会被放进application中，做为Web应用的全局变量使用，接下来创建listener时会使用到这个全局变量，因此，Web应用在容器中部署后，进行初始化时会先读取这个全局变量，之后再进行上述讲解的初始化启动过程。

接着定义了一个ContextLoaderListener类的listener。查看ContextLoaderListener的类声明源码如下:
```
public class ContextLoaderListener extends ContextLoader implements ServletContextListener {
```
 ContextLoaderListener类继承了ContextLoader类并实现了ServletContextListener接口，再看一下ServletContextListener接口源码:
 ```public interface ServletContextListener extends EventListener {
    
        default public void contextInitialized(ServletContextEvent sce) {}
    
        default public void contextDestroyed(ServletContextEvent sce) {}
    }
 ```
 该接口只有两个方法contextInitialized和contextDestroyed，这里采用的是观察者模式，也称为为订阅-发布模式，实现了该接口的listener会向发布者进行订阅，当Web应用初始化或销毁时会分别调用上述两个方法。
 
 继续看ContextLoaderListener，该listener实现了ServletContextListener接口，因此在Web应用初始化时会调用该方法，该方法的具体实现如下：
 ```
 	public void contextInitialized(ServletContextEvent event) {
 		initWebApplicationContext(event.getServletContext());
 	}
 	
 	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
 	        /*
                首先通过WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE
                这个String类型的静态变量获取一个根IoC容器，根IoC容器作为全局变量
                存储在application对象中，如果存在则有且只能有一个
                如果在初始化根WebApplicationContext即根IoC容器时发现已经存在
                则直接抛出异常，因此web.xml中只允许存在一个ContextLoader类或其子类的对象
                */
            
    		if (servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null) {
    			throw new IllegalStateException(
    					"Cannot initialize context because there is already a root application context present - " +
    					"check whether you have multiple ContextLoader* definitions in your web.xml!");
    		}
    
    		Log logger = LogFactory.getLog(ContextLoader.class);
    		servletContext.log("Initializing Spring root WebApplicationContext");
    		if (logger.isInfoEnabled()) {
    			logger.info("Root WebApplicationContext: initialization started");
    		}
    		long startTime = System.currentTimeMillis();
    
    		try {
    			// Store context in local instance variable, to guarantee that
    			// it is available on ServletContext shutdown.
    			// 如果当前成员变量中不存在WebApplicationContext则创建一个根WebApplicationContext
    			if (this.context == null) {
    				this.context = createWebApplicationContext(servletContext);
    			}
    			if (this.context instanceof ConfigurableWebApplicationContext) {
    				ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext) this.context;
    				if (!cwac.isActive()) {
    					// The context has not yet been refreshed -> provide services such as
    					// setting the parent context, setting the application context id, etc
    					if (cwac.getParent() == null) {
    						// The context instance was injected without an explicit parent ->
    						// determine parent for root web application context, if any.
    						ApplicationContext parent = loadParentContext(servletContext);
    						//为根WebApplicationContext设置一个父容器
    						cwac.setParent(parent);
    					}
    					//配置并刷新整个根IoC容器，在这里会进行Bean的创建和初始化
    					configureAndRefreshWebApplicationContext(cwac, servletContext);
    				}
    			}
    			
    			//将创建好的IoC容器放入到application对象中，并设置key为WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE
    			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);
    
    			ClassLoader ccl = Thread.currentThread().getContextClassLoader();
    			if (ccl == ContextLoader.class.getClassLoader()) {
    				currentContext = this.context;
    			}
    			else if (ccl != null) {
    				currentContextPerThread.put(ccl, this.context);
    			}
    
    			if (logger.isDebugEnabled()) {
    				logger.debug("Published root WebApplicationContext as ServletContext attribute with name [" +
    						WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE + "]");
    			}
    			if (logger.isInfoEnabled()) {
    				long elapsedTime = System.currentTimeMillis() - startTime;
    				logger.info("Root WebApplicationContext: initialization completed in " + elapsedTime + " ms");
    			}
    
    			return this.context;
    		}
    		catch (RuntimeException ex) {
    			logger.error("Context initialization failed", ex);
    			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ex);
    			throw ex;
    		}
    		catch (Error err) {
    			logger.error("Context initialization failed", err);
    			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, err);
    			throw err;
    		}
    	}
 ```
 initWebApplicationContext()方法如上注解讲述，主要目的就是创建root WebApplicationContext对象即根IoC容器，其中比较重要的就是，整个Web应用如果存在根IoC容器则有且只能有一个，根IoC容器作为全局变量存储在ServletContext即application对象中。将根IoC容器放入到application对象之前进行了IoC容器的配置和刷新操作，调用了configureAndRefreshWebApplicationContext()方法，该方法源码如下:
```
	protected void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac, ServletContext sc) {
		if (ObjectUtils.identityToString(wac).equals(wac.getId())) {
			// The application context id is still set to its original default value
			// -> assign a more useful id based on available information
			String idParam = sc.getInitParameter(CONTEXT_ID_PARAM);
			if (idParam != null) {
				wac.setId(idParam);
			}
			else {
				// Generate default id...
				wac.setId(ConfigurableWebApplicationContext.APPLICATION_CONTEXT_ID_PREFIX +
						ObjectUtils.getDisplayString(sc.getContextPath()));
			}
		}

		wac.setServletContext(sc);
		//获取web.xml中<context-param>标签配置的全局变量，也就是获取spring.xml，并将其放入到WebApplicationContext中
		String configLocationParam = sc.getInitParameter(CONFIG_LOCATION_PARAM);
		if (configLocationParam != null) {
			wac.setConfigLocation(configLocationParam);
		}

		// The wac environment's #initPropertySources will be called in any case when the context
		// is refreshed; do it eagerly here to ensure servlet property sources are in place for
		// use in any post-processing or initialization that occurs below prior to #refresh
		ConfigurableEnvironment env = wac.getEnvironment();
		if (env instanceof ConfigurableWebEnvironment) {
			((ConfigurableWebEnvironment) env).initPropertySources(sc, null);
		}

		customizeContext(sc, wac);
		
		// 调用父类AbstractApplicationContext创建并初始化spring.xml中的Bean
		wac.refresh();
	}
```

## Filter的初始化
在监听器listener初始化完成后，按照文章开始的讲解，接下来会进行filter的初始化操作，filter的创建和初始化中没有涉及IoC容器的相关操作，因此不是本文讲解的重点，本文举例的filter是一个用于编码用户请求和响应的过滤器，采用utf-8编码用于适配中文。

## Servlet的初始化
Web应用启动的最后一个步骤就是创建和初始化相关Servlet，在开发中常用的Servlet就是DispatcherServlet类前端控制器，前端控制器作为中央控制器是整个Web应用的核心，用于获取分发用户请求并返回响应。

通过类的继承关系可以看出DispatcherServlet类间接实现了Servlet接口，因此其本质上依旧是一个Servlet。DispatcherServlet类的设计很巧妙，上层父类不同程度的实现了相关接口的部分方法，并留出了相关方法用于子类覆盖，将不变的部分统一实现，将变化的部分预留方法用于子类实现。
在Web应用部署到容器后进行Servlet初始化时会调用相关的init(ServletConfig)方法，此方法的实现实在父类HttpServletBean中，源码如下：

```
	public final void init() throws ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing servlet '" + getServletName() + "'");
		}

		// Set bean properties from init parameters.
		PropertyValues pvs = new ServletConfigPropertyValues(getServletConfig(), this.requiredProperties);
		if (!pvs.isEmpty()) {
			try {
				BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
				ResourceLoader resourceLoader = new ServletContextResourceLoader(getServletContext());
				bw.registerCustomEditor(Resource.class, new ResourceEditor(resourceLoader, getEnvironment()));
				initBeanWrapper(bw);
				bw.setPropertyValues(pvs, true);
			}
			catch (BeansException ex) {
				if (logger.isErrorEnabled()) {
					logger.error("Failed to set bean properties on servlet '" + getServletName() + "'", ex);
				}
				throw ex;
			}
		}

		// Let subclasses do whatever initialization they like.
		initServletBean();

		if (logger.isDebugEnabled()) {
			logger.debug("Servlet '" + getServletName() + "' configured successfully");
		}
	}
```
init()中调用了子类FrameworkServlet的initServletBean()方法，
```
	protected final void initServletBean() throws ServletException {
		getServletContext().log("Initializing Spring FrameworkServlet '" + getServletName() + "'");
		if (this.logger.isInfoEnabled()) {
			this.logger.info("FrameworkServlet '" + getServletName() + "': initialization started");
		}
		long startTime = System.currentTimeMillis();

		try {
			this.webApplicationContext = initWebApplicationContext();
			initFrameworkServlet();
		}
		catch (ServletException | RuntimeException ex) {
			this.logger.error("Context initialization failed", ex);
			throw ex;
		}

		if (this.logger.isInfoEnabled()) {
			long elapsedTime = System.currentTimeMillis() - startTime;
			this.logger.info("FrameworkServlet '" + getServletName() + "': initialization completed in " +
					elapsedTime + " ms");
		}
	}
```
该方法中比较重要的就是initWebApplicationContext()方法的调用，该方法仍由FrameworkServlet抽象类实现，继续查看其源码如下所示:
```
	protected WebApplicationContext initWebApplicationContext() {
        // 获取之前由ContextLoaderListener创建的根IoC容器
		WebApplicationContext rootContext =
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		WebApplicationContext wac = null;

		if (this.webApplicationContext != null) {
			// A context instance was injected at construction time -> use it
			wac = this.webApplicationContext;
			if (wac instanceof ConfigurableWebApplicationContext) {
				ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext) wac;
				if (!cwac.isActive()) {
					// The context has not yet been refreshed -> provide services such as
					// setting the parent context, setting the application context id, etc
					if (cwac.getParent() == null) {
						// The context instance was injected without an explicit parent -> set
						// the root application context (if any; may be null) as the parent

                        // 如果当前Servelt存在一个WebApplicationContext即子IoC容器,则将根IoC容器作为子IoC容器的父容器
						cwac.setParent(rootContext);
					}
					// 读取spring-mvc.xml文件，配置并刷新当前的子IoC容器，功能与前文讲解根IoC容器时的配置刷新一致，用于构建相关Bean
					configureAndRefreshWebApplicationContext(cwac);
				}
			}
		}
		if (wac == null) {
			// No context instance was injected at construction time -> see if one
			// has been registered in the servlet context. If one exists, it is assumed
			// that the parent context (if any) has already been set and that the
			// user has performed any initialization such as setting the context id
			// 如果当前Servlet不存在一个子IoC容器则去查找一下
			wac = findWebApplicationContext();
		}
		if (wac == null) {
			// No context instance is defined for this servlet -> create a local one
			// 如果仍旧没有查找到子IoC容器则创建一个子IoC容器
			wac = createWebApplicationContext(rootContext);
		}

		if (!this.refreshEventReceived) {
			// Either the context is not a ConfigurableApplicationContext with refresh
			// support or the context injected at construction time had already been
			// refreshed -> trigger initial onRefresh manually here.
			//调用子类DispatcherServlet覆盖的onRefresh方法完成“可变”的初始化过程
			onRefresh(wac);
		}

		if (this.publishContext) {
			// Publish the context as a servlet context attribute.
			String attrName = getServletContextAttributeName();
			getServletContext().setAttribute(attrName, wac);
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Published WebApplicationContext of servlet '" + getServletName() +
						"' as ServletContext attribute with name [" + attrName + "]");
			}
		}

		return wac;
	}
```
当IoC子容器构造完成后调用了onRefresh()方法，
```
	/**
	 * This implementation calls {@link #initStrategies}.
	 */
	@Override
	protected void onRefresh(ApplicationContext context) {
		initStrategies(context);
	}

	/**
	 * Initialize the strategy objects that this servlet uses.
	 * <p>May be overridden in subclasses in order to initialize further strategy objects.
	 */
	protected void initStrategies(ApplicationContext context) {
		initMultipartResolver(context);
		initLocaleResolver(context);
		initThemeResolver(context);
		initHandlerMappings(context);
		initHandlerAdapters(context);
		initHandlerExceptionResolvers(context);
		initRequestToViewNameTranslator(context);
		initViewResolvers(context);
		initFlashMapManager(context);
	}
```

onRefresh()方法直接调用了initStrategies()方法，源码如上，通过函数名可以判断，该方法用于初始化创建multipartResovle来支持图片等文件的上传、本地化解析器、主题解析器、HandlerMapping处理器映射器、HandlerAdapter处理器适配器、异常解析器、视图解析器、flashMap管理器等，这些组件都是SpringMVC开发中的重要组件，九大组件的初始化创建过程均在此完成。





