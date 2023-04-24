package com.ruoyi.web.quartz;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.Threads;
import com.ruoyi.web.service.IKyLegalPersonDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 
* @ClassName: ScheduleService
* @Description: 定时器将任务写入本地磁盘文件，防止下次重启时自动装载
* @author LongYD  
* @date 2021年1月5日 下午4:08:09
* @version V1.0
 */
@Component
@EnableScheduling
public class ScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);
	@Autowired
	private IKyLegalPersonDatabaseService legalPersonDatabaseService;

	@Scheduled(cron = "0 35 2 * * ?")//每天的两点35分执行一次：0 35 2 * * ?//0 0/1 * * * ?每分
	public void synLegalPersonDatabase() {
		try {
			Long startTime=System.currentTimeMillis();
			legalPersonDatabaseService.synLegalPersonDatabaseAll();
			Long endTime=System.currentTimeMillis();
			logger.info("同步法人库信息执行成功，共耗时{}秒",(endTime-startTime)/1000);
		}catch (Exception e){
			logger.info("同步法人库信息执行异常，异常信息为{}",e.getMessage());
		}
	}
	
}
