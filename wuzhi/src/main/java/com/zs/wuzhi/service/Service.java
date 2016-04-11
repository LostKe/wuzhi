package com.zs.wuzhi.service;

import java.util.Random;

import com.mongodb.client.model.Filters;
import com.zs.dao.ADao;
import com.zs.dao.DiaryDao;
import com.zs.dao.DiaryDaoImpl;
import com.zs.dao.ResultDao;
import com.zs.dao.ResultDaoImpl;
import com.zs.dao.UndefindDaoImpl;
import com.zs.db.DBHelper;
import com.zs.wuzhi.SpiderManager;
import com.zs.wuzhi.bean.Diary;
import com.zs.wuzhi.bean.Undefind;

public class Service {

	DiaryDao diaryDao;
	ResultDao resultDao;
	ADao<Undefind> unfindDao;

	private Service() {
		diaryDao = new DiaryDaoImpl();
		resultDao = new ResultDaoImpl();
		unfindDao = new UndefindDaoImpl();
	};

	private static Service instance = null;

	public static Service getInstance() {
		if (instance == null) {
			synchronized (SpiderManager.class) {
				if (instance == null) {
					instance = new Service();
				}
			}
		}
		return instance;
	}

	public Diary getRandomDiary() {
		long max = diaryDao.queryIdMax();
		Diary diary=null;
		do{
			long id=nextLong(new Random(), max);
			diary=diaryDao.findOne(Filters.eq(DBHelper.DiaryCollection.USER_ID, id));
		}while(diary==null || diary.getAccountStatu()!=1 || diary.getIsPublic()!=1);
		System.out.println(diary.getUserId());
		return diary;

	}

	public long nextLong(Random rng, long n) {
		long bits, val;
		do {
			bits = (rng.nextLong() << 1) >>> 1;
			val = bits % n;
		} while (bits - val + (n - 1) < 0L);
		return val;
	}
}
