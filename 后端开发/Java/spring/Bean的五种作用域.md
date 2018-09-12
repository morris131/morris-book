## Bean的五种作用域
在Spring中，那些组成应用程序的主体及由Spring IoC容器所管理的对象，被称之为Bean。简单地讲，bean就是由IoC容器初始化、装配及管理的对象，除此之外，bean就与应用程序中的其他对象没有什么区别了。而bean的定义以及bean相互间的依赖关系将通过配置元数据来描述。

下面就是Spring直接支持的五种作用域了，当然开发者也可以自己定制作用域。


作用域 | 作用域
---|---
singleton | 容器中仅存在一个对象，默认值
prototype |	每调用一次getBean()，都返回一个新的对象
request | 每一个HTTP请求会产生一个Bean对象
session| 同一个Http Session共用一个Bean	
global session|	类似于seesion作用域，仅在portletweb应用中有意义

说明：request,session以及global session这三个作用域都是只有在基于web的SpringApplicationContext实现的（比如XmlWebApplicationContext）中才能使用。 如果开发者仅仅在常规的Spring IoC容器中比如ClassPathXmlApplicationContext中使用这些作用域，那么将会抛出一个IllegalStateException来说明使用了未知的作用域。

### singleton
当定义一个Bean的作用域为singleton时，容器只会根据Bean定义来创建该Bean的唯一实例。这些唯一的实例会缓存到容器中，后续针对单例Bean的请求和引用，都会从这个缓存中拿到这个唯一的实例。

Singleton作用域是Spring中的缺省作用域。在XML中将bean定义成singleton，可以这样配置：

```
<bean id="ServiceImpl" class="com.morris.service.ServiceImpl" scope="singleton">
```

### prototype
prototype指的就是每次请求Bean实例的时候，返回的都是新实例的Bean对象。这是基于线程安全性的考虑，对有状态的bean应该使用prototype作用域，而对无状态的bean则应该使用singleton作用域。

下面的例子展示了XML中如何定义一个原型的Bean：

```
<bean id="ServiceImpl" class="com.morris.service.ServiceImpl" scope="prototype"> 
```

与其他的作用域相比，Spring是不会完全管理原型Bean的生命周期的：Spring容器只会初始化，配置以及装载这些Bean，传递给Client。但是之后就不会再去管原型Bean之后的动作了。 
也就是说，初始化生命周期回调方法在所有作用域的Bean是都会调用的，但是销毁生命周期回调方法在原型Bean是不会调用的。所以，客户端代码必须注意清理原型Bean以及释放原型Bean所持有的一些资源。可以通过使用自定义的bean post-processor来让Spring释放掉原型Bean所持有的资源。