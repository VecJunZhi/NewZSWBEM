package com.zst.test.rbac.distributedSystem.memcache;

/**
 * 1 ʲô��memcached��
 * 		��memcached��һ�������ܵķֲ�ʽ�ڴ���󻺴�ϵͳ����Ҫ���������������ݣ��Ӷ��������ݿ��ѹ����
 * 			memcached�ķֲ�ʽ���ڿͻ���ʵ�ֵġ�
 * 			memcached�ķֲ�ʽ�ǲ�����ͨ�ŵġ�
 * 2 ����ڰ�װ����ʹ��memcached?
 * 		����window��ʹ��memcached��
 * 			1 ����memcached�����ִ�г��򡣲����С�
 * 			2 cmd --telnet 127.0.0.1 11211 ���롣
 * 			3 ��ctrl+]���������״̬����memcached�ṩ������������ݲ�����
 * 3 memcached���õ���������Щ��
 * 		������add(keyû�о������о�ά��ԭ״)��set��keyû�о������о͸ģ�,append,preappend,
 * 			ɾ��delete
 * 			�ģ�set
 * 			�飺get gets
 * 			ͳ��stats
 * 4 javaʵ��memcached�����ӷ�ʽ�������֣��ֱ������ȱ�㣿
 * 		��1 memcached client for java  
 * 			2 spymemcached ��ʵ���˻���java��NIO
 * 			3 xmlMemcached:ʵ���˻���java��NIO
 * 5 memcached��slab allocator������ơ�
 * 		���ڴ涯̬���䣬����ǰ���ڴ�ֳɿռ䲻һ�Ŀ飬������С��ͬ�Ŀ��װ��һ���γ�slab classes.
 * 6 memcached���ڴ�ռ����ԭ��
 * 		�������ش���memcached�����ݵ���Ч�ڲ���ʵ��ʵʱ��أ�������get��ʱ���ȥЧ��ʱ������ʧЧ����ռ䡣
 * 			LUB:�����������ʹ���㷨�����洢�ռ䲻���ǣ�����LUB�㷨��������ռ䡣
 * 7 memcached�ڷֲ�ʽ�㷨�еĴ洢��ʽ��
 * 		�𣺸���һ����hash�㷨��ѡ��洢��memcached���������������Ϊ����memcached�ķֲ��ڵ����hash�㷨�ֲ���0--2^32��Բ�ϡ�
 * 			�����ݵ�keyֵ����hash�㷨�ֲ���Բ�ϣ�˳ʱ��Ѱ��memcached����������һ���ҵ����ͽ����ݴ洢�ڸķ������С�
 * 8 һ����hash�㷨��ԭ��
 * 		
 *
 */
public class Memcache {

}

