package com.mongo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongo.dao.MenuDao;
import com.mongo.entity.Menu;
import com.mongo.entity.Page;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class MenuController {

	@Autowired
	MenuDao menuDao;

	
	@ResponseBody
	@RequestMapping(value = "/admin/menu/leftmenu")
	public Object leftmenu(HttpServletRequest request,HttpServletResponse response) {
		List<Menu> l = menuDao.getUserMenu("1");
		return l;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/menu/findall")
	public Object findall(HttpServletRequest request,HttpServletResponse response) {
		List<Menu> l = menuDao.findAll();
		return l;
	}

	
	@ResponseBody
	@RequestMapping(value = "/admin/menu/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,Menu menu) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = menuDao.findPage(page, menu);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/menu/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(menuDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", false);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/menu/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,Menu menu) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/menu/edit");
			mav.addObject("page", menuDao.find(id));
			return mav;
		}else{
			if(menu.getId() == null || "".equals(menu.getId())){
				menu.setId(null);
				menuDao.save(menu);
			}else{
				menuDao.update(menu);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}
	
}
