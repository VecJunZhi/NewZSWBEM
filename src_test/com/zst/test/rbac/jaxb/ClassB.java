package com.zst.test.rbac.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClassB {
	
	private int classBId;
    private String classBName;

    public int getClassBId() {
        return classBId;
    }

    public void setClassBId(int classBId) {
        this.classBId = classBId;
    }

    public String getClassBName() {
        return classBName;
    }

    public void setClassBName(String classBName) {
        this.classBName = classBName;
    }
}
