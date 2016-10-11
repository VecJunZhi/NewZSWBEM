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
 * 1 ��β�����redis����������
 * 	����һ��redis������Ŀ¼���޸�redis.windows.conf ��redis.windows-service.conf �ļ���cmd,������ӦĿ¼������  redis-server.exe redis.windows.conf��
 *  ���´�cmd ������redis-cli.exe -h 127.0.0.1 -p 6380��Ϊ�·������Ŀͻ��ˡ�
 * 2 ������ξ��������ݴ洢�ڷֲ�ʽredis�������е��Ǹ��ڵ㣿
 * �ֲ�ʽredis������һ����hash�㷨����������key�����Ǹ�redis��������
 * 3 ���Ӧ�÷ֲ�ʽredis����������
 * 
 *
 */
public class SharedJedis {
	
	
	
	public static void main(String[] args) {

        //sharedPool();
		master_slave_repul();
        
		
	}
	/**
	 * jedis���еķֲ�ʽ����
	 */
	public static void sharedPool(){
		//��������redis��������������Ϣ����
		String host="localhost";
		JedisShardInfo share1=new JedisShardInfo(host, 6379, 500);
		JedisShardInfo share2=new JedisShardInfo(host, 6380, 500);
		//������redis�������������ϵ�һ��list�����С�
		List<JedisShardInfo> list=Arrays.asList(share1,share2);
		//�������ӳ�������ԡ�
		JedisPoolConfig pool=new JedisPoolConfig();
		pool.setMaxIdle(1);
		pool.setMaxActive(10);
		pool.setMaxWait(1000);
		pool.setTestOnBorrow(false);
		//������redis�������ں�Ϊһ��͸�������ӳء�
		ShardedJedisPool jedispool=new ShardedJedisPool(pool, list);
		//�鿴
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
        //��ӡkey���ĸ�server��
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
		//�������ӳ�������ԡ�
		JedisPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxIdle(1);
		poolConfig.setMaxActive(10);
		poolConfig.setMaxWait(1000);
		poolConfig.setTestOnBorrow(false);
		//��������redis��������������Ϣ����
		String host="localhost";
		JedisPool pool1 = new JedisPool(poolConfig, host, 6379, 0);  
		JedisPool pool2 = new JedisPool(poolConfig, host, 6380, 0);  
		Jedis jedis1 = pool1.getResource();  
		Jedis jedis2 = pool2.getResource();  
		
		
		//����redis2Ϊredis1�Ĵӷ�����
		/*jedis1.slaveofNoOne();//redis1Ϊ��������
		jedis2.slaveof(host, 6379);
		//���Ӹ��ƣ�redis1�����ݣ�redis2�п��Զ�ȡ��
		jedis1.set("jedis1", "jedis1 master master");
		try {
			jedis2.set("jedis2", "jedis2 slave");
		} catch (Exception e) {
			System.out.println("jedis2Ϊ�ӷ�������ֻ�ܶ�ȡ������д��");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string=jedis2.get("jedis1");
		System.out.println("jedis2��ȡjedis1�����ݣ� "+string);*/
		
		
		
		//���Ӹ��ƣ�redis2�����ݣ�redis1�п��Զ�ȡ������redis2Ϊ����������redis1Ϊ�ӷ�����
		jedis2.slaveofNoOne();
		jedis1.slaveof(host, 6380);
		jedis2.set("mj", "mj2ddd");
		try {
			jedis1.set("mj1", "mj1");
		} catch (Exception e) {
			System.out.println("�˿� jedis1Ϊ�ӷ�����������д��");
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hk=jedis1.get("mj");
		System.out.println("jedis1 ��ȡjedis2�����ݣ�"+hk);
		jedis1.slaveofNoOne();  
		//jedis1.del("jedis1", "mykey2");  
        //jedis2.del("jedis1", "mykey2");  
	}
	
	

}
