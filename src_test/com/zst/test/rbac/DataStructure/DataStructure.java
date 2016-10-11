package com.zst.test.rbac.DataStructure;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * 数据结构：
 * List: --ArrayList:可变数组，非同步的，默认容量16元素，自动扩容为当前容量的2倍（将当前元素复制到新的数组中，并丢弃原先的数组）。数据结构中的顺序存储，查找元素快，添加，删除元素较慢。
 * 		---LinkedList：数据结构为双向链表结构，非同步的，添加，删除元素快。查找元素漫。适合构造栈和队列。
 * 		---Vector：可变数组，同步的，用到多线程较适合用Vector.
 * Set: ---HashSet:无序的，元素不可重复的，用HashCode和equal方法来判断方法是否相同。
 * 		---LinkedHashSet：元素不可重复的。equal方法来判断方法是否相同。已链表的方式添加元素，用链表的方式维护元素次序。
 * 		---TreeSet:有序的，元素不可重复的。equal方法来判断方法是否相同。（排序有自然排序和定制排序。排序是根据compareTo,compare方法来实现的，所以TreeSet集合中添加的元素必须实现接口compareable,或compartor）
 * Map: ---TreeMap:键值对，key不可以重复，value可以重复，
 * 		---HashMap:键值对，key不可以重复，value可以重复，根据hash值来存储数据，不支持线程的同步，用Collections.Synchronized（hashMap）使得HashMap线程同步，允许一个键为null,多个value为null
 * 		---LinkedHashMap:键值对，key不可以重复，value可以重复，通过链表来维护顺序，保存了记录的插入顺序。支持线程的同步，不允许键和值为null,
 * 		---HashTable:键值对，key不可以重复，value可以重复，根据hash值来存储数据，支持线程的同步，不允许键和值为null,
 * 1 ArrayList和Vector的区别？
 *   线程：
 *   空间 ：初始空间和增长空间
 * 2 HashTable和HashMap的区别和联系
 * 	答：线程安全 ，HashMap 可以将 null值作为key
 * 3 你所知道的集合类都有哪些？主要方法？
 * 4 什么是java序列化，如何实现java序列化？或者请解释Serializable接口的作用。
 * 	答：java对象的状态在IO中传递 或将一个java对象的状态保存在硬盘中，java序列化是存储和读取当前对象的状态。为了确保读取的是当前的状态，加入了版本号，
 * 5 jvm如何加载类文件
 * 	答 通过classLoad组件来搜索并加载类文件。
 * 
 * 6 从类似如下的文本文件中读取出所有的姓名，并打印出重复的姓名和重复的次数，并按重复次数排序：
		1,张三,28
		2,李四,35
		3,张三,28
		4,王五,35
		5,张三,28
		6,李四,35
		7,赵六,28
		8,田七,35
 * 
 */
public class DataStructure {
	
	public static void main(String[] args) {
		hashMap();
	}
	
	public static void hashMap(){
		Map map = new HashMap();
		map.put(null, null);
		Hashtable map2 = new Hashtable();
		map2.put(null, "sdf");
		Iterator it=map2.entrySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
