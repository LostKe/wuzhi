package com.zs.dao;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoDatabase;
import com.zs.db.DBHelper;
import com.zs.wuzhi.bean.Result;

public class ResultDaoImpl extends ADao<Result> implements ResultDao{
	
	protected final Log logger = LogFactory.getLog(getClass());
	

	public ResultDaoImpl() {
		MongoDatabase database=DBHelper.getInstance().getIsmartDB();
		dbCollection=database.getCollection(DBHelper.ResultCollection.COLLECTION_NAME);
	}

	public void insertItem(Result obj) {
		dbCollection.drop();
		Document document=new Document();
		document.put(DBHelper.ResultCollection.INVALID_COUNT, obj.getInvalidCount());
		document.put(DBHelper.ResultCollection.PRIVATE_USER_COUNT, obj.getPrivateCount());
		document.put(DBHelper.ResultCollection.PUBLIC_USER_COUNT, obj.getPublicCount());
		document.put(DBHelper.ResultCollection.MAX_STAR, JSON.toJSONString(obj.getMaxstars()));
		document.put(DBHelper.ResultCollection.DATE, new Date());
		dbCollection.insertOne(document);
	}

	public long getCount() {
		return dbCollection.count();
	}

	public Result parstItemFromCursor(Document doc) {
		Result result=new Result();
		result.setInvalidCount(doc.getInteger(DBHelper.ResultCollection.INVALID_COUNT));
		String context=doc.getString(DBHelper.ResultCollection.MAX_STAR);
		result.setMaxstars(JSON.parseArray(context, Long.class));
		result.setPrivateCount(doc.getInteger(DBHelper.ResultCollection.PRIVATE_USER_COUNT));
		result.setPublicCount(doc.getInteger(DBHelper.ResultCollection.PUBLIC_USER_COUNT));
		return result;
	}

	
}
