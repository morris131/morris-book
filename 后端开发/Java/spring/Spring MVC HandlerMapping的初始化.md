---
title: Spring MVC HandlerMapping的初始化
date: 2018-09-12 18:16:22
categories: spring
tags: [spring,mvc,handlerMapping]
---

# Spring MVC HandlerMapping的初始化

## <mvc:annotation-driven>
在spring-mvc.xml中有这样一个配置：<mvc:annotation-driven>。

<mvc:annotation-driven>会自动注册RequestMappingHandlerMapping与RequestMappingHandlerAdapter两个Bean,这是Spring MVC为@Controller分发请求所必需的，并且提供了数据绑定支持，@NumberFormatannotation支持，@DateTimeFormat支持,@Valid支持读写XML的支持（JAXB）和读写JSON的支持（默认Jackson）等功能。

## RequestMappingHandlerMapping
RequestMappingHandlerMapping的父类AbstractHandlerMethodMapping类实现了InitializingBean接口，在属性初始化完成后会调用afterPropertiesSet()方法，在该方法中调用initHandlerMethods();进行HandlerMethod初始化。
```
	public void afterPropertiesSet() {
		initHandlerMethods();
	}
	
	// 扫描ApplicationContext中的Bean，查找并注册 handlerMethod 
	protected void initHandlerMethods() {
		if (logger.isDebugEnabled()) {
			logger.debug("Looking for request mappings in application context: " + getApplicationContext());
		}
		
		// 从ApplicationContext中获取所有Bean名称  
		String[] beanNames = (this.detectHandlerMethodsInAncestorContexts ?
				BeanFactoryUtils.beanNamesForTypeIncludingAncestors(obtainApplicationContext(), Object.class) :
				obtainApplicationContext().getBeanNamesForType(Object.class));

		for (String beanName : beanNames) {
			if (!beanName.startsWith(SCOPED_TARGET_NAME_PREFIX)) {
				Class<?> beanType = null;
				try {
					beanType = obtainApplicationContext().getType(beanName);
				}
				catch (Throwable ex) {
					// An unresolvable bean type, probably from a lazy bean - let's ignore it.
					if (logger.isDebugEnabled()) {
						logger.debug("Could not resolve target class for bean with name '" + beanName + "'", ex);
					}
				}
				if (beanType != null && isHandler(beanType)) {
				    //从bean中查找handler method
					detectHandlerMethods(beanName);
				}
			}
		}
		handlerMethodsInitialized(getHandlerMethods());
	}
```
detectHandlerMethods()的源代码如下：
```
	protected void detectHandlerMethods(final Object handler) {
		Class<?> handlerType = (handler instanceof String ?
				obtainApplicationContext().getType((String) handler) : handler.getClass());

		if (handlerType != null) {
			final Class<?> userType = ClassUtils.getUserClass(handlerType);
			
			// value为RequestingMappingInfo
			Map<Method, T> methods = MethodIntrospector.selectMethods(userType,
					(MethodIntrospector.MetadataLookup<T>) method -> {
						try {
						    // 调用RequestMappingHandlerMapping中的getMappingForMethod
							return getMappingForMethod(method, userType);
						}
						catch (Throwable ex) {
							throw new IllegalStateException("Invalid mapping on handler class [" +
									userType.getName() + "]: " + method, ex);
						}
					});
			if (logger.isDebugEnabled()) {
				logger.debug(methods.size() + " request handler methods found on " + userType + ": " + methods);
			}
			methods.forEach((method, mapping) -> {
				Method invocableMethod = AopUtils.selectInvocableMethod(method, userType);
				registerHandlerMethod(handler, invocableMethod, mapping);
			});
		}
	}
```
调用RequestMappingHandlerMapping中的getMappingForMethod：
```
	protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		RequestMappingInfo info = createRequestMappingInfo(method);
		if (info != null) {
			RequestMappingInfo typeInfo = createRequestMappingInfo(handlerType);
			if (typeInfo != null) {
			    // 类和方法的@RequestMapping注解都存在的话，进行组合
				info = typeInfo.combine(info);
			}
		}
		return info;
	}
	
	private RequestMappingInfo createRequestMappingInfo(AnnotatedElement element) {
	    // 寻找RequestMapping注解
		RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
		RequestCondition<?> condition = (element instanceof Class ?
				getCustomTypeCondition((Class<?>) element) : getCustomMethodCondition((Method) element));
		return (requestMapping != null ? createRequestMappingInfo(requestMapping, condition) : null);
	}
```
 



