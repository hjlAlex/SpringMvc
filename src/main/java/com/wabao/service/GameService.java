/** 广州哇宝信息技术有限公司 */
package com.wabao.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wabao.bean.PageBean;
import com.wabao.dao.GameDao;
import com.wabao.entity.Game;

/**
 * (统一在service层使用事务管理,并且到每个方法上,注意事务是否是只读还是可读可写,确保事务的完整性)
 * (ps:特别要注意,在一个开启了可读可写的事务方法内,必须严格地根据业务需要考虑从数据库中查询出来的结果,是否针对必要调用其中的对象的set方法,
 * 否者严禁使用,因为整个开启了可读可写事务方法在执行完成后,没有Exception情况下,所有相关的事务都会提交,所以当边查询数据库结果,
 * 边修改查询出来的结果时格外留意,这种操作是否属于当前业务需要,否者会发生业务异常,很影响业务)
 * 
 * @since 2017年11月18日 下午5:15:05
 * @author Administrator
 */
@Service
public class GameService {
	@Autowired
	private GameDao gameDao;

	@Transactional(readOnly = true)
	public PageBean<Game> find(String hql, int currentPage, int pageSize) {
		PageBean<Game> page = gameDao.find(hql, currentPage, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public PageBean<Game> findPage(String hql, Map<String, Object> params,
			int currentPage, int pageSize) {
		PageBean<Game> page = gameDao.find(hql, params, currentPage, pageSize);
		return page;
	}

	@Transactional
	public Object addGame() {
		Game game = new Game();
		game.setName("空战帝国");
		game.setUrl("xxx");
		Object result = gameDao.save(game);
		return result;
	}
}
