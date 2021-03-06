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

import com.mongo.dao.ChannelDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Channel;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class ChannelController {

	@Autowired
	ChannelDao channelDao;

	

	@ResponseBody
	@RequestMapping(value = "/admin/cms/channel/findall")
	public Object findall(HttpServletRequest request,HttpServletResponse response) {
		List<Channel> l = channelDao.findAll();
		return l;
	}

	
	@ResponseBody
	@RequestMapping(value = "/admin/cms/channel/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,Channel channel) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = channelDao.findPage(page, channel);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/channel/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(channelDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", false);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/channel/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,Channel channel) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/admin/cms/channel/edit");
			mav.addObject("page", channelDao.find(id));
			return mav;
		}else{
			if(channel.getId() == null || "".equals(channel.getId())){
				channel.setId(null);
				channelDao.save(channel);
			}else{
				channelDao.update(channel);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}
	
}
