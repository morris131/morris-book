# redis key

## 命令
- del key 在key存在时删除 key。
- dump key 序列化给定 key ，并返回被序列化的值
- exists key 检查给定key是否存在。
- expire key seconds 给定key设置过期时间，以秒计
- keys pattern 查找所有符合给定模式( pattern)的 key
- move key db 将当前数据库的 key 移动到给定的数据库 db 当中
- persist key 移除 key 的过期时间，key 将持久保持
- ttl key 以秒为单位，返回给定key的剩余生存时间(TTL, time to live)
- pexpire key milliseconds 设置 key 的过期时间以毫秒计。
- pexpireat key milliseconds-timestamp 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计
- randomkey 从当前数据库中随机返回一个 key 。
- rename key newkey 修改key的名称，存在会替换
- renamenx key newkey 仅当 newkey 不存在时，将 key 改名为 newkey
- type key 返回 key 所储存的值的类型

## 使用
```
127.0.0.1:6379> set k1 aa
OK
127.0.0.1:6379> get k1
"aa"
127.0.0.1:6379> exists k1
(integer) 1
127.0.0.1:6379> keys *
1) "k1"
127.0.0.1:6379> keys k*
1) "k1"
127.0.0.1:6379> del k1
(integer) 1
```
