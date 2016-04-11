package com.zs.wuzhi;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.model.Filters;
import com.zs.dao.ADao;
import com.zs.dao.DiaryDao;
import com.zs.dao.DiaryDaoImpl;
import com.zs.dao.ResultDao;
import com.zs.dao.ResultDaoImpl;
import com.zs.dao.UndefindDaoImpl;
import com.zs.db.DBHelper;
import com.zs.wuzhi.bean.Diary;
import com.zs.wuzhi.bean.Result;
import com.zs.wuzhi.bean.Undefind;

public class SpiderManager {
	
	DiaryDao diaryDao;
	ResultDao resultDao;
	ADao<Undefind> unfindDao;	
	private SpiderManager(){
		diaryDao=new DiaryDaoImpl();
		resultDao =new ResultDaoImpl();
		unfindDao=new UndefindDaoImpl();
	};
	
	private static SpiderManager instance;
	
	
	private volatile  long userId;
	
	private volatile int link_invalid_count;
	
	private volatile boolean link_invalid=false;
	
	public void init(){
		userId=1;
		link_invalid_count=1;
	}
	
	public void clearLinkInvalid(){
		link_invalid=false;
		link_invalid_count=1;
	}
	public synchronized void addLinkInvalid(){
		link_invalid_count++;
	}
	public boolean isLinkInvalid(){
		return link_invalid;
	}
	
	public  synchronized long getUserId(){
		return userId++;
	}
	
	public  synchronized boolean shouldContinue(){
		if(link_invalid_count>500){//连续无效账户
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
	
	
	public void loadData(String root){
		for (int i = 0; i < 10; i++) {
			Thread t=new Thread(new AnalysisDoc(root));
			t.start();
		}
	}
	
	/**
	 * 分析爬取到得数据
	 */
	public void analysisAllData(){
		Result r=new Result();
		//1.获取 星星最多的前10个用户
		List<Diary> starMaxList=diaryDao.queryStarMax();
		List<Long> ids=new ArrayList<Long>();
		for (Diary d : starMaxList) {
			ids.add(d.getUserId());
		}
		r.setMaxstars(ids);
		//2.注销的账号数量
		List<Diary> indevids=diaryDao.findMany(Filters.eq(DBHelper.DiaryCollection.ACCOUNT_STATU, 0));
		r.setInvalidCount(indevids.size());
		//3.日记公开的用户
		List<Diary> public_user=diaryDao.findMany(Filters.eq(DBHelper.DiaryCollection.IS_PUBLIC, 1));
		r.setPublicCount(public_user.size());
		//4.日记非公开的用户
		List<Diary> private_user=diaryDao.findMany(Filters.eq(DBHelper.DiaryCollection.IS_PUBLIC, 0));
		r.setPrivateCount(private_user.size());
		resultDao.insertItem(r);
	}
	
	
	public void insertDiary(Diary diary){
		diaryDao.insertItem(diary);
	}
	
	public void insertUndefind(Undefind undefind){
		unfindDao.insertItem(undefind);
	}
	
	

}
