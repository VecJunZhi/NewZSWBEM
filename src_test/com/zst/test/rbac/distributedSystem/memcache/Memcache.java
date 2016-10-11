package com.zst.test.rbac.distributedSystem.memcache;

/**
 * 1 什么是memcached？
 * 		答：memcached是一个高性能的分布式内存对象缓存系统。主要用来缓存请求数据，从而缓解数据库端压力。
 * 			memcached的分布式是在客户端实现的。
 * 			memcached的分布式是不互相通信的。
 * 2 如何在安装配置使用memcached?
 * 		答：在window中使用memcached：
 * 			1 下载memcached服务端执行程序。并运行。
 * 			2 cmd --telnet 127.0.0.1 11211 进入。
 * 			3 按ctrl+]，进入回显状态，用memcached提供的命令进行数据操作。
 * 3 memcached常用的命令有哪些？
 * 		答：增：add(key没有就增，有就维持原状)，set（key没有就增，有就改）,append,preappend,
 * 			删：delete
 * 			改：set
 * 			查：get gets
 * 			统：stats
 * 4 java实现memcached的连接方式有那三种？分别阐述优缺点？
 * 		答：1 memcached client for java  
 * 			2 spymemcached ：实现了基于java的NIO
 * 			3 xmlMemcached:实现了基于java的NIO
 * 5 memcached的slab allocator分配机制。
 * 		答：内存动态分配，将提前将内存分成空间不一的块，并将大小相同的块封装到一起，形成slab classes.
 * 6 memcached的内存空间清除原则：
 * 		答：懒加载处理：memcached对数据的有效期并不实行实时监控，而是在get的时候才去效验时间错，如果失效清除空间。
 * 			LUB:根据最近最少使用算法，当存储空间不足是，根据LUB算法进行清除空间。
 * 7 memcached在分布式算法中的存储方式。
 * 		答：根据一致性hash算法来选择存储的memcached服务器。具体过程为：将memcached的分布节点根据hash算法分布在0--2^32的圆上。
 * 			将数据的key值根据hash算法分布在圆上，顺时针寻找memcached服务器。第一次找到，就将数据存储在改服务器中。
 * 8 一致性hash算法的原理。
 * 		
 *
 */
public class Memcache {

}

