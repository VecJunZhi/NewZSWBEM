package com.zst.test.rbac.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *  Ҫ���л�������� @XmlRootElementע�⣬����ᱨ��(������ʾ������������Ͳ���������)
	JAXB���л�XMLʱ  Ĭ�����л�getter��setter����getter��setter����ɶԳ��ֲŻᱻ���л�
	�������ƣ�Ĭ�����л������������������Ĭ��������ĸת��ΪСд������Ҫ��������������Ҫ��getter��setter��ʹ�� @XmlElement(name="ClassAId") ָ�����ƣ�����Ҫע�����@XmlElement������getter��setter�϶��У���ֻ�ܷ�һ����Ҳ����˵����ͬʱ��getter��setter��ʹ��@XmlElementע��
	��ο��Ƹ��ڵ����ƣ�
		ʹ��@XmlRootElementָ��name���Լ��ɣ���@XmlRootElement(name="ClassA")
	��ô��������ռ�
		ʹ��@XmlRootElement(namespace="cn.lzrabbit") ָ��namespace����
	��ô��ȷ����ÿ����������
		JAXB�Զ�ת��Ϊ����ĸСд�ᵼ�²���Ԥ�ϵ��������Ƴ��֣� �����鷳�Ļ�Ϊÿ����������@XmlElement(name="")����ʡ�µĻ�ʹ��Field
	��ô��ʵ�����л�ʱʹ��Field�ֶζ�����ʹ��setter��getter
		��Ҫʹ�õ����������@XmlAccessorType(XmlAccessType.FIELD)ע�⣬��ָ��ΪXmlAccessType.FIELD������ǿ���Ƽ�ʹ��@XmlAccessorType(XmlAccessType.FIELD)ע�⣬��Ϊ��������Ծ�ȷ�Ŀ���ÿ��Ԫ�ص����ƣ�������ҪΪÿ������ȥ����@XmlElement(name="")ע�⣬��ȻҲ������Field��ʹ��@XmlElementע��
 * @author zsjr
 *
 */
@XmlRootElement(namespace="cn.jaxb")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassA {

    private int classAId;
    private String classAName;
    
    @XmlElement(name="ClassBS")
    private ClassB classB;

    public int getClassAId() {
        return classAId;
    }

    public void setClassAId(int classAId) {
        this.classAId = classAId;
    }

    public String getClassAName() {
        return classAName;
    }

    public void setClassAName(String classAName) {
        this.classAName = classAName;
    }

    public ClassB getClassB() {
        return classB;
    }

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }
}

