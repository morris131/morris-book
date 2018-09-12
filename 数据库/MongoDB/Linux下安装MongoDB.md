下载MongoDB压缩包
官网地址  https://www.mongodb.org/

可以在客户端下载后上传至服务器
在服务器使用wget或curl命令直接下载



解压压缩包


#
 tar 
-
zxvf mongodb
-
linux
-
i686
-
3.2
.
6.tgz
 
-
C 
/
usr
/
local

mongodb
-
linux
-
i686
-
3.2
.
6
/
README

mongodb
-
linux
-
i686
-
3.2
.
6
/
THIRD
-
PARTY
-
NOTICES

mongodb
-
linux
-
i686
-
3.2
.
6
/
MPL
-
2

... ...


创建软链接

软连接相当于windows下桌面的快捷方式。



# 
cd /usr/local

#
 ln mongodb
-
linux
-
i686
-
3.0
.
9
 mongodb 
-
s


添加环境变量
修改profile文件



#
 vi 
/
etc
/
profile

在最后加入:


export
 PATH
=
"$PATH:/usr/local/mongodb/bin"
使配置生效


#
 source 
/
etc
/
profile


创建数据库目录
MongoDB的数据默认存储在/data/db，但是这个目录在安装过程不会自动创建，所以需要手动创建/data/db目录。


#
 mkdir 
-
p 
/
data
/
db
注意：/data/db 是 MongoDB 默认的启动的数据库路径(--dbpath)。
 
命令行中启动MongoDB服务


# mongod --storageEngine=mmapv1

2016
-
05
-
12T05
:
25
:
48.820
+
0800
 I CONTROL  
[
main
]
 

2016
-
05
-
12T05
:
25
:
48.821
+
0800
 W CONTROL  
[
main
]
 
32
-
bit servers don
't have journaling enabled by default. Please use --journal if you want durability.

... ...

2016-05-12T05:25:49.820+0800 I NETWORK  [HostnameCanonicalizationWorker] Starting hostname canonicalization worker

2016-05-12T05:25:49.823+0800 I NETWORK  [initandlisten] waiting for connections on port 27017
注意：如果你的数据库目录不是/data/db，可以通过 --dbpath 来指定，如果没有设置环境变量，只能手动切换到/usr/local/mongo/bin目录下运行./mongod启动MongoDB服务。
 
MongoDB Shell后台管理
MongoDB Shell是MongoDB自带的交互式Javascript shell,用来对MongoDB进行操作和管理的交互式环境。进入mongoDB后台后，它默认会链接到 test 文档（数据库）。


#
 mongo

MongoDB
 shell version
:
 
3.2
.
6

connecting to
:
 test

Welcome
 to the 
MongoDB
 shell
.

... ...

>
 

