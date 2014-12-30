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

import com.mongo.dao.OrganizationDao;
import com.mongo.entity.Organization;
import com.mongo.entity.Page;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class OrganizationController {

	@Autowired
	OrganizationDao organizationDao;

	

	@ResponseBody
	@RequestMapping(value = "/admin/organization/findall")
	public Object findall(HttpServletRequest request,HttpServletResponse response) {
		List<Organization> l = organizationDao.findAll();
		return l;
	}

	
	@ResponseBody
	@RequestMapping(value = "/admin/organization/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,Organization organization) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = organizationDao.findPage(page, organization);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/organization/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(organizationDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", false);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/organization/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,Organization organization) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/organization/edit");
			mav.addObject("page", organizationDao.find(id));
			return mav;
		}else{
			if(organization.getId() == null || "".equals(organization.getId())){
				organization.setId(null);
				organizationDao.save(organization);
			}else{
				organizationDao.update(organization);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}
	
}
