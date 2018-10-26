package com.zkx.cn.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 接口调用工具
 * @author 张凯旋
 *
 */
public class HttpUtil {
	
	private static final String JSON = "application/json";//json请求类型
	private static final String FORM_DATA = "application/x-www-form-urlencoded;charset=UTF-8;";//formdata请求类型
	
//  webService借口调用示例
//	public static void main(String[] args) {
//		String url = "http://chowkd1600105:8080/OpSOA/cxf/hempolicy4eser";
//		StringBuffer sb = new StringBuffer();
//		sb.append(
//				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.eservice.getPolicyInfo.policysrv.cn.aia/\">");
//		sb.append("<soapenv:Header/>");
//		sb.append("<soapenv:Body>");
//		sb.append("<ser:hempolicy4eser>");
//		sb.append("<policyNo>C010000980</policyNo>");
//		sb.append("</ser:hempolicy4eser>");
//		sb.append("</soapenv:Body>");
//		sb.append("</soapenv:Envelope>");
//
//		String str = doWebService(url, sb.toString());
		
//		PolicyInfoVo policyInfoVo = SAXParseUtil.parseToList(str, PolicyInfoVo.class);
//	}
	
	/**
	 * 发送webService接口请求
	 * @param url 请求地址（删除?wsdl）
	 * @param soapXml（拼接请求xml字符串（可由soapUI工具获得））
	 * @return xml字符串
	 */
	public static String doWebService(String url, String soapXml) {
		
		System.out.println("请求地址：" + url);
		System.out.println("请求Xml:" + soapXml);
		String retStr = "";
		// HttpClient
		HttpClient httpClient = new HttpClient();
		// PostMethod
		PostMethod postMethod = new PostMethod(url);
		// 设置请求和传输超时时间
		// httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt(socketTimeout));
		// 连接超时
		// httpClient.getHttpConnectionManager().getParams().setSoTimeout(Integer.parseInt(connectTimeout));
		// 设置请求体
		try {
			RequestEntity requestEntity = new ByteArrayRequestEntity(soapXml.getBytes("utf-8"));
			postMethod.setRequestEntity(requestEntity);
			// postMethod.setRequestBody(soapXml);
			// 方法过时     
			// 设置请求参数     
			postMethod.setRequestHeader("Content-Type", "text/xml;charset=UTF-8");
			postMethod.setRequestHeader("SOAPAction", "");
			httpClient.executeMethod(postMethod); // 发送请求             
			retStr = postMethod.getResponseBodyAsString();// 响应体             
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭链接             
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return retStr;
	}
	
	/**
	 * formData格式post请求
	 * @param url 请求地址
	 * @param mapEntity 请求参数
	 * @param headerMap 请求头设置参数
	 * @return json字符串
	 * @throws Exception 
	 */
	public static String formDataDoPost(String url, Map<String, String> mapEntity, Map<String, String> headerMap) throws Exception{
		//设置参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : mapEntity.entrySet()) {
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		HttpEntity entity = new UrlEncodedFormEntity(list, StandardCharsets.UTF_8);
		//请求头设置请求类型
		headerMap.put("Content-Type", FORM_DATA);
		
		System.out.println("请求类型：post--" + FORM_DATA);
		
		return doPost(url, entity, headerMap);
	}
	
	/**
	 * Json格式post请求
	 * @param url 请求地址
	 * @param stringEntity json字符串
	 * @param headerMap 请求头设置参数
	 * @return json字符串
	 * @throws Exception 
	 */
	public static String jsonDoPost(String url, String stringEntity, Map<String, String> headerMap) throws Exception{
		//设置参数
		HttpEntity entity = new StringEntity(stringEntity, StandardCharsets.UTF_8);
		
		//请求头设置请求类型
		headerMap.put("Content-Type", JSON);
		
		System.out.println("请求类型：post--" + JSON);
		
		return doPost(url, entity, headerMap);
	}
	
	/**
	 * get请求
	 * @param url 请求地址
	 * @param headerMap 请求头参数
	 * @return json字符串
	 * @throws Exception 
	 */
	public static String doGet(String url, Map<String, String> headerMap) throws Exception{
		//创建get请求对象
		HttpGet request = new HttpGet(url);
		
		//设置请求头
		for (Entry<String, String> entry : headerMap.entrySet()) {
			request.addHeader(entry.getKey(), entry.getValue());
		}
		
		System.out.println("请求类型：get");
		
		return doRequest(request);
	}
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static String doGet(String url) throws Exception{
		//创建get请求对象
		HttpGet request = new HttpGet(url);
		
		System.out.println("请求类型：get");
		
		return doRequest(request);
	}
	
	/**
	 * post请求
	 * @param url 请求地址
	 * @param entity 请求参数
	 * @param headerMap 请求头参数
	 * @return json字符串
	 * @throws Exception 
	 */
	public static String doPost(String url, HttpEntity entity, Map<String, String> headerMap) throws Exception{
		//创建post请求对象
		HttpPost request = new HttpPost(url);
		
		//设置请求头
		for (Entry<String, String> entry : headerMap.entrySet()) {
			request.addHeader(entry.getKey(), entry.getValue());
		}
		
		request.setEntity(entity);
		return doRequest(request);
	} 
	/**
	 * post请求
	 * @param url 请求地址
	 * @param entity 请求参数
	 * @return
	 * @throws Exception 
	 */
	public static String doPost(String url, HttpEntity entity) throws Exception{
		//创建post请求对象
		HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		return doRequest(request);
	} 
	
	/**
	 * 资源下载请求
	 * @param response 
	 * @param url 请求地址
	 * @param headerMap 请求头参数
	 */
	public static void download(HttpServletResponse response, String url, Map<String, String> headerMap) {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpResponse httpResponse = null;
		//创建get请求对象
        HttpGet request = new HttpGet(url);
        InputStream is = null;
        //设置请求头
  		for (Entry<String, String> entry : headerMap.entrySet()) {
  			request.addHeader(entry.getKey(), entry.getValue());
  		}
        
        try {
        	httpResponse = client.execute(request);
            int code = httpResponse.getStatusLine().getStatusCode();
            
            if (code == HttpStatus.SC_OK) {
            	// 根据InputStream 下载文件
                ByteArrayOutputStream output = new ByteArrayOutputStream();
            	HttpEntity entity = httpResponse.getEntity();
            	OutputStream os = response.getOutputStream();
            	is = entity.getContent();
            	byte[] buffer = new byte[4096];
                int r = 0;
                
                while ((r = is.read(buffer)) > 0) {
                    output.write(buffer, 0, r);
                }
                
                output.writeTo(os);
                output.flush();
                output.close();
                os.close();
                EntityUtils.consume(entity);
            }
        } 
        catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (null != client) {
        		try {
    				client.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
			}
		}
	}
	
	/**
	 * 发送请求
	 * @param request （HttpUriRequest请求对象）
	 * @return json字符串
	 * @throws Exception 
	 * @throws ClientProtocolException 
	 */
	private static String doRequest(HttpUriRequest request) throws Exception{
		
        CloseableHttpClient httpclient = HttpClients.createDefault();;  
        CloseableHttpResponse response = null;
			response = httpclient.execute(request);
            int code = response.getStatusLine().getStatusCode();
            
            System.out.println("请求返回code：" + code);
            
            if (code == HttpStatus.SC_OK) {	//请求成功
            	HttpEntity responseEntity = response.getEntity();
            	String jsonString = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            	return jsonString;
            }
        
			if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
	}
}


