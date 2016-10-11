package com.zst.test.rbac.webService;
/**
 * 1 ʲô��webService
 * 		��webservice��һ�����Խ��Լ������ĳ��򷢲�ΪwebӦ�ó��򣬿��Ա�����Ӧ�ó���ʹ�á�
 * 			ͨ��web���� Ӧ��  ���ҡ�
 * 			ʹ�ÿ��ŵ�Э��ͨ�ţ�http
 * 			ʹ��UDDI������webservice��
 * 2 Ϊʲôʹ��webService
 * 		�𣺽����򷢲�Ϊweb������ߴ��븴���Ժʹ�����õİ�ȫ�ԡ�
 * 			�����������е�web���񣬼��ٴ��뿪������������Эͬ������
 * 3 webService��ʵ��ԭ��
 * 		��webServiceƽ̨��Ҫ������Ԫ�أ�SOAP  UUDI  WSDL.
 * 			SOAP:simple object access protocol ��һ�ּ򵥵������Ļ���xml�Ľ������ݵ�һ��Э�顣���Լ���Ϊ��SOAP=XML+HTTP,���������web���Ͻ����ṹ��(xml)�̻�(xml����)�����ݡ�
 * 				 SOAP��������������Ϣ�ĸ�ʽ��һ��soap��Ϣ����һ����ͨ��xml�ļ���
 * 				 SOAP��Ϣ�Ļ����ṹ��
						<?xml version="1.0"?>
						<soap:Envelope//�����yuԪ�أ����԰ɴ�xml�ļ�����ʾΪһ��soap��Ϣ��
						xmlns:soap="http://www.w3.org/2001/12/soap-envelope"//����ʹ��
						soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">//����ʹ��
						<soap:Header>//��ѡԪ�أ����ṩ������envelope�ĵ�һ��Ԫ�ء�
						...
						</soap:Header>
						<soap:Body>//��ѡԪ��
						...
						  <soap:Fault>
						  ...
						  </soap:Fault>
						</soap:Body>
						</soap:Envelope>	
				SOAP HTTP Binding��
					SOAP������ָ����soap��������http����/��Ӧ��
						ʵ����POST /item HTTP/1.1
							Content-Type: application/soap+xml; charset=utf-8
			WSDL:�������ʹ�÷�����web���񣬽ӿ���ʲô������Щ��������Ҫʲô������������������ʲô��
					wsdlԪ�أ�protType, message   types  binding 
					protType:������ִ�еĲ�����
					bingding:����ʹ�õ�ͨ��Э�顣
					message:ÿ����Ϣ����һ������������ɡ����԰���Щ����������ͳ���������һ���������õĲ�����
					types �Բ����������������ͣ��������Ƶ�������
				ʵ����
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
			UDDI:ע�ᣬ����webservice
 * 4 ʹ��webService�Ŀ�������
 *
 */
public class WebService {

}
