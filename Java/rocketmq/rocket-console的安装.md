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

4. 浏览器运行 http://192.168.252.10:12581

5. 效果图

![效果图](https://gitee.com/morris131/morris-book/raw/master/\Java\rocketmq/image/81d652ad-2882-4515-97ba-4eaad67da048.jpg "")
