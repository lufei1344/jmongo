import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongo.dao.RoleDao;
import com.mongo.dao.UserDao;
import com.mongo.dao.impl.BaseDao;
import com.mongo.entity.Menu;
import com.mongo.entity.Page;
import com.mongo.entity.User;
import com.mongodb.util.JSON;


public class T {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws Exception {
		//remove();
		//add();
		//page();
		//fs();
		//update();
		//find();
		role();
	}
	
	public static void role(){
		String id = "54a0eddeb75bae91235a7deb";
		RoleDao dao = SpringUtil.getBean("roleDao");
/*		Role r = new Role();
		r.setDescription("系统");
		r.setName("系统");
		dao.save(r);
		dao.saveMenu(r.getId(), "549d19f20a6ac18b4f7146c6,549d19f20a6ac18b4f7146c7,549d19f20a6ac18b4f7146c8,549d19f20a6ac18b4f7146c9");*/
		List<Menu> ml = dao.roleMenu(id);
		for(Menu m : ml){
			System.out.println(m.getName());
		}
		System.out.println(JSON.serialize(ml));
	}
	public static void find(){
		String id = "549a7a86f246caa6e33f1007";
		UserDao userDao = SpringUtil.getBean("userDao");
		User u = userDao.find(id);
		System.out.println(u.getName());
	}
	
	public static void add(){
		UserDao userDao = SpringUtil.getBean("userDao");
		for(int i=0; i<20; i++){
			User u = new User();
			u.setAccount("admin-"+i);
			u.setName("系统-"+i);
			u.setPassword("admin="+i);
			System.out.println(u.getId());
			u = userDao.save(u);
			System.out.println(u.getId());
		}
		
		
	}
	public static void page(){
		UserDao userDao = SpringUtil.getBean("userDao");
		Page page = new Page();
		page.setRows(10);
		page.setPage(3);
		User user = new User();
		page = userDao.findPage(page, user);
		List<User> list = page.getList();
		for(User u : list){
			System.out.println(u.getName());
		}
		
	}
	
	public static void update(){
		BaseDao dao  = SpringUtil.getBean("baseDao");
		User u = new User();
		u.setName("修改草案");
		u.setDescription("测试");
		u.setCreated(new Date());
		dao.findAndModify("549bb08c05e6256d67f45e19", u);
	}
	
	public static void fs() throws Exception{
		User obj = new User();
		obj.setName("ss");
		Class c = obj.getClass();
    	Field[] fs = getFields(obj.getClass());
    	List<Object> va = new ArrayList<Object>();
    	Field f = null;
    	for(int i=0; i<fs.length; i++){
    		f = fs[i];
    		System.out.println(c.getDeclaredMethod("get"+upperFirstChar(f.getName()), null).invoke(obj, null));
    	}
	}
	
	  private static Field[] getFields(Class clazz){
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
	public static void remove(){
		BaseDao dao  = SpringUtil.getBean("baseDao");
		dao.removeAll(User.class);
		
		
	}

}
