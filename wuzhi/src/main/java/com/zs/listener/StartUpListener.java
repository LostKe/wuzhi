package com.zs.listener;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zs.util.BuildHelper;
import com.zs.wuzhi.SpiderManager;

/**
 * 程序启动 加载
 * @author zhangshuqing
 *
 */
public class StartUpListener implements ServletContextListener {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	Timer timer=null;

	public void contextDestroyed(ServletContextEvent arg0) {
		if(timer!=null){
			timer.cancel();
		}
		
	}

	public void contextInitialized(ServletContextEvent event) {
		logger.info("Listener启动");
		//程序启动 爬取初始数据
		if(BuildHelper.needInit()){
			SpiderManager.getInstance().loadData();
		}
		//设置定时器  24:00开始爬数据，更新db
		Timer timer=new Timer();
		
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				logger.info("定时爬取--> 启动 开始爬取数据");
				SpiderManager.getInstance().loadData();
				SpiderManager.getInstance().analysisAllData();
			}
		};
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		timer.schedule(task, cal.getTime());
		SpiderManager.getInstance().analysisAllData();
	}
	
	
	
}
