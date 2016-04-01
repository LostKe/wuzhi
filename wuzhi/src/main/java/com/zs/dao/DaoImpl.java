package com.zs.dao;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zs.db.DBHelper;
import com.zs.wuzhi.bean.Diary;
import com.zs.wuzhi.bean.Undefind;

public class DaoImpl implements Dao{
	MongoCollection<Document> diaryDocument;
	MongoCollection<Document> unDefindDocument;

	public DaoImpl() {
		MongoDatabase database=DBHelper.getInstance().getIsmartDB();
		diaryDocument=database.getCollection(DBHelper.DiaryCollection.COLLECTION_NAME);
		unDefindDocument=database.getCollection(DBHelper.UndefindCollection.COLLECTION_NAME);
		
		
	}

	public void insertDiary(Diary diary) {
		Document document=new Document();
		document.put(DBHelper.DiaryCollection.USER_ID, diary.getUserId());
		document.put(DBHelper.DiaryCollection.USER_NAME, diary.getUserName());
		document.put(DBHelper.DiaryCollection.IMG, diary.getImg());
		document.put(DBHelper.DiaryCollection.SIGN, diary.getSign());
		document.put(DBHelper.DiaryCollection.STAR, diary.getStar());
		document.put(DBHelper.DiaryCollection.LAST, diary.getLast());
		document.put(DBHelper.DiaryCollection.IS_PUBLIC, diary.getIsPublic());
		document.put(DBHelper.DiaryCollection.ACCOUNT_STATU, diary.getAccountStatu());
		document.put(DBHelper.DiaryCollection.DIARY_CONTENT, JSON.toJSONString(diary.getDiaryContent()));
		diaryDocument.insertOne(document);
		System.out.println("["+diary.getUserId()+":插入完成]");
		
	}

	public void insertUndefind(Undefind undefind) {
		Document document=new Document();
		document.put(DBHelper.UndefindCollection.USER_ID, undefind.getUserId());
		unDefindDocument.insertOne(document);
	}
	
	public long getDiaryCount(){
		return diaryDocument.count();
	}
	
	
	
}
