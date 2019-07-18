#  rocketmq的安装

1. 下载与服务器对应的版本，[下载地址](http://rocketmq.apache.org/release_notes/release-notes-4.4.0/ "下载地址")

2. 使用xshell上传至/usr/local目录下

3. 解压
```
# unzip rocketmq-all-4.4.0-bin-release.zip 
```
若unzip命令不存在则安装:
```
# yum install unzip
```

4. 创建软连接
```
# ln -s rocketmq-all-4.4.0-bin-release rocketmq
```

5. 启动name server

先修改启动脚本中的jvm参数，默认为4g，会

```

```