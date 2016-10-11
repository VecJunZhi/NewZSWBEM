package com.zs.test.rbac.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * 1 什么是redis?优缺点是什么？
 * 	答：redis是一个内存型数据库，将数据保存在内存中，为了实现持久化，将定期把数据写入到磁盘中。
 * 		redis的存储数据类型有五种：string ,list ,set zset,hash.比较丰富。
 * 		redis存储方式 key-value方式。
 * 		redis实现了消息发布/订阅模式，可以实现主从服务器同步。
 * 		在分布式应用中，redis各个节点间相互通信，实现同步。
 * 2 redis的应用场景是什么？
 * 	答：redis可以用作前段的缓存数据库，也可以作为数据库使用。也可以用作消息队列。
 * 		redis应用于对读写要求都很高的场合。
 * 3 如何在应用程序中使用redis?
 * 
 */
public class RedisJava {
	
	public static void main(String[] args) {
		//连接服务
		 Jedis jedis = new Jedis("localhost");
		 //String 类型
		 jedis.set("string", "I am a string");
		 System.out.println(jedis.get("string"));
		 //list 类型
		 jedis.lpush("list", "a","b","c");
		 System.out.println(jedis.llen("list"));
		 //hash 类型
		 jedis.hset("hashSet", "hset", "hsetvalue");
		 System.out.println(jedis.hget("hashSet", "hset"));
		 //jedis.bgsave();
		 //set 类型
		 jedis.sadd("set", "a","b","c","b","g","e");
		 System.out.println(jedis.smembers("set"));
		 //sorder 类型
		 jedis.zadd("zset", 0, "a");
		 jedis.zadd("zset", 1, "c");
		 jedis.zadd("zset", 0, "b");
		 jedis.zadd("zset", 1, "b");
		 System.out.println(jedis.zcard("zset"));
		 System.out.println(jedis.zrangeByScore("zset", 0, 5));
		 System.out.println(jedis.zscore("zset", "b"));
		 //事务
		 Transaction trans=jedis.multi();
		 trans.set("mstring", "dfe");
		 Response<String>res=trans.get("mstring");
		 trans.sadd("tset", "0");
		 trans.sadd("tset", "1");
		 Response<Set<String>> tset=trans.smembers("tset");
		 
		 trans.exec();
		 System.out.println(res.get());
		 System.out.println(tset.get().size());
		 //管道及在管道中调用事务
		 Pipeline pipl=jedis.pipelined();
		 pipl.multi();
		 pipl.set("a", "aa");
		 pipl.set("b", "bb");
		 pipl.set("c", "cc");
		 Response<String> resp=pipl.get("c");
		 pipl.exec();
		 pipl.syncAndReturnAll();
		 System.out.println(resp.get());
		 //发布订阅
		 
		 //
	}

}
