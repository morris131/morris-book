<<<<<<< HEAD
### Windows下安装MongoDB
#### 下载MongoDB安装包
官网下载地址： https://www.mongodb.org  选择Windows的安装包下载


#### 安装MongoDB
=======
下载MongoDB安装包
官网下载地址： https://www.mongodb.org  选择Windows的安装包下载


安装MongoDB
>>>>>>> 42afd59c5a20a76739fcd518c4dd43c89e18fdbd
根据你的系统下载 32 位或 64 位的 .msi 文件，下载后双击该文件，按操作提示安装即可。 安装过程中，你可以通过点击 "Custom(自定义)" 按钮来设置你的安装目录。


创建数据库目录和日志目录


D
:
\>mkdir \data\db

D
:
\>mkdir \data\log


添加环境变量




测试环境变量是否添加成功


D
:
\>mongo 
-
version

MongoDB
 shell version
:
 
3.0
.
7


将MongoDB服务作为Windows服务运行
请注意，你必须有管理权限才能运行下面的命令。执行以下命令将MongoDB服务器作为Windows服务运行：


D
:
\>mongod 
--
bind_ip 
127.0
.
0.1
 
--
logpath 
"d:\data\log\mongodb.log"
 
--
logappend 
-

-
dbpath 
"d:\data\db"
 
--
port 
27017
 
--
serviceName 
MongoDB
 
--
serviceDisplayName 
Mon

goDB 
--
install

2016
-
05
-
05T20
:
14
:
53.284
+
0800
 I CONTROL

2016
-
05
-
05T20
:
14
:
53.288
+
0800
 W CONTROL  
32
-
bit servers don
'
t have journaling ena

bled by 
default
.
 
Please
 use 
--
journal 
if
 you want durability
.

2016
-
05
-
05T20
:
14
:
53.288
+
0800
 I CONTROL
下表为mongodb启动的参数说明：
 参数
 描述

 --bind_ip
 绑定服务IP，若绑定127.0.0.1，则只能本机访问，不指定默认本地所有IP

 --logpath
 指定MongoDB日志文件，注意是指定文件不是目录

 --logappend
 使用追加的方式写日志

 --port
 指定服务端口号，默认端口27017

 --dbpath
 指定数据库路径

 --serviceName
 指定服务名称

 --serviceDisplayName
 指定服务名称，有多个mongodb服务时执行

 --install
 指定作为一个Windows服务安装



启动MongoDB服务器


D
:
\>net start 
MongoDB

MongoDB
 
服务正在启动
 
.

MongoDB
 
服务已经启动成功。


MongoDB Shell 客户端连接服务器


D
:
\>mongo

2016
-
05
-
05T20
:
17
:
57.474
+
0800
 I CONTROL  
Hotfix
 KB2731284 or later update is not

installed
,
 will zero
-
out data files

MongoDB
 shell version
:
 
3.0
.
7

connecting to
:
 test

Server
 has startup warnings
:

2016
-
05
-
05T20
:
16
:
54.083
+
0800
 I CONTROL  
[
initandlisten
]

2016
-
05
-
05T20
:
16
:
54.083
+
0800
 I CONTROL  
[
initandlisten
]
 
**
 NOTE
:
 
This
 is a 
32
 bi

t 
MongoDB
 binary
.

2016
-
05
-
05T20
:
16
:
54.083
+
0800
 I CONTROL  
[
initandlisten
]
 
**
       
32
 bit builds a

re limited to less than 
2GB
 of data 
(
or less with 
--
journal
).

2016
-
05
-
05T20
:
16
:
54.083
+
0800
 I CONTROL  
[
initandlisten
]
 
**
       
Note
 that journ

aling defaults to off 
for
 
32
 bit and is currently off
.

2016
-
05
-
05T20
:
16
:
54.084
+
0800
 I CONTROL  
[
initandlisten
]
 
**
       
See
 http
:
//doch

ub
.
mongodb
.
org
/
core
/
32bit

2016
-
05
-
05T20
:
16
:
54.084
+
0800
 I CONTROL  
[
initandlisten
]

>


停止MongoDB服务器


D
:
\>net stop 
MongoDB

MongoDB
 
服务正在停止.

MongoDB
 
服务已成功停止。


