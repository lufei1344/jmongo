package com.mongo.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongo.dao.UserDao;
import com.mongo.entity.User;

@Controller
@SuppressWarnings("unchecked")
public class LoginController {

	@Autowired
	UserDao userDao;

	
	@ResponseBody
	@RequestMapping(value = "/login",produces = "application/json")
	public Object login(HttpServletRequest request,HttpServletResponse response,User user) {
		user = userDao.validate(user);
		Map<String,Object> msg = new HashMap<String,Object>();
		if(user != null){
			request.getSession().setAttribute("user", user);
			msg.put("status", true);
		}else{
			msg.put("status", false);
			msg.put("message", "用户名或密码错误");
		}
		return msg;
	}

	
}
