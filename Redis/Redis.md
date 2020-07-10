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

##### 存取

```bash
#追加字符串（不存在则新建）
127.0.0.1:6379>  set name ahhtou
OK
127.0.0.1:6379> append name Pro
(integer) 9
127.0.0.1:6379> get name
"ahhtouPro"

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

#getset 先get后查询
127.0.0.1:6379> getset key hello
(nil)
127.0.0.1:6379> getset key world
"hello"
```

##### 询问

```bash
#获取字符串长度
127.0.0.1:6379> strlen name
(integer) 9
```

##### 自增自减

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

##### 截取和替换

```bash
127.0.0.1:6379> get key1
"hello,ahhTou"
#截取部分
127.0.0.1:6379> getrange key1 0 3
"hell"
#查看全部
127.0.0.1:6379> getrange key1 0 -1
"hello,ahhTou"

#替换
127.0.0.1:6379> setrange key1 0 ahhhhhhh
(integer) 12
127.0.0.1:6379> get key1
"ahhhhhhhhTou"
```

##### 条件set

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

##### 对象

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

## List

##### 查询

```bash
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
3) "one"
127.0.0.1:6379> lrange list 0 1
1) "three"
2) "two"
```

##### 插入

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

##### 移除

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

##### 截取

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

##### 移动

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

##### 设置

```bash
127.0.0.1:6379> lrange list 0 0
1) "value"

#指定下标的值
127.0.0.1:6379> lset list 0 hello
OK

127.0.0.1:6379> lrange list 0 0
1) "hello"
```

##### 插入

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

##### 添加

```bash
127.0.0.1:6379> sadd myset hello
(integer) 1
127.0.0.1:6379> sadd myset ahhtou
(integer) 1
127.0.0.1:6379> sadd myset redis
(integer) 1
```

##### 查看

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

##### 判断

```bash
#该value存在
127.0.0.1:6379> sismember myset hello
(integer) 1
#该value不存在
127.0.0.1:6379> sismember myset world
(integer) 0
```

##### 移除

```bash
#移除set中的指定元素
127.0.0.1:6379> srem myset hello
(integer) 1

127.0.0.1:6379> smembers myset
1) "ahhtou"
2) "redis"
```

##### 随机操作

```bash
# 随机抽取
127.0.0.1:6379> srandmember myset
"ahhtou"

# 随机删除
127.0.0.1:6379> spop myset
"ahhtou"
```

##### 移动

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

##### 数字集合类

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

> Map集合, key-map 的时候是一个map集合

##### 存取

```bash
#单存
127.0.0.1:6379> hset myhash field ahhtou
(integer) 1

#单取
127.0.0.1:6379> hget myhash field
"ahhtou"

#多存（会覆盖）
127.0.0.1:6379> hmset myhash f1 hello f2 world
OK

#多取
127.0.0.1:6379> hmget myhash f1 f2
1) "hello"
2) "world"

#全取
127.0.0.1:6379> hgetall myhash
1) "field"
2) "ahhtou"
3) "f1"
4) "hello"
5) "f2"
6) "world"
```

##### 删除

```bash
#删除指定
127.0.0.1:6379> hdel myhash field
(integer) 1

127.0.0.1:6379> hgetall myhash
1) "f1"
2) "hello"
3) "f2"
4) "world"
```

##### 询问

```bash
#查询长度
127.0.0.1:6379> hlen myhash
(integer) 2

#是否存在
127.0.0.1:6379> hexists myhash f1
(integer) 1
127.0.0.1:6379> hexists myhash f3
(integer) 0

#全部的field
127.0.0.1:6379> hkeys myhash
1) "f1"
2) "f2"

#全部的value
127.0.0.1:6379> hvals myhash
1) "hello"
2) "world"
```

##### 自增自减

```bash
127.0.0.1:6379> hset myhash f3 5
(integer) 1
#自增（步长）
127.0.0.1:6379> hincrby myhash f3 2
(integer) 7
#自减（步长）
127.0.0.1:6379> hincrby myhash f3 -2
(integer) 5
```

##### 条件set

```bash
#不存在则可以设置，存在则不能设置（不能覆盖）
127.0.0.1:6379> hsetnx myhash f4 hello
(integer) 1
127.0.0.1:6379> hsetnx myhash f4 hello
(integer) 0
```

##### 对象

> 和String一样，但是更适合存储对象

## Zset

> 有序集合，在set的基础上，增加了一个值，set k1 v1 ,zset k1 score1 v1

##### 存取

```bash
# 单存
127.0.0.1:6379> zadd myset 1 one
(integer) 1
# 多存
127.0.0.1:6379> zadd myset 2 two 3 there
(integer) 2
# 查看全部
127.0.0.1:6379> zrange myset 0 -1
1) "one"
2) "two"
3) "there"

```

##### 排序

```bash
127.0.0.1:6379> zadd salary 2500 a
(integer) 1
127.0.0.1:6379> zadd salary 3000 b
(integer) 1
127.0.0.1:6379> zadd salary 500 c
(integer) 1
# 小到大，无限制
127.0.0.1:6379> zrangebyscore salary -inf +inf
1) "c"
2) "a"
3) "b"
# 小到大 并且限制大到2500，而且带上score值
127.0.0.1:6379> zrangebyscore salary -inf 2500 withscores
1) "c"
2) "500"
3) "a"
4) "2500"

# 大到小 并且限制小到600，而且带上score值
127.0.0.1:6379> zrevrangebyscore salary +inf 600 withscores
1) "b"
2) "3000"
3) "a"
4) "2500"
```

##### 移除

```bash
127.0.0.1:6379> zrem salary b
(integer) 1

127.0.0.1:6379> zrange salary 0 -1
1) "c"
2) "a"
```

##### 询问

```bash
#查询长度
127.0.0.1:6379> zcard salary
(integer) 2

#查询区间的成员数量
127.0.0.1:6379> zadd myset 1 hello
(integer) 1
127.0.0.1:6379> zadd myset 2 world 3 ahhtou
(integer) 2
127.0.0.1:6379> zcount myset 1 3
(integer) 3
127.0.0.1:6379> zcount myset 1 2
(integer) 2
```

# 三大特殊类型

## geospatial

##### geoadd

> 规则：两级无法直接添加 我们一般会下载城市数据，直接通过java程序一次性导入!
>
> 参数: key （维度 经度 名称）
>
> 维度：-180到180度
>
> 经度：-85.05112878 到 85.05112878度

```bash
# 一次添加一个
127.0.0.1:6379> geoadd china:city 116.40 39.90 beijing
(integer) 1
# 一次添加多个
127.0.0.1:6379> geoadd china:city 114.05 22.52 shenzhen 120.16 30.24 hangzhou 108.96 34.26 xian
(integer) 3
```

##### geopos

> 得到地理位置

```bash
#一次获取一个
127.0.0.1:6379> geopos china:city beijing
1) 1) "116.39999896287918091"
   2) "39.90000009167092543"

#一次获取多个
127.0.0.1:6379> geopos china:city beijing shanghai
1) 1) "116.39999896287918091"
   2) "39.90000009167092543"
2) 1) "121.47000163793563843"
   2) "31.22999903975783553" 
```

##### geodist

> 得到两地之间距离

```bash
# 获得距离
127.0.0.1:6379> geodist china:city beijing shanghai
"1067378.7564"
# 指定单位
127.0.0.1:6379> geodist china:city beijing shanghai km
"1067.3788"
```

##### georadius

> 找到坐标附近指定半径的地理位置名称

```bash
#范围内全部
127.0.0.1:6379> georadius china:city 110 30 1000 km
1) "chongqign"
2) "xian"
3) "shenzhen"
4) "hangzhou"

#显示查询出的输入坐标距离
127.0.0.1:6379> georadius china:city 110 30 1000 km withdist
1) 1) "chongqign"
   2) "341.9374"
2) 1) "xian"
   2) "483.8340"
3) 1) "shenzhen"
   2) "924.6408"
4) 1) "hangzhou"
   2) "977.5143"


# 显示查询出的经纬
127.0.0.1:6379> georadius china:city 110 30 1000 km withcoord
1) 1) "chongqign"
   2) 1) "106.49999767541885376"
      2) "29.52999957900659211"
2) 1) "xian"
   2) 1) "108.96000176668167114"
      2) "34.25999964418929977"
3) 1) "shenzhen"
   2) 1) "114.04999762773513794"
      2) "22.5200000879503861"
4) 1) "hangzhou"
   2) 1) "120.1600000262260437"
      2) "30.2400003229490224"
127.0.0.1:6379> 


#限制数量
127.0.0.1:6379> georadius china:city 110 30 1000 km withcoord count 2
1) 1) "chongqign"
   2) 1) "106.49999767541885376"
      2) "29.52999957900659211"
2) 1) "xian"
   2) 1) "108.96000176668167114"
      2) "34.25999964418929977"
127.0.0.1:6379> 

```

##### georadiusbymembe

> 找出指定元素周围的其他元素

```bash
127.0.0.1:6379> georadiusbymember china:city beijing 1000 km
1) "beijing"
2) "xian"
```

##### geohash

> 讲二维的经纬度转换成一维的字符串

```bash
127.0.0.1:6379> geohash china:city beijing chongqign
1) "wx4fbxxfke0"
2) "wm5xzrybty0"
```

##### 底层操作

> 底层zset

```bash
#查看地图中全部元素
127.0.0.1:6379> zrange china:city 0 -1
1) "chongqign"
2) "xian"
3) "shenzhen"
4) "hangzhou"
5) "shanghai"
6) "beijing"

# 实现移除
127.0.0.1:6379> zrem china:city chongqign
(integer) 1

```

## Hyperloglog

##### 什么是基数

A{1,3,5,7,8} B{1,3.5,7,8}

基数（不重复的元素） = 5 ，可以接受误差

##### 简介

Redis 2.8.9版本就更新了Hyperloglog 数据结构!
Redis Hyperloglog基数统计的算法!
优点:占用的内存是固定, 2^64不同的元素的技术,只需要废12KB内存!如果要从内存角度来比较的话Hyperloglog首选!
**网页的UV ( -个人访问一个网站多次,但是还是算作-一个人! )**
传统的方式，set保存用户的id ,然后就可以统计set中的元素数量作为标准判断!
这个方式如果保存大量的用户id ,就会比较麻烦!我们的目的是为了计数,而不是保存用户id ;
0.81%错误率!统计UV任务,可以忽略不计的! .

##### 测试使用

```bash
127.0.0.1:6379> pfadd mykey a b c d e f g h i j 
(integer) 1
127.0.0.1:6379> pfcount mykey
(integer) 10
127.0.0.1:6379> pfadd mykey2 i j z x c v  b n m
(integer) 1
127.0.0.1:6379> pfcount mykey2
(integer) 9

#合并
127.0.0.1:6379> pfmerge mykey3 mykey mykey2
OK

127.0.0.1:6379> pfcount mykey3
(integer) 15

```

## Bitmaps

##### 位储存

统计用户信息,活跃,不活跃!登录、未登录!打卡, 365打卡!两个状态的,都可以使用Bitmaps !Bitmaps位图,数据结构!都是操作二进制位来进行记录,就只有0和1两个状态!
365天= 365 bit 1字节= 8bit 46 个字节左右!

##### 测试

```bash
#记录7天打卡
127.0.0.1:6379> setbit sign 0 1
(integer) 0
127.0.0.1:6379> setbit sign 1 0
(integer) 0
127.0.0.1:6379> setbit sign 2 0
(integer) 0
127.0.0.1:6379> setbit sign 3 1
(integer) 0
127.0.0.1:6379> setbit sign 4 1
(integer) 0
127.0.0.1:6379> setbit sign 5 0
(integer) 0
127.0.0.1:6379> setbit sign 6 0
(integer) 0

#查看打卡了没
127.0.0.1:6379> getbit sign 3
(integer) 1

#统计打卡的天数
127.0.0.1:6379> bitcount sign 
(integer) 3
127.0.0.1:6379> bitcount sign 0 5
(integer) 3
```

# 事务

一般的关系型数据库要么同时成功，要么同时失败，原子性！

**Redis单条命令是保证原子性的，但是事务不保证原子性**

Redis事物本质：一组命令的集合！一个事务中的所有命令都会被序列化,在事务执行过程的中,会按照顺序执行!

**一次性、顺序性、排他性**！执行一些列的命令

**Redis事务没有隔离级别的概念**

所有的命令在事务中,并没有直接被执行!只有发起执行命令的时候才会执行! Exec



Redis的事务：

- 开启事务（multi）
- 命令入队（...）
- 执行事务（exec）

##### 执行事务

```bash
# 开启事务
127.0.0.1:6379> multi
OK

#命令入队
127.0.0.1:6379> set k1 v1 
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED

#执行
127.0.0.1:6379> exec
1) OK
2) OK
3) "v2"
4) OK
```

##### 放弃事务

```bash
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2 
QUEUED
127.0.0.1:6379> set k4 v4
QUEUED

#放弃
127.0.0.1:6379> discard 
OK
127.0.0.1:6379> get k4
(nil)
```

##### 编译型异常

>  代码有问题，命令错误  所有命令都不会执行

```bash
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
#编译异常
127.0.0.1:6379> getset k3
(error) ERR wrong number of arguments for 'getset' command
127.0.0.1:6379> set k4 k4
QUEUED
127.0.0.1:6379> set k5 k5
QUEUED
127.0.0.1:6379> exec
(error) EXECABORT Transaction discarded because of previous errors.
#结果为空
127.0.0.1:6379> get k5
(nil)
```

##### 运行时异常

> （1/0） 如果如果事务队列中存在语法性,那么执行命令的时候,其他命令式可以正常执行的

```ba
127.0.0.1:6379> set k1 "v1"
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> incr k1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> exec
1) (error) ERR value is not an integer or out of range
2) OK
3) "v2"

```

# 锁

##### 悲观锁

- 很悲观，认为什么时候都会出问题,无论做什么都会加锁!

##### 乐观锁

- 很乐观，认为什么时候都不会出问题,所以不会上锁!更新数据的时候去判断-下,在此期间是否有人修改过这个数据，
- 获取version
- 更新的时候比较version

##### Redis监视测试

###### 正常执行成功

```bash
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
# 监视 money 对象
127.0.0.1:6379> watch money
OK
# 事务正常结束，数据期间没有发生变动，这个时候就正常执行成功!
127.0.0.1:6379> multi
OK
127.0.0.1:6379> decrby money 20
QUEUED
127.0.0.1:6379> incrby out 20
QUEUED
127.0.0.1:6379> exec
1) (integer) 80
2) (integer) 20
```

测试多线程修改值,使用watch可以当做redis的乐观锁操作!

```bash
# 监视money（乐观锁）
127.0.0.1:6379> watch money
OK
127.0.0.1:6379> mulit
(error) ERR unknown command `mulit`, with args beginning with: 
127.0.0.1:6379> multi
OK
127.0.0.1:6379> decrby money 10
QUEUED
127.0.0.1:6379> incrby out 10
QUEUED
# 执行之前，另一个线程修改了money会导致事务失败（自动解锁）
127.0.0.1:6379> exec
(nil)
```

# Jedis

> 什么是Jedis是Redis官方推荐的java连接开发工具!使用Java 操作Redis中间件!如果你要使用java操作redis ,那么一定要对Jedis十分的熟悉!

##### 依赖

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.2.0</version>
</dependency>
```

##### 编码测试

- 连接数据库

- 操作命令
- 断开链接

```java
Jedis jedis = new Jedis("127.0.0.1", 6379);

Transaction multi = jedis.multi();

JSONObject jsonObject = new JSONObject();
jsonObject.put("hello", "world");
jsonObject.put("name", "ahhTou");

String s = jsonObject.toJSONString();

try {
    multi.set("user1", s);
    multi.set("user2", s);

    multi.exec();
} catch (Exception e) {
    e.printStackTrace();
} finally {
    System.out.println(jedis.get("user1"));
    System.out.println(jedis.get("user2"));
    jedis.close();

}
```

# SpringBoot整合

##### Jedis？

jedis :采用的直连,多个线程操作的话,是不安全的,如果想要避免不安全的,使用jedis pool 连接池! BIO
lettuce :采用netty ,实例可以再多个线程中进行共享,不存在线程不安全的情况!

##### 原理

```java
@Bean
@ConditionalOnMissingBean(
    name = {"redisTemplate"}
)
public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    // 默认的RedisTemplate没有过多的设置，redis对象都需要序列化
    // 两个泛型都说Object类型，我们后使用需要强制类型转换
    RedisTemplate<Object, Object> template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}

@Bean
@ConditionalOnMissingBean
// 由于String 是redis中最常用的类型，所以单独提出来
public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}
```

##### 测试

导入依赖

配置连接

```properties
spring.redis.host=127.0.0.1
spring.redis.port=6379
```

测试

```java
    @Test
    void contextLoads() {

        // redisTemplate
        // opsForValue 操作字符串 类似String
        // opsForList 操作List 类似List
        // opsFrSet
        // opsForZSet
        // opsForGeo
        // opsForHyperLogLog


        // 除了基本的操作，我们常用的方法都可以直接redisTempLate操作

        // 获取redis的连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();

        redisTemplate.opsForValue().set("myKey","ahhTou");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
```

##### 序列化

pojo类不序列化会报错，传入redis对象时会报错

##### 自定义模板

> 自定义redisTemplate模板

```java
package com.kuang;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) throws UnknownHostException {
        // 为了开发方便，一般直接使用<String, Object>
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);

        // 序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式也采用jackson
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        
        return template;
    }


}
```