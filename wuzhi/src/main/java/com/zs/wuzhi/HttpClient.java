package com.zs.wuzhi;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

import com.zs.util.CommonUtil;

/**
 * 模拟浏览器
 * 
 * @author zhangshuqing
 *
 */
public class HttpClient {

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

	public static void main(String[] args) {
		for (int i = 36979; i < 36982; i++) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			Document doc = getWuzhiNowDoc(i);
			// System.out.println(doc);
			String title = doc.getElementsByTag("title").first().text();
			if ("页面不存在".equals(title)) {
				
				continue;
			} else {
				System.out.println(title);
			}
			

			// System.out.println(element);

			// 用户头像，用户签名
			Element user_info_element = doc.getElementsByClass("siderbar_left").get(0);
			// System.out.println(user_info_element);

			// 获取头像
			Element user_img_node = user_info_element.getElementsByTag("img").first();
			if(user_img_node!=null){
				String user_img_url = user_img_node.attr("src");
				System.out.println(user_img_url);
			}else{
				System.out.println(title+":用户头像为空");
			}
			
			
			// 获取签名
			Element user_sign_node = user_info_element.getElementsByTag("span").first();
			if (user_sign_node != null) {
				String user_sign = user_sign_node.text();
				System.out.println(user_sign);
			}

			Element privacy_tip = doc.getElementsByClass("privacy_tip").first();
			if (privacy_tip != null) {
				System.out.println("该用户日记设置了只自己可见");
				continue;
			}
			
			// 用户最后一篇日记 时间，总共获得心数目
			Element user_start_element = doc.getElementsByClass("main_right").first();
			if(user_start_element.children().isEmpty()){//用户已注销
				System.out.println(title+":用户已注销");
				continue;
			}
			
			
			
			
			Elements user_start = user_start_element.getElementsByTag("span");
			Element last_diary_time_element = user_start.get(0);// 最后一篇日记时间节点
			String last_diary_time = last_diary_time_element.text();
			System.out.println(last_diary_time);
			Element start_count_element = user_start.get(1);// 心数目节点
			String e = start_count_element.text();
			System.out.println(e);

			// 用户最后一天的日记，可能有多篇
			Elements note_each = doc.getElementsByClass("note_each");
			for (Element note : note_each) {
				String time = note.getElementsByClass("note_time").first().text();
				System.out.println(time);
				String content = note.getElementsByClass("note_content").first().text();
				System.out.println(content);
			}
			
		}
		
	}
}
