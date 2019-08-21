# redis安装

## 下载
下载地址：https://redis.io/download

下载至 /usr/local目录下

## 安装
make之前确保安装了gcc，未安装则需安装
```
$ cd /usr/local
$ tar xzf redis-5.0.4.tar.gz
$ mv redis-5.0.4 redis
$ cd redis
$ make
```

## 开启守护进程模式
打开/usr/local/redis/redis.conf文件，将daemonize设置的值设置为yes
```
$ daemonize yes
```

### daemonize设置yes或者no区别
- daemonize:yes:代表开启守护进程模式，redis会在后台运行，并将进程pid号写入至redis.conf选项pidfile设置的文件中。
- daemonize:no: 启动将进入redis的命令行界面，exit或者关闭连接工具(putty,xshell等)都会导致redis进程退出。

## 启动服务
```
$ src/redis-server redis.conf
```

## 运行客户端
```
$ src/redis-cli 
127.0.0.1:6379> set hello world
OK
127.0.0.1:6379> get hello
"world"
```

## 注册成服务
```
$ cp /usr/local/redis/utils/redis_init_script /etc/init.d/redis
```
修改/etc/init.d/redis的内容
```
REDISPORT=6379$
EXEC=/usr/local/redis/src/redis-server$
CLIEXEC=/usr/local/redis/src/redis-cli$
PIDFILE=/var/run/redis_${REDISPORT}.pid$                                                                                                                                                                                                                                  
CONF="/usr/local/redis/redis.conf"$
```

将redis注册成服务后就可以用service命令来启动了。

打开redis命令:service redis start

关闭redis命令:service redis stop

若报错：unrecognized service 需将脚本权限改为可执行, chmod +x redis

## 设置开机自启
```
$ chkconfig --add redis
$ chkconfig --list | grep 'redis'
redis          	0:off	1:off	2:on	3:on	4:on	5:on	6:off
```
