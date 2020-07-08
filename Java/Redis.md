# 安装启动

## 安装

解压  tar -zxvf

make

make install

默认位置 在 usr/local/bin

## 配置

> redis.conf中修改

```conf
deamonize yes
```

## 启动

> 地址 /usr/local/bin

```bash
redis-server MyConf/redis.conf
```

## 链接

```bash
#指定端口号
redis-cli -p 6379
```

## 关闭

```bash
127.0.0.1:6379> shutdown
not connected> exit
```

# 入门

## 基础的知识

> redis默认有16个数据库

```bash
#切换数据库
127.0.0.1:6379> select 3
#清空当前数据库数据
127.0.0.1:6379> flushdb
#清空所有数据库数据
127.0.0.1:6379> flushall
#查看所有键
127.0.0.1:6379> key *
```

> redis是单线程（默认）

# 五大数据类型

## Redis-key

```bash
#设置
127.0.0.1:6379[1]> set name ahhtou
OK

#查看
127.0.0.1:6379[1]> get name
"ahhtou"

#查看key的类型
127.0.0.1:6379> type name
string

#查看key是否存在
127.0.0.1:6379> exists name
(integer) 1

#移除当前key（不常用）
127.0.0.1:6379> move name 1
(integer) 1

#查看全部
127.0.0.1:6379[1]> keys *
1) "name"

#设置过期时间，单位是秒
127.0.0.1:6379[1]> expire name 10
(integer) 1

#查看剩余过期时间
127.0.0.1:6379[1]> ttl name
(integer) 5

```



## String

### 基础

```bash
#追加字符串（不存在则新建）
127.0.0.1:6379>  set name ahhtou
OK
127.0.0.1:6379> append name Pro
(integer) 9
127.0.0.1:6379> get name
"ahhtouPro"

#获取字符串长度
127.0.0.1:6379> strlen name
(integer) 9
```

### 自增自减

```bash
127.0.0.1:6379> set views 0
OK
# 自增自减1
127.0.0.1:6379> incr views
(integer) 1
127.0.0.1:6379> decr views
(integer) 0
# 自增自减 步长
127.0.0.1:6379> incrby views 10
(integer) 10
127.0.0.1:6379> decrby views 10
(integer) 0
```

### 字符串范围

```bash
127.0.0.1:6379> get key1
"hello,ahhTou"
#截取部分
127.0.0.1:6379> getrange key1 0 3
"hell"
#查看全部
127.0.0.1:6379> getrange key1 0 -1
"hello,ahhTou"
```

### 替换

```bash
127.0.0.1:6379> setrange key1 0 ahhhhhhh
(integer) 12
127.0.0.1:6379> get key1
"ahhhhhhhhTou"
```

### setnx和setex

```bash
#setex 设置应该30s后过期的hello
127.0.0.1:6379> setex key1 30 hello
OK
127.0.0.1:6379> set key2 world
OK
#setnx 存在则set失败，不存在才能set成功
127.0.0.1:6379> setnx key2 ww
(integer) 0
127.0.0.1:6379> setnx key3 ww
(integer) 1
```

### 批量set和get

```bash
#批量get和set
127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3
OK
127.0.0.1:6379> mget k1 k2 k3
1) "v1"
2) "v2"
3) "v3"

#msetnx是原子性的操作，一起成功，一起失败
127.0.0.1:6379> msetnx k1 v1 k4 v4
(integer) 0
127.0.0.1:6379> get k4
(nil)
```

### 对象

```bash
#json 形式
127.0.0.1:6379> set user:2 {name:zhangsan,age:3}
OK
127.0.0.1:6379> get user:2
"{name:zhangsan,age:3}"

#redis独有的
127.0.0.1:6379> mset user:1:name zhangsan user:2:age 2
OK
127.0.0.1:6379> mget user:1:name user1:1:age
1) "zhangsan"
2) (nil)
```

### getset

```bash
127.0.0.1:6379> getset key hello
(nil)
127.0.0.1:6379> getset key world
"hello"
```

## List

### 查询

```bash
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
3) "one"
127.0.0.1:6379> lrange list 0 1
1) "three"
2) "two"
```

### 插入

```bash
# 头插
127.0.0.1:6379> LPUSH list one
(integer) 1
127.0.0.1:6379> Lpush list two
(integer) 2
127.0.0.1:6379> lpush list three
(integer) 3

# 尾插
127.0.0.1:6379> rpush list zero
(integer) 4
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
3) "one"
4) "zero"

```

### 移除

```bash
#从头移除
127.0.0.1:6379> lpop list
"three"

#从尾移除
127.0.0.1:6379> rpop list
"zero"

#移除指定value的key
127.0.0.1:6379> lrange list 0 -1
1) "there"
2) "two"
3) "one"
127.0.0.1:6379> lrem list 1 two #移除一个two
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "there"
2) "one"
```

### 截取

```bash
127.0.0.1:6379> lrange list 0 -1
1) "4"
2) "3"
3) "2"
4) "1"

#通过下表截取指定的长度（直接截断）
127.0.0.1:6379> ltrim list 1 2
OK

127.0.0.1:6379> lrange list 0 -1
1) "2"
2) "3"
```

### 移动

```bash
127.0.0.1:6379> rpush list 1
(integer) 1
127.0.0.1:6379> rpush list 2
(integer) 2
127.0.0.1:6379> rpush list 3
(integer) 3

# 移动
127.0.0.1:6379> rpoplpush list other
"3"

127.0.0.1:6379> lrange list 0 -1
1) "1"
2) "2"
127.0.0.1:6379> lrange other 0 -1
1) "3"

```

### 设置

```bash
127.0.0.1:6379> lrange list 0 0
1) "value"

#指定下标的值
127.0.0.1:6379> lset list 0 hello
OK

127.0.0.1:6379> lrange list 0 0
1) "hello"
```

### 插入

```bash
127.0.0.1:6379> lrange list 0 -1
1) "hello"
3) "world"

# 前插
127.0.0.1:6379> linsert list before "world" "other"
(integer) 3
127.0.0.1:6379> lrange list 0 -1
1) "hello"
2) "other"
3) "world"

# 后插
127.0.0.1:6379> linsert list after "world" "new"
(integer) 4
127.0.0.1:6379> lrange list 0 -1
1) "hello"
2) "other"
3) "world"
4) "new"

```

## Set

> set的值不能重复

### 添加

```bash
127.0.0.1:6379> sadd myset hello
(integer) 1
127.0.0.1:6379> sadd myset ahhtou
(integer) 1
127.0.0.1:6379> sadd myset redis
(integer) 1
```

### 查看

```bash
#查看元素
127.0.0.1:6379> smembers myset
1) "ahhtou"
2) "redis"
3) "hello"

#查看长度
127.0.0.1:6379> scard myset
(integer) 3
```

### 判断

```bash
#该value存在
127.0.0.1:6379> sismember myset hello
(integer) 1
#该value不存在
127.0.0.1:6379> sismember myset world
(integer) 0
```

### 移除

```bash
#移除set中的指定元素
127.0.0.1:6379> srem myset hello
(integer) 1

127.0.0.1:6379> smembers myset
1) "ahhtou"
2) "redis"
```

### 随机操作

```bash
# 随机抽取
127.0.0.1:6379> srandmember myset
"ahhtou"

# 随机删除
127.0.0.1:6379> spop myset
"ahhtou"
```

### 移动

```bash
127.0.0.1:6379> sadd myset hello world kuangshen
(integer) 3
127.0.0.1:6379> sadd myset2 set2
(integer) 1

#移动
127.0.0.1:6379> smove myset myset2 kuangshen
(integer) 1

127.0.0.1:6379> smembers myset
1) "world"
2) "hello"
127.0.0.1:6379> smembers myset2
1) "kuangshen"
2) "set2"

```

### 数字集合类

```bash
127.0.0.1:6379> sadd key1 a b c
(integer) 3
127.0.0.1:6379> sadd key c d e 
(integer) 3

#差集
127.0.0.1:6379> sdiff key1 key
1) "a"
2) "b"

#交集
127.0.0.1:6379> sinter key1 key
1) "c"

#并集
127.0.0.1:6379> sunion key1 key
1) "c"
2) "a"
3) "b"
4) "e"
5) "d"
```



## Hash

## Zset

