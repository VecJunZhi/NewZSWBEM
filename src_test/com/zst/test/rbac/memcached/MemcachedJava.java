package com.zst.test.rbac.memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;

import com.zs.busi.utils.LogUtil;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
public class MemcachedJava {
	
	static Log log =LogUtil.getLog();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 try {
			 // 连接本地的 Memcached 服务
			 MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			 System.out.println("Connection to server sucessful.");
			 //Set(mcc);
	         //Add(mcc);
	         //Replace(mcc);
			 //Append(mcc);
			 //PreAppend(mcc);
			 //Cas(mcc);
			 //Delete(mcc);
			 Incr_Decr(mcc);
	         // 关闭连接
	         mcc.shutdown();
		} catch (Exception ex) {
			 log.info( ex.getMessage() );
		} 
	}
	
	public static void Set(MemcachedClient mcc){//存在就更新，不存在就新增
		OperationFuture op=mcc.set("runoob", 900, "Free education");
		try {
			log.info(op.get());
			log.info(mcc.get("runoob"));
			log.info(op.getKey());
			log.info(op.getStatus());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	public static void Add(MemcachedClient mcc){//如果add的key已经存在，则不更新数据，之前的值仍然保持相同，获得相应NOT_STORED
		OperationFuture op=mcc.add("runoob", 60, "addOperation");
		OperationFuture op2=mcc.add("add", 60, "add_Operation");
		try {
			log.info(op.get()+" "+op.getStatus());
			log.info(mcc.get("runoob"));
			log.info(mcc.get("add"));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	public static void Replace(MemcachedClient mcc){//存在的就更新，不存在的则表示更新失败
		OperationFuture op=mcc.replace("runoob", 20, "replaceEducation");
		OperationFuture op2=mcc.replace("my_runoob", 20, "replaceEducation");
		try {
			log.info(op.get()+" "+op.getStatus());
			log.info(mcc.get("runoob"));
			log.info(op2.get()+" "+op2.getStatus());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	public static void Append(MemcachedClient mcc){//在已经存在的key的值的后面添加内容
		OperationFuture op=mcc.append("runoob", "replaceEducation");
		OperationFuture op2=mcc.append("my_runoob", "replaceEducation");
		try {
			log.info(op.get()+" "+op.getStatus());
			log.info(mcc.get("runoob"));
			log.info(op2.get()+" "+op2.getStatus());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	public static void PreAppend(MemcachedClient mcc){//在已经存在的key的值的后面添加内容
		OperationFuture op=mcc.prepend("runoob", "prepend");
		OperationFuture op2=mcc.prepend("my_runoob", "prepend");
		try {
			log.info(op.get()+" "+op.getStatus());
			log.info(mcc.get("runoob"));
			log.info(op2.get()+" "+op2.getStatus());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	public static void Cas(MemcachedClient mcc){//在已经存在的key的值的后面添加内容
		CASValue<Object> op=mcc.gets("runoob");
		CASResponse response=mcc.cas("runoob", op.getCas(), 90, "casvalue");
		try {
			log.info(op+" "+op.getCas()+" "+op.getValue());
			log.info(mcc.get("runoob"));
			log.info(response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void Delete(MemcachedClient mcc){//在已经存在的key的值的后面添加内容
		OperationFuture op=mcc.delete("runoob");
		OperationFuture op2=mcc.delete("my_runoob");
		try {
			log.info(op.get()+" "+op.getStatus());
			log.info(mcc.get("runoob"));
			log.info(op2.get()+" "+op2.getStatus());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void Incr_Decr(MemcachedClient mcc){//在已经存在的key的值的后面添加内容
		Future fo = mcc.set("number", 900, "1000");
		log.info("value in cache after increment - " + mcc.incr("number", 111));
        // 自减并输出
		log.info("value in cache after decrement - " + mcc.decr("number", 112));
	}
	
}
