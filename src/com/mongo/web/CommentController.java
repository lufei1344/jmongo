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

import com.mongo.dao.CommentDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Comment;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class CommentController {

	@Autowired
	CommentDao commentDao;

	
	@ResponseBody
	@RequestMapping(value = "/admin/cms/comment/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,Comment comment) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = commentDao.findPage(page, comment);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/comment/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(commentDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", true);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/comment/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,Comment comment) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/admin/cms/config/edit");
			mav.addObject("page", commentDao.find(id));
			return mav;
		}else{
			if(comment.getId() == null || "".equals(comment.getId())){
				comment.setId(null);
				commentDao.save(comment);
			}else{
				commentDao.update(comment);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}

	
}
