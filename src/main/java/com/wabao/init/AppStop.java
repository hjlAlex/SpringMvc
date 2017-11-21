/** 广州哇宝信息技术有限公司 */
package com.wabao.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * @since 2017年11月16日 上午11:30:28
 * @author Administrator
 */
@Component
public class AppStop implements ApplicationListener<ContextClosedEvent> {
	private static Logger log = LoggerFactory.getLogger(AppStop.class);

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		// 有些时候该方法会被执行两次,一个是Root容器,一个是Spring容器,这种情况为避免执行两次可以通过获取event.getApplicationContext().getDisplayName()判断进行处理
		log.info("SpringMVC容器关闭成功...");
		log.info("系统关闭中...");
	}

}
