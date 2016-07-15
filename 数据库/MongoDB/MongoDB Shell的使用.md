MongoDB Shell是MongoDB自带的JavaScript Shell，随MongoDB一同发布， 它是MonoDB客户端工具，可以在Shell中使用命令与MongoDB实例交互，对数据 库的管理操作(CURD、集群配置、状态查看等)都可以通过MongoDB Shell来完 成。


基本功能

执行JavaScript命令



>
 
function
 add
(
x
,
 y
)
 
{

...
 
return
 x 
+
 y
;

...
 
}

>
 add
(
3
,
4
)

7
MongoDB客户端——基本命令
连接/切换数据库




>
 use stu

switched to db stu
数据插入




>
 db
.
stu
.
insert
({
name
:
'morris'
,
age
:
22
})

WriteResult
({
 
"nInserted"
 
:
 
1
 
})
数据查询




>
 db
.
stu
.
find
({
name
:
'morris'
})

{
 
"_id"
 
:
 
ObjectId
(
"573095170fcbcbaceb9ee999"
),
 
"name"
 
:
 
"morris"
,
 
"age"
 
:
 
22
 
}
数据更新




>
 db
.
stu
.
update
({
name
:
'morris'
},{
$set
:{
age
:
25
}})

WriteResult
({
 
"nMatched"
 
:
 
1
,
 
"nUpserted"
 
:
 
0
,
 
"nModified"
 
:
 
1
 
})
数据删除




>
 db
.
stu
.
remove
({
name
:
'morris'
})

WriteResult
({
 
"nRemoved"
 
:
 
1
 
})


使用技巧

help查看帮助



>
 help

        db
.
help
()
                    help on db methods

        db
.
mycoll
.
help
()
             help on collection methods

        sh
.
help
()
                    sharding helpers

... ...
执行脚本
脚本stu.js


//stu.js

var db 
=
  connect
(
"localhost:27017/stu"
);

db
.
stu
.
drop
();

db
.
stu
.
insert
({
name
:
'morris'
,
age
:
22
});

var cursor 
=
 db
.
stu
.
find
({
name
:
'morris'
});

printjson
(
cursor
.
toArray
());
直接运行




# mongo --quiet stu.js

[

	
{

		
"_id"
 
:
 
ObjectId
(
"5733a5730752a25a0914f1a9"
),

		
"name"
 
:
 
"morris"
,

		
"age"
 
:
 
22

	
}

]
交互式运行




>
 load
(
'stu.js'
)

connecting to
:
 localhost
:
27017
/
stu

[

	
{

		
"_id"
 
:
 
ObjectId
(
"5733a656c8c908c678618650"
),

		
"name"
 
:
 
"morris"
,

		
"age"
 
:
 
22

	
}

]

true

执行命令行程序



>
 run
(
'pwd'
)

2016
-
05
-
12T05
:
39
:
32.782
+
0800
 I 
-
        
[
thread1
]
 shell
:
 started program 
(
sh4873
):
  pwd

sh4873
|
 
/
root

0
.mongorc.js文件使用

在用户目录下新建.mongorc.js文件：


//
 
.
mongorc
.
js

print
(
"hello mongodb"
);
登录mongodb shell客户端：


[
root@chen 
~]#
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

hello mongodb

... ...

>
 

