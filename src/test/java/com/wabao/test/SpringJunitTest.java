/** 广州哇宝信息技术有限公司 */
package com.wabao.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wabao.service.GameService;
import com.xiaoleilu.hutool.json.JSONUtil;

/**
 * 
 * @since 2017年11月21日 下午2:17:41
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-hibernate.xml",
		"classpath:spring-quartz.xml", "classpath:spring-mvc.xml" })
public class SpringJunitTest {
	@Autowired
	private GameService gameService;

	@Test
	public void gameTestFind() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 2);
		System.out.println(JSONUtil.toJsonStr(gameService
				.findPage("from Game where id <= :id", params, 2, 2)));
	}

	@Test
	public void gameTestSave() {

		System.out.println(gameService.addGame());
	}
}
