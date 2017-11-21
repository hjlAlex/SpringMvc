package com.wabao.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ManageInterceptor implements HandlerInterceptor {
	private static Logger log = LoggerFactory
			.getLogger(ManageInterceptor.class);

	/**
	 * 在请求处理之前执行
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		log.info("preHandle...可以进行登录判断,权限判断等");
		// 请求的路径
		String contextPath = request.getContextPath();
		String url = request.getServletPath().toString();// 记录访问路径,可以在转发到登录页面后,用户重新登录成功,立马转发回到此url
		if (request.getRequestURI().endsWith("ajaxTest.htmldd")) {
			response.sendRedirect(contextPath + "/index.html?redirectURL="
					+ URLEncoder.encode(url, "UTF-8"));
			return false;
		}
		return true;
	}

	/**
	 * 该方法将在Controller执行之后，返回视图之前执行，ModelMap表示请求Controller处理之后返回的Model对象
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		log.info("postHandle...");
		log.info("Controller请求完成,可以进行日志记录...");
	}

	/**
	 * 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		log.info("afterCompletion...");

	}

}