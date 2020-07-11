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

# Redis.conf

启动的时候，就通过配置文件



**在redis-cli中sava可以保存配置文件**



##### 大小写

配置文件 unit单位 对大小写不敏感

##### 配置文件

```bash
# include /path/to/local.conf
# include /path/to/other.conf
```

##### 网络  

```bash
# 绑定ip（能访问redis的ip）
bind 127.0.0.1

# 保护模式
protected-mode yes

# 端口设置
prot 6379
```

##### 通用

```bash
# 以守护进程的方式运行（后台运行），默认是no，手动开启yes
daemonize yes

# 如果以后台的方式运行，我们就需要指定一个pid文件
pidfile /var/run/redis_6379.pid

# 日志
# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably) 生产环境
# warning (only very important / critical messages are logged)
loglevel notice

#生成的日志文件位置名
logfile ""

# 数据库数量名
databases 16

# 是否显示logo
always-show-logo yes
```

##### 快照

持久化，在规定时间内，执行了多少次操作，则会持久化到文件.rdb aof

redis是内存数据库如果没有持久化，那么数据断电就会失去

```bash
# 如果900秒内，如果至少一个key进行了修改，我们进行持久化操作
save 900 1
save 300 10
save 60 10000
# 我们之后学习持久化，会自己定义这个测试

# 持久化如果出错，是否继续工作
stop-writes-on-bgsave-error yes

# 是否压缩rdb文件，需要小号cpu资源
rdbcompression yes

# 保存rdb文件的时候，进行错误的校验
rdbchecksum yes

# 保存的目录
dir ./
```

##### 复制

> 主从复制

##### 安全

```bash
# 设置密码
127.0.0.1:6379> config set requirepass 123456
OK
# 进行验证（登录）
127.0.0.1:6379> auth 123456
```

##### 限制

> CLIENTS

```bash
# 限制能连接上redis的最大的客户端数量
maxclients 10000

# redis配置最大的内存容量
maxmemory <bytes>

# 内存达到上线之后的处理策略
# 1、volatile-lru：只对设置了过期时间的key进行LRU（默认值） 
# 2、allkeys-lru ： 删除lru算法的key   
# 3、volatile-random：随机删除即将过期key   
# 4、allkeys-random：随机删除   
# 5、volatile-ttl ： 删除即将过期的   
# 6、noeviction ： 永不过期，返回错误
maxmemory-policy noeviction
```

AOF

> APPEND ONLY MODE  aof配置

```bash
# 默认关闭
appendonly no
# 持久化文件的名字
appendfilename "appendonly.aof"

# 同步
# appendfsync always # 每秒都会同步，消耗性能
appendfsync everysec #每秒执行一次，可能会丢失一秒的数据
# appendfsync no	# 不执行sync，这个时候操作系统自己同步数据，速度罪最快
```

# 持久化

## RDB

##### 触发机制

1、save的规则满足的情况下,会自动触发rdb规则

2、执行flushall命令,也会触发我们的rdb规则!

3、退出redis ,也会产生rdb文件!

##### 恢复

1、只需要将rdb文件放在我们redis启动目录就可以, redis启动的时候会自动检查dump.rdb恢复其中的数据!

2、查看需要的存在的位置

```bash
127.0.0.1:6379> config get dir
1) "dir"
2) "/usr/local/bin"
```

##### 优缺点

###### 优点

1、适合大规模的数据恢复

2、对数据的完整性要求不高

###### 缺点

1、需要一定的时间间隔进行操作，如果redis意外宕机了，,这个最后一次修改数据就没有的了!

2、fork进程的时候,会占用- -定的内容空间! !

## AOF

> Append Only File
>
> 将我们所有的命令都记录下来，history，恢复的时候就把这个文件全部执行一遍
>
> 

##### 启动

默认是不开启的,我们需要手动进行配置!我们只需要将appendonly改为yes就开启了aof !
重启, redis就可以生效了!

##### 修复

如果redis的aof文件有错位，这时候redis是启动不起来的，我们需要修复这个aof文件

redis提供了一个工具

```bash
redis-check-aof --fix
```

##### 优缺点

###### 优点

1、每一次修改都同步,文件的完整会更加好!
2、每秒同步一次,可能会丢失-秒的数据
3、从不同步,效率最高的!

###### 缺点

1、相对于数据文件来说, aof远远大于rdb ,修复的速度也比rdb慢

2、Aof 运行效率也要比rdb慢,所以我们redis默认的配置就是rdb持久化

# 发布订阅

##### 订阅端

```bash
# 订阅一个频倒
127.0.0.1:6379> subscribe ahhtou
Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "ahhtou"
3) (integer) 1

1) "message" 		# 消息
2) "ahhtou"			# 哪个频道
3) "hello,ahhtou"	# 消息的具体内容
```

发送端

```bash
127.0.0.1:6379> publish ahhtou "hello,ahhtou"
(integer) 1
```

##### 使用场景

1、实时消息系统!
2、事实聊天! (频道当做聊天室,将信息回显给所有人即可! )
3、订阅,关注系统都是可以的!

# 主从复制

数据的复制是单向的，只能从主节点到节点

默认情况下，每台Redis服务器都说主节点

##### 环境配置

复制3个配置文件修改对应信息

1、端口

2、pid名字

3、log文件名称

4、dump.rdb

##### 查看信息

```bash
127.0.0.1:6381> info replication
# Replication
role:master
connected_slaves:0
master_replid:962bff5e2b5e8cdfdf4ac7bd12fab1d9b0599e32
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
```

##### 一主二从

**默认情况下,每台Redis服务器都是主节点;**我们一般情况下只用配置从机就好了!

一主（79）二从（80.81）

命令配置是临时的，去配置文件配置才是永久的

###### 从机

```bash
# 认老大
127.0.0.1:6380> slaveof 127.0.0.1 6379
OK

127.0.0.1:6380> info replication
# Replication
role:slave
master_host:127.0.0.1
master_port:6739
master_link_status:down
master_last_io_seconds_ago:-1
master_sync_in_progress:0
slave_repl_offset:0
master_link_down_since_seconds:1594458694
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:245c9cb4edb21cdb2d13c21754363e7b73639a13
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
```

从机只能读

```bash
127.0.0.1:6381> set k1 k2
(error) READONLY You can't write against a read only replica.
```

###### 主机

```bash
127.0.0.1:6379> info replication
# Replication
role:master
connected_slaves:2
#从机配置
slave0:ip=127.0.0.1,port=6381,state=online,offset=14,lag=0
slave1:ip=127.0.0.1,port=6380,state=online,offset=14,lag=0

master_replid:8e1bcbe8f4c9b282aa9a7cef4ccf213eed4ae60d
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:14
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:14
```

##### 复制原理

Slave启动成功连接到master后会发送一个sync同步命令
Master接到命令,启动后台的存盘进程,同时收集所有接收到的用于修改数据集命令,在后台进程执行完毕之后, master将传送
整个数据文件到slave ,并完成-次完全同步。
**全量复制**:而slave服务在接收到数据库文件数据后，将其存盘并加载到内存中。
**增量复制**: Master继续将新的所有收集到的修改命令依次传给slave ,完成同步
但是只要是重新连接master , -次完全同步(全量复制)将被自动执行

##### 谋权篡位

> 让自己变成主机

```bash
127.0.0.1:6381> slaveof no one
OK
```

# 哨兵模式

##### 概述

主从切换技术的方法是:当主服务器宕机后,需要手动把-台从服务器切换为主服务器,这就需要人工干预,费事费力,还会造成
-段时间内服务不可用。这不是一种推荐的方式,更多时候,我们优先考虑哨兵模式。Redis从2.8开始正式提供了Sentinel (哨
兵)架构来解决这个问题。
谋朝篡位的自动版,能够后台监控主机是否故障,如果故障了根据投票数**自动将从库转换为主库**。
哨兵模式是一种特殊的模式,首先Redis提供了哨兵的命令,哨兵是一一个独立的进程,作为进程,它会独立运行。其原理是* *哨兵
**通过发送命令,等待Redis服务器响应,从而监控运行的多个Redis实例。**

##### 测试

核心配置

```conf
# sentinel monitor 被监控的名称 host port 1
sentinel monitor myredis 127.0.0.1 6379 1
```

```bash
[root@ahhtou bin]# redis-sentinel MyConf/sentinel.conf
```



后面的数字1代表主机挂了 slave投票看让谁接替成主机。票多就会成为主机

##### 优缺点

###### 优点

1、哨兵集群,基于主从复制模式,所有的主从配置优点，它全有
2、主从可以切换,故障可以转移,系统的可用性就会更好
3、哨兵模式就是主从模式的升级,手动到自动,更加健壮!

###### 缺点

1、Redis不好啊在线扩容的,集群容量--旦到达上限,在线扩容就+分麻烦!
2、实现哨兵模式的配置其实是很麻烦的,里面有很多选择!

# 缓存穿透和雪崩

## 缓存穿透

##### 概念

缓存穿透的概念很简单,用户想要查询一-个数据,发现redis内存数据库没有,也就是缓存没有命中,于是向持久层数据库查询。发现也没有,于是本次查询失败。当用户很多的时候,缓存都没有命中(秒杀! ) , 于是都去请求了持久层数据库。这会给持久层数据库造成很大的压力,这时候就相当于出现了缓存穿透。

##### 解决方案

布隆过滤器和缓存空对象



## 缓存击穿

##### 概念

这里需要注意和缓存击穿的区别,缓存击穿,是指- -个key非常热点,在不停的扛着大并发,大并发集中对这一-个点进行访问，当这个key在失效的瞬间,持续的大并发就穿破缓存,直接请求数据库,就像在一个屏障 上凿开了一一个洞。
当某个key在过期的瞬间,有大量的请求并发访问,这类数据- -般是热点数据 ,由于缓存过期,会同时访问数据库来查询最新数据,并且回写缓存,会导使数据库瞬间压力过大。

##### 解决方案

##### 设置热点数据永不过期

从缓存层面来看,没有设置过期时间,所以不会出现热点key过期后产生的问题。

##### 加互斥锁

分布式锁:使用分布式锁,保证对于每个key同时只有一个线程去查询后端服务,其他线程没有获得分布式锁的权限,因此只需要等待即可。这种方式将高并发的压力转移到了分布式锁,因此对分布式锁的考验很大。

## 缓存雪崩

##### 概念

实集中过期,倒不是非常致命,比较致命的缓存雪崩,是缓存服务器某个节点宕机或断网。因为自然形成的缓存雪崩, 一定是在某个时间段集中创建缓存,这个时候,数据库也是可以顶住压力的。无非就是对数据库产生周期性的压力而已。而缓存服务节点的宕机，对数据库服务器造成的压力是不可预知的,很有可能瞬间就把数据库压垮。

##### 解决方案

###### redis高可用

这个思想的含义是,既然redis有可能挂掉,那我多增设几台redis ,这样-台挂掉之 后其他的还可以继续工作,其实就是搭建的集
群。( 异地多活! )

###### 限流降级

这个解决方案的思想是,在缓存失效后,通过加锁或者队列来控制读数据库写缓存的线程数量。比如对某个key只允许- -个线程查
询数据和写缓存,其他线程等待。I

###### 数据预热

数据加热的含义就是在正式部署之前,我先把可能的数据先预先访问一遍,这样部分可能大量访问的数据就会加载到缓存中。在即
将发生大并发访问前手动触发加载缓存不同的key ,设置不同的过期时间,让缓存失效的时间点尽量均匀。