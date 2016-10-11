package com.zst.test.rbac.DataStructure;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * ���ݽṹ��
 * List: --ArrayList:�ɱ����飬��ͬ���ģ�Ĭ������16Ԫ�أ��Զ�����Ϊ��ǰ������2��������ǰԪ�ظ��Ƶ��µ������У�������ԭ�ȵ����飩�����ݽṹ�е�˳��洢������Ԫ�ؿ죬��ӣ�ɾ��Ԫ�ؽ�����
 * 		---LinkedList�����ݽṹΪ˫������ṹ����ͬ���ģ���ӣ�ɾ��Ԫ�ؿ졣����Ԫ�������ʺϹ���ջ�Ͷ��С�
 * 		---Vector���ɱ����飬ͬ���ģ��õ����߳̽��ʺ���Vector.
 * Set: ---HashSet:����ģ�Ԫ�ز����ظ��ģ���HashCode��equal�������жϷ����Ƿ���ͬ��
 * 		---LinkedHashSet��Ԫ�ز����ظ��ġ�equal�������жϷ����Ƿ���ͬ��������ķ�ʽ���Ԫ�أ�������ķ�ʽά��Ԫ�ش���
 * 		---TreeSet:����ģ�Ԫ�ز����ظ��ġ�equal�������жϷ����Ƿ���ͬ������������Ȼ����Ͷ������������Ǹ���compareTo,compare������ʵ�ֵģ�����TreeSet��������ӵ�Ԫ�ر���ʵ�ֽӿ�compareable,��compartor��
 * Map: ---TreeMap:��ֵ�ԣ�key�������ظ���value�����ظ���
 * 		---HashMap:��ֵ�ԣ�key�������ظ���value�����ظ�������hashֵ���洢���ݣ���֧���̵߳�ͬ������Collections.Synchronized��hashMap��ʹ��HashMap�߳�ͬ��������һ����Ϊnull,���valueΪnull
 * 		---LinkedHashMap:��ֵ�ԣ�key�������ظ���value�����ظ���ͨ��������ά��˳�򣬱����˼�¼�Ĳ���˳��֧���̵߳�ͬ�������������ֵΪnull,
 * 		---HashTable:��ֵ�ԣ�key�������ظ���value�����ظ�������hashֵ���洢���ݣ�֧���̵߳�ͬ�������������ֵΪnull,
 * 1 ArrayList��Vector������
 *   �̣߳�
 *   �ռ� ����ʼ�ռ�������ռ�
 * 2 HashTable��HashMap���������ϵ
 * 	���̰߳�ȫ ��HashMap ���Խ� nullֵ��Ϊkey
 * 3 ����֪���ļ����඼����Щ����Ҫ������
 * 4 ʲô��java���л������ʵ��java���л������������Serializable�ӿڵ����á�
 * 	��java�����״̬��IO�д��� ��һ��java�����״̬������Ӳ���У�java���л��Ǵ洢�Ͷ�ȡ��ǰ�����״̬��Ϊ��ȷ����ȡ���ǵ�ǰ��״̬�������˰汾�ţ�
 * 5 jvm��μ������ļ�
 * 	�� ͨ��classLoad������������������ļ���
 * 
 * 6 ���������µ��ı��ļ��ж�ȡ�����е�����������ӡ���ظ����������ظ��Ĵ����������ظ���������
		1,����,28
		2,����,35
		3,����,28
		4,����,35
		5,����,28
		6,����,35
		7,����,28
		8,����,35
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
