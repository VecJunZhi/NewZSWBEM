package com.zst.test.rbac.struts;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.springframework.transaction.jta.ManagedTransactionAdapter;

/**
 * 1 谈谈struts2
 * 	答：struts2是mvc的web层框架模式，核心处理类是ActionServlet，它将不同的请求转发给配置的相应的action。
 * 		缺点：展现层配置麻烦：配置forward 多少个展现层的jsp就的配置多少次strut
 * 			每次配置完struts都需要重启服务。
 * 			测试不方便，每一个action都与web层耦合在一起，这样测试依赖与web容器，单元测试也是
 * 			对servlet依赖性较强。
 * 			对action执行前后的控制较难。
 *
 */
public class Struts {
	public static void main(String[] args) {
		TransactionManager trm = null;
		try {
			Transaction trs= new ManagedTransactionAdapter(trm);
			trs.commit();
		} catch (SystemException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
