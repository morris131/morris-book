# linux下安装jdk

1. 去官网下载jdk，[下载地址](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

2. 上传至/usr/local目录

3. 解压
```
tar -zxvf jdk-8u211-linux-x64.tar.gz
```

4. 配置环境变量，在/etc/profile最后增加如下内容：
```
JAVA_HOME=/usr/local/jdk1.8.0_211
PATH=$PATH:$JAVA_HOME/bin
export PATH JAVA_HOME
```

5. 让配置生效
```
source /etc/profile
```

6. 验证
```
# java -version
java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
```


