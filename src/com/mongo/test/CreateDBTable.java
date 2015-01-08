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

			Menu m = new Menu();
			m.setName("内容管理");
			m.setUrl("javascript:void(0);");
			m.setIconCls("icon-sys");
			m.setPid(null);
			m.setIsused("Y");
			m.setMyid("cmsMgr");
			
			dao.save(m);
			dao.save(m1);
			
			Menu m2 = new Menu();
			m2.setName("用户管理");
			m2.setUrl("jsp/admin/user/list.jsp");
			m2.setIconCls("icon-sys");
			m2.setPid(m1.getId());
			m2.setIsused("Y");
			m2.setMyid("userMgr");

			Menu m3 = new Menu();
			m3.setName("菜单管理");
			m3.setUrl("jsp/admin/menu/list.jsp");
			m3.setIconCls("icon-sys");
			m3.setPid(m1.getId());
			m3.setIsused("Y");
			m3.setMyid("menuMgr");

			Menu m4 = new Menu();
			m4.setName("字典管理");
			m4.setUrl("jsp/admin/systemCode/list.jsp");
			m4.setIconCls("icon-sys");
			m4.setPid(m1.getId());
			m4.setIsused("Y");
			m4.setMyid("systemCodeMgr");
			
			Menu m5 = new Menu();
			m5.setName("部门管理");
			m5.setUrl("jsp/admin/organization/list.jsp");
			m5.setIconCls("icon-sys");
			m5.setPid(m1.getId());
			m5.setIsused("Y");
			m5.setMyid("organizationMgr");
			
			Menu m6 = new Menu();
			m6.setName("角色管理");
			m6.setUrl("jsp/admin/role/list.jsp");
			m6.setIconCls("icon-sys");
			m6.setPid(m1.getId());
			m6.setIsused("Y");
			m6.setMyid("roleMgr");
			
			List<Menu> child = new ArrayList<Menu>();
			child.add(m2);
			child.add(m3);
			//m1.setChild(child.toArray(new Menu[2]));
			
			
			
			//cms
			Menu m7 = new Menu();
			m7.setName("广告管理");
			m7.setUrl("jsp/admin/cms/ad/list.jsp");
			m7.setIconCls("icon-sys");
			m7.setPid(m.getId());
			m7.setIsused("Y");
			m7.setMyid("adMgr");

			Menu m8 = new Menu();
			m8.setName("广告位管理");
			m8.setUrl("jsp/admin/cms/adslot/list.jsp");
			m8.setIconCls("icon-sys");
			m8.setPid(m.getId());
			m8.setIsused("Y");
			m8.setMyid("adSlotMgr");

			Menu m9 = new Menu();
			m9.setName("文章管理");
			m9.setUrl("jsp/admin/cms/aricle/list.jsp");
			m9.setIconCls("icon-sys");
			m9.setPid(m.getId());
			m9.setIsused("Y");
			m9.setMyid("aricleMgr");

			Menu m10 = new Menu();
			m10.setName("栏目管理");
			m10.setUrl("jsp/admin/cms/channel/list.jsp");
			m10.setIconCls("icon-sys");
			m10.setPid(m.getId());
			m10.setIsused("Y");
			m10.setMyid("channelMgr");

			Menu m11 = new Menu();
			m11.setName("评论管理");
			m11.setUrl("jsp/admin/cms/comment/list.jsp");
			m11.setIconCls("icon-sys");
			m11.setPid(m.getId());
			m11.setIsused("Y");
			m11.setMyid("commentMgr");

			Menu m12 = new Menu();
			m12.setName("配置管理");
			m12.setUrl("jsp/admin/cms/config/list.jsp");
			m12.setIconCls("icon-sys");
			m12.setPid(m.getId());
			m12.setIsused("Y");
			m12.setMyid("configMgr");

			Menu m13 = new Menu();
			m13.setName("友情链接");
			m13.setUrl("jsp/admin/cms/friendlink/list.jsp");
			m13.setIconCls("icon-sys");
			m13.setPid(m.getId());
			m13.setIsused("Y");
			m13.setMyid("friendlinkMgr");
			
			dao.save(m2);
			dao.save(m3);
			dao.save(m4);
			dao.save(m5);
			dao.save(m6);
			dao.save(m7);
			dao.save(m8);
			dao.save(m9);
			dao.save(m10);
			dao.save(m11);
			dao.save(m12);
			dao.save(m13);
			
			
			
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
