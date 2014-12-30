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

import com.mongo.dao.SystemCodeDao;
import com.mongo.entity.Page;
import com.mongo.entity.SystemCode;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class SystemCodeController {

	@Autowired
	SystemCodeDao systemCodeDao;

	

	@ResponseBody
	@RequestMapping(value = "/admin/systemcode/findall")
	public Object findall(HttpServletRequest request,HttpServletResponse response) {
		List<SystemCode> l = systemCodeDao.findAll();
		return l;
	}

	
	@ResponseBody
	@RequestMapping(value = "/admin/systemcode/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,SystemCode systemCode) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = systemCodeDao.findPage(page, systemCode);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/systemcode/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(systemCodeDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", false);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/systemcode/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,SystemCode systemCode) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/systemcode/edit");
			mav.addObject("page", systemCodeDao.find(id));
			return mav;
		}else{
			if(systemCode.getId() == null || "".equals(systemCode.getId())){
				systemCode.setId(null);
				systemCodeDao.save(systemCode);
			}else{
				systemCodeDao.update(systemCode);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}
	
}
