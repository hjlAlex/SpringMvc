package com.wabao.timer;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoleilu.hutool.date.DateUtil;

/**
 * 每分钟任务
 * 
 * @since 2016年6月23日 上午11:38:25
 * @author hjielong
 */
@Transactional
public class QuartzMinute {
	private static final Logger log = LoggerFactory
			.getLogger(QuartzMinute.class);
	private static final Object LOCK = new Object();

	public void minuteWork() {
		synchronized (LOCK) {
			Date d = new Date();
			log.info("------< Quartz的分钟任务调度！调用时间:"
					+ DateUtil.format(d, "yyyy-MM-dd HH:mm:ss") + ">------");

			log.info("分钟执行任务列表开始...");

			log.info("分钟执行任务列表结束...");
		}
	}

}
