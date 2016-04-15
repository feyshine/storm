package com.cn.hnust.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {
	/***
	 * 创建一个定长线程池，支持定时及周期性任务执行;
	 * @param runnable 
	 * @param delayTime 延迟多久执行;
	 * @param period 每隔一定时间执行;
	 * @param size 线程池大小;
	 */
	public static void newScheduledThreadPoolAtFixedRate(Runnable runnable,long delayTime,long period,int size) {
		ScheduledExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(size);
		scheduledThreadPool.scheduleAtFixedRate(runnable, delayTime, period,TimeUnit.SECONDS);
	}
	
	/***
	 * 
	 * @param runnable
	 * @param delayTime
	 * @param size
	 */
	public static void newScheduedThreadPool(Runnable runnable,long delayTime,int size){
		ScheduledExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(size);
		scheduledThreadPool.schedule(runnable, delayTime, TimeUnit.SECONDS);
	}
	
	
	/***
	 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待;
	 * @param runnable 
	 * @param size 线程池大小;
	 */
	public static void newFixedThreadPool(Runnable runnable,int size){
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(size);
		fixedThreadPool.execute(runnable);
	}
	
	/***
	 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 * @param runnable
	 */
	public static void newCachedThreadPool(Runnable runnable){
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		cachedThreadPool.execute(runnable);
	}

}
