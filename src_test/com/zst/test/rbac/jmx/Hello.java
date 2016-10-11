package com.zst.test.rbac.jmx;

public class Hello implements HelloMBean {
    private String name;
    private String sex;
    
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void printHello() {
        System.out.println("Hello World, " + name +" "+sex);
    }
    public void printHello(String whoName) {
        System.out.println("Hello , " + whoName);
    }
}