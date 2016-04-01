package com.zs.client;

import com.zs.wuzhi.AnalysisDoc;
/**
 * 开起30个线程爬数据
 * @author zhangshuqing
 *
 */
public class Client {
	public static void main(String[] args) {
		for (int i = 0; i < 40; i++) {
			Thread t=new Thread(new AnalysisDoc());
			t.start();
		}
	}

}
