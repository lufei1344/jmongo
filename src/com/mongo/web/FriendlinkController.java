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

import com.mongo.dao.FriendlinkDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Friendlink;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class FriendlinkController {

	@Autowired
	FriendlinkDao friendlinkDao;

	
	@ResponseBody
	@RequestMapping(value = "/admin/cms/friendlink/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,Friendlink friendlink) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = friendlinkDao.findPage(page, friendlink);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/friendlink/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(friendlinkDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", true);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/friendlink/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,Friendlink friendlink) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/admin/cms/friendlink/edit");
			mav.addObject("page", friendlinkDao.find(id));
			return mav;
		}else{
			if(friendlink.getId() == null || "".equals(friendlink.getId())){
				friendlink.setId(null);
				friendlinkDao.save(friendlink);
			}else{
				friendlinkDao.update(friendlink);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}

	
}
