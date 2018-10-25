package com.zkx.cn.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.sf.json.xml.XMLSerializer;

/**
 * xml解析
 * @author NSNP901
 *
 */
public class SAXParseUtil <T> extends DefaultHandler{

	private StringBuffer buf;   
	private String str;   
	public List<T> list = new ArrayList<T>();
	private JSONObject json;
	private Class<T> clazz;
	
	public SAXParseUtil(){   
	} 
	
	/**
	 * xml转javabean集合（适用于简单的javaBean，XML元素与javaBean属性名一致）
	 * @param protocolXML
	 * @param clazz 
	 * @return 
	 */
	public static <T> List<T>  parseToList(String protocolXML, Class<T> clazz) {

			SAXParseUtil<T> tsax = getSAXParseUtil(protocolXML, clazz);
			List<T> lists = tsax.list;
			if (lists.size() == 0) {
				lists.add(JSON.parseObject(tsax.json.toJSONString(), clazz));
			}
			return tsax.list;
	}

	/**
	 * xml转单个javaBean（适用于简单的javaBean，XML元素与javaBean属性名一致）
	 * @param protocolXML
	 * @param clazz 
	 * @return 
	 */
	public static <T> T  parseToObject(String protocolXML, Class<T> clazz) {

		SAXParseUtil<T> tsax = getSAXParseUtil(protocolXML, clazz);
		T data = JSON.parseObject(tsax.json.toJSONString(), clazz);
		return data;
	}
	
	
	/**
	 * xml转单个JSONObject
	 * @param protocolXML
	 * @return 
	 */
	public static JSONObject  parseToJsonObject(String protocolXML) {
		
		SAXParseUtil<JSONObject> tsax = new SAXParseUtil<JSONObject>();
		SAXParserFactory saxfac = SAXParserFactory.newInstance();
		StringReader str = new StringReader(protocolXML);
		InputSource inp = new InputSource(str);

		SAXParser saxparser;
		try {
			saxparser = saxfac.newSAXParser();
			saxparser.parse(inp, tsax);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return tsax.json;
	}
	
	@Override
	public void startDocument() throws SAXException{   
		buf=new StringBuffer();  
		json = new JSONObject();
	}   

	@Override
	public void endDocument() throws SAXException{  
	}   

	@Override
	public void endElement(String namespaceURI,String localName,String fullName )throws SAXException{ 
		str = buf.toString();
		if (json.containsKey(fullName)) {
			list.add(JSON.parseObject(json.toJSONString(), clazz));
			json = new JSONObject();
		}
		json.put(fullName, str.trim());
		buf.delete(0,buf.length());   
	}   

	@Override
	public void characters( char[] chars, int start, int length )throws SAXException{   
		buf.append(chars,start,length);   
	} 
	
	/**
	 * 解析为json字符串（适用性更广）
	 * @param xml
	 * @return json字符串
	 */
	public static String ConvertXMLtoJSON(String xml)  {
		  XMLSerializer xmlSerializer = new XMLSerializer(); 
		  net.sf.json.JSON json = xmlSerializer.read(xml);
		  return json.toString();
	} 
	
	//将xml包含数据注入SAXParseUtil对象的属性中
	private static <T> SAXParseUtil<T> getSAXParseUtil(String protocolXML, Class<T> clazz){
		SAXParseUtil<T> tsax = new SAXParseUtil<T>();
		SAXParserFactory saxfac = SAXParserFactory.newInstance();
		StringReader str = new StringReader(protocolXML);
		InputSource inp = new InputSource(str);
		tsax.clazz = clazz;
		try {
			SAXParser saxparser = saxfac.newSAXParser();
			saxparser.parse(inp, tsax);
			return tsax;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
