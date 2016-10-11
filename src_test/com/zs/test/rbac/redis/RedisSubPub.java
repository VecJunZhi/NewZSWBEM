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
        final JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");//redis java客户端不能创建JedisPool 缺少 common-pool jar包
        final RedisSubPub subpub=new RedisSubPub();
            //两个订阅者
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = pool.getResource();
                    MyListener listener = subpub.new MyListener();
                    jedis.subscribe(listener, "channel01");//线程阻塞

                }
            }).start();
            
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = pool.getResource();
                    MyListener listener =subpub.new MyListener(); //listener为JedisPubSub对象，监听类
                    jedis.subscribe(listener, "channel01");//线程阻塞。

                }
            }).start();

            //一个发布者
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //注意，订阅使用的jedis对象与发布使用的jedis对象不能相同，否者运行时会报错
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


