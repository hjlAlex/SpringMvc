/** 广州哇宝信息技术有限公司 */
package com.wabao.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wabao.dao.GameDao;
import com.wabao.entity.Game;
import com.wabao.service.GameService;
import com.xiaoleilu.hutool.json.JSONUtil;

/**
 * SpringJUnit环境下使用事务注解@Transactional默认都是回滚的,如果想要不会滚,使用注解@Rollback(false)
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

	@Autowired
	private GameDao gameDao;

	@Autowired
	private SessionFactoryImpl sessionFactoryImpl;

	@Test
	public void gameTestFind() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 2);
		System.out.println(JSONUtil.toJsonStr(gameService
				.findPage("from Game where id <= :id", params, 2, 2)));
	}

	@Transactional
	@Test
	public void gameTestSave() {
		Game game = new Game();
		game.setName("mybatis还好");
		gameDao.save(game);
		System.out.println(1);
	}

	@Test
	public void gameTestAdd() {
		System.out.println(gameService.addGame());
	}

	@Test
	public void sessionFactoryTest() {
		Properties properties = sessionFactoryImpl.getProperties();
		for (Object key : properties.keySet()) {
			System.out.println(key + ":" + properties.get(key));
		}
	}
}
