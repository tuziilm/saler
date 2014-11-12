package com.zhanghui.saler.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhanghui.saler.common.LoginContext;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		if(uri.startsWith(contextPath)){
			uri=uri.substring(contextPath.length());
		}
		if(uri.startsWith("/callback") || uri.startsWith("/get/") || uri.startsWith("/user/register") || uri.startsWith("/static") ||uri.startsWith("/public") || uri.startsWith("/login")){//static resource or login page or callback interface, not authorize
			return true;
		}else{
			boolean isLogin=LoginContext.checkLogin(request.getSession());
			if(!isLogin)
				response.sendRedirect(contextPath+"/login");
			return isLogin;
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LoginContext.clear();
	}
}
