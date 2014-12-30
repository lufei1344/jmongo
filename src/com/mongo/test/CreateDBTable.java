package com.mongo.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongo.dao.impl.BaseDao;
import com.mongo.entity.Menu;
import com.mongo.entity.Organization;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class CreateDBTable {
	public static void main(String[] args) {
		//test();
		menu();
		/*BaseDao dao = SpringUtil.getBean("baseDao");
		List<Menu> l = dao.find(Menu.class);
		for(Menu m : l){
			System.out.println(m.getName());
		}*/
		
		//up();
	}

	public static void up(){
		MongoTemplate mongoTemplate = SpringUtil.getBean("mongoTemplate");
		
		mongoTemplate.execute(Organization.class,new CollectionCallback(){

			@Override
			public Object doInCollection(DBCollection dbc)
					throws MongoException, DataAccessException {
				System.out.println(dbc);
				dbc.find();
				return null;
			}
			
		});
	}
	
	public static void menu(){
		try {
			BaseDao dao = SpringUtil.getBean("baseDao");
			dao.removeAll(Menu.class);
			Menu m1 = new Menu();
			m1.setName("系统管理");
			m1.setUrl("javascript:void(0);");
			m1.setIconCls("icon-sys");
			m1.setPid(null);
			m1.setIsused("Y");
			m1.setMyid("sysMgr");
			
			dao.save(m1);
			
			Menu m2 = new Menu();
			m2.setName("用户管理");
			m2.setUrl("jsp/user/list.jsp");
			m2.setIconCls("icon-sys");
			m2.setPid(m1.getId());
			m2.setIsused("Y");
			m2.setMyid("userMgr");

			Menu m3 = new Menu();
			m3.setName("菜单管理");
			m3.setUrl("jsp/menu/list.jsp");
			m3.setIconCls("icon-sys");
			m3.setPid(m1.getId());
			m3.setIsused("Y");
			m3.setMyid("menuMgr");

			Menu m4 = new Menu();
			m4.setName("字典管理");
			m4.setUrl("jsp/systemCode/list.jsp");
			m4.setIconCls("icon-sys");
			m4.setPid(m1.getId());
			m4.setIsused("Y");
			m4.setMyid("systemCodeMgr");
			
			Menu m5 = new Menu();
			m5.setName("部门管理");
			m5.setUrl("jsp/organization/list.jsp");
			m5.setIconCls("icon-sys");
			m5.setPid(m1.getId());
			m5.setIsused("Y");
			m5.setMyid("organizationMgr");
			
			Menu m6 = new Menu();
			m6.setName("角色管理");
			m6.setUrl("jsp/role/list.jsp");
			m6.setIconCls("icon-sys");
			m6.setPid(m1.getId());
			m6.setIsused("Y");
			m6.setMyid("roleMgr");
			
			List<Menu> child = new ArrayList<Menu>();
			child.add(m2);
			child.add(m3);
			//m1.setChild(child.toArray(new Menu[2]));
			
			
			dao.save(m2);
			dao.save(m3);
			dao.save(m4);
			dao.save(m5);
			dao.save(m6);
			//m1.setChild(child.toArray(new Menu[2]));
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test(){
		try {
			// connect to mongoDB
			Mongo mongo = new Mongo("localhost", 27017);

			// 类似数据库名
			DB db = mongo.getDB("mydb");
			db.addUser("admin", "admin".toCharArray());
			// 类似数据表名
			DBCollection collection = db.getCollection("caonima");

			for(int i=0;i<1000;i++){
				// create a document to store attributes
				BasicDBObject document = new BasicDBObject();
				document.put("id", i);
				document.put("name", "lmx"+i);
				document.put("message", "hello "+i);
				document.put("score", i);
				// save it into collection named "myCollection"
				collection.insert(document);
			}
			

			// search query
			BasicDBObject searchQuery = new BasicDBObject();
			//searchQuery.put("id", 100);

			DBCursor cursor = collection.find(searchQuery);

			// loop over the cursor and display the result
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}
