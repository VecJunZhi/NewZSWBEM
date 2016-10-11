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
			 // ���ӱ��ص� Memcached ����
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
	         // �ر�����
	         mcc.shutdown();
		} catch (Exception ex) {
			 log.info( ex.getMessage() );
		} 
	}
	
	public static void Set(MemcachedClient mcc){//���ھ͸��£������ھ�����
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
	public static void Add(MemcachedClient mcc){//���add��key�Ѿ����ڣ��򲻸������ݣ�֮ǰ��ֵ��Ȼ������ͬ�������ӦNOT_STORED
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
	public static void Replace(MemcachedClient mcc){//���ڵľ͸��£������ڵ����ʾ����ʧ��
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
	public static void Append(MemcachedClient mcc){//���Ѿ����ڵ�key��ֵ�ĺ����������
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
	public static void PreAppend(MemcachedClient mcc){//���Ѿ����ڵ�key��ֵ�ĺ����������
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
	public static void Cas(MemcachedClient mcc){//���Ѿ����ڵ�key��ֵ�ĺ����������
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
	public static void Delete(MemcachedClient mcc){//���Ѿ����ڵ�key��ֵ�ĺ����������
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
	public static void Incr_Decr(MemcachedClient mcc){//���Ѿ����ڵ�key��ֵ�ĺ����������
		Future fo = mcc.set("number", 900, "1000");
		log.info("value in cache after increment - " + mcc.incr("number", 111));
        // �Լ������
		log.info("value in cache after decrement - " + mcc.decr("number", 112));
	}
	
}
