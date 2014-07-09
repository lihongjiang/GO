package com.netfuture.go.db.xml;


import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomXmlUtil {
	
	String currentstate = "";

	/**
	 * 解析文本消息
	 * 
	 * @param inStream
	 * @return
	 */
	public HashMap<String,String> parseTextMessage(InputStream inStream) {
		final HashMap<String, String> msg = new HashMap<String, String>();

		try {
		
			// 实例化一个文档构建器工厂
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// 通过文档构建器工厂获取一个文档构建器
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 通过文档通过文档构建器构建一个文档实例
			Document document = builder.parse(inStream);
			// 获取XML文件根节点
			Element root = document.getDocumentElement();
			// 获得所有子节点
			NodeList childNodes = root.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				// 遍历子节点
				Node childNode = (Node) childNodes.item(j);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					Element childElement = (Element) childNode;
					msg.put(childElement.getNodeName(), childElement.getFirstChild().getNodeValue());
				}
			}
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
