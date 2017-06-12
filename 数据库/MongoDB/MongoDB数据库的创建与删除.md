数据库的创建
MongoDB创建数据库的语法格式如下：


use DATABASE_NAME
如果数据库不存在，则创建数据库，否则切换到指定数据库。
查看所有数据库：



>
 show dbs

local
  
0.078GB

stu    
0.078GB
创建或切换数据库：



>
 use morris

switched to db morris
查看当前数据库：


>
 db

morris

> show dbs

local  0.078GB

stu    0.078GB
可以看到，刚创建的数据库morris并不在数据库的列表中，要显示它需要向morris数据库插入一些数据。


>
 db
.
t_user
.
insert
({
name
:
'Bob'
,
age
:
25
})

WriteResult
({
 
"nInserted"
 
:
 
1
 
})

>
 show dbs

local
   
0.078GB

morris  
0.078GB

stu     
0.078GB
MongoDB中默认的数据库为test，如果你没有创建新的数据库，集合将存放在test数据库中。


数据库的删除
MongoDB 删除数据库的语法格式如下：


db
.
dropDatabase
()
删除morris数据库：


>
 show dbs

local
   
0.078GB

morris  
0.078GB

stu     
0.078GB

>
 db
.
dropDatabase
()

{
 
"dropped"
 
:
 
"morris"
,
 
"ok"
 
:
 
1
 
}

>
 show dbs

local
  
0.078GB

stu    
0.078GB









