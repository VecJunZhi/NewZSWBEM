package com.zs.test.rbac.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * 1 ʲô��redis?��ȱ����ʲô��
 * 	��redis��һ���ڴ������ݿ⣬�����ݱ������ڴ��У�Ϊ��ʵ�ֳ־û��������ڰ�����д�뵽�����С�
 * 		redis�Ĵ洢�������������֣�string ,list ,set zset,hash.�ȽϷḻ��
 * 		redis�洢��ʽ key-value��ʽ��
 * 		redisʵ������Ϣ����/����ģʽ������ʵ�����ӷ�����ͬ����
 * 		�ڷֲ�ʽӦ���У�redis�����ڵ���໥ͨ�ţ�ʵ��ͬ����
 * 2 redis��Ӧ�ó�����ʲô��
 * 	��redis��������ǰ�εĻ������ݿ⣬Ҳ������Ϊ���ݿ�ʹ�á�Ҳ����������Ϣ���С�
 * 		redisӦ���ڶԶ�дҪ�󶼺ܸߵĳ��ϡ�
 * 3 �����Ӧ�ó�����ʹ��redis?
 * 
 */
public class RedisJava {
	
	public static void main(String[] args) {
		//���ӷ���
		 Jedis jedis = new Jedis("localhost");
		 //String ����
		 jedis.set("string", "I am a string");
		 System.out.println(jedis.get("string"));
		 //list ����
		 jedis.lpush("list", "a","b","c");
		 System.out.println(jedis.llen("list"));
		 //hash ����
		 jedis.hset("hashSet", "hset", "hsetvalue");
		 System.out.println(jedis.hget("hashSet", "hset"));
		 //jedis.bgsave();
		 //set ����
		 jedis.sadd("set", "a","b","c","b","g","e");
		 System.out.println(jedis.smembers("set"));
		 //sorder ����
		 jedis.zadd("zset", 0, "a");
		 jedis.zadd("zset", 1, "c");
		 jedis.zadd("zset", 0, "b");
		 jedis.zadd("zset", 1, "b");
		 System.out.println(jedis.zcard("zset"));
		 System.out.println(jedis.zrangeByScore("zset", 0, 5));
		 System.out.println(jedis.zscore("zset", "b"));
		 //����
		 Transaction trans=jedis.multi();
		 trans.set("mstring", "dfe");
		 Response<String>res=trans.get("mstring");
		 trans.sadd("tset", "0");
		 trans.sadd("tset", "1");
		 Response<Set<String>> tset=trans.smembers("tset");
		 
		 trans.exec();
		 System.out.println(res.get());
		 System.out.println(tset.get().size());
		 //�ܵ����ڹܵ��е�������
		 Pipeline pipl=jedis.pipelined();
		 pipl.multi();
		 pipl.set("a", "aa");
		 pipl.set("b", "bb");
		 pipl.set("c", "cc");
		 Response<String> resp=pipl.get("c");
		 pipl.exec();
		 pipl.syncAndReturnAll();
		 System.out.println(resp.get());
		 //��������
		 
		 //
	}

}
