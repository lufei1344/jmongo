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

import com.mongo.dao.UserDao;
import com.mongo.entity.Page;
import com.mongo.entity.User;
import com.mongo.viewmodel.GridModel;

@Controller
@SuppressWarnings("unchecked")
public class WebIndexController {

	@Autowired
	UserDao userDao;

	
	@ResponseBody
	@RequestMapping(value = "/index")
	public Object index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/jsp/web/index");
		//mav.addObject("page", userDao.find(id));
		return mav;
	}


	
}
