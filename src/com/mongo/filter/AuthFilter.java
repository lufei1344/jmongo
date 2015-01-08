package com.mongo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;

import com.mongo.entity.User;
import com.mongo.web.LoginController;




public class AuthFilter implements Filter{
		private List<String> uauth = new ArrayList<String>();
		public void destroy() {
			
		}

		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain filter) throws IOException, ServletException {
			HttpServletRequest req=(HttpServletRequest)request;
			HttpServletResponse res=(HttpServletResponse)response;
			
			String pro = req.getScheme();//协议类型 https
			
			String path=req.getServletPath();
			String[] url =  path.split("/");
			if(uauth.contains(url[url.length-1]) ){
				filter.doFilter(request, response);
				return;
			}
			if(path.endsWith(".action")){
				if(((path.endsWith("action"))||path.endsWith("sys/index.action"))
						&& (req.getSession(true).getAttribute(LoginController.LOGIN_USER)==null)){
					if ("XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With")) || request.getParameter("ajax") != null) {
						response.setCharacterEncoding("UTF-8");
						StringBuffer json = new StringBuffer();
						json.append("{");
						json.append("\"statusCode\":\"301\",\"message\":\"登录超时，请重新登录!\"");
						json.append("}");
						response.getWriter().write(json.toString());
						return;
					}else{
						res.sendRedirect(req.getContextPath()+"/login.jsp");
						return;
					}
				}else{//验证是否有操作权限
					/*User u = (User) req.getSession(true).getAttribute(LoginAction.LOGIN_USER);
					UserMng mng = new UserMng();
					String url = req.getRequestURI().replace(req.getContextPath()+"/", "");
					boolean[] b = mng.auth(String.valueOf(u.getUid()), url);
					if(!nAuthUrl.contains(url) && !b[0]){
						res.sendRedirect(req.getContextPath()+"/noauth.jsp");
						return;
					}else{
						
					}*/
					
				}
			}
			
			
			filter.doFilter(request, response);
		}
		public void init(FilterConfig filterConfig) throws ServletException {
			String s = filterConfig.getInitParameter("uauth");
			String[] url = s.split(",");
			for(int i=0; i<url.length; i++){
				uauth.add(url[i]);
			}
	    }
		
}
