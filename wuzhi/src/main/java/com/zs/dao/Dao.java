package com.zs.dao;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;

public interface Dao<T> {
	
	public static int ORDERBY_ASC = 1;
	public static int ORDERBY_DESC = -1;
	
	public abstract void insertItem(T obj);
	
	
	public abstract long getCount();
	public List<T> getAllItem();
	
	public T findOne(Bson query);
	
	public List<T> findMany(Bson query);
	
	public List<T> parse(FindIterable<Document> docs);
	
	public abstract T parstItemFromCursor(Document doc);

}
