package com.zs.test.rbac.redis;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class RedisSubPub {
	
	class MyListener extends JedisPubSub{

		public void onMessage(String channel, String message) {
	        System.out.println("get a msg: " + "channel=" + channel + ", message=" + message);
	    }

	    public void onSubscribe(String channel, int subscribedChannels) {
	        System.out.println("channel:" + channel + ", subscribedChannels:" + subscribedChannels);
	    }

	    public void onUnsubscribe(String channel, int subscribedChannels) {
	        System.out.println("channel:" + channel + ", subscribedChannels:" + subscribedChannels);
	    }

	    public void onPSubscribe(String pattern, int subscribedChannels) {
	        System.out.println("pattern:" + pattern + ", subscribedChannels:" + subscribedChannels);
	    }

	    public void onPUnsubscribe(String pattern, int subscribedChannels) {
	        System.out.println("pattern:" + pattern + ", subscribedChannels:" + subscribedChannels);
	    }

	    public void onPMessage(String pattern, String channel, String message) {
	        System.out.println("pattern:" + pattern + ", channel:" + channel + ", message:" + message);
	    }
		
	}
	
	
	 
	 public static void main(String[] args) {
        final JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");//redis java�ͻ��˲��ܴ���JedisPool ȱ�� common-pool jar��
        final RedisSubPub subpub=new RedisSubPub();
            //����������
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = pool.getResource();
                    MyListener listener = subpub.new MyListener();
                    jedis.subscribe(listener, "channel01");//�߳�����

                }
            }).start();
            
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = pool.getResource();
                    MyListener listener =subpub.new MyListener(); //listenerΪJedisPubSub���󣬼�����
                    jedis.subscribe(listener, "channel01");//�߳�������

                }
            }).start();

            //һ��������
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //ע�⣬����ʹ�õ�jedis�����뷢��ʹ�õ�jedis��������ͬ����������ʱ�ᱨ��
                    Jedis jedis = pool.getResource();
                    while(true){
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String msg = new Date().toLocaleString();
                        System.out.println("publish a msg:" + msg);
                        jedis.publish("channel01", msg);
                    }

                }
            }).start();
            
        //pool.close();
    }
}


