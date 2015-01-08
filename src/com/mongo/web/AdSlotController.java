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

import com.mongo.dao.AdSlotDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.AdSlot;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class AdSlotController {

	@Autowired
	AdSlotDao adSlotDao;

	
	@ResponseBody
	@RequestMapping(value = "/admin/cms/adslot/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,AdSlot adSlot) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = adSlotDao.findPage(page, adSlot);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/adslot/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(adSlotDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", true);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/adslot/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,AdSlot adSlot) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/admin/cms/adslot/edit");
			mav.addObject("page", adSlotDao.find(id));
			return mav;
		}else{
			if(adSlot.getId() == null || "".equals(adSlot.getId())){
				adSlot.setId(null);
				adSlotDao.save(adSlot);
			}else{
				adSlotDao.update(adSlot);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}

	
}
