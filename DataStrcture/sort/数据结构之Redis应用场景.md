### 数据结构之Redis应用场景

### 说明

- 1、本文参考了[Redis开发实战指南](https://gnuhpc.gitbooks.io/redis-all-about/Intro/)GitBook，还有[《Redis实战》](https://github.com/guoxiaoxu/redis_Java)自己之前的笔记。主体框架来自[这里](http://www.jb51.net/article/54774.htm)。
- 2、感谢大佬们的付出，在这里自己只是记录，加深自己的印象。

- 3、本文会同步放到我自己的[GitHub](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/DataStrcture/sort),方便自己复习，希望的可以点个star。
- 4、这个系列也算是对@阿里大佬[梁桂钊](https://juejin.im/post/5a94a8ca6fb9a0635c049e67)提出的面试通关要点解答。
- ![](https://i.imgur.com/SWsW3gQ.jpg)
- 5、原谅我现在还没能力自己总结，所以只能临摹别人，加自己的理解，结合自己之前的笔记
- 6、如有拼写错误，还请谅解。有不同观点，可以评论出来，一起努力。


gogogo 我们正式进入主题吧，
### Redis中5种数据结构的使用场景介绍

Redis is an open source (BSD licensed), in-memory data structure store, used as a database, cache and message broker. It supports data structures such as strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs and geospatial indexes with radius queries. Redis has built-in replication, Lua scripting, LRU eviction, transactions and different levels of on-disk persistence, and provides high availability via Redis Sentinel and automatic partitioning with Redis Cluster。

推介看《Redis In Action》非常非常非常不错，尤其前三章。使用Redis的关键点不是设计数据库，而是如何选择合适场景的数据结构。就像我们之前操作容器时，选择不同容易装不同对象。每个都有各自的各点，如Set不能重复、无序啊，List可以重复，有序那。Map存放键值对。具体的内容过两天看源码的时候分享给大家。今天的主题是以下五种数据结构。

主要5种数据结构：

- String ——>字符串
- Hash  ——>字典(哈希)
- List ——>列表
- Set ——>集合
- Sorted Set ——>有序集合

### 1. String 字符串

String数据结构是简单的Key-Value类型，Value不仅可以是String，也可以是数字。使用String类型，可以完全实现目前`Memcached`(只有一种String)的功能，并且效率更高，还可以享受Redis的定时持久化(RDB模式或AOF模式)，提供日志及Replication等功能。除了提供与Memcached的get、set、incr、decr等操作外，Redis还提供了下面一些操作：

1. LEN niushuai：O(1)获取字符串长度
2. APPEND niushuai redis：往字符串 append 内容，而且采用智能分配内存（每次2倍）
3. 设置和获取字符串的某一段内容
4. 设置及获取字符串的某一位（bit）
5. 批量设置一系列字符串的内容
6. 原子计数器
7. GETSET 命令的妙用，请于清空旧值的同时设置一个新值，配合原子计数器使用

```java
//1、计数器的使用
    String articleId = String.valueOf(conn.incr("article:"));     //String.valueOf(int i) : 将 int 变量 i 转换成字符串
```
放一些常用命令，方便自己复习
```java
set key value [ex 秒数] / [px 毫秒数]  [nx] /[xx]     //返回1表示成功，0失败
incr key                                             //对key做加加操作，
decr key                                             //对key做减减操作
setnx key value                                      //仅当key不存在时才set，nx表示not exist。(重点)
mset key1 value1   key2 value2 .。。                 //一次设置多个key，
--------------------------------------------------------------------------------
get key                                              //如果key不存在返回null
mget key1 key2 ... keyN                              //一次获取多个key的值，如果对于的key不存在，则返回null
getset key value                                     //设置新值返回旧值。
```
分布式锁的思路：

1. C3发送SETNX lock.foo 想要获得锁，由于C0还持有锁，所以Redis返回给C3一个0
2. C3发送GET lock.foo 以检查锁是否超时了，如果没超时，则等待或重试。
3. 反之，如果已超时，C3通过下面的操作来尝试获得锁：
4. GETSET lock.foo
5. 通过GETSET，C3拿到的时间戳如果仍然是超时的，那就说明，C3如愿以偿拿到锁了。
6. 如果在C3之前，有个叫C4的客户端比C3快一步执行了上面的操作，那么C3拿到的时间戳是个未超时的值，这时，C3没有如期获得锁，需要再次等待或重试。留意一下，尽管C3没拿到锁，但它改写了C4设置的锁的超时值，不过这一点非常微小的误差带来的影响可以忽略不计。

伪代码：

```java
# get lock
lock = 0
while lock != 1:
    timestamp = current Unix time + lock timeout + 1       //时间戳
    lock = SETNX lock.foo timestamp                        //尝试获取锁，返回0，则下面检查是否超时，GET。
    if lock == 1 or (now() > (GET lock.foo) and now() > (GETSET lock.foo timestamp)):
        break;                                            //关键点上面这条代码
    else:
        sleep(10ms)

# do your job
do_job()

# release
if now() < GET lock.foo:
    DEL lock.foo
```

gogogo
```java
incr key                       //对key做加加操作，
decr key                       //对key做减减操作

incrby key interge             //对key加指定的数

incrbyfloat key floatnumber     //针对浮点数

append key value               //返回新字符的长度

substr key start end           //并不会修改字符串，返回截取的字符串

getrange key start end         //返回子串

strlen  key                     //取指定key的Value
```

### 2. Hash——字典

在Memcached中，经常就爱那个一些结构化的信息打包成hashMap，在客户端序列化后存储为一个字符串的值，(通常为JSON格式)，比如用户的昵称、年龄、性别、积分。这时候在需要修改其中某一项时，通用需要将字符串(JSON)取出来,然后进行反序列化，修改某一项的值，在序列化成字符串(JSON)存储回去。而Redis和Hash结构可以使你像在数据库中Update一个属性一样只修改某一项属性值。

底层实现是hash table，一般操作复杂度是O(1)，要同时操作多个field时就是O(N)，N是field的数量。应用场景：土法建索引。比如User对象，除了id有时还要按name来查询。

常用命令：
```java
hset key field value                        //设置hash field为指定值，如果key不存在，则先创建
hsetnx                                      //同时，如果存在返回0，nx是not exist的意思
hmset key filed1 value1 ... filedN valueN   //设置多个值

hget key field                              //获取指定的hash field
hmget key field1 field2                     //获取全部指定的field
-------------------------------------------------------------------------------
hincrby key field integer                  //将指定的hash field加上给定值
hexists key field                          //测试指定field是否存在
hdel  key field                            //删除指定的field
hlen key                                   //返回会指定hash的field数量

hgetall                                    //返回hash所有field和value
```
场景：

```java

/**
 * 使用Redis重新实现登录cookie，取代目前由关系型数据库实现的登录cookie功能
 * 1、将使用一个散列来存储登录cookie令牌与与登录用户之间的映射。
 * 2、需要根据给定的令牌来查找与之对应的用户，并在已经登录的情况下，返回该用户id。
 */

//1、尝试获取并返回令牌对应的用户    注意“login：” 一般使用冒号做分割符，这是不成文的规矩
 conn.hget("login:", token);
//2、维持令牌与已登录用户之间的映射。
conn.hset("login:", token, user);
//6、移除被删除令牌对应的用户信息
conn.hdel("login:", tokens);
---------------------------注意中间还有其他步骤----------------------------------
/**
 * 使用cookie实现购物车——就是将整个购物车都存储到cookie里面，
 * 优点：无需对数据库进行写入就可以实现购物车功能，
 * 缺点：怎是程序需要重新解析和验证cookie，确保cookie的格式正确。并且包含商品可以正常购买
 * 还有一缺点：因为浏览器每次发送请求都会连cookie一起发送，所以如果购物车的体积较大，
 * 那么请求发送和处理的速度可能降低。
 * -----------------------------------------------------------------
 * 1、每个用户的购物车都是一个散列，存储了商品ID与商品订单数量之间的映射。
 * 2、如果用户订购某件商品的数量大于0，那么程序会将这件商品的ID以及用户订购该商品的数量添加到散列里。
 * 3、如果用户购买的商品已经存在于散列里面，那么新的订单数量会覆盖已有的。
 * 4、相反，如果某用户订购某件商品数量不大于0，那么程序将从散列里移除该条目
 * 5、需要对之前的会话清理函数进行更新，让它在清理会话的同时，将旧会话对应的用户购物车也一并删除。
 */

//1、从购物车里面移除指定的商品        注意"cart:" 可以在数据迁移、转换 、和删除时轻松识别
conn.hdel("cart:" + session, item);
//2、将指定的商品添加到购物车
conn.hset("cart:" + session, item, String.valueOf(count));
//6、移除被删除令牌对应的用户信息
conn.hdel("login:", tokens);

--------------------------------------------------------------------------------
//5、将文章信息存储到一个散列里面。
//HMSET key field value [field value ...]
//同时将多个 field-value (域-值)对设置到哈希表 key 中。
//此命令会覆盖哈希表中已存在的域。
conn.hmset(article, articleData);

//为哈希表 key 中的域 field 的值加上增量 increment 。
//增量也可以为负数，相当于对给定域进行减法操作。
//HINCRBY counter page_view 200
conn.hincrBy(article, "votes", 1L);

//如果返回1表示还没有这个数据，注意-1后面的L
conn.hincrBy(article, "votes", -1L);
//3、根据文章ID获取文章的详细信息
Map<String,String> articleData = conn.hgetAll(id);
--------------------------测试-------------------------------------------------
//2、测试文章的投票过程
articleVote(conn, "other_user", "article:" + articleId);
String votes = conn.hget("article:" + articleId, "votes");
System.out.println("我们为该文章投票，目前该文章的票数 " + votes);
assert Integer.parseInt(votes) > 1;

```
其实应用场景还有很多。这里只是摘出程序片段中的一部分，具体可以[点这里](https://github.com/guoxiaoxu/redis_Java/tree/master/1_article_voted)。需要注意的是组合使用，取其精华、去其糟粕。

### 3. List——列表

List说白了就是链表(Redis使用双端链表实现的List)，使用List结构，我们可以轻松的实现最新消息的排行等功能(比如Twitter的TimeLine)，List的另一个应用就是消息队列，可以利用List的PUSH操作，将任务存在List中，然后工作线POP操作取出任务执行。Redis还提供了操作List中某一段元素的API，可以直接查询，删除List中某一段元素。

![](https://i.imgur.com/HrfDKXM.jpg)

命令：
```java
lpush key string                        //在key对应的list的头部添加元素
rpush key string                        //在list的尾部添加元素

lpushx key value                        //如果key不存在，什么都不做
rpushx key value                        //同上

linsert key BEFORE|AFTER pivot value     //在list对应的位置之前或之后
---------------------------------------------------------------------------
llen key                                 //查看列表对应的长度
lindex key index                         //指定索引的位置，0第一个

lrange key start end                     //查看一段列表
lrange key 0 -1                          // -1表示返回所有数据

ltrim key start end                      //保留指定区间的元素

lset key index value                     //idnex表示指定索引的位置

ldel                                     //删除元素

blpop key [key ...] timeout               //阻塞队列
brpop key [key ...] timeout
```

基于redis构建消息队列[点这里，写的非常不错](https://lanjingling.github.io/2016/01/29/messagequeue-redis/)。为了自己复习，就拿；来主义了。

一般来说，消息队列有两种场景：一种是发布者订阅者模式；一种是生产者消费者模式。利用redis这两种场景的消息队列都能够实现。

- 生产者消费者模式：生产者生产消息放到队列里，多个消费者同时监听队列，谁先抢到消息谁就会从队列中取走消息；即对于每个消息只能被最多一个消费者拥有。（常用于处理高并发写操作）
- 发布者订阅者模式：发布者生产消息放到队列里，多个监听队列的消费者都会收到同一份消息；即正常情况下每个消费者收到的消息应该都是一样的。（常用来作为日志收集中一份原始数据对多个应用场景）

1、redis作为消息中间件：
- 1）Producer/ConsumerMode:
该方式是借助redis的list结构实现的。Producer调用redis的lpush往特定key里塞入消息，Consumer调用`brpop`（阻塞方法）去不断监听该key。
```java
// producer code
String key = "demo:mq:test";
String msg = "hello world";
redisDao.lpush(key, msg);
// consumer code
String key = "demo:mq:test";
while (true) {
    // block invoke
   List<String> msgs = redisDao.brpop(BLOCK_TIMEOUT, listKey);    //注意brpop
       if (msgs == null) continue;
           String jobMsg = msgs.get(1);
               processMsg(jobMsg);
}
```

1、使用redis怎么做消息队列

- 1、首先redis它的设计是用来做缓存的，但是由于它自身的某种特性使得他可以用来做消息队列。它有几个阻塞式的API（brpop、Sub，他们都是阻塞版的）可以使用，正是这些阻塞式的API让他有做消息队列的能力。
- 2、其次，消息队列的其他特性例如FIFO也很容易实现，只需要一个List对象从头取数据，从尾部塞数据即可实现。

2、订阅-发布系统

Pub/Sub 从字面上理解就是发布（Publish）与订阅（Subscribe），在 Redis 中，你可以设定对某一个 key 值进行消息发布及消息订阅，当一个 key 值上进行了消息发布后，所有订阅它的客户端都会收到相应的消息。这一功能最明显的用法就是用作实时消息系统，比如普通的即时聊天，群聊等功能。

代码实现[点这里和上面同一个人，已征求](https://lanjingling.github.io/2015/11/20/redis-pub-sub/)：

要使用Jedis的Publish/Subscribe功能，必须编写对JedisPubSub的自己的实现。
```java

public class MyListener extends JedisPubSub {

	// 取得订阅的消息后的处理
	@Override
	public void onMessage(String channel, String message) {
		// TODO Auto-generated method stub
		System.out.println(channel + "=" + message);
	}

	// 取得按表达式的方式订阅的消息后的处理
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		// TODO Auto-generated method stub
		System.out.println(pattern + ":" + channel + "=" + message);
	}
	// 初始化订阅时候的处理
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub
		System.out.println("初始化 【频道订阅】 时候的处理  ");
	}
	// 取消订阅时候的处理
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub
		System.out.println("// 取消 【频道订阅】 时候的处理  ");
	}

	// 初始化按表达式的方式订阅时候的处理
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub
		System.out.println("初始化 【模式订阅】 时候的处理  ");
	}
	// 取消按表达式的方式订阅时候的处理
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub
		System.out.println("取消 【模式订阅】 时候的处理  ");
	}
}
```

2、Sub

```java
public class Sub {
	public static void main(String[] args) {
		try {
			Jedis jedis = getJedis();
			MyListener ml = new MyListener();

			//可以订阅多个频道
			//jedis.subscribe(ml, "liuxiao","hello","hello_liuxiao","hello_redis");
			//jedis.subscribe(ml, new String[]{"hello_foo","hello_test"});
			//这里启动了订阅监听，线程将在这里被阻塞
			//订阅得到信息在lister的onPMessage(...)方法中进行处理

			//使用模式匹配的方式设置频道
			jedis.psubscribe(ml, new String[]{"hello_*"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

需要注意的是：
> 调用subscribe()或psubscribe() 时，当前线程都会阻塞。

3、Pub

```java
public class Pub {
	public static void main(String[] args) {
		try {
			Jedis jedis = getJedis();
			jedis.publish("hello_redis","hello_redis");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

### 4. Set——集合（重点）

Set 就是一个集合，集合的概念就是一堆不重复值的组合。利用 Redis 提供的 Set 数据结构，可以存储一些集合性的数据。比如在Twitter应用中，可以将一个用户所有的关注人存在一个集合中，将其所有粉丝存在一个集合。因为 Redis 非常人性化的为集合提供了求交集、并集、差集等操作，那么就可以非常方便的实现如共同关注、共同喜好、二度好友等功能，对上面的所有集合操作，你还可以使用不同的命令选择将结果返回给客户端还是存集到一个新的集合中。

1. 共同好友、二度好友
2. 利用唯一性，可以统计访问网站的所有独立 IP
3. 好友推荐的时候，根据 tag 求交集，大于某个 threshold 就可以推荐

命令：
```java
sadd key member               //添加元素，成功返回1，

srem key member               //移除元素，成功返回1

spop key                     //删除并返回，如果set是空或者不存在则返回null

srandmember key              //同spop，随机取set中一个元素，但是不删除

smove srckey dstkey member   //集合间移动元素

scard key                    //查看集合的大小，如果set是空或者key不存在则返回0

sismember key member         //判断member是否在Set中，存在返回1，0表示不存在或key不存在

smembers  key                 //获取所有元素，返回key对应的所有元素，结果是无序的哦

--------------------------------------------------------------------------------
//集合交集
sinter key1 key2                  //返回所有给定key的交集
sinterstore dstkey key1 key2      //同sinter，并同时保存并集到dstkey下

//集合并集
sunion key1 key2                 //返回所有给定key的并集
sunionstore dstkey key1 key2      //同sunion，并同时保存并集到dstkey下

//集合差集
sdiff key1 key2                   //返回给定key的差集
sdiffstore dstkey key1 key2       //同sdiff，并同时保存并集到dstkey下
```

为了防止用户对同一篇文章进行多次投票，需要为每篇文章记录一个已投票用户名单。使用集合来存储已投票的用户ID。由于集合是不能存储多个相同的元素的，所以不会出现同个用户对同一篇文章多次投票的情况。
代码实现：

```java
2、程序需要使用SADD将文章发布者的ID添加到记录文章已投票用户名单的集合中
并使用EXPIRE命令为这个集合设置一个过期时间，让Redis在文章发布期满一周之后自动删除这个集合。
//2、添加到记录文章已投用户名单中，
conn.sadd(voted, user);
//3、设置一周为过期时间
conn.expire(voted, ONE_WEEK_IN_SECONDS);
--------------------------------------------------------------------------------
4、检查用户是否第一次为这篇文章投票，如果是第一次，则在增加这篇文章的投票数量和评分。
将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
if (conn.sadd("voted:" + articleId, user) == 1) {
    //ZINCRBY salary 2000 tom   # tom 加薪啦！
    conn.zincrby("score:", VOTE_SCORE, article);
    //HINCRBY counter page_view 200
    conn.hincrBy(article, "votes", 1L);
}
---------------------------------------------------------------------------------
/**
 * 记录文章属于哪个群组
 * 将所属一个群组的文章ID记录到那个集合中
 * Redis不仅可以对多个集合执行操作，甚至在一些情况下，还可以在集合和有序集合之间执行操作
 */

 //1、构建存储文章信息的键名
String article = "article:" + articleId;
for (String group : toAdd) {
    //2、将文章添加到它所属的群组里面
    conn.sadd("group:" + group, article);
}
```

### 5. Sorted Set——有序集合（重点之重点）

Sorted Set的实现是hash table(element->score, 用于实现ZScore及判断element是否在集合内)，和skip list(score->element,按score排序)的混合体。 skip list有点像平衡二叉树那样，不同范围的score被分成一层一层，每层是一个按score排序的链表。 ZAdd/ZRem是O(log(N))，ZRangeByScore/ZRemRangeByScore是O(log(N)+M)，N是Set大小，M是结果/操作元素的个数。可见，原本可能很大的N被很关键的Log了一下，1000万大小的Set，复杂度也只是几十不到。当然，如果一次命中很多元素M很大那谁也没办法了。

常用命令 ：
```java
zadd key score member             //添加元素到集合，元素在集合中存在则更新对应的score
zrem key member                   //1表示成功，如果元素不存在则返回0

zremrangebyrank min max           //删除集合中排名在给定的区间

zincrvy key member                //增加对于member的score的值。

zcard key                         //返回集合中元素的个数

//获取排名
zrank key member                 //返回指定元素在集合中的排名，
zrebrank key member              //同时，但是集合中的元素是按score从大到小排序的

//获取排行榜
zrange key start end            //类似lrange操作从集合中去指定区间的元素，返回时有序的。

//给给定分数区间的元素
zrangebyscore key min max

//评分的聚合
ZUNIONSTORE destination numkeys key [key ...] [WEIGHTS weight] [AGGREGATE SUM|MIN|MAX]
```

代码实现：

```java
/**
 * 1、每次用户浏览页面的时候，程序需都会对用户存储在登录散列里面的信息进行更新，
 * 2、并将用户的令牌和当前时间戳添加到记录最近登录用户的集合里。
 * 3、如果用户正在浏览的是一个商品，程序还会将商品添加到记录这个用户最近浏览过的商品有序集合里面，
 * 4、如果记录商品的数量超过25个时，对这个有序集合进行修剪。
 */

 //1、获取当前时间戳
 long timestamp = System.currentTimeMillis() / 1000;
 //2、维持令牌与已登录用户之间的映射。
 conn.hset("login:", token, user);
 //3、记录令牌最后一次出现的时间
 conn.zadd("recent:", timestamp, token);
 if (item != null) {
     //4、记录用户浏览过的商品
     conn.zadd("viewed:" + token, timestamp, item);
     //5、移除旧记录，只保留用户最近浏览过的25个商品
     conn.zremrangeByRank("viewed:" + token, 0, -26);
     //6、为有序集key的成员member的score值加上增量increment。通过传递一个负数值increment 让 score 减去相应的值，
     conn.zincrby("viewed:", -1, item);
 }
---------------------------------------------------------------------------------
/**
 *存储会话数据所需的内存会随着时间的推移而不断增加，所有我们需要定期清理旧的会话数据。
 * 1、清理会话的程序由一个循环构成，这个循环每次执行的时候，都会检查存储在最近登录令牌的有序集合的大小。
 * 2、如果有序集合的大小超过了限制，那么程序会从有序集合中移除最多100个最旧的令牌，
 * 3、并从记录用户登录信息的散列里移除被删除令牌对应的用户信息，
 * 4、并对存储了这些用户最近浏览商品记录的有序集合中进行清理。
 * 5、于此相反，如果令牌的数量没有超过限制，那么程序会先休眠一秒，之后在重新进行检查。
 */

 public void run() {
         while (!quit) {
             //1、找出目前已有令牌的数量。
             long size = conn.zcard("recent:");
             //2、令牌数量未超过限制，休眠1秒，并在之后重新检查
             if (size <= limit) {
                 try {
                     sleep(1000);
                 } catch (InterruptedException ie) {
                     Thread.currentThread().interrupt();
                 }
                 continue;
             }

             long endIndex = Math.min(size - limit, 100);
             //3、获取需要移除的令牌ID
             Set<String> tokenSet = conn.zrange("recent:", 0, endIndex - 1);
             String[] tokens = tokenSet.toArray(new String[tokenSet.size()]);

             ArrayList<String> sessionKeys = new ArrayList<String>();
             for (String token : tokens) {
                 //4、为那些将要被删除的令牌构建键名
                 sessionKeys.add("viewed:" + token);
             }
             //5、移除最旧的令牌
             conn.del(sessionKeys.toArray(new String[sessionKeys.size()]));
             //6、移除被删除令牌对应的用户信息
             conn.hdel("login:", tokens);
             //7、移除用户最近浏览商品记录。
             conn.zrem("recent:", tokens);
         }
     }
}

//7、移除用户最近浏览商品记录。
conn.zrem("recent:", tokens);
```

另外一个案例：

```java
/**
 * 为了应对促销活动带来的大量负载，需要对数据行进行缓存，具体做法是：
 * 1、编写一个持续运行的守护进程，让这个函数指定的数据行缓存到redis里面，并不定期的更新。
 * 2、缓存函数会将数据行编码为JSON字典并存储在Redis字典里。其中数据列的名字会被映射为JSON的字典，
 * 而数据行的值则被映射为JSON字典的值。
 * -----------------------------------------------------------------------------------------
 * 程序使用两个有序集合来记录应该在何时对缓存进行更新：
 * 1、第一个为调用有序集合，他的成员为数据行的ID，而分支则是一个时间戳，
 * 这个时间戳记录了应该在何时将指定的数据行缓存到Redis里面
 * 2、第二个有序集合为延时有序集合，他的成员也是数据行的ID，
 * 而分值则记录了指定数据行的缓存需要每隔多少秒更新一次。
 * ----------------------------------------------------------------------------------------
 * 为了让缓存函数定期的缓存数据行，程序首先需要将hangID和给定的延迟值添加到延迟有序集合里面，
 * 然后再将行ID和当前指定的时间戳添加到调度有序集合里面。
 */
public void scheduleRowCache(Jedis conn, String rowId, int delay) {
    //1、先设置数据行的延迟值
    conn.zadd("delay:", delay, rowId);
    //2、立即对需要行村的数据进行调度
    conn.zadd("schedule:", System.currentTimeMillis() / 1000, rowId);
}

--------------------------------------------------------------------------------------

/**
 * 1、通过组合使用调度函数和持续运行缓存函数，实现类一种重读进行调度的自动缓存机制，
 * 并且可以随心所欲的控制数据行缓存的更新频率：
 * 2、如果数据行记录的是特价促销商品的剩余数量，并且参与促销活动的用户特别多的话，那么最好每隔几秒更新一次数据行缓存：
 * 另一方面，如果数据并不经常改变，或者商品缺货是可以接受的，那么可以每隔几分钟更新一次缓存。
 */
public class CacheRowsThread extends Thread {
    private Jedis conn;
    private boolean quit;

    public CacheRowsThread() {
        this.conn = new Jedis("localhost");
        this.conn.select(14);
    }

    public void quit() {
        quit = true;
    }

    public void run() {
        Gson gson = new Gson();
        while (!quit) {
            //1、尝试获取下一个需要被缓存的数据行以及该行的调度时间戳，返回一个包含0个或一个元组列表
            Set<Tuple> range = conn.zrangeWithScores("schedule:", 0, 0);
            Tuple next = range.size() > 0 ? range.iterator().next() : null;
            long now = System.currentTimeMillis() / 1000;
            //2、暂时没有行需要被缓存，休眠50毫秒。
            if (next == null || next.getScore() > now) {
                try {
                    sleep(50);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                continue;
            }
            //3、提前获取下一次调度的延迟时间，
            String rowId = next.getElement();
            double delay = conn.zscore("delay:", rowId);
            if (delay <= 0) {
                //4、不必在缓存这个行，将它从缓存中移除
                conn.zrem("delay:", rowId);
                conn.zrem("schedule:", rowId);
                conn.del("inv:" + rowId);
                continue;
            }
            //5、继续读取数据行
            Inventory row = Inventory.get(rowId);
            //6、更新调度时间，并设置缓存值。
            conn.zadd("schedule:", now + delay, rowId);
            conn.set("inv:" + rowId, gson.toJson(row));
        }
    }
}
```

具体的内容看我之前写的笔记[购物网站的redis相关实现(Java)](https://juejin.im/post/5a81b1806fb9a06340520f6e)和[文章投票网站的redis相关实现(Java)](https://juejin.im/post/5a8006bb6fb9a0633a70fcac).
这个主题就算完成了，
