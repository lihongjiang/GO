package com.netfuture.go.db.xml;


import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxXmlUtil {
	String currentstate = "";

	/**
	 * 解析消息内容
	 * 
	 * @param is
	 * @return
	 */
	public HashMap<String, String>  parseTextMessage(InputStream is) {
		final HashMap<String, String> msg = new HashMap<String, String>();

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(is, new DefaultHandler() {
				@Override
				public void startDocument() throws SAXException {
					super.startDocument();
				}

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					currentstate = qName;
				}

				@Override
				public void characters(char[] ch, int start, int length)
						throws SAXException {
					String theString = new String(ch, start, length);
					// 消息内容
					if ("Content".equals(currentstate)) {
						msg.put("Content", theString);
						currentstate = "";
					}
					// 消息ID
					else if (("MsgId".equals(currentstate))) {
						msg.put("MsgId", theString);
						currentstate = "";
					}
					// 消息类型
					else if (("MsgType".equals(currentstate))) {
						msg.put("MsgType", theString);
						currentstate = "";
					}
					// 消息来源唯一ID
					else if (("FromUserName".equals(currentstate))) {
						msg.put("FromUserName", theString);
						currentstate = "";
					}
					// 消息用户
					else if (("ToUserName".equals(currentstate))) {
						msg.put("ToUserName", theString);
						currentstate = "";
					}
					// 消息时间
					else if (("CreateTime".equals(currentstate))) {
						msg.put("CreateTime", theString);
						currentstate = "";
					}
					//图片消息
					else if (("PicUrl".equals(currentstate))) {
						msg.put("PicUrl", theString);
						currentstate = "";
					}
					//mediaId
					else if (("MediaId".equals(currentstate))) {
						msg.put("MediaId", theString);
						currentstate = "";
					}
					//format
					else if (("Format".equals(currentstate))) {
						msg.put("Format", theString);
						currentstate = "";
					}
					//thumbMediaId
					else if (("ThumbMediaId".equals(currentstate))) {
						msg.put("ThumbMediaId", theString);
						currentstate = "";
					}
					// location_X  VARCHAR,--地理位置维度
					else if (("Location_X".equals(currentstate))) {
						msg.put("Location_X", theString);
						currentstate = "";
					}
					// location_Y  VARCHAR,--地理位置精度
					else if (("Location_Y".equals(currentstate))) {
						msg.put("Location_Y", theString);
						currentstate = "";
					}
					// scale  VARCHAR,--地图缩放大小
					else if (("Scale".equals(currentstate))) {
						msg.put("Scale", theString);
						currentstate = "";
					}
					// label  VARCHAR,--地理位置信息
					else if (("Label".equals(currentstate))) {
						msg.put("Label", theString);
						currentstate = "";
					}
					// title  VARCHAR,--消息标题
					else if (("Title".equals(currentstate))) {
						msg.put("Title", theString);
						currentstate = "";
					}
					//description  VARCHAR,--消息描述
					else if (("Description".equals(currentstate))) {
						msg.put("Description", theString);
						currentstate = "";
					}
					//event  VARCHAR,--事件类型，有ENTER(进入会话)和LOCATION(地理位置)
					else if (("Event".equals(currentstate))) {
						msg.put("Event", theString);
						currentstate = "";
					}
					//latitude  VARCHAR,--地理位置维度，事件类型为LOCATION的时存在
					else if (("Latitude".equals(currentstate))) {
						msg.put("Latitude", theString);
						currentstate = "";
					}
					//longitude  VARCHAR,--地理位置经度，事件类型为LOCATION的时存在
					else if (("Longitude".equals(currentstate))) {
						msg.put("Longitude", theString);
						currentstate = "";
					}
					//precision  VARCHAR,--地理位置精度，事件类型为LOCATION的时存在
					else if (("Precision".equals(currentstate))) {
						msg.put("Precision", theString);
						currentstate = "";
					}
				}
			});
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
