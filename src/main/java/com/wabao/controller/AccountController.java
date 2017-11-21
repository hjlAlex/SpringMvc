/** 广州哇宝信息技术有限公司 */
package com.wabao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {
	@RequestMapping("/account_list.html")
	public ModelAndView listAccount(
			@RequestParam(value = "curPage", required = false, defaultValue = "1") int curPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			String account, String channelId, String regTimeStart,
			String regTimeEnd, String loginTimeStart, String loginTimeEnd,
			String status, String testAcc, String trace, String loginIp,
			String imei) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("channels", "1");
		mav.addObject("fmap", "2");
		mav.addObject("pageBean", "3");
		return mav;
	}

}
