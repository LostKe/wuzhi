package com.zs.wuzhi;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zs.util.CommonUtil;
import com.zs.wuzhi.bean.Diary;
import com.zs.wuzhi.bean.DiaryContent;
import com.zs.wuzhi.bean.Undefind;

/**
 * 分析文档数据存入数据库中
 * 
 * @author zhangshuqing
 *
 */
public class AnalysisDoc implements Runnable {
	public AnalysisDoc() {

	}

	public void run() {
		while(SpiderManager.shouldContinue()){
			long userId = SpiderManager.getUserId();
			System.out.println("开始爬用户ID>>>" + userId+" 的数据");
			Document doc=HttpClient.getWuzhiNowDoc(userId);
			
			//处理页面不存在情况
			String title = doc.getElementsByTag("title").first().text();
			if ("页面不存在".equals(title)) {
				SpiderManager.getInstance().insertUndefind(new Undefind(userId));
				continue;
			} else {
				Diary diary=new Diary();
				diary.setUserId(userId);
				diary.setUserName(title);
				// 用户头像，用户签名
				Element user_info_element = doc.getElementsByClass("siderbar_left").first();
				// 获取头像
				Element user_img_node = user_info_element.getElementsByTag("img").first();
				if(user_img_node!=null){
					String user_img_url = user_img_node.attr("src");
					diary.setImg(user_img_url);
				}
				
				// 获取签名
				Element user_sign_node = user_info_element.getElementsByTag("span").first();
				if (user_sign_node != null) {
					String user_sign = user_sign_node.text();
					diary.setSign(user_sign);
				}
				
				Element privacy_tip = doc.getElementsByClass("privacy_tip").first();
				if (privacy_tip != null) {
					diary.setIsPublic(0);
					SpiderManager.getInstance().insertDiary(diary);
					continue;
				}
				
				// 用户最后一篇日记 时间，总共获得心数目
				Element user_start_element = doc.getElementsByClass("main_right").first();
				if(user_start_element.children().isEmpty()){//用户已注销
					diary.setAccountStatu(0);
					SpiderManager.getInstance().insertDiary(diary);
					continue;
				}
				
				Elements user_start = user_start_element.getElementsByTag("span");
				Element last_diary_time_element = user_start.get(0);// 最后一篇日记时间节点
				String last_diary_time = last_diary_time_element.text();
				diary.setLast(CommonUtil.getDate(last_diary_time));
				Element start_count_element = user_start.get(1);// 心数目节点
				String star_count = start_count_element.text();
				diary.setStar(CommonUtil.getStarCount(star_count));
				List<DiaryContent> contents=new ArrayList<DiaryContent>();
				// 用户最后一天的日记，可能有多篇
				Elements note_each = doc.getElementsByClass("note_each");
				for (Element note : note_each) {
					DiaryContent content=new DiaryContent();
					String time = note.getElementsByClass("note_time").first().text();
					content.setTime(time);
					String content_text = note.getElementsByClass("note_content").first().text();
					content.setText(content_text);
					contents.add(content);
				}
				diary.setDiaryContent(contents);
				SpiderManager.getInstance().insertDiary(diary);
				
			}
			
		}
		
		
	}

}
