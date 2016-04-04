package com.zs.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DBHelper {

	private static DBHelper mInstance = new DBHelper();
	public static final String DB_NAME = "wuzhi";
	private static final String USER_NAME = "root";
	private static final String PASWORD = "bcad8c";

	private static ThreadLocal<MongoDatabase> db = new ThreadLocal<MongoDatabase>();


	public static DBHelper getInstance() {
		return mInstance;
	}

	public  MongoDatabase getIsmartDB() {
		MongoDatabase ismartDB = null;
		try {
			ismartDB = db.get();
			if (ismartDB == null) {
				MongoClient client=new MongoClient("127.0.0.1", 27017);
				ismartDB=client.getDatabase(DB_NAME);
				db.set(ismartDB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ismartDB;
	}


	public static class DiaryCollection {
		public static final String COLLECTION_NAME = "Diary";
		public static final String ID = "_id";
		public static final String USER_ID = "userId";
		public static final String USER_NAME = "userName";
		public static final String DIARY_CONTENT = "diaryContent";
		public static final String ACCOUNT_STATU = "accountStatu";
		public static final String IMG = "img";
		public static final String IS_PUBLIC = "isPublic";
		public static final String LAST = "last";
		public static final String SIGN = "sign";
		public static final String STAR = "star";
	}
	
	public static class UndefindCollection {
		public static final String COLLECTION_NAME = "Undefind";
		public static final String ID = "_id";
		public static final String USER_ID = "userId";
	}
	
	
	public static class ResultCollection {
		public static final String COLLECTION_NAME = "Result";
		public static final String ID = "_id";
		public static final String MAX_STAR = "maxstar";
		public static final String INVALID_COUNT= "invalidCount";
		public static final String PRIVATE_USER_COUNT = "privateCount";
		public static final String PUBLIC_USER_COUNT = "publicCount";
		public static final String DATE = "time";
	}
	
	

}
