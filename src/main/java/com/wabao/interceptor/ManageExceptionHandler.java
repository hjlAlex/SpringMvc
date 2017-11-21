/** 广州哇宝信息技术有限公司 */
package com.wabao.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.wabao.constant.AjaxStatus;
import com.wabao.json.AjaxJson;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * 全局异常统一处理
 * 
 * @since 2017年11月17日 上午10:58:16
 * @author Administrator
 */
public class ManageExceptionHandler implements HandlerExceptionResolver {
	private static final Logger log = LoggerFactory
			.getLogger(ManageExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exp) {
		log.error("异常捕获", exp);
		// 区分传统的请求和AJAX请求
		String ajaxHeader = request.getHeader("x-requested-with");
		if (StrUtil.isEmpty(ajaxHeader)) {// 传统请求
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("exp", exp);
			mav.addObject("stackMsg", getExpStackString(exp));
			return mav;
		} else {// AJAX请求
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			AjaxJson ajaxJson = new AjaxJson();
			ajaxJson.setStatus(AjaxStatus.AJAX_EXCEPTION);
			JSONObject data = new JSONObject();
			data.put("exp", exp);
			data.put("stackMsg", getExpStackString(exp));
			ajaxJson.setData(data);
			String resp = JSONUtil.toJsonStr(ajaxJson);
			try {
				response.getOutputStream().write(resp.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	private String getExpStackString(Exception exp) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		exp.printStackTrace(ps);
		String stackMsg = baos.toString();
		return stackMsg;
	}
}
