package com.zst.test.rbac.struts;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.springframework.transaction.jta.ManagedTransactionAdapter;

/**
 * 1 ̸̸struts2
 * 	��struts2��mvc��web����ģʽ�����Ĵ�������ActionServlet��������ͬ������ת�������õ���Ӧ��action��
 * 		ȱ�㣺չ�ֲ������鷳������forward ���ٸ�չ�ֲ��jsp�͵����ö��ٴ�strut
 * 			ÿ��������struts����Ҫ��������
 * 			���Բ����㣬ÿһ��action����web�������һ����������������web��������Ԫ����Ҳ��
 * 			��servlet�����Խ�ǿ��
 * 			��actionִ��ǰ��Ŀ��ƽ��ѡ�
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
