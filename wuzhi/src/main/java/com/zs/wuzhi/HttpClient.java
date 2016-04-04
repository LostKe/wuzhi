package com.zs.wuzhi;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
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
}
