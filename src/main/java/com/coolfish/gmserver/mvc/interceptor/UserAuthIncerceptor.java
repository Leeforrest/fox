package com.coolfish.gmserver.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.coolfish.gmserver.common.UserMananger;

/**
 * 用户验证拦截器
 * @author Forrest
 *
 */
public class UserAuthIncerceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 //验证用户是否登陆
        Object obj = request.getSession().getAttribute("id");
        if (obj == null || !(obj instanceof Integer)) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        
        if(!UserMananger.getInstance().check(Integer.parseInt(obj.toString()))) {
        	response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	
}
