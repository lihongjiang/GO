package com.netfuture.go.db.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.netfuture.go.db.contentprovider.SmsInfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Xml;

public class PullUtils {

	// Pull解析XML
	public void recoverySms(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			// 得到pull解析器
			XmlPullParser xml = Xml.newPullParser();
			// 设置解析xml来源
			xml.setInput(fis, "UTF-8");
			// 循环解析
			int eventType = xml.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					// 文档开始
					break;
				case XmlPullParser.START_TAG:
					// 开始节点
					String name = xml.getName().trim();
					// 解析条数
					if (name.equals("sms")) {
						// 属性获取值方法
						// xml.getAttributeValue(null, "item");
					} else if (name.equals("id")) {
						// 获取文本值方法
						// id = Integer.parseInt(xml.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					// 结束节点
					if (xml.getName().equals("smsinfo")) {
						// 保存兄弟节点到集合
						// SmsInfo sms = new SmsInfo(id, address, date, type,
						// body);
						// coll.add(sms);
						// 添加短信,如果手机收件箱没收收到短信,是调用的问题
					}
					break;
				}
				eventType = xml.next();
			}

			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// pull XmlSerializer 保存到XML
	public boolean backupSms(File file) {

		try {
			FileOutputStream bw = new FileOutputStream(file, false);
			// 获得XML序列化类
			XmlSerializer xml = Xml.newSerializer();
			// 映射一个输出流
			xml.setOutput(bw, "UTF-8");

			// 开始文档

			xml.startDocument("UTF-8", true);
			// 根元素
			xml.startTag(null, "sms");
			xml.attribute(null, "item", "12");
			// N个兄弟元素
			for (int i = 0; i < 12; i++) {
				// 开始兄弟节点
				xml.startTag(null, "smsinfo");
				// 短信ID
				xml.startTag(null, "id");
				xml.text("12");
				xml.endTag(null, "id");
				// 短信来源
				xml.startTag(null, "address");
				xml.text("12121212");
				xml.endTag(null, "address");
				// 结束兄弟节点
				xml.endTag(null, "smsinfo");
			}
			// 结束根节点
			xml.endTag(null, "sms");
			// 结束文档
			xml.endDocument();
			xml.flush();
			// 刷新缓冲区
			bw.flush();
			// 关闭流
			bw.close();
			// 返回成功
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
