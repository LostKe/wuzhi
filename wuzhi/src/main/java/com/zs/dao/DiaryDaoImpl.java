package com.zs.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.zs.db.DBHelper;
import com.zs.wuzhi.bean.Diary;
import com.zs.wuzhi.bean.DiaryContent;

public class DiaryDaoImpl extends ADao<Diary>  implements DiaryDao  {
	protected final Log logger = LogFactory.getLog(getClass());

	public DiaryDaoImpl() {
		MongoDatabase database=DBHelper.getInstance().getIsmartDB();
		dbCollection=database.getCollection(DBHelper.DiaryCollection.COLLECTION_NAME);
	}

	public void insertItem(Diary diary) {
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
		
		dbCollection.replaceOne(Filters.eq(DBHelper.DiaryCollection.USER_ID, diary.getUserId()), document, new UpdateOptions().upsert(true));
		logger.info("[Diary-->"+diary.getUserId()+":插入完成]");
		
	}

	public long getCount() {
		return dbCollection.count();
	}

	
	public Diary parstItemFromCursor(Document doc) {
		Diary diary=new Diary();
		diary.setUserId(doc.getLong(DBHelper.DiaryCollection.USER_ID));
		diary.setUserName(doc.getString(DBHelper.DiaryCollection.USER_NAME));
		diary.setImg(doc.getString(DBHelper.DiaryCollection.IMG));
		diary.setSign(doc.getString(DBHelper.DiaryCollection.SIGN));
		diary.setStar(doc.getInteger(DBHelper.DiaryCollection.STAR));
		diary.setLast(doc.getDate(DBHelper.DiaryCollection.LAST));
		diary.setIsPublic(doc.getInteger(DBHelper.DiaryCollection.IS_PUBLIC));
		diary.setAccountStatu(doc.getInteger(DBHelper.DiaryCollection.ACCOUNT_STATU));
		String content=doc.getString(DBHelper.DiaryCollection.DIARY_CONTENT);
		diary.setDiaryContent(JSON.parseArray(content, DiaryContent.class));
		return diary;
	}
	
	/**
	 * 获取❤ 数量前10位用户
	 * @return
	 */
	public List<Diary> queryStarMax(){
		FindIterable<Document> docs=dbCollection.find().sort(Filters.eq(DBHelper.DiaryCollection.STAR, ORDERBY_DESC)).limit(10);
		return parse(docs);
	}

	public Long queryIdMax() {
		Document documet=dbCollection.find().sort(Filters.eq(DBHelper.DiaryCollection.USER_ID, ORDERBY_DESC)).first();
		Diary diary=parstItemFromCursor(documet);
		return diary.getUserId();
	}

	
}
