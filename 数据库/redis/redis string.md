# redis key

## 命令
- set key value：设置指定 key 的值
- get key：获取指定 key 的值
- getrange key start end：返回key中字符串值的子字符
- getset key value：将给定key的值设为value ，并返回key的旧值(old value)
- mget key1 [key2..]：获取所有(一个或多个)给定 key 的值
- setex key seconds value：将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。
- setnx key value：只有在 key 不存在时设置 key 的值。
10	SETRANGE key offset value用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。
11	STRLEN key 返回 key 所储存的字符串值的长度。
12	MSET key value [key value ...]同时设置一个或多个 key-value 对。
13	MSETNX key value [key value ...] 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
15	INCR key
将 key 中储存的数字值增一。
16	INCRBY key increment
将 key 所储存的值加上给定的增量值（increment） 。
17	INCRBYFLOAT key increment
将 key 所储存的值加上给定的浮点增量值（increment） 。
18	DECR key
将 key 中储存的数字值减一。
19	DECRBY key decrement
key 所储存的值减去给定的减量值（decrement） 。
20	APPEND key value
如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。

## 使用
```
(integer) 1
```
