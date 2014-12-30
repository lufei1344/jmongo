package com.mongo.db;

import java.net.UnknownHostException;  
import com.mongodb.DB;  
import com.mongodb.DBCollection;  
import com.mongodb.Mongo;  
import com.mongodb.MongoException;  

public class DBUtils {
	protected String collectionsName = "root";
	protected String dbName = "root";
	protected DBCollection coll = null;
	protected Mongo m = null;

	public DBUtils() {
		try {
			coll = getDBCollection(collectionsName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DBCollection getDBCollection(String collectionName) {
		DBCollection coll = null;
		try {
			m = new Mongo("localhost", 27017);
			DB db = m.getDB(dbName);
			coll = db.getCollection(collectionName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return coll;
	}

}
