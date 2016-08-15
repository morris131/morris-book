### MongoDB文档与集合
下表是将SQL中的概念与Mongo中的一些概念的对照：

| SQL术语/概念  | MongoDB术语/概念  | 解释/说明  |
| ------------ | ------------ | ------------ |
| database | database | 数据库 |
|table |collection |数据库表/集合|
|row| document| 数据记录行/文档|
|column|field| 数据字段/域|
| index| index| 索引|
| table joins| embedded documents/reference| 表连接/内嵌文档，表连接|
| primary key| primary key| 主键,MongoDB自动将_id字段设置为主键|

#### 文档
文档是MongoDB最核心的概念，本质上是一种类JSON的BSON格式的数据。 BSON是一种类JSON的二进制格式数据，它可以理解为在JSON基础上添加了 一些新的数据类型，包括日期、int32, int64等。 BSON是由一组组键值对组成，它具有轻量性、可遍 历性和高效性三个特征。可遍历性是MongoDB将BSON 作为数据存储的主要原因。
使用MongoDB文档时需要注意以下问题:
- MongoDB中写操作的原子性限制在文档级别，对文档的保存、修改、删除 等都是原子操作
- 单个文档占用的存储空间不能超过16MB
- MongoDB会尽量保持文档被插入时键值对的顺序
- 文档中的键/值对是有序的
- 文档中的值不仅可以是在双引号里面的字符串，还可以是其他几种数据类型（甚至可以是整个嵌入的文档)
- MongoDB区分类型和大小写。
- MongoDB的文档不能有重复的键。
- 文档的键是字符串。除了少数例外情况，键可以使用任意UTF-8字符。
关于文档键的命名需要注意以下几点:
- _id是系统保留的关键字，它是默认的主键，该值在集合中必须唯一，且不 可更改
- 键不能包含\0或空字符，这个字符用于表示键的结尾
- 不能以$开头
- 不能包含.(点号)
- 键是区分大小写的且不能重复例如:{foo:l,Foo:1}

#### 集合
把一组相关的文档放到一起组成了集合，如果将MongoDB的一个文档比喻 为关系型数据库中的一行，那么一个集合就相当于一张表。 MongoDB的集合是模式自由的，一个集合里面的文档可以是各式各样。
例如 下面的两个文档可以出现了同一个集合中。
```javascript
{
"site"
:
"www.baidu.com"
}

{
"site"
:
"www.google.com"
,
"name"
:
"Google"
}

{
"site"
:
"www.mongodb.org"
,
"name"
:
"Mongodb"
,
"num"
:
5
}
```
MongoDB提供了一些特殊功能的集合，例如 capped collection、 system.indexes、system.namespaces等。
关于集合的命名需要注意以下几点:
- 集合名不能是空字符串("")
- 集合名不能含有\0字符(空字符)，该字符表示集合名的结尾
- 集合名不能以”syste m .'’开头，此前缀是系统本身保留的
- 集合名中不能包含$字符(注:可包含.)

#### 命名空间
数据库的信息是存储在集合中。它们使用了系统的命名空间：
dbname.system.*
在MongoDB数据库中名字空间 <dbname>.system.* 是包含多种系统信息的特殊集合(Collection)，如下:

| 集合命名空间  | 描述  |
| ------------ | ------------ |
| dbname.system.namespaces  | 列出所有名字空间  |
| dbname.system.indexes  | 列出所有索引  |
| dbname.system.profile  | 包含数据库概要(profile)信息  |
| dbname.system.users  | 列出所有可访问数据库的用户  |
| dbname.local.sources  | 包含复制对端（slave）的服务器信息和状态  |

对于修改系统集合中的对象有如下限制：

在{{system.indexes}}插入数据，可以创建索引。但除此之外该表信息是不可变的(特殊的drop index命令将自动更新相关信息)。
{{system.users}}是可修改的。
{{system.profile}}是可删除的。

#### 数据库
多个文档组成集合，而多个集合组成了数据库。一个MongoDB实例可以 承载多个数据库，每个数据库都有独立的权限，在磁盘上，不同的数据库也 可放置在不同的文件夹中(启动时加directoryperdb选项)。 为了更好的组织数据，一般情况下，会把属于同一个应用程序(或同一种 业务类型)的所有数据放到一个数据库中。
关于数据库的命名需要注意以下几点:
- 不能是空字符串("")
- 不能以$开头
- 不能包含.(点号)和空字符串
- 数据库名字区分大小写(建议数据库名全部使用小写)
- 数据库名长度最多为64个字节
- 不要与系统保留的数据库名字相同，这些数据库包括:admin, local, config等


