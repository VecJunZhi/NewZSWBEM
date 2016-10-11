package com.zst.test.rbac.netWorkCommuication;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author yzj
 *
 */
public class InetAddres {
	
	public static void main(String[] args) {
		
		try {
			InetAddress address=InetAddress.getByName("www.baidu.com");
			System.out.println(address);
			InetAddress localaddress=InetAddress.getLocalHost();
			System.out.println(localaddress);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
