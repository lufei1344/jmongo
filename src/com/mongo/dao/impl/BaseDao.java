package com.mongo.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongo.entity.Page;

@Repository
public class BaseDao {  
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public BaseDao(){}
	
	public BaseDao(MongoTemplate mongoTemplate){
		this.mongoTemplate = mongoTemplate;
	}
	
    public <T> List<T> find(Class<T> clazz) {  
        return mongoTemplate.find(new Query(), clazz);  
    }
    public <T> List<T> find(Class<T> clazz,Query query) {  
    	return mongoTemplate.find(query, clazz);  
    }
    
    public <T> T findOne(Class<T> clazz,String id) {  
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), clazz);  
    }
  
    @SuppressWarnings("unchecked")
	public Object findAndModify(String id,Object obj) {
    	Field[] fs = getFields(obj.getClass());
    	Field f = null;
    	Update  update = new Update();
    	Class c = obj.getClass();
    	for(int i=0; i<fs.length; i++){
    		f = fs[i];
    		if("serialVersionUID".equals(f.getName())||"id".equals(f.getName())){
    			continue;
    		}
    		try {
    			Object v = c.getDeclaredMethod("get"+upperFirstChar(f.getName()), null).invoke(obj, null);
				if(v != null){
					update.set(f.getName(), v);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
    	}
    	
    	//WriteResult res = mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)), update, obj.getClass());
    	obj = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(id)), update, obj.getClass());
    	//res.
    	return obj;
    }  
   
    public Object save(Object obj) {  
        mongoTemplate.insert(obj); 
        return obj;
    }  
  
      
    public <T> void  removeAll(Class<T> clazz) {  
    	mongoTemplate.remove(new Query(), clazz);
    }  
  
      
    public <T> void removeOne(Class<T> clazz ,String id){  
        Criteria criteria = Criteria.where("id").in(id);
        mongoTemplate.remove(new Query(criteria),clazz);
    }  
    
    public <T>  Page findPage(Class<T> clazz,Page page,Query query){
    	if(page.getPage()<=1){
    		query.skip(0).limit(page.getRows());
    	}else{
    		query.skip((page.getPage()-1)*page.getRows()).limit(page.getRows());
    	}
    	page.setCount(mongoTemplate.count(query, clazz));
		page.setList(mongoTemplate.find(query, clazz));
    	return page;
    }
    
    private Field[] getFields(Class clazz){
		List<Field> f = new ArrayList<Field>();
		Field[] fs = clazz.getDeclaredFields();
		for(Field ff : fs){
			f.add(ff);
		}
		Class superclass = clazz.getSuperclass(); 
		if(superclass != null){
			fs = getFields(superclass);
			for(Field ff : fs){
				f.add(ff);
			}
		}
		return f.toArray(new Field[f.size()]);
	}
    private static String upperFirstChar(String s) {
		char[] chars = s.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}
}
