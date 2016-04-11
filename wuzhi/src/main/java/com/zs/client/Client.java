package com.zs.client;

import org.jsoup.nodes.Document;

import com.zs.wuzhi.AnalysisDoc;
import com.zs.wuzhi.HttpClient;
/**
 * 开起30个线程爬数据
 * @author zhangshuqing
 *
 */
public class Client {
	public static void main(String[] args) {
//		for (int i = 0; i < 40; i++) {
//			Thread t=new Thread(new AnalysisDoc());
//			t.start();
//		}
		Document doc=HttpClient.getWuzhiNowDoc(140445);
		doc.outputSettings().prettyPrint(true);
		System.out.println(doc);
	}

}
