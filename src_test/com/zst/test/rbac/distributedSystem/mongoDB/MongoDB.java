package com.zst.test.rbac.distributedSystem.mongoDB;

public class MongoDB {

}
/*mongoDB 是一种文档性的数据库。先解释一下文档的数据库，即可以存放xml、json、bson类型系那个的数据。
1.mongodb持久化原理

mongodb与mysql不同，mysql的每一次更新操作都会直接写入硬盘，但是mongo不会，做为内存型数据库，数据操作会先写入内存，然后再会持久化到硬盘中去，那么mongo是如何持久化的呢
mongodb在启动时，专门初始化一个线程不断循环（除非应用crash掉），用于在一定时间周期内来从defer队列中获取要持久化的数据并写入到磁盘的journal(日志)和mongofile(数据)处，当然因为它不是在用户添加记录时就写到磁盘上，所以按mongodb开发者说，它不会造成性能上的损耗，因为看过代码发现，当进行CUD操作时，记录(Record类型)都被放入到defer队列中以供延时批量（groupcommit）提交写入，但相信其中时间周期参数是个要认真考量的参数，系统为90毫秒，如果该值更低的话，可能会造成频繁磁盘操作，过高又会造成系统宕机时数据丢失过。

4.MongoDB的特点是什么？
（1）面向文档（2）高性能（3）高可用（4）易扩展（5）丰富的查询语言

6.如何理解MongoDB中的GridFS机制，MongoDB为何使用GridFS来存储文件？
GridFS是一种将大型文件存储在MongoDB中的文件规范。使用GridFS可以将大文件分隔成多个小文档存放，这样我们能够有效的保存大文档，而且解决了BSON对象有限制的问题。

3、内存空间的大小和数据量的大小

redis在2.0版本后增加了自己的VM特性，突破物理内存的限制；可以对key value设置过期时间（类似memcache）

memcache可以修改最大可用内存,采用LRU算法

mongoDB适合大数据量的存储，依赖操作系统VM做内存管理，吃内存也比较厉害，服务不要和别的服务在一起

6、数据一致性（事务支持）

Memcache 在并发场景下，用cas保证一致性

redis事务支持比较弱，只能保证事务中的每个操作连续执行

mongoDB不支持事务
5、可靠性（持久化）

对于数据持久化和数据恢复，

redis支持（快照、AOF）：依赖快照进行持久化，aof增强了可靠性的同时，对性能有所影响

memcache不支持，通常用在做缓存,提升性能；

MongoDB从1.8版本开始采用binlog方式支持持久化的可靠性

================================

内存管理机制
Redis数据全部存在内存，定期写入磁盘，当内存不够时，可以选择指定的LRU算法删除数据。

MongoDB数据存在内存，由Linux系统mmap实现，当内存不够时，只将热点数据放入内存，其他数据存在磁盘。
支持的数据结构
Redis支持的数据结构丰富，包括hash、set、list等。

MongoDB数据结构比较单一，但是支持丰富的数据表达，索引，最类似关系型数据库，支持的查询语言非常丰富。*/

