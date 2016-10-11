package com.zst.test.rbac.jaxb;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtil {
	
	public static String toXML(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            
            File file=new File("E:\\file.xml");
            marshaller.marshal(obj, file);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	 @SuppressWarnings("unchecked")
	public static <T> T fromXML(String xml, Class<T> valueType) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(valueType);
	            Unmarshaller unmarshaller = context.createUnmarshaller();
	            return (T) unmarshaller.unmarshal(new StringReader(xml));
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }
}
