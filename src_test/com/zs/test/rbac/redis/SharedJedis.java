package com.zs.test.rbac.redis;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * 1 如何部署多个redis服务器。？
 * 	复制一份redis服务器目录，修改redis.windows.conf 和redis.windows-service.conf 文件，cmd,进入相应目录，运行  redis-server.exe redis.windows.conf。
 *  重新打开cmd ，运行redis-cli.exe -h 127.0.0.1 -p 6380称为新服务器的客户端。
 * 2 数据如何决定将数据存储在分布式redis服务器中的那个节点？
 * 分布式redis，采用一致性hash算法来决定数据key存在那个redis服务器。
 * 3 如何应用分布式redis服务器。？
 * 
 *
 */
public class SharedJedis {
	
	
	
	public static void main(String[] args) {

        //sharedPool();
		master_slave_repul();
        
		
	}
	/**
	 * jedis独有的分布式连接
	 */
	public static void sharedPool(){
		//创建各个redis服务器的连接信息对象。
		String host="localhost";
		JedisShardInfo share1=new JedisShardInfo(host, 6379, 500);
		JedisShardInfo share2=new JedisShardInfo(host, 6380, 500);
		//将各个redis服务器对象整合到一个list集合中。
		List<JedisShardInfo> list=Arrays.asList(share1,share2);
		//配置连接池相关属性。
		JedisPoolConfig pool=new JedisPoolConfig();
		pool.setMaxIdle(1);
		pool.setMaxActive(10);
		pool.setMaxWait(1000);
		pool.setTestOnBorrow(false);
		//将各个redis服务器融合为一个透明的连接池。
		ShardedJedisPool jedispool=new ShardedJedisPool(pool, list);
		//查看
        ShardedJedis jedis = jedispool.getResource();
        jedis.set("1111", "1111");
        jedis.set("2222", "2222");
        jedis.set("3333", "3333");
        jedis.set("4444", "4444");
        jedis.set("a1111", "1111");
        jedis.set("a2222", "2222");
        jedis.set("a3333", "3333");
        jedis.set("a4444", "4444");
        Client client1 = jedis.getShard("1111").getClient();
        Client client2 = jedis.getShard("2222").getClient();
        Client client3 = jedis.getShard("3333").getClient();
        Client client4 = jedis.getShard("4444").getClient();
        Client client11 = jedis.getShard("a1111").getClient();
        Client client12 = jedis.getShard("a2222").getClient();
        Client client13 = jedis.getShard("a3333").getClient();
        Client client14 = jedis.getShard("a4444").getClient();
        //打印key在哪个server中
        System.out.println("1111 in server:" + client1.getHost() + " and port is:" + client1.getPort());
        System.out.println("2222 in server:" + client2.getHost() + " and port is:" + client2.getPort());
        System.out.println("3333 in server:" + client3.getHost() + " and port is:" + client3.getPort());
        System.out.println("4444 in server:" + client4.getHost() + " and port is:" + client4.getPort());
        System.out.println("a1111 in server:" + client11.getHost() + " and port is:" + client11.getPort());
        System.out.println("a2222 in server:" + client12.getHost() + " and port is:" + client12.getPort());
        System.out.println("a3333 in server:" + client13.getHost() + " and port is:" + client13.getPort());
        System.out.println("a4444 in server:" + client14.getHost() + " and port is:" + client14.getPort());
	}
	public static void master_slave_repul(){
		//配置连接池相关属性。
		JedisPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxIdle(1);
		poolConfig.setMaxActive(10);
		poolConfig.setMaxWait(1000);
		poolConfig.setTestOnBorrow(false);
		//创建各个redis服务器的连接信息对象。
		String host="localhost";
		JedisPool pool1 = new JedisPool(poolConfig, host, 6379, 0);  
		JedisPool pool2 = new JedisPool(poolConfig, host, 6380, 0);  
		Jedis jedis1 = pool1.getResource();  
		Jedis jedis2 = pool2.getResource();  
		
		
		//设置redis2为redis1的从服务器
		/*jedis1.slaveofNoOne();//redis1为主服务器
		jedis2.slaveof(host, 6379);
		//主从复制，redis1的数据，redis2中可以读取。
		jedis1.set("jedis1", "jedis1 master master");
		try {
			jedis2.set("jedis2", "jedis2 slave");
		} catch (Exception e) {
			System.out.println("jedis2为从服务器，只能读取，不能写入");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string=jedis2.get("jedis1");
		System.out.println("jedis2读取jedis1的数据： "+string);*/
		
		
		
		//主从复制，redis2的数据，redis1中可以读取。提升redis2为主服务器，redis1为从服务器
		jedis2.slaveofNoOne();
		jedis1.slaveof(host, 6380);
		jedis2.set("mj", "mj2ddd");
		try {
			jedis1.set("mj1", "mj1");
		} catch (Exception e) {
			System.out.println("此刻 jedis1为从服务器，不能写入");
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hk=jedis1.get("mj");
		System.out.println("jedis1 读取jedis2的数据："+hk);
		jedis1.slaveofNoOne();  
		//jedis1.del("jedis1", "mykey2");  
        //jedis2.del("jedis1", "mykey2");  
	}
	
	

}
