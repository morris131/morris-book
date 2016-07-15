BSON可以理解为在JSON基础上添加了一些新的数据类型，包括Date，正则 表达式，对数值类型的更进一步划分等。

 数据类型 类型编号 数据类型 类型编号
 Double
 1 Regular Expression
 11
 String
 2 JavaScript
 13
 Object
 3 Symbol
 14
 Array
 4 JavaScript(Scope)
 15
 Binary data
 5 32一bit integer
 16
 Object id
 7 Timestamp
 17
 Boolean
 8 64一bit integer
 18
 Date
 9 Min Key
 255
 Null 10 Max Key
 127



基本数据类型

null：表示空值或不存在的字段
布尔：有两个值true或false
数值：类型支持32-int，64-int以及64-double
字符串：使用UTF-8对字符串进行编码
二进制数据：可以保存由任意字节组成的字符串
正则表达式：主要用于查询，使用正则表达式作为限定条件
JavaScript： 文档中可以包含任意的JavaScript代码

日期
MongoDB中，日期类型是一个64位的整数，它代表的是距Unix epoch的 毫秒数。
MongoDB在存储时间时，先转化为UTC时间， 北京时间(CST)=UTC+8个小时。
MongoDB Shell中可以使用new Date或ISODate来创建时间对象，在进行 显示时，Shell会根据本地时间去设置显示日期对象。


ObjectId
Objectld由24个十六进制字符构成，每个字节存储两位十六进制数字，总共需 12字节存储空间。
例如：


{
"_id"
:
Objectld
(
"55212126e05a3e401e3efe46"
)}
每个字节代表的含义如下：






内嵌文档
文档可以作为键的值，这样的文档称为内嵌文档。内嵌文档可以使数据不用 保存成扁平结构的键值对，从而使数据组织方式更加自然。
例如:下面是一个与博客管理有关的文档


{

	_id
:<
Objectldl
>,

	title
:
"MongoDB Date Model"
,

	author
:
"jike"
，

	comments
:[

		
{
who
:
"John"
,
comment
:"
Good"
},

		
{
who
:
"Joe"
,
coment
:
"Excellent"}

	]

}


数组
数组是使用方括号来表示的一组值，它既可以作为有序对象(列表、栈、队 列)，也能作为无序对象(如集合)来操作。
数组中可以包含不同数据类型的元素(字符串、浮点数、文档等)。
例如:[3.14,"hello",[1,2,3],{"key":"MongoDB"}]。
针对数组MongoDB提供了许多特定的操作符，例如:$push, $pop, $pull，$slice，$addToSet等。
MongoDB可自动的为数组元素建立Multikey索引。






      
