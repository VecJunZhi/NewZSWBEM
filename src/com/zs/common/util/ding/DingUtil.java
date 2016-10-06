package com.zs.common.util.ding;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import com.dingtalk.open.client.api.model.corp.MessageBody;
import com.dingtalk.open.client.api.model.corp.MessageBody.OABody.Body;
import com.dingtalk.open.client.api.model.corp.MessageType;
/**
 * 钉钉
 * 发送消息和富文本消息
 */
public class DingUtil {
	
	/**
	 * 统计数组中相同元素个数
	 * @param ss：要统计的数组元素
	 * @param opp：附加数组元素，用来与统计数组元素组成键值对（可以传个空的JSONArray，只用来获取统计数组相同元素个数）
	 * @return
	 */
		public static Map<String,Object> getDDmap(JSONArray ss,JSONArray opp) {
			Map<String,Object> map=new HashMap<String,Object>();
			for (int i = 0; i < ss.size(); i++) {
				String temp=ss.getString(i);
				int count=0;
				for (int j = 0; j < ss.size(); j++) {
					String temp2=ss.getString(j);
					if(temp.equals(temp2)){
						count++;
					}
					if(opp.size()>0){
						map.put(ss.getString(i), new DingDingVo(count, opp.getString(i)));
					}else{
						map.put(ss.getString(i), count);
					}
				}
			}
			return map;
		}
	/**
	 * 发送文本消息
	 * @param message：自定义消息内容  utf-8格式
	 * @param userId：  oa用户uid,若为多个用户：uid1|uid2|uid3
	 * @param partyId：部门id（可以为空）多个部门：pid1|pid2|pid3
	 * @param agentId：微应用id（云客的id：37907377）
	 * @return Receipt 返回的信息
	 */
	public static Receipt sendTextMessage(String message,String userId,String partyId,String agentId){
		Receipt receipt = new Receipt();
		try {
			String accessToken = AuthHelper.getAccessToken();//获取accessToken
			MessageBody.TextBody textBody = new MessageBody.TextBody();//定义文本消息
			textBody.setContent(message);
			MessageDelivery messageDelivery = new MessageDelivery(userId, partyId, agentId);
			messageDelivery.withMessage(MessageType.TEXT, textBody);
			receipt=AuthHelper.send(accessToken, messageDelivery,"text");//发送
		}catch (Exception e) {
			e.printStackTrace();
		}
		return receipt;
	}
	/**
	 * 发送富文本消息
	 * @param message：自定义消息内容    utf-8格式
	 * @param userId：  oa用户uid,若为多个用户：uid1,uid2,uid3
	 * @param partyId：部门id（可以为空）多个部门：pid1,pid2,pid3
	 * @param agentId：微应用id（云客的id：37907377）
	 * @return Receipt 返回的错误信息
	 */
	public static Receipt sendRichMessage(String toUrl,String message,String userId,String partyId,String agentId){
		Receipt receipt = new Receipt();
		try {
			String accessToken = AuthHelper.getAccessToken();//获取accessToken
			MessageBody.OABody oaBody = new MessageBody.OABody();//定义oa富文本消息
			oaBody.setMessage_url(toUrl);
			Body body = new Body();
			body.setContent(message);
			oaBody.setBody(body);
			MessageDelivery messageDelivery = new MessageDelivery(userId, partyId, agentId);
			messageDelivery.withMessage(MessageType.OA, oaBody);
			receipt=AuthHelper.send(accessToken, messageDelivery,"oa");//发送
		}catch (Exception e) {
			e.printStackTrace();
		}
		return receipt;
	}
	public static void main(String[] args){
		String message="好好学习";
		String userId="149";
		String partyId="";
		String agentId="37907377";
		String toUrl="http://www.hao123.com";
		DingUtil.sendTextMessage(message, userId, partyId, agentId);
		System.out.println("over....");
		//DingUtil.sendRichMessage(toUrl, message, userId, partyId, agentId);
	}
}
