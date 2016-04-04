package com.zs.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.zs.db.DBHelper;
import com.zs.wuzhi.bean.Undefind;

public class UndefindDaoImpl extends ADao<Undefind> {
	protected final Log logger = LogFactory.getLog(getClass());

	public UndefindDaoImpl() {
		MongoDatabase database=DBHelper.getInstance().getIsmartDB();
		dbCollection=database.getCollection(DBHelper.UndefindCollection.COLLECTION_NAME);
	}


	public void insertItem(Undefind undefind) {
		Document document=new Document();
		document.put(DBHelper.UndefindCollection.USER_ID, undefind.getUserId());
		dbCollection.replaceOne(Filters.eq(DBHelper.UndefindCollection.USER_ID, undefind.getUserId()), document, new UpdateOptions().upsert(true));
		logger.info("[Undefind-->"+undefind.getUserId()+":插入完成]");
	}
	
	
	
	public long getCount(){
		return dbCollection.count();
	}


	public Undefind parstItemFromCursor(Document doc) {
		Undefind un=new Undefind();
		un.setUserId(doc.getLong(DBHelper.UndefindCollection.USER_ID));
		return un;
	}
	
	
}
