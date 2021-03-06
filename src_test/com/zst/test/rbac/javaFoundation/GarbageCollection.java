package com.zst.test.rbac.javaFoundation;
/**
 * 1 垃圾回收机制的基本原理：
 * 	答垃圾回收时对堆内存中以及死亡或长时间不用的对象进行回收。
 * 	当对象创建的时候，垃圾回收器就监控对象的地址，大小，使用情况，当一些对象被标记为不可达时，GC就有开始回收这个对象。
 * 2 java中会存在内存泄露吗？
 * 	答：会，内存泄露 两点：无用，无法回收。长生命周期的对象持有短生命周期的对象的引用时，当短生命周期没用的时候，不能被回收，造成内存泄露。
 *     如HashSet中因hash值被修改，而检索不到的元素，造成内存泄漏。
 *
 */
public class GarbageCollection {

}
