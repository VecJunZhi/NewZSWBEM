package com.zs.test.rbac.springAOPdeveloped;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/aopDevelop/")
public class AimClass {
	@RequestMapping("/memcachedTest")
	public void memcachedTest(){
		System.out.println("����ִ��memcachedTest����");
		/*if(memcachedClient.get("user")==null){
			memcachedClient.add("users",0, 25);
		}if(memcachedClient.get("users")!=null){
			log.info(memcachedClient.get("users"));
		}*/
	}
}
