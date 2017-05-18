package com.iif.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 网络工具类
 * 
 * @author Administrator
 * 
 */
public class NetUtil {
	public static byte[] getImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			return readStream(inputStream);
		}
		return null;
	}

	public static String getHtml(String path, String encoding) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			byte[] data = readStream(inputStream);
			return new String(data, encoding);
		}
		return null;
	}

	/**description:通过HTTPget请求获取数据
	 * Nov 23, 2013
	 * @author:sajh
	 */
	public static String getDataByHttpGet(String path) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet  httpget = new HttpGet(path);
		HttpResponse httpResponse = httpClient.execute(httpget);
		
		HttpEntity ett = httpResponse.getEntity(); // 获取响应里面的内容
		
		String str=  EntityUtils.toString(ett);
		//System.out.println(str);
		httpClient.getConnectionManager().shutdown();// Add By LiuM 2014-12-3
		return str;
	}
	
	
	/**description:通过HTTPget请求获取数据
	 * Nov 23, 2013
	 * @author:sajh
	 */
	public static String getDataByHttpGet(URI uri) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet  httpget = new HttpGet(uri);
		HttpResponse httpResponse = httpClient.execute(httpget);
		
		HttpEntity ett = httpResponse.getEntity(); // 获取响应里面的内容
		
		String str=  EntityUtils.toString(ett);
		//System.out.println(str);
		httpClient.getConnectionManager().shutdown();// Add By LiuM 2014-12-3
		return str;
	}
	
	/**
	 * 发送 XML 请求
	 * 
	 * @param path
	 * @param map
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static InputStream sendXmlRequest(String path,InputStream inputStream, String encoding) throws Exception {
		byte[] data = readStream(inputStream);
		URL url = new URL(path);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setConnectTimeout(6 * 1000);
		con.setDoOutput(true);// 发送 POST 请求必须允许输出
		con.setUseCaches(false);// 不设置 cache
		con.setRequestProperty("Connection", "Keep-Alive");// 维持长链接
		con.setRequestProperty("Charset", encoding);
		con.setRequestProperty("Content-Length", String.valueOf(data.length));
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//		con.setRequestProperty("content-type", "text/html");
		DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
		dataOutputStream.write(data);
		dataOutputStream.flush();
		dataOutputStream.close();
		if (con.getResponseCode() == 200) {
			return con.getInputStream();
		}
		return null;
	}

	/**
	 * 发送 POST 请求
	 * 
	 * @param path
	 *            请求路径
	 * @param map
	 *            参数
	 * @return 如果为空设置 GET 请求
	 * @throws Exception
	 */
	public static InputStream sendPostRequest(String path,Map<String, String> map) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			// sb.append(entry.getKey()).append("=").append(entry.getValue());
			sb.append('&');
		}
		sb.deleteCharAt(sb.length() - 1);

		byte[] data = sb.toString().getBytes();
		URL url = new URL(path);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setConnectTimeout(6 * 1000);
		con.setDoOutput(true);// 发送 POST 请求必须设置允许输出
		con.setUseCaches(false);// 不设置 cache
		con.setRequestProperty("Connection", "Keep-Alive");// 维持长链接
		con.setRequestProperty("Charset", "UTF-8");
		con.setRequestProperty("Content-Length", String.valueOf(data.length));
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
		dataOutputStream.write(data);
		dataOutputStream.flush();
		dataOutputStream.close();
		if (con.getResponseCode() == 200) {
			return con.getInputStream();
		}
		return null;
	}

	/**description:发送post请求，参数是json字符串格式的
	 * Apr 27, 2014
	 * @author:sajh
	 */
	public static String sendPostReqByJsonParam(String jsonStr,String url)throws Exception{
		
		try {
		    // 创建连接
		   URL requestUrl = new URL(url);
		    HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
		    conn.setDoOutput(true);
		    conn.setDoInput(true);
		    conn.setUseCaches(false);
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		    conn.connect();
		    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		    // json参数
		    out.writeBytes(jsonStr);
		    out.flush();
		    out.close();
		     
		    // 获取响应
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String lines;
		    StringBuffer sb = new StringBuffer();
		    while((lines = reader.readLine()) != null){
		        lines = new String(lines.getBytes(), "utf-8");
		        sb.append(lines);
		    }
		    reader.close();
		    // 关闭连接
		    conn.disconnect();
		    return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("发送post请求失败：URL="+url+" param="+jsonStr);
		}
	}
	/**
	 * 把输入流转换成字符串
	 * 
	 * @param inputStream
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String getTextContent(InputStream inputStream, String encoding)
			throws Exception {
		byte[] data = readStream(inputStream);
		return new String(data, encoding);
	}

	/**
	 * 根据路径获返回输入流
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static InputStream getContent(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		if (conn.getResponseCode() == 200) {
			return conn.getInputStream();
		}
		return null;
	}

	/**
	 * 把输入流转换成字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream outputSteam = new ByteArrayOutputStream();// 往内存中写字节数据
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inputStream.read(buffer)) != -1) {
			outputSteam.write(buffer, 0, len);
		}

		inputStream.close();
		outputSteam.close();
		return outputSteam.toByteArray();
	}

	public static void main(String[] args) throws Exception {
		String xml="<xml><ToUserName>ziroomer</ToUserName><FromUserName>chengxiaojian_</FromUserName><CreateTime>"+new Date().getTime()+"</CreateTime><MsgType>text</MsgType><Content>2</Content></xml>";
		
		InputStream inputStream=new ByteArrayInputStream(xml.getBytes());   
		
		
		
		String content=getTextContent(NetUtil.sendXmlRequest("http://localhost:6/ZRWCT/WeiXinSearcher", inputStream,"UTF-8"),"UTF-8");
		System.out.println(content);
	}
}
