package com.zst.test.rbac.webService;
/**
 * 1 什么是webService
 * 		答：webservice是一个可以将自己开发的程序发布为web应用程序，可以被其他应用程序使用。
 * 			通过web发布 应用  查找。
 * 			使用开放的协议通信，http
 * 			使用UDDI来发现webservice。
 * 2 为什么使用webService
 * 		答：将程序发布为web服务，提高代码复用性和代码调用的安全性。
 * 			可以连接现有的web服务，减少代码开发，可以用来协同开发。
 * 3 webService的实现原理。
 * 		答：webService平台主要包含的元素：SOAP  UUDI  WSDL.
 * 			SOAP:simple object access protocol 是一种简单的轻量的基于xml的交换数据的一种协议。可以简单认为：SOAP=XML+HTTP,设计用来在web层上交换结构化(xml)固化(xml数据)的数据。
 * 				 SOAP用来描述传递信息的格式：一个soap消息就是一个普通的xml文件。
 * 				 SOAP消息的基本结构：
						<?xml version="1.0"?>
						<soap:Envelope//必须的yu元素，可以吧此xml文件按表示为一条soap消息。
						xmlns:soap="http://www.w3.org/2001/12/soap-envelope"//必须使用
						soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">//必须使用
						<soap:Header>//可选元素，若提供必须是envelope的第一个元素。
						...
						</soap:Header>
						<soap:Body>//必选元素
						...
						  <soap:Fault>
						  ...
						  </soap:Fault>
						</soap:Body>
						</soap:Envelope>	
				SOAP HTTP Binding：
					SOAP方法是指遵守soap编码规则的http请求/响应。
						实例：POST /item HTTP/1.1
							Content-Type: application/soap+xml; charset=utf-8
			WSDL:描述如何使用发布的web服务，接口是什么，有哪些方法，需要什么参数，及参数类型是什么。
					wsdl元素：protType, message   types  binding 
					protType:描述被执行的操作。
					bingding:描述使用的通信协议。
					message:每个消息均由一个或多个部件组成。可以把这些部件比作传统编程语言中一个函数调用的参数。
					types 对参数个数，参数类型，参数名称的描述。
				实例：
			<wsdl:types>
				<xs:schema xmlns:ax22="http://entity.oa.zs.com/xsd" attributeFormDefault="qualified" elementFormDefault="qualified" targetNamespace="http://action.web.oa.zs.com">
					<xs:import namespace="http://entity.oa.zs.com/xsd"/>
					<xs:element name="smpTongXin">
						<xs:complexType>
							<xs:sequence>
								<xs:element minOccurs="0" name="json" nillable="true" type="xs:string"/>
								<xs:element minOccurs="0" name="flg" nillable="true" type="xs:string"/>
								<xs:element minOccurs="0" name="machineCode" nillable="true" type="xs:string"/>
							</xs:sequence>
						</xs:complexType>
					</xs:element>
					<xs:element name="smpTongXinResponse">
						<xs:complexType>
							<xs:sequence>
								<xs:element minOccurs="0" name="return" nillable="true" type="xs:string"/>
							</xs:sequence>
						</xs:complexType>
					</xs:element>
				</xs:schema>
				<xs:schema attributeFormDefault="qualified" elementFormDefault="qualified" targetNamespace="http://entity.oa.zs.com/xsd">
					<xs:complexType name="OtherResponseDto">
						<xs:sequence>
							<xs:element minOccurs="0" name="attendIp" nillable="true" type="xs:string"/>
							<xs:element minOccurs="0" name="name" nillable="true" type="xs:string"/>
							<xs:element minOccurs="0" name="time" nillable="true" type="xs:string"/>
							<xs:element minOccurs="0" name="user_id" nillable="true" type="xs:string"/>
							<xs:element minOccurs="0" name="work_no" nillable="true" type="xs:string"/>
						</xs:sequence>
					</xs:complexType>
				</xs:schema>
			</wsdl:types>
				<wsdl:message name="smpTongXinRequest">
					<wsdl:part name="parameters" element="ns:smpTongXin"/>
				</wsdl:message>
				<wsdl:message name="smpTongXinResponse">
					<wsdl:part name="parameters" element="ns:smpTongXinResponse"/>
				</wsdl:message>
				<wsdl:portType name="AxisServicePortType">
					<wsdl:operation name="smpTongXin">
						<wsdl:input message="ns:smpTongXinRequest" wsaw:Action="urn:smpTongXin"/>
						<wsdl:output message="ns:smpTongXinResponse" wsaw:Action="urn:smpTongXinResponse"/>
					</wsdl:operation>
				</wsdl:portType>
				<wsdl:binding name="AxisServiceSoap11Binding" type="ns:AxisServicePortType">
					<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
					<wsdl:operation name="smpTongXin">
						<soap:operation soapAction="urn:smpTongXin" style="document"/>
						<wsdl:input>
							<soap:body use="literal"/>
						</wsdl:input>
						<wsdl:output>
							<soap:body use="literal"/>
						</wsdl:output>
					</wsdl:operation>
				</wsdl:binding>
			UDDI:注册，搜索webservice
 * 4 使用webService的开发流程
 *
 */
public class WebService {

}
