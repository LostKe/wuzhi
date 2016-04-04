package com.zs.dao;

import java.util.List;

import com.zs.wuzhi.bean.Diary;

public interface DiaryDao extends Dao<Diary>{
	
	public List<Diary> queryStarMax();

}
