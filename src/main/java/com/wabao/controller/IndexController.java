/** 广州哇宝信息技术有限公司 */
package com.wabao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wabao.constant.AjaxStatus;
import com.wabao.entity.User;
import com.wabao.json.AjaxJson;
import com.xiaoleilu.hutool.json.JSONUtil;

@Controller
public class IndexController {
	@RequestMapping("/")
	public ModelAndView index(User user) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("channels", "user");
		mav.addObject("fmap", user.getName());
		mav.addObject("pageBean", user.getAge());
		return mav;
	}

	@RequestMapping(value = "/ajaxTest", method = RequestMethod.POST, produces = {
			"text/html;charset=UTF-8" })
	@ResponseBody
	public String ajaxTest(int cid) {
		System.out.println("cid:" + cid);
		AjaxJson aj = new AjaxJson();
		aj.setStatus(AjaxStatus.AJAX_OK);
		aj.setData("你好");
		return JSONUtil.toJsonStr(aj);
	}

}
