/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.kaishengit.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaishengit.pojo.TulingRes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * XMLParse class
 *
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
public class XMLParse {

	/**
	 * 提取出xml数据包中的加密消息
	 * @param xmltext 待提取的xml字符串
	 * @return 提取出的加密消息字符串
	 * @throws AesException 
	 */
	public static Object[] extract(String xmltext) throws AesException     {
		Object[] result = new Object[3];
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
			NodeList nodelist2 = root.getElementsByTagName("ToUserName");
			//公众平台使用ToUserName，第三方平台使用 AppId
			if(nodelist2 == null || nodelist2.item(0) == null){
				nodelist2 = root.getElementsByTagName("AppId");
			}
			result[0] = 0;
			result[1] = nodelist1.item(0).getTextContent();
			result[2] = nodelist2.item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ParseXmlError);
		}
	}

	public static String generate(String fromUserName, String toUserName, String timestamp, String msgType, String content) {

		String format = "<xml>\n" + "<ToUserName><![CDATA[%1$s]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>\n"
				+ "<CreateTime>%3$s</CreateTime>\n"
				+ "<MsgType><![CDATA[%4$s]]></MsgType>\n"
				+ "<Content><![CDATA[%5$s]]></Content>\n" + "</xml>";
		Map<String, String> param = new HashMap();
		param.put("key", "932f929bc81b41b38de4d1a2ff72c038");
		param.put("info", content);
		param.put("userid", fromUserName);
		String res = HttpUtils.getData("http://www.tuling123.com/openapi/api", param);
		ObjectMapper mapper = new ObjectMapper();
		TulingRes tuling = null;
		try {
			tuling = mapper.readValue(res, TulingRes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return String.format(format, fromUserName, toUserName, timestamp, msgType, tuling.getText());

	}
}
