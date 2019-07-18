# rocket-console的安装

1. 下载源码
[下载源码](https://github.com/apache/rocketmq-externals.git "下载源码")

2. 源码编译
只需编译源码中的rocketmq-console项目
```
mvn clean package -Dmaven.test.skip=true
```
上传源码至服务器/usr/local目录下

3.  启动服务
```
nohup java -jar rocketmq-console-ng-1.0.1.jar --server.port=12581 --rocketmq.config.namesrvAddr=localhost:9876 &
```
