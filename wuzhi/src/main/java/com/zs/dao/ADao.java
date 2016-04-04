package com.zs.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public abstract class ADao<T> implements Dao<T>{
	MongoCollection<Document>  dbCollection;
	
	
	public List<T> getAllItem() {
		FindIterable<Document> docs=dbCollection.find();
		return parse(docs);
	
	}
	
	public T findOne(Bson query){
		Document doc=dbCollection.find(query).first();
		if(doc==null){
			return null;
		}
		return parstItemFromCursor(doc);
	}
	
	public List<T> findMany(Bson query){
		FindIterable<Document> docs=dbCollection.find(query);
		
		return parse(docs);
	}
	
	public List<T> parse(FindIterable<Document> docs){
		List<T> list = new ArrayList<T>();
		MongoCursor<Document> cursors=docs.iterator();
		while(cursors.hasNext()){
			Document doc=cursors.next();
			list.add(parstItemFromCursor(doc));
		}
		cursors.close();
		return list;
	}

}
