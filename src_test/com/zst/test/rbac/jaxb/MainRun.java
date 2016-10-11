package com.zst.test.rbac.jaxb;

public class MainRun {
	
	 public static void main(String[] args) {

	        ClassB classB = new ClassB();
	        classB.setClassBId(22);
	        classB.setClassBName("B2");

	        ClassA classA = new ClassA();
	        classA.setClassAId(11);
	        classA.setClassAName("A1");
	        classA.setClassB(classB);
	        String xml=XmlUtil.toXML(classA);
	        System.out.println(xml);
	        ClassA re_classA=XmlUtil.fromXML(xml, ClassA.class);
	        System.out.println(re_classA.getClassAName());
	    }

}
