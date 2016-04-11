package com.zs.wuzhi;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;

import com.zs.util.CommonUtil;

/**
 * 模拟浏览器
 * 
 * @author zhangshuqing
 *
 */
public class HttpClient {
	protected final Log logger = LogFactory.getLog(getClass());
	private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36";

	/**
	 * 
	 * @param user_no
	 *            用户编号 从1开始爬数据
	 */
	public static Document getWuzhiNowDoc(long user_no) {
		Document doc = null;
		try {
			Connection con = HttpConnection.connect(CommonUtil.getUrl(user_no));
			con.userAgent(USER_AGENT);
			
			doc = con.get();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;

	}
	
	public static String getImg(String root,String url){
		String img_name=null;
		try {
			URL u=new URL(url);
			HttpURLConnection conn=(HttpURLConnection) u.openConnection();
			conn.setAllowUserInteraction(false);         
			conn.setDoOutput(true);
			conn.addRequestProperty("Cache-Control", "no-cache");
			conn.addRequestProperty("User-Agent", USER_AGENT);
			conn.addRequestProperty("Referer", url.substring(0, url.indexOf('/', url.indexOf('.'))+1));
			conn.connect();
			if(conn.getResponseCode() != HttpURLConnection.HTTP_OK){
				 return null;
			}
			InputStream in=conn.getInputStream();
			img_name=getFileName(url);
			File path=new File(root+"img");
			if(!path.exists()){
				path.mkdirs();
			}
			File out_img=new File(path,img_name);
			FileUtils.copyInputStreamToFile(in, out_img);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img_name;
	}
	
	
	private static String getFileName(String url){
		int index=url.lastIndexOf("/");
		return url.substring(index+1,url.length());
	}
}
