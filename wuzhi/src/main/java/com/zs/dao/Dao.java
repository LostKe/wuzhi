package com.zs.dao;

import com.zs.wuzhi.bean.Diary;
import com.zs.wuzhi.bean.Undefind;

public interface Dao {
	void insertDiary(Diary diary);
	
	void insertUndefind(Undefind undefind);
	
	public long getDiaryCount();
}
