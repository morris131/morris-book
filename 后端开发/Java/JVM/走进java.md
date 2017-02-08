### 走进java

#### java三大支柱
- 平台无关性：java与平台无关，jvm与平台有关
- 安全性
- 网络移动性

#### java技术体系
java体系结构独立又相关的四个技术：
 - Java程序设计语言
 - Java .class 文件
 - Java应用编程接口（Java API）
 - Java虚拟机

![](http://172.16.50.198:8888/admin/image/77d8a40f-1b43-4b98-8cda-f4b7dddc80d4.jpg)

#### JVM
java的面向网络的核心就是Java虚拟机,它支持Java面向网络体系结构三大支柱:平台无关性、安全性和网络移动性。
Java虚拟机是一台抽象的计算机，其规范定义了每个Java虚拟机都必须实现的特性，但是为每个特定实现都留下了很多选择。
Java虚拟机的主要任务是装载class文件并且执行其中的字节码。装载类由类装载器(class loader)完成，它可以从程序和API中装载class文件。字节码由执行引擎来执行。

