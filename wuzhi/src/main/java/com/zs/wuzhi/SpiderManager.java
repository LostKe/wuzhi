package com.zs.wuzhi;

import com.zs.dao.Dao;
import com.zs.dao.DaoImpl;
import com.zs.wuzhi.bean.Diary;
import com.zs.wuzhi.bean.Undefind;

public class SpiderManager {
	
	Dao dao;
	
	private SpiderManager(){
		dao=new DaoImpl();
	};
	
	private static SpiderManager instance;
	
	
	private volatile static long userId=1;
	
	public static synchronized long getUserId(){
		return userId++;
	}
	
	public static synchronized boolean shouldContinue(){
		if(userId>256907){//目前最大用户
			return false;
		}else{
			return true;
		}
	}
	
	public static SpiderManager getInstance(){
		if(instance==null){
			synchronized (SpiderManager.class) {
				if(instance==null){
					instance=new SpiderManager();
				}
			}
		}
		return instance;
	}
	
	public void insertDiary(Diary diary){
		dao.insertDiary(diary);
		
	}
	
	public void insertUndefind(Undefind undefind){
		dao.insertUndefind(undefind);
	}
	
	

}
