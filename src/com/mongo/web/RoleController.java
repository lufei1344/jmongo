package com.mongo.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mongo.dao.MenuDao;
import com.mongo.dao.RoleDao;
import com.mongo.entity.Page;
import com.mongo.entity.Role;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class RoleController {

	@Autowired
	RoleDao roleDao;
	
	@Autowired
	MenuDao menuDao;

	
	@ResponseBody
	@RequestMapping(value = "/admin/role/roleMenu")
	public Object roleMenu(HttpServletRequest request,HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		ModelAndView mav = new ModelAndView("/jsp/role/roleMenu");
		mav.addObject("mids", JSON.toJSONString(roleDao.find(roleId).getMenuIds()));
		mav.addObject("menus", JSON.toJSONString(menuDao.findAll()));
		
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/role/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,Role role) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = roleDao.findPage(page, role);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/role/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(roleDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", false);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/role/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,Role role) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/role/edit");
			mav.addObject("page", roleDao.find(id));
			return mav;
		}else{
			if(role.getId() == null || "".equals(role.getId())){
				role.setId(null);
				roleDao.save(role);
			}else{
				roleDao.update(role);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}

	@ResponseBody
	@RequestMapping(value = "/admin/role/saveMenu")
	public Object saveMenu(HttpServletRequest request,HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String checkedIds = request.getParameter("checkedIds");
		Map<String,Object> res = new HashMap<String,Object>();
		if(roleDao.saveMenu(roleId, checkedIds)){
			res.put("message", "保存成功");
			res.put("status", true);
		}else{
			res.put("message", "保存成功");
			res.put("status", false);
		}
		
		res.put("title", "提示");
		res.put("status", true);
		return res;
		
	}
	
}
