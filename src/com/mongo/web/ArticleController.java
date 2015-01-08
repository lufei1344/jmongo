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

import com.mongo.dao.ArticleDao;
import com.mongo.entity.Page;
import com.mongo.entity.cms.Article;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class ArticleController {

	@Autowired
	ArticleDao articleDao;

	
	@ResponseBody
	@RequestMapping(value = "/admin/cms/article/list")
	public Object list(HttpServletRequest request,HttpServletResponse response,Page page,Article article) {
		if(page == null){
			page = new Page();
		}
		//ModelAndView mav = new ModelAndView("/jsp/user/list");
		//mav.addObject("page", userDao.findPage(page, user));
		GridModel m = new GridModel();
		page = articleDao.findPage(page, article);
		m.setRows(page.getList());
		m.setTotal(page.getCount());
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/article/del")
	public Object del(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("title", "提示");
		if(articleDao.remove(id)){
			res.put("message", "删除成功");
			res.put("status", true);
		}else{
			res.put("message", "删除失败");
			res.put("status", true);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/cms/article/edit")
	public Object edit(HttpServletRequest request,HttpServletResponse response,Article article) {
		String id = request.getParameter("id");
		if("GET".equals(request.getMethod().toString().toUpperCase())){
			ModelAndView mav = new ModelAndView("/jsp/admin/cms/article/edit");
			mav.addObject("page", articleDao.find(id));
			return mav;
		}else{
			if(article.getId() == null || "".equals(article.getId())){
				article.setId(null);
				articleDao.save(article);
			}else{
				articleDao.update(article);
			}
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("message", "保存成功");
			res.put("title", "提示");
			res.put("status", true);
			return res;
		}
		
	}

	
}
